package ir.e.sujeyab.models;

public class FarakhanVijehModel {
    private String id;
    private String picture;
    private String onvan;
    private String modat_baghimande;
    private String matn_kholase;
    private String mozo;
    private String id_ferestande;
    private String motavali;
    private String type;
    private String type_vaziyat_farakhan;

    public FarakhanVijehModel(String id, String picture, String onvan, String modat_baghimande, String matn_kholase, String mozo, String id_ferestande, String motavali, String type, String type_vaziyat_farakhan) {
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
}