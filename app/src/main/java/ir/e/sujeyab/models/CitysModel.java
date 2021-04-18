package ir.e.sujeyab.models;

import com.google.gson.annotations.SerializedName;

public class CitysModel {
    private String id;
    private String name;

    public CitysModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}