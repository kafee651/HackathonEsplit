package com.example.hackathon.hackathonesplit;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class TokenRefreshRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://104.154.204.10/hackathon/firebasetokenadd.php";
    private Map<String, String> params;

    public TokenRefreshRequest(String username, String token, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("token", token);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}