/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioassignmentdataset3;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Jake
 */
public class BioAssignmentDataset3 {
    public rule run(int poolsize, int rulelength, int rulecount, Random rng, float[] data, int generations, float mutationrate) throws IOException {
        rule[] rules = new rule[poolsize];
        rule[] offspring = new rule[poolsize];
        
        int bi = 0;
        int best = 0;
        
        for(int i = 0; i < poolsize; i++)
            rules[i] = new rule(rulelength, rulecount, rng);
        for(rule r : rules)
        {
            r.getFitness(data);
            //System.out.println(r.toString());
        }
        for(int gen = 0; gen < generations; gen++)
        {
            //System.out.println("gen");
            for(int i = 0; i < poolsize; i++)
            {
                int a = rng.nextInt(poolsize);
                int b = rng.nextInt(poolsize);
                offspring[i] = rules[a].fitness > rules[b].fitness ? new rule(rulelength, rulecount, Arrays.copyOf(rules[a].r, rules[a].r.length)) : new rule(rulelength, rulecount, Arrays.copyOf(rules[b].r, rules[b].r.length));
            }
            //System.out.println("tourny");
            //for(rule r : offspring)
            //    System.out.println(r.toString());
            for(int i = 0; i < poolsize; i+=2)
            {
                int c = rng.nextInt(rulelength * rulecount);
                float t = 0;
                for(int j = c; j < rulelength * rulecount; j++)
                {
                    t = offspring[i].r[j];
                    offspring[i].r[j] = offspring[i + 1].r[j];
                    offspring[i + 1].r[j] = t;
                }
            }
            //System.out.println("cross");
            //for(rule r : offspring)
            //    System.out.println(r.toString());
            for(int i = 0; i < poolsize; i++)
            {
                for(int j = 0; j < rulelength * rulecount; j++)
                    if(rng.nextFloat() < mutationrate)
                        if(j % rulelength != 0 || j == 0)
                            offspring[i].r[j] += rng.nextInt(2) == 1 ? 0.1f : -0.1f;
                        else if(j % rulelength == 0 || j != 0)
                            offspring[i].r[j] = offspring[i].r[j] == 1 ? 0 : 1;
            }
            //System.out.println("mut");
            //for(rule r : offspring)
            //    System.out.println(r.toString());
            rules = Arrays.copyOf(offspring, poolsize);
            //System.out.println("copy");
            //for(rule r : rules)
            //    System.out.println(r.toString());
        }
        for(int i = 0; i < rules.length; i++)
        {
            int t = rules[bi].getFitness(data);
            if(t > best)
            {
                best = t;
                bi = i;
            }
        }
        return rules[bi];
    }
    
}
