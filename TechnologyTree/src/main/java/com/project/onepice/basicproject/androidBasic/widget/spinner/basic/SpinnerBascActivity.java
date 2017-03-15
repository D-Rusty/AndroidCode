package com.project.onepice.basicproject.androidBasic.widget.spinner.basic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.onepice.basicproject.R;

/**
 * Created by onepice2015 on 2016/12/18.
 *
 * 快捷键 ctrl+o 重写父类方法
 */

public class SpinnerBascActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner basic_spinner;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.project.onepice.basicproject.R.layout.activity_spinner_basc);
        basic_spinner = (Spinner) findViewById(R.id.basic_spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_basic_array,
                android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        basic_spinner.setAdapter(arrayAdapter);
        basic_spinner.setOnItemSelectedListener(this);
        basic_spinner.setPrompt("地球");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(SpinnerBascActivity.this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
