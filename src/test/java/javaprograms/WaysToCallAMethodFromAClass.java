package javaprograms;

import java.lang.reflect.Method;

public class WaysToCallAMethodFromAClass
{
	public static void main(String[] args) throws Exception
	{
		Parent p=new Parent();
		p.method1();
		
		Child c=new Child();
		c.method1();
		
		Method m[]=c.getClass().getMethods();
		int x=m.length;
		System.out.println(x);
		for(int i=0;i<x;i++)
		{
			if(m[i].getName().equals("method3"))
			{
				m[i].invoke(c);
			}
		}
	}
}
