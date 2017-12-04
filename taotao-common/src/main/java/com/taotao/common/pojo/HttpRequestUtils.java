package com.taotao.common.pojo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLDecoder;


public class HttpRequestUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);
    private static DefaultHttpClient client;
    private static HttpPost method;
    private static StringEntity entity;
    private static CloseableHttpResponse result;
    private static Gson gson;
    private static JSONObject object;
    private static HttpGet request;
    private static CloseableHttpResponse response;
    private static String strResult;
    private static JSONObject jsonResult;

    public static JsonObject httpPost(String url, JsonObject jsonParam, boolean noNeedResponse) {
        //post请求返回结果
        client = new DefaultHttpClient();
        jsonResult = null;
        method = new HttpPost(url);

        try {
            if (null != jsonParam) {
                entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            request = new HttpGet(url);
            result = client.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            //请求发送成功并得到相应
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String str = "";
                try {
                    //读取服务器返回来的json字符串数据
                    str = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    //把json字符串转换成json对象
                    object = new JSONObject(str);
                    System.out.println("OBJECT-LIB" + object);
                } catch (Exception e) {
                    logger.error("post请求失败：" + url, e);
                }
            }
        } catch (IOException e) {
            logger.error("post请求失败：" + url, e);
        }
        return httpPost(url, jsonParam, false);
    }

    /*
    httpGet请求
     */
    public static JSONObject httpGet(String url) {
        jsonResult = null;

        try {
            client = new DefaultHttpClient();
            request = new HttpGet(url);
            response = client.execute(request);
            //请求发送成功得到相应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //读取服务器返回过来的json字符串数据
                strResult = EntityUtils.toString(response.getEntity());
                jsonResult = new JSONObject(strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else {
                logger.error("get请求失败:" + url);
            }

        } catch (Exception e) {
            logger.error("get请求失败:" + url, e);
        }
        return jsonResult;
    }

    public static Object httpGetArray(String url) {
        //get请求返回结果
        Object jsonResult = null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**通过读取服务器转换过来的json字符串数据**/
                jsonResult = EntityUtils.toString(response.getEntity());
                /**将转换过后的json字符串添加到集合中*/
                /**adList.add(jsonResult);*/
                /**把json字符串转换成json对象*
                 jsonResult =new JSONArray(strResult);
                 url = URLDecoder.decode(url, "UTF-8");*/
                url = new String(url.getBytes("iso-8859-1"), "UTF-8");
            } else {
                logger.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            logger.error("get请求提交失败:" + url, e);
        }
        return jsonResult;
    }
}
