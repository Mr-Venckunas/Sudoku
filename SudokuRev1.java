/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokurev1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Leandoer
 */
public class SudokuRev1 {

public static int userGrid[][]=new int[][]
            {{0,0,0,2,6,0,7,0,1},
             {6,8,0,0,7,0,0,9,0},
             {1,9,0,0,0,4,5,0,0},
             {8,2,0,1,0,0,0,4,0},
             {0,0,4,6,0,2,9,0,0},
             {0,5,0,0,0,3,0,2,8},
             {0,0,9,3,0,0,0,7,4},
             {0,4,0,0,5,0,0,3,6},
             {7,0,3,0,1,8,0,0,0},};    
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean running = true;
            int workGrid[][]=new int[9][9];
            ArrayList<Integer> msg = new ArrayList<Integer>();
            msg.add(1);
            msg.add(2);
            
            
           
                System.out.println("trying");
                while(running = true)
                {
                if(solve(userGrid))
                {
                    running = false;
                    System.out.println("Solution found");
                }
                }
            
            //printGrid(userGrid);
            //System.out.println("now printing worker grid");
            //workGrid = setWorker(userGrid, workGrid);
            //printGrid(workGrid);
            //setNumber(userGrid, workGrid);
           
            
        
            
        
    }
    public static int[][] setWorker(int[][] grid, int[][] a)
    {
        for(int i = 0; i<9; i++)
        {
            for(int l=0; l<9; l++)
            {
                a[i][l] = grid[i][l];
            }
        }
        return a;
    }
    public static void setNumber(int[][] grid, int[][] a)
    {
        Boolean running = true;
        Scanner sc = new Scanner(System.in);
            System.out.println("Enter Row 1-9");
            int vert = sc.nextInt();
            System.out.println("Enter Coolumn 1-9");
            int horiz = sc.nextInt();
            System.out.println("Enter value from 0-9");
            int value = sc.nextInt();
            grid[vert-1][horiz-1] = value;
            a[vert-1][horiz-1] = value;
            printGrid(grid);
            System.out.println("now printing worker grid");
            printGrid(a);
            if(running)
            {
                        System.out.println("ENTER Y TO DO BOOLEAN CHECK");
                        String str = sc.next();
                        if (str.equalsIgnoreCase("Y")) 
                        {
                             System.out.println("Please enter the Row you want to check from 1-9");
                             int chckrow = sc.nextInt();
                             System.out.println("Please enter the Column you want to check from 1-9");
                             int chckcol = sc.nextInt();
                             System.out.println("Please enter the number you want to check from 0-9");
                             int chknum = sc.nextInt();
                             if(check(grid,chckrow-1,chckcol-1,chknum))
                             {
                                 System.out.println("Found number");
                                 setNumber(grid,a);
                             }
                             else
                             {
                                 System.out.println("Number not found");
                                 setNumber(grid,a);
                             }
                        }
            setWorker(grid,a);
            setNumber(grid,a);
            }
    }
    //public static int getEmpty(int[][] grid)
    //{
    //    for (int i=0;i<9;i++)
    //   {
    //        for(int l=0;l<9;l++)
    //        {
    //            if(grid[i+1][l+1] == 0)
    //            {
    //                printGrid(grid);
    //                return grid[i+1][l+1];
     //           }
    //            else
     //           {
     //              
     //           }
     //       }
     //   }
     //   return grid[8][8];
   // }
    
    public static int getEmptyRow(int[][] grid)
    {
        for (int i=0;i<9;i++)
        {
            for(int l=0;l<9;l++)
            {
                if(grid[i][l] == 0)
                {
                    return i;
                }
            }
        }
        return grid[8][8];
    }
    
    public static int getEmptyCol(int[][] grid)
    {
        for (int i=0;i<9;i++)
        {
            for(int l=0;l<9;l++)
            {
                if(grid[i][l] == 0)
                {
                    return l;
                }
            }
        }
        return grid[9][9];
    }
    public static boolean solve(int[][] grid)
    {
        System.out.println("try2");
        
        if(getEmptyRow(grid) == 8 && getEmptyCol(grid) ==8 )
        {
            return true;
        }
        System.out.println("try3");
        
        
        int row = getEmptyRow(grid) ;
        int col = getEmptyCol(grid) ;
        System.out.println("try4");
        for(int number = 1; number<=9; number++)
        {
            System.out.println(number);
            if(check(grid, row, col, number))
            {
                //System.out.println("try6");
                grid[row][col] = number;
                userGrid[row][col] = number;
                printGrid(userGrid);
                
                if(solve(grid))
                {
                    return true;
                }
                
                grid[row][col] = 0;
            }
            
        }
        
        return false;
    }
    public static void printGrid(int[][] grid)
    {
        System.out.println();
        
        for(int i=0;i<9;i++)
        {
            
            for(int l=0; l<9; l++)
            {
                System.out.print(grid[i][l]);
            }
            System.out.println();
        }
    }
    
    public static boolean checkRow(int[][] grid, int row, int num)
    {
        
        for(int col=0; col<9; col++)
        {
            if(grid[row][col] == num)
            {
               // System.out.println("ROW ERROR");
                return true;
            }
        }
        return false;
    }
    
    public static boolean checkCol(int[][] grid, int col, int num)
    {
        
        for(int row=0; row<9; row++)
        {
            if(grid[row][col] == num)
            {
                //System.out.println("Col ERROR");
                return true;
            }
        }
        return false;
    }
    
    public static boolean checkGrid(int[][] grid, int startRow, int startCol, int num)
    {
        
        for(int row=0; row<3; row++)
        {
            for(int col=0; col<3; col++)
            {
          
                if(grid[row+startRow][col+startCol] == num)
                {
                    //System.out.println("GRID ERROR");
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static boolean check(int[][] grid, int startRow, int startCol, int num)
    {
        
        if(!checkRow(grid, startRow, num) && !checkCol(grid, startCol, num) && !checkGrid(grid,startRow,startCol,num))
        {
            //System.out.println("it is safe to place number here!");
            return true;
        }
        //System.out.println("it is NOT safe to place number here!");
        return false;
    }
    
}


