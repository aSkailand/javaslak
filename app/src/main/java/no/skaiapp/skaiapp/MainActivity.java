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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

class JokeWrapper{
    public String text;
}

class Joke{
    public JokeWrapper joke;
}

class Contents{
    public List<Joke> jokes;

}

class Jod{
    public Contents contents;
}


public class MainActivity extends AppCompatActivity {
    private boolean flippy;
    private TextView textView;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    private TextView dateTimeDisplay;
    private Response.ErrorListener eL;

    private String jokeText;

    private String getJokeText() {
        return this.jokeText;
    }

    public void setJokeText(String newJokeText){
        this.jokeText = newJokeText;
    }
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

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.jokes.one/jod";
        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                Jod jod = gson.fromJson(response.toString(), Jod.class);
                setJokeText(jod.contents.jokes.get(0).joke.text);

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("that did not work");
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                queue.add(jsonObject);
                System.out.println("Wanna hear a joke?");
                if (flippy) {
                    textView.setText(getJokeText());
                } else {
                    textView.setText("Wanna hear a joke?");
                }
                flippy = !flippy;
            }
        });

    }
}