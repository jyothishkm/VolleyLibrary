package com.example.bridgeit.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {
String url = "https://raw.githubusercontent.com/ianbar20/JSON-Volley-Tutorial/master/Example-JSON-Files/Example-Object.JSON";
    TextView txt;
    String data ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) findViewById(R.id.txt);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url, new Response.Listener<JSONObject>()
        {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject obj = response.getJSONObject("colorObject");
                    String color  = obj.getString("colorName");
                    String desc = obj.getString("description");
                    data += "color:" + color + "\n" +"Description:" + desc;
                    txt.setText(data);

                } catch (JSONException e) {
                    Log.e("Volley", "Error");
                    e.printStackTrace();
                }

            }

        },
        new Response.ErrorListener()

            {

                @Override
                public void onErrorResponse (VolleyError error){
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
             });
        requestQueue.add(jsonObjectRequest);



    }
}
