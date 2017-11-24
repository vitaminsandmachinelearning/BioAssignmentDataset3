/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioassignmentdataset3;

import java.util.Arrays;
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
            r[i - 1] = i % rlen == 0 ? rng.nextInt(2) : rng.nextFloat();
    }
    
    public rule(int rulelength, int rulecount, float[] R)
    {
        r = Arrays.copyOf(R, R.length);
        rlen = rulelength;
        rcount = rulecount;
    }
    
    public int getFitness(float[] data)
    {
        fitness = 0;
        for(int i = 0; i < data.length / 7; i++)
            for(int j = 0; j < rcount ; j++)
                if(matchrule(j * rlen, i * 7, data))
                {  
                    if(r[(j * rlen) + rlen - 1] == data[(i * 7) + 7 - 1])
                        fitness++;
                    break;
                }
        return fitness;
    }
    
    public boolean matchrule(int rl, int dl, float[] data)
    {
        for(int i = 0; i < 6; i++)
        {
            if(r[rl + i * 2] <= r[rl + i * 2 + 1]){
                if(r[rl + i * 2] > data[dl + i] || data[dl + i] > r[rl + i * 2 + 1])
                    return false;
            }else
                if(r[rl + i * 2] < data[dl + i] || data[dl + i] < r[rl + i * 2 + 1])
                    return false;
        }
        return true;
    }
    
    @Override
    public String toString()
    {
        String s = "";
        for(float f : r)
            s += f + "\t ";
        return s;
    }
}
