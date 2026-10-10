package ecom.bitm.SwagLabsProduct;

import org.testng.annotations.Test;


import org.testng.Assert;
import org.testng.Reporter;

import ecom.bitm.SwagLabs.SwagLabsHamburger;
import ecom.bitm.SwagLabs.SwagLabsLogin;
import ecom.bitm.SwagLabs.SwagLabsProduct;
import ecom.bitm.genericUtility.BaseClass;
import ecom.bitm.genericUtility.DataProviderUtility;



public class ProductNegativeTest extends BaseClass {

    @Test(
        dataProvider = "data",
        dataProviderClass = DataProviderUtility.class,
        groups = {"FT", "Negative"}
    )
    public void verifyProductNotAddedWithoutClickingAddToCart(
            String Username,
            String Password) {

        // Login
        SwagLabsLogin ln = new SwagLabsLogin(driver);
        ln.login(Username, Password);

        // Product Page
        SwagLabsProduct product = new SwagLabsProduct(driver);

        // Do NOT click Add to Cart

        // Verify cart is empty
        Assert.assertEquals(
                product.verifyCartContains1item().getText(),
                "",
                "Cart should be empty when no product is added"
        );

        Reporter.log( "Negative test passed: Product was not added without clicking Add to Cart",true);
        SwagLabsHamburger hb=new SwagLabsHamburger(driver);
	     hb.logout();
    }
}