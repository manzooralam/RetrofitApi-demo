package restaurent.manzoor.restaurentapp.model

import com.google.gson.annotations.SerializedName

class StopDataModel {

    @SerializedName("id")
    var id: String? = null
        internal set
    @SerializedName("status")
    var status: Int = 0
        internal set
}
