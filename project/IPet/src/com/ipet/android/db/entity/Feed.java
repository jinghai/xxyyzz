package com.ipet.android.db.entity;

public class Feed {

    public Integer id;

    public String content;

    public String createdBy;

    public String createdAt;

    public String imgUrl;

    public Feed(String content, String createdBy, String createdAt, String imgUrl) {
        super();
        this.id = null;
        this.content = content;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.imgUrl = imgUrl;
    }

    public Feed(Integer id, String content, String createdBy, String createdAt, String imgUrl) {
        super();
        this.id = id;
        this.content = content;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.imgUrl = imgUrl;
        new Feed(content, createdBy, createdAt, imgUrl);
    }

    @Override
    public String toString() {
        return "Feed [id=" + id + ", content=" + content + ", createdBy="
                + createdBy + ", createdAt="
                + createdAt + ", imgUrl=" + imgUrl + "]";
    }

}
