package testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import seleniumtest.PageTools;

public class TestLogon
{
	private WebDriver driver;
	
	public TestLogon(WebDriver driver)
	{
		this.driver=driver;
	}
	
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
			
			
			try
			{
				String chkCodeRet=driver.findElement(By.xpath("//*[@id=\"selectShopName\"]")).getText();
				System.out.println(String.format("[%d] %s: %s",errNum,chkCode,chkCodeRet));
				
				if(!chkCodeRet.equals("验证码不正确！"))
				{
					break;
				}
			}
			catch (Exception e)
			{
				System.out.println("验证码 OK");
				return;
			}
			
			errNum++;
		}
	}
	
}
