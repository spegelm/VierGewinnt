package at.donaugarten;
import edu.princeton.cs.introcs.StdDraw;

public class Main {

    public static void main(String[] args) {
        int rows = 7, cols = 10, fieldSize = 60;
        double rad = 0.4;
	    int[][] board = new int[cols][rows];
        initBoard(board, fieldSize, rad);
        
    }

    public static void initBoard (int[][] board, int fSize, double rad) {
        int rows=board[0].length, cols = board.length;
        double hRows = rows / 2., hCols = cols / 2.;
        StdDraw.setCanvasSize(cols*fSize, rows*fSize);
        StdDraw.setXscale(-0.5, cols-0.5);
        StdDraw.setYscale(-0.5, rows-0.5);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledRectangle(hCols-0.5,hRows-0.5,hCols,hRows);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int i=0; i<cols; i++) {
            for (int j=0; j<rows; j++) {
                board[i][j] = 0;
                StdDraw.filledCircle(i, j, rad);
            }
        }
        return;
    }
}
