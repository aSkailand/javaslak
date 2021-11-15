package no.skaiapp.skaiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private boolean flippy;
    private TextView textView;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    private TextView dateTimeDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        flippy = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateTimeDisplay = (TextView)findViewById(R.id.dateTimeDisplay);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy - hh:mm");
        date = dateFormat.format(calendar.getTime());
        dateTimeDisplay.setText(date);

        Button button = findViewById(R.id.mainButton);
        textView = (TextView)findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Hello, world!");
                if (flippy) {
                    textView.setText("Aslak e best!");
                } else {
                    textView.setText("Ingen protest!");
                }
                flippy = !flippy;
            }
        });

    }
}