import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // put your code here
        new Main().runner();
    }

    private void runner(){
        Scanner scanner = new Scanner(System.in);
        String pattern = scanner.nextLine();
        String text = scanner.nextLine();

        List<Integer> occurrences = KMPSearch.search(text, pattern);
        System.out.println(occurrences.size());
        for(int index : occurrences){
            System.out.print(index + " ");
        }
    }
}

class KMPSearch {
    private static int[] prefixFunction(String str){
        int[] prefixFunc = new int[str.length()];

        for(int i = 1; i < str.length(); i++)
        {
            int j = prefixFunc[i - 1];

            while(j > 0 && str.charAt(i) != str.charAt(j)){
                j = prefixFunc[j - 1];
            }

            if(str.charAt(i) == str.charAt(j)){
                j += 1;
            }

            prefixFunc[i] = j;
        }
        return prefixFunc;
    }

    static List<Integer> search(String text, String pattern){
        int[] prefixFunc = prefixFunction(pattern);
        ArrayList<Integer> occurrences = new ArrayList<Integer>();

        if (text.length() < pattern.length()) {
            return occurrences;
        }
        if(text.isEmpty()){
            occurrences.add(0);
            return occurrences;
        }
        if(pattern.isEmpty()){
            for(int i = 0; i < text.length(); i++){
                occurrences.add(i);
            }
            return occurrences;
        }

        int j = 0;

        for(int i = 0; i < text.length(); i++){
            while(j > 0 && text.charAt(i) != pattern.charAt(j)){
                j = prefixFunc[j - 1];
            }

            if(text.charAt(i) == pattern.charAt(j)){
                j += 1;
            }

            if(j == pattern.length()){
                occurrences.add(i - j + 1);
                j = 0;
            }
        }
        return occurrences;
    }

    static List<Integer> anotherSearch(String text, String pattern){
        int[] prefixFunc = prefixFunction(pattern + '#' + text);
        ArrayList<Integer> occurrences = new ArrayList<Integer>();

        for(int i = pattern.length() + 1; i < prefixFunc.length; i++){
            if(prefixFunc[i] == pattern.length()){
                occurrences.add(i - 2 * pattern.length());
            }
        }
        return occurrences;
    }
}
