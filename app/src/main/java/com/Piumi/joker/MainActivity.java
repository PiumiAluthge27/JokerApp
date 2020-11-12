package com.Piumi.joker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue;
    String url ="https://official-joke-api.appspot.com/random_joke";
    String tenJokeUrl = "https://official-joke-api.appspot.com/jokes/";
    ListView joList;
    ProgressBar progressBar;
    List<JokeObject> tenJokes;
    // Array of choices
    String types[] = {"Random","general","knock-knock","programming"};

    // Selection of the spinner
    Spinner spinnerType;

    // Application of the Array to the Spinner
    ArrayAdapter<String> spinnerArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue=Volley.newRequestQueue(this);

        joList = findViewById(R.id.jokeslist);
        tenJokes = new ArrayList<>();
        progressBar=findViewById(R.id.progressBar);
        spinnerType = (Spinner) findViewById(R.id.typeSelection);
        spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, types);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerType.setAdapter(spinnerArrayAdapter);

        final Context thisContex = this;

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedType = (String)parentView.getItemAtPosition(position);
                getJokesByType(thisContex,selectedType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        //getJokes(this);


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        MainActivity.this.finish();
        startActivity(intent);
    }

    public void getJokes(final Context context) {
        progressBar.setVisibility(View.VISIBLE);
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET, tenJokeUrl+"ten", null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JokeObject joke;
                            tenJokes = new ArrayList<>();
                            for (int i=0; i < response.length(); i++) {
                                joke = new JokeObject();
                                JSONObject obj = response.getJSONObject(i);
                                joke.Id = obj.getInt("id");
                                joke.Type = obj.getString("type");
                                joke.Setup = obj.getString("setup");
                                joke.Punchline = obj.getString("punchline");

                                tenJokes.add(joke);
                            }
                            
                            JokeListAdapter adapter = new JokeListAdapter(context,R.layout.jokelistlayout,tenJokes);
                            joList.setAdapter(adapter);
                            progressBar.setVisibility(View.GONE);
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String err=error.toString();
                    }
                });

        queue.add(jsonArrayRequest);


    }

    public void getJokesByType(final Context context,String type) {
        progressBar.setVisibility(View.VISIBLE);
        String jokeUrl = tenJokeUrl+type+"/ten";
        if(type.equals("Random")){
            jokeUrl = tenJokeUrl+"ten";
        }
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET, jokeUrl, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JokeObject joke;
                            tenJokes = new ArrayList<>();
                            for (int i=0; i < response.length(); i++) {
                                joke = new JokeObject();
                                JSONObject obj = response.getJSONObject(i);
                                joke.Id = obj.getInt("id");
                                joke.Type = obj.getString("type");
                                joke.Setup = obj.getString("setup");
                                joke.Punchline = obj.getString("punchline");

                                tenJokes.add(joke);
                            }

                            JokeListAdapter adapter = new JokeListAdapter(context,R.layout.jokelistlayout,tenJokes);
                            joList.setAdapter(adapter);
                            progressBar.setVisibility(View.GONE);
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String err=error.toString();
                    }
                });

        queue.add(jsonArrayRequest);


    }

}
