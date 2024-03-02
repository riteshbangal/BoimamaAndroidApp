 package in.boimama.app.android.story;

 import static android.widget.Toast.makeText;

 import android.os.AsyncTask;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.Toast;

 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;
 import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

 import org.json.JSONArray;
 import org.json.JSONException;

 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.net.HttpURLConnection;
 import java.net.URL;
 import java.util.ArrayList;
 import java.util.List;

 import in.boimama.app.android.AppActivity;
 import in.boimama.app.android.adapter.StoryAdapter;
 import in.boimama.app.android.databinding.ActivityListStoriesBinding;
 import in.boimama.app.android.model.StoryItemModel;
 import in.boimama.app.android.utils.ModelMapperHelper;

 public class ListStoriesActivity extends AppActivity {

     private ActivityListStoriesBinding binding;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

         binding = ActivityListStoriesBinding.inflate(getLayoutInflater());
         setContentView(binding.getRoot());

         //binding.progressBarLoadData.setVisibility(View.GONE);
         new LoadDataAsyncTask().execute("https://api-gw-dev.boimama.in/story/all");

         binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
             @Override
             public void onRefresh() {
                 System.out.println("Refreshing...");
                 // Action post refresh
                 new LoadDataAsyncTask().execute("https://api-gw-dev.boimama.in/story/all");

                 // After data is fetched, stop the refresh animation
                 binding.swipeRefreshLayout.setRefreshing(false);
             }
         });

         binding.floatingButtonAddStory
                 .setOnClickListener(view -> {
                     makeText(getApplicationContext(), "This feature is coming soon", Toast.LENGTH_SHORT).show();
                 });

//         binding.recyclerViewStories.getFocusedChild() // TODO
//                 .setOnClickListener(view -> {
//                     new Intent(this, ReadStoryActivity.class);
//                 });

    }

     class LoadDataAsyncTask extends AsyncTask<String, Void, List<StoryItemModel>> {

         @Override
         protected void onPreExecute() {
             // Show the ProgressBar before starting the background task
             binding.recyclerViewStories.setVisibility(View.INVISIBLE);
             binding.progressBarLoadData.setVisibility(View.VISIBLE);
         }

         @Override
         protected List<StoryItemModel> doInBackground(String... params) {
             String apiUrl = params[0];
             try {
                 URL url = new URL(apiUrl);
                 HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                 // Set request method (GET, POST, etc.)
                 connection.setRequestMethod("GET");

                 // Get the response code
                 int responseCode = connection.getResponseCode();

                 if (responseCode == HttpURLConnection.HTTP_OK) {
                     // Reading response from input stream
                     BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                     StringBuilder response = new StringBuilder();
                     String line;
                     while ((line = reader.readLine()) != null) {
                         response.append(line);
                     }
                     reader.close();

                     // Return the response
                     return parseResponse(response.toString(), true);
                 } else {
                     // Handle the error, e.g., connection.getResponseMessage()
                     return parseResponse("Error: " + responseCode, false);
                 }
             } catch (IOException e) {
                 e.printStackTrace();
                 return parseResponse("Error: " + e.getMessage(), false);
             }
         }

         private List<StoryItemModel> parseResponse(String responseString, boolean isSuccess) {
             try {
                 // Parse the response
                 JSONArray storiesArray = new JSONArray(responseString);

                 // Create a list to store StoryModel objects
                 List<StoryItemModel> storyList = new ArrayList<>();

                 // Iterate through the array and create StoryModel objects
                 for (int i = 0; i < storiesArray.length(); i++) {
                     storyList.add(new ModelMapperHelper().getStoryItemModel(storiesArray.getJSONObject(i), StoryItemModel.class));
                 }

                 return storyList;
             } catch (JSONException e) {
                 throw new RuntimeException(e);
             }
         }

         @Override
         protected void onPostExecute(List<StoryItemModel> storyList) {
             // Update UI with the API response
             final RecyclerView recyclerView = binding.recyclerViewStories;
             final StoryAdapter adapter = new StoryAdapter(ListStoriesActivity.this, storyList);
             recyclerView.setAdapter(adapter);
             recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


             // Hide the ProgressBar after the task is complete
             binding.progressBarLoadData.setVisibility(View.GONE);
             recyclerView.setVisibility(View.VISIBLE);
         }
     }
 }