package dd_testcases;



import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dd_core.TestCore;
import dd_util.CommonUtil;

public class LoginTest extends TestCore{
	
	//checking whether the test is Executable
	@BeforeTest
	public void isSkip(){
		
		if(!CommonUtil.isExecutable("LoginTest")){
			
			throw new SkipException("Skipping the test as the Runmode is No");
			
		}
		
	}
	
	@Test(dataProvider="getData")
	public void doLogin(String username,String password) throws IOException{
		try{
		driver.findElement(By.id(OR.getProperty("username"))).sendKeys(username);
		String id=  CommonUtil.switchhandles();
		driver.switchTo().window(id);
		driver.findElement(By.id(OR.getProperty("password"))).sendKeys(password);
		driver.findElement(By.xpath(OR.getProperty("login"))).click();
		}
		catch(Exception e){
			Assert.assertTrue(false, e.getMessage());
			CommonUtil.CaptureScreenshot();
		}
	}
	
	
	@DataProvider
	public static Object[][] getData(){
				 
		 
		return CommonUtil.getData("LoginTest");
		
		
	}
	
	

}
