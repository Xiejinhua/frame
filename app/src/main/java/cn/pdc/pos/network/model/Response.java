package cn.pdc.pos.network.model;

/**
 * Created by alex on 2017/8/4.
 * 请求响应的基类，这里封装了所有请求都必须会响应的参数
 */

public class Response {
    /**
     * 请求结果
     */
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
