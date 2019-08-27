package cn.pdc.pos;

/**
 * Created by alex on 11/04/2017.
 */

public class Constant {
    final static public String common_url = "";//服务器域名

    final static public String API_Target_url = common_url + "/app/";

    final static public String md5Key = "C*mXgi19%dxs1_1^s&$~";
    public static final String GREEN_APP_KEY = "RUzvdqO5vqRyZuY19wkdhDJMCrCU9hrK";
    public static String AuthTokenHolder = "";



    //Global Function
    static public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
