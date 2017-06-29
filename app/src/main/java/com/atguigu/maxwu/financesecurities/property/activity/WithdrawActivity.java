package com.atguigu.maxwu.financesecurities.property.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.maxwu.financesecurities.R;
import com.atguigu.maxwu.financesecurities.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class WithdrawActivity extends BaseActivity {

    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.account_zhifubao)
    TextView accountZhifubao;
    @BindView(R.id.select_bank)
    RelativeLayout selectBank;
    @BindView(R.id.chongzhi_text)
    TextView chongzhiText;
    @BindView(R.id.et_input_money)
    EditText etInputMoney;
    @BindView(R.id.chongzhi_text2)
    TextView chongzhiText2;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.btn_tixian)
    Button btnTixian;


    @OnClick({R.id.ib_back, R.id.btn_tixian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                finish();
                break;
            case R.id.btn_tixian:
                showToast("提现成功");
                break;
        }
    }

    @Override
    protected void initListener() {
        etInputMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    btnTixian.setEnabled(false);
                    btnTixian.setBackgroundResource(R.drawable.btn_02);
                } else {
                    btnTixian.setEnabled(true);
                    btnTixian.setBackgroundResource(R.drawable.btn_01);
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tvTitle.setText("提现");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_withdraw;
    }
}
