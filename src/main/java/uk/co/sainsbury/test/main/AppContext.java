package uk.co.sainsbury.test.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import uk.co.sainsbury.test.facade.ProductFacade;
import uk.co.sainsbury.test.facade.impl.DefaultProductFacade;
import uk.co.sainsbury.test.service.ProductService;
import uk.co.sainsbury.test.service.tests.DefaultProductService;

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
     * Get Product Facade Bean object
     * @return
     */
    public ProductFacade getProductFacadeBean()
    {
        final ProductFacade productFacade = (DefaultProductFacade)getInstance().getContext().getBean("productFacade");

        return productFacade;
    }

    /**
     * Gets the Product Service Bean object
     * @return
     */
    public ProductService getProductServiceBean()
    {
        final DefaultProductService productService = (DefaultProductService)getInstance().getContext().getBean("productService");

        return productService;
    }
}
