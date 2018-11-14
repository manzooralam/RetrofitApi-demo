package restaurent.manzoor.restaurentapp.model

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

class StopModel {

    @SerializedName("status")
    var status: String? = null
        internal set
    @SerializedName("msg")
    var msg: String? = null
        internal set
    @SerializedName("data")
    var data: ArrayList<StopDataModel>? = null
        internal set


}
