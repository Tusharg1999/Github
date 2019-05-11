package com.example.github_followers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {
    private EditText userid;
    private Button submitbutton;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    private void init()
    {
        userid=findViewById(R.id.text_editor);
        submitbutton=findViewById(R.id.submit_button);
        OkHttpClient client=new OkHttpClient();
        url="https://api.github.com/users";
    }

}
