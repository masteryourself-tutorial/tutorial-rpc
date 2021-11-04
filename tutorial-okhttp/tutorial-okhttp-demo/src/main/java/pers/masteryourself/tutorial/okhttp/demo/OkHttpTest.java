package pers.masteryourself.tutorial.okhttp.demo;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.concurrent.TimeUnit;

/**
 * <p>description : OkHttpTest
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2021/9/28 15:03
 */
public class OkHttpTest {

    public static void main(String[] args) throws Exception {
        okhttp3.OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(4, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
        Request request = new Request.Builder().url("http://127.0.0.1:8081/basic/ping").build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        System.out.println(response.body().string());
    }

}
