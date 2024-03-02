package in.boimama.app.android.authentication;

import static android.widget.Toast.makeText;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import in.boimama.app.android.AppActivity;
import in.boimama.app.android.databinding.ActivityAuthenticationBinding;

public class AuthenticationActivity extends AppActivity {

    private ActivityAuthenticationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthenticationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonLogin
                .setOnClickListener(view -> startActivity(
                        new Intent(this, LoginActivity.class))
                );

        binding.buttonRegister
                .setOnClickListener(view -> {
                    //startActivity(new Intent(this, SplashActivity.class));

                    // Show a Toast message for 2 seconds
                    makeText(getApplicationContext(), "This feature is coming soon", Toast.LENGTH_SHORT).show();
                });

        binding.buttonGuestUser
                .setOnClickListener(view -> startActivity(
                        new Intent(this, GuestLoginActivity.class))
                );
    }
}