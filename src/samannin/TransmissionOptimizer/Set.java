package samannin.TransmissionOptimizer;

/**
 * Created by Shadow on 3/27/2017.
 */
public class Set {

    private Gear ringGear;
    private Gear planetGear;
    private Gear sunGear;

    public Set(Gear ringGear, Gear planetGear, Gear sunGear){
        this.ringGear = ringGear;
        this.planetGear = planetGear;
        this.sunGear = sunGear;
    }

    public Gear getPlanetGear() {
        return planetGear;
    }

    public void setPlanetGear(Gear planetGear) {
        this.planetGear = planetGear;
    }

    public Gear getRingGear() {
        return ringGear;
    }

    public void setRingGear(Gear ringGear) {
        this.ringGear = ringGear;
    }

    public Gear getSunGear() {
        return sunGear;
    }

    public void setSunGear(Gear sunGear) {
        this.sunGear = sunGear;
    }

    public void solveSunGear(){
        sunGear.setTeeth(ringGear.getTeeth()-(planetGear.getTeeth()*2));
        double n = sunGear.getTeeth()/planetGear.getTeeth();
        sunGear.setRPM(-((2+n)*ringGear.getRPM()-2*(1+n)*planetGear.getRPM())/n);
    }

    public void solveRingGear(){
        ringGear.setTeeth(sunGear.getTeeth()+(2*planetGear.getTeeth()));
        double n = sunGear.getTeeth()/planetGear.getTeeth();
        ringGear.setRPM(-((n*sunGear.getRPM()-2*(1+n)*planetGear.getRPM())/(2+n)));
    }

    public void solvePlanetGear(){
        planetGear.setTeeth((ringGear.getTeeth()-sunGear.getTeeth())/2);
        double n = sunGear.getTeeth()/planetGear.getTeeth();
        planetGear.setRPM((n*sunGear.getRPM()+(2+n)*ringGear.getRPM())/(2*(1+n)));
    }
}
