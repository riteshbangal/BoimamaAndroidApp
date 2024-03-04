package in.boimama.app.android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import in.boimama.app.android.databinding.ActivitySplashBinding;
import in.boimama.app.android.story.ListStoriesActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppActivity {

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new Handler(Looper.getMainLooper())
                .postDelayed(() -> startActivity(
                        // new Intent(this, AuthenticationActivity.class) // TODO: Temporary
                         new Intent(this, ListStoriesActivity.class)
                ), 1000);

        binding.imageViewBannerLogo
                .setOnClickListener(view -> {
                    new Intent(this, ListStoriesActivity.class);
                });
    }
}