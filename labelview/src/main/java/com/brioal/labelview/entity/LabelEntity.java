package com.brioal.labelview.entity;

/**
 * LabelView Entity
 * Created by Brioal on 2016/9/7.
 */
public class LabelEntity {
    private String mTitle;
    private String mContent;

    public LabelEntity(String title, String content) {
        mTitle = title;
        mContent = content;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    @Override
    public boolean equals(Object o) {
        try {
            LabelEntity other = (LabelEntity) o;
            if (getTitle().equals(other.getTitle())&&getContent().equals(other.getContent())) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
