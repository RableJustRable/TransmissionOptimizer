
public class Driver {
	public static void main(String args[]){
		Driver temp = new Driver();
		temp.run();
	}
	public void run(){
		System.out.println(solveForSun(60,.2857142857,20,1,20,0));
		System.out.println(solveForCarrier(40,.25,12,0,16,1));
		System.out.println(solveForRing(80,0,24,-.33333333333,32,1));		
	}
	public double solveForSun(double ringTeeth, double ringSpeed, double planetTeeth, double carrierSpeed, double sunTeeth, double sunSpeed){
		double n = sunTeeth/planetTeeth;
		sunSpeed = -((2+n)*ringSpeed-2*(1+n)*carrierSpeed)/n;
		return sunSpeed;
	}
	public double solveForCarrier(double ringTeeth, double ringSpeed, double planetTeeth, double planetSpeed, double sunTeeth, double sunSpeed){
		double n = sunTeeth/planetTeeth;
		double carrierSpeed = (n*sunSpeed+(2+n)*ringSpeed)/(2*(1+n));
		return carrierSpeed;
	}
	public double solveForRing(double ringTeeth, double ringSpeed, double planetTeeth, double carrierSpeed, double sunTeeth, double sunSpeed){
		double n = sunTeeth/planetTeeth;
		ringSpeed = -((n*sunSpeed-2*(1+n)*carrierSpeed)/(2+n));
		return ringSpeed;
	}
	public double get1stGR(){
		return 0;
	}
}
