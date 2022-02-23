package com.clfsys.pojo.page;

/**
 * @author cdy
 * @date 2021/4/4 20:06
 */
public class SectionDetail {

    private int sectionId;
    private String sectionName;
    private String sectionIcon;
    private String sectionDescr;
    private int postId;
    private String postTitle;
    private int postNum;
    private String createTime;


    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionIcon() {
        return sectionIcon;
    }

    public void setSectionIcon(String sectionIcon) {
        this.sectionIcon = sectionIcon;
    }

    public String getSectionDescr() {
        return sectionDescr;
    }

    public void setSectionDescr(String sectionDescr) {
        this.sectionDescr = sectionDescr;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public int getPostNum() {
        return postNum;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "sectionDrtail{" +
                "sectionId=" + sectionId +
                ", sectionName='" + sectionName + '\'' +
                ", sectionIcon='" + sectionIcon + '\'' +
                ", sectionDescr='" + sectionDescr + '\'' +
                ", postId=" + postId +
                ", postTitle='" + postTitle + '\'' +
                ", postNum=" + postNum +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
