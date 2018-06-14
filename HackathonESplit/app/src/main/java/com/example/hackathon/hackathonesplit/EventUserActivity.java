package com.example.hackathon.hackathonesplit;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class EventUserActivity extends AppCompatActivity {

    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_user);

        Intent intent = getIntent();

        final String eventname = intent.getStringExtra("eventname");
        final String username = intent.getStringExtra("username");
        final int eventid = intent.getIntExtra("eventid",  -1);
        final String name = intent.getStringExtra("name");
        final int age = intent.getIntExtra("age", -1);
        final String amount = intent.getStringExtra("amount");
        final String dateofcreation = intent.getStringExtra("dateofcreation");

        count = 1;
        // Display user details
        final EditText etusernameadd = (EditText) findViewById(R.id.etusernameadd);
        final Button bcreateandadduserevent = (Button) findViewById(R.id.bcreateandadduserevent);
        bcreateandadduserevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = etusernameadd.getText().toString();
                final String shareamount = String.valueOf(Double.parseDouble(amount)/count);
                Log.d(TAG, "ShareAmount: " + shareamount);
                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {

                                AlertDialog.Builder builder = new AlertDialog.Builder(EventUserActivity.this);
                                builder.setMessage(count+" User Added To Event"+eventname)
                                        .setNegativeButton("Add More", null)
                                        .create()
                                        .show();
                                count++;
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(EventUserActivity.this);
                                builder.setMessage("User Not Present in System")
                                        .setNegativeButton("Create Account", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                EventUserRequest eventuserRequest = new EventUserRequest(String.valueOf(eventid), username, amount, shareamount, shareamount, dateofcreation, eventname, responseListener);
                RequestQueue queue = Volley.newRequestQueue(EventUserActivity.this);
                queue.add(eventuserRequest);
            }
        });
        final Button bcalnotification = (Button) findViewById(R.id.bcalnotification);
        bcalnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EventUserActivity.this, EventViewActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                intent.putExtra("eventid",String.valueOf(eventid));
                intent.putExtra("eventdesc", eventname+" Rs"+amount);
                Log.d(TAG, "eventid: " + eventid);
                EventUserActivity.this.startActivity(intent);
            }
        });
    }
}
