package bioassignmentdataset3;

import java.io.*;

public class loader {
    static public float[] load(int length, int count, int set) throws FileNotFoundException, IOException 
    {
        float[] toRead = new float[length * count];
        int index = 0;
        BufferedReader r;
        String x;
        try{
            r = new BufferedReader(new FileReader(String.format("data%d.txt", set)));
            r.readLine();
            while((x = r.readLine()) != null)
                for(int i = 0; i < length + 1; i++)
                {
                    if(i != length - 1)
                    {
                        for(String s : x.split(" "))
                            toRead[index] = Float.parseFloat(s);
                        index++;
                    }
                }
        } catch(Exception e){System.out.println(e);}
        return toRead;
    }
}
