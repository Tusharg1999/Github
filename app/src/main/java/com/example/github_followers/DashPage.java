package com.example.github_followers;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DashPage extends AppCompatActivity {
    private static final String TAG = "DashPage";
    String user_url;
    Handler handler;
    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_page);
        init();
        new Thread(() -> {
            getUserData();
        }).start();

    }


    private void init() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("user_id");
        user_url = "https://api.github.com/users/" + id;
        handler=new Handler(getApplicationContext().getMainLooper());
        userName=findViewById(R.id.name_text);
    }

    private void getUserData() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(user_url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    String responseData = response.body().string();
                    Log.d(TAG, "onResponse: "+responseData);
                    JSONObject json = new JSONObject(responseData);
                    String owner = json.getString("name");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            userName.setText(owner);
                        }
                    });

                } catch (JSONException e1) {
                    e1.printStackTrace();
                }

            }
        });


    }
}


