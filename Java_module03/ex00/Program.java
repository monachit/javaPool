import java.io.*;
import java.util.*;

class Mythread extends Thread {

    private int count;

    public Mythread(int count)
    {
        this.count = count;
    }

    @Override 
    public void run()
    {
        for (int i = 0; i < count ; i++)
            System.out.println("Egg");
    }
}

class Mythread2 extends Thread{

    private int count;

    public Mythread2(int count)
    {
        this.count = count;
    }
    
    @Override 
    public void run()
    {
        for (int i = 0; i < count ; i++)
            System.out.println("Hen");
    }
}
public class Program{
    public static void main(String[] args)
    {
        if (args.length != 1)
            return;
        String perfix = "--count=";
        if (!args[0].startsWith(perfix)){
            System.out.println("Usage: java Program --count=<number>");
            return;
        }
        String countStr = args[0].substring(perfix.length());
        int count = Integer.parseInt(countStr);

        Mythread t1 = new Mythread(count);
        Mythread2 t2 = new Mythread2(count);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            // TODO: handle exception
        }
        for (int i = 0; i < count ; i++)
            System.out.println("human");
    }
}