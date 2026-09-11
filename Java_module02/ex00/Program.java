import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



public class Program{
    public static void main(String[] args)
    {
        Scanner Myobjs = new Scanner(System.in);
        String moad = Myobjs.nextLine();
        File obj = new File("signatures.txt");
        Map<String, String> signatures = new HashMap<>();
        try (Scanner myReader = new Scanner(obj)){
            while (myReader.hasNextLine())
            {
                String data = myReader.nextLine();
                String[] pairs = data.split(",");
                signatures.put(pairs[0], pairs[1]);
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            System.exit(1);
        }


        int decimalValue;
        List<String> results = new ArrayList<>();

        // candidates daba fiha:
        // [ Entry(PNG -> 89 50 4E 47), Entry(GIF -> 47 49 46 38) ]        
        String match = null;
        
        while (!moad.equals("42"))
        {
            int position = 0;
            List<Map.Entry<String, String>> candidates = new ArrayList<>(signatures.entrySet());
            match = null;
            try(FileInputStream input = new FileInputStream(moad))
            {
                // List of byte by byte  47 49 ....
                int i;
                while ((i = input.read()) != -1)
                {
                    Iterator<Map.Entry<String, String>> it = candidates.iterator();
                    while (it.hasNext())
                    {
                        Map.Entry<String, String> entry = it.next();
                        String[] hexBytes = entry.getValue().trim().split(" ");

                        if (position >= hexBytes.length)
                        {
                            it.remove();
                            continue;
                        }

                        decimalValue = Integer.parseInt(hexBytes[position], 16);

                        if (i != decimalValue)
                            it.remove();

                        else if (position == hexBytes.length - 1)
                            match = entry.getKey();
                            

                    }

                    if (candidates.isEmpty())
                        break;
                    position++;
                        
                        
                }
                if (match == null)
                    System.out.println("UNDEFINED");
                else
                {
                    System.out.println("PROCESSED");
                    results.add(match);
                }


            } catch (IOException e) {
                 System.out.println("UNDEFINED");
            }

            moad = Myobjs.nextLine();
        }
        if (!results.isEmpty())
        {
            try {
                System.out.println("Results: " + results);
                FileWriter myWriter = new FileWriter("result.txt");
                for (String result : results)
                {
                    myWriter.write(result);
                }
                 myWriter.close(); 
            } catch (IOException e) {
                System.out.println("An error occurred.");
            }
        }
    }
}