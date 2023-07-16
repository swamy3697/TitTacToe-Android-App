package com.swamy.tictactoe;


public class Checker {
    final int SIZE = 3;
    final int[][] EMPTYARRAY = {{0, 0}, {0, 0}, {0, 0}, {0, 0}};
    final String EMPTY = "-";
    int[][] resultArrayReturnedFromChecking=new int[4][2];

    public int[][] isWin(String [][] board) {
        resultArrayReturnedFromChecking=checkRows(board);

        if(resultArrayReturnedFromChecking[0][0]==1)
        {
            return resultArrayReturnedFromChecking;
        }
        resultArrayReturnedFromChecking=checkColumns(board);
        if(resultArrayReturnedFromChecking[0][0]==1)
        {
            return resultArrayReturnedFromChecking;
        }
        resultArrayReturnedFromChecking=checkDiagonals(board);
        if(resultArrayReturnedFromChecking[0][0]==1)
        {
            return resultArrayReturnedFromChecking;
        }
        return EMPTYARRAY;
    }

    private int[][] checkRows(String[][] board) {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] != EMPTY && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return new int[][]{{1, 1}, {i, 0}, {i, 1}, {i, 2}};
            }
        }
        return EMPTYARRAY;
    }

    private int[][] checkColumns(String[][] board) {
        for (int j = 0; j < SIZE; j++) {
            if (board[0][j] != EMPTY && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return new int[][]{{1, 1}, {0, j}, {1, j}, {2, j}};
            }
        }
        return EMPTYARRAY;
    }

    private int[][] checkDiagonals(String[][] board) {
        if (board[0][0] != EMPTY && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return new int[][]{{1, 1}, {0, 0}, {1, 1}, {2, 2}};
        }
        if (board[0][2] != EMPTY && board[0][2] == board[1][1] && board[1][1] == board[2][0])
        {
            return new int[][]{{1, 1}, {0, 2}, {1, 1}, {2, 0}};
        }
        return EMPTYARRAY;
    }


}


























/***
 * String [][] matrix =new String[3][3];
 *     String win="Win";
 *     String loss="Loss";
 *     public String check(String[][]newMatrix,int areaInMatrix,int placeArea)
 *     {
 *         matrix=newMatrix;
 *
 *
 *         switch(areaInMatrix)
 *         {
 *             case 0:
 *                 if (firstHorizontalCheck()==win)
 *                 {
 *                     return "firstHorizontal";
 *                 }
 *                 if (placeArea==0)
 *                 {
 *                     if (firstVerticalCheck()==win)
 *                     {
 *                         return "firstVertical";
 *                     }
 *
 *                     if (leftcrossCheck()==win)
 *                     {
 *                         return "leftcross";
 *                     }
 *
 *                 }
 *                 if (placeArea==1)
 *                 {
 *                     if (secondVerticalCheck()==win)
 *                     {
 *                         return "secondVertical";
 *                     }
 *
 *
 *                 }
 *                 if (placeArea==2)
 *                 {
 *
 *                     if (thirdVerticalCheck()==win)
 *                     {
 *                         return "thirdVerticalCheck";
 *                     }
 *                     if (rightcrossCheck()==win)
 *                     {
 *                         return "rightcross";
 *                     }
 *
 *
 *                 }
 *                 break;
 *             case 1:
 *                 if (secondHorizontalCheck()==win)
 *                 {
 *                     return "secondHorizontal";
 *                 }
 *                 if (placeArea==0)
 *                 {
 *                     if (firstVerticalCheck()==win)
 *                     {
 *                         return "firstVertical";
 *                     }
 *
 *
 *                 }
 *                 if (placeArea==1)
 *                 {
 *                     if (leftcrossCheck()==win)
 *                     {
 *                         return "leftcross";
 *                     }
 *                     if (rightcrossCheck()==win)
 *                     {
 *                         return "rightcross";
 *                     }
 *
 *                     if (secondVerticalCheck()==win)
 *                     {
 *                         return "secondVertical";
 *                     }
 *
 *
 *                 }
 *                 if (placeArea==2)
 *                 {
 *                     if (thirdVerticalCheck()==win)
 *                     {
 *                         return "thirdVerticalCheck";
 *                     }
 *
 *                 }
 *                 break;
 *             case 2:
 *                 if (thirdHorizontalCheck()==win)
 *                 {
 *                     return "thirdHorizontal";
 *                 }
 *                 if (placeArea==0)
 *                 {
 *                     if (firstVerticalCheck()==win)
 *                     {
 *                         return "firstVertical";
 *                     }
 *                     if (rightcrossCheck()==win)
 *                     {
 *                         return "rightcross";
 *                     }
 *
 *
 *                 }
 *                 if (placeArea==1)
 *                 {
 *                     if (secondVerticalCheck()==win)
 *                     {
 *                         return "secondVertical";
 *                     }
 *
 *
 *
 *                 }
 *                 if (placeArea==2)
 *                 {
 *                     if (thirdVerticalCheck()==win)
 *                     {
 *                         return "thirdVerticalCheck";
 *                     }
 *                     if (leftcrossCheck()==win)
 *                     {
 *                         return "leftcross";
 *                     }
 *
 *                 }
 *                 break;
 *             default:
 *                 throw new IllegalStateException("Unexpected value: " + areaInMatrix);
 *         }
 *         return loss;
 *     }
 */

/***
 *
 *   public String firstHorizontalCheck()
 *     {
 *         if((matrix[0][0] == matrix[0][1]) && (matrix[0][0] == matrix[0][2]) && (matrix[0][0] != "-"))
 *         {
 *             return win;
 *         }
 *         return loss;
 *     }
 *     public String secondHorizontalCheck()
 *     {
 *         if((matrix[1][0] == matrix[1][1]) && (matrix[1][0] == matrix[1][2]) && (matrix[1][0] != "-"))
 *         {
 *             return win;
 *         }
 *         return loss;
 *     }
 *     public String thirdHorizontalCheck()
 *     {
 *         if((matrix[2][0] == matrix[2][1]) && (matrix[2][0] == matrix[2][2]) && (matrix[2][0] != "-") )
 *         {
 *             return win;
 *         }
 *         return loss;
 *     }
 *     public String firstVerticalCheck()
 *     {
 *         if((matrix[0][0] == matrix[1][0]) && (matrix[0][0] == matrix[2][0]) && (matrix[0][0] != "-"))
 *         {
 *             return win;
 *         }
 *         return loss;
 *     }
 *     public String secondVerticalCheck()
 *     {
 *         if((matrix[0][1] == matrix[1][1]) && (matrix[0][1] == matrix[2][1])  && (matrix[0][1] != "-"))
 *         {
 *             return win;
 *         }
 *         return loss;
 *     }
 *     public String thirdVerticalCheck()
 *     {
 *         if((matrix[0][2] == matrix[1][2]) && (matrix[0][2] == matrix[2][2])  && (matrix[0][2] != "-"))
 *         {
 *             return win;
 *         }
 *         return loss;
 *     }
 *     public String leftcrossCheck()
 *     {
 *         if((matrix[0][0] == matrix[1][1]) && (matrix[0][0] == matrix[2][2]) && (matrix[0][0] != "-"))
 *         {
 *             return win;
 *         }
 *         return loss;
 *     }
 *     public String rightcrossCheck()
 *     {
 *         if((matrix[0][2] == matrix[1][1]) && (matrix[0][2] == matrix[2][0]) && (matrix[0][2] != "-"))
 *         {
 *             return win;
 *         }
 *         return loss;
 *     }
 * */