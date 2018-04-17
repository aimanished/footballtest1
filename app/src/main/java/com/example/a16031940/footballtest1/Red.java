package com.example.a16031940.footballtest1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Red extends Activity {
    RadioButton rb;
    RadioGroup rg;
    TextView tv, tv1;
    EditText name, jnum;
    Button yellow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int heightt = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.8), (int) (heightt * 0.6));
        tv = (TextView) findViewById(R.id.tvh);
        tv1 = (TextView) findViewById(R.id.tva);
        name = (EditText) findViewById(R.id.name);
        jnum = (EditText) findViewById(R.id.jnum);
        yellow = (Button) findViewById(R.id.yellow);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        String homeName = getIntent().getStringExtra("Homename");
        String AwayName = getIntent().getStringExtra("Awayname");
        tv.setText(homeName);
        tv1.setText(AwayName);

    }

    public void rb2click(View view) {

        yellow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int radiobuttonid = rg.getCheckedRadioButtonId();
                rb = (RadioButton) findViewById(radiobuttonid);
                String event = "";

                if (rb.getText().toString().equalsIgnoreCase("home")) {
                    if (name.getText().toString().equals("") && jnum.getText().toString().equals("")) {
                        event = "Home player gets a red card";
                    } else if (name.getText().toString().equals("") && jnum.getText().toString().trim().length() > 0) {
                        event = "Home player number " + jnum.getText().toString() + "gets a red!";
                    } else if (name.getText().toString().trim().length() > 0 && jnum.getText().toString().equals("")) {
                        event = "Home player," + name.getText().toString() + " , gets a red!";
                    } else {
                        event = "Home player," + name.getText().toString() + " number" + jnum.getText().toString() + ",gets a red!";
                    }
                } else {
                    if (name.getText().toString().equals("") && jnum.getText().toString().equals("")) {
                        event = "Away player gets a red card";
                    } else if (name.getText().equals("") && jnum.getText().toString().trim().length() > 0) {
                        event = "Away player number " + jnum.getText().toString() + "gets a red!";
                    } else if (name.getText().toString().trim().length() > 0 && jnum.getText().equals("")) {
                        event = "Away player," + name.getText().toString() + " , gets a red!";
                    } else {
                        event = "Away player," + name.getText().toString() + " number" + jnum.getText().toString() + ",gets a red!";
                    }
                }
                Toast.makeText(Red.this, event, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Red.this, Startgame.class);
                intent.putExtra("eventRed" , event);
                startActivity(intent);
            }
        });
    }
}
