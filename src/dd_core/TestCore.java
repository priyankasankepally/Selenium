package dd_core;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import dd_util.DbManager;
import dd_util.ExcelReader;

public class TestCore {
	
	public static WebDriver driver = null;
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\dd_properties\\testdata.xlsx");
	public static Logger log = Logger.getLogger("devpinoyLogger"); 
	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	
	
	@BeforeSuite
	public static void setUp() throws IOException, AddressException, SQLException, ClassNotFoundException, MessagingException{
		
		
		if(driver==null){
			
			
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\dd_properties\\OR.properties");
			OR.load(fis);
			log.debug("OR properties file loaded");
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\dd_properties\\Config.properties");
			Config.load(fis);
			log.debug("Config properties file loaded");
			
			if(Config.getProperty("browser").equals("firefox")){
				
				driver = new FirefoxDriver();
				
			}else if(Config.getProperty("browser").equals("ie")){
				
				driver = new InternetExplorerDriver();
				
			}else if(Config.getProperty("browser").equals("chrome")){
				
				driver = new ChromeDriver();
				
			}
			
			driver.get(Config.getProperty("testsiteurl"));
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			
			DbManager.setMysqlDbConnection();
			
			
		}
		
		
		
	}
	
	@AfterSuite
	public static void tearDown(){
		
		//driver.quit();
		
	}

}
