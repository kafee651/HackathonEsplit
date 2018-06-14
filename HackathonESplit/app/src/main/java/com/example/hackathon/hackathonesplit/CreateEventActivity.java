package com.example.hackathon.hackathonesplit;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        final String name = intent.getStringExtra("name");
        final int age = intent.getIntExtra("age", -1);

        final EditText etEventname = (EditText) findViewById(R.id.etEventname);
        final EditText etDescription = (EditText) findViewById(R.id.etDescription);
        final EditText etamount = (EditText) findViewById(R.id.etamount);
        final RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radioGroup);

        // Display user details
        final Button bcreateandadduser = (Button) findViewById(R.id.bcreateandadduser);
        bcreateandadduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String eventname = etEventname.getText().toString();
                final String description = etDescription.getText().toString();
                final String amount = etamount.getText().toString();

                int selectedId = radioGroup.getCheckedRadioButtonId();
                final RadioButton calradioButton = (RadioButton) findViewById(selectedId);
                if(selectedId==-1){
                    Toast.makeText(CreateEventActivity.this,"Calculation Method not Selected", Toast.LENGTH_SHORT).show();
                }
                else {

                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");

                                    if (success) {
                                        int eventid = jsonResponse.getInt("eventid");
                                        String dateofcreation = jsonResponse.getString("dateofcreation");

                                        String caltext = (String)calradioButton.getText();

                                        // Response received from the server
                                        if(new String("Equal").equals(caltext)) {
                                            Intent intent = new Intent(CreateEventActivity.this, EventUserActivity.class);
                                            intent.putExtra("name", name);
                                            intent.putExtra("username", username);
                                            intent.putExtra("eventid", eventid);
                                            intent.putExtra("eventname", eventname);
                                            intent.putExtra("age", age);
                                            intent.putExtra("amount", amount);
                                            intent.putExtra("dateofcreation", dateofcreation);
                                            CreateEventActivity.this.startActivity(intent);
                                        }
                                        else{
                                            Intent intent = new Intent(CreateEventActivity.this, EventUserPerActivity.class);
                                            intent.putExtra("name", name);
                                            intent.putExtra("username", username);
                                            intent.putExtra("eventid", eventid);
                                            intent.putExtra("eventname", eventname);
                                            intent.putExtra("age", age);
                                            intent.putExtra("amount", amount);
                                            intent.putExtra("dateofcreation", dateofcreation);
                                            CreateEventActivity.this.startActivity(intent);
                                        }
                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(CreateEventActivity.this);
                                        builder.setMessage("Group Creation Failed")
                                                .setNegativeButton("Retry", null)
                                                .create()
                                                .show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };

                        CreateEventRequest createeventRequest = new CreateEventRequest(eventname, description, username, amount, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(CreateEventActivity.this);
                        queue.add(createeventRequest);

                }
            }
        });
    }

}
