import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2{

    public static Pattern pat1 = Pattern.compile("(\\d+)\\1");
    public static Pattern pat2 = Pattern.compile("(\\d+)\\1+");

    public static boolean isInvalidID1(long num){
        String numVal = Long.toString(num);

        return pat1.matcher(numVal).matches();

    }

    public static boolean isInvalidID2(long num){
        String numVal = Long.toString(num);

        return pat2.matcher(numVal).matches();
    }

    public static void main(String[] args) throws IOException {
;
        // System.setOut(new PrintStream("Day2.txt"));

        BufferedReader sc = new BufferedReader(new FileReader("Day2.in"));

        String[] queries = sc.readLine().split(",");

        long sum1 = 0;
        long sum2 = 0;

        for(String query: queries){
            String[] unparsedNums = query.split("-");

            long start = Long.parseLong(unparsedNums[0]);
            long end = Long.parseLong(unparsedNums[1]);

            // long queryRes = getInvalidIds(start, end);

            for(long i = start; i <= end; i++){
                if(isInvalidID1(i)) sum1+=i;
                if(isInvalidID2(i)) sum2+=i;
            }

            // sum+=queryRes;
            
        }

        System.out.println("Part 1:");
        System.out.println(sum1);

        System.out.println("Part 2:");
        System.out.println(sum2);

    }
}

//40042591037
//40042591037 -- TOO HIGH
//40041433861