package com.example.github_followers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {
    private EditText userid;
    private Button submitbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String id=userid.getText().toString();
                if (TextUtils.isEmpty(id))
                {
                    Toast.makeText(MainActivity.this, "Enter user id...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent=new Intent(MainActivity.this,DashPage.class);
                    intent.putExtra("user_id",id);
                    startActivity(intent);
                }
            }
        });
    }


    private void init()
    {
        userid=findViewById(R.id.text_editor);
        submitbutton=findViewById(R.id.submit_button);
//
    }

}
