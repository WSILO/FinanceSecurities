package com.atguigu.maxwu.financesecurities.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.atguigu.maxwu.financesecurities.R;
import com.atguigu.maxwu.financesecurities.common.AppManager;
import com.atguigu.maxwu.financesecurities.home.homefragment.HomeFragment;
import com.atguigu.maxwu.financesecurities.invest.investfragment.InvestFragment;
import com.atguigu.maxwu.financesecurities.morefragment.MoreFragment;
import com.atguigu.maxwu.financesecurities.propertyfragment.PropertyFragment;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rgMain;
    private HomeFragment homeFragment;
    private MoreFragment moreFragment;
    private InvestFragment investFragment;
    private PropertyFragment propertyFragment;
    private boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();
        AppManager.getInstance().addActivity(this);

    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switchFragment(i);
            }
        });
    }

    private void switchFragment(int i) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hiddenFragment(ft);
        switch (i) {
            case R.id.rb_home:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.fl_main, homeFragment);
                } else {
                    ft.show(homeFragment);
                }
                break;
            case R.id.rb_invest:
                if (investFragment == null) {
                    investFragment = new InvestFragment();
                    ft.add(R.id.fl_main, investFragment);
                } else {
                    ft.show(investFragment);
                }
                break;
            case R.id.rb_property:
                if (propertyFragment == null) {
                    propertyFragment = new PropertyFragment();
                    ft.add(R.id.fl_main, propertyFragment);
                } else {
                    ft.show(propertyFragment);
                }
                break;
            case R.id.rb_more:
                if (moreFragment == null) {
                    moreFragment = new MoreFragment();
                    ft.add(R.id.fl_main, moreFragment);
                } else {
                    ft.show(moreFragment);
                }
                break;
        }
        ft.commit();
    }

    //将添加后的fragment都设置为隐藏
    private void hiddenFragment(FragmentTransaction ft) {
        if (investFragment != null) {
            ft.hide(investFragment);
        }
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (propertyFragment != null) {
            ft.hide(propertyFragment);
        }
        if (moreFragment != null) {
            ft.hide(moreFragment);
        }
    }

    private void initData() {

    }

    private void initView() {
        setContentView(R.layout.activity_main);
        rgMain = instance(R.id.rg_main);
        switchFragment(R.id.rb_home);
    }

    public <T> T instance(int id) {
        return (T) findViewById(id);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && !isExit) {
            isExit = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
            Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }
}
