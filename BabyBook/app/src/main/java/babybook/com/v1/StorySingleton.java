package babybook.com.v1;

import java.util.*;

public class StorySingleton {



    public static StorySingleton storyObj = null;
    public List<Story> storyList = new ArrayList<Story>();

    private StorySingleton(){}

    public static StorySingleton getInstance(){

        if (storyObj == null){
            storyObj = new StorySingleton();
        }

        return storyObj;
    }

    public void addStory(Story story){
        storyList.add(story);
    }

    public List<Story> getStoryList(){

        return storyList;
    }

    public Story getStory(int id){

        return storyList.get(id);
    }

    public int getNumberOfStories(){

        return storyList.size();
    }

}
