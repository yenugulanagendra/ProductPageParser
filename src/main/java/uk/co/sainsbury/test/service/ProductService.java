package uk.co.sainsbury.test.service;

import java.util.List;

import org.springframework.stereotype.Component;

import uk.co.sainsbury.test.data.ProductData;
import uk.co.sainsbury.test.data.Results;
import uk.co.sainsbury.test.expection.ProductException;

/**
 * Product Service for
 */
@Component
public interface ProductService
{
    /**
     * Gets the product Urls from category with product url
     * @param url - Main Product Url
     * @return - Collection Product Data object with otherwise empty
     */
    List<ProductData> getProductDataFromURL(final String url) throws ProductException;

    /**
     * Calcualtes the total for all products
     * @param productDatas
     * @return - Result object
     */
    Results calculateTotalsOfProductDatas(final List<ProductData> productDatas);
}
