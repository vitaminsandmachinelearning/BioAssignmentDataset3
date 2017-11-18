/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioassignmentdataset3;

import java.util.Random;

/**
 *
 * @author Jake
 */
public class rule {
    float[] r;
    int rlen;
    int rcount;
    public int fitness = 0;
    public rule(int rulelength, int rulecount, Random rng)
    {
        rlen = rulelength;
        rcount = rulecount;
        r = new float[rlen * rcount];
        float t;
        for(int i = 1; i < rlen * rcount + 1; i++)
        {
            r[i - 1] = i % rlen == 0 ? rng.nextInt(2) : rng.nextFloat();
            if(i % 2 == 0 && i % rlen != 0)
            {
                if(r[i - 2] > r[i - 1])
                {
                    t = r[i - 2];
                    r[i - 2] = r[i - 1];
                    r[i - 1] = t;
                }
            }
        }
    }
    
    public int getFitness()
    {
        fitness = 0;
        
        return fitness;
    }
    public String toString()
    {
        String s = "";
        for(float f : r)
            s += f + "\t ";
        return s;
    }
}
