package practiceAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class AutomationPrac {

	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();
	}

	@AfterTest
	public void teardowm() {
		driver.quit();
	}

	@Test(priority = 1)
	public void radioBtn() throws InterruptedException {
		List<WebElement> allRadioBtn = driver.findElements(By.cssSelector("input[class='radioButton']"));
		for (WebElement ref : allRadioBtn) {
			ref.click();
			// Thread.sleep(1500);
		}
	}

	@Test(priority = 2)
	public void suggesstionClassExample() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.cssSelector("input[id='autocomplete']")).sendKeys("Aus");
		List<WebElement> allList = driver.findElements(By.cssSelector("li[class='ui-menu-item']"));
		for (WebElement ref : allList) {
			String allCountryList = ref.getText();
			System.out.println(allCountryList);
			if (allCountryList.equalsIgnoreCase("Australia")) {
				ref.click();
			}
		}
		Thread.sleep(1500);
	}

	@Test(priority = 3)
	public void dropdownExample() {
		WebElement selectdd = driver.findElement(By.id("dropdown-class-example"));
		selectdd.click();
		Select select = new Select(selectdd);
		select.selectByValue("option2");
	}

	@Test(priority = 4)
	public void switchWindowExample() {
		String pWH = driver.getWindowHandle();
		String cWH = "";
		driver.findElement(By.id("openwindow")).click();
		Set<String> allWH = driver.getWindowHandles();
		for (String ref : allWH) {
			if (!driver.switchTo().window(ref).getWindowHandle().equalsIgnoreCase(pWH)) {
				cWH = driver.getWindowHandle();
			}
		}
		System.out.println(pWH + " & " + cWH);
		driver.switchTo().window(pWH);
		driver.findElement(By.id("checkBoxOption1")).click();
		System.out.println(driver.switchTo().window(cWH).getTitle());
	}

	@Ignore
	public void switchTabExample() {
		String pWH = driver.getWindowHandle();
		String cWH = "";
		driver.findElement(By.cssSelector("a[id='opentab']")).click();
		Set<String> allWH = driver.getWindowHandles();
		for (String ref : allWH) {
			if (!driver.switchTo().window(ref).getWindowHandle().equalsIgnoreCase(pWH)) {
				cWH = driver.getWindowHandle();
			}
		}
		driver.switchTo().window(cWH);
		System.out.println("current url: " + driver.getCurrentUrl());
		System.out.println("current title: " + driver.getTitle());
		driver.switchTo().window(pWH);
		System.out.println("current url: " + driver.getCurrentUrl());

	}

	@Test(priority = 5)
	public void switchToalertExample() throws InterruptedException {
		driver.findElement(By.id("name")).sendKeys("Ankit is the highest paid QA");
		driver.findElement(By.id("alertbtn")).click();
		Alert a = driver.switchTo().alert();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Thread.sleep(1500);
		a.accept();
		driver.findElement(By.id("name")).sendKeys("Ankit is the highest paid QA");
		driver.findElement(By.id("confirmbtn")).click();
		Thread.sleep(1500);
		a.dismiss();
	}

	@Test(priority = 6)
	public void webTableExample() {
		WebElement webTable = driver.findElement(By.xpath("//table[@name='courses']"));
		List<WebElement> allRows = webTable.findElements(By.xpath("//table//tbody//tr"));
		for (WebElement allrows : allRows) {
			// System.out.println(allrows.getText());
		}

		List<WebElement> allCol3 = webTable.findElements(By.xpath("//table//tbody//tr//td[3]"));
		for (WebElement allColThree : allCol3) {
			System.out.println(allColThree.getText());
		}
	}

	@Test(priority = 7)
	public void mosueHoverExample() throws InterruptedException {
		WebElement mouseHoverBtn = driver.findElement(By.id("mousehover"));
		Actions ac = new Actions(driver);
		ac.moveToElement(mouseHoverBtn).build().perform();

		List<WebElement> allOptions = driver.findElements(By.cssSelector("div[class='mouse-hover-content']"));
		for (WebElement ref : allOptions) {
			System.out.println(ref.getText());

			ac.click(ref).build().perform();
			Thread.sleep(1500);

		}
	}

	@Test(priority = 8)
	public void screenshotExample() throws IOException {

		File f = ((TakesScreenshot) driver.findElement(By.cssSelector("div[id='gf-BIG']")))
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f, new File("C:\\Users\\Ankit\\Desktop\\AUTOMATION DOCS\\screenshots\\a1.png"));

	}

	@Test(priority = 9)
	public void excelIntegerationExample() throws BiffException, IOException {

		FileInputStream fs = new FileInputStream("C:\\Users\\Ankit\\Desktop\\AUTOMATION DOCS\\excel file\\p1.xls");

		Workbook wb = Workbook.getWorkbook(fs);

		Sheet s = wb.getSheet("Sheet1");

		System.out.println(s.getCell(0, 1).getContents());
	}

	@Test(priority = 10)
	public void waitConcept() {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.numberOfElementsToBe(By.tagName("a"), 28));

	}

	@Test(priority = 11)
	public void amitTest() {
		System.out.println("hello");
		System.out.println("h1");
		System.out.println("h2");

	}
}
