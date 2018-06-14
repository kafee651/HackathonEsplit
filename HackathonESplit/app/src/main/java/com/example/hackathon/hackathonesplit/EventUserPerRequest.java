package com.example.hackathon.hackathonesplit;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class EventUserPerRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://104.154.204.10/hackathon/addusereventper.php";
    private Map<String, String> params;

    public EventUserPerRequest(String eventid, String username, String amount, String shareamount, String due, String dateofcreation, String eventname,  Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("eventid", eventid);
        params.put("username", username);
        params.put("amount", amount);
        params.put("shareamount", shareamount);
        params.put("due", due);
        params.put("dateofcreation", dateofcreation);
        params.put("eventname", eventname);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
