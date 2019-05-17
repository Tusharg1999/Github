package com.example.github_followers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DashPage extends AppCompatActivity {
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
        try (Response response = client.newCall(request).execute())
        {String value=response.body().string();
        Log.d("DashPage",value);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
