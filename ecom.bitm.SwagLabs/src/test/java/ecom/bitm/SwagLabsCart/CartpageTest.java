

package ecom.bitm.SwagLabsCart;

import org.testng.annotations.Test;






import ecom.bitm.SwagLabs.SwagLabsCartpage;
import ecom.bitm.SwagLabs.SwagLabsLogin;
import ecom.bitm.SwagLabs.SwagLabsProduct;
import ecom.bitm.genericUtility.BaseClass;
import ecom.bitm.genericUtility.DataProviderUtility;

import org.testng.Assert;


import org.testng.Reporter;



public class CartpageTest extends BaseClass {
	@Test(dataProvider = "data", dataProviderClass = DataProviderUtility.class,groups = {"FT", "Smoke"})
	public void verifyCartPage(String Username,String Password) {
		SwagLabsLogin ln=new SwagLabsLogin(driver);
		ln.login(Username,Password);
		SwagLabsProduct p=new SwagLabsProduct(driver);
		SwagLabsCartpage cart=new SwagLabsCartpage(driver);
		p.clickCart();
		Assert.assertEquals(driver.getCurrentUrl().contains("cart.html"),true);
		Reporter.log("Cart is Visible",true);
		driver.navigate().refresh();

	}

	@Test(dataProvider = "data", dataProviderClass = DataProviderUtility.class,groups = {"FT", "Smoke"})
	public void verifyAddProductToCart(String Username,String Password) throws InterruptedException {
		SwagLabsLogin ln=new SwagLabsLogin(driver);
		ln.login(Username,Password);
		SwagLabsProduct p=new SwagLabsProduct(driver);
		SwagLabsCartpage cart=new SwagLabsCartpage(driver);
		p.clickaddtocart();
		p.clickCart();
		Thread.sleep(2000);
		Assert.assertTrue(cart.verifyBackpackDisplayed(),"Backpack is not displayed in Cart");	
		Reporter.log("Product is in  cart  Page",true);
		

	}

	@Test(dataProvider = "data", dataProviderClass = DataProviderUtility.class,groups={"FT","Integration"})
	public void verifyRemoveProductFromCart(String Username,String Password) {
		SwagLabsLogin ln=new SwagLabsLogin(driver);
		ln.login(Username,Password);
		SwagLabsProduct p=new SwagLabsProduct(driver);
		SwagLabsCartpage cart=new SwagLabsCartpage(driver);
		p.clickaddtocart();
		Assert.assertEquals(p.verifyCartContains1item().getText(),"1");
		Reporter.log("Product added successfully",true);
		p.clickCart();
		cart.getRemove();
		Assert.assertEquals(p.verifyCartContains1item().getText(),"");
		Reporter.log("Product removed successfully",true);
		
		

	}

	@Test(dataProvider = "data", dataProviderClass = DataProviderUtility.class,groups="System")
	public void verifyCartCheckoutIntegration(String Username,String Password) {
		SwagLabsLogin ln=new SwagLabsLogin(driver);
		ln.login(Username,Password);
		SwagLabsProduct p=new SwagLabsProduct(driver);
		SwagLabsCartpage cart=new SwagLabsCartpage(driver);
		p.clickaddtocart();
		Assert.assertEquals(p.verifyCartContains1item().getText(),"1");
		Reporter.log("Product added successfully",true);
		p.clickCart();
		Assert.assertTrue(
		        cart.verifyBackpackDisplayed(),
		        "Backpack is not displayed in Cart"
		);		Reporter.log("Product is in  cart  Page",true);
		cart.clickCheckout();
		Assert.assertEquals(driver.getCurrentUrl().contains("checkout-step-one"),true);
		Reporter.log("CheckOut",true);
		



	}



}