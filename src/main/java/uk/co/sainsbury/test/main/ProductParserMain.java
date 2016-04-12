package uk.co.sainsbury.test.main;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import uk.co.sainsbury.test.data.ProductUrlData;
import uk.co.sainsbury.test.data.Results;
import uk.co.sainsbury.test.expection.ProductException;
import uk.co.sainsbury.test.facade.ProductFacade;


/**
 * Product Parser Main Page invoker
 */
public class ProductParserMain
{
//	public static void main(String[] args)
//	{
//		String url = "http://www.sainsburys.co.uk/shop/gb/groceries/fruit-veg/ripe---ready#langId=44&storeId=10151&catalogId=10122&categoryId=185749&parent_category_rn=12518&top_category=12518&pageSize=20&orderBy=FAVOURITES_FIRST&searchTerm=&beginIndex=0";
//		try
//		{
//			AppContext appContext = AppContext.getInstance();
//			final ProductFacade productFacade = appContext.getProductFacadeBean();
//			final List<ProductUrlData> productUrls = productFacade.getProductUrls(url);
//
//            if(productUrls.size() > 0) {
//                //Process the products into Json Object
//                Results results = productFacade.parseProductUrlToDataObject(productUrls);
//
//                ObjectMapper mapper = new ObjectMapper();
//
//                final String writeValueAsString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results);
//                System.out.println(writeValueAsString);
//            }else{
//                System.out.println("No products urls are found for the input url  :- "+url);
//            }
//		}
//		catch (ProductException pe)
//		{
//            System.out.println("******************** ERROR Message **************************");
//            System.out.println(pe.getMessage());
//            System.out.println("*************************************************************");
//		}
//		catch (IOException e)
//		{
//			System.out.println(e.getMessage());
//		}
//	}
}
