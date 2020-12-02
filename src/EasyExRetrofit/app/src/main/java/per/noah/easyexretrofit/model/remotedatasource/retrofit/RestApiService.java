package per.noah.easyexretrofit.model.remotedatasource.retrofit;

import java.util.List;

import per.noah.easyexretrofit.datamodel.Todos;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestApiService {
    // base url : https://jsonplaceholder.typicode.com/
    // get url : https://jsonplaceholder.typicode.com/todos/1
    // post url : https://jsonplaceholder.typicode.com/todos

    @GET("todos/")
//    Call<TodosWrapper> getTodosList();
    Call<List<Todos>> getTodosList();

    @GET("todos/1")    // set a path of get url
    Call<Todos> getTodos();    // use Todos object to receive response data

    @GET("todos/{id}") // use {} present params about input，@Path will use params to {} as child path
    Call<Todos> getTodosById(@Path("id") int id);

    @POST("todos")
    Call<Todos> postTodos(@Body Todos todos);// use @Body present post data
}