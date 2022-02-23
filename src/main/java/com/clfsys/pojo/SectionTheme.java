package com.clfsys.pojo;

/**
 * @author cdy
 * @date 2021/2/4 19:33
 */
public class SectionTheme {
    private  int themeId;
    private int sectionId;
    private String themeName;

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    @Override
    public String toString() {
        return "SectionTheme{" +
                "themeId=" + themeId +
                ", sectionId=" + sectionId +
                ", themeName='" + themeName + '\'' +
                '}';
    }
}
