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

public class Initialization
{
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	
	private String currDir;
	private String geckoPath;
	private String iePath;
	
	public Initialization()
	{
		this.currDir=System.getProperty("user.dir");
		this.geckoPath=this.currDir+"\\testdriver";
		this.iePath=tools.IniFileRead.GetValueByKey(this.currDir+"\\cfgini.properties", "webdriver.firefox.bin");
		
		System.out.println("currDir: "+this.currDir);
		System.out.println("geckoPath: "+this.geckoPath);
		System.out.println("iePath: "+this.iePath);
		
		System.setProperty("webdriver.gecko.driver", this.geckoPath+"\\geckodriver_x64.exe");
		System.setProperty("webdriver.firefox.bin",this.iePath);
		
		driver = new FirefoxDriver();
	}
	
	public WebDriver GetWebDriver()
	{
		return this.driver;
	}
	
}






















