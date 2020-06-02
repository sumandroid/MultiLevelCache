import domain.models.MultiCache;
import processors.CommandProcessor;
import processors.CommandProcessorImpl;
import services.MultiCacheService;
import services.MultiCacheServiceImpl;

import java.util.HashMap;
import java.util.Scanner;

public class DriverClass {

    public static void main(String []args){
        try{
            System.out.println("Welcome to multi-level cache system");
            System.out.println("Enter levels of cache");
            Scanner scanner = new Scanner(System.in);
            int levels = scanner.nextInt();
            if(levels == 0) throw new IllegalStateException("levels cannot be zero");
            int i = 1;
            HashMap<Integer, Integer> levelToCapacityMap = new HashMap<>();
            while(i <= levels){
                System.out.println("enter capacity of level: " + i);
                Integer capacity = scanner.nextInt();
                levelToCapacityMap.put(i, capacity);
                i++;
            }
            System.out.println("initializing multi level cache......");
            MultiCache multiCache = new MultiCache(levelToCapacityMap);
            MultiCacheService multiCacheService = new MultiCacheServiceImpl(multiCache);
            CommandProcessor commandProcessor = new CommandProcessorImpl(multiCacheService);
            while(true){
                System.out.println("Enter command");
                String input = scanner.nextLine();
                if(!input.equals("")){
                    if(input.equalsIgnoreCase("exit")) break;
                    String result = commandProcessor.execute(input);
                    System.out.println(result);
                }
            }
            System.out.println("System exiting");
        }catch (Exception e ){
            System.out.println(e.getMessage());
        }
    }
}
