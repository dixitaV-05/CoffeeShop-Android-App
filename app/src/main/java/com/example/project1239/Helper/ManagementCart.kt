package com.example.project1239.Helper



import android.content.Context
import android.widget.Toast
import com.example.project1239.domain.ItemsModel
import kotlin.collections.ArrayList

interface ChangeNumberItemsListener {
    fun changed()
}

class ManagementCart(val context: Context) {

    private val tinyDB = TinyDB(context)

    fun insertItems(item: ItemsModel) {
        val listItem: ArrayList<ItemsModel> = getListCart()
        val existAlready = listItem.any { it.title == item.title }
        val index = listItem.indexOfFirst { it.title == item.title }

        if (existAlready) {
            listItem[index].numberInCart = item.numberInCart
        } else {
            listItem.add(item)
        }

        tinyDB.putListObject("CartList", listItem)
        Toast.makeText(context, "Added to Cart", Toast.LENGTH_SHORT).show()
    }

    fun getListCart(): ArrayList<ItemsModel> {
        return tinyDB.getListObject("CartList")
    }

    fun minusItem(list: ArrayList<ItemsModel>, position: Int, listener: ChangeNumberItemsListener) {
        if (list[position].numberInCart == 1) {
            list.removeAt(position)
        } else {
            list[position].numberInCart--
        }
        tinyDB.putListObject("CartList", list)
        listener.changed()
    }

    fun plusItem(list: ArrayList<ItemsModel>, position: Int, listener: ChangeNumberItemsListener) {
        list[position].numberInCart++
        tinyDB.putListObject("CartList", list)
        listener.changed()
    }

    fun removeItem(list: ArrayList<ItemsModel>, position: Int, listener: ChangeNumberItemsListener) {
        list.removeAt(position)
        tinyDB.putListObject("CartList", list)
        listener.changed()
    }

    fun getTotalFee(): Double {
        val listItem = getListCart()
        var fee = 0.0
        for (item in listItem) {
            fee += item.price * item.numberInCart
        }
        return fee
    }
}
