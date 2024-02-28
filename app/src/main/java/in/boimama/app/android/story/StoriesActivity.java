 package in.boimama.app.android.story;

 import static android.widget.Toast.makeText;

 import android.os.Bundle;
 import android.widget.Toast;

 import androidx.annotation.NonNull;
 import androidx.appcompat.app.AppCompatActivity;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

 import java.util.ArrayList;
 import java.util.List;

 import in.boimama.app.android.adapter.StoryAdapter;
 import in.boimama.app.android.databinding.ActivityStoriesBinding;
 import in.boimama.app.android.model.StoryItemModel;

 public class StoriesActivity extends AppCompatActivity {

     private ActivityStoriesBinding binding;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         binding = ActivityStoriesBinding.inflate(getLayoutInflater());
         setContentView(binding.getRoot());

         List<StoryItemModel> items = getDummyItems(); // TODO: Set dummy data (stories) into recycler view

         final RecyclerView recyclerView = binding.recyclerViewStories;
         final StoryAdapter adapter = new StoryAdapter(this, items);
         recyclerView.setAdapter(adapter);
         recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

         binding.floatingButtonAddStory
                 .setOnClickListener(view -> {
                     makeText(getApplicationContext(), "This feature is coming soon", Toast.LENGTH_SHORT).show();
                 });

    }

     @NonNull
     private static List<StoryItemModel> getDummyItems() {
         List<StoryItemModel> items = new ArrayList<>();
         final StoryItemModel item1 = new StoryItemModel();
         item1.setTitle("বাগদত্তা");
         item1.setContent("সমীর আর লক্ষ্মীর সংসারটা ভারী সুন্দর। ওদের তেরো বছরের কিশোরী মেয়ে সুজাতা আর ছয় বছরের ছেলে বিশে- এদের নিয়েই ওদের দিন কাটে। সমীর কাঠমিস্ত্রির কাজ করে। সারাদিন সেই কাজেই কেটে যায়। সমীরের কাজের অভাব নেই। প্রায় রোজই সে কাজ পায়। আয়-রোজগার ভালোই। পয়সা জমিয়ে দু-কামরা ঘর, বারান্দা, রান্নাঘর, কলতলা, উঠোন সমেত একটা দেড় কাঠা জায়গা সে কিনেছে। এক কামরার ভাড়া ঘরে আর কতদিন চলে");
         item1.setAuthor("অহীন্দ্র");
         item1.setLength(40);
         item1.setNumberOfLikes(21);
         item1.setPublishedDate("Feb 19, 2024");
         item1.setImageUrl("https://api-gw.boimama.in/story/05a22286-9981-4533-b3d9-45a5862b874f/image");
         // item1.setImageUrl("https://www.boimama.in/assets/images/story-1.jpg");

         items.add(item1);
         items.add(item1);
         items.add(item1);
         items.add(item1);
         items.add(item1);
         return items;
     }
 }