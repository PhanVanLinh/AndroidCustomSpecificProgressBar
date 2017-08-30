package com.toong.androidcustomspecificprogressbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    // background

    // add view 1
    // add view 2

    SpecificProgressBar mSpecificProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpecificProgressBar = (SpecificProgressBar) findViewById(R.id.spe);

        TextView aa = new TextView(this);
        aa.setText("bb");

        TextView aa2 = new TextView(this);
        aa2.setText("dd");


        mSpecificProgressBar.addGold(60, 1);
        mSpecificProgressBar.addWalkingPoint(30, 2);
        mSpecificProgressBar.addWalkingPoint(90, 3);

        mSpecificProgressBar.setProgress(50);
    }
}
