package samannin.TransmissionOptimizer;

public class Driver {
	public static void main(String args[]){
		Driver temp = new Driver();
		temp.run();
	}
	public void run(){
		//System.out.println(solveForSun(60,.2857142857,20,1,20,0));
		//System.out.println(solveForCarrier(40,.25,12,0,16,1));
		//System.out.println(solveForRing(80,0,24,-.33333333333,32,1));
		Gear ringGear1 = new Gear(40);
		Gear planetGear1 = new Gear(12);
		Gear sunGear1 = new Gear(16);


		Gear ringGear2 = new Gear(40);
		Gear planetGear2 = new Gear(12);
		Gear sunGear2 = new Gear(16);


        Gear ringGear3 = new Gear(40);
        Gear planetGear3 = new Gear(12);
        Gear sunGear3 = new Gear(16);

        Set s1 = new Set(ringGear1,planetGear1,sunGear1);
		Set s2 = new Set(ringGear2,planetGear2,sunGear2);
        Set s3 = new Set(ringGear3,planetGear3,sunGear3);
        System.out.println(get1stGR(s1,s2,s3));
        System.out.println(get2ndGR(s1,s2,s3));

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
	public double get1stGR(Set s1, Set s2, Set s3){
        s3.getRingGear().setRPM(0);
        s3.getSunGear().setRPM(1);
        s3.solvePlanetGear();
        return 1/s3.getPlanetGear().getRPM();
	}

	public double get2ndGR(Set s1, Set s2, Set s3){
        s2.getRingGear().setRPM(0);
        s2.getSunGear().setRPM(1);
        s2.solvePlanetGear();
        s3.getRingGear().setRPM(s2.getPlanetGear().getRPM());
        s3.getSunGear().setRPM(1);
        s3.solvePlanetGear();
        return 1/s3.getPlanetGear().getRPM();
	}

}
