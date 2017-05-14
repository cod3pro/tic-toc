package com.example.borhan.Game;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    int player = 0;
    int count = 0;
    //we use to check if a position is already tapped or not
    int[] check = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    //here is the sets of winning positions
    int[][] winner = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void move(View view) {
        ImageView v = (ImageView) view;

        System.out.println(v.getTag().toString());

        int tapped = Integer.parseInt(v.getTag().toString());
        TextView text = (TextView) findViewById(R.id.editText);
        Button btn = (Button) findViewById(R.id.button);
        LinearLayout vw = (LinearLayout) findViewById(R.id.message);
        ImageView la = (ImageView) findViewById(R.id.leftarrow);
        ImageView ra = (ImageView) findViewById(R.id.rightarrow);


        if (check[tapped] == 2 && count <= 7) {
            check[tapped] = player;
            v.setTranslationY(-1000f);

            if (player == 0) {
                count++;
                v.setImageResource(R.drawable.circle);
                ra.setVisibility(View.VISIBLE);
                la.setVisibility(View.INVISIBLE);
                player = 1;

            } else {
                count++;
                v.setImageResource(R.drawable.circlered);
                la.setVisibility(View.VISIBLE);
                ra.setVisibility(View.INVISIBLE);


                player = 0;
            }

            v.animate().translationYBy(1000F).rotation(3600).setDuration(300);

            for(int[] winners: winner){
                if(check[winners[0]]==check[winners[1]]
                        &&check[winners[1]]==check[winners[2]]
                        &&check[winners[0]]!= 2){
                        if(check[winners[0]]==0){
                            vw.setVisibility(view.VISIBLE);
                            vw.setBackgroundColor(Color.parseColor("#ADD8E6"));
                            text.setText("Blue Team won!");
                        }else{
                            vw.setVisibility(view.VISIBLE);
                            vw.setBackgroundColor(Color.parseColor("#ff0000"));
                            text.setText("Red Team won!");
                        }
                }
            }
        } else{
            vw.setVisibility(view.VISIBLE);
            vw.setBackgroundColor(Color.parseColor("#ffffff"));
            text.setText("No one win!");
        }
    }

    public void reset(View view){
        LinearLayout ll = (LinearLayout) findViewById(R.id.message);
        ll.setVisibility(view.INVISIBLE);
        count = 0;
        for(int i=0; i < check.length; i++){
            check[i] = 2;
        }

        GridLayout gl = (GridLayout) findViewById(R.id.nineViews);

        for(int j=0; j < gl.getChildCount(); j++){
            ((ImageView) gl.getChildAt(j)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView la = (ImageView) findViewById(R.id.leftarrow);
        ImageView ra = (ImageView) findViewById(R.id.rightarrow);

        if(player ==0) {
            ra.setVisibility(View.VISIBLE);
            la.setVisibility(View.INVISIBLE);
        }else{
            la.setVisibility(View.VISIBLE);
            ra.setVisibility(View.INVISIBLE);
        }
    }


}
