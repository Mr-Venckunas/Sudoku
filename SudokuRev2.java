/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokurev2;

/**
 *
 * @author Leandoer
 */
public class SudokuRev2 {

    //Initial Grid to solve
    //Static variable, can find template of uncompleted sudoku puzzles online and input here.
    public static int[][] TEST = {{0,0,0,2,6,0,7,0,1},
             {6,0,0,0,0,0,0,0,0},
             {4,3,1,5,0,0,0,0,0},
             {0,0,0,0,0,0,0,0,0},
             {0,0,0,0,0,0,0,0,0},
             {0,0,0,0,0,0,0,0,0},
             {0,0,0,0,0,0,0,0,0},
             {0,0,0,0,0,0,0,0,0},
             {0,0,0,0,0,0,0,0,0},}; 
    private int Board[][];
    //Constructor to create a grid from the test grid.
    public SudokuRev2(int[][] Board)
    {
        //Creates a 2d ARRAY 1-9
        this.Board = new int[9][9];
        
        for(int i=0;i<9;i++)
        {
            for(int l=0;l<9;l++)
            {
                this.Board[i][l] = Board[i][l];
            }
        }
    }
    //RULES ARE HERE
    
    //Rule ROW
    private boolean isInRow(int row, int num)
    {
        //Takes input ROW and NUM
        //Checks row 0-9
        for (int i=0;i<9;i++)
        {
            //Checks if any number in Row contains Input number, if yes return true which would mean rule broken.
            if(Board[row][i] == num)
            {
                return true;
            }
        }
        //If it is okay to place number return false.
       return false;
    }
    
    //Rule COL
    private boolean isInCol(int Col, int num)
    {
       //Takes input COL and NUM
        //Checks Column 0-9
        for (int i=0;i<9;i++)
        {
            //Checks if any number in Column contains Input number, if yes return true which would mean rule broken.
            if(Board[i][Col] == num)
            {
                return true;
            }
        }
         //If it is okay to place number return false.
       return false;
    }
    
    //Rule GRID
    private boolean isInGrid(int row, int col, int num)
    {
        //Ensures that grid checking can only occur for every 1,4,7 positions.
        int r = row-row%3;
        int c = col-col%3;
        //Checks grid from 0+3, 3+3, 6+3
        for(int i = r; i < r + 3; i++)
        {
            for(int l = c; l < c + 3; l++)
            {
                if(Board[i][l] == num)
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    //Applies the rules in the simplest way, if they are all valid proceed.
    private boolean isGood(int row, int col, int num)
    {
        return !isInRow(row,num) && !isInCol(col,num) && !isInGrid(row,col,num);
    }
    
    //Solve
    public boolean TrySolve()
    {
        //Go through columns 1-9
        for(int i=0; i<9;i++)
        {
            //Go through Rows 1-9
            for(int l =0;l<9;l++)
            {
                //If the position at the board is 0
               if(Board[i][l] == 0)
               {
                   //Go through possible numbers through 1-9
                   for(int k=1;k<=9;k++)
                   {
                       //Checks the row, column, and grid rules
                       if(isGood(i,l,k))
                       {
                           //If row column and gril rules apply then set number.
                           Board[i][l] = k;
                           
                           //If grid complete
                           if(TrySolve())
                           {
                               //Grid complete
                               return true;
                           }
                           //If number invalid or grid incomplete set that position to 0
                           else
                           {
                               Board[i][l] = 0;
                           }
                       }
                   }
                   
                   //If there is still an empty space in grid it will return as NOT COMPLETE
                   return false;
               }
            }
        }
        //Grid already complete
        return true;
    }
    
    //Prints out the completed puzzle
    public void showPuzzle()
    {
        //Go through columns 1-9
        for(int i=0; i<9;i++)
        {
            //Go through rows 1-9
            for(int l=0; l<9; l++)
            {
                //print out completed board.
                System.out.print(Board[i][l]);
            }
            //Prints spaces so whole board isnt on one line.
            System.out.println();
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Creates new object to work with
       SudokuRev2 test = new SudokuRev2(TEST);
        
       //Attempts to solve the sudoku grid based off the object and TEST variable.
        if(test.TrySolve())
        {
            
            System.out.println("Sudoku has been solved");
            test.showPuzzle();
        }
        else
        {
            test.showPuzzle();
            System.out.println("Sudoku is impossible, or the solving algorithm is just too bad to solve it.");
        }
    }
    
}
