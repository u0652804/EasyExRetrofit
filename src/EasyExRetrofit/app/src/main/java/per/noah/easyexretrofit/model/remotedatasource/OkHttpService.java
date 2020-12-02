package per.noah.easyexretrofit.model.remotedatasource;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

public class OkHttpService {
    private OkHttpClient client, client2;

    public OkHttpService(){
        // Create OkHttpClient
        client = new OkHttpClient().newBuilder().build();
    }

    public OkHttpClient getClient(){
        return client;
    }

    public void setClient2(long timeout){
        if (null == client2){
            client2 = new OkHttpClient().newBuilder()
                    .connectTimeout(timeout, TimeUnit.SECONDS)   // 設置連線Timeout
                    .build();
        }
    }

    public OkHttpClient getClient2(){
        return client2;
    }

    public void postWithOkHttp1(String url, OkHttpClient client) {
        Timber.d("postWithOkHttp1");

        FormBody formBody = new FormBody.Builder()
                .add("userId", "1")
                .add("id", "1")
                .add("title", "OkHttp post test")
                .build();

        // Create Req to setup info of connection
        final Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        // create Call
        Call call = client.newCall(request);

        // execute call to conn to url
        executeCall(call);
    }

    public void getWithOkHttp1(String url, OkHttpClient client) {
        Timber.d("getWithOkHttp1");
        // Create Req to setup info of connection
        final Request request = new Request.Builder()
                .url(url)
                .build();

        // create Call
        Call call = client.newCall(request);

        // execute call to conn to url
        executeCall(call);
    }

    private void executeCall(Call call){
        // execute call to conn to url
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // connect success to get data
                String result = response.body().string();
                Timber.d("OKHttp result : ".concat(result));
            }

            @Override
            public void onFailure(Call call, IOException e) {
                // conn fail
                Timber.e("OKHttp connect fail");
            }
        });
    }

    public void getWithOkHttpTimeout(String url, OkHttpClient client) {
        if (null == client){
            Timber.e("Client hasn't init yet");
            return;
        }

        getWithOkHttp1(url, client);
    }
}
