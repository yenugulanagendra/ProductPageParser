package uk.co.sainsbury.test.service.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import uk.co.sainsbury.test.data.ProductData;
import uk.co.sainsbury.test.data.Results;
import uk.co.sainsbury.test.service.JsonBuilderService;
import uk.co.sainsbury.test.service.impl.DefaultJsonBuilderService;


/**
 * Json Builder Service Test.
 */
@RunWith(MockitoJUnitRunner.class)
public class JsonBuilderServiceTest
{

	private JsonBuilderService jsonBuilderService;

	@Before
	public void setUp()
	{
		jsonBuilderService = new DefaultJsonBuilderService();
	}

	@Test
	public void check_when_no_products_available()
	{
		final String jsonObject = jsonBuilderService.buildJsonObject(new Results());

		Assert.assertNotNull(jsonObject);
	}

    @Test
    public void when_products_info_available()
    {
        List<ProductData> productDataList = new ArrayList<ProductData>();
        ProductData productData = new ProductData();
        productData.setDescription("Avacodas");
        productData.setSize("1kb");
        productData.setTitle("Avacados");
        productData.setUnitPrice("1.85");
        productDataList.add(productData);

        productData = new ProductData();
        productData.setDescription("Ripe Fruits");
        productData.setSize("2kb");
        productData.setTitle("Kiwi");
        productData.setUnitPrice("1.11");
        productDataList.add(productData);

        Results results = new Results();
        results.setResults(productDataList);

        final String jsonObject = jsonBuilderService.buildJsonObject(results);

        Assert.assertNotNull(jsonObject);

    }
}
