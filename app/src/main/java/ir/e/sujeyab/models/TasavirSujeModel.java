package ir.e.sujeyab.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TasavirSujeModel {

    private String p1;

    public TasavirSujeModel(String p1) {
        this.p1 = p1;
    }

    public String getP1() {
        return p1;
    }
}