package ecom.bitm.SwagLabsCart;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import ecom.bitm.SwagLabs.SwagLabsCartpage;
import ecom.bitm.SwagLabs.SwagLabsLogin;
import ecom.bitm.SwagLabs.SwagLabsProduct;
import ecom.bitm.genericUtility.BaseClass;
import ecom.bitm.genericUtility.DataProviderUtility;



public class CartpageNegativeTest extends BaseClass {

    @Test(
        dataProvider = "data",
        dataProviderClass = DataProviderUtility.class,
        groups = {"FT", "Negative"}
    )
    public void verifyEmptyCartDoesNotShowProduct(
            String Username,
            String Password) {

        // Login
        SwagLabsLogin ln = new SwagLabsLogin(driver);
        ln.login(Username, Password);

        // Product Page
        SwagLabsProduct product = new SwagLabsProduct(driver);

        // Do NOT add any product
        product.clickCart();

        // Cart Page
        SwagLabsCartpage cart = new SwagLabsCartpage(driver);

        // Verify product is NOT displayed
        Assert.assertNotEquals(product.verifyCartContains1item(),"1");

        Reporter.log( "Negative test passed: Empty cart does not show any product",true);
        driver.navigate().refresh();
    }
}