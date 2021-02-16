package ir.e.sujeyab.models;

public class RecyclerModel {
    private String id;
    private String picture;
    private String onvan;
    private String modat_baghimande;
    private String matn_kolase;
    private String motavali;
    public RecyclerModel(String id, String picture, String onvan , String modat_baghimande, String matn_kolase, String motavali) {
        this.id = id;
        this.picture = picture;
        this.onvan = onvan;
        this.modat_baghimande = modat_baghimande;
        this.matn_kolase = matn_kolase;
        this.motavali = motavali;
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
}