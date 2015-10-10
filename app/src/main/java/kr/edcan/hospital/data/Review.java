package kr.edcan.hospital.data;

/**
 * Created by Marzin-Oh on 2015-10-10.
 */
public class Review {
    private String title;
    private String description;
    public Review(String title, String description){
        this.title = title;
        this.description = description;
    }
    public String getTitle(){
        return this.title;
    }
    public String getDescription(){
        return this.description;
    }
}
