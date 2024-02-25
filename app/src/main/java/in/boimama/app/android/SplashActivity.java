package in.boimama.app.android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import in.boimama.app.android.authentication.AuthenticationActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler(Looper.getMainLooper())
                .postDelayed(() -> startActivity(
                        new Intent(SplashActivity.this, AuthenticationActivity.class)
                ), 3000);
    }
}