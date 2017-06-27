package com.atguigu.maxwu.financesecurities.property.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.maxwu.financesecurities.R;
import com.atguigu.maxwu.financesecurities.activity.LoginActivity;
import com.atguigu.maxwu.financesecurities.base.BaseActivity;
import com.atguigu.maxwu.financesecurities.bean.LoginBean;
import com.atguigu.maxwu.financesecurities.common.AppManager;
import com.atguigu.maxwu.financesecurities.common.NetConfig;
import com.atguigu.maxwu.financesecurities.utils.BitmapUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

public class SettingsActivity extends BaseActivity {

    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ib_settings)
    ImageButton ibSettings;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @BindView(R.id.tv_user_change)
    TextView tvUserChange;
    @BindView(R.id.btn_user_logout)
    Button btnUserLogout;
    private Transformation transformation;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        String image = getImage();
        if(TextUtils.isEmpty(image)) {
            LoginBean user = getUser();
            if (user != null) {
                String imageUrl = user.getData().getImageurl().substring(33);
                Picasso.with(this).load(NetConfig.BASE_URL + imageUrl).transform(transformation).into(ivUserIcon);
            }
        }else {
            Picasso.with(this).load(new File(image)).transform(transformation).into(ivUserIcon);
        }

    }

    @Override
    protected void initView() {
        transformation = new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                return BitmapUtils.getBitmap(source);
            }

            @Override
            public String key() {
                return "cir";
            }
        };
        tvTitle.setText("设置");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_settings;
    }

    @OnClick({R.id.tv_user_change, R.id.ib_back, R.id.btn_user_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_user_change:
                new AlertDialog.Builder(this).setItems(new String[]{"相机", "相册"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                showCamera();
                                break;
                            case 1:
                                showPhoto();
                                break;
                        }
                    }
                }).show();
                break;
            case R.id.ib_back:
                finish();
                break;
            case R.id.btn_user_logout:
                clearSp();
                AppManager.getInstance().removeAll();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

    private void showPhoto() {
        GalleryFinal.openGallerySingle(02, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                makeImage(resultList.get(0).getPhotoPath());
            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {

            }
        });
    }

    private void showCamera() {
        GalleryFinal.openCamera(01, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                makeImage(resultList.get(0).getPhotoPath());
            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {

            }
        });
    }

    private void makeImage(String photoPath) {
        Picasso.with(this).load(new File(photoPath)).transform(transformation).into(ivUserIcon);
        saveImage(photoPath);
    }

}
