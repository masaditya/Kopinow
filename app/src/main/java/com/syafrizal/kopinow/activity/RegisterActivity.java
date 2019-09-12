package com.syafrizal.kopinow.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.syafrizal.kopinow.R;
import com.syafrizal.kopinow.models.User;

public class RegisterActivity extends AppCompatActivity {
    private DatabaseReference database;

    EditText et_email;
    EditText et_name;
    EditText et_username;
    EditText et_phone;
    EditText et_password;
    EditText et_conf_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView tv_login = findViewById(R.id.tv_login_register);
        et_username = findViewById(R.id.et_username_reg);
        et_email = findViewById(R.id.et_email_reg);
        et_name = findViewById(R.id.et_name_reg);
        et_phone = findViewById(R.id.et_phone_reg);
        et_password = findViewById(R.id.et_password_reg);
        et_conf_password  = findViewById(R.id.et_conf_password);
        Button btn_register = findViewById(R.id.btn_register);

        database = FirebaseDatabase.getInstance().getReference();

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);

                startActivity(intent);
                Animatoo.animateSlideRight(RegisterActivity.this);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(et_username.getText().toString()) && isEmpty(et_email.getText().toString()) && isEmpty(et_name.getText().toString()) && isEmpty(et_phone.getText().toString()) && isEmpty(et_password.getText().toString()) &&isEmpty(et_conf_password.getText().toString())){
                    Snackbar.make(findViewById(R.id.btn_register), "Field can't be empty !", Snackbar.LENGTH_LONG).show();
                }else if(!isValidEmail(et_email.getText().toString().trim())){
                    Snackbar.make(findViewById(R.id.btn_register), "Email not valid !", Snackbar.LENGTH_LONG).show();
                }else if (!et_password.getText().toString().equals(et_conf_password.getText().toString()))
                    Snackbar.make(findViewById(R.id.btn_register), "Confirm Password not same", Snackbar.LENGTH_LONG).show();
                else{
                    User user = new User();
                    user.setUsername(et_username.getText().toString());
                    user.setEmail(et_email.getText().toString());
                    user.setName(et_name.getText().toString());
                    user.setTelephone(et_phone.getText().toString());
                    user.setPassword(et_password.getText().toString());
                    user.setRole("user");
                    doRegister(user);
                }

            }
        });
    }

    public static boolean isEmpty(String s) {
        return TextUtils.isEmpty(s);
    }

    public static boolean isValidEmail(CharSequence email) {
        return (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private void doRegister(User user){
        database.child("users").push().setValue(user).addOnSuccessListener(this,new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                Snackbar.make(findViewById(R.id.btn_register), "Register Success !", Snackbar.LENGTH_LONG).show();
                Animatoo.animateSlideRight(RegisterActivity.this);

            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Snackbar.make(findViewById(R.id.btn_register), "Please check your connection !", Snackbar.LENGTH_LONG).show();
            }
        });

    }
}
