package ir.e.sujeyab.models;

public class MediaModel {
    private String id;
    private String postId;
    private String mediaUrl;
    private String tozihat;

    public MediaModel(String id, String postId, String mediaUrl, String tozihat) {
        this.id = id;
        this.postId = postId;
        this.mediaUrl = mediaUrl;
        this.tozihat = tozihat;
    }

    public String getId() {
        return id;
    }

    public String getPostId() {
        return postId;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public String getTozihat() {
        return tozihat;
    }
}
