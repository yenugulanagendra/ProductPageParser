package uk.co.sainsbury.test.service;

import uk.co.sainsbury.test.data.ProductUrlData;
import uk.co.sainsbury.test.data.Results;
import uk.co.sainsbury.test.expection.ProductException;

import java.util.List;

/**
 * Product Service for
 */
public interface ProductService
{
    /**
     *
     * @param url
     * @return
     */
    List<ProductUrlData> getProductUrlsFromCategory(final String url) throws ProductException;

    /**
     *
     * @param productUrls
     */
    Results parseProductUrls(List<ProductUrlData> productUrls) throws ProductException;
}
