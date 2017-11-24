/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioassignmentdataset3;

import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Jake
 */
public class Overlord {
    //USED TO RUN MULTIPLE GAS IN A ROW
    static BioAssignmentDataset3 bad3 = new BioAssignmentDataset3();
    static Random rng = new Random();
    
    static int tests = 10;
    
    static int poolsize = 200;
    static int rulelength = 13;
    static int rulecount = 150;
    static int generations = 500;
    
    static float mutationrate = 1f / (rulelength * rulecount);
    
    public static void main(String args[]) throws IOException
    {
        float[] data = loader.load(7, 2000, 3);
        int mfit = 1000000000;
        rule r = new rule(1,1,new float[]{0f});
        int fit = 0;
        int bfit = 0;
        for(int i = 0; i < tests; i++)
        {
            System.out.println("Test: " + i);
            r = bad3.run(poolsize, rulelength, rulecount, rng, data, generations, mutationrate);
            fit = r.fitness;
            if(fit > bfit)
                bfit = fit;
            if(fit < mfit)
                mfit = fit;
        }
        System.out.println(r.toString());
        System.out.println("Worst: " + mfit);
        System.out.println("Best: " + bfit);
    }
}
