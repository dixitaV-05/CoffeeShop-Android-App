package com.example.project1239.Adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.project1239.Helper.ManagementCart
import com.example.project1239.databinding.ViewholderCartBinding
import com.example.project1239.domain.ItemsModel
import com.example.project195.Helper.ChangeNumberItemsListener


class CartAdapter (
    private val listItemSelected: ArrayList<ItemsModel>,
    context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener?=null



): RecyclerView.Adapter<CartAdapter.Viewholder>(){
    class Viewholder (val binding: ViewholderCartBinding):
    RecyclerView. ViewHolder(binding.root)

    private val managementCart= ManagementCart(context)







    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartAdapter.Viewholder {
        val binding= ViewholderCartBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Viewholder(binding)

    }

    override fun onBindViewHolder(holder: CartAdapter.Viewholder, position: Int) {
        val item=listItemSelected[position]

        holder.binding.titleTxt.text=item.title
        holder.binding.feeEachItem.text="$${item.price}"
        holder.binding.totalEachItem.text="$${item.numberInCart*item.price}"
        holder.binding.numberInCartTxt.text=item.numberInCart.toString()


        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.picCart)


        holder.binding.plusBtn.setOnClickListener {
            managementCart.plusItem(listItemSelected,position,object : com.example.project1239.Helper.ChangeNumberItemsListener{
                override fun changed() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()

                }
            })
        }

        holder.binding.minusBtn.setOnClickListener {
            managementCart.minusItem(listItemSelected,position,object : com.example.project1239.Helper.ChangeNumberItemsListener{
                override fun changed() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()

                }
            })
        }

        holder.binding.removeItemBtn.setOnClickListener {
            managementCart.removeItem(listItemSelected,position,object : com.example.project1239.Helper.ChangeNumberItemsListener{
                override fun changed() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()

                }
            })
        }












    }

    override fun getItemCount(): Int =listItemSelected.size

}