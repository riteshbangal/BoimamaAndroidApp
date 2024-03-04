package in.boimama.app.android.user;

import static android.widget.Toast.makeText;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import in.boimama.app.android.AppActivity;
import in.boimama.app.android.databinding.ActivityProfileBinding;
import in.boimama.app.android.story.ListStoriesActivity;

public class ProfileActivity extends AppActivity {

    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imageButtonClose
                .setOnClickListener(view -> startActivity(new Intent(this, ListStoriesActivity.class)));

        binding.appCompatButtonAddStory.setOnClickListener(view -> makeText(getApplicationContext(),
                "This feature is coming soon", Toast.LENGTH_SHORT).show());

        binding.appCompatButtonSavedStories.setOnClickListener(view -> makeText(getApplicationContext(),
                "This feature is coming soon", Toast.LENGTH_SHORT).show());

        binding.appCompatButtonStartReading
                .setOnClickListener(view -> startActivity(new Intent(this, ListStoriesActivity.class)));
    }
}