package restaurent.manzoor.restaurentapp.retrofitnetwork;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import restaurent.manzoor.restaurentapp.model.StopModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataProvider extends AppCompatActivity implements ServiceMethods {
    private Context context;

    private ApiRetrofitService createRetrofitServiceStop() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apextechies.com/englishbolega/index.php/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiRetrofitService.class);

    }

    private ApiRetrofitService createRetrofitService() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apextechies.com/englishbolega/index.php/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiRetrofitService.class);

    }

    public  RetrofitDataProvider(Context context)
    {
        this.context = context;
    }

    @Override
    public void stopApp(final DownlodableCallback<StopModel> callback) {
        createRetrofitServiceStop().stopApp().enqueue(
                new Callback<StopModel>() {
                    @Override
                    public void onResponse(@NonNull Call<StopModel> call, @NonNull final Response<StopModel> response) {
                        if (response.code()==200) {

                            StopModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);

                        } else

                        {
                            if (response.code() == 401)
                            {
                                callback.onUnauthorized(response.code());
                            }
                            else {

                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<StopModel> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());

                    }
                }
        );
    }



}
