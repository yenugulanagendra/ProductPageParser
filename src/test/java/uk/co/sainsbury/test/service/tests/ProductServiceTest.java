package uk.co.sainsbury.test.service.tests;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.jsoup.Jsoup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import uk.co.sainsbury.test.data.ProductUrlData;
import uk.co.sainsbury.test.data.Results;
import uk.co.sainsbury.test.expection.ProductException;
import uk.co.sainsbury.test.service.ProductService;


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


	@Test(expected = ProductException.class)
	public void validate_url() throws Exception
	{
		productService.getProductUrlsFromCategory(INVALID_URL);
	}


	@Test
	public void valid_url_doesnot_have_any_product() throws Exception
	{
		final List<ProductUrlData> productUrlsFromCategory = productService.getProductUrlsFromCategory(VALID_URL_NO_PRODUCTS);

		Assert.assertEquals("Valid URL with no products", 0, productUrlsFromCategory.size());
	}


	@Test
	public void validUrl_with_product_information() throws Exception
	{
		final List<ProductUrlData> productUrlsFromCategory = productService.getProductUrlsFromCategory(VALID_URL);

		Assert.assertTrue("Valid URL which has products information", (productUrlsFromCategory.size() > 0));

		DefaultProductService defaultProductService = new DefaultProductService();
		for (ProductUrlData productUrlData : productUrlsFromCategory)
		{
			Assert.assertFalse(defaultProductService.isUrlInValid(productUrlData.getUrl()));
		}
	}


	@Test
	public void parse_urls_with_no_results() throws Exception
	{

		final Results results = productService.parseProductUrls(Collections.<ProductUrlData> emptyList());

		Assert.assertTrue("No Results are returned ", results.getResults().size() == 0);
	}


	@Test
	public void parse_urls_with_invalid_urls() throws Exception
	{
		List<ProductUrlData> productUrlDatas = new ArrayList<ProductUrlData>();
		ProductUrlData productUrlData = new ProductUrlData();
		productUrlData.setUrl(INVALID_URL);
		productUrlDatas.add(productUrlData);

		final Results results = productService.parseProductUrls(productUrlDatas);

		Assert.assertTrue("No Results are returned ", results.getResults().size() == 0);
	}


    @Test
    public void parse_urls_with_valid_products() throws Exception
    {
        final List<ProductUrlData> productUrlsFromCategory = productService.getProductUrlsFromCategory(VALID_URL);

        Assert.assertTrue("Valid URL which has products information", (productUrlsFromCategory.size() > 0));

        final Results results = productService.parseProductUrls(productUrlsFromCategory);

        Assert.assertNotNull(results);
        Assert.assertTrue(results.getResults().size()>0);
    }
}
