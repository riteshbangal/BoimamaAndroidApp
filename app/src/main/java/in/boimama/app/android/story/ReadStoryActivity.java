package in.boimama.app.android.story;

import static android.widget.Toast.makeText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.Serializable;

import in.boimama.app.android.R;
import in.boimama.app.android.databinding.ActivityReadStoryBinding;
import in.boimama.app.android.model.StoryItemModel;

public class ReadStoryActivity extends AppCompatActivity {

    private ActivityReadStoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReadStoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        StoryItemModel storyItemModel = extractedStoryItemModel();
        if (null != storyItemModel) {
            binding.textViewStoryTitle.setText(storyItemModel.getTitle());
            binding.textViewAuthorName.setText(storyItemModel.getAuthor());
            binding.textViewStoryContent.setText(storyItemModel.getContent());
            binding.textViewStoryMetadata.setText(storyItemModel.getPublishedDate() + " . " + storyItemModel.getLength() + " mins");
            Glide.with(binding.imageViewAuthorImage.getContext())
                    .load(storyItemModel.getImageUrl())
                    .placeholder(R.drawable.no_image)
                    .error(R.drawable.no_image)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            Log.e("Glide", "Image load failed: " + e.getMessage());
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            Log.d("Glide", "Image loaded successfully");
                            return false;
                        }
                    })
                    .into(binding.imageViewAuthorImage);

        } else {
            makeText(getApplicationContext(), "Failed to load story details", Toast.LENGTH_SHORT).show();
        }


        binding.imageButtonBack
                .setOnClickListener(view -> {
                    startActivity(new Intent(this, ListStoriesActivity.class));
                });
    }

    private StoryItemModel extractedStoryItemModel() {
        Serializable serializableExtra = getIntent().getSerializableExtra("storyItemModel");
        StoryItemModel storyItemModel = null;
        if (serializableExtra instanceof StoryItemModel) {
            storyItemModel = (StoryItemModel) serializableExtra;
        }
        return storyItemModel;
    }
}