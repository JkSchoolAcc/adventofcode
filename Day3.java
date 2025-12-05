import java.util.*;
import java.io.*;

public class Day3 {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new FileReader("Day3.in"));

        String line;

        long part1 = 0;

        long part2 = 0;

        while((line = sc.readLine()) != null){
            part1+=solve(line, 2);
            part2+=solve(line, 12);
        }

        System.out.printf("Part 1: %d\n",part1);
        System.out.printf("Part 2: %d\n",part2);

    }

    public static long solve(String line, int digits){
        
        int iDigit = digits;

        char[] arr = line.toCharArray();

        String num = "";

        int L = -1;

        for(int round = 0; round < iDigit; round++){


            int maxIdx = L+1;

            for(int i = L+2; i <= arr.length-digits; i++){
                if(arr[i] > arr[maxIdx]) maxIdx = i;
            }

            digits--;
            L = maxIdx;

            num+=arr[L];

        }

        
        return Long.parseLong(num);
    }
    
}