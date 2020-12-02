package per.noah.easyexretrofit;

import android.util.Log;

import org.junit.Test;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void testOkHttpGet1(){
        // Create OkHttpClient
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        // Create Req to setup info of connection
        final Request request = new Request.Builder()
                .url("https://jsonplaceholder.typicode.com/albums/1")
                .build();

        // create Call
        Call call = client.newCall(request);

        // execute call to conn to url
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // connect success to get data
                String result = response.body().string();
                showInfo("OKHttp result : ".concat(result));
            }

            @Override
            public void onFailure(Call call, IOException e) {
                // conn fail
                showInfo("OKHttp connect fail");
            }
        });
    }

    public void showInfo(String msg){
//        Log.d(TAG, msg);
        System.out.println(msg);
    }
}