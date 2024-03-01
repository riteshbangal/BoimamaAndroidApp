package in.boimama.app.android.story;

import static android.widget.Toast.makeText;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

    boolean isLiked = false, isSaved = false;


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

        // Set initial mode as dark
        binding.imageButtonDarkMode.setTag(Integer.toString(darkModeDrawable));
        binding.imageButtonDarkMode
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toggleDarkMode();
                    }
                });



        final int likeUnselectDrawable=  R.drawable.like_icon;
        final int likeSelectDrawable=  R.drawable.like_selected_icon;
        binding.floatingButtonLikeStory.setTag(likeUnselectDrawable); // Set tag to achieve toggle functionality
        binding.floatingButtonLikeStory
                .setOnClickListener(view -> {
                    // int currentSrcResourceId = Integer.parseInt(binding.floatingButtonLikeStory.getTag().toString());
                    int nextDrawableToggleResource = isLiked ? likeUnselectDrawable : likeSelectDrawable;
                    binding.floatingButtonLikeStory.setImageResource(nextDrawableToggleResource);

                    // Update the tag to store the current drawable resource ID
                    binding.floatingButtonLikeStory.setTag(Integer.toString(nextDrawableToggleResource));

                    // Toggle the button
                    isLiked = !isLiked;

                    if (isLiked) {
                        // Write logic to increase like count
                    } else {
                        // Write logic to reset like count
                    }
                });

        final int saveUnselectDrawable=  R.drawable.saved_icon;
        final int saveSelectDrawable=  R.drawable.save_icon;
        binding.floatingButtonSaveStory.setTag(saveUnselectDrawable); // Set tag to achieve toggle functionality
        binding.floatingButtonSaveStory
                .setOnClickListener(view -> {
                    int nextDrawableToggleResource = isSaved ? saveUnselectDrawable : saveSelectDrawable;
                    binding.floatingButtonSaveStory.setImageResource(nextDrawableToggleResource);

                    // Update the tag to store the current drawable resource ID
                    binding.floatingButtonSaveStory.setTag(Integer.toString(nextDrawableToggleResource));

                    // Toggle the button
                    isSaved = !isSaved;

                    if (isSaved) {
                        makeText(getApplicationContext(), "Story added to your list", Toast.LENGTH_SHORT).show();
                    } else {
                        makeText(getApplicationContext(), "Story removed from your saved list", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private boolean isDarkMode = false;
    private final int darkModeDrawable = R.drawable.toggle_mode_dark;
    private final int lightModeDrawable = R.drawable.toggle_mode_light;

    private void toggleDarkMode() {
        // Change the drawable resource based on the dark mode flag
        int nextDrawableToggleResource = isDarkMode ? darkModeDrawable : lightModeDrawable; // When mode is dark, theme icon is opposite!
        binding.imageButtonDarkMode.setImageResource(nextDrawableToggleResource);

        // Update the tag to store the current drawable resource ID
        binding.imageButtonDarkMode.setTag(Integer.toString(nextDrawableToggleResource));

        // Toggle the dark mode flag
        isDarkMode = !isDarkMode;

        // Set theme
        if (isDarkMode) { // Set dark mode theme
            binding.readStoryConstraintLayout.setBackgroundColor(getColor(R.color.app_dark_background));
            binding.imageButtonBack.setImageResource(R.drawable.back_icon_light);
            binding.textViewStoryTitle.setTextColor(getColor(R.color.white));
            binding.textViewAuthorName.setTextColor(getColor(R.color.app_dark_font));
            binding.textViewStoryMetadata.setTextColor(getColor(R.color.app_dark_font));
            binding.dividerStoryCard.setBackgroundColor(getColor(R.color.app_dark_grey));
            binding.textViewStoryContent.setTextColor(getColor(R.color.white));
       } else { // Set light mode theme
            binding.readStoryConstraintLayout.setBackgroundColor(getColor(R.color.white));
            binding.imageButtonBack.setImageResource(R.drawable.back_icon_dark);
            binding.textViewStoryTitle.setTextColor(getColor(R.color.blue));
            binding.textViewAuthorName.setTextColor(getColor(R.color.app_dark_grey));
            binding.textViewStoryMetadata.setTextColor(getColor(R.color.app_dark_grey));
            binding.dividerStoryCard.setBackgroundColor(getColor(R.color.app_light_grey));
            binding.textViewStoryContent.setTextColor(getColor(R.color.black));
        }
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