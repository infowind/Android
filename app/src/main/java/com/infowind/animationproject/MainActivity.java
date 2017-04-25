package com.infowind.animationproject;

import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int progressStatus = 0;
    private Handler handler = new Handler();

    ProgressBar bar;

    private Handler progressBarHandler = new Handler();

    int status = 0;

    int progress = 0;
    ProgressBar simpleProgressBar;


    GradientDrawable progressGradientDrawable = new GradientDrawable(
            Orientation.LEFT_RIGHT, new int[]{
            0xff1e90ff, Color.RED, 0xff367ba8});
    ClipDrawable progressClipDrawable = new ClipDrawable(
            progressGradientDrawable, Gravity.LEFT, ClipDrawable.HORIZONTAL);
    Drawable[] progressDrawables = {
            new ColorDrawable(0xffffffff),
            progressClipDrawable, progressClipDrawable};
    LayerDrawable progressLayerDrawable = new LayerDrawable(progressDrawables);

    private ProgressBar firstBar = null;
    private ProgressBar secondBar = null;
    private Button myButton;
    private int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
        final Button btn = (Button) findViewById(R.id.button_progress);
        final TextView tv = (TextView) findViewById(R.id.textView);
        simpleProgressBar = (ProgressBar) findViewById(R.id.pb);


        final ProgressBar progressHorizontal = (ProgressBar) findViewById(R.id.pb);
        setProgress(progressHorizontal.getProgress() * 100);
        setSecondaryProgress(progressHorizontal.getSecondaryProgress() * 100);





/*
        progressLayerDrawable.setId(0, android.R.id.background);
        progressLayerDrawable.setId(1, android.R.id.secondaryProgress);
        progressLayerDrawable.setId(2, android.R.id.progress);
*/


        //  pb.setProgressDrawable(progressLayerDrawable);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the progress status zero on each button click
                setProgressValue(progress);
            }
        });
    }
    private void setProgressValue(final int progress) {

        // set the progress
       simpleProgressBar.setProgress(progress);
       simpleProgressBar.setSecondaryProgress(progress-5);
        simpleProgressBar.incrementProgressBy(progress);




        // thread is used to change the progress value
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setProgressValue(progress + 10);
            }
        });
        thread.start();
    }
}