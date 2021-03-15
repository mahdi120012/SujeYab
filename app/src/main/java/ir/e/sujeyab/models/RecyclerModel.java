package ir.e.sujeyab.models;

import com.google.gson.annotations.SerializedName;

public class RecyclerModel {
    private String id;
    private String picture;
    private String onvan;
    private String modat_baghimande;
    private String matn_kolase;
    private String motavali;
    private String date_create;
    private String name_family;
    private String semat_shoghli;
    public RecyclerModel(String id, String picture, String onvan , String modat_baghimande, String matn_kolase, String motavali, String date_create, String name_family, String semat_shoghli) {
        this.id = id;
        this.picture = picture;
        this.onvan = onvan;
        this.modat_baghimande = modat_baghimande;
        this.matn_kolase = matn_kolase;
        this.motavali = motavali;
        this.motavali = date_create;
        this.name_family = name_family;
        this.semat_shoghli = semat_shoghli;
    }

    public String getId() {
        return id;
    }

    public String getOnvan() {
        return onvan;
    }

    public String getModat_baghimande() {
        return modat_baghimande;
    }

    public String getMatn_kolase() {
        return matn_kolase;
    }

    public String getPicture() {
        return picture;
    }

    public String getMotavali() {
        return motavali;
    }

    public String getDate_create() {
        return date_create;
    }

    public String getName_family() {
        return name_family;
    }

    public String getSemat_shoghli() {
        return semat_shoghli;
    }
}