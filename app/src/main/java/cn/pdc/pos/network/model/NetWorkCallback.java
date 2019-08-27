package cn.pdc.pos.network.model;

/**
 * Created by davy on 2017/8/4.
 * 网络请求响应的回调接口
 */

public interface NetWorkCallback<T extends Object> {
    void onResponse(T response);

    void onFailure(String message);
}
