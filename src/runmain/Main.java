package runmain;

import org.openqa.selenium.WebDriver;

import seleniumtest.Initialization;
import seleniumtest.Uninitialize;
import testcase.TestLogon;

public class Main
{
	private WebDriver driver;
	
	//初始化webdriver资源
	public Main()
	{
		Initialization init=new Initialization();
		this.driver=init.GetWebDriver();
	}
	
	//回收webdirver资源
	public void _Main() throws Exception
	{
		//测试完毕，回收资源，以及后续报告处理
		Thread.sleep(3000);
		new Uninitialize(this.driver);
	}
	
	//TestCase执行
	public void Run() throws Exception
	{
		new TestLogon(this.driver).testUntitledTestCase();
	}
	
	public static void main(String[] args) throws Exception
	{
		Main test=new Main();
		test.Run();
		test._Main();
		
	}

}
