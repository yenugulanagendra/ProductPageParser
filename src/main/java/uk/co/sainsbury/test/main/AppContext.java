package uk.co.sainsbury.test.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uk.co.sainsbury.test.service.JsonBuilderService;
import uk.co.sainsbury.test.service.ProductService;
import uk.co.sainsbury.test.service.impl.DefaultJsonBuilderService;
import uk.co.sainsbury.test.service.impl.DefaultProductService;

/**
 * Loading Application Context
 */
public class AppContext
{
    private static AppContext appContext = null;

    public static AppContext getInstance()
    {
        if(appContext == null)
        {
            appContext = new AppContext();
        }

        return appContext;
    }

    /**
     * Gets the Application Context
     * @return
     */
    private ApplicationContext getContext()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        return context;
    }

    /**
     * Gets the Product Service Bean object
     * @return
     */
    public ProductService getProductServiceBean()
    {
        final ProductService productService = (DefaultProductService)getInstance().getContext().getBean("productService");

        return productService;
    }

    public JsonBuilderService getJsonBuilderServiceBean()
    {
        final JsonBuilderService jsonBuilderService = (DefaultJsonBuilderService)getInstance().getContext().getBean("jsonBuilderService");

        return jsonBuilderService;
    }
}
