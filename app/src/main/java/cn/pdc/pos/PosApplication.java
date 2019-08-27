package cn.pdc.pos;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.zhy.http.okhttp.OkHttpUtils;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Xie on 2019/8/27.
 */

public class PosApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    @Override
    public void onCreate() {
        super.onCreate();
//        registerActivityLifecycleCallbacks(new MyLifecycleHandlerUtil());
        OkHttpUtils.getInstance().getOkHttpClient().setSslSocketFactory(createSSLSocketFactory());
        OkHttpUtils.getInstance().getOkHttpClient().setConnectTimeout(200000, TimeUnit.MILLISECONDS);
        OkHttpUtils.getInstance().getOkHttpClient().setReadTimeout(100000, TimeUnit.MILLISECONDS);
        OkHttpUtils.getInstance().getOkHttpClient().setWriteTimeout(100000, TimeUnit.MILLISECONDS);

    }


    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()
            }, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }

    public static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}
