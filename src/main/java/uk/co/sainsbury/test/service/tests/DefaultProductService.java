package uk.co.sainsbury.test.service.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import uk.co.sainsbury.test.data.ProductData;
import uk.co.sainsbury.test.data.ProductUrlData;
import uk.co.sainsbury.test.data.Results;
import uk.co.sainsbury.test.expection.ProductException;
import uk.co.sainsbury.test.service.ProductService;


/**
 * Implementation of Product Service
 */
public class DefaultProductService implements ProductService {
    public List<ProductUrlData> getProductUrlsFromCategory(final String url) throws ProductException{
        if (isUrlInValid(url))
        {
            throw new ProductException("Invalid Url, Please give a valid url");
        }
        List<ProductUrlData> noOfProductUrls = new ArrayList<ProductUrlData>();
        try {
            final Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 Chrome/49.0.2623.110 Safari/537.36").get();

            Elements productList = document.select("div#productLister").select("li");
            for (int i = 0; i < productList.size(); i++) {
                boolean insertData = false;
                ProductUrlData productUrlData = new ProductUrlData();
                Element ele = productList.get(i);
                Elements anchorElement = ele.select(".productInfo").select(".productNameAndPromotions").select("h3").select("a");
                if (anchorElement.size() > 0) {
                    productUrlData.setUrl(anchorElement.attr("href"));
                    insertData = true;
                }
                Elements imageElement = ele.select(".productInfo").select(".productNameAndPromotions").select("h3").select("a")
                        .select("img");
                if (imageElement.size() > 0) {

                    final String src = imageElement.attr("src");
                    URL imageUrl = new URL(src);
                    int size = imageUrl.openConnection().getContentLength() / 1024;
                    productUrlData.setSize(String.valueOf(size) + "kb");
                    insertData = true;
                }
                if (insertData) {
                    noOfProductUrls.add(productUrlData);
                }
            }

        } catch (IOException ioe) {
            throw new ProductException("Something went wrong, Please try again");
        }

        return noOfProductUrls;

    }


    public Results parseProductUrls(final List<ProductUrlData> productUrls) throws ProductException{
        Results results = new Results();
        try {
            double totalPrice = 0.0;
            List<ProductData> productDatas = new ArrayList<ProductData>();
            for (ProductUrlData productUrl : productUrls) {
                ProductData productData = new ProductData();
                if(isUrlInValid(productUrl.getUrl()))
                {
                    continue; // If any urls are invalid, then continue to next process.
                }
                final Document document = Jsoup.connect(productUrl.getUrl()).userAgent("Mozilla/5.0 Chrome/49.0.2623.110 Safari/537.36").get();
                final String title = document.select("div.productTitleDescriptionContainer").select("h1").text();
                productData.setTitle(title);
                final String priceUnit = document.select("div.pricing").select("p.pricePerUnit").text();
                final String priceValue = priceUnit.replace("£", "").replace("/unit", "");
                totalPrice = totalPrice + Double.parseDouble(priceValue);
                productData.setUnit_price(priceValue);
                final String description = document.select("div.productText").select("p").get(0).select("p").text();
                productData.setDescription(description);
                productData.setSize(productUrl.getSize());
                productDatas.add(productData);
            }
            results.setResults(productDatas);
            results.setTotal(String.format("%.2f", totalPrice));
        } catch (IOException ioe)
        {
            throw new ProductException("Something went wrong, Please try again");
        }catch (Exception e)
        {
            throw new ProductException("Something went wrong, Please try again");
        }

        return results;
    }


    /**
     * Validates input URL whether its valid or not
     * @param inputUrl
     * @return <true>If its a invalid url </true> otherwise <false>Valid url</false>
     */
    protected boolean isUrlInValid(final String inputUrl) {
        boolean urlInValid = false;
        try {
            URL url = new URL(inputUrl);
            URLConnection conn = url.openConnection();
            conn.connect();

        } catch (MalformedURLException e) {
            urlInValid = true;
        } catch (IOException e) {
            urlInValid = true;
        }
        return urlInValid;
    }

}
