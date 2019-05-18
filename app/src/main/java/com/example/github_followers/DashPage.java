package com.example.github_followers;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
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
    CircleImageView avatarImage;

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
        avatarImage=findViewById(R.id.avatar_image);
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
                    Log.d(TAG, "onResponse: name"+owner);
                    String imageUrl=json.getString("avatar_url");
                    Log.d(TAG, "onResponse: image url is"+imageUrl);
                    handler.post(new Runnable(){
                        @Override
                        public void run() {
                            userName.setText(owner);
                            Picasso.with(DashPage.this).load(imageUrl).into(avatarImage);
                        }
                    });

                } catch (JSONException e1) {
                    e1.printStackTrace();
                }

            }
        });


    }
}


