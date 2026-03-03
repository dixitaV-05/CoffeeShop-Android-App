package com.example.project1239.Activity


import android.os.Bundle
import com.example.project1239.R

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.project1239.Helper.ManagementCart
import com.example.project1239.databinding.ActivityDetailBinding
import com.example.project1239.domain.ItemsModel


class DetailActivity : AppCompatActivity() {
   lateinit var binding: ActivityDetailBinding
   private lateinit var item: ItemsModel
   private lateinit var managementCart: ManagementCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart= ManagementCart(this)

        bundle()
        initSizeList()

    }

    private fun initSizeList() {
         binding.apply {
             smallBtn.setOnClickListener {
                 smallBtn.setBackgroundResource(R.drawable.brown_storke_bg)
                 mediumbtn.setBackgroundResource(0)
                 largebtn.setBackgroundResource(0)
             }



                 mediumbtn.setOnClickListener {
                     smallBtn.setBackgroundResource(0)
                     mediumbtn.setBackgroundResource(R.drawable.brown_storke_bg)
                     largebtn.setBackgroundResource(0)
                 }


             largebtn.setOnClickListener {
                 smallBtn.setBackgroundResource(0)
                 mediumbtn.setBackgroundResource(0)
                 largebtn.setBackgroundResource(R.drawable.brown_storke_bg)
             }


             }
    }

    private fun bundle() {
        binding.apply {
            item=intent.getSerializableExtra("object") as ItemsModel

            Glide.with(this@DetailActivity)
                .load(item.picUrl[0])
                .into(binding.picMain)

            titletxt.text=item.title
            descriptionTx.text=item.description
            priceTxt.text="$"+item.price
            ratingtxt.text=item.rating.toString()

            addToCartBtn.setOnClickListener {
                item.numberInCart=Integer.valueOf(
                    numberInCartTxt.text.toString()
                )
                managementCart.insertItems(item)
            }

            backBtn.setOnClickListener { finish() }

            plusBtn.setOnClickListener {
                numberInCartTxt.text =(item.numberInCart +1).toString()
                item.numberInCart++

                minusBtn.setOnClickListener {
                    if (item.numberInCart > 0){
                        numberInCartTxt.text = (item.numberInCart - 1). toString()
                        item.numberInCart--
                    }
                }
            }



        }
    }
}