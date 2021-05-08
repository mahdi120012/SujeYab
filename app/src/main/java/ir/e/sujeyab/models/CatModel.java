package ir.e.sujeyab.models;

public class CatModel {
    private String id;
    private String cat;
    private String main_cat;
    private String picture;
    private String tedad_mataleb;

    public CatModel(String id, String cat, String main_cat, String picture, String tedad_mataleb) {
        this.id = id;
        this.cat = cat;
        this.main_cat = main_cat;
        this.picture = picture;
        this.tedad_mataleb = tedad_mataleb;
    }

    public String getId() {
        return id;
    }

    public String getCat() {
        return cat;
    }

    public String getMain_cat() {
        return main_cat;
    }

    public String getPicture() {
        return picture;
    }

    public String getTedad_mataleb() {
        return tedad_mataleb;
    }
}