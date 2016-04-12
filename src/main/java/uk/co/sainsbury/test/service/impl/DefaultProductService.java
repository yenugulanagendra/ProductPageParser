package uk.co.sainsbury.test.service.impl;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import uk.co.sainsbury.test.data.ProductData;
import uk.co.sainsbury.test.data.ProductUrlData;
import uk.co.sainsbury.test.data.Results;
import uk.co.sainsbury.test.expection.ProductException;
import uk.co.sainsbury.test.service.ProductService;


/**
 * Implementation of Product Service
 */
public class DefaultProductService implements ProductService
{

    private static final String PRODUCT_INFO = ".productInfo";
    private static final String PRODUCT_NAME_AND_PROMOTIONS = ".productNameAndPromotions";
    private static final String H_3 = "h3";
    private static final String A = "a";
    private static final String IMG = "img";

    public List<ProductUrlData> getProductUrlsFromCategory(final String url) throws ProductException
	{
		if (isUrlInValid(url))
		{
			throw new ProductException("Invalid Url, Please give a valid url");
		}
		WebDriver webDriver = new FirefoxDriver();
		List<ProductUrlData> noOfProductUrls = new ArrayList<ProductUrlData>();
		try
		{
			webDriver.get(url);

			final String pageSource = webDriver.getPageSource();

			final Document document = Jsoup.parse(pageSource);

			Elements productList = document.select("div#productLister").select("li");
			for (int i = 0; i < productList.size(); i++)
			{
				boolean insertData = false;
				ProductUrlData productUrlData = new ProductUrlData();
				Element ele = productList.get(i);
				Elements anchorElement = ele.select(PRODUCT_INFO).select(PRODUCT_NAME_AND_PROMOTIONS).select(H_3).select(A);
				if (anchorElement.size() > 0)
				{
					productUrlData.setUrl(anchorElement.attr("href"));
					insertData = true;
				}
				Elements imageElement = ele.select(PRODUCT_INFO).select(PRODUCT_NAME_AND_PROMOTIONS).select(H_3).select(A).select(IMG);
				if (imageElement.size() > 0)
				{

					final String src = imageElement.attr("src");
					URL imageUrl = new URL(src);
					int size = imageUrl.openConnection().getContentLength() / 1024;
					productUrlData.setSize(String.valueOf(size) + "kb");
					insertData = true;
				}
				if (insertData)
				{
					noOfProductUrls.add(productUrlData);
				}
			}

		}
		catch (IOException ioe)
		{
			throw new ProductException("Something went wrong, Please try again");
		}
		finally
		{
			webDriver.close();
        }

		return noOfProductUrls;

	}


	public List<ProductData> parseProductUrls(final List<ProductUrlData> productUrls) throws ProductException
	{
        List<ProductData> productDatas = new ArrayList<ProductData>();
        try
		{
			for (ProductUrlData productUrl : productUrls)
			{
				ProductData productData = new ProductData();
				if (isUrlInValid(productUrl.getUrl()))
				{
					continue; // If any urls are invalid, then continue to next process.
				}

                WebDriver webDriver = new FirefoxDriver();
                webDriver.get(productUrl.getUrl());
				final Document document = Jsoup.parse(webDriver.getPageSource());

				final String title = document.select("div.productTitleDescriptionContainer").select("h1").text();
				productData.setTitle(title);
				final String priceUnit = document.select("div.pricing").select("p.pricePerUnit").text();
				final String priceValue = priceUnit.replace("£", "").replace("/unit", "");
				productData.setUnitPrice(priceValue);
				final String description = document.select("div.productText").select("p").get(0).select("p").text();
				productData.setDescription(description);
				productData.setSize(productUrl.getSize());
				productDatas.add(productData);
                webDriver.close();
			}
		}
		catch (Exception e)
		{
			throw new ProductException("Something went wrong, Please try again");
		}

		return productDatas;
	}


	/**
	 * Validates input URL whether its valid or not
	 * 
	 * @param inputUrl
	 * @return <true>If its a invalid url </true> otherwise <false>Valid url</false>
	 */
	public boolean isUrlInValid(final String inputUrl)
	{
		boolean urlInValid = false;
		try
		{
			URL url = new URL(inputUrl);
			URLConnection conn = url.openConnection();
			conn.connect();

		}
		catch (MalformedURLException e)
		{
			urlInValid = true;
		}
		catch (IOException e)
		{
			urlInValid = true;
		}
		return urlInValid;
	}

}
