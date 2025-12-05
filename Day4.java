import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Day4 {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new FileReader("Day4.in"));

        Object[] lines = sc.lines().toArray();


        char[][] mat = new char[(int) lines.length][];
        char[][] dismat = new char[(int) lines.length][];

        long ttl = 0;

        for(int i = 0; i < lines.length; i++){
            mat[i] = ((String)(lines[i])).toCharArray();
            dismat[i] = ((String)(lines[i])).toCharArray();

        }

        long p1 = solve(mat, false);

        System.out.printf("Part One: %d\n",p1);

        long p2 = 0;

        long curr;

        while((curr = solve(mat, true)) > 0) p2 += curr;

        System.out.printf("Part Two: %d\n", p2);
        
    }

    public static long solve(char[][] mat, boolean remove){
        long ttl = 0;

        for(int r = 0; r < mat.length; r++){
            for(int c = 0; c < mat.length; c++){

                if(mat[r][c] != '@') continue;

                int paperCount = 0;

                for(int dirY = -1; dirY <= 1; dirY++)
                    for(int dirX = -1; dirX <= 1; dirX++)
                        if(dirX == 0 && dirY == 0) continue;
                        else if(c+dirX >= 0 && c+dirX < mat[c].length && r+dirY >= 0 && r+dirY < mat.length){
                            paperCount+=(mat[r+dirY][c+dirX] == '@' ? 1 : 0);
                        }
                
                if(paperCount < 4){
                    ttl++;
                    if(remove) mat[r][c] = 'X';
                } 
            }
        }
        return ttl;
    }
}
