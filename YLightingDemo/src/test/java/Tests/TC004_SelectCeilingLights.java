package Tests;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import FunctionalLibrary.DriverActions;
import Logs.Log;

public class TC004_SelectCeilingLights {
	

	//Creating Objects for DriverActions and Properties Class to access its Methods
		DriverActions action = new DriverActions();
		Properties prop = new Properties();
		
		
		/**Invoke Constructor
		 * Invoked Constructor to Initialize Object for the Properties Class
		 * To Configure Logger 
		 * @throws IOException
		 */
		public TC004_SelectCeilingLights() throws IOException {
			prop = action.getPropertyFile();
			DOMConfigurator.configure("Logger\\Log4j.xml");
		}
		
		
		/**Before Test Method
		 * This Method is to Invoke Browser and to Navigate to Application 
		 * @throws Exception 
		 */
		@BeforeTest
		public void invokeBrowser() throws Exception {
			action.getBrowser("Chrome");
			action.getWebLink(prop.getProperty("getAppLink"));
			action.Wait();
			action.clickWebElement("xpath",prop.getProperty("closepopup"));
		}
		
		/** Test Method
		 * This Method is used for Login Functionality where we are giving User Details for Login
		 * @throws Exception 
		 */
		@Test
		public void Login() throws Exception {
			action.clickWebElement("xpath", prop.getProperty("clickAccount"));
			action.clickWebElement("xpath", prop.getProperty("clickMyAccount"));
			
			action.inputValues("xpath", prop.getProperty("Username"), action.ReadTestData("Login", 2, 1));
			action.inputValues("xpath", prop.getProperty("Password"), action.ReadTestData("Login", 2, 2));
			action.Wait();
			action.clickWebElementP("xpath", prop.getProperty("Login"));
			
			action.ImplicitWait();
		}
		
		/** Test Method
		 * This Method is used for selecting the Ceiling lights from Lightning tab
		 * @throws Exception 
		 */
		@Test
		public void SelectCeilinglights() throws Exception {
			action.clickWebElement("xpath",prop.getProperty("Ceiling"));
			action.ImplicitWait();
			action.clickWebElement("xpath",prop.getProperty("Chandeliers"));
			action.ImplicitWait();
			action.clickWebElement("xpath",prop.getProperty("RectangularChandel"));
			action.ImplicitWait();
			
		}
		
		/** After Method
		 * This Method is show the Successful Login
		 * @throws Exception
		 */
		@AfterTest
		public void selectChandeleirs() throws Exception {
			Log.info("ProductSelected");
			action.Wait();
			action.TakeScreenShot("TC004-04-SelectChandliers");
			action.Quit();		
		}
		
}
