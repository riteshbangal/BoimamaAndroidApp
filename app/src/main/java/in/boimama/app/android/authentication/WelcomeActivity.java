package in.boimama.app.android.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import in.boimama.app.android.R;

public class WelcomeActivity extends AppCompatActivity {

    //ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }
}