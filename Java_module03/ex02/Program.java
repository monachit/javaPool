import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

class SumThread extends Thread{
    private int start;
    private int end;
    private int [] array;
    private long sum;

    public SumThread(int[] array, int start, int end)
    {
        this.array = array;
        this.start = start;
        this.end  = end;
    }
    
    @Override 
    public void run()
    {
        for (int i = start; i < end; i++)
            sum += array[i];
    }

    public int getEnd() {return end; }
    public long getSum() { return sum; }
    public int getStart() { return start; }    
    

}


public class Program{
    public static void main(String[] args)
    {
        if (args.length != 2)
            return ;
        String perfix1 = "--arraySize=";
        String perfix2 = "--threadsCount=";
        if (!args[0].startsWith(perfix1) || !args[1].startsWith(perfix2))
        {
            System.out.println("Usage: java Program --arraySize=<number> --threadsCount=<number>");
            return;
        }
        String countStr1 = args[0].substring(perfix1.length());
        String countStr2 = args[1].substring(perfix2.length());
        int arrayCount = Integer.parseInt(countStr1);
        int threadsCount = Integer.parseInt(countStr2);
        Random rand = new Random();
        int[] array = new int[arrayCount];
        long sum = 0;
        for (int i = 0; i < arrayCount; i++)
            array[i] = rand.nextInt(2001) - 1000;
        for (int i =0; i < arrayCount; i++)
            sum+= array[i];
        System.out.println("Sum: " + sum);
        int chunkSize = (arrayCount + threadsCount - 1) / threadsCount;
        int start = 0, end =0;
        SumThread[] threads = new SumThread[threadsCount];
        for (int i = 0; i < threadsCount; i++)
        {
            start = Math.min(i * chunkSize, arrayCount);
            end = Math.min(start + chunkSize, arrayCount);
            threads[i] = new SumThread(array, start, end);
        }

        for (SumThread t : threads)
            t.start();

        long totalsum = 0; 
        for (int i = 0; i < threadsCount; i++) {
            try {
                threads[i].join();
                totalsum += threads[i].getSum();
                if (threads[i].getStart() >= threads[i].getEnd()) {
                    System.out.println("Thread " + (i + 1) + ": empty, sum is 0");
                }
                else 
                {
                    System.out.println("Thread " + (i + 1) + ": from " + threads[i].getStart()
                        + " to " + (threads[i].getEnd() - 1) + " sum is " + threads[i].getSum());
                }
            } catch (InterruptedException e) {}
        }
        System.out.println("Sum by threads: " + totalsum);

    }
}
