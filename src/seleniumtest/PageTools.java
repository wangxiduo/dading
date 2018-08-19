package seleniumtest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import autotools.AutoPublicTools;
import httppkg.SendDataHttp;

public class PageTools
{
	private WebDriver driver;
	private String codeUrl;
	private String currDir;
	
	public PageTools(WebDriver driver)
	{
		currDir=System.getProperty("user.dir");
		this.driver=driver;
		this.codeUrl=tools.IniFileRead.GetValueByKey(this.currDir+"\\cfgini.properties", "codeurl");
		System.out.println("check code uri: "+codeUrl);
		
	}
	
	public String SaveImg(String xpath,String savePath) throws Exception
	{
		String retDat="";
		String imgFile="radompicture.jpg";
		
		String chkImg=driver.findElement(By.xpath(xpath)).getAttribute("src");
		System.out.println(chkImg);
		java.io.File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage  fullImg = ImageIO.read(screenshot);
		WebElement ele = driver.findElement(By.xpath(xpath));
		Point point = ele.getLocation();
		int eleWidth = ele.getSize().getWidth();
		int eleHeight = ele.getSize().getHeight();
		BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "jpg", screenshot);
		
		if(savePath==null)
		{
			savePath=System.getProperty("java.io.tmpdir")+imgFile;
			System.out.println("sava path null,save to tmps: "+savePath);
		}
		
		File screenshotLocation = new File(savePath);
		FileUtils.copyFile(screenshot, screenshotLocation);
		
		retDat=AutoPublicTools.ImgToBase64(savePath);
		
		screenshotLocation.deleteOnExit();
		
		return retDat;
	}
	
	public String GetChkCode(String xpath) throws Exception
	{
		String imgBase64=this.SaveImg(xpath,null);
		
		SendDataHttp httpReq = new SendDataHttp();
		
		String url="http://localhost:8080/";
		//System.out.println(imgBase64);
		
		String retCode=httpReq.post(this.codeUrl, imgBase64);
		
		//System.out.println(retCode);
		
		return retCode;
	}
}












