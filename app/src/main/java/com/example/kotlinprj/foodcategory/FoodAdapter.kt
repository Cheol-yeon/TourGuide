package com.example.kotlinprj.foodcategory

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinprj.R
import kotlinx.android.synthetic.main.foodtab_item.view.*

class FoodAdapter(private var context: Context, private var roomFoodData: ArrayList<RoomFoodData>) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    init {
        Log.e("Adapter", "In Adapter")
    }

    private var dataCount: Int? = roomFoodData.size
    var datas: ArrayList<RoomFoodData>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.foodtab_item, parent,false)

        datas = roomFoodData

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas!![position])
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(roomFoodData: RoomFoodData) {
            Glide.with(itemView)
                .load(roomFoodData.main_img)
                .into(view.iv_food)
            view.tv_title.text = roomFoodData.main_title
            view.tv_addr1.text = "주소 : " + roomFoodData.addr1
            view.tv_tel.text = "전화번호 : " + roomFoodData.cntct_tel
            view.tv_menu.text = "대표메뉴 : " + roomFoodData.menu
            view.tv_time.text = "영업시간 : " + roomFoodData.usage_day_week_and_time
            itemView.setOnClickListener(View.OnClickListener {
                Log.e("Adapter", "No.${roomFoodData.item_no} item clicked")
                val foodInfoFragment = FoodInfoFragment()

                var bundle: Bundle = Bundle()
                bundle.putInt("itemNo", roomFoodData.item_no)

                foodInfoFragment.arguments = bundle

                val appCompatActivity = it.context as AppCompatActivity
                appCompatActivity.supportFragmentManager.
                beginTransaction()
                    .replace(R.id.container, foodInfoFragment)
                    .addToBackStack(null)
                    .commit()
            })
        }

    }

    override fun getItemCount(): Int = dataCount!!
}