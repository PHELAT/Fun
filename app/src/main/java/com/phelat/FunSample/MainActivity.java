package com.phelat.FunSample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.phelat.fun.Control.FunControl;
import com.phelat.fun.Layouts.Funny;
import com.phelat.fun.Widget.FunnyButton;

public class MainActivity extends AppCompatActivity {

    private Funny funny;

    private FunnyButton funnyButton;

    private LinearLayout funnyContainer;

    private FunControl funControl;

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        funny = (Funny) findViewById(R.id.mFunny);

        funnyButton = (FunnyButton) findViewById(R.id.mFunnyButton);

        funnyContainer = (LinearLayout) findViewById(R.id.mFunContainer);

        funControl = new FunControl.Builder()
                .setFunnyLayout(funny)
                .setFunnyButton(funnyButton)
                .setContainer(funnyContainer)
                .setAnimationDuration(400)
                .setGravityToExpand(Gravity.CENTER)
                .build();

        funnyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funControl.expand();
            }
        });

        //funnyButton.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Black.ttf"));

    }

    @Override
    public void onBackPressed() {
        if (funControl.isExpanded()){
            funControl.collapse();
        }else{
            super.onBackPressed();
        }
    }

}
