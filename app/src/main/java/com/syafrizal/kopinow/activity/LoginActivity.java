package com.syafrizal.kopinow.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.syafrizal.kopinow.R;

public class LoginActivity extends AppCompatActivity {
    EditText et_username;
    EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView tv_register = findViewById(R.id.tv_register_login);
        et_username = findViewById(R.id.et_username_login);
        et_password = findViewById(R.id.et_password_login);


        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this , RegisterActivity.class);

                startActivity(intent);
                Animatoo.animateSlideLeft(LoginActivity.this);
            }
        });
    }
}
