package com.example.hackathon.hackathonesplit;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class UserAreaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final String username = intent.getStringExtra("username");
        final int age = intent.getIntExtra("age", -1);

        TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        //final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        //EditText etAge = (EditText) findViewById(R.id.etAge);
        final Button bcreatelistfriend = (Button) findViewById(R.id.bcreatelistfriend);
        bcreatelistfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(UserAreaActivity.this, DisplayFriendlistActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                UserAreaActivity.this.startActivity(intent);
            }
        });
        final Button bListItem = (Button) findViewById(R.id.blist_item);

        // Display user details
        String message = " Welcome to Payकर, "+ name+" !!";
        tvWelcomeMsg.setText(message);
        //etUsername.setText(username);
        //etAge.setText(age + "");
        bListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //final String username = etUsername.getText().toString();
                final String token = MyFirebaseInstanceIDService.onUserLogin();
                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Intent intent = new Intent(UserAreaActivity.this, CreateListEventActivity.class);
                                intent.putExtra("name", name);
                                intent.putExtra("age", age);
                                intent.putExtra("username", username);
                                UserAreaActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(UserAreaActivity.this);
                                builder.setMessage("Token save Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                TokenRefreshRequest tokenrefreshRequest = new TokenRefreshRequest(username, token, responseListener);
                RequestQueue queue = Volley.newRequestQueue(UserAreaActivity.this);
                queue.add(tokenrefreshRequest);
            }
        });
    }
}