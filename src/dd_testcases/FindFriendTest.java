package dd_testcases;

import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dd_core.TestCore;
import dd_util.CommonUtil;

public class FindFriendTest extends TestCore {
	
	//checking whether the test is Executable
	@BeforeTest
	public void isSkip(){
	
		if(!CommonUtil.isExecutable("FindFriendTest")){
			
			throw new SkipException("Skipping the test as the Runmode is No");
			
		}
	}
	
	@Test(dataProvider="getData")
	public void editProfileTest(String friendName){
		
		System.out.println("Finding a friend");
		
	}

	@DataProvider
	public static Object[][] getData(){
		

		return CommonUtil.getData("FindFriendTest");
		
		
		
	}
	
}
