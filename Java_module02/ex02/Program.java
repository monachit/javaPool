import java.io.File;
import java.util.Scanner;



public class Program{
    
    public static void main (String [] args)
    {
        if (args.length != 1)
        {
            System.out.println("Usage: java Program --current-folder=<path>");
            return;
        }
        String perfix = "--current-folder=";
        if(!args[0].startsWith(perfix)){
            System.out.println("Usage: java Program --current-folder=<path>");
            return;
        }

        String path = args[0].substring(perfix.length());
        File folder = new File(path);
        if (!folder.exists())
        {
            System.out.println("Error: folder does not exist");
            return ;
        }
        if (!folder.isDirectory())
        {
            System.out.println("Error: not a directory");
            return ;
        }
        Scanner Myobjs = new Scanner(System.in);
        String moad = Myobjs.nextLine();
        while (!moad.equals("exit"))
        {
            String[] words = moad.trim().split("\\s+");
            if (words.length == 0 || words[0].isEmpty())
                continue;
            switch (words[0]) {
                case "ls":
                    if (words.length != 1)
                        System.out.println("ls: too many arguments");
                    else
                    {
                        File dir = new File(path);
                        String[] files = dir.list();

                        for(String file : files)
                        {
                            System.out.println(file);
                        }
                    }
                    break;
                case "cd":
                    if(words.length != 2)
                        System.out.println("cd: expected 1 argument");
                    else
                    {
                        File dir = new File(path+ "/" + words[1]);
                        if (!dir.exists())
                            System.out.println("cd: no such file or dierctory: " + words[1]);

                        else if (!dir.isDirectory())
                            System.out.println("cd : Not a directory : " + words[1]);
                        else
                            path = dir.getPath();
                    }
                    break;
                case "mv":
                    if(words.length != 3)
                        System.out.println("mv: expected 2 argument");
                    else
                    {
                        String what = words[1];
                        String where = words[2];
                        File dir = new File(path + "/" + what);
                        if (!dir.exists())
                        {
                            System.out.println("mv: " + what + ": No such file or directory");
                            break;
                        }
                        File destination = new File(path + "/" + where);
                        File actualDestination;
                        if (destination.isDirectory())
                            actualDestination = new File (destination, dir.getName());
                        else
                            actualDestination = destination;

                        boolean check = dir.renameTo(actualDestination);
                        if (!check)
                            System.out.println("mv: cannot move " + what);
    
                    }
                    break;
                default:
                    System.out.println(words[0] + ": command not found");
            }
            moad = Myobjs.nextLine();
        }


    }
}