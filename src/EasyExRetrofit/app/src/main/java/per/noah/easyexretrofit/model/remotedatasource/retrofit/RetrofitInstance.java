package per.noah.easyexretrofit.model.remotedatasource.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static long TIME_OUT_SEC = 3;
    private static String BASE_URL_TODOS = "https://jsonplaceholder.typicode.com/";

    public static RestApiService getApiService(){
        if (null == retrofit){
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .connectTimeout(TIME_OUT_SEC, TimeUnit.SECONDS)   // 設置連線Timeout
                    .build();
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL_TODOS)
                    .build();
        }
        return retrofit.create(RestApiService.class);
    }
}
