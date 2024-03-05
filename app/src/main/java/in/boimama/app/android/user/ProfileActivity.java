package in.boimama.app.android.user;

import static android.widget.Toast.makeText;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import in.boimama.app.android.AppActivity;
import in.boimama.app.android.R;
import in.boimama.app.android.databinding.ActivityProfileBinding;
import in.boimama.app.android.story.ListStoriesActivity;

public class ProfileActivity extends AppActivity {

    private ActivityProfileBinding binding;

    private SharedPreferences preferences;

    public static final int PICK_IMAGE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);

        String userDisplayName = preferences.getString("userDisplayName", null);
        binding.textViewUserDisplayName.setText(userDisplayName);
        String userProfileImagePath = preferences.getString("userProfileImagePath", null);
        setProfileImage(userProfileImagePath);

        binding.cardViewProfileImageContainer
                .setOnClickListener(view -> {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(new Intent(Intent.createChooser(intent, "Select image")), PICK_IMAGE_REQUEST_CODE);
                    makeText(getApplicationContext(), "Choose an image", Toast.LENGTH_SHORT).show();
                });

        binding.imageButtonClose
                .setOnClickListener(view -> startActivity(new Intent(this, ListStoriesActivity.class)));

        binding.appCompatButtonAddStory.setOnClickListener(view -> makeText(getApplicationContext(),
                "This feature is coming soon", Toast.LENGTH_SHORT).show());

        binding.appCompatButtonSavedStories.setOnClickListener(view -> makeText(getApplicationContext(),
                "This feature is coming soon", Toast.LENGTH_SHORT).show());

        binding.appCompatButtonStartReading
                .setOnClickListener(view -> startActivity(new Intent(this, ListStoriesActivity.class)));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK
            && data != null && data.getData() != null) {

            // User has picked an image
            Uri selectedImageUri = data.getData();

            try {
                // Convert the selected image URI to a file
                File imageFile = createImageFile();
                saveImageToFile(selectedImageUri, imageFile);

                final SharedPreferences.Editor editor = preferences.edit();
                editor.putString("userProfileImagePath", imageFile.getAbsolutePath());
                editor.apply();

                setProfileImage(imageFile.getAbsolutePath());
                makeText(getApplicationContext(), "Profile image updated", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            makeText(getApplicationContext(), "Image uploaded cancelled!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setProfileImage(String pImagePath) {
        Glide.with(binding.imageViewProfileImage.getContext())
                //.load("https://api-gw.boimama.in/story/42698925-76f6-4bf8-8b71-30587192f80d/image")
                .load(pImagePath)
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
                //.apply(RequestOptions.circleCropTransform())
                .into(binding.imageViewProfileImage);
    }

    private File createImageFile() throws IOException {
        final String imageFileName = "profile_image";
        return File.createTempFile(imageFileName, ".jpg", getExternalFilesDir(null));
    }

    private void saveImageToFile(Uri selectedImageUri, File imageFile) throws IOException {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);

        try (OutputStream os = new FileOutputStream(imageFile)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
        }
    }
}