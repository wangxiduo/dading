package seleniumtest;

import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import autotools.AutoPublicTools;


public class mendiandenglu
{
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	
	private String currDir;
	private String geckoPath;
	private String iePath;
	
	public mendiandenglu()
	{
		this.currDir=System.getProperty("user.dir");
		this.geckoPath=this.currDir+"\\testdriver";
		this.iePath=tools.IniFileRead.GetValueByKey(this.currDir+"\\cfgini.properties", "webdriver.firefox.bin");
		
		System.out.println("currDir: "+this.currDir);
		System.out.println("geckoPath: "+this.geckoPath);
		System.out.println("iePath: "+this.iePath);
		
	}

	@Before
	public void setUp() throws Exception
	{

		System.setProperty("webdriver.gecko.driver", this.geckoPath+"\\geckodriver_x64.exe");
		System.setProperty("webdriver.firefox.bin",this.iePath);
		
		driver = new FirefoxDriver();
		

		//baseUrl = "https://www.dadingsoft.com";
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testUntitledTestCase() throws Exception
	{
		driver.get("https://www.dadingsoft.com/");
		
		String chkCodeXpath="//*[@id=\"randompicture\"]";
		
		
		driver.findElement(By.linkText("系统用户")).click();
		driver.findElement(By.id("loginName")).click();
		driver.findElement(By.id("passWord")).clear();
		driver.findElement(By.id("passWord")).sendKeys("123456");
		driver.findElement(By.id("loginName")).clear();
		driver.findElement(By.id("loginName")).sendKeys("wangxiduo11");
		driver.findElement(By.id("passWord")).click();
		
/*		String chkImg=driver.findElement(By.xpath(chkCodeXpath)).getAttribute("src");
		System.out.println(chkImg);
		java.io.File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage  fullImg = ImageIO.read(screenshot);
		WebElement ele = driver.findElement(By.xpath(chkCodeXpath));
		Point point = ele.getLocation();
		int eleWidth = ele.getSize().getWidth();
		int eleHeight = ele.getSize().getHeight();
		BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "jpg", screenshot);
		File screenshotLocation = new File("F:\\radompicture.jpg");
		FileUtils.copyFile(screenshot, screenshotLocation);
		System.out.println(AutoPublicTools.ImgToBase64("F:\\radompicture.jpg"));*/
		
		PageTools tool=new PageTools(driver);
		
		int errNum=0;
		while(true)
		{
			//看不清,换一张
			driver.findElement(By.xpath("/html/body/div[1]/form[2]/div/div[4]/a")).click();
			
			String chkCode=tool.GetChkCode(chkCodeXpath);
			
/*			byte [] bChkCode=chkCode.getBytes();
			bChkCode[chkCode.length()-1]='\0';
			chkCode=new String(bChkCode);*/
			chkCode=chkCode.replaceAll("[\\t\\n\\r]", "");
			System.out.println("===> "+chkCode);
			
			driver.findElement(By.id("radompicture")).click();
			driver.findElement(By.id("radompicture")).clear();
			driver.findElement(By.id("radompicture")).sendKeys(chkCode);
			
			driver.findElement(By.linkText("立即登录")).click();
			
			
			String chkCodeRet=driver.findElement(By.xpath("//*[@id=\"selectShopName\"]")).getText();
			System.out.println(String.format("[%d] %s: %s",errNum,chkCode,chkCodeRet));
			
			if(!chkCodeRet.equals("验证码不正确！"))
			{
				break;
			}
			errNum++;
		}
		
		Thread.sleep(10000);
	}

	@After
	public void tearDown() throws Exception
	{
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString))
		{
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by)
	{
		try
		{
			driver.findElement(by);
			return true;
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}

	private boolean isAlertPresent()
	{
		try
		{
			driver.switchTo().alert();
			return true;
		}
		catch (NoAlertPresentException e)
		{
			return false;
		}
	}

	private String closeAlertAndGetItsText()
	{
		try
		{
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert)
			{
				alert.accept();
			}
			else
			{
				alert.dismiss();
			}
			return alertText;
		}
		finally
		{
			acceptNextAlert = true;
		}
	}
}
