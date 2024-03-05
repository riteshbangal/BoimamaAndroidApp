package in.boimama.app.android.model;

import java.util.List;

public class UserDataModel {

    private String displayName;

    private String profileImageUrl;

    private List<StoryItemModel> likedStories;

    private List<StoryItemModel> savedStories;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public List<StoryItemModel> getLikedStories() {
        return likedStories;
    }

    public void setLikedStories(List<StoryItemModel> likedStories) {
        this.likedStories = likedStories;
    }

    public List<StoryItemModel> getSavedStories() {
        return savedStories;
    }

    public void setSavedStories(List<StoryItemModel> savedStories) {
        this.savedStories = savedStories;
    }

    @Override
    public String toString() {
        return "UserDataModel{" +
                "displayName='" + displayName + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", likedStories=" + likedStories +
                ", savedStories=" + savedStories +
                '}';
    }
}
