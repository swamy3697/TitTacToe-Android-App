package com.swamy.tictactoe;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.Arrays;

public class Matrix {
    String[][] matrix;

    int[] returnedPosition;
    int[][] returnArrayFromCheckClass;
    Checker checker;
    final int[][] EMPTY_ARRAY_IN_MATRIX_CLASS = {{0, 0}, {0, 0}, {0, 0}, {0, 0}};
    Matrix() {
        this.matrix = new String[][]{{"-", "-", "-"}, {"-", "-", "-"}, {"-", "-", "-"}};
        returnArrayFromCheckClass=new int[4][2];
        checker=new Checker();
    }

    String getMatrixValues() {
        //return Arrays.toString(matrix);
        String str = "{{";
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++) {
                str += matrix[i][j]+"";
            }
            str+="}\n";
        }
        return str+="}";
    }

    public int[][] updateInMatrix(int position,String whosTurn)
    {
        this.returnedPosition= position(position);
        this.matrix[this.returnedPosition[0]][this.returnedPosition[1]]=whosTurn;
        returnArrayFromCheckClass=checker.isWin(this.matrix);
        for (int[] k:returnArrayFromCheckClass)
        {
            for (int i:k)
            {
                Log.d("MainActivity from matrix class", "iiii:  "+ i);
            }
        }
        if(returnArrayFromCheckClass[0][0]==1)
        {
            reset();
            return returnArrayFromCheckClass;

        }
        return EMPTY_ARRAY_IN_MATRIX_CLASS;


    }
    public void reset()
    {
        this.matrix = new String[][]{{"-", "-", "-"}, {"-", "-", "-"}, {"-", "-", "-"}};
        this.returnedPosition=new int[2];

    }
    public int[] position(int place){
        switch (place)
        {
            case 1:
                return new int[]{0,0};
            case 2:
                return new int[]{0,1};
            case 3:
                return new int[]{0,2};
            case 4:
                return new int[]{1,0};
            case 5:
                return new int[]{1,1};
            case 6:
                return new int[]{1,2};
            case 7:
                return new int[]{2,0};
            case 8:
                return new int[]{2,1};
            case 9:
                return new int[]{2,2};
        }
        return new int[]{0,2};
    }


}
