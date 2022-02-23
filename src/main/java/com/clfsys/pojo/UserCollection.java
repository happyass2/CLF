package com.clfsys.pojo;

import java.util.Date;

/**
 * @author cdy
 * @date 2021/2/4 2:10
 */
public class UserCollection {
    private int collectionId;
    private int postId;
    private Date collectDatetime;
    private int collectorId;

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Date getCollectDatetime() {
        return collectDatetime;
    }

    public void setCollectDatetime(Date collectDatetime) {
        this.collectDatetime = collectDatetime;
    }

    public int getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(int collectorId) {
        this.collectorId = collectorId;
    }

    @Override
    public String toString() {
        return "UserCollection{" +
                "collectionId=" + collectionId +
                ", postId=" + postId +
                ", collectDatetime=" + collectDatetime +
                ", collectorId=" + collectorId +
                '}';
    }
}
