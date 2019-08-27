package cn.pdc.pos.network.model;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pdc.pos.Constant;
import cn.pdc.pos.network.APIManagerUtil;

/**
 * @author alex
 * @since 2018/8/27
 */


public class TestModel extends Response {


    private static final String url = Constant.API_Target_url + "";

    //请求数据
    public static void getResponse(NetWorkCallback netWorkCallback) {
        Map<String, String> map = new HashMap<>();
        APIManagerUtil.getInstance().startPostResponse(url, map, netWorkCallback, TestModel.class);

    }

    //取消请求
    public static void cancelResponse() {
        RequestCall call = OkHttpUtils.get().url(url).build();
        call.cancel();
    }
}
