package javaprograms;

import java.util.ArrayList;
import java.util.Collections;

public class StringsSortingAlphabeticalOrder
{
	public static void main(String[] args)
	{
		ArrayList<String> a=new ArrayList<String>();
		a.add("Shwetha");
		a.add("ZeelPatel");
		a.add("Malayalam");
		a.add("Anusha");
		a.add("Liril");
		a.add("Priyanka");
		a.add("Rohan");
		
		/*Unsorted List*/
		System.out.println("Before Sorting:");
		for(String counter: a)
		{
			System.out.print(counter+"\t");
		}
		System.out.println();
		/* Sort statement*/
		Collections.sort(a);
		//Collections.reverse(a);

		/* Sorted List*/
		System.out.println("After Sorting:");
		for(String counter: a)
		{
			System.out.print(counter+"\t");
		}
	}
}
