package com.example.github_followers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DashPage extends AppCompatActivity {
    private static final String TAG ="DashPage" ;
    String user_url;
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
        Intent intent=getIntent();
        String id=intent.getStringExtra("user_id");
        user_url= "https://api.github.com/users";
    }
    private void getUserData() {
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(user_url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException
            {
                try {
                    String responsedata=response.body().string();
                    JSONObject json = new JSONObject(responsedata);
                    final String owner = json.getString("id");
                    Log.d(TAG, "onResponse: "+owner);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }
}
