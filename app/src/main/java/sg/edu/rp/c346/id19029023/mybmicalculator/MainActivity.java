package sg.edu.rp.c346.id19029023.mybmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
EditText weight, height;
TextView date, bmi;
Button calc, reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        date = findViewById(R.id.tvdate);
        bmi = findViewById(R.id.tvbmi);
        calc = findViewById(R.id.calc);
        reset = findViewById(R.id.reset);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String w = weight.getText().toString();
                Float w1 = Float.parseFloat(w);
                String h = height.getText().toString();
               Float h1 = Float.parseFloat(h);
                float bmicalced = w1/h1*h1;
              String currentTime = Calendar.getInstance().getTime().toString();
                date.setText("Last Calculated Date: "+ currentTime);
                bmi.setText("Last Calculated BMI: "+ bmicalced);

            }
        });
reset.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        date.setText("Last Calculated Date: ");
        bmi.setText("Last Calculated BMI: ");
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor x = pref.edit();
        x.clear();
        x.commit();
    }
});
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor x = pref.edit();
        x.putString("Date", date.getText().toString());
        x.putFloat("bmi", Float.parseFloat(bmi.getText().toString()));

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String strdate = pref.getString("Date","");
        Float flbmi = pref.getFloat("bmi", (float) 0.0);
        date.setText("Las Calculated Date: "+strdate);
        bmi.setText("Last Calculated BMI: "+flbmi);


    }
}
