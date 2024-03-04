package in.boimama.app.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import in.boimama.app.android.R;
import in.boimama.app.android.databinding.StoryItemBinding;
import in.boimama.app.android.model.StoryItemModel;
import in.boimama.app.android.story.ReadStoryActivity;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {

    private List<StoryItemModel> items;

    private LayoutInflater inflater;

    public StoryAdapter(Context context, List<StoryItemModel> items) {
        this.inflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        StoryItemBinding binding = StoryItemBinding.inflate(inflater, parent, false);
        return new StoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(StoryViewHolder holder, int position) {
        StoryItemModel item = items.get(position);
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateData(List<StoryItemModel> newData) {
        this.items = newData;
        notifyDataSetChanged();
    }


    public class StoryViewHolder extends RecyclerView.ViewHolder {

        private final StoryItemBinding binding;

        public StoryViewHolder(StoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(StoryItemModel storyItemModel) {
            binding.textViewStoryTitle.setText(storyItemModel.getTitle());
            binding.textViewAuthorName.setText(storyItemModel.getAuthor());
            binding.textViewStoryMetadata.setText(storyItemModel.getPublishedDate() + " . " + storyItemModel.getLength() + "mins");
            binding.textViewStoryShortContent.setText(storyItemModel.getContent());
            binding.textViewLikesCount.setText(String.valueOf(storyItemModel.getNumberOfLikes()));

            Glide.with(binding.imageViewAuthorImage.getContext())
                    .load(storyItemModel.getAuthorImageUrl())
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

            // Set on click listener
            binding.buttonReadMore.setOnClickListener(view -> {
                Context context = binding.getRoot().getContext();
                Intent intent = new Intent(context, ReadStoryActivity.class);
                intent.putExtra("storyItemModel", storyItemModel); // TODO: Need to pass story Id
                context.startActivity(intent);

                // Show a Toast message for 2 seconds
                //makeText(getApplicationContext(), "This feature is coming soon", Toast.LENGTH_SHORT).show();
            });
        }
    }
}
