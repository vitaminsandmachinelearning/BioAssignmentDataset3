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
public class BioAssignmentDataset3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        loader l = new loader();
        float[] data = l.load(13, 2000, 3);
        rule r = new rule(13, 1, new Random());
        System.out.println(r.toString());
    }
    
}
