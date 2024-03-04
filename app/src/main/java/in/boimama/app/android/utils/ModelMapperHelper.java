package in.boimama.app.android.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.boimama.app.android.model.StoryItemModel;

public class ModelMapperHelper {

    public <S, D> StoryItemModel getStoryItemModel(JSONObject pStoryJsonObject, Class<D> storyItemModel) throws JSONException { // TODO: Fix the Generic type
        // Parse the JSON string
        JSONObject storyObject = pStoryJsonObject;

        // Extract data from the JSON object
        String id = storyObject.getString("id");
        String title = storyObject.getString("title");
        // String author = storyObject.getString("authorNames");
        String content = storyObject.getString("content");
        String category = storyObject.getString("category");
        String publishedDate = storyObject.getString("publishedDate");
        String rating = storyObject.getString("rating");
        String lengthInMins = storyObject.getString("lengthInMins");
        String imagePath = storyObject.getString("imagePath");
        // Parse the "authorNames" array
        List<String> authorNames = new ArrayList<>();
        JSONArray authorNamesArray = storyObject.getJSONArray("authorNames");
        for (int j = 0; j < authorNamesArray.length(); j++) {
            authorNames.add(authorNamesArray.getString(j));
        }

        // Parse the "authorIds" array
        List<String> authorIds = new ArrayList<>();
        JSONArray authorIdsArray = storyObject.getJSONArray("authorIds");
        for (int j = 0; j < authorIdsArray.length(); j++) {
            authorIds.add(authorIdsArray.getString(j));
        }


        // Create a StoryItemModel object and add it to the list
        StoryItemModel story = new StoryItemModel();
        story.setId(id);
        story.setTitle(title);
        story.setContent(content);
        story.setAuthor(authorNames.get(0));
        story.setPublishedDate(publishedDate);
        story.setLength(Integer.parseInt(lengthInMins));
        story.setNumberOfLikes(Integer.parseInt(rating));
        story.setStoryImageUrl("https://api-gw-dev.boimama.in" + imagePath);
        story.setAuthorImageUrl("https://api-gw-dev.boimama.in/author/" + authorIds.get(0) + "/image");

        return story;
    }
}
