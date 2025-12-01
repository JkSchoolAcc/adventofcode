import java.util.*;
import java.io.*;

public class Day1 {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new FileReader("Day1.in"));
        
        int dial = 50;
        int zeroCount = 0;
        String line;

        int extraZeroCount = 0;

        while((line = sc.readLine()) != null){
            char direction = line.charAt(0);
            int distance = Integer.parseInt(line.substring(1));

            if(direction == 'R'){
                extraZeroCount += (dial+distance)/100;
                dial = (dial+distance)%100;
            }
            else{
                extraZeroCount += Math.abs((distance-dial)/100);
                if(dial-distance <= 0 && dial != 0) extraZeroCount++;
                dial = dial-(distance%100);
                if(dial < 0) dial = 100+dial;
                
            }

            System.out.printf("%s -> %d\n", line, dial);
            if(dial == 0) zeroCount++;
        }

        System.out.printf("Part I: %d\n", zeroCount);
        System.out.printf("Part II: %d\n", extraZeroCount);
    }
}