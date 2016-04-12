package uk.co.sainsbury.test.main;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uk.co.sainsbury.test.data.ProductData;
import uk.co.sainsbury.test.data.ProductUrlData;
import uk.co.sainsbury.test.service.JsonBuilderService;
import uk.co.sainsbury.test.service.ProductService;


/**
 * Product Page Resolver using Spring boot
 */
@SpringBootApplication
public class ProductPageResolver implements CommandLineRunner
{

	@Override
	public void run(final String... args) throws Exception
	{
		String urlString = "";
		if (args != null && args.length > 0)
		{
			urlString = args[0];
		}

		if (urlString.isEmpty())
		{
			System.out.println("Please provide a valid URL ...to get product details ");
		}
		else
		{

			AppContext appContext = AppContext.getInstance();
			final ProductService productService = appContext.getProductServiceBean();
			final JsonBuilderService jsonBuilderService = appContext.getJsonBuilderServiceBean();
			try
			{

				final List<ProductUrlData> productUrls = productService.getProductUrlsFromCategory(urlString);

				if (productUrls.size() > 0)
				{
					final List<ProductData> productDatas = productService.parseProductUrls(productUrls);

					if (productDatas.size() > 0)
					{
						jsonBuilderService.buildJsonObject(productDatas);
					}
					else
					{
						System.out.println("No products found with supplied url");
					}
				}
				else
				{
					System.out.println("Specified product url is either invalid or has no products");
				}

			}
			catch (IllegalStateException e)
			{
				System.err.println("Error: An Error has occured while trying to get details of Product Page");
			}
			catch (Exception e)
			{
				System.err.println("An Error has occured while trying to get details of Product Page" + e.getMessage());
			}
		}
	}

	public static void main(String[] args) throws Exception
	{
		SpringApplication.run(ProductPageResolver.class, args);
	}
}
