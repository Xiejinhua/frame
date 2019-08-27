package cn.pdc.pos.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pdc.pos.Constant;
import cn.pdc.pos.network.gsonutil.NullBooleanToEmptyAdapterFactory;
import cn.pdc.pos.network.gsonutil.NullDoubleToEmptyAdapterFactory;
import cn.pdc.pos.network.gsonutil.NullIntgerToEmptyAdapterFactory;
import cn.pdc.pos.network.gsonutil.NullStringToEmptyAdapterFactory;
import cn.pdc.pos.network.model.NetWorkCallback;
import cn.pdc.pos.util.ActivityUtil;
import cn.pdc.pos.util.MapSortUtil;
import cn.pdc.pos.util.TimeUtil;
import cn.pdc.pos.viewutil.WaitingDialogUtil;

/**
 * Created by alex on 2017/8/4.
 * 处理网络请求api类
 */

public class APIManagerUtil {

    private static APIManagerUtil apiManagerUtil;


    public static APIManagerUtil getInstance() {

        if (apiManagerUtil == null) {
            apiManagerUtil = new APIManagerUtil();
        }
        return apiManagerUtil;
    }

    private static boolean isFinsh = false;
    private static boolean isCustom = false;


    //不做任何处理的配饰器，传回参数需要自己做处理，等待页面需要自己调起
    public void notDisposeResponse(String url, Map<String, String> params, final NetWorkCallback callback, final Class requestModel) {
        params = getGreenMap(params);
        try {

            NetWorkUtil.getInstance().startPostResponse(url, params, getHeader(), new NetWorkCallback() {
                @Override
                public void onResponse(Object response) {
                    WaitingDialogUtil.cancel();
                    if (response != null) {

                        JSONObject json;
                        try {
                            json = new JSONObject((String) response);
                            Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullDoubleToEmptyAdapterFactory()).registerTypeAdapterFactory(new NullBooleanToEmptyAdapterFactory()).registerTypeAdapterFactory(new NullIntgerToEmptyAdapterFactory()).registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
                            callback.onResponse(gson.fromJson(json.toString(), requestModel));

                        } catch (Exception e) {
                            callback.onFailure(e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(String message) {
                    WaitingDialogUtil.cancel();
                    callback.onFailure(message);
                }

            });
        } catch (Exception e) {
            WaitingDialogUtil.cancel();
            e.printStackTrace();
            callback.onFailure(e.getLocalizedMessage());
        }
    }

    //有弹窗 待处理
    public void startPostResponse(final String url, Map<String, String> params, final NetWorkCallback callback, final Class requestModel) {
        params = getGreenMap(params);
        WaitingDialogUtil.show(ActivityUtil.getActivity());
        try {

            NetWorkUtil.getInstance().startPostResponse(url, params, getHeader(), new NetWorkCallback() {
                @Override
                public void onResponse(Object response) {
                    WaitingDialogUtil.cancel();
                    if (response != null) {
                        try {
                            JSONObject json;
                            json = new JSONObject((String) response);
                            Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullDoubleToEmptyAdapterFactory()).registerTypeAdapterFactory(new NullBooleanToEmptyAdapterFactory()).registerTypeAdapterFactory(new NullIntgerToEmptyAdapterFactory()).registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
                            callback.onResponse(gson.fromJson(json.toString(), requestModel));
                        } catch (Exception e) {
                            callback.onFailure(e.getLocalizedMessage());
                            e.printStackTrace();

                        }
                    } else {
                        callback.onFailure("Data is Empty");
                    }
                }

                @Override
                public void onFailure(String message) {
                    WaitingDialogUtil.cancel();
                    callback.onFailure(message);
                }

            });
        } catch (Exception e) {
            WaitingDialogUtil.cancel();
            e.printStackTrace();
            callback.onFailure(e.getLocalizedMessage());
        }

    }


    public static Map<String, String> getGreenMap(Map<String, String> map) {
        if (!map.containsKey("sign")) {
            String time = TimeUtil.getTimeMillis();
            map.put("_token", Constant.AuthTokenHolder);
            String sign = MapSortUtil.getInstance().getNetWorkMd5String(map, time);
            map.put("time", time);
            map.put("sign", sign);
        }
        return map;
    }

    //统一头部获取
    public static Map<String, String> getHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("content-type", "application/x-www-form-urlencoded; charset=utf-8");
        header.put("Key", Constant.GREEN_APP_KEY);
        return header;
    }
}
