package in.boimama.app.android.authentication;

import static android.widget.Toast.makeText;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Toast;

import in.boimama.app.android.AppActivity;
import in.boimama.app.android.R;
import in.boimama.app.android.databinding.ActivityGuestLoginBinding;
import in.boimama.app.android.story.ListStoriesActivity;

public class GuestLoginActivity extends AppActivity {

    private ActivityGuestLoginBinding binding;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGuestLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);

        binding.buttonGuestUser
                .setOnClickListener(view -> {
                    final String guestUserName = binding.editTextGuestDisplayName.getText().toString();
                    if (guestUserName.isEmpty()) {
                        // If text is empty, show an error message or take appropriate action
                        binding.editTextGuestDisplayName.setHintTextColor(ColorStateList.valueOf(getColor(R.color.red)));
                        makeText(getApplicationContext(), "Please enter your name!", Toast.LENGTH_SHORT).show();
                    } else {
                        final SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("userDisplayName", guestUserName);
                        editor.apply();

                        System.out.println("Guest user: " + guestUserName);
                        startActivity(new Intent(GuestLoginActivity.this, ListStoriesActivity.class));
                    }
                });

        binding.buttonLogin
                .setOnClickListener(view -> {
                    // startActivity(new Intent(this, LoginActivity.class));
                    makeText(getApplicationContext(), "This feature is coming soon", Toast.LENGTH_SHORT).show();
                });
    }
}