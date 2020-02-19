package at.donaugarten;
import edu.princeton.cs.introcs.StdDraw;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int rows = 7, cols = 10, fieldSize = 60;
        double rad = 0.4;
        String stdPrompt = "Enter column number or 0 to exit:";
	    int[][] board = new int[cols][rows];
	    int player = 1;
	    boolean moveOk = true;
        initBoard(board, fieldSize, rad);
        System.out.println("Welcome to 4-in-a-row!");
        Scanner sc = new Scanner(System.in);
        System.out.println(stdPrompt);
        int in = sc.nextInt();
        while (in != 0) {
            moveOk = move(board, in-1, player, rad);
            if (moveOk) player = player%2 + 1;
            else System.out.println("Column " + in + " full. Please retry.");
            System.out.println(stdPrompt);
            in = sc.nextInt();
        }
        System.out.println("Good bye!");
        System.exit(0);
    }

    public static boolean move (int[][] board, int inCol, int player, double rad) {
        int rows=board[0].length, i = 0;
        if (player==1) StdDraw.setPenColor(StdDraw.RED);
        else StdDraw.setPenColor(StdDraw.YELLOW);
        while (i<rows) {
            if (board[inCol][i]==0) {
                board[inCol][i] = player;
                StdDraw.filledCircle(inCol, i, rad);
                return true;
            }
            i++;
        }
        return false;
    }


    public static void initBoard (int[][] board, int fSize, double rad) {
        int rows=board[0].length, cols = board.length;
        double hRows = rows / 2., hCols = cols / 2.;
        StdDraw.setCanvasSize(cols*fSize, rows*fSize);
        StdDraw.setXscale(-0.5, cols-0.5);
        StdDraw.setYscale(-0.5, rows-0.5);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.01);
        StdDraw.filledRectangle(hCols-0.5,hRows-0.5,hCols,hRows);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int i=0; i<cols; i++) {
            for (int j=0; j<rows; j++) {
                board[i][j] = 0;
                StdDraw.circle(i, j, rad);
            }
        }
        return;
    }
}
