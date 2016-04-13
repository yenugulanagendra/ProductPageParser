package uk.co.sainsbury.test.main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uk.co.sainsbury.test.data.ProductData;
import uk.co.sainsbury.test.data.Results;
import uk.co.sainsbury.test.service.JsonBuilderService;
import uk.co.sainsbury.test.service.ProductService;


/**
 * Product Page Resolver using Spring boot
 */
@SpringBootApplication
public class ProductPageResolver implements CommandLineRunner {

    @Override
    public void run(final String... args) throws Exception {
        String inputUrl = "";
        if (args != null && args.length > 0) {
            inputUrl = args[0];
        }

        if (inputUrl.isEmpty() || validateUrl(inputUrl)) {
            System.out.println("Please provide a valid URL or might be unable to connect with Url ");
        } else {

            AppContext appContext = AppContext.getInstance();
            final ProductService productService = appContext.getProductServiceBean();
            final JsonBuilderService jsonBuilderService = appContext.getJsonBuilderServiceBean();
            try {

                final List<ProductData> productDatas = productService.getProductDataFromURL(inputUrl);
                Results results = productService.calculateTotalsOfProductDatas(productDatas);

                if (productDatas.size() > 0) {
                    jsonBuilderService.buildJsonObject(results);
                } else {
                    System.out.println("Specified product url is either invalid or has no products");
                }

            } catch (IllegalStateException e) {
                System.err.println("Error: An Error has occured while trying to get details of Product Page");
            } catch (Exception e) {
                System.err.println("An Error has occured while trying to get details of Product Page" + e.getMessage());
            }
        }
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(ProductPageResolver.class, args);
    }


    private boolean validateUrl(final String inputUrl) {
        boolean isValidUrl = false;
        try {
            URL url = new URL(inputUrl);
            url.openConnection();
            isValidUrl = true;
        } catch (MalformedURLException e) {
            isValidUrl = false;
        } catch (IOException e) {
            isValidUrl = false;
        }
        return isValidUrl;
    }
}
