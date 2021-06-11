package ir.e.sujeyab.models;

public class CommentsModel {
    private String id;
    private String username;
    private String post_id;
    private String comment;
    private String date_create;
    private String name;
    private String profile_picture;
    private String tedad_pasokh;

    public CommentsModel(String id, String username, String post_id, String comment, String date_create, String name, String profile_picture, String tedad_pasokh) {
        this.id = id;
        this.username = username;
        this.post_id = post_id;
        this.comment = comment;
        this.date_create = date_create;
        this.name = name;
        this.profile_picture = profile_picture;
        this.tedad_pasokh = tedad_pasokh;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPostId() {
        return post_id;
    }

    public String getComment() {
        return comment;
    }

    public String getDate_create() {
        return date_create;
    }

    public String getName() {
        return name;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public String getTedad_pasokh() {
        return tedad_pasokh;
    }
}