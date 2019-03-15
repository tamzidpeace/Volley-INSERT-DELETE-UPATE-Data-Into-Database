package com.example.arafat.volley_save_data_into_database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //const
    private static final String TAG = "MainActivity";

    //member variable
    private EditText username, preUsername;
    private EditText password, prePassword;
    private TextView infoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        preUsername = findViewById(R.id.pre_username);
        password = findViewById(R.id.password);
        prePassword = findViewById(R.id.pre_password);
        infoTextView = findViewById(R.id.info_tv);

        Button save = findViewById(R.id.save);
        final Button delete = findViewById(R.id.delete);
        final Button updateBtn = findViewById(R.id.update);
        final Button infoBtn = findViewById(R.id.show_btn);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteInfo();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateInfo();
            }
        });

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInformation();
            }
        });

        // insert operation

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                RequestQueue requestQueue;


                Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

                Network network = new BasicNetwork(new HurlStack());

                requestQueue = new RequestQueue(cache, network);

                requestQueue.start();

                String url = "http://192.168.43.30/volley/volley-insert-data.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Do something with the response
                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error
                                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "onErrorResponse: " + error.toString());
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        String name = username.getText().toString();
                        String pass = password.getText().toString();

                        Map<String, String> param = new HashMap<>();
                        param.put("name", name);
                        param.put("pass", pass);
                        return param;
                    }
                };

                MySingleton.getInstance(MainActivity.this).addToRequestQueue(stringRequest);

            }
        });
    }

    // delete operation

    private void deleteInfo() {
        RequestQueue requestQueue;


        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);

        requestQueue.start();

        String url = "http://192.168.43.30/volley-delete-data.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do something with the response
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onErrorResponse: " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                String name = username.getText().toString();

                Map<String, String> param = new HashMap<>();
                param.put("user_name", name);
                return param;
            }
        };

        MySingleton.getInstance(MainActivity.this).addToRequestQueue(stringRequest);

    }

    // update operation

    private void updateInfo() {

        RequestQueue requestQueue;


        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);

        requestQueue.start();

        String url = "http://192.168.43.30/volley-update-data.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do something with the response
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onResponse: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onErrorResponse: " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                String name = username.getText().toString();
                String preName = preUsername.getText().toString();
                String pass = password.getText().toString();
                String prePass = prePassword.getText().toString();

                Map<String, String> param = new HashMap<>();
                param.put("user_name", name);
                param.put("pre_user_name", preName);
                param.put("user_pass", pass);
                param.put("pre_user_pass", prePass);
                return param;
            }
        };

        MySingleton.getInstance(MainActivity.this).addToRequestQueue(stringRequest);
    }

    // show information operation

    private void showInformation() {

        RequestQueue requestQueue;


        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        Network network = new BasicNetwork(new HurlStack());

        requestQueue = new RequestQueue(cache, network);

        requestQueue.start();

        String url = "http://192.168.43.30/volley/volley-request-data.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            for (int i = 0; i < 2 ; i++) {
                                String id = response.getJSONArray("DataArray").getJSONObject(i).getString("ID");
                                String name = response.getJSONArray("DataArray").getJSONObject(i).getString("Name");
                                String pass = response.getJSONArray("DataArray").getJSONObject(i).getString("Password");

                                infoTextView.append(id + " " + name + " " + pass + "\n");
                                //infoTextView.setText();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onErrorResponse: " + error.toString());
                    }
                });

        MySingleton.getInstance(MainActivity.this).addToRequestQueue(jsonObjectRequest);

    }
}
