package no.skaiapp.skaiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import no.skaiapp.skaiapp.models.Contents;
import no.skaiapp.skaiapp.models.JOD;

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

        final TextView jokeOfTheDayTextView = findViewById(R.id.JokeOfTheDayTextView);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String jokeUrl = "https://api.jokes.one/jod";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jokeUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JOD joke = gson.fromJson(response, JOD.class);
                jokeOfTheDayTextView.setText(joke.contents.jokes.get(0).joke.text);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                jokeOfTheDayTextView.setText("Error getting joke");
            }
        });
        requestQueue.add(stringRequest);
    }
}