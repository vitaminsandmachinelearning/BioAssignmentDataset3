/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioassignmentdataset3;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;

/**
 *
 * @author Jake
 */
public class BioAssignmentDataset3 {
    public rule run(int poolsize, int rulelength, int rulecount, Random rng, float[] data, int generations, float mutationrate, int testno) throws IOException {
        rule[] rules = new rule[poolsize];
        rule[] offspring = new rule[poolsize];
        
        int bi = 0;
        int best = 0;
        int worst = 1000000;
        int avg = 0;
        ArrayList<String> lines = new ArrayList<>();
        Path file = Paths.get(String.format("DS3VROR%d.txt", testno));
        
        for(int i = 0; i < poolsize; i++)
            rules[i] = new rule(rulelength, rulecount, rng);
        for(rule r : rules)
            r.getFitness(data);
        for(int gen = 0; gen < generations; gen++)
        {
            worst = 1000000;
            for(rule r : rules)
            {
                int fit = r.getFitness(data);
                if(fit > best)
                    best = fit;
                if(fit < worst)
                    worst = fit;
                avg += fit;
            }
            avg /= poolsize;
            lines.add(String.format("%s\t%s\t%s",worst,avg,best));
            for(int i = 0; i < poolsize; i++)
            {
                int a = rng.nextInt(poolsize);
                int b = rng.nextInt(poolsize);
                offspring[i] = rules[a].fitness > rules[b].fitness ? new rule(rulelength, rulecount, Arrays.copyOf(rules[a].r, rules[a].r.length)) : new rule(rulelength, rulecount, Arrays.copyOf(rules[b].r, rules[b].r.length));
            }
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
            for(int i = 0; i < poolsize; i++)
            {
                for(int j = 0; j < rulelength * rulecount; j++)
                    if(rng.nextFloat() < mutationrate)
                        if(j % rulelength != 0 || j == 0)
                            offspring[i].r[j] += rng.nextInt(2) == 1 ? 0.1f : -0.1f;
                        else if(j % rulelength == 0 || j != 0)
                            offspring[i].r[j] = offspring[i].r[j] == 1 ? 0 : 1;
            }
            rules = Arrays.copyOf(offspring, poolsize);
        }
        best = 0;
        bi = 0;
        for(int i = 0; i < rules.length; i++)
        {
            int t = rules[bi].getFitness(data);
            if(t > best)
            {
                best = t;
                bi = i;
            }
        }
        
        Files.write(file, lines, Charset.forName("UTF-8"));
        return rules[bi];
    }
    
}
