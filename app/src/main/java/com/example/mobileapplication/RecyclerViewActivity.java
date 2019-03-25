package com.example.mobileapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<CompanyData> myDataset = new ArrayList();

    String company_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);


        recyclerView = findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        String query = getIntent().getStringExtra("key");
        jsonParse(query);

        // specify an adapter (see also next example)
      //mAdapter = new MyAdapter(myDataset);
      recyclerView.setAdapter(mAdapter);
    }

//    private void SearchDirector(){
//        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
//        System.out.println(company_no);
//    }

    private void jsonParse(String query) {

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        String url = "https://api.companieshouse.gov.uk/search/companies?q=" + query;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
//                            mTextViewResult.setText(response.toString());
                           // int num = response.getInt("total_results");
                            //mTextViewResult.setText(num + "");

                            //show the list of company title
                            //mTextViewResult.setText(response.toString());

                            JSONArray items = response.getJSONArray("items");

                            //(String title = 0; title < item.length(): title++);
                            for (int i = 0; i < items.length()  ; i ++) {
                                JSONObject jsonObject = items.getJSONObject(i);
                                CompanyData company = new CompanyData(jsonObject);
//                               String company_no = items.getJSONObject(i).has("company_number") ?
//                                        items.getJSONObject(i).getString("company_number") :
//                                        "N/A";
                                myDataset.add(company);
//                                myDataset.add(company_no);
//                                SearchDirector();
                                System.out.println(jsonObject);
                            }
                            //JSONObject firstItem = items.getJSONObject(0);
                            mAdapter = new MyAdapter(myDataset,getApplicationContext());
                            recyclerView.setAdapter(mAdapter);
                        } catch (Exception e) {
                         }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "17fTRSL1d9HnznaSPKWa9xjAVtN7KY25uCrGCdVm");
                return headers;
            }
        };

        queue.add(request);
    }
}
