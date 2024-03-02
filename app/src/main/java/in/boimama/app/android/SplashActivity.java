package in.boimama.app.android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import in.boimama.app.android.story.ListStoriesActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler(Looper.getMainLooper())
                .postDelayed(() -> startActivity(
                        // new Intent(this, AuthenticationActivity.class) // TODO: Temporary
                         new Intent(this, ListStoriesActivity.class)
//                        new Intent(this, ReadStoryActivity.class)
                ), 1000);
    }
}