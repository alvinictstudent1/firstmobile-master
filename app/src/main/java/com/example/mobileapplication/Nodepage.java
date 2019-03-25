package com.example.mobileapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nodepage extends AppCompatActivity {


    private RequestQueue oQueue;
    private ArrayList<comOfficer>officerArray = new ArrayList<>();
    private ForceDirectedGraph directedGraph;
    public Nodepage() {
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nodepage);
        oQueue = Volley.newRequestQueue(this);

        jsonParse2(getIntent().getStringExtra("companyNumm"));


        //request using volley for officer data

    }
    public void jsonParse2(String companyNumm){
        String company_no = companyNumm;
        String url_f = "https://api.companieshouse.gov.uk/company/"+company_no+"/officers";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url_f, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("response", response.toString());
                            JSONArray items = response.getJSONArray("items");
                            Log.d("name",response.toString());
                            for (int i = 0; i < items.length()  ; i ++) {

                                JSONObject jsonObject = items.getJSONObject(i);
                                if (jsonObject.has("name")){
                                comOfficer officer = new comOfficer();
                                String ofName = jsonObject.getString("name");
                                officer.setOfficerName(ofName);
                                Log.d("officername",ofName);
                                officerArray.add(officer);

                                }
                            }
                            directedGraph.setOfficername(officerArray);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "17fTRSL1d9HnznaSPKWa9xjAVtN7KY25uCrGCdVm");
                return headers;
            }
        };
        oQueue.add(request);

        open();
    }
    public void open(){
        GraphView view = new GraphView(this);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        view.setOfficers(officerArray);

        setContentView(view);

        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.ic_afonso);
        view.defaultPhoto = b;
    }

    //Todo: create a constructor for this node public void drawOnCanvas(data){make the constructor for the canvas}
//   public void drawOnCanvas(data) {
//
//    }
}
