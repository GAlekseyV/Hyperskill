package analyzer;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class Main {

    private void run(String[] args){
        if(args.length != 3){
            System.out.println("Please provide correct arguments\n");
            System.exit(0);
        }

        String result = "Unknown file type";
        String pattern = args[1];
        String fileType = args[2];
        try {
            List<String> allStrings = Files.readAllLines(Paths.get(args[0]));
            for(String str : allStrings){
                if(str.contains(pattern)){
                    result = fileType;
                    break;
                };
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        new Main().run(args);
    }
}
