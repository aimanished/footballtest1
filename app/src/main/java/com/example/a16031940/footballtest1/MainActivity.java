package com.example.a16031940.footballtest1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText HomeName;
    EditText AwayName;
    Button Btnstart;
    RadioButton rb;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HomeName = (EditText) findViewById(R.id.editText5);
        AwayName = (EditText) findViewById(R.id.editText7);
        Btnstart = (Button) findViewById(R.id.button);

        rg = (RadioGroup) findViewById(R.id.rggroup);

    }

    public void rbClick(View view){
        Btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (HomeName.getText().toString().trim().equalsIgnoreCase("")) {
                    HomeName.setError("Cannot remain blank!");
                } else if (AwayName.getText().toString().trim().equalsIgnoreCase("")) {
                    AwayName.setError("Cannot remain blank!");
                } else {

                    int selectedId = rg.getCheckedRadioButtonId();

                    // find the radio button by returned id
                    RadioButton radioButton = (RadioButton) findViewById(selectedId);

                    Intent intent = new Intent(getBaseContext(), Startgame.class);
                    intent.putExtra("Homename", HomeName.getText().toString());
                    intent.putExtra("Awayname", AwayName.getText().toString());
                    intent.putExtra("kickoff",radioButton.getText());

                    startActivity(intent);
                }
            }
        });
    }

}



