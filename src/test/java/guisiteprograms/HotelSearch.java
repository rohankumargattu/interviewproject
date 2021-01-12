package guisiteprograms;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HotelSearch
{
	public static void main(String[] args) throws Exception
	{
		//Declare driver object
		RemoteWebDriver driver=null;
		
		//Enter browser name from keyboard
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter browser name as chrome/firefox");
		String bn=sc.nextLine();
		sc.close();
		
		//Open browser
		if(bn.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(bn.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else
		{
			System.out.println("Browser spelling Raadha..? Name correct ga ivvu");
			System.exit(0);	//Stop execution forcibly
		}
		
		//Maximize
		driver.manage().window().maximize();
		
		//Launch site
		driver.get("https://www.booking.com/");
		
		//Create wait object
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Stays')]")));
		if(driver.findElement(By.xpath("(//*[@class='bui-tab__item'])[1]/a")).isEnabled())
		{
			if(!driver.findElement(By.xpath("(//*[@class='bui-tab__item'])[1]/a")).isSelected())
			{
				driver.findElement(By.xpath("(//*[@class='bui-tab__item'])[1]/a")).click();
			}
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("ss"))).sendKeys("New");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-label='List of suggested destinations ']")));
		//Thread.sleep(3000);
		List<WebElement> destinations=driver.findElements(By.xpath("//*[@aria-label='List of suggested destinations ']/li"));
		int flag=0;
		for(int i=0;i<destinations.size();i++)
		{
			String reqdest=driver.findElement(By.xpath("//*[@aria-label='List of suggested destinations ']/li["+(i+1)+"]/span[2]/span")).getText();
			if(reqdest.equalsIgnoreCase("New York"))
			{
				destinations.get(i).click();
				//Thread.sleep(3000);
				flag=1;
				break;
			}
		}
		if(flag==0)
		{
			destinations.get(0).click();
			//Thread.sleep(3000);
		}
		//Select check-in date(next day to current day)
		String curdate=driver.findElement(By.xpath("//*[contains(@class,'today')]/span")).getText();
		int curday=Integer.parseInt(driver.findElement(By.xpath("//*[contains(@class,'today')]/span/span")).getText());
		if(curdate.contains("Jan") || curdate.contains("Mar") || curdate.contains("May") || curdate.contains("Jul") || curdate.contains("Aug") || curdate.contains("Oct") || curdate.contains("Dec"))
		{
			if(curday==31)
			{
				driver.findElement(By.xpath("(//*[text()='1'])[2]")).click();
				//Thread.sleep(3000);
			}
			else
			{
				driver.findElement(By.xpath("(//*[text()='"+(curday+1)+"'])[1]")).click();
				//Thread.sleep(3000);
			}
		}
		else if(curdate.contains("Apr") || curdate.contains("Jun") || curdate.contains("Sep") || curdate.contains("Nov"))
		{
			if(curday==30)
			{
				driver.findElement(By.xpath("(//*[text()='1'])[2]")).click();
				//Thread.sleep(3000);
			}
			else
			{
				driver.findElement(By.xpath("(//*[text()='"+(curday+1)+"'])[1]")).click();
				//Thread.sleep(3000);
			}
		}
		else
		{
			if(curday==28 || curday==29)
			{
				driver.findElement(By.xpath("(//*[text()='1'])[2]")).click();
				//Thread.sleep(3000);
			}
			else
			{
				driver.findElement(By.xpath("(//*[text()='"+(curday+1)+"'])[1]")).click();
				//Thread.sleep(3000);
			}
		}
		//Select check-in date(exactly 3 weeks later)
		LocalDate dt=LocalDate.now().plusWeeks(3);
		driver.findElement(By.xpath("//*[@data-date='"+dt+"']")).click();
		//Thread.sleep(3000);
		//Select Travellers
		driver.findElement(By.xpath("(//*[contains(text(),'adults')])[2]")).click();
		//Thread.sleep(3000);
		//Select Adults(2)
		while(2>1)
		{
			int noa=Integer.parseInt(driver.findElement(By.xpath("//*[@aria-label='Decrease number of Adults']/following-sibling::span[1]")).getText());
			if(noa<2)
			{
				driver.findElement(By.xpath("//*[@aria-label='Increase number of Adults']")).click();
				//Thread.sleep(3000);
			}
			else if(noa>2)
			{
				driver.findElement(By.xpath("//*[@aria-label='Decrease number of Adults']")).click();
				//Thread.sleep(3000);
			}
			else
			{
				//Thread.sleep(3000);
				break;
			}
		}
		//Select Children(1) - 2years old
		while(2>1)
		{
			int noc=Integer.parseInt(driver.findElement(By.xpath("//*[@aria-label='Decrease number of Children']/following-sibling::span[1]")).getText());
			if(noc<1)
			{
				driver.findElement(By.xpath("//*[@aria-label='Increase number of Children']")).click();
				//Thread.sleep(3000);
			}
			else if(noc>1)
			{
				driver.findElement(By.xpath("//*[@aria-label='Decrease number of Children']")).click();
				//Thread.sleep(3000);
			}
			else
			{
				//Thread.sleep(3000);
				break;
			}
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("age")));
		WebElement e=driver.findElement(By.name("age"));
		Select s=new Select(e);
		s.selectByValue("2");
		//Select Room(1)
		while(2>1)
		{
			int nor=Integer.parseInt(driver.findElement(By.xpath("//*[@aria-label='Decrease number of Rooms']/following-sibling::span[1]")).getText());
			if(nor<1)
			{
				driver.findElement(By.xpath("//*[@aria-label='Increase number of Rooms']")).click();
				//Thread.sleep(3000);
			}
			else if(nor>1)
			{
				driver.findElement(By.xpath("//*[@aria-label='Decrease number of Rooms']")).click();
				//Thread.sleep(3000);
			}
			else
			{
				//Thread.sleep(3000);
				break;
			}
		}
		//Click on search button
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Search')]"))).click();
		//Collect the count of all no of properties found
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'properties found')]")));
		String nopbf=driver.findElement(By.xpath("//h1[contains(text(),'properties found')]")).getText();
		Pattern p1=Pattern.compile("[0-9,]+");
		Matcher m1=p1.matcher(nopbf);
		String anopbf="";
		if(m1.find())
		{
			anopbf=m1.group();
		}
		System.out.println("No of all properties found before filters applied are: "+anopbf);
		//Thread.sleep(3000);
		//Apply filters(3 Star hotels)
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'3 stars')]"))).click();
		//Thread.sleep(10000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@class,'bui-spinner--size-large')]/div")));
		//Apply filters(price(lowest first)
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Price (lowest first)']"))).click();
		//Thread.sleep(10000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@class,'bui-spinner--size-large')]/div")));
		String nopaf=driver.findElement(By.xpath("//h1[contains(text(),'properties found')]")).getText();
		Pattern p2=Pattern.compile("[0-9]+");
		Matcher m2=p2.matcher(nopaf);
		int anopaf=0;
		if(m2.find())
		{
			anopaf=Integer.parseInt(m2.group());
		}
		System.out.println("No of all properties found after filters applied are: "+anopaf);
		//Thread.sleep(3000);
		//Display the Lowest Valued hotel
		if(anopaf==0)
		{
			System.out.println("No single hotel is found after applying the given search criteria");
		}
		else
		{
			String hotel_name=driver.findElement(By.xpath("//*[@aria-label='Search results']/div[1]/div[2]/div/div/div/h3/a/span[1]")).getText();
			String hotel_price=driver.findElement(By.xpath("(//*[@class='bui-price-display__value prco-inline-block-maker-helper '])[1]")).getText();
			Pattern p3=Pattern.compile("[0-9,]+");
			Matcher m3=p3.matcher(hotel_price);
			if(m3.find())
			{
				hotel_price=m3.group();
			}
			System.out.println("Least priced Hotel is:");
			System.out.println(hotel_name+"-->"+hotel_price);
		}
		//Thread.sleep(3000);
		//Close site
		driver.close();	
	}
}
