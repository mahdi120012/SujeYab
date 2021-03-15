package ir.e.sujeyab.models;

import com.google.gson.annotations.SerializedName;

public class FarakhanVijehModel {
    private String id;
    @SerializedName("p1")
    private String picture;
    private String onvan;
    private String modat_baghimande;
    private String matn_kholase;
    private String mozo;
    private String id_ferestande;
    private String motavali;
    private String type;
    private String type_vaziyat_farakhan;
    private String name_family;
    private String semat_shoghli;
    private String date_create;

    public FarakhanVijehModel(String id, String picture, String onvan, String modat_baghimande, String matn_kholase, String mozo, String id_ferestande,
                              String motavali, String type, String type_vaziyat_farakhan, String name_family, String semat_shoghli, String date_create) {
        this.id = id;
        this.picture = picture;
        this.onvan = onvan;
        this.modat_baghimande = modat_baghimande;
        this.matn_kholase = matn_kholase;
        this.mozo = mozo;
        this.id_ferestande = id_ferestande;
        this.motavali = motavali;
        this.type = type;
        this.type_vaziyat_farakhan = type_vaziyat_farakhan;
        this.name_family = name_family;
        this.semat_shoghli = semat_shoghli;
        this.date_create = date_create;
    }

    public String getId() {
        return id;
    }

    public String getPicture() {
        return picture;
    }

    public String getOnvan() {
        return onvan;
    }

    public String getModat_baghimande() {
        return modat_baghimande;
    }

    public String getMatn_kholase() {
        return matn_kholase;
    }

    public String getMozo() {
        return mozo;
    }

    public String getId_ferestande() {
        return id_ferestande;
    }

    public String getMotavali() {
        return motavali;
    }

    public String getType() {
        return type;
    }

    public String getType_vaziyat_farakhan() {
        return type_vaziyat_farakhan;
    }

    public String getName_family() {
        return name_family;
    }

    public String getSemat_shoghli() {
        return semat_shoghli;
    }

    public String getDate_create() {
        return date_create;
    }
}