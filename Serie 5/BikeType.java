/**
 * Created by Bennet on 28.11.2016.
 * An enum for all possible types of bikes
 */
public enum BikeType {

	TOPS(277.376,3.078,0.4891,"Hands on the tops"),
	DROPS(399.611,4.4226,0.3397,"Hands on the drops"),
	ROADSTER(181.0455,3.3899,0.7457,"Roadster");

	public final double A,B,SURFACE;
	public final String DESCRIPTION;

	BikeType(double a,double b,double surface,String disc){
		A = a;
		B = b;
		SURFACE = surface;
		DESCRIPTION = disc;
	}

}
