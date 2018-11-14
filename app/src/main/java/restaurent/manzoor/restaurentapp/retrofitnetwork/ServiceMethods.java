package restaurent.manzoor.restaurentapp.retrofitnetwork;


import restaurent.manzoor.restaurentapp.model.StopModel;

public interface ServiceMethods {

    void stopApp(DownlodableCallback<StopModel> callback);

}
