package com.example.kotlinprj

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import com.example.kotlinprj.attractioncategory.AttractionTabFragment
import com.example.kotlinprj.databinding.ActivityMainBinding
import com.example.kotlinprj.foodcategory.FoodTabFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_food_tab.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private var mContext: Context? = null

    var foodTabFragment: FoodTabFragment? = FoodTabFragment()
    var attractionTabFragment: AttractionTabFragment? = AttractionTabFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // StatusBar 아래로 앱이 실행되게 statusBar의 높이만큼 Padding을 줌
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        container.setPadding(0, statusBarHeight(this), 0, 0)

        Log.e("MainActivity", "In Main Activity")

        Log.e("MainActivity", "2222")
        binding.btnFood.setOnClickListener(View.OnClickListener {
            if (savedInstanceState == null) {
                btn_food.visibility = View.GONE
                btn_attraction.visibility = View.GONE

                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(binding.container.id, foodTabFragment!!).addToBackStack(null)
                    .commit()
            }
        })

        binding.btnAttraction.setOnClickListener(View.OnClickListener {
            if (savedInstanceState == null) {
                btn_food.visibility = View.GONE
                btn_attraction.visibility = View.GONE

                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(binding.container.id, attractionTabFragment!!).addToBackStack(null)
                    .commit()
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        var foodTabFragment = FoodTabFragment()
        foodTabFragment.et_search?.text = null
        val currentFragment: Fragment? = supportFragmentManager.findFragmentById(binding.root.id)
        Log.e("currentFragment", "${currentFragment}")
        if (currentFragment == null) {
            btn_food.visibility = View.VISIBLE
            btn_attraction.visibility = View.VISIBLE
        }

    }

    // StatusBar의 높이를 구하는 메소드
    fun statusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }
}

