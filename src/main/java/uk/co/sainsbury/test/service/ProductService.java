package uk.co.sainsbury.test.service;

import org.springframework.stereotype.Component;
import uk.co.sainsbury.test.data.ProductData;
import uk.co.sainsbury.test.data.ProductUrlData;
import uk.co.sainsbury.test.data.Results;
import uk.co.sainsbury.test.expection.ProductException;

import java.util.List;

/**
 * Product Service for
 */
@Component
public interface ProductService
{
    /**
     * Gets the product Urls from category with product url
     * @param url - Main Product Url
     * @return - Collection Product Url Data object with otherwise empty
     */
    List<ProductUrlData> getProductUrlsFromCategory(final String url) throws ProductException;

    /**
     * Parses the urls from product with product Urls
     * @param productUrls - Collection of product url object.
     * @return Results object with total of products.
     */
    List<ProductData> parseProductUrls(List<ProductUrlData> productUrls) throws ProductException;


}
