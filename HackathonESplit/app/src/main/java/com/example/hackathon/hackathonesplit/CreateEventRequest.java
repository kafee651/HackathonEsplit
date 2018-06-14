package com.example.hackathon.hackathonesplit;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CreateEventRequest extends StringRequest {
    private static final String CREATE_EVENT_REQUEST_URL = "http://104.154.204.10/hackathon/createevent.php";
    private Map<String, String> params;

    public CreateEventRequest(String eventname, String description, String username, String amount, Response.Listener<String> listener) {
        super(Method.POST, CREATE_EVENT_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", eventname);
        params.put("description", description);
        params.put("username", username);
        params.put("amount", amount);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}