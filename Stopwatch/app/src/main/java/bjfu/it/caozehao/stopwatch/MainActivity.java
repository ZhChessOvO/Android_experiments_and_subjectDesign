package bjfu.it.caozehao.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.net.IpSecManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running = false;
    private boolean wasRunning = false;

    public void onClickStart(View view){
        running = true;
    }

    public void onClickStop(View view){
        running = false;
    }

    public void onClickReset(View view){
        running = false;
        seconds=0;
    }

    private void runTimer(){
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours=seconds/3600;
                int minutes=(seconds%3600)/60;
                int secs =seconds%60;
                String time = String.format("%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d("life cycle","onStop");
        wasRunning=running;
        running=false;
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d("life cycle","onStart");
        if(wasRunning){
            running = true;
        }
    }

//    @Override
//    public void onResume(){
//        super.onResume();
//        Log.d("life cycle","onResume");
//        if(wasRunning){
//            running = true;
//        }
//    }
//
//    @Override
//    public void onPause(){
//        super.onPause();
//        Log.d("life cycle","onPause");
//        wasRunning=running;
//        running=false;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("life cycle","onCreate");
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null){
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
            wasRunning=savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }
}
