package per.noah.easyexretrofit.model.remotedatasource.retrofit;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import per.noah.easyexretrofit.datamodel.Todos;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class TodosRepository {

    Application application;
    ArrayList<Todos> todosList = new ArrayList<>();

    public interface ReceiveCallback{
        void receiveData(ArrayList<Todos> todosList);
        void receiveError(String errMsg);
    }

    public TodosRepository(Application application){
        this.application = application;
    }

    public void getTodosList(final ReceiveCallback callback){
        RestApiService apiService = RetrofitInstance.getApiService();
        Call<List<Todos>> call = apiService.getTodosList();
        call.enqueue(new Callback<List<Todos>>() {
            @Override
            public void onResponse(Call<List<Todos>> call, Response<List<Todos>> response) {
                Timber.d("check resp data with retrofit, data = " + response.body());
                // get response data
                List<Todos> todosList = response.body();
                // exception process
                if (null == todosList) return;
                // init list
                TodosRepository.this.todosList = new ArrayList<>();
                // copy response data to list
                for (Todos e : todosList) {
                    TodosRepository.this.todosList.add(e);
                }
                callback.receiveData(TodosRepository.this.todosList);
            }

            @Override
            public void onFailure(Call<List<Todos>> call, Throwable t) {
                callback.receiveError(t.getMessage());
            }
        });
    }
}