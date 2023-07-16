package com.swamy.tictactoe;




import com.swamy.tictactoe.databinding.ActivityMainBinding;
import com.swamy.tictactoe.databinding.RoundWinDialogBoxBinding;
import com.swamy.tictactoe.databinding.WinnerDialogBoxBinding;



import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;




public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;
    LayoutInflater layoutInflater;
    View dialogView;
    AlertDialog.Builder builder;
    String crossPlayerName;
    String OPlayerName;
    AlertDialog dialog;
    String whosTurn;
    String win;
    String loss;
    String crossX;
    String Osymbol;
    int[] returnedPosition;
    int previousTouchedBlock;
    int rounds;
    String s;
    Matrix matrix;
    String TAG;
    int crossWinCount;
    int oWinCount;
    int blockCount;
    int[][] returnArrayFromMatrixClass;

    View v;

    //round winner cross
    View crossRoundWinnerView;
    AlertDialog.Builder crossRoundWinnerDialogBuilder;
    AlertDialog crossRoundWinnerDialog;

    // o round winner
    View oRoundWinnerView;
    AlertDialog.Builder oRoundWinnerDialogBuilder;
    AlertDialog oRoundWinnerDialog;

    // draw round winner
    View drawDialogView;
    AlertDialog.Builder drawDialogBuilder;
    AlertDialog drawAlertDialog;

    // game winner dialog
    View gameWinnerDialogView;
    AlertDialog.Builder gameWinnerDialogBuilder;
    TextView winnerText;
    AlertDialog gameWinnerDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        v = findViewById(R.id.view2);
        whosTurn="X";
        win="Win";
        loss="Loss";
        rounds=0;
        TAG="MainActivity";
        OPlayerName="O";
        crossPlayerName="cross";
        matrix=new Matrix();
        previousTouchedBlock=0;
        binding.crossicon.setBackground(getDrawable(R.drawable.background_for_icon_enabled));

        returnArrayFromMatrixClass= new int[4][2];

        layoutInflater=LayoutInflater.from(this);
        dialogView=layoutInflater.inflate(R.layout.ask_names_dailog,null);


        builder= new AlertDialog.Builder(this);
        builder.setView(dialogView)
                .setPositiveButton("Enter", (dialogInterface, i) -> {
                    EditText cross_Player_name=(EditText) dialogView.findViewById(R.id.cross_player_name);
                    EditText o_Player_name=(EditText) dialogView.findViewById(R.id.O_player_name);
                    crossPlayerName=cross_Player_name.getText().toString();
                    OPlayerName=o_Player_name.getText().toString();
                    Toast.makeText(this,crossPlayerName+" "+OPlayerName, Toast.LENGTH_LONG).show();

                })
                .setNegativeButton("close",((dialogInterface, i) -> {
                    dialogInterface.dismiss();
                }));
        dialog= builder.create();




        binding.block1.setOnClickListener(this);
        binding.block2.setOnClickListener(this);
        binding.block3.setOnClickListener(this);
        binding.block4.setOnClickListener(this);
        binding.block5.setOnClickListener(this);
        binding.block6.setOnClickListener(this);
        binding.block7.setOnClickListener(this);
        binding.block8.setOnClickListener(this);
        binding.block9.setOnClickListener(this);
        createDialogs();
        // end of oncreate





    }

    private void createDialogs() {



        // game winner dialog box

        gameWinnerDialogView=layoutInflater.inflate(R.layout.winner_dialog_box,null);
        gameWinnerDialogBuilder=new AlertDialog.Builder(this);
        gameWinnerDialogBuilder.setView(gameWinnerDialogView);
        winnerText= gameWinnerDialogView.findViewById(R.id.winnerText);
        gameWinnerDialogView.findViewById(R.id.palyAgain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameWinnerDialog.dismiss();
            }
        });

        gameWinnerDialog=gameWinnerDialogBuilder.create();


        // cross round winner alert dialog box builder
        crossRoundWinnerView =layoutInflater.inflate(R.layout.round_win_dialog_box,null);
        crossRoundWinnerDialogBuilder =new AlertDialog.Builder(this);
        crossRoundWinnerDialogBuilder.setView(crossRoundWinnerView);
        crossRoundWinnerDialogBuilder.setPositiveButton("next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                crossRoundWinnerDialog.dismiss();
            }
        });
        crossRoundWinnerDialog=crossRoundWinnerDialogBuilder.create();




        // o round winner dialog box builder
        oRoundWinnerView=layoutInflater.inflate(R.layout.o_round_win_dialog_box,null);
        oRoundWinnerDialogBuilder=new AlertDialog.Builder(this);
        oRoundWinnerDialogBuilder.setView(oRoundWinnerView);
        oRoundWinnerDialogBuilder.setPositiveButton("next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                oRoundWinnerDialog.dismiss();
            }
        });
        oRoundWinnerDialog=oRoundWinnerDialogBuilder.create();


        // draw winner dialog box builder
        drawDialogView=layoutInflater.inflate(R.layout.draw_dialog,null);
        drawDialogBuilder=new AlertDialog.Builder(this);
        drawDialogBuilder.setView(drawDialogView);
        drawDialogBuilder.setPositiveButton("next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                drawAlertDialog.dismiss();
            }
        });
        drawAlertDialog=drawDialogBuilder.create();
        dialog.show();
        resetRound();

    }

    public void resetRound()
    {
        matrix.reset();
        blockCount=0;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(previousTouchedBlock!=0)
                {
                    removePreviousBackgroundForBlock();
                    previousTouchedBlock=0;
                }


                binding.block1.setBackground(getDrawable(R.drawable.background_for_icons));
                binding.block2.setBackground(getDrawable(R.drawable.background_for_icons));
                binding.block3.setBackground(getDrawable(R.drawable.background_for_icons));
                binding.block4.setBackground(getDrawable(R.drawable.background_for_icons));
                binding.block5.setBackground(getDrawable(R.drawable.background_for_icons));
                binding.block6.setBackground(getDrawable(R.drawable.background_for_icons));
                binding.block7.setBackground(getDrawable(R.drawable.background_for_icons));
                binding.block8.setBackground(getDrawable(R.drawable.background_for_icons));
                binding.block9.setBackground(getDrawable(R.drawable.background_for_icons));
                binding.block1.setText("-");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block1.setTextColor(getColor(R.color.white));
                }
                binding.block2.setText("-");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block2.setTextColor(getColor(R.color.white));
                }
                binding.block3.setText("-");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block3.setTextColor(getColor(R.color.white));
                }
                binding.block4.setText("-");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block4.setTextColor(getColor(R.color.white));
                }
                binding.block5.setText("-");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block5.setTextColor(getColor(R.color.white));
                }
                binding.block6.setText("-");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block6.setTextColor(getColor(R.color.white));
                }
                binding.block7.setText("-");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block7.setTextColor(getColor(R.color.white));
                }
                binding.block8.setText("-");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block8.setTextColor(getColor(R.color.white));
                }
                binding.block9.setText("-");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block9.setTextColor(getColor(R.color.white));
                }
                Log.d(TAG, "resetRound: resetting round");
            }
        },1000);
    }
    @Override
    public void onClick(View view)
    {
        int positionTouched=Integer.parseInt(view.getTag().toString());
        blockCount=blockCount+1;
        if(isFilledWithText(positionTouched))
        {
            return;
        }
        updateBlockUi(positionTouched);
        try
        {
            returnArrayFromMatrixClass=matrix.updateInMatrix(positionTouched,whosTurn);

            if(rounds<=4)
            {
                notLastRound(returnArrayFromMatrixClass);
            } else if (rounds>=5) {
                lastRound(returnArrayFromMatrixClass);
            }
        }

        catch (Exception e)
        {
            Log.e("game Abord line 188", "onClick: ",e);
        }

        if(whosTurn=="X")
        {
            binding.crossicon.setBackground(getDrawable(R.drawable.background_for_icons));
            binding.oicon.setBackground(getDrawable(R.drawable.background_for_icon_enabled));

            whosTurn="O";

        }
        else{
            whosTurn="X";
            binding.oicon.setBackground(getDrawable(R.drawable.background_for_icons));
            binding.crossicon.setBackground(getDrawable(R.drawable.background_for_icon_enabled));
        }



        // Toast.makeText(this,matrix.getMatrixValues(),Toast.LENGTH_LONG).show();

    }

    private void lastRound(int[][] returnArrayFromMatrixClass) {
        Log.d(TAG, "onClick: in round 5 ");
        if(returnArrayFromMatrixClass[0][0] ==1 && blockCount!=9)
        {
            //  Toast.makeText(this,"this win aindhi ra babu",Toast.LENGTH_LONG).show();
            Log.d(TAG, "onClick: in round 5 1 ");
            binding.count.setText(Integer.toString(rounds));
            if(whosTurn=="X")
            {
                // blockCount=0;
                Log.d(TAG, "onClick: in round 5 2");
                crossWinCount=crossWinCount+1;
                binding.crossCount.setText(Integer.toString(crossWinCount));

                // crossRoundWinnerDialog.show();
            }
            else
            {
                Log.d(TAG, "onClick: in round 5 3");
                binding.oCount.setText(Integer.toString(++oWinCount));

                // oRoundWinnerDialog.show();

            }
            lastRoundDialog();

            Log.d(TAG, "onClick: reset round fun");
        } else if (returnArrayFromMatrixClass[0][0] ==1 && blockCount==9)
        {

            Log.d(TAG, "onClick: in round 5 1 ");
            binding.count.setText(Integer.toString(rounds));
            if(whosTurn=="X")
            {
                // blockCount=0;
                Log.d(TAG, "onClick: in round 5 2");
                crossWinCount=crossWinCount+1;
                binding.crossCount.setText(Integer.toString(crossWinCount));

                // crossRoundWinnerDialog.show();
            }
            else
            {
                Log.d(TAG, "onClick: in round 5 3");
                binding.oCount.setText(Integer.toString(++oWinCount));

                // oRoundWinnerDialog.show();

            }
            lastRoundDialog();
        } else if (returnArrayFromMatrixClass[0][0] !=1 && blockCount==9) {
            lastRoundDialog();

        }

        resetgame();
        //end last round fun
    }

    private void lastRoundDialog() {
        if(oWinCount>=crossWinCount)
        {
            if(oWinCount==crossWinCount)
            {
                winnerText.setText(OPlayerName);
                gameWinnerDialog.show();
            }
            else {
                drawAlertDialog.show();
            }
        }
        else
        {
            winnerText.setText(crossPlayerName);
            gameWinnerDialog.show();
        }


    }

    private void notLastRound(int[][] returnArrayFromMatrixClass) {
        if(returnArrayFromMatrixClass[0][0] ==1 && blockCount<9)
        {


            rounds=rounds+1;
            Log.d(TAG, "notLastRound: fun" +rounds);
            binding.count.setText(Integer.toString(rounds));
            if(whosTurn=="X")
            {
                // Toast.makeText(this, whosTurn+" is won the game", Toast.LENGTH_SHORT).show();
                crossWinCount=crossWinCount+1;
                binding.crossCount.setText(Integer.toString(crossWinCount));

                crossRoundWinnerDialog.show();

            }
            else{

                // Toast.makeText(this, whosTurn+" is won the game", Toast.LENGTH_SHORT).show();
                binding.oCount.setText(Integer.toString(++oWinCount));
                // setRoundWinnerOAlertDialogShowFun();
                // winnerDialogViewFun("o");
                //roundWinnerAlertDialog./show();
                oRoundWinnerDialog.show();
            }

            resetRound();
            Log.d(TAG, "onClick: reset round fun");
        } else if (returnArrayFromMatrixClass[0][0]==1 && blockCount==9)
        {

            rounds=rounds+1;
            binding.count.setText(Integer.toString(rounds));

            if(whosTurn=="X")
            {
                // Toast.makeText(this, whosTurn+" is won the game", Toast.LENGTH_SHORT).show();
                crossWinCount=crossWinCount+1;
                binding.crossCount.setText(Integer.toString(crossWinCount));
                //setRoundWinnerCrossAlertDialogShowFun();
                // roundWinnerAlertDialog.show();
                crossRoundWinnerDialog.show();

            }
            else
            {

                // Toast.makeText(this, whosTurn+" is won the game", Toast.LENGTH_SHORT).show();
                binding.oCount.setText(Integer.toString(++oWinCount));
                // setRoundWinnerOAlertDialogShowFun();
                // winnerDialogViewFun("o");
                //roundWinnerAlertDialog./show();
                oRoundWinnerDialog.show();

            }


            resetRound();
        } else if (returnArrayFromMatrixClass[0][0]!=1 && blockCount==9) {
            rounds=rounds+1;
            binding.count.setText(Integer.toString(rounds));
            resetRound();
            drawAlertDialog.show();

        }


    }


    private void makeBlockWinnerBlocks( String statusofWinnerBlocks) {
        switch (statusofWinnerBlocks)
        {
            case "firstVertical":
                binding.block1.setBackground(getDrawable(R.drawable.background_for_icon_win));
                binding.block4.setBackground(getDrawable(R.drawable.background_for_icon_win));
                binding.block7.setBackground(getDrawable(R.drawable.background_for_icon_win));
                break;
            case "secondVertical":

                /***
                 v.setScaleX(0.0f);
                 v.setScaleY(0.0f);
                 v.setVisibility(View.VISIBLE);
                 v.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(new OvershootInterpolator()).setDuration(500).start();

                 **/

                binding.block2.setBackground(getDrawable(R.drawable.background_for_icon_win));
                binding.block5.setBackground(getDrawable(R.drawable.background_for_icon_win));
                binding.block8.setBackground(getDrawable(R.drawable.background_for_icon_win));
                break;
            case "thirdVertical":
                binding.block3.setBackground(getDrawable(R.drawable.background_for_icon_win));
                binding.block6.setBackground(getDrawable(R.drawable.background_for_icon_win));
                binding.block9.setBackground(getDrawable(R.drawable.background_for_icon_win));
                break;
            case "firstHorizontal":
                binding.block1.setBackground(getDrawable(R.drawable.background_for_icon_win));
                binding.block2.setBackground(getDrawable(R.drawable.background_for_icon_win));
                binding.block3.setBackground(getDrawable(R.drawable.background_for_icon_win));
                break;
            case "secondHorizontal":
                binding.block4.setBackground(getDrawable(R.drawable.background_for_icon_win));
                binding.block5.setBackground(getDrawable(R.drawable.background_for_icon_win));
                binding.block6.setBackground(getDrawable(R.drawable.background_for_icon_win));
                break;
            case "thirdHorizontal":
                binding.block7.setBackground(getDrawable(R.drawable.background_for_icon_win));
                binding.block8.setBackground(getDrawable(R.drawable.background_for_icon_win));
                binding.block9.setBackground(getDrawable(R.drawable.background_for_icon_win));
                break;
            case "leftCross":
                binding.block1.setBackground(getDrawable(R.drawable.background_for_icon_win));
                binding.block5.setBackground(getDrawable(R.drawable.background_for_icon_win));
                binding.block9.setBackground(getDrawable(R.drawable.background_for_icon_win));
                break;
            case "rightCross":
                binding.block3.setBackground(getDrawable(R.drawable.background_for_icon_win));
                binding.block5.setBackground(getDrawable(R.drawable.background_for_icon_win));
                binding.block7.setBackground(getDrawable(R.drawable.background_for_icon_win));
                break;
            default:
                Log.d(TAG, "makeBlockWinnerBlocks: in default state");
                break;

        }

    }

    private void resetgame() {
        matrix.reset();
        resetRound();
        crossWinCount=0;
        oWinCount=0;
        rounds=0;
        binding.crossCount.setText(Integer.toString(crossWinCount));
        binding.oCount.setText(Integer.toString(oWinCount));
        binding.count.setText(Integer.toString(rounds));

    }

    private boolean isFilledWithText(int touched) {
        switch (touched)
        {

            case 1:
                s=binding.block1.getText().toString();
                if(s.equals("X") || s.equals("O"))
                {
                    return true;
                }
                else{
                    return false;
                }

            case 2:
                s=binding.block2.getText().toString();
                if(s.equals("X") || s.equals("O"))
                {
                    return true;
                }
                else{
                    return false;
                }

            case 3:
                s=binding.block3.getText().toString();
                if(s.equals("X") || s.equals("O"))
                {
                    return true;
                }
                else{
                    return false;
                }

            case 4:
                s=binding.block4.getText().toString();
                if(s.equals("X") || s.equals("O"))
                {
                    return true;
                }
                else{
                    return false;
                }

            case 5:
                s=binding.block5.getText().toString();
                if(s.equals("X") || s.equals("O"))
                {
                    return true;
                }
                else{
                    return false;
                }


            case 6:
                s=binding.block6.getText().toString();
                if(s.equals("X") || s.equals("O"))
                {
                    return true;
                }
                else{
                    return false;
                }



            case 7:
                s=binding.block7.getText().toString();
                if(s.equals("X") || s.equals("O"))
                {
                    return true;
                }
                else{
                    return false;
                }


            case 8:
                s=binding.block8.getText().toString();
                if(s.equals("X") || s.equals("O"))
                {
                    return true;
                }
                else{
                    return false;
                }


            case 9:
                s=binding.block9.getText().toString();
                if(s.equals("X") || s.equals("O"))
                {
                    return true;
                }
                else{
                    return false;
                }


            default:
                return true;

        }
    }

    private void updateBlockUi(int positionTouched) {
        Log.d(TAG, "updateBlockUi: entered");
        int block=positionTouched;
        removePreviousBackgroundForBlock();
        setBackgroundForBlock(block);
        //block one
        if(whosTurn=="X")
        {
            Log.d(TAG, "updateBlockUi: X");
            setCrossContentInBlock(block);

        }
        else{
            setOContentInBlock(block);
            Log.d(TAG, "updateBlockUi: y");
        }
        previousTouchedBlock=block;
    }
    private void setOContentInBlock(int pos) {
        switch (pos){
            case 1:
                binding.block1.setText(getResources().getString(R.string.O));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block1.setTextColor(getColor(R.color.OGreenColor));
                }
                binding.block1.setTextSize(getResources().getDimension(R.dimen.sp72));

                break;
            case 2:
                binding.block2.setText(getResources().getString(R.string.O));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block2.setTextColor(getColor(R.color.OGreenColor));
                }
                binding.block2.setTextSize(getResources().getDimension(R.dimen.sp72));
                break;
            case 3:
                binding.block3.setText(getResources().getString(R.string.O));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block3.setTextColor(getColor(R.color.OGreenColor));
                }
                binding.block3.setTextSize(getResources().getDimension(R.dimen.sp72));
                break;
            case 4:
                binding.block4.setText(getResources().getString(R.string.O));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block4.setTextColor(getColor(R.color.OGreenColor));
                }
                binding.block4.setTextSize(getResources().getDimension(R.dimen.sp72));
                break;
            case 5:
                binding.block5.setText(getResources().getString(R.string.O));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block5.setTextColor(getColor(R.color.OGreenColor));
                }
                binding.block5.setTextSize(getResources().getDimension(R.dimen.sp72));
                break;
            case 6:
                binding.block6.setText(getResources().getString(R.string.O));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block6.setTextColor(getColor(R.color.OGreenColor));
                }
                binding.block6.setTextSize(getResources().getDimension(R.dimen.sp72));
                break;
            case 7:
                binding.block7.setText(getResources().getString(R.string.O));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block7.setTextColor(getColor(R.color.OGreenColor));
                }
                binding.block7.setTextSize(getResources().getDimension(R.dimen.sp72));
                break;
            case 8:
                binding.block8.setText(getResources().getString(R.string.O));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block8.setTextColor(getColor(R.color.OGreenColor));
                }
                binding.block8.setTextSize(getResources().getDimension(R.dimen.sp72));
                break;
            case 9:
                binding.block9.setText(getResources().getString(R.string.O));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block9.setTextColor(getColor(R.color.OGreenColor));
                }
                binding.block9.setTextSize(getResources().getDimension(R.dimen.sp72));
                break;
            default:
                Log.d("MainACtivity", "removePreviousBackgroundForBlock: deafult ");
                break;
        }
    }
    private void setCrossContentInBlock(int number) {
        Log.d(TAG, "setCrossContentInBlock: "+number);
        switch (number){
            case 1:
                binding.block1.setText(getResources().getString(R.string.cross));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block1.setTextColor(getColor(R.color.CrossRedColor));
                }
                break;
            case 2:
                binding.block2.setText(getResources().getString(R.string.cross));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block2.setTextColor(getColor(R.color.CrossRedColor));
                }
                break;
            case 3:
                binding.block3.setText(getResources().getString(R.string.cross));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block3.setTextColor(getColor(R.color.CrossRedColor));
                }
                break;
            case 4:
                binding.block4.setText(getResources().getString(R.string.cross));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block4.setTextColor(getColor(R.color.CrossRedColor));
                }
                break;
            case 5:
                binding.block5.setText(getResources().getString(R.string.cross));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block5.setTextColor(getColor(R.color.CrossRedColor));
                }
                break;
            case 6:
                binding.block6.setText(getResources().getString(R.string.cross));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block6.setTextColor(getColor(R.color.CrossRedColor));
                }
                break;
            case 7:
                binding.block7.setText(getResources().getString(R.string.cross));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block7.setTextColor(getColor(R.color.CrossRedColor));
                }
                break;
            case 8:
                binding.block8.setText(getResources().getString(R.string.cross));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block8.setTextColor(getColor(R.color.CrossRedColor));
                }
                break;
            case 9:
                binding.block9.setText(getResources().getString(R.string.cross));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.block9.setTextColor(getColor(R.color.CrossRedColor));
                }
                break;
            default:
                Log.d("MainACtivity", "removePreviousBackgroundForBlock: deafult ");
                break;
        }
    }


    private void removePreviousBackgroundForBlock() {
        Log.d("background setting", "setBackgrpundforBlock: set background"+previousTouchedBlock);

        switch (previousTouchedBlock){
            case 1:
                binding.block1.setBackground(getDrawable(R.drawable.background_for_icons));
                previousTouchedBlock=1;
                break;
            case 2:
                binding.block2.setBackground(getDrawable(R.drawable.background_for_icons));
                previousTouchedBlock=2;
                break;
            case 3:
                binding.block3.setBackground(getDrawable(R.drawable.background_for_icons));
                previousTouchedBlock=3;
                break;
            case 4:
                binding.block4.setBackground(getDrawable(R.drawable.background_for_icons));
                previousTouchedBlock=4;
                break;
            case 5:
                binding.block5.setBackground(getDrawable(R.drawable.background_for_icons));
                previousTouchedBlock=5;
                break;
            case 6:
                binding.block6.setBackground(getDrawable(R.drawable.background_for_icons));
                previousTouchedBlock=6;
                break;
            case 7:
                binding.block7.setBackground(getDrawable(R.drawable.background_for_icons));
                previousTouchedBlock=7;
                break;
            case 8:
                binding.block8.setBackground(getDrawable(R.drawable.background_for_icons));
                previousTouchedBlock=8;
                break;
            case 9:
                binding.block9.setBackground(getDrawable(R.drawable.background_for_icons));
                previousTouchedBlock=9;
                break;
            default:
                Log.d("MainACtivity", "removePreviousBackgroundForBlock: deafult ");
                break;
        }
    }

    private void setBackgroundForBlock(int block) {

        switch (block){
            case 1:
                binding.block1.setBackground(getDrawable(R.drawable.background_for_icon_enabled));
                break;
            case 2:
                binding.block2.setBackground(getDrawable(R.drawable.background_for_icon_enabled));
                break;
            case 3:
                binding.block3.setBackground(getDrawable(R.drawable.background_for_icon_enabled));
                break;
            case 4:
                binding.block4.setBackground(getDrawable(R.drawable.background_for_icon_enabled));
                break;
            case 5:
                binding.block5.setBackground(getDrawable(R.drawable.background_for_icon_enabled));
                break;
            case 6:
                binding.block6.setBackground(getDrawable(R.drawable.background_for_icon_enabled));
                break;
            case 7:
                binding.block7.setBackground(getDrawable(R.drawable.background_for_icon_enabled));
                break;
            case 8:
                binding.block8.setBackground(getDrawable(R.drawable.background_for_icon_enabled));
                break;
            case 9:
                binding.block9.setBackground(getDrawable(R.drawable.background_for_icon_enabled));
                break;
            default:
                Log.d("MainACtivity", "removePreviousBackgroundForBlock: deafult ");
                break;
        }
    }


}

