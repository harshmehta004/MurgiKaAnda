package com.example.welcome.murgikaanda;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
int x;
SeekBar seekBar;
boolean check=false;
TextView textView;
Button button;
ImageView imageView1;
ImageView imageView2;
boolean checkImage=true;
int minutes, seconds;
String second2;
CountDownTimer count;
    public void update(int i){
        minutes=(int)(i/60);
        seconds=i-(minutes*60);
        second2="";
        x=i;
        if(seconds<10)second2="0";
        textView.setText(Integer.toString(minutes)+":"+second2+Integer.toString(seconds));
    }
    public void controlTimer(View view){
        if(check==false){
            button.setText("Stop!");
        seekBar.setEnabled(false);
        check=true;
        count=new CountDownTimer(seekBar.getProgress()*1000,1000){

            @Override
            public void onTick(long l) {
                update((int)l/1000);
            }

            @Override
            public void onFinish() {
                button.setText("RESTART");
                imageView1.setAlpha(0f);
                imageView2.setAlpha(1f);
                MediaPlayer mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.babysound);
                mediaPlayer.start();

            }
        }.start();}
        else {
            seekBar.setEnabled(true);
            textView.setText(Integer.toString(minutes)+":"+second2+Integer.toString(seconds));
            button.setText("GO");
            check=false;
            count.cancel();
            imageView2.setAlpha(0f);
            imageView1.setAlpha(1f);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        textView=(TextView)findViewById(R.id.textView);
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        seekBar.setProgress(30);
        seekBar.setMax(300);
        imageView1=(ImageView)findViewById(R.id.egg);
        imageView2=(ImageView)findViewById(R.id.chick);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                update(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


}
