package samannin.TransmissionOptimizer;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Driver {
    int numToKeep=100;
    double worstScore;
    int minGearSize = 10;
    int maxGearSize = 200;
    ArrayList<Solution> solutionList = new ArrayList<Solution>();
    double targetRatios[] = new double[7];

    public static void main(String args[]){
		Driver temp = new Driver();
		temp.run();
	}
	public void run(){

        targetRatios[0]=0;
        targetRatios[1]=3.1;
        targetRatios[2]=1.81;
        targetRatios[3]=1.41;
        targetRatios[4]=1;
        targetRatios[5]=0.71;
        targetRatios[6]=0.61;

        worstScore = 100;


        //System.out.println(solveForSun(60,.2857142857,20,1,20,0));
		//System.out.println(solveForCarrier(40,.25,12,0,16,1));
		//System.out.println(solveForRing(80,0,24,-.33333333333,32,1));
		Gear ringGear1 = new Gear(80);
		Gear planetGear1 = new Gear(22);
		Gear sunGear1 = new Gear(36);


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
        System.out.println(get3rdGR(s1,s2,s3));
        System.out.println(get4thGR(s1,s2,s3));
        System.out.println(get5thGR(s1,s2,s3));
        System.out.println(get6thGR(s1,s2,s3));

        for (int i=0;i<10000000;i++){
            Solution sol = new Solution(generateRandomSet(),generateRandomSet(),generateRandomSet());
            addToBestScores(sol);
        }

        System.out.println(solutionListToString());

    }

    public Set generateRandomSet(){
        Gear planetGear = new Gear(ThreadLocalRandom.current().nextInt(minGearSize, maxGearSize + 1));
        Gear sunGear = new Gear(ThreadLocalRandom.current().nextInt((int) planetGear.getTeeth(), maxGearSize + 1));
        Gear ringGear = new Gear(sunGear.getTeeth()+(2*planetGear.getTeeth()));

        return new Set(ringGear,planetGear,sunGear);
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
	public static double get1stGR(Set s1, Set s2, Set s3){
        s3.getRingGear().setRPM(0);
        s3.getSunGear().setRPM(1);
        s3.solvePlanetGear();
        return 1/s3.getPlanetGear().getRPM();
	}
    public static double get1stGR(Solution sol){
        return get1stGR(sol.getS1(),sol.getS2(),sol.getS3());
    }

	public static double get2ndGR(Set s1, Set s2, Set s3){
        s2.getRingGear().setRPM(0);
        s2.getSunGear().setRPM(1);
        s2.solvePlanetGear();
        s3.getRingGear().setRPM(s2.getPlanetGear().getRPM());
        s3.getSunGear().setRPM(1);
        s3.solvePlanetGear();
        return 1/s3.getPlanetGear().getRPM();
	}
    public static double get2ndGR(Solution sol){
        return get2ndGR(sol.getS1(),sol.getS2(),sol.getS3());
    }

    public static double get3rdGR(Set s1, Set s2, Set s3){
        s1.getRingGear().setRPM(0);
        s1.getSunGear().setRPM(1);
        s1.solvePlanetGear();

        s2.getRingGear().setRPM(s1.getPlanetGear().getRPM());
        s2.getSunGear().setRPM(1);
        s2.solvePlanetGear();

        s3.getRingGear().setRPM(s2.getPlanetGear().getRPM());
        s3.getSunGear().setRPM(1);
        s3.solvePlanetGear();

        return 1/s3.getPlanetGear().getRPM();
    }
    public static double get3rdGR(Solution sol){
        return get3rdGR(sol.getS1(),sol.getS2(),sol.getS3());
    }

    public static double get4thGR(Set s1, Set s2, Set s3){
        s3.getRingGear().setRPM(1);
        s3.getSunGear().setRPM(1);
        s3.solvePlanetGear();

        return s3.getPlanetGear().getRPM();
    }
    public static double get4thGR(Solution sol){
        return get4thGR(sol.getS1(),sol.getS2(),sol.getS3());
    }

    public static double get5thGR(Set s1, Set s2, Set s3){
        s1.getRingGear().setRPM(0);
        s1.getSunGear().setRPM(1);
        s1.solvePlanetGear();

        s2.getRingGear().setRPM(s1.getPlanetGear().getRPM());
        s2.getPlanetGear().setRPM(1);
        s2.solveSunGear();

        s3.getRingGear().setRPM(1);
        s3.getSunGear().setRPM(s2.getSunGear().getRPM());
        s3.solvePlanetGear();

        return 1/s3.getPlanetGear().getRPM();
    }
    public static double get5thGR(Solution sol){
        return get5thGR(sol.getS1(),sol.getS2(),sol.getS3());
    }

    public static double get6thGR(Set s1, Set s2, Set s3){
        s2.getRingGear().setRPM(0);
        s2.getPlanetGear().setRPM(1);
        s2.solveSunGear();

        s3.getRingGear().setRPM(1);
        s3.getSunGear().setRPM(s2.getSunGear().getRPM());
        s3.solvePlanetGear();

        return 1/s3.getPlanetGear().getRPM();
    }
    public static double get6thGR(Solution sol){
        return get6thGR(sol.getS1(),sol.getS2(),sol.getS3());
    }

    private void addToBestScores(Solution sol){
        double score = evaluateSolution(sol,targetRatios);
        if (score < worstScore){
            int i = 0;
            for (i=i;i<solutionList.size();i++){
                Solution test = solutionList.get(i);
                if (score<test.getScore()){
                    break;
                }
            }
            solutionList.add(i,sol);
            if (solutionList.size()>numToKeep){
                try{
                    solutionList.remove(numToKeep+1);
                }
                catch (Exception E){
                    E.printStackTrace();
                }
            }
        }
    }

    private double evaluateSolution(Solution sol, double targetRatios[]){
        double score =0;

        score += Math.abs(targetRatios[1]-get1stGR(sol));
        score += Math.abs(targetRatios[2]-get2ndGR(sol));
        score += Math.abs(targetRatios[3]-get3rdGR(sol));
        score += Math.abs(targetRatios[4]-get4thGR(sol));
        score += Math.abs(targetRatios[5]-get5thGR(sol));
        score += Math.abs(targetRatios[6]-get6thGR(sol));
        sol.setScore(score);
        return score;
    }

    private String solutionListToString(){
        StringBuilder sb = new StringBuilder();
        for(Solution s:solutionList){
            sb.append(""+ s.toString()+"\n");
        }
        return sb.toString();
    }

}
