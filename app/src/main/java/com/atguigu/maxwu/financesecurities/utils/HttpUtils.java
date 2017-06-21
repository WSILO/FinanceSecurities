package com.atguigu.maxwu.financesecurities.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * 作者: WuKai
 * 时间: 2017/6/21
 * 邮箱: 648838173@qq.com
 * 作用:
 */

public class HttpUtils {

    private static HttpUtils httpUtils;
    private AsyncHttpClient client;
    private AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler(){
        @Override
        public void onSuccess(String content) {
            super.onSuccess(content);
            if(listener != null) {
                listener.onSuccess(content);
            }
        }

        @Override
        public void onFailure(Throwable error, String content) {
            super.onFailure(error, content);
            if(listener != null) {
                listener.onFailure(content);
            }
        }
    };
    private CallBackListener listener;

    private HttpUtils() {
        client = new AsyncHttpClient();
    }

    public static HttpUtils getInstance() {
        if (httpUtils == null) {
            synchronized (HttpUtils.class) {
                if (httpUtils == null) {
                    httpUtils = new HttpUtils();
                }
            }
        }
        return httpUtils;
    }

    public void get(String url, CallBackListener listener){
        this.listener = listener;
        client.get(url, handler);
    }

    public interface CallBackListener{

        void onSuccess(String content);

        void onFailure(String content);
    }

}
