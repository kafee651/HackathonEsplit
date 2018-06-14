package com.example.hackathon.hackathonesplit;

        import android.app.AlertDialog;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
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

        import static android.content.ContentValues.TAG;

public class AddFriendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        final String name = intent.getStringExtra("name");
        final int age = intent.getIntExtra("age", -1);

        final EditText etfriendnameadd = (EditText) findViewById(R.id.etusernameadd);
        final Button blistfriend = (Button) findViewById(R.id.blistfriend);
        blistfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(AddFriendActivity.this, DisplayFriendlistActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                AddFriendActivity.this.startActivity(intent);
            }
        });
        final Button bcreateandaddfriend = (Button) findViewById(R.id.bcreateandaddfriend);
        bcreateandaddfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //final String friendnameadd = etfriendnameadd.getText().toString();
                final String friendnameadd = etfriendnameadd.getText().toString();
                Log.d(TAG, "Call request data " + username+" "+friendnameadd);
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddFriendActivity.this);
                                builder.setMessage("New Friend Added")
                                        .setNegativeButton("Add More", null)
                                        .create()
                                        .show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddFriendActivity.this);
                                builder.setMessage("Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                AddFriendRequest addfriendrequest = new AddFriendRequest(username, friendnameadd, responseListener);
                RequestQueue queue = Volley.newRequestQueue(AddFriendActivity.this);
                queue.add(addfriendrequest);

            }
        });

    }
}
