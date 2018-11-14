package restaurent.manzoor.restaurentapp.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.bumptech.glide.Glide
import restaurent.manzoor.restaurentapp.R

class FragmentSlider : Fragment() {

    private var imageUrls: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        imageUrls = arguments!!.getString(ARG_PARAM1)
        val view = inflater.inflate(R.layout.fragment_slider_item, container, false)
        val img = view.findViewById<View>(R.id.img) as ImageView
        Glide.with(activity).load(imageUrls).into(img)
        return view
    }

    companion object {
        private val ARG_PARAM1 = "params"

        @JvmStatic
        fun newInstance(params: String): FragmentSlider {
            val fragment = FragmentSlider()
            val args = Bundle()
            args.putString(ARG_PARAM1, params)
            fragment.arguments = args
            return fragment
        }



    }
}
