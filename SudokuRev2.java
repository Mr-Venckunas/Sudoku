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
public class Sudoku {

    //Initial Grid to solve
    public static int[][] TEST = {{0,0,0,2,6,0,7,0,1},
             {6,8,0,0,7,0,0,9,0},
             {1,9,0,0,0,4,5,0,0},
             {8,2,0,1,0,0,0,4,0},
             {0,0,4,6,0,2,9,0,0},
             {0,5,0,0,0,3,0,2,8},
             {0,0,9,3,0,0,0,7,4},
             {0,4,0,0,5,0,0,3,6},
             {7,0,3,0,1,8,0,0,0},}; 
    private int Board[][];
    //Constructor to create a grid from the test grid.
    public Sudoku(int[][] Board)
    {
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
        for (int i=0;i<9;i++)
        {
            if(Board[row][i] == num)
            {
                return true;
            }
        }
       return false;
    }
    
    //Rule COL
    private boolean isInCol(int Col, int num)
    {
        for (int i=0;i<9;i++)
        {
            if(Board[i][Col] == num)
            {
                return true;
            }
        }
       return false;
    }
    
    //Rule GRID
    private boolean isInGrid(int row, int col, int num)
    {
        int r = row-row%3;
        int c = col-col%3;
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
    
    //Attempt solving.
    public boolean TrySolve()
    {
        for(int i=0; i<9;i++)
        {
            for(int l =0;l<9;l++)
            {
               if(Board[i][l] == 0)
               {
                   for(int k=1;k<=9;k++)
                   {
                       if(isGood(i,l,k))
                       {
                           Board[i][l] = k;
                           
                           if(TrySolve())
                           {
                               //Grid complete
                               return true;
                           }
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
    
    public void showPuzzle()
    {
        for(int i=0; i<9;i++)
        {
            for(int l=0; l<9; l++)
            {
                
                System.out.print(Board[i][l]);
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        
       Sudoku test = new Sudoku(TEST);
        
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
