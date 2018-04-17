package com.example.a16031940.footballtest1;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Startgame extends AppCompatActivity {
    private RecyclerView recyclerView;
    Vibrator vibrator;
    Button start, goals, yellow, red;
    Handler customhandler = new Handler();
    TextView tv, tv1 , tv2 , sa , sh;
    TextView time;
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView lv;
    long startTimer = 0L, timeInMilliseconds = 0L, timeSwapBuff = 0L, updateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startgame);
        tv = (TextView) findViewById(R.id.tv);
        start = (Button) findViewById(R.id.button2);
        time = (TextView) findViewById(R.id.time);
        tv1 = (TextView) findViewById(R.id.textView5);
        goals = (Button) findViewById(R.id.goals);
        red = (Button) findViewById(R.id.red);
        yellow = (Button) findViewById(R.id.yellow);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        tv2 = (TextView) findViewById(R.id.textView7);
        sa = (TextView) findViewById(R.id.ascore);
        sh = (TextView) findViewById(R.id.hscore);
        lv = (ListView) findViewById(R.id.lv);


        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                arrayList);
        lv.setAdapter(adapter);



        checkKickOff();
        timeVibrates();
        yellowCard();
        GoalsScored();
        RedCard();
        checkIntent();
        scoreline();


        final Runnable updateTimerThread = new Runnable() {
            @Override
            public void run() {
                timeInMilliseconds = SystemClock.uptimeMillis() - startTimer;
                updateTime = timeSwapBuff + timeInMilliseconds;
                int secs = (int) (updateTime / 100)/2;
                int mins = secs / 60;
                int milliseconds = (int) (updateTime % 100);
                time.setText("" + mins + ":" + String.format("%2d", secs) + ":"
                        + String.format("%3d", milliseconds));

                customhandler.postDelayed(this, 0);
            }
        };


        String homeName = getIntent().getStringExtra("Homename");
        String AwayName = getIntent().getStringExtra("Awayname");

        tv.setText(homeName);
        tv1.setText(AwayName);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer = SystemClock.uptimeMillis();
                customhandler.postDelayed(updateTimerThread, 0);
                vibrator.vibrate(50);
            }
        });
    }

    public void checkKickOff() {

        if (getIntent().getStringExtra("kickoff") != null && getIntent().getStringExtra("kickoff").equalsIgnoreCase("home")) {
            arrayList.add("0m Lets start the game with a kick off from the home side");
            tv2.setText(getIntent().getStringExtra("kickoff"));
        } else if(getIntent().getStringExtra("kickoff") != null && getIntent().getStringExtra("kickoff").equalsIgnoreCase("away")) {
            arrayList.add("0m Lets start the game with a kick off from the away side");
            tv2.setText(getIntent().getStringExtra("kickoff"));
        }else{
            Toast.makeText(getBaseContext(),"added!" , Toast.LENGTH_LONG).show();
        }
        adapter.notifyDataSetChanged();
    }

    public void StopTime() {

    }

    public void GetTime() {
        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View addView = inflater.inflate(R.layout.activity_startgame, null);
        Toast.makeText(Startgame.this, "Time is" + time.getText(), Toast.LENGTH_LONG).show();

    }

    public void disableFeatures() {
        if (time.getText().equals("0:00:000")) {
            yellow.setEnabled(false);
            red.setEnabled(false);
            goals.setEnabled(false);
        } else {
            yellow.setEnabled(true);
            red.setEnabled(true);
            goals.setEnabled(true);
        }
    }

    public void timeVibrates() {
        if (tv2.getText().toString().equalsIgnoreCase("home")) {
            if (time.getText().equals("45:00:000")) {
                arrayList.add("The second half starts with the away team");
                vibrator.vibrate(50);
            }
        } else {
            if (time.getText().equals("45:00:000")) {
                arrayList.add("The second half starts with the away team");
                vibrator.vibrate(50);
            }
        }

    }

    public void yellowCard(){
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Yellow.class);
                intent.putExtra("Homename", tv.getText().toString());
                intent.putExtra("Awayname", tv1.getText().toString());
                startActivity(intent);
            }
        });
    }

    public void checkIntent(){

        if (this.getIntent().getExtras() != null && this.getIntent().getExtras().containsKey("eventYellow")) {
            String eventYellow = getIntent().getStringExtra("eventYellow");
            arrayList.add(eventYellow);
        }else if(this.getIntent().getExtras() != null && this.getIntent().getExtras().containsKey("eventRed")){
            String eventRed = getIntent().getStringExtra("eventRed");
            arrayList.add(eventRed);
        }else if(this.getIntent().getExtras() != null && this.getIntent().getExtras().containsKey("eventGoals")){
            String eventGoals = getIntent().getStringExtra("eventGoals");
            arrayList.add(eventGoals);
        }

        else{
            Toast.makeText(Startgame.this,"Not inserted" ,Toast.LENGTH_LONG).show();
        }
        adapter.notifyDataSetChanged();


    }

    public void RedCard(){
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Red.class);
                intent.putExtra("Homename", tv.getText().toString());
                intent.putExtra("Awayname", tv1.getText().toString());
                startActivity(intent);
            }
        });
    }


    public void GoalsScored(){
        goals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Golazo.class);
                intent.putExtra("Homename", tv.getText().toString());
                intent.putExtra("Awayname", tv1.getText().toString());
                startActivity(intent);
            }
        });
    }

    public void scoreline() {

        if (this.getIntent().getExtras() != null && this.getIntent().getExtras().containsKey("golazo")) {
            int hscoreline = 0;
            int ascoreline = 0;
            String whoScored = getIntent().getStringExtra("golazo");
            if (whoScored.equalsIgnoreCase("Home")) {
                hscoreline = hscoreline + 1;
            } else {
                ascoreline = ascoreline + 1;
            }

            sa.setText(String.valueOf(ascoreline));
            sh.setText(String.valueOf(hscoreline));

        }
    }


}





