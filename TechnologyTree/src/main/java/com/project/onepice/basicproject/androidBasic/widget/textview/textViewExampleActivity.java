package com.project.onepice.basicproject.androidBasic.widget.textview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.project.onepice.basicproject.R;

/**
 * Created by onepice2015 on 2016/12/20.
 *
 * textView 练习
 *
 */

public class textViewExampleActivity extends AppCompatActivity {
     private TextView text_html_example;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview_example);
        text_html_example= (TextView) findViewById(R.id.text_html_example);
        //1.Html.fromHtml
        text_html_example.setText(Html.fromHtml("北京市发布霾黄色预警，<font color='#ff0000'><big><big>外出携带好</big></big></font>口罩"));
    }
}





