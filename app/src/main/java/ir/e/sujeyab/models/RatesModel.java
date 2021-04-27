package ir.e.sujeyab.models;

public class RatesModel {
    private String id;
    private String username;
    private String post_id;
    private String rate;
    private String date_create;
    private String tedad_rate;
    private String miyangin_rate;

    public RatesModel(String id, String username, String post_id, String rate, String date_create, String tedad_rate, String miyangin_rate) {
        this.id = id;
        this.username = username;
        this.post_id = post_id;
        this.rate = rate;
        this.date_create = date_create;
        this.tedad_rate = tedad_rate;
        this.miyangin_rate = miyangin_rate;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPost_id() {
        return post_id;
    }

    public String getRate() {
        return rate;
    }

    public String getDate_create() {
        return date_create;
    }

    public String getTedad_rate() {
        return tedad_rate;
    }

    public String getMiyangin_rate() {
        return miyangin_rate;
    }
}