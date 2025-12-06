import java.util.*;
import java.io.*;

record Range(long start, long end) implements Comparable<Range>{
    public boolean inRange(long n){
        return n >= start && n <= end;
    }

    public int compareTo(Range other){
        long startDiff = Long.compare(this.start(), other.start());

        if(start == 0) return Long.compare(this.end(), other.end());
        return (int) startDiff;
    }

    //559990685160468
    //401137154895499

    public long diff(){
        return this.end() - this.start() +1;
    }

    public String toString(){
        return String.format("%d -> %d", this.start(), this.end());
    }
}

public class Day5 {
    public static void main(String[] args) throws IOException{
        BufferedReader sc = new BufferedReader(new FileReader("Day5.in"));


        String line;

        LinkedList<Range> ranges = new LinkedList<>();

        
        
        while(!(line = sc.readLine()).equals("")){
            String[] stringNums = line.split("-");
            
            long start = Long.parseLong(stringNums[0]);
            long end = Long.parseLong(stringNums[1]);
            
            Range range = new Range(start,end);
            ranges.add(range);
        }
        
        Collections.sort(ranges);      
        

        LinkedList<Range> newRanges = new LinkedList<>();


        while(ranges.size() > 1){
            
            System.out.println(ranges);

            Range prev = ranges.remove();
            Range next = ranges.peek();
            
            while(next.start() <= prev.end()){
                prev = new Range(prev.start(), Math.max(prev.end(), next.end()));
                ranges.remove();
                if(ranges.size()>0)
                next = ranges.peek();
                else break;

                System.out.println("\t"+ranges);
            }

            newRanges.add(prev);
        }

        while(!ranges.isEmpty()) newRanges.add(ranges.remove());

        ranges = newRanges;

        System.out.println();
        System.out.println(ranges);

        long p2 = 0;

        long sum = 0;
        while((line = sc.readLine()) != null){
            long query = Long.parseLong(line);
            
            for(Range range : ranges)
                if(range.inRange(query))
                {
                        sum++;
                        break;                    
                }
                
        
        
        }

        while(!ranges.isEmpty()) p2+=ranges.remove().diff();
        System.out.printf("Part One: %d\n", sum);
        System.out.printf("Part Two:  %d", p2);

        //319701265798056 too LOW
        //322211098737218
        //322211098737036
        //343143696885053
    }
}