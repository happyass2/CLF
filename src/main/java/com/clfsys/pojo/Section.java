package com.clfsys.pojo;

/**
 * @author cdy
 * @date 2021/2/3 22:19
 */
public class Section {
    private int sectionId;
    private String sectionName;
    private String sectionDescribe;
    private String sectionIcon;
    private String sectionRule;

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

    public String getSectionDescribe() {
        return sectionDescribe;
    }

    public void setSectionDescribe(String sectionDescribe) {
        this.sectionDescribe = sectionDescribe;
    }

    public String getSectionIcon() {
        return sectionIcon;
    }

    public void setSectionIcon(String sectionIcon) {
        this.sectionIcon = sectionIcon;
    }

    public String getSectionRule() {
        return sectionRule;
    }

    public void setSectionRule(String sectionRule) {
        this.sectionRule = sectionRule;
    }

    @Override
    public String toString() {
        return "Section{" +
                "sectionId=" + sectionId +
                ", sectionName='" + sectionName + '\'' +
                ", sectionDescribe='" + sectionDescribe + '\'' +
                ", sectionIcon='" + sectionIcon + '\'' +
                ", sectionRule='" + sectionRule + '\'' +
                '}';
    }
}
