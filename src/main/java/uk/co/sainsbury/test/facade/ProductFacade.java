package uk.co.sainsbury.test.facade;

import uk.co.sainsbury.test.data.ProductUrlData;
import uk.co.sainsbury.test.data.Results;
import uk.co.sainsbury.test.expection.ProductException;

import java.util.List;

/**
 *
 */
public interface ProductFacade
{
    /**
     * Gets the list of Product Urls available in one category like 'Ripe & ready'
     * @param url - Url to parse the Html page to get specific urls
     * @return - Collection of product urls in specific category.
     */
    List<ProductUrlData> getProductUrls(final String url) throws ProductException;

    /**
     * Converts the product Urls to Product Data objects with size of image, unit price etc.
     * @param productUrls - Collection of Product urls of specific category
     * @return - Results objects contains total price and total no of products
     */
    Results parseProductUrlToDataObject(List<ProductUrlData> productUrls) throws ProductException;
}
