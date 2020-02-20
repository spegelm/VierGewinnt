package at.donaugarten;
import edu.princeton.cs.introcs.StdDraw;
import java.awt.Font;

public class Main {

    public static void main(String[] args) {
        int cols = 8, rows = 6;
        int[][] board = new int[cols][rows];
        int fieldSize = 60;
        double radius = 0.35;
        boolean newGame = true;
        int mouseX, mouseY;
        while (newGame) {
            int player = 1, fieldsUsed = 0;
            initBoard(board, fieldSize, radius);
            updateStatusBar ("Welcome! Click a column to play.", player, cols, radius);
            boolean newMove = true, isFull = false, isWon = false;
            while (newMove) {
                if (StdDraw.isMousePressed()) {
                    mouseX = (int) Math.round(StdDraw.mouseX());
                    mouseY = (int) Math.round(StdDraw.mouseY());
                    StdDraw.pause(200);
                    if (mouseY >= 0) {
                        if (isFull || isWon) newMove = false;
                        else if (move(board, mouseX, player, radius)) {
                            fieldsUsed++;
                            // check board status
                            if (checkWon(board, player)) {
                                updateStatusBar("Player " + player + " has won! Click to restart.", player, cols, radius);
                                isWon = true;
                            } else if (fieldsUsed>=cols*rows) {
                                updateStatusBar("Board is full. Click to restart.", player, cols, radius);
                                isFull = true;
                            } else {
                                player = player % 2 + 1;
                                updateStatusBar("Player " + player + " is next.", player, cols, radius);
                            }
                        } else updateStatusBar("Column " + (mouseX+1) + " is full. Please retry.", player, cols, radius);
                    } else if (mouseX > cols-2) {
                        newMove = false;
                        newGame = false;
                    }
                }
            }
        }
        System.exit(0);
    }

    private static void initBoard (int[][] board, int fSize, double radius) {
        int rows=board[0].length, cols = board.length;
        double hRows = rows / 2., hCols = cols / 2., penWidth = 0.01;
        StdDraw.setCanvasSize(cols*fSize, (rows+1)*fSize); // extra row for status bar
        StdDraw.setXscale(-0.5, cols-0.5);
        StdDraw.setYscale(-1.5, rows-0.5); // status bar at the bottom
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(penWidth);
        StdDraw.filledRectangle(hCols-0.5,hRows-0.5,hCols,hRows);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int i=0; i<cols; i++) {
            for (int j=0; j<rows; j++) {
                board[i][j] = 0;
                StdDraw.circle(i, j, radius+4*penWidth);
            }
        }
    }

    private static void updateStatusBar (String text, int player, int cols, double radius) {
        double hCols = cols / 2.;
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.filledRectangle(hCols-0.5,-1.,hCols,0.5);
        if (player==1) StdDraw.setPenColor(StdDraw.RED);
        else StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.filledCircle(0., -1., radius);
        Font font = new Font("Arial", Font.BOLD, 20);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(hCols-0.5, -1.05, text);
        StdDraw.text(cols-1.05, -1.05, "Exit");
    }

    private static boolean move (int[][] board, int inCol, int player, double radius) {
        int rows=board[0].length, i = 0;
        if (player==1) StdDraw.setPenColor(StdDraw.RED);
        else StdDraw.setPenColor(StdDraw.YELLOW);
        while (i<rows) {
            if (board[inCol][i]==0) {
                board[inCol][i] = player;
                StdDraw.filledCircle(inCol, i, radius);
                return true;
            }
            i++;
        }
        return false; // column was full
    }

    private static boolean checkWon (int[][] board, int player) {
        int rows=board[0].length, cols = board.length, count;
        for (int i=0; i<cols; i++) {
            for (int j=0; j<rows; j++) {
                count = 0; //check horizontally
                for (int n=0; n<4 && i+n<cols; n++) {
                    if (board[i+n][j]==player) { count++; if (count >=4) return true; }
                }
                count = 0; //check vertically
                for (int n=0; n<4 && j+n<rows; n++) {
                    if (board[i][j+n]==player) { count++; if (count >=4) return true; }
                }
                count = 0; //check upwards
                for (int n=0; n<4 && i+n<cols && j+n<rows; n++) {
                    if (board[i+n][j+n]==player) { count++; if (count >=4) return true; }
                }
                count = 0; //check downwards
                for (int n=0; n<4 && i+n<cols && j-n>=0; n++) {
                    if (board[i+n][j-n]==player) { count++; if (count >=4) return true; }
                }
            }
        }
        return false;
    }
}
