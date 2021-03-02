package ir.e.sujeyab.models;

import android.media.Image;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SliderModel {
    @SerializedName("id")
    @Expose

    private String id;
    private String picture;
    private String link;
    private String description;
    private String matn_kholase;

    public SliderModel(String id, String picture, String link , String description, String matn_kholase) {
        this.id = id;
        this.picture = picture;
        this.link = link;
        this.description = description;
        this.matn_kholase = matn_kholase;
    }

    public String getId() {
        return id;
    }

    public String getPicture() {
        return picture;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getMatn_kholase() {return matn_kholase; }

}