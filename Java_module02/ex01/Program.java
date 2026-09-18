import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;




public class Program{

    public static Map<String, Integer> countWords(String path)
    {
        File myObj = new File(path);
        Map<String, Integer> wordCount = new HashMap<>();
        
        try (Scanner myReader = new Scanner(myObj)) {
          while (myReader.hasNextLine()) {
        
            String data = myReader.nextLine();
            String [] words = data.split("\\W+");
        
            for (int i = 0; i < words.length; i++)
            {
                if (words[i].isEmpty())
                    continue;
                String word = words[i].toLowerCase();
                if (wordCount.containsKey(word))
                    wordCount.put(word, wordCount.get(word) + 1);
                else 
                    wordCount.put(word, 1);
            
            }
          }

        } catch (FileNotFoundException e) {
           System.out.println("Cannot read file: " + path);
        }
        return wordCount;

    }


    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Program <input_file> <output_file>");
            return;
        }

        Map<String, Integer> countsA = countWords(args[0]);
        Map<String, Integer> countsB = countWords(args[1]);
        
        Set<String> dictionary = new TreeSet<>();

        dictionary.addAll(countsA.keySet());
        dictionary.addAll(countsB.keySet());

        System.out.println(dictionary);

        double dotProduct = 0;
        double sumA2 = 0;
        double sumB2 = 0;

        for(String word : dictionary)
        {
            int a = countsA.getOrDefault(word, 0);
            int b = countsB.getOrDefault(word, 0);

            dotProduct += a * b;
            sumA2 += a * a;
            sumB2 += b * b;
        }

        double result = dotProduct / (Math.sqrt(sumA2) * Math.sqrt(sumB2));
        try(FileWriter myWriter = new FileWriter("dictionary.txt"))
        {
            
            boolean first = true;
            for (String word : dictionary)
            {
                if (!first)
                    myWriter.write("\n");
                myWriter.write(word);
                first = false;
            }
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
        }
        System.out.println(result);
    }
}