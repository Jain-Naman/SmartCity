package com.example.smartcity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartcity.AdminViews.AdminAction;
import com.example.smartcity.Models.User;
import com.example.smartcity.UserViews.ILoginView;
import com.example.smartcity.UserViews.Menu;

import com.example.smartcity.Adapters.AuthHandler;
import com.example.smartcity.Utils.FirebaseResponseListener;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements ILoginView {
    Button loginButton;
    EditText email, password;
    private FirebaseAuth mAuth;

    @Override
    public void OnLoginSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnLoginError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("main_activity", "Launched main");

        loginButton = findViewById(R.id.loginButton);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        User user = new User(email.getText().toString().trim(), password.getText().toString().trim());
        user.setEmail(email.getText().toString().trim());
        user.setPassword(password.getText().toString().trim());

        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthHandler auth = new AuthHandler(mAuth, MainActivity.this);
                auth.signIn(email.getText().toString().trim(), password.getText().toString().trim(), new FirebaseResponseListener<Boolean>() {
                    @Override
                    public void onCallback(Boolean response) {
                        if (response) {
                            Intent i;
                            Globals.email = email.getText().toString();
                            Globals.currentUser = email.getText().toString().substring(0, 5);
                            if (Globals.currentUser.equals("admin")) {
                                i = new Intent(MainActivity.this, AdminAction.class);
                            } else {
                                i = new Intent(MainActivity.this, Menu.class);
                            }
                            startActivity(i);
                        } else {
                            OnLoginError("Failed");
                        }
                    }
                });
            }
        });

    }
}