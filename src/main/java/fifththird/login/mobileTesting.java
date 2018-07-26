package fifththird.login;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.perfectomobile.selenium.api.IMobileDevice;
import com.perfectomobile.selenium.api.IMobileWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

public class mobileTesting {

	AppiumDriver 	driver;
	WebDriverWait	wait;
	String 			os;
	final int 		interval = 30000; // The interval of time to watch the trailer. *currently 30 seconds.
	
	//TODO: Change my user , password and host cloud URL.
	final String user = "dinesh.venkataraman@internal.53.com";
	final String pass = "Perfecto123";
	final String host = "fifththird.perfectomobile.com";
	
	@BeforeTest
	public void beforTest() throws MalformedURLException{

		try{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("user", user);
			capabilities.setCapability("password", pass);
			capabilities.setCapability("model", "iPhone-8 Plus");
			capabilities.setCapability("platformName", "iOS");
			capabilities.setCapability("platformVersion", "11.4");
			capabilities.setCapability("deviceName", "6CEDD7EA68F5F5211728B0554694B223BB4F86ED");
			capabilities.setCapability("autoAcceptAlerts",true);
			driver = new IOSDriver(new URL("https://" + this.host + "/nexperience/perfectomobile/wd/hub"), capabilities) ;
					
			wait = new WebDriverWait(driver, 10);
			driver.context("NATIVE_APP");
			System.out.println("Driver intsalled successfully");
			driver.manage().timeouts().implicitlyWait(15 , TimeUnit.SECONDS);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test(priority=1)	
	public void OpenAPP(){
				
		HashMap<String , Object> script = new HashMap<String, Object>();
		script.put("name", "Fifth Third");
		driver.executeScript("mobile:application:open", script);
		System.out.println("---------------------------->App is opened");
		
	}
	
	
	@Test(priority=2)	
	public void enterUsernamePassword() throws Exception
	{
		Thread.sleep(1600);
		
		driver.findElement(By.xpath("//AppiumAUT/XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeTextField[1]")).click();
		System.out.println("I clicked User Name");
		driver.findElement(By.xpath("//AppiumAUT/XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeTextField[1]")).sendKeys("MobilePOCPefecto");
		System.out.println("I entered user name");
		driver.findElement(By.xpath("//AppiumAUT/XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeSecureTextField[1]")).click();
		System.out.println("I clicked password");
		driver.findElement(By.xpath("//AppiumAUT/XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeSecureTextField[1]")).sendKeys("test");
		System.out.println("I entered password");
	}
	
	@Test(priority=3)	
	public void clickLogin() throws Exception
	{
		Thread.sleep(1200);
		
		driver.findElement(By.xpath("//AppiumAUT/XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeButton[1]")).click();
		System.out.println("I clicked Login Button");
	}
	
	@Test(priority=4)	
	public void validateLogin() throws Exception
	{
		Thread.sleep(1200);
		
		driver.findElement(By.xpath("//AppiumAUT/XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeAlert[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[3]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[1]")).click();
		System.out.println("I clicked ok Button");
	}

	@Test(priority=5)	
	public void switchwifiOff() throws Exception
	{
		Map<String, Object> appName = new HashMap<>();
		appName.put("name", "Settings");
		driver.executeScript("mobile:application:open", appName);
		driver.executeScript("mobile:application:close", appName);     
		driver.executeScript("mobile:application:open", appName);
		 
		switchToContext("NATIVE_APP");
		driver.findElementByXPath("//*[@value=\"Wi-Fi\"]").click();
		 
		switchToContext("NATIVE_APP");
		WebElement switchOnOff = driver.findElementByXPath("//*[@label=\"Wi-Fi\" and @class=\"UIASwitch\"]");
		switchOnOff.click();
		Thread.sleep(2000);
		//switchOnOff.click();
		System.out.println("---------------------------->Wifi is swithed off");
		
		HashMap<String , Object> script = new HashMap<String, Object>();
		script.put("name", "Fifth Third");
		driver.executeScript("mobile:application:open", script);
		script.put("name", "Fifth Third");
		driver.executeScript("mobile:application:open", script);
		
		driver.findElement(By.xpath("//AppiumAUT/XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeSecureTextField[1]")).click();
		System.out.println("I clicked password after wifi is disabled");
		driver.findElement(By.xpath("//AppiumAUT/XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeSecureTextField[1]")).sendKeys("test");
		System.out.println("I entered password after wifi is disabled");
		
		Thread.sleep(1200);
		
		driver.findElement(By.xpath("//AppiumAUT/XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeButton[1]")).sendKeys("test");
		System.out.println("I clicked Login Button after wifi is disabled");
		
		Thread.sleep(2200);
		
		driver.findElement(By.xpath("//AppiumAUT/XCUIElementTypeApplication[1]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeAlert[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[3]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeButton[1]")).click();
		System.out.println("I clicked ok Button");
		Thread.sleep(1200);
	}

	public void switchToContext(String context) {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
        Map<String,String> params = new HashMap<>();
        params.put("name", context);
        executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
    }
	
	
	 
	
}

