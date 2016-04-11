package uk.co.sainsbury.test.facade.impl;

import uk.co.sainsbury.test.data.ProductUrlData;
import uk.co.sainsbury.test.data.Results;
import uk.co.sainsbury.test.expection.ProductException;
import uk.co.sainsbury.test.facade.ProductFacade;
import uk.co.sainsbury.test.main.AppContext;
import uk.co.sainsbury.test.service.ProductService;

import java.util.List;

/**
 * Implementation class for Product Facade
 */
public class DefaultProductFacade implements ProductFacade
{

    private ProductService productService;

    public List<ProductUrlData> getProductUrls(final String url) throws ProductException
    {
        return getProductService().getProductUrlsFromCategory(url);
    }


    public Results parseProductUrlToDataObject(final List<ProductUrlData> productUrls) throws ProductException
    {
        return  getProductService().parseProductUrls(productUrls);
    }


    public ProductService getProductService()
    {
        AppContext appContext = AppContext.getInstance();
        this.productService = appContext.getProductServiceBean();
        return productService;
    }

    public void setProductService(final ProductService productService)
    {
        this.productService = productService;
    }
}
