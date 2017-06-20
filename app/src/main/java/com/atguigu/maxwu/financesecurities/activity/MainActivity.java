package com.atguigu.maxwu.financesecurities.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.atguigu.maxwu.financesecurities.R;
import com.gyf.barlibrary.ImmersionBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImmersionBar.with(this).init();
    }
}
