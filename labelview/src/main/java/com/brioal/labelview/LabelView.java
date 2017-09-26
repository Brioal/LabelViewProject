package com.brioal.labelview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brioal.labelview.entity.LabelEntity;
import com.brioal.labelview.interfaces.OnLabelSelectedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 以后增加的功能  可拖动 , 可删除, 可动态添加
 * Created by Brioal on 2016/9/7.
 */
public class LabelView extends ViewGroup {
    private List<TextView> mTvs;
    private int mWidth;
    private int mHeight;
    private int mLabelPading = 10;
    private OnLabelSelectedListener mListener;
    private int mColorBGNormal = Color.RED;
    private int mColorBGSelect = Color.RED;
    private int mColorTextNormal = Color.WHITE;
    private int mColorTextSelect = Color.WHITE;
    private int mSelectedIndex = 0;

    public void setSelectedIndex(int selectedIndex) {
        mSelectedIndex = selectedIndex;
        for (int j = 0; j < getChildCount(); j++) {
            TextView tv = (TextView) getChildAt(j);
            if (j == selectedIndex) {
                //选中
                tv.setTextColor(mColorTextSelect);
                tv.getBackground().setColorFilter(mColorBGSelect, PorterDuff.Mode.SRC_IN);
            } else {
                //未选中
                tv.setTextColor(mColorTextNormal);
                tv.getBackground().setColorFilter(mColorBGNormal, PorterDuff.Mode.SRC_IN);
            }
        }
    }

    public int getSelectedIndex() {
        return mSelectedIndex;
    }

    public LabelView(Context context) {
        super(context);
    }

    public LabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setColorBGNormal(int colorBGNormal) {
        mColorBGNormal = colorBGNormal;
    }

    public void setColorBGSelect(int colorBGSelect) {
        mColorBGSelect = colorBGSelect;
    }

    public void setColorTextNormal(int colorTextNormal) {
        mColorTextNormal = colorTextNormal;
    }

    public void setColorTextSelect(int colorTextSelect) {
        mColorTextSelect = colorTextSelect;
    }

    public void setListener(OnLabelSelectedListener listener) {
        mListener = listener;
    }

    public void setLabelPading(int labelPading) {
        mLabelPading = labelPading;
    }

    public void setLabels(final List<LabelEntity> labels) {
        removeAllViews();
        mTvs = new ArrayList<>();
        for (int i = 0; i < labels.size(); i++) {
            final TextView tv = new TextView(getContext());
            tv.setBackgroundResource(R.drawable.label_bg);
            tv.setPadding(mLabelPading * 2, mLabelPading, mLabelPading * 2, mLabelPading);
            tv.setTextColor(mColorTextNormal);
            tv.setText(labels.get(i).getTitle());
            tv.getBackground().setColorFilter(mColorBGNormal, PorterDuff.Mode.SRC_IN);
            if (mSelectedIndex == i) {
                //选中
                tv.setTextColor(mColorTextSelect);
                tv.getBackground().setColorFilter(mColorBGSelect, PorterDuff.Mode.SRC_IN);
            } else {
                //未选中
                tv.setTextColor(mColorTextNormal);
                tv.getBackground().setColorFilter(mColorBGNormal, PorterDuff.Mode.SRC_IN);
            }
            final int finalI = i;
            tv.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (mListener != null) {
                        mListener.longClick(finalI, labels.get(finalI).getContent());
                    }
                    return true;
                }
            });
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedIndex = finalI;
                    for (int j = 0; j < labels.size(); j++) {
                        TextView tv = (TextView) getChildAt(j);
                        if (j == finalI) {
                            //选中
                            tv.setTextColor(mColorTextSelect);
                            tv.getBackground().setColorFilter(mColorBGSelect, PorterDuff.Mode.SRC_IN);
                        } else {
                            //未选中
                            tv.setTextColor(mColorTextNormal);
                            tv.getBackground().setColorFilter(mColorBGNormal, PorterDuff.Mode.SRC_IN);
                        }
                    }
                    if (mListener != null) {
                        mListener.selected(finalI, labels.get(finalI).getContent());
                    }
                }
            });
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            addView(tv, params);
            mTvs.add(tv);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        int count = 0;
        int w = 0;
        for (int i = 0; i < getChildCount(); i++) {
            TextView tv = (TextView) getChildAt(i);
            measureChild(tv, widthMeasureSpec, heightMeasureSpec);
            if (w + tv.getMeasuredWidth() + tv.getPaddingLeft() + tv.getPaddingRight() > mWidth) {
                count++;
                w = 0;
            }
            w += tv.getMeasuredWidth();
        }
        if (w > 0) {
            count++;
        }
        System.out.println("Count" + count);
        setMeasuredDimension(mWidth, count * (mLabelPading * 2 + getChildAt(0).getMeasuredHeight()));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int startX = mLabelPading;
        int startY = mLabelPading;
        for (int i = 0; i < getChildCount(); i++) {
            TextView tv = (TextView) getChildAt(i);
            if (startX + tv.getMeasuredWidth() > mWidth) {
                startX = mLabelPading;
                startY += mLabelPading * 2 + tv.getMeasuredHeight();
            }
            tv.layout(startX, startY, startX + tv.getMeasuredWidth(), startY + tv.getMeasuredHeight());
            startX += tv.getMeasuredWidth() + mLabelPading;
        }
    }
}
