package com.example.hackathon.hackathonesplit;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DisplayFriendlistRequest extends StringRequest {
    private static final String CREATE_EVENT_REQUEST_URL = "http://104.154.204.10/hackathon/listfriend.php";
    private Map<String, String> params;

    public DisplayFriendlistRequest(String username, Response.Listener<String> listener) {
        super(Method.POST, CREATE_EVENT_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}