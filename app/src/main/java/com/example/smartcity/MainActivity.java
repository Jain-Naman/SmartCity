package com.example.smartcity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartcity.AdminViews.AdminAction;
import com.example.smartcity.Adapters.ILoginController;
import com.example.smartcity.Adapters.LoginController;
import com.example.smartcity.UserViews.ILoginView;
import com.example.smartcity.UserViews.JobseekerActivity;
import com.example.smartcity.UserViews.Menu;
import com.example.smartcity.UserViews.TravelActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements ILoginView {
    Button loginButton;
    EditText email, password;
    ILoginController loginPresenter;
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
        loginPresenter = new LoginController(this);


        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Globals.currentUser = "user"; // set via Login information
                Intent i = new Intent(MainActivity.this, Menu.class);
                startActivity(i);
                // finish();
            }
        });

//        mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
//                .addOnCompleteListener(MainActivity.this, task -> {
//                    if (task.isSuccessful()) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d("in", "signInWithEmail:success");
//                        FirebaseUser user = mAuth.getCurrentUser();
//                        final Intent i = new Intent(MainActivity.this, Menu.class);
//                        startActivity(i);
//                        // updateUI(user);
//                    } else {
//                        Log.d("in", "failed");
//                        OnLoginError("Failed");
//                    }
//                });
    }
}