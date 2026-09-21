import java.util.*;
import java.io.*;


class Downloadmanager{
    private List<String> url;
    private int index = 0;

    public Downloadmanager(List<String> url)
    {
        this.url = url;
    }
    public synchronized int getIndext()
    {
        if (index >= url.size())
            return -1;
        index++;
        return index;
    }
}

class Worker extends Thread{
    private Downloadmanager manager;

    public Worker(Downloadmanager manager, String name)
    {
        super(name);
        this.manager = manager;
    }

    @Override 
    public void run()
    {
        while (true)
        {
            int index = manager.getIndext();
            if (index == -1)
                break;
            System.out.println(getName() + " start download file number " + index);
             try {
                Thread.sleep(500 + (int)(Math.random() * 1500));
            } catch (InterruptedException e) {}
            System.out.println(getName() + " finish download file number " + index);
        }
    }
}

public class Program {
    public static void main(String [] args)
    {
        if (args.length != 1)
            return ;
        String perfix = "--threadsCount=";
        if (!args[0].startsWith(perfix))
            return ;
        String count = args[0].substring(perfix.length());
        int length = Integer.parseInt(count);
        List <String> url = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("files_urls.txt"))) 
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                if (!line.isEmpty())
                    url.add(line);
            }
            
        } catch (Exception e) {
            System.out.println("Cannot read files_urls.txt");
        }
        Downloadmanager manager = new Downloadmanager(url);
        Worker[] thread = new Worker[length];

        for (int i = 0; i < length ; i++)
            thread[i] = new Worker(manager, "Thread-" + (i + 1));

        for (Worker t : thread)
            t.start();

        for (Worker t : thread)
        {
            try {
                t.join();
            } catch (Exception e) {
            }
        }
    }
}