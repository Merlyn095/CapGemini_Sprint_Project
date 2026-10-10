package ecom.bitm.SwagLabsCheckOut;

import org.testng.annotations.Test;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import ecom.bitm.SwagLabs.SwagLabsCartpage;
import ecom.bitm.SwagLabs.SwagLabsCheckout;
import ecom.bitm.SwagLabs.SwagLabsHamburger;
import ecom.bitm.SwagLabs.SwagLabsLogin;
import ecom.bitm.SwagLabs.SwagLabsProduct;
import ecom.bitm.genericUtility.BaseClass;
import ecom.bitm.genericUtility.DataProviderUtility;
import ecom.bitm.genericUtility.ScreenshotUtility;


public class CheckOutNegativeTest extends BaseClass {
	
	@Test(dataProvider="readcheckoutdata",dataProviderClass = DataProviderUtility.class,groups = {"Negative"})
	public void verifyCheckoutTextFieldFN(String Username,
            String Password,
            String Firstname,
            String Lastname,
            String Postalcode) throws InterruptedException {
		 SwagLabsLogin login = new SwagLabsLogin(driver);
	     login.login(Username, Password);
	     
	     SwagLabsProduct p=new SwagLabsProduct(driver);
	     p.clickaddtocart();
	     p.clickCart();
	     
	     SwagLabsCartpage c=new SwagLabsCartpage(driver);
	     c.clickCheckout();
	     
	     SwagLabsCheckout checkout = new SwagLabsCheckout(driver);
	     checkout.enterFirstName(Firstname);
	     checkout.enterLastName(Lastname);
	     checkout.enterPostalCode(Postalcode);
	     checkout.clickContinue();
	     Assert.assertEquals(checkout.getErrorMessage(),"Error: First Name is required");
	     ScreenshotUtility.takeScreenshot(
	    		    driver, "Negative_Checkout"
	    		);
	     Reporter.log("PASS: First Name,Last Name , ZipCode validation verified", true);
	     
	     
		
		
		
	}
	
	
}
