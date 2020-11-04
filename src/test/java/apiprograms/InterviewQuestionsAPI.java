package apiprograms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class InterviewQuestionsAPI
{
	public static void main(String[] args) throws Exception
	{
		//Register end-point and Create HTTP Request
		RestAssured.baseURI="https://samples.openweathermap.org/data/2.5/forecast/hourly";
		RequestSpecification req=RestAssured.given();
		//Send HTTP Request with one path parameter value
		Response res=req.queryParam("q","London,us").queryParam("appid","b6907d289e10d714a6e88b30761fae22").request(Method.GET,"");
		//Get Status line from Response
		String rsl=res.getStatusLine();
		System.out.println("Status line is:\n"+rsl);
		JsonPath jp=res.jsonPath();
		List<String> l=jp.getList("list.dt_txt");
		System.out.println("Total no of dates are: "+l.size());
		
		//Scenario 1
		int c=1;
		for(int i=0;i<l.size();i++)
		{
			if((i+1)<=l.size()-1)
			{
				String[] dates1=l.get(i).split(" ");
				String date1=dates1[0];
				String[] dates2=l.get(i+1).split(" ");
				String date2=dates2[0];
				if(date1.equals(date2))
				{
					continue;
				}
				else
				{
					c=c+1;
				}
			}
		}
		System.out.println("Total no of days response contains are: "+c);
		
		//Scenario 2
		int flag=0;
		for(int i=0;i<l.size();i++)
		{
			if((i+1)<=l.size()-1)
			{
				String[] dates1=l.get(i).split(" ");
				String date1=dates1[1];
				String[] hours1=date1.split(":");
				int hour1=Integer.parseInt(hours1[0]);
				
				String[] dates2=l.get(i+1).split(" ");
				String date2=dates2[1];
				String[] hours2=date2.split(":");
				int hour2=Integer.parseInt(hours2[0]);
				
				if(hour2-hour1==1 || hour2-hour1==-23)
				{
					continue;
				}
				else
				{
					flag=1;
					break;
				}
			}
		}
		if(flag==0)
		{
			System.out.println("Forecast is in hourly interval");
		}
		else
		{
			System.out.println("Any hour missed/sometimes forecast is not in hourly interval");
		}
		
		//Scenario 3
		List<Map<String,Object>> lofmaps=jp.getList("list");
		ArrayList<Object> temp=new ArrayList<Object>();
		ArrayList<Object> temp_min=new ArrayList<Object>();
		ArrayList<Object> temp_max=new ArrayList<Object>();
		for(int i=0;i<lofmaps.size();i++)
		{
			for(Map<String,Object> map:lofmaps)
			{
				Map<String,Object> main=(Map<String, Object>) lofmaps.get(i).get("main");
				if(main.get("temp").toString().contains("."))
				{
					temp.add((float) main.get("temp"));
				}
				else
				{
					temp.add((int) main.get("temp"));
				}
				if(main.get("temp_min").toString().contains("."))
				{
					temp_min.add((float) main.get("temp_min"));
				}
				else
				{
					temp_min.add((int) main.get("temp_min"));
				}
				if(main.get("temp_max").toString().contains("."))
				{
					temp_max.add((float) main.get("temp_max"));
				}
				else
				{
					temp_max.add((int) main.get("temp_max"));
				}
			}
		}
		
		int flag2=0;
		for(int i=0;i<temp.size();i++)
		{
			float x=Float.parseFloat(temp.get(i).toString());
			float y=Float.parseFloat(temp_min.get(i).toString());
			float z=Float.parseFloat(temp_max.get(i).toString());
			if(x>=y && x<=z)
			{
				continue;
			}
			else
			{
				flag2=1;
				break;
			}
		}
		if(flag2==0)
		{
			System.out.println("All days temperatures are within the boundaries");
		}
		else
		{
			System.out.println("Temp crossed boundaries");
		}
		
		//Scenario 4 and 5
		int number1=0;
		int number2=0;
		List<Map<String,Object>> lm=jp.getList("list");
		for(int i=0;i<lm.size();i++)
		{
			List<Map<String,Object>> wl=(List<Map<String, Object>>) lm.get(i).get("weather");
			for(int j=0;j<wl.size();j++)
			{
				int id=(int) wl.get(j).get("id");
				if(id==500)
				{
					String y=(String) wl.get(j).get("description");
					if(y.equals("light rain"))
					{
						continue;
					}
					else
					{
						number1=1;
					}
				}
				
				if(id==800)
				{
					String y=(String) wl.get(j).get("description");
					if(y.equals("clear sky"))
					{
						continue;
					}
					else
					{
						number2=1;
					}
				}
			}
		}
		
		if(number1==0)
		{
			System.out.println("For all \"500\" id's description is \"light rain\"");
		}
		else
		{
			System.out.println("For all \"500\" id's description is NOT \"light rain\"");
		}
		if(number2==0)
		{
			System.out.println("For all \"800\" id's description is \"clear sky\"");
		}
		else
		{
			System.out.println("For all \"800\" id's description is NOT \"clear sky\"");
		}
	}
}
