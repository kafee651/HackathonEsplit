package com.example.hackathon.hackathonesplit;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class InitialUpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_update);

        Intent intent = getIntent();

        final String username = intent.getStringExtra("username");
        final String name = intent.getStringExtra("name");
        final int age = intent.getIntExtra("age", -1);
        final int count = intent.getIntExtra("count", -1);
        final String amount = intent.getStringExtra("amount");
        final int eventid = intent.getIntExtra("eventid", -1);
        final String dateofcreation = intent.getStringExtra("dateofcreation");
        // Display user details


        final Button bequaldivision = (Button)findViewById(R.id.bequaldivision);
        bequaldivision.setOnClickListener(new View.OnClickListener() {
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
                                AlertDialog.Builder builder = new AlertDialog.Builder(InitialUpdateActivity.this);
                                builder.setMessage("Individual amount to be paid: "+ Double.parseDouble(amount)/count)
                                        .setNegativeButton("Ok", null)
                                        .create()
                                        .show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(InitialUpdateActivity.this);
                                builder.setMessage("Database not up")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                InitialUpdateRequest initialupdateRequest = new InitialUpdateRequest(String.valueOf(eventid), Double.toString(Double.parseDouble(amount)/count), responseListener);
                RequestQueue queue = Volley.newRequestQueue(InitialUpdateActivity.this);
                queue.add(initialupdateRequest);
            }
        });
        final Button bnotifyuser = (Button) findViewById(R.id.bnotifyuser);
        bnotifyuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            Log.d(TAG, "eventid: "+eventid);
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(InitialUpdateActivity.this);
                                builder.setMessage("Notified to all users of event")
                                        .setNegativeButton("Ok", null)
                                        .create()
                                        .show();
                                Intent intent = new Intent(InitialUpdateActivity.this, UserAreaActivity.class);
                                intent.putExtra("username", username);
                                intent.putExtra("name", name);
                                intent.putExtra("age", age);
                                InitialUpdateActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(InitialUpdateActivity.this);
                                builder.setMessage("Database not up")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                CreateEventNotificationRequest createeventnotificationrequest = new CreateEventNotificationRequest(String.valueOf(eventid), responseListener);
                RequestQueue queue = Volley.newRequestQueue(InitialUpdateActivity.this);
                queue.add(createeventnotificationrequest);
            }
        });
    }
}
