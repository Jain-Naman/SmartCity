//package com.example.smartcity.Utils;
//
//import android.content.Intent;
//import android.os.Handler;
//import android.os.Looper;
//import android.os.Message;
//import android.util.Log;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//
//import com.example.smartcity.MainActivity;
//import com.example.smartcity.UserViews.Menu;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
//import java.util.Timer;
//import java.util.TimerTask;
//import java.util.concurrent.Executor;
//
//import javax.security.auth.callback.Callback;
//
//public class AuthHandler {
////    private MainActivity activity;
//    private  FirebaseAuth auth;
//    private MainActivity activity;
//    public AuthHandler(FirebaseAuth auth, MainActivity activity){
//        this.auth=auth;
//        this.activity = activity;
//    }
//
//    public boolean signIn(String email,String password){
//        Log.d("state", "In sign in");
//        final boolean[] signedIn = new boolean[1];
//
//        post(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
//
//        auth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener((Executor) this, task -> {
//                    if (task.isSuccessful()) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d("in","signInWithEmail:success");
//                        FirebaseUser user = auth.getCurrentUser();
//
////                                    updateUI(user);
//                    } else {
//                        Log.d("in","failed");
//
//                    }
//                });
//
//
//        Log.d("state", "Outside Auth");
//        Log.d("state", String.valueOf(signedIn[0]));
//        return  signedIn[0];
//    }
//
//
//
//}
