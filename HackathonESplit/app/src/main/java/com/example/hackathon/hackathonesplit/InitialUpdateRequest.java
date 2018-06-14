package com.example.hackathon.hackathonesplit;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InitialUpdateRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://104.154.204.10/hackathon/initialupdate.php";
    private Map<String, String> params;

    public InitialUpdateRequest(String eventid, String amountset,  Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("eventid", eventid);
        params.put("amountset", amountset);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}