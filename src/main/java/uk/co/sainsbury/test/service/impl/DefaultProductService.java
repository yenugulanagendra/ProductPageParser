package uk.co.sainsbury.test.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import uk.co.sainsbury.test.data.ProductData;
import uk.co.sainsbury.test.data.Results;
import uk.co.sainsbury.test.expection.ProductException;
import uk.co.sainsbury.test.service.ProductService;


/**
 * Implementation of Product Service
 */
public class DefaultProductService implements ProductService
{

	private static final String PRODUCT = "product";
	private static final String PRODUCT_INNER = "productInner";
	private static final String ANCHOR_TAG = "h3 > a";
	private static final String HREF = "href";
	public static final String PRODUCT_TEXT = "productText";
	public static final String PRICE_PER_UNIT = "pricePerUnit";
	public static final String UNIT = "/unit";
	public static final String KB = " kb";

	public List<ProductData> getProductDataFromURL(final String url) throws ProductException
	{
		List<ProductData> productDatas = new ArrayList<ProductData>();
		try
		{
			WebDriver parentFireFoxDriver = new FirefoxDriver();
			parentFireFoxDriver.get(url);

			//To load the page
			Thread.sleep(5000);

			// get elements from page
			List<WebElement> elements = getListOfProducts(parentFireFoxDriver);
			for (WebElement webElement : elements)
			{
				ProductData productData = new ProductData();
				WebElement productInner = webElement.findElement(By.className(PRODUCT_INNER));

				String unitPrice = getUnitPrice(productInner);
				productData.setUnitPrice(unitPrice);

				WebElement anchor = productInner.findElement(By.cssSelector(ANCHOR_TAG));
				String title = anchor.getText();
				productData.setTitle(title);

				WebDriver childFireFoxDriver = new FirefoxDriver();
				childFireFoxDriver.get(anchor.getAttribute(HREF));

				// To load the page
				Thread.sleep(5000);

				final String sizeOfFile = getProductImageFileSize(childFireFoxDriver);
				productData.setSize(sizeOfFile);

				final String description = childFireFoxDriver.findElement(By.className(PRODUCT_TEXT)).getText();
				productData.setDescription(description);

                childFireFoxDriver.quit();
                parentFireFoxDriver.quit();

				productDatas.add(productData);
			}
		}
		catch (InterruptedException e)
		{
			System.err.println("Oops!! Something went wrong while to load the page.");
            throw new ProductException("Oops!! Something went wrong while to load the page.");
		}
		catch (IOException ioe)
		{
			System.err.println("Oops!! Something went wrong while to load the page.");
            throw new ProductException("Oops!! Something went wrong while to load the page.");
		}

		return productDatas;

	}

	@Override
	public Results calculateTotalsOfProductDatas(final List<ProductData> productDatas)
	{
		Results results = new Results();

		double totalAmt = 0.0;
		for (ProductData productData : productDatas)
		{

            try {
                totalAmt += Double.valueOf(productData.getUnitPrice()).doubleValue();
            } catch (NumberFormatException e)
            {
                throw new NumberFormatException();
            }
        }
		results.setTotal(String.format("%.2f", totalAmt));
		results.setResults(productDatas);

		return results;
	}

	/**
	 * Get the products Information
	 * 
	 * @param driver
	 * @return
	 */
	private List<WebElement> getListOfProducts(WebDriver driver)
	{
		List<WebElement> elements = driver.findElements(By.className(PRODUCT));
		return elements;
	}

	/**
	 * Gets the Unit Price of the products
	 * 
	 * @param webElement
	 * @return Price of unit
	 */
	private String getUnitPrice(WebElement webElement)
	{
		final String unitPriceOfProduct = webElement.findElement(By.className(PRICE_PER_UNIT)).getText();

		return unitPriceOfProduct.substring(1, unitPriceOfProduct.indexOf(UNIT));
	}

	/**
	 * Get the size of product Image
	 * 
	 * @param driver
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private String getProductImageFileSize(WebDriver driver) throws IOException, InterruptedException
	{

		File fileSize = new File("fileSize.txt");

		// File doesn't exists then create a new file
		if (!fileSize.exists())
		{
			fileSize.createNewFile();
		}

		FileWriter fileWriter = new FileWriter(fileSize.getAbsoluteFile());
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write(driver.getPageSource());
		bufferedWriter.close();

		double sizeOfFileInKb = fileSize.length() / 1024;
		fileSize.delete();


		return String.format("%.2f", sizeOfFileInKb) + KB;
	}

}
