package in.boimama.app.android.authentication;

import static android.graphics.Color.RED;
import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Toast;

import in.boimama.app.android.databinding.ActivityLoginBinding;
import in.boimama.app.android.story.StoriesActivity;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonLogin
                .setOnClickListener(view -> {
                    final String userName = binding.editTextUserName.getText().toString();
                    final String password = binding.editTextPassword.getText().toString();
                    if (userName.isEmpty()) {
                        // If text is empty, show an error message or take appropriate action
                        // binding.editTextGuestDisplayName.setError("Please enter your username!");
                        binding.editTextUserName.setHintTextColor(ColorStateList.valueOf(RED));
                        makeText(getApplicationContext(), "Please enter your username!", Toast.LENGTH_SHORT).show();
                    } else if (password.isEmpty()) {
                        // If text is empty, show an error message or take appropriate action
                        // binding.editTextGuestDisplayName.setError("Please enter your password!");
                        binding.editTextPassword.setHintTextColor(ColorStateList.valueOf(RED));
                        makeText(getApplicationContext(), "Please enter your password!", Toast.LENGTH_SHORT).show();
                    } else if (userName.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
                        makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, StoriesActivity.class));
                    } else {
                        makeText(getApplicationContext(), "Invalid credentials!", Toast.LENGTH_SHORT).show();
                    }

                });

        binding.textViewBackHome
                .setOnClickListener(view -> {
                    startActivity(new Intent(this, AuthenticationActivity.class));
                });
    }
}
