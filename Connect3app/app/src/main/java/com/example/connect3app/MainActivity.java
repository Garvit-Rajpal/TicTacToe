package com.example.connect3app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //1:zero,0:cross,2:empty
    int activePlayer=0;
    int[]gameState={2,2,2,2,2,2,2,2,2};
    int[][]winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean visited=true;
     public void dropIn(View view) {
         ImageView counter=(ImageView) view;
         int tappedCounter=Integer.parseInt(counter.getTag().toString());

         if(gameState[tappedCounter]==2&& visited==true) {
             counter.setTranslationY(-1500);
             gameState[tappedCounter]=activePlayer;
             if (activePlayer == 0) {
                 counter.setImageResource(R.drawable.cross);
                 activePlayer = 1;
             } else {
                 counter.setImageResource(R.drawable.zero);
                 activePlayer = 0;
             }
             counter.animate().translationYBy(1500).setDuration(300);

             for (int[] winningPositions : winningPositions) {
                 if (gameState[winningPositions[0]] == gameState[winningPositions[1]] && gameState[winningPositions[0]] == gameState[winningPositions[2]] && gameState[winningPositions[0]] != 2) {
                     String winner = "";
                     if (activePlayer == 1) {
                         winner = "cross has won";
                         visited=false;

                     } else {
                         winner = "zero has won";
                         visited=false;
                     }


                     Button playAgain=(Button)findViewById(R.id.button);
                     TextView textView=(TextView)findViewById(R.id.textView2);
                     textView.setText(winner);
                     playAgain.setVisibility(View.VISIBLE);
                     textView.setVisibility(View.VISIBLE);
                 }
             }

         }
         else{
             int flag=0;
             for(int i=0;i<8;i++){

                 if(gameState[tappedCounter]!=2){
                     flag=1;
                     continue;

                 }
                 else {
                     break;
                 }

             }
             if(flag==1){
                 Button reset=(Button)findViewById(R.id.button2);
                 reset.setVisibility(View.VISIBLE);
             }
         }


     }
     public void playAgain(View view) {
         Log.i("info", "pressed");
         Button playAgain = (Button) findViewById(R.id.button);
         TextView textView = (TextView) findViewById(R.id.textView2);
         playAgain.setVisibility(View.INVISIBLE);
         textView.setVisibility(View.INVISIBLE);
         GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
         for (int i = 0; i < gridLayout.getChildCount(); i++) {
             ImageView child = (ImageView) gridLayout.getChildAt(i);
             child.setImageDrawable(null);
         }

         activePlayer = 0;
         for(int i=0;i<9;i++) {
             gameState[i] = 2;
         }

         visited = true;
     }
    public void reset(View view) {
        Log.i("info", "pressed");
        Button playAgain = (Button) findViewById(R.id.button2);
        TextView textView = (TextView) findViewById(R.id.textView2);
        playAgain.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView) gridLayout.getChildAt(i);
            child.setImageDrawable(null);
        }

        activePlayer = 0;
        for (int i = 0; i < 9; i++) {
            gameState[i] = 2;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}