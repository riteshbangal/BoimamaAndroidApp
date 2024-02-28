package in.boimama.app.android.authentication;

import static android.graphics.Color.RED;
import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Toast;

import in.boimama.app.android.databinding.ActivityGuestLoginBinding;
import in.boimama.app.android.story.ListStoriesActivity;

public class GuestLoginActivity extends AppCompatActivity {

    private ActivityGuestLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGuestLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonGuestUser
                .setOnClickListener(view -> {
                    final String guestUserName = binding.editTextGuestDisplayName.getText().toString();
                    if (guestUserName.isEmpty()) {
                        // If text is empty, show an error message or take appropriate action
                        // binding.editTextGuestDisplayName.setError("Please enter your name!");
                        binding.editTextGuestDisplayName.setHintTextColor(ColorStateList.valueOf(RED));
                        makeText(getApplicationContext(), "Please enter your name!", Toast.LENGTH_SHORT).show();
                    } else {
                        System.out.println("Guest user: " + guestUserName);
                        startActivity(new Intent(GuestLoginActivity.this, ListStoriesActivity.class));
                    }

                });

        binding.buttonLogin
                .setOnClickListener(view -> startActivity(
                        new Intent(this, LoginActivity.class))
                );
    }
}