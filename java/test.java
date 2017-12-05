import java.util.Map;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.SimpleTimeZone;
import java.util.UUID;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
//import java.util.SignatureException;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.SignatureException;

//import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

//import com.aliyun.signature.Signature;
//import com.aliyun.signature.The;
//import java.lang.SecretKeySpec;



public class test{
    public static void main(String[]agrs){
        System.out.println("HelloWorld!");
        System.out.println(generateTimestamp());
        System.out.println(generateRandom());
        Map map = new HashMap();
        
        String testAccessKeyId = "testAccessKeyId:En0nONU6WXugT58U";
        String testAccessKeySecret = "testAccessKeySecret:kfbfxIkSvdFBkhzVWeXIu4gPd8at13";
        List<String> list = new LinkedList<String>();
        list.add(testAccessKeyId);
        list.add(testAccessKeySecret);
        String stringToSign = "GET" + getCQS(list) + "&" + percentEncode("/") + "&" + percentEncode("http://vod.cn-shanghai.aliyuncs.com/?TimeStamp=2017-10-10T12:02:54Z&Format=XML&AccessKeyId=testAccessKeyId&Action=GetVideoPlayAuth&SignatureMethod=HMAC-SHA1&SignatureNonce=3ee8c1b8-83d3-44af-a94f-4e0ad82fd6cf&Version=2017-03-21&SignatureVersion=1.0&VideoId=5aed81b74ba84920be578cdfe004af4b");
        System.out.println(stringToSign);
    }
    /*对所有参数名称和参数值做URL编码*/
    public static List<String> getAllParams(Map<String, String> publicParams, Map<String, String> privateParams) {
        List<String> encodeParams = new ArrayList<String>();
        if (publicParams != null) {
            for (String key : publicParams.keySet()) {
                String value = publicParams.get(key);
                //将参数和值都urlEncode一下。
                String encodeKey = percentEncode(key);
                String encodeVal = percentEncode(value);
                encodeParams.add(encodeKey + "=" + encodeVal);
            }
        }
        if (privateParams != null) {
            for (String key : privateParams.keySet()) {
                String value = privateParams.get(key);
                //将参数和值都urlEncode一下。
                String encodeKey = percentEncode(key);
                String encodeVal = percentEncode(value);
                encodeParams.add(encodeKey + "=" + encodeVal);
            }
        }
        return encodeParams;
    }
//
//    /*构造待签名的字符串*/
//    String StringToSign = "GET" + getCQS(list) + "&" + percentEncode("/") + "&" + percentEncode("http://vod.cn-shanghai.aliyuncs.com/?TimeStamp=2017-10-10T12:02:54Z&Format=XML&AccessKeyId=testAccessKeyId&Action=GetVideoPlayAuth&SignatureMethod=HMAC-SHA1&SignatureNonce=3ee8c1b8-83d3-44af-a94f-4e0ad82fd6cf&Version=2017-03-21&SignatureVersion=1.0&VideoId=5aed81b74ba84920be578cdfe004af4b");
    
//    /*特殊字符替换为转义字符*/
    public static String percentEncode(String value) {
        try {
            String urlEncodeOrignStr = URLEncoder.encode(value, "UTF-8");
            String plusReplaced = urlEncodeOrignStr.replace("+", "%20");
            String starReplaced = plusReplaced.replace("*", "%2A");
            String waveReplaced = starReplaced.replace("%7E", "~");
            return waveReplaced;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }
//    /*获取 CanonicalizedQueryString*/
    public static String getCQS(List<String> allParams) {
        System.out.println(allParams);
        ParamsComparator paramsComparator = new ParamsComparator();
        Collections.sort(allParams, paramsComparator);
        String cqString = "";
        for (int i = 0; i < allParams.size(); i++) {
            cqString += allParams.get(i);
            if (i != allParams.size() - 1) {
                cqString += "&";
            }
        }
        return cqString;
    }
//
//    public static byte[] hmacSHA1Signature(String accessKeySecret, String stringToSign) {
//        try {
//            String key = accessKeySecret + "&";
//            try {
//                SecretKeySpec signKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
//                Mac mac = Mac.getInstance("HmacSHA1");
//                mac.init(signKey);
//                return mac.doFinal(stringToSign.getBytes());
//            } catch (Exception e) {
//                throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
//            }
//
//        } catch (SignatureException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /*字符串参数比较器*/
    public static class ParamsComparator implements Comparator<String> {
        @Override
        public int compare(String lhs, String rhs) {
            return lhs.compareTo(rhs);
        }
    }
//
//    public static String newStringByBase64(byte[] bytes) throws UnsupportedEncodingException {
//            if (bytes == null || bytes.length == 0) {
//                return null;
//            }
//        return new String(new BASE64Encoder().encode(bytes));
//    }

    /*生成当前UTC时间戳Time*/
    public static String generateTimestamp() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }

    public static String generateRandom() {
        String signatureNonce = UUID.randomUUID().toString();
        return signatureNonce;
    }
}
