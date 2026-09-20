import java.io.*;
import java.util.*;



class Printer{
    private  boolean trueOrfalse = false;
    public synchronized void printA()
    {
        while (trueOrfalse)
        {
            try {
                wait();
                
            } catch (Exception e) {}
        }
        trueOrfalse = true;
        System.out.println("Egg");
        notify();
    }

    public synchronized void printB()
    {
        while (!trueOrfalse)
        {
            try {
                wait();
            } catch (Exception e) {}
        }
        trueOrfalse = false;
        System.out.println("Hen");
        notify();

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
        Printer p = new Printer();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < count; i++)
                p.printA();
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < count; i++)
                p.printB();
        });

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