package com.brioal.labelviewproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.brioal.labelview.LabelView;
import com.brioal.labelview.entity.LabelEntity;
import com.brioal.labelview.interfaces.OnLabelSelectedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    LabelView mLabelView;
    List<LabelEntity> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLabelView = (LabelView) findViewById(R.id.main_label);
        mLabelView.setListener(new OnLabelSelectedListener() {
            @Override
            public void selected(int position, String content) {
                Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void longClick(int position, String content) {
                Toast.makeText(MainActivity.this, "长按:" + content, Toast.LENGTH_SHORT).show();
            }
        });
        mLabelView.setColorBGNormal(Color.RED);
        mLabelView.setColorBGSelect(Color.GREEN);
        mLabelView.setColorTextNormal(Color.GREEN);
        mLabelView.setColorTextSelect(Color.RED);
        mList = new ArrayList<>();
        mList.add(new LabelEntity("+", "的钱我的我"));
        mLabelView.setSelectedIndex(2);
        mLabelView.setLabels(mList);
    }

    public void addLabel(View view) {
        int count = new Random().nextInt(8);
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < count; i++) {
            buffer.append((char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1))));
        }
        String str = buffer.toString();
        mList.add(new LabelEntity(str, str));
        mLabelView.setSelectedIndex(2);
        mLabelView.setLabels(mList);
    }

    public void select(View view) {
        int count = mList.size() / 2;
        List<LabelEntity> selecteds = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            selecteds.add(mList.get(new Random().nextInt(count*2)));
        }
        mLabelView.setSelectedLabels(selecteds);
    }
}
