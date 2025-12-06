import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.io.*;

public class Day6 {

    public static Pattern findSkips = Pattern.compile("\\d+\\s(\\s+)");

    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new FileReader("Day6.in"));
        

        List<String> stream = sc.lines().toList();

        String[][] arr = new String[(int)stream.size()][];

       for(int i = 0; i < stream.size(); i++){
            String[] opers = stream.get(i).replaceAll("(^\\s+)|(\\s+$)", "").replaceAll("\\s+"," ").split(" "); 
            
            arr[i] = opers;
       }


        long sum = 0;
        long sum2 = 0;
        for(int c = 0; c < arr[arr.length-1].length; c++){
            char operation = arr[arr.length-1][c].charAt(0);

            long inner = 0;
            long inner2 = 0;

            if(operation == '*') inner = inner2 = 1;

            for(int r = 0; r < arr.length-1; r++){
                if(arr[r][c].isBlank()) continue;
// 
                // System.out.println(arr[r][c]);
                
                if(operation == '*') inner*=Long.parseLong(arr[r][c]);
                else inner+=Long.parseLong(arr[r][c]);
            }
            sum+=inner;
            
        }
        System.out.printf("Part One: %d\n", sum);
        System.out.println(sum2);
    }
}