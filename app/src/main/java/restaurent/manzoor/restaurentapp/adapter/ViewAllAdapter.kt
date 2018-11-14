package restaurent.manzoor.restaurentapp.adapter

import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import restaurent.manzoor.restaurentapp.R
import java.util.ArrayList

class ViewAllAdapter (private val context: Context, private val myCartModels: ArrayList<String>) : RecyclerView.Adapter<ViewAllAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtCompanyName: TextView
        var actualPrice: TextView
        var sellPrice: TextView
        var imgHert: ImageView
        var addCart: TextView


        init {
            txtCompanyName = view.findViewById<View>(R.id.txtCompanyName) as TextView
            actualPrice = view.findViewById<View>(R.id.actualPrice) as TextView
            sellPrice = view.findViewById<View>(R.id.sellPrice) as TextView
            imgHert = view.findViewById<View>(R.id.imgHert) as ImageView
            addCart = view.findViewById<View>(R.id.addCart) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.viewall_row_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val sa = myCartModels!![position]

        holder.actualPrice.setPaintFlags(holder.actualPrice.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)

    }

    override fun getItemCount(): Int {
        return myCartModels!!.size
    }

    interface SelectAddress{
        fun selected(position: Int, ischecked: Boolean)
    }

}
