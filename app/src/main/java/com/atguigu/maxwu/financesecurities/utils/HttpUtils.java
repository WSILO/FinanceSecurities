package com.atguigu.maxwu.financesecurities.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Map;

/**
 * 作者: WuKai
 * 时间: 2017/6/21
 * 邮箱: 648838173@qq.com
 * 作用:
 */

public class HttpUtils {

    private static HttpUtils httpUtils;
    private AsyncHttpClient client;
    private AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int statuCode, String content) {
            super.onSuccess(content);
            if (listener != null) {
                if (statuCode == 200) {
                    listener.onSuccess(content);
                } else {
                    listener.onSuccess(null);
                }
            }
        }

        @Override
        public void onFailure(Throwable error, String content) {
            super.onFailure(error, content);
            if (listener != null) {
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

    public void get(String url, CallBackListener listener) {
        this.listener = listener;
        client.get(url, handler);
    }

    public void post(String url, Map<String, String> map, CallBackListener listener) {
        this.listener = listener;
        RequestParams params = new RequestParams(map);
        client.post(url, params, handler);
    }

    public interface CallBackListener {

        void onSuccess(String content);

        void onFailure(String content);
    }

}
