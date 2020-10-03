package com.nayan.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Trace;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    //0 :yellow & 1 :red & 2 :empty

    int[]  gameState    = {2,2,2,2,2,2,2,2,2};

    int[] [] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    int activePlayer = 0;

    boolean gameActive = true;  // variable used to stop someone has won it is initialy true when some one has won turned back to fals as shown bellow//


    public void dropIn(View view){   //inorder need to know which imageview was taped so we need to cal dropin method//

        ImageView counter = (ImageView) view; //view is the image view which was taped on//

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {   /* we add a check up, here not only checks the weather the coin is  empty in the taped location it also checks wether the game is active*/

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);//to translate from top //

            // and when we drop the counter in we need to check to see what is the active player is//
            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);//now we set image for it//

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);

                activePlayer = 0;

            }

            counter.animate().translationYBy(1500).rotation(3500).setDuration(300);// to drop the counter in//


            /* now we will be looping through the all the winning positions and see if all got the same value in the gamestate that is and they are not equal to 2*/

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    //some one has won!

                    gameActive = false;   // when someone has won turned bacl to false so that we can play it again //

                    String winner = "";


                    if (activePlayer == 1) {//we have already changed the active player so its opposit way round//

                        winner = "Yellow";

                    } else {

                        winner = "Red";
                    }

                    Button playAgainButton  = (Button) findViewById(R.id.playAgainButton);

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + "has won");

                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);


                }
            }

        }

    }

    //to play game again and again//

    public void playAgain(View view){

        Button playAgainButton  = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        /*to loop with the iamgeview and to clear the table*/

        for (int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);     //to remove image from the image view//

        }

        for (int i=0; i<gameState.length; i++){  // we cant update the every array so we have to loop it to turn back to normal once again //

         gameState[i] =2;

        }
         activePlayer = 0;

         gameActive = true;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
