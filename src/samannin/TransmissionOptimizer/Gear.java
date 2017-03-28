package samannin.TransmissionOptimizer;

/*
    represnets a gear (either ring, sun, or planets)
 */

public class Gear {


	private double RPM;



	private double teeth;
	
	public Gear (double RPM, double teeth){
		this.RPM=RPM;
		this.teeth=teeth;
	}

    public Gear (double teeth){
        this.teeth = teeth;
        this.RPM = 0;
    }
	
	public double getRPM(){
		return RPM;
	}

	public void setRPM(double RPM) {
		this.RPM = RPM;
	}
	
	public double getTeeth(){
		return teeth;
	}

	public void setTeeth(double teeth) {
		this.teeth = teeth;
	}
}
