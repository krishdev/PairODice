package com.example.user.pairodice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Player2 extends ActionBarActivity {
    private FrameLayout die1, die2;
    private Button roll, hold;
    private int score = 0;
    private int scorep1 = 0;
    private int scorep2 = 0;
    private String player;
    public  int sump2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player2);
        TextView tvp1 = (TextView) findViewById(R.id.p1);
        TextView tvp2 = (TextView) findViewById(R.id.p2);


        Intent intent = getIntent();
      //  score = intent.getIntExtra("score", 0);
        scorep1=intent.getIntExtra("scorep1", 0);
        scorep2=intent.getIntExtra("scorep2", 0);
        Toast.makeText(this, "The score is: " + scorep1, Toast.LENGTH_LONG).show();


        roll = (Button) findViewById(R.id.button);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();

            }
        });

        tvp1.setText("P1 "+scorep1);
        tvp2.setText("P2 "+scorep2);


        hold = (Button)findViewById(R.id.hold);
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scorep2 = score+scorep2;
                if(scorep2 == 100 || scorep2>100){
                    AlertDialog alertDialog = new AlertDialog.Builder(Player2.this).create();
                    alertDialog.setTitle("Player2 Won!");
                    alertDialog.setMessage("Yipeeaieahhh!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                else {
                    Intent intent = new Intent(Player2.this, MainActivity.class);
                    intent.putExtra("scorep1", scorep1);
                    intent.putExtra("scorep2", scorep2);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                }
            }
        });

        die1 = (FrameLayout) findViewById(R.id.die1);
        die2 = (FrameLayout) findViewById(R.id.die2);

    }

    //get two random ints between 1 and 6 inclusive
    public void rollDice() {
        int val1 = 1 + (int) (6 * Math.random());
        int val2 = 1 + (int) (6 * Math.random());
        setDie(val1, die1);
        setDie(val2, die2);

    }

    //set the appropriate picture for each die per int
    public void setDie(int value, FrameLayout layout) {
        Drawable pic = null;
        TextView tv = (TextView) findViewById(R.id.round);
        switch (value) {
            case 1:
                pic = getResources().getDrawable(R.drawable.die_face_1);

                //when die1 appears it should go the next player
                scorep2 = score+scorep2;
                if(scorep2 == 100 || scorep2>100){
                    AlertDialog alertDialog = new AlertDialog.Builder(Player2.this).create();
                    alertDialog.setTitle("Player2 Won!");
                    alertDialog.setMessage("Yipeeaieahhh!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                else {
                    Intent intent = new Intent(Player2.this, MainActivity.class);
                    intent.putExtra("scorep1", scorep1);
                    intent.putExtra("scorep2", scorep2);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);

                }
                break;
            case 2:
                pic = getResources().getDrawable(R.drawable.die_face_2);
                score=score+1;
                break;
            case 3:
                pic = getResources().getDrawable(R.drawable.die_face_3);
                score=score+1;
                break;
            case 4:
                pic = getResources().getDrawable(R.drawable.die_face_4);
                score=score+1;
                break;
            case 5:
                pic = getResources().getDrawable(R.drawable.die_face_5);
                score=score+1;
                break;
            case 6:
                pic = getResources().getDrawable(R.drawable.die_face_6);
                score=score+1;
                break;
        }
        layout.setBackground(pic);
        tv.setText("Round:"+score);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
/*
* AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
alertDialog.setTitle("Alert");
alertDialog.setMessage("Alert message to be shown");
alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
    new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    });
alertDialog.show();*/