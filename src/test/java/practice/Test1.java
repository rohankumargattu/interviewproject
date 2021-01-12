package practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test1 
{
	public static void main(String[] args) throws InterruptedException 
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.booking.com/searchresults.en-gb.html?aid=304142&label=gen173nr-1FCAEoggI46AdIM1gEaGyIAQGYAQm4ARfIAQzYAQHoAQH4AQuIAgGoAgO4AsPJ9v8FwAIB0gIkNTQyODFhMTYtZGYzNi00ZmQ5LWI1YzItNjI1YTM4ZWUwNmUy2AIG4AIB&sid=7863c1ad54c3e319e6442aa6f5fed8a8&tmpl=searchresults&ac_click_type=b&ac_position=0&age=2&checkin_month=1&checkin_monthday=13&checkin_year=2021&checkout_month=2&checkout_monthday=2&checkout_year=2021&class_interval=1&dest_id=20088325&dest_type=city&dtdisc=0&from_sf=1&group_adults=2&group_children=1&iata=NYC&inac=0&index_postcard=0&label_click=undef&no_rooms=1&order=price&postcard=0&raw_dest_type=city&room1=A%2CA%2C2&sb_price_type=total&search_selected=1&shw_aparth=1&slp_r_match=0&srpvid=9ac6704918c800bc&ss=New%20York%2C%20New%20York%20State%2C%20United%20States&ss_all=0&ss_raw=New&ssb=empty&sshis=0&top_ufis=1&nflt=class%3D3%3B&rsf=");
		Thread.sleep(5000);
		String hotel_price=driver.findElement(By.xpath("(//*[@class='bui-price-display__value prco-inline-block-maker-helper '])[1]")).getText();
		Pattern p3=Pattern.compile("[0-9,]+");
		Matcher m3=p3.matcher(hotel_price);
		if(m3.find())
		{
			String lhp=m3.group();
			System.out.println(lhp);
		}
		driver.close();
	}
}
