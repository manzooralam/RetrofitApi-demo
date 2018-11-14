package restaurent.manzoor.restaurentapp.retrofitnetwork;


import restaurent.manzoor.restaurentapp.model.StopModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRetrofitService {


    @GET("start_stop_app")
    Call<StopModel> stopApp();


}
