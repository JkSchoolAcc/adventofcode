import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.io.*;

public class Day6 {

    public static Pattern findSkips = Pattern.compile("\\d+\\s(\\s+)");

    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new FileReader("Day6.in"));
        
        long p1 = 0;
        long p2 = 0;

        List<String> stream = sc.lines().toList();

        String[][] arr = new String[(int)stream.size()][];

       for(int i = 0; i < stream.size(); i++){
            String[] opers = stream.get(i).split("");
            arr[i] = opers;
        }

        for(int oper = 0; oper < arr[arr.length-1].length; oper++){
            if(arr[arr.length-1][oper].equals(" ")) continue;

            String operation = arr[arr.length-1][oper];

            long sum = operation.equals("*") ? 1 : 0;
            long sum2 = operation.equals("*") ? 1 : 0;

            
            int c2 = oper;
            
            while(c2 < arr[arr.length-1].length && (arr[arr.length-1][c2].matches("[ \\d]")||c2==oper)){
                int r = 0;
                long num2 = 0;
                while(r < arr.length-1 && arr[r][c2].equals(" ")) r++;

                if(r == arr.length-1) break;

                while(r < arr.length-1 && !arr[r][c2].equals(" "))
                {
                    num2*=10;
                    num2+=Integer.parseInt(arr[r++][c2]);
                }

                c2++;

                if(operation.equals("*")) sum2*=num2;
                else sum2+=num2;
            }

            for(int r = 0; r < arr.length-1; r++){
                int c =  oper;
                long num = 0;

                while(arr[r][c].equals(" ")) c++;
                 while(c < arr[r].length && arr[r][c].matches("\\d")){
                    num*=10;
                    num += Integer.parseInt(arr[r][c].strip());
                    c++;
                }

                if(operation.equals("*")) sum*=num;
                else sum+=num;
            }

            p1+=sum;
            p2+=sum2;
        }

        System.out.printf("Part One: %d\n", p1);
        System.out.printf("Part Two: %d\n", p2);
    }
}