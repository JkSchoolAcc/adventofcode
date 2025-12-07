import java.util.*;
import java.io.*;

public class Day7 {

    public static long dfs(char[][] map, int x, int y, boolean[][] visited, int[][] prefix){
        Queue<Integer> pos = new LinkedList<>();
        pos.add(x);

        long count = 0;

        while(y < map.length){

            System.out.println(map.length-1-y);

            int size = pos.size();

            while(size-- > 0){
                int xVal = pos.poll();

                if(map[y][xVal] == '^'){
                    if(!visited[y][xVal]) count++;

                    visited[y][xVal] = true;

                    if(xVal-1 >= 0 && !pos.contains(xVal-1)) pos.offer(xVal-1);
                    if(xVal+1 < map[y].length && !pos.contains(xVal+1)) pos.offer(xVal+1);
                }
                else{
                    int splittersLeft = prefix[xVal][prefix[xVal].length-1];
                    if(y > 0) splittersLeft-=prefix[xVal][y-1];
                    
                    if(splittersLeft > 0 && !pos.contains(xVal)) pos.offer(xVal);


                }
            }

            y++;
        }

        return count;
    }

    public static long timelines(char[][] map, int x, int y, Map<Integer, Long> count, int[][] prefix, long[][] visited){
        
        if(x < 0 || x >= map[0].length) return Integer.MIN_VALUE;
        
        if(visited[y][x] != 0)
        {
            count.put(x, count.getOrDefault(x, 0L)+visited[y][x]);
            return visited[y][x];
        }

        int timesLinesLeft = prefix[x][prefix[0].length-1];
        if(y > 0) timesLinesLeft -= prefix[x][y-1];

        if(timesLinesLeft < 1){
            count.put(x, count.getOrDefault(x,0L)+1);
            return 1;
        }
        
        if(map[y][x] == '^'){
            long left = timelines(map,x-1, y, count, prefix, visited);
            long right = timelines(map, x+1, y, count, prefix, visited);

            long res =  left+right;
            visited[y][x] = res;

            return res;
        }


        long res =  timelines(map, x, y+1, count, prefix, visited);
        return (visited[y][x] = res);


    }
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new FileReader("Day7.in"));
        
        List<String> stream = sc.lines().toList();
        HashMap<Integer, Long> timelineCount = new HashMap<>();

        char[][] map = new char[stream.size()][];
        boolean[][] visited = new boolean[stream.size()][];
        long[][] visited2 = new long[stream.size()][];

        int[][] prefix = new int[stream.get(0).length()][stream.size()];

        for(int i = 0; i < stream.size(); i++){
            map[i] = stream.get(i).toCharArray();

            for(int c = 0; c < map[i].length; c++)
            {
                if(i > 0)
                    prefix[c][i] = prefix[c][i-1];
                if(map[i][c]=='^') prefix[c][i]++;

            } 

            visited[i] = new boolean[map[i].length];
            visited2[i] = new long[map[i].length];
        }

        int startX = map[0].length/2;

        long count = (dfs(map, startX, 1, visited, prefix));


        for(char[] row : map)
            System.out.println(row);

        long p2 =timelines(map, startX, 0, timelineCount, prefix, visited2);

        System.out.printf("Part One: %d\n",count);

        System.out.printf("Part Two: %d\n", p2);

        //7436775307
        //
    }
}