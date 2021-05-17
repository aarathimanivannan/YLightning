package FunctionalLibrary;

	import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import DataProvider.DriverPathProvider;
import DataProvider.PropertiesFilePath;
import DataProvider.TestDataPathProvider;

	public class DriverActions {

		public static WebDriver Driver;


		/****************************************************************************
		'* NAME				: getBrowser
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function to Get Browser
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: BrowserName
		'***************************************************************************/
		public void getBrowser(String BrowserName) {
			DriverPathProvider DPP = new DriverPathProvider();
			switch(BrowserName) {
			case "IE": {
				System.setProperty("webdriver.ie.driver", DPP.IEDriverPath);
				Driver = new InternetExplorerDriver();
				Driver.manage().deleteAllCookies();
				Driver.manage().window().maximize();
				break;
				}
			case "Chrome": {
				System.setProperty("webdriver.chrome.driver", DPP.ChromeDriverPath);
				Driver = new ChromeDriver();
				Driver.manage().deleteAllCookies();
				Driver.manage().window().maximize();
				break;
				}
			case "Firefox": {
				System.setProperty("webdriver.gecko.driver", DPP.FirefoxDriverPath);
				Driver = new FirefoxDriver();
				Driver.manage().deleteAllCookies();
				Driver.manage().window().maximize();
				break;
				}
			default: {
				System.out.println("Choose Proper Browser");
				break;
				}
			}
		}


		/****************************************************************************
		'* NAME				: getWebLink
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function to Get Application Web Link
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: WebLink
		'***************************************************************************/
		public void getWebLink(String WebLink) {
			Driver.get(WebLink);
		}





		/****************************************************************************
		'* NAME				: clearTextArea
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function used to Clear Text Area
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: getAttributeType, getAttributeValue
		'***************************************************************************/
		public void clearTextArea(String getAttributeType, String getAttributeValue) {
			switch(getAttributeType) {
			case "id": {
				Driver.findElement(By.id(getAttributeValue)).clear();
				break;
				}
			case "xpath": {
				Driver.findElement(By.xpath(getAttributeValue)).clear();
				break;
				}
			case "css": {
				Driver.findElement(By.cssSelector(getAttributeValue)).clear();
				break;
				}
			}
		}


		/****************************************************************************
		'* NAME				: inputValues
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function used to Input Values in Text Field
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: getAttributeType, getAttributeValue, Value
		'***************************************************************************/
		public void inputValues(String getAttributeType, String getAttributeValue, String Value) {
			switch(getAttributeType) {
			case "id": {
				Driver.findElement(By.id(getAttributeValue)).sendKeys(Value);
				break;
				}
			case "xpath": {
				Driver.findElement(By.xpath(getAttributeValue)).sendKeys(Value);
				break;
				}
			case "css": {
				Driver.findElement(By.cssSelector(getAttributeValue)).sendKeys(Value);
				break;
				}
			}
		}


		/****************************************************************************
		'* NAME				: clickWebElement
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function to Click Web Element in a Web Page
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: getAttributeType, getAttributeValue
		'***************************************************************************/
		public void clickWebElement(String getAttributeType, String getAttributeValue) {
			switch(getAttributeType) {
			case "id": {
				Driver.findElement(By.id(getAttributeValue)).click();
				break;
				}
			case "xpath": {
				Driver.findElement(By.xpath(getAttributeValue)).click();
				break;
				}
			case "css": {
				Driver.findElement(By.cssSelector(getAttributeValue)).click();
				break;
				}
			}
		}



		/****************************************************************************
		'* NAME				: getPropertyFile
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function to Load the Property File(Object Property)
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: NA
		'***************************************************************************/
		public Properties getPropertyFile() throws IOException {
			PropertiesFilePath PFP = new PropertiesFilePath();
			FileInputStream getFilePath = new FileInputStream (PFP.getPropertiesFilePath);
			Properties getProp = new Properties();
			getProp.load(getFilePath);
			return getProp;
		}
		
		
		/****************************************************************************
		'* NAME				: ReadTestData
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function to Read Test Data from Excel
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: SheetName, RowNo, CellNo
		'***************************************************************************/
		public String ReadTestData(String SheetName, int RowNo, int CellNo) throws IOException {
			TestDataPathProvider TDPP = new TestDataPathProvider();
			FileInputStream getFilePath = new FileInputStream (TDPP.TestDataPath);
			@SuppressWarnings("resource")
			XSSFWorkbook book = new XSSFWorkbook(getFilePath);
			XSSFSheet sheet = book.getSheet(SheetName);
			String getValue = sheet.getRow(RowNo).getCell(CellNo).getStringCellValue();
			return getValue;
		}


		/****************************************************************************
		'* NAME				: SelList
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function used to Select Values from List Box
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: getAttributeType, getAttributeValue, Value
		'***************************************************************************/
		public void SelList(String getAttributeType, String getAttributeValue, String Value) {
			switch(getAttributeType) {
			case "id": {
				Select Sel = new Select(Driver.findElement(By.id(getAttributeValue)));
				Sel.selectByVisibleText(Value);
				break;
				}
			case "xpath": {
				Select Sel = new Select(Driver.findElement(By.xpath(getAttributeValue)));
				Sel.selectByVisibleText(Value);
				break;
				}
			case "css": {
				Select Sel = new Select(Driver.findElement(By.cssSelector(getAttributeValue)));
				Sel.selectByVisibleText(Value);
				break;
				}
			}
		}


		/****************************************************************************
		'* NAME				: getStoreValue
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function to Store the Value and Return the Stored Value
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: getAttributeType, getAttributeValue
		'***************************************************************************/
		public String getStoreValue(String getAttributeType, String getAttributeValue) {
			String Value;
			if(getAttributeType.compareTo("id") == 0) {
				Value = Driver.findElement(By.id(getAttributeValue)).getText();
			}
			else if(getAttributeType.compareTo("xpath") == 0) {
				Value = Driver.findElement(By.xpath(getAttributeValue)).getText();
			}
			else {
				Value = Driver.findElement(By.cssSelector(getAttributeValue)).getText();
			}
			return Value;
		}


		/**********************************************************************************
		'* NAME				: clickWebElementP
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function used to Click Web Element by Initializing WebElement
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: getAttributeType, getAttributeValue
		'*********************************************************************************/
		public void clickWebElementP(String getAttributeType, String getAttributeValue) {
			WebElement WE;
			switch(getAttributeType) {
			case "id": {
				WE = Driver.findElement(By.id(getAttributeValue));
				WE.click();
				break;
				}
			case "xpath": {
				WE = Driver.findElement(By.xpath(getAttributeValue));
				WE.click();
				break;
				}
			case "css": {
				WE = Driver.findElement(By.cssSelector(getAttributeValue));
				WE.click();
				break;
				}
			}
		}


		/****************************************************************************
		'* NAME				: SwitchToNewWindow
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function used to Switch to New Window
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: NA
		'***************************************************************************/
		public void SwitchToNewWindow() {
			String OldWindow;
			OldWindow = Driver.getWindowHandle();
			Set<String> S = Driver.getWindowHandles();
			for(String NewWindow : S) {
				if(!OldWindow.equalsIgnoreCase(NewWindow)) {
					Driver.switchTo().window(NewWindow);
				}
			}
			Driver.close();
			Driver.switchTo().window(OldWindow);
		}


		/****************************************************************************
		'* NAME				: SwitchToFrame
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function to Switch between Frames by Passing FrameName
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: FrameName
		'***************************************************************************/
		public void SwitchToFrame(String FrameName) {
			Driver.switchTo().frame(FrameName);
		}


		/****************************************************************************
		'* NAME				: SwitchToFrameI
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function to Switch between Frames by Passing FrameNo
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: FrameNo
		'***************************************************************************/
		public void SwitchToFrameI(int FrameNo) {
			Driver.switchTo().frame(FrameNo);
		}


		/****************************************************************************
		'* NAME				: TakeScreenShot
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function to Take Screen Shot
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: getFilePath
		'***************************************************************************/
		public void TakeScreenShot(String getScreenshotName) throws IOException {
			TakesScreenshot Screenshot = ((TakesScreenshot)Driver);
			File SrcFile = Screenshot.getScreenshotAs(OutputType.FILE);
			File DestFilePath = new File("C:\\Users\\91994\\git\\Venkat-s-opencartfile\\ylightining\\git_auto\\YLightingDemo\\Screenshots\\"+
					getScreenshotName+".png");
			FileUtils.copyFile(SrcFile, DestFilePath);
		}



		/****************************************************************************
		'* NAME				: Wait
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function to Wait for WebPage to Load
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: NA
		'***************************************************************************/
		public void Wait() throws Exception {
			Thread.sleep(5000);
		}


		/****************************************************************************
		'* NAME				: WaitM
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function to Wait for WebPage to Load
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: NA
		'***************************************************************************/
		public void WaitM() throws Exception {
			System.out.println(Calendar.getInstance().getTime()+"Waiting");
			Thread.sleep(5000);
		}


		/****************************************************************************
		'* NAME				: ImplicitWait
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function to Wait for WebPage to Load
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: NA
		'***************************************************************************/
		public void ImplicitWait() throws Exception {
			Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		
		
		/****************************************************************************
		'* NAME				: ExplicitWait
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function to Wait for WebPage to Load
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: NA
		'***************************************************************************/
		public void ExplicitWait(String getXpathLocator) throws Exception {
			WebDriverWait wait = new WebDriverWait(Driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getXpathLocator)));
		}

		
		
		/****************************************************************************
		'* NAME				: ExplicitWait
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function to Wait for WebPage to Load
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: NA
		'***************************************************************************/
		public void ExplicitWaitClickable(String getXpathLocator) throws Exception {
			WebDriverWait wait = new WebDriverWait(Driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(getXpathLocator)));
		}

		
		
		/****************************************************************************
		'* NAME				: MouseOver
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function used for Mouse Over
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: Path
		'***************************************************************************/
		public void MouseOverClick(String getAttributeType, String getAttributeValue) {
			Actions action = new Actions(Driver);
			WebElement WE;
			switch(getAttributeType) {
			case "id": {
				WE = Driver.findElement(By.id(getAttributeValue));
				action.build().perform();
				action.click(WE);
				break;
				}
			case "xpath": {
				WE = Driver.findElement(By.xpath(getAttributeValue));
				action.build().perform();
				action.click(WE);
				break;
				}
			case "css": {
				WE = Driver.findElement(By.cssSelector(getAttributeValue));
				action.build().perform();
				action.click(WE);
				break;
				}
			}
		}


		/****************************************************************************
		'* NAME				: Refresh
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function used to Refresh Web Page
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: NA
		'***************************************************************************/
		public void Refresh() {
			Driver.navigate().refresh();

		}


		/****************************************************************************
		'* NAME				: Quit
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function used to Quit the Browser
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: NA
		'***************************************************************************/
		public void Quit() {
			Driver.quit();
		}


		/****************************************************************************
		'* NAME				: Highlight
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function used to Highlight WebElement
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: WebElement
		'***************************************************************************/
		public void Highlight(WebElement WebElement) {
			JavascriptExecutor js = (JavascriptExecutor)Driver;
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", WebElement);
		}
		
		
		/****************************************************************************
		'* NAME				: ScrollDown
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function used to Scroll Down WebPage
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: WebElement
		'***************************************************************************/
		public void ScrollDown() {
			JavascriptExecutor js = (JavascriptExecutor)Driver;
			js.executeScript("window.scrollBy(0,100)");
		}


		/****************************************************************************
		'* NAME				: clickWebElementJS
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function used to Click Web Element using JS Executer
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: WE
		'***************************************************************************/
		public void clickWebElementJS(String getAttributeType, String WE) {
			WebElement Web;
			switch(getAttributeType) {
			case "id": {
				Web = Driver.findElement(By.id(WE));
				JavascriptExecutor js = (JavascriptExecutor)Driver;
				js.executeScript("arguments[0].click();", Web);
				break;
			}
			case "xpath": {
				Web = Driver.findElement(By.xpath(WE));
				JavascriptExecutor js = (JavascriptExecutor)Driver;
				js.executeScript("arguments[0].click();", Web);
				break;
			}
			case "css": {
				Web = Driver.findElement(By.cssSelector(WE));
				JavascriptExecutor js = (JavascriptExecutor)Driver;
				js.executeScript("arguments[0].click();", Web);
				break;
			}
			}
		}
		
		
		
		/****************************************************************************
		'* NAME				: MouseOverJS
		'* TAGS				: General Code
		'* APPLICATION NAME	: Used for All Applications
		'* SYNOPSIS			: Function used to Click Web Element using JS Executer
		'* CREATED BY		: Venkatraman Ganesan
		'* CREATED DATE		: 19-02-2021
		'* PARAMETERS		: WE
		'***************************************************************************/
		public void MouseOverJS(String WE) {
			WebElement Web = Driver.findElement(By.xpath(WE));
			
			String strJavaScript = "var element = arguments[0];"
		            + "var mouseEventObj = document.createEvent('MouseEvents');"
		            + "mouseEventObj.initEvent( 'mouseover', true, true );"
		            + "element.dispatchEvent(mouseEventObj);";
			
			JavascriptExecutor js = (JavascriptExecutor)Driver;
			js.executeScript(strJavaScript, Web);
		}

		
		public void closeActiveWindow() {
			Driver.close();
		}
		
		
		public WebElement returnWebElement(String getAttributeType, String getAttributeValue) {
			WebElement Web;
			if(getAttributeType == "id") {
				Web = Driver.findElement(By.id(getAttributeValue));
			}
			else if(getAttributeType == "xpath") {
				Web = Driver.findElement(By.xpath(getAttributeValue));
			}
			else {
				Web = Driver.findElement(By.cssSelector(getAttributeValue));
			}
			return Web;
		}

		public static void main(String[] args) {

		}

	}

