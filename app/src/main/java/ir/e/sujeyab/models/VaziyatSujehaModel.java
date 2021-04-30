package ir.e.sujeyab.models;

public class VaziyatSujehaModel {
    private String id;
    private String name_vaziyat_suje_ha;

    public VaziyatSujehaModel(String id, String name_vaziyat_suje_ha) {
        this.id = id;
        this.name_vaziyat_suje_ha = name_vaziyat_suje_ha;
    }

    public String getId() {
        return id;
    }

    public String getName_vaziyat_suje_ha() {
        return name_vaziyat_suje_ha;
    }
}