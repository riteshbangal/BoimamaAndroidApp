package in.boimama.app.android.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import in.boimama.app.android.SplashActivity;

import in.boimama.app.android.databinding.ActivityAuthenticationBinding;

public class AuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAuthenticationBinding activityAuthenticationBinding = ActivityAuthenticationBinding.inflate(getLayoutInflater());
        setContentView(activityAuthenticationBinding.getRoot());

        activityAuthenticationBinding.buttonLogin
                .setOnClickListener(view -> startActivity(
                        new Intent(AuthenticationActivity.this, LoginActivity.class))
                );

        activityAuthenticationBinding.buttonRegister
                .setOnClickListener(view -> startActivity(
                        new Intent(AuthenticationActivity.this, SplashActivity.class))
                );

        activityAuthenticationBinding.buttonGuestUser
                .setOnClickListener(view -> startActivity(
                        new Intent(AuthenticationActivity.this, GuestLoginActivity.class))
                );
    }
}