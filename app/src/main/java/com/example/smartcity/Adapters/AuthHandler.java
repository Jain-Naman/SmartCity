package com.example.smartcity.Adapters;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.smartcity.MainActivity;
import com.example.smartcity.UserViews.Menu;
import com.example.smartcity.Utils.FirebaseResponseListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;

import javax.security.auth.callback.Callback;

public class AuthHandler {
    //    private MainActivity activity;
    private FirebaseAuth auth;
    private MainActivity activity;

    public AuthHandler(FirebaseAuth auth, MainActivity activity) {
        this.auth = auth;
        this.activity = activity;
    }


    public void signIn(String email, String password, FirebaseResponseListener<Boolean> firebaseResponseListener) {
        Log.d("state", "In sign in");

        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        // FirebaseUser user = auth.getCurrentUser();
                        Log.d("state", authResult.toString());
                        firebaseResponseListener.onCallback(true);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Log.d("state", "Failed");
                firebaseResponseListener.onCallback(false);
            }
        });
        Log.d("state", "Outside Auth");
    }

}