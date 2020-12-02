package per.noah.easyexretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

import per.noah.easyexretrofit.datamodel.Todos;
import per.noah.easyexretrofit.model.remotedatasource.OkHttpService;
import per.noah.easyexretrofit.model.remotedatasource.retrofit.TodosRepository;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private OkHttpService okHttpService = new OkHttpService();
    // declare test url
    // for okhttp
    private final static String URL1 = "https://jsonplaceholder.typicode.com/todos/1";
    private final static String URL2 = "https://jsonplaceholder.typicode.com/posts";
    private final static String URL3 = "https://noThisUrl";
    // for retrofit
    private TodosRepository todosRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Until().initTimber(Until.enableTimber);
        testOkHttp();
        testRetrofit();
    }

    private void testOkHttp(){
        // test no timeout get, post with okhttp
        okHttpService.getWithOkHttp1(URL1, okHttpService.getClient());
        okHttpService.postWithOkHttp1(URL2, okHttpService.getClient());

        // test timeout get with okhttp
        okHttpService.setClient2(3);
        okHttpService.getWithOkHttpTimeout(URL3, okHttpService.getClient2());
    }

    private void testRetrofit(){
        // create dataModel(s) : one of key is jsonObject or jsonArray
        // interface RestApiService : define APIService as a url router part of retrofit
        // RetrofitInstance : init retrofit and .create(RestApiService), can get RestApiService from here
        // Create TodosRepository(dataModelRepository) : Use RetrofitInstance to use RestApi and updateData
        todosRepository = new TodosRepository(getApplication());
//        Timber.d("Test Retrofit, data = " + todosRepository.getTodosList().toString());// bad to get restAPI data
        todosRepository.getTodosList(new TodosRepository.ReceiveCallback() {
            @Override
            public void receiveData(ArrayList<Todos> todosList) {
                Timber.d("Test Retrofit, data.size = " + todosList.size());
            }

            @Override
            public void receiveError(String errMsg) {
                Timber.e("err = ".concat(errMsg));
            }
        });
    }
}
