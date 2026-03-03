package com.example.project1239.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1239.Adapter.CartAdapter
import com.example.project1239.Helper.ChangeNumberItemsListener
import com.example.project1239.Helper.ManagementCart
import com.example.project1239.R
import com.example.project1239.databinding.ActivityCartBinding
import com.example.project1239.databinding.ActivityDetailBinding

class CartActivity : AppCompatActivity() {
    lateinit var binding: ActivityCartBinding
    lateinit var managementCart: ManagementCart
    private var tax: Double=0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityCartBinding.inflate(layoutInflater)

        setContentView(binding.root)

        managementCart= ManagementCart(this)

        calculatedCart()
        setVariable()
        initCartlist()

    }

    private fun initCartlist() {
        binding.apply {
            listView.layoutManager=
                LinearLayoutManager(this@CartActivity,LinearLayoutManager.VERTICAL,false)
            listView.adapter= CartAdapter(
                managementCart.getListCart(),
                this@CartActivity,
                object : com.example.project195.Helper.ChangeNumberItemsListener{
                    override fun onChanged() {
                        calculatedCart()
                    }

                }

            )
        }

    }

    private fun setVariable() {
       binding.backBtn.setOnClickListener { finish() }
    }

    private fun calculatedCart() {
        val percentTax=0.02
        val delivery: Double=10.0
        tax=Math.round(managementCart.getTotalFee()*percentTax*100.0)/100.0
        val total=Math.round((managementCart.getTotalFee()+tax+delivery)*100.0)/100.0
        val itemTotal=Math.round(managementCart.getTotalFee()*100.0)/100.0
        binding.apply {
            totalTaxtxt.text="$$itemTotal"
            totalTaxtxt.text="$$tax"
            totalfeetxt.text="$$delivery"
            totaltxt.text="$$total"


        }

    }
}