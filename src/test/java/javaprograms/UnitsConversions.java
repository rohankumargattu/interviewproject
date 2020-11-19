package javaprograms;

public class UnitsConversions 
{
	public static void main(String[] args) 
	{
		//given centimeter to nearest inches value(round off)
		float cms=(float) 49.4;	//in cms
		float inches=(float) (cms/2.54);
		System.out.println(inches);
		String rz=String.format("%.0f",inches);
		int rv=Integer.parseInt(rz);
		System.out.println(rv);
	}
}
