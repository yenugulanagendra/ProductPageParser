package uk.co.sainsbury.test.service.tests;


import org.jsoup.Jsoup;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import uk.co.sainsbury.test.data.ProductData;
import uk.co.sainsbury.test.data.Results;
import uk.co.sainsbury.test.expection.ProductException;
import uk.co.sainsbury.test.service.ProductService;
import uk.co.sainsbury.test.service.impl.DefaultProductService;

import java.util.ArrayList;
import java.util.List;


/**
 * JUnit Product Service Tests
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest
{

	private ProductService productService;

	private static final String INVALID_URL = "anyUrl";

	private static final String VALID_URL_NO_PRODUCTS = "http://www.sainsburys.co.uk";

	private static final String VALID_URL = "http://www.sainsburys.co.uk/shop/gb/groceries/fruit-veg/ripe---ready#langId=44&storeId=10151&catalogId=10122&categoryId=185749&parent_category_rn=12518&top_category=12518&pageSize=20&orderBy=FAVOURITES_FIRST&searchTerm=&beginIndex=0";

	@Mock
	private Jsoup jsoup;

	@Before
	public void setUp()
	{
		productService = new DefaultProductService();
	}


    @Test(expected = RuntimeException.class)
    public void when_execption_is_thrown__exception() throws ProductException
    {
        productService.getProductDataFromURL(INVALID_URL);
    }

    @Test
    public void test_valid_products() throws ProductException
    {
        final List<ProductData> productDataFromURL = productService.getProductDataFromURL(VALID_URL);

        Assert.assertTrue("Size of productData ",productDataFromURL.size()>0);
        Assert.assertNotNull(productDataFromURL.get(0).getDescription());
        Assert.assertNotNull(productDataFromURL.get(0).getSize());
        Assert.assertNotNull(productDataFromURL.get(0).getTitle());
        Assert.assertNotNull(productDataFromURL.get(0).getUnitPrice());
    }


    @Test
    public void calculate_product_totals()
    {
        List<ProductData> productDataList = new ArrayList<ProductData>();
        ProductData productData = new ProductData();
        productData.setDescription("Ripe Fruits");
        productData.setSize("1.33");
        productData.setTitle("Avacado");
        productData.setUnitPrice("1.85");
        productDataList.add(productData);

        productData = new ProductData();
        productData.setDescription("Ripe Fruits");
        productData.setSize("0.1234");
        productData.setTitle("Avacado");
        productData.setUnitPrice("1.10");
        productDataList.add(productData);

        final Results results = productService.calculateTotalsOfProductDatas(productDataList);

        Assert.assertNotNull(results);
        Assert.assertEquals("Total amount of all products",String.valueOf(2.95),results.getTotal());

    }

    @Test(expected = NumberFormatException.class)
    public void calcualtion_totals_throws_number_format_exception()
    {
        List<ProductData> productDataList = new ArrayList<ProductData>();
        ProductData productData = new ProductData();
        productData.setDescription("Ripe Fruits");
        productData.setSize("1.33");
        productData.setTitle("Avacado");
        productData.setUnitPrice("1.85ff");
        productDataList.add(productData);

        productData = new ProductData();
        productData.setDescription("Ripe Fruits");
        productData.setSize("0.1234");
        productData.setTitle("Avacado");
        productData.setUnitPrice("1.10ff");
        productDataList.add(productData);

        productService.calculateTotalsOfProductDatas(productDataList);
    }

}
