package ecom.bitm.SwagLabsHamBurger;

import org.testng.annotations.Test;


import org.testng.Assert;
import org.testng.Reporter;

import ecom.bitm.SwagLabs.SwagLabsHamburger;
import ecom.bitm.SwagLabs.SwagLabsLogin;
import ecom.bitm.SwagLabs.SwagLabsProduct;
import ecom.bitm.genericUtility.BaseClass;
import ecom.bitm.genericUtility.DataProviderUtility;




public class HamburgerNegativeTest extends BaseClass {

    @Test(dataProvider = "data", dataProviderClass = DataProviderUtility.class,groups = {"Negative"})
    public void verifyResetAppStateWithEmptyCart(String Username,String Password) {

        SwagLabsProduct product = new SwagLabsProduct(driver);
        SwagLabsHamburger hamburger = new SwagLabsHamburger(driver);
        SwagLabsLogin login = new SwagLabsLogin(driver);
        login.login(Username, Password);

        // Pre-condition: User is logged in and cart is empty
        Assert.assertEquals(product.verifyCartContains1item().getText(),"", "Cart is not empty before executing the test");

        // Open Hamburger menu
        hamburger.getMenu();

        // Click Reset App State
        hamburger.getRestButton();

        // Verify application remains on Products page
        Assert.assertTrue(product.verifyProductsPage(),"Application is not on Products page");

        // Verify cart is still empty
        Assert.assertEquals(product.verifyCartContains1item().getText(),"", "Cart is not empty before executing the test");
        hamburger.clickCross();
        Reporter.log("Negactive test Passes",true);
        ;
    }
    
   
}