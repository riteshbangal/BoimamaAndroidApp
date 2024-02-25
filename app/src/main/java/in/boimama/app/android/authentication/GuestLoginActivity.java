package in.boimama.app.android.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import in.boimama.app.android.databinding.ActivityGuestLoginBinding;

public class GuestLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityGuestLoginBinding activityGuestLoginBinding = ActivityGuestLoginBinding.inflate(getLayoutInflater());
        setContentView(activityGuestLoginBinding.getRoot());

        activityGuestLoginBinding.buttonGuestUser
                .setOnClickListener(view -> {
                    final String guestUserName = activityGuestLoginBinding.editTextGuestDisplayName.getText().toString();
                    System.out.println("Guest user: " + guestUserName);
                    //startActivity(new Intent(GuestLoginActivity.this, GuestLoginActivity.class));
                });
    }
}