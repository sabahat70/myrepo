package babybook.com.v1;


public class Story {

    private int storyid;
    private String date,title,description;
    private String imagePath;

    public Story (int id, String date, String title, String imagePath, String description){

        this.storyid = id;
        this.date = date;
        this.title = title;
        this.imagePath = imagePath;
        this.description = description;
    }

    public int getId(){

        return this.storyid;

    }

    public void setDate(String date){

        this.date = date;
    }

    public String getDate(){

        return date;
    }


    public void setTitle(String title){

        this.title = title;
    }

    public String getTitle(){

        return title;
    }

    public void setImagePath(String imagePath){

        this.imagePath = imagePath;

    }

    public String getImagePath (){

        return imagePath;
    }

    public void setDescription(String description){

        this.description = description;
    }

    public String getDescription(){

        return description;
    }


}
