package samannin.TransmissionOptimizer;

/**
 * Created by Shadow4 on 3/28/2017.
 * Represents a possible optimized solution
 */
public class Solution {
    Set s1;
    Set s2;
    Set s3;
    double score;

    public Solution(Set s1, Set s2, Set s3){
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }

    public void setScore(double score){
        this.score = score;
    }

    public Set getS1() {
        return s1;
    }

    public Set getS2() {
        return s2;
    }

    public Set getS3() {
        return s3;
    }

    public String toString(){
        return ""+score+","+s1.getSunGear().getTeeth()+","+s1.getPlanetGear().getTeeth()+","+s2.getSunGear().getTeeth()+","+s2.getPlanetGear().getTeeth()+","+s3.getSunGear().getTeeth()+","+s3.getPlanetGear().getTeeth();
    }

}
