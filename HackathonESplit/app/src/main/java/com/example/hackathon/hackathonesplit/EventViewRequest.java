package com.example.hackathon.hackathonesplit;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class EventViewRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://104.154.204.10/hackathon/listeventuser.php";
    private Map<String, String> params;

    public EventViewRequest(String eventid,  Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("eventid", eventid);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}