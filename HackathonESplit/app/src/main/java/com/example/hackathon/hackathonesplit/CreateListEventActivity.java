package com.example.hackathon.hackathonesplit;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class CreateListEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list_event);

        /*
        String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry", "WebOS","Ubuntu","Windows7","Max OS X"};
        ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_create_list_event, mobileArray);
        ListView thelistview = (ListView)findViewById(R.id.lvevent);
        thelistview.setAdapter(adapter);*/

        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        final String name = intent.getStringExtra("name");
        final int age = intent.getIntExtra("age", -1);

        // Display user details
        final Button bcreateevent = (Button) findViewById(R.id.bcreateevent);
        bcreateevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(CreateListEventActivity.this, CreateEventActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                CreateListEventActivity.this.startActivity(intent);
            }
        });
        final Button blistevent = (Button) findViewById(R.id.blistevent);
        blistevent.setOnClickListener(new View.OnClickListener() {
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
                                for (int i = 1; i < keys.length (); ++i) {

                                    keysarray[i-1] = keys.getString (i); // Here's your key
                                    valuearray[i-1] = jsonResponse.getString (keysarray[i-1]); // Here's your value

                                }
                                final ListView listView = (ListView) findViewById(R.id.lvevent);
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreateListEventActivity.this,android.R.layout.simple_list_item_1, android.R.id.text1, valuearray);
                                listView.setAdapter(adapter);
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view,
                                                            int position, long id) {

                                        // ListView Clicked item index
                                        Intent intent = new Intent(CreateListEventActivity.this, EventViewActivity.class);
                                        intent.putExtra("username", username);
                                        intent.putExtra("name", name);
                                        intent.putExtra("age", age);
                                        intent.putExtra("eventid",keysarray[position]);
                                        intent.putExtra("eventdesc", valuearray[position]);
                                        CreateListEventActivity.this.startActivity(intent);

                                    }

                                });
                                AlertDialog.Builder builder = new AlertDialog.Builder(CreateListEventActivity.this);
                                builder.setMessage("List refreshed")
                                        .setNegativeButton("Close", null)
                                        .create()
                                        .show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(CreateListEventActivity.this);
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

                CreateListEventRequest createlisteventrequest = new CreateListEventRequest(username, responseListener);
                RequestQueue queue = Volley.newRequestQueue(CreateListEventActivity.this);
                queue.add(createlisteventrequest);
            }
        });

    }
}
