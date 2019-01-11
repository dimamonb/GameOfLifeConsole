package ru.rzn.sbt.javaschool.gameoflife;
import java.util.Random;
public class Main {

    public static boolean[][] board(int size){
        boolean[][] result = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = false;
            }
        }
        return result;
    }

    public static void paint(boolean[][] boardToPaint){
        for (int i = 0; i < boardToPaint.length; i++) {
            for (int j = 0; j < boardToPaint[0].length; j++) {
                if (boardToPaint[i][j] != false) System.out.print("0");
                else System.out.print("+");
            }
            System.out.println();
        }
    }

    public static void setLiveCells(boolean[][] someGrid){
        //Glider
        someGrid[4][3] = true;
        someGrid[4][4] = true;
        someGrid[4][5] = true;
        someGrid[3][5] = true;
        someGrid[2][4] = true;
    }

    public static boolean[][] newGrid(boolean[][] someGrid){
        boolean[][] result = new boolean[someGrid.length][someGrid[0].length];
        int num;
        for (int i = 0; i < someGrid.length; i++) {
            for (int j = 0; j < someGrid[0].length; j++) {
                num = liveNeighboursCount(someGrid,i,j);
                if(someGrid[i][j] == true && (num == 2 || num == 3))
                    result[i][j] = true;
                else if (someGrid[i][j] == false && (num == 3))
                    result[i][j] = true;
                else
                    result[i][j] = false;
            }
        }
        return result;
    }
    public static int liveNeighboursCount(boolean[][] someGrid, int row, int column){
        int numberOfNeighbours = 0;
//        int rowMinusOne = (row-1 == -1)?someGrid.length-1:row-1;
//        int colMinusOne = (column-1 == -1)?someGrid.length-1:column-1;
//        int rowPlusOne = (row+1 > someGrid.length-1)?(row+1)-(someGrid.length-1):row+1;
//        int columnPlusOne =  (column+1 > someGrid.length-1)?(column+1)-(someGrid.length-1):column+1;

        int rowMinusOne = row - 1;
        int colMinusOne = column - 1;
        int rowPlusOne = row + 1;
        int columnPlusOne = column + 1;

        int[][] cellsForCheck = {
                {rowMinusOne, colMinusOne},
                {rowMinusOne, column},
                {rowMinusOne, columnPlusOne},
                {row, colMinusOne},
                {row, columnPlusOne},
                {rowPlusOne, colMinusOne},
                {rowPlusOne, column},
                {rowPlusOne, columnPlusOne},
        };


        for (int i = 0; i < cellsForCheck.length; i++) {
            if(onBoard(someGrid, cellsForCheck[i][0], cellsForCheck[i][1]) && someGrid[cellsForCheck[i][0]][cellsForCheck[i][1]] == true){
                numberOfNeighbours++;
            }
        }

        return numberOfNeighbours;
    }


    private static boolean onBoard(boolean[][] someGrid, int x, int y) {
        return x >= 0 && x < someGrid.length && y >= 0 &&
                y < someGrid[0].length;
    }


    public static void main(String[] args) {
        final int BOARDSIZE = 10;
        boolean[][] grid, newBoard;

        grid = board(BOARDSIZE);
        setLiveCells(grid);
        paint(grid);

        System.out.println();
        //clearConsole();
        grid = newGrid(grid);
        paint(grid);


        int i= 0;
        while(i != 40){
            System.out.println();
            //clearConsole();
            grid = newGrid(grid);
            paint(grid);

            i++;
        }
    }
/*
    public final static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }*/
}

