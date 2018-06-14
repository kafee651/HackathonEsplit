package com.example.hackathon.hackathonesplit;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EventViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final String username = intent.getStringExtra("username");
        final int age = intent.getIntExtra("age", -1);
        final String eventid = intent.getStringExtra("eventid");
        final String eventdesc = intent.getStringExtra("eventdesc");

        TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvevent);
        tvWelcomeMsg.setText(eventdesc);
        final Button blistuser = (Button) findViewById(R.id.blistuser);
        blistuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                //Add code here for list.
                                JSONArray keys = jsonResponse.names ();
                                final String[] keysarray = new String[keys.length()-1];
                                final String[] valuearray = new String[keys.length()-1];
                                for (int i = 1; i < keys.length(); ++i) {

                                    keysarray[i-1] = keys.getString (i); // Here's your key
                                    valuearray[i-1] = jsonResponse.getString (keysarray[i-1]); // Here's your value

                                }
                                final ListView listView = (ListView) findViewById(R.id.lvuser);
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(EventViewActivity.this,android.R.layout.simple_list_item_1, android.R.id.text1, valuearray);
                                listView.setAdapter(adapter);
                                AlertDialog.Builder builder = new AlertDialog.Builder(EventViewActivity.this);
                                builder.setMessage("List refreshed")
                                        .setNegativeButton("Close", null)
                                        .create()
                                        .show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(EventViewActivity.this);
                                builder.setMessage("List view Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                EventViewRequest eventviewrequest = new EventViewRequest(eventid, responseListener);
                RequestQueue queue = Volley.newRequestQueue(EventViewActivity.this);
                queue.add(eventviewrequest);
            }
        });
    }
}
