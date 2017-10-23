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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LabelEntity that = (LabelEntity) o;

        if (mTitle != null ? !mTitle.equals(that.mTitle) : that.mTitle != null) return false;
        return mContent != null ? mContent.equals(that.mContent) : that.mContent == null;

    }

    @Override
    public int hashCode() {
        int result = mTitle != null ? mTitle.hashCode() : 0;
        result = 31 * result + (mContent != null ? mContent.hashCode() : 0);
        return result;
    }
}
