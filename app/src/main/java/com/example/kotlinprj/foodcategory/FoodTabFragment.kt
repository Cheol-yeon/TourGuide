package com.example.kotlinprj.foodcategory

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinprj.MainActivity
import com.example.kotlinprj.R
import com.example.roomtestkt.RoomAccess
import kotlinx.android.synthetic.main.fragment_food_tab.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FoodTabFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodTabFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var foodAdapter: FoodAdapter
    lateinit var layoutManager: LinearLayoutManager
    var datas: ArrayList<RoomFoodData>? = null
    private var keyWord: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("FoodTabFragment", "In Fragment:start")
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        Log.e("FoodTabFragment", "In Fragment:end")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("FoodTabFragment", "onCreateView")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("FoodTabFragment", "onViewCreated")
        et_search.text = null
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        datas = RoomAccess(context as MainActivity).foodGetAll() as ArrayList<RoomFoodData>
        for(i in 0 until RoomAccess(context as MainActivity).foodGetCount()) {
            println("프래그먼트 에서 실행 : " + datas!!.get(i))
        }

//        datas!!.forEach { roomFoodData -> Log.e("datas", "${roomFoodData}") }

        rv_foodItems?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(requireContext())
        rv_foodItems?.setLayoutManager(layoutManager)
        foodAdapter = FoodAdapter(requireContext(), datas!!)
        rv_foodItems!!.adapter = foodAdapter

        var status: Int = 0
        iv_showSearchBox.setOnClickListener(View.OnClickListener {
            if(status == 0) {
                et_search.visibility = VISIBLE
                iv_search.visibility = VISIBLE
                status = 1
            } else if (status == 1) {
                et_search.visibility = GONE
                iv_search.visibility = GONE
                status = 0
            }
        })

        iv_arrowBack.setOnClickListener(View.OnClickListener {
            if(et_search.text.toString()!="" && status == 1 ) {
                et_search.text = null
                et_search.visibility = GONE
                iv_search.visibility = GONE
                status = 0
                foodAdapter = FoodAdapter(requireContext(), datas!!)
                rv_foodItems!!.adapter = foodAdapter
            } else if(et_search.isVisible == false) {
                et_search.text = null
                activity?.onBackPressed()
            } else activity?.onBackPressed()
        })

        iv_search.setOnClickListener(View.OnClickListener {
            var searchItems: ArrayList<RoomFoodData> = arrayListOf()
            keyWord = et_search.text.toString()
            Log.e("FragmentTab", "${keyWord}")

            if(keyWord!="") {
                for(i in 0 until datas!!.size) {
                    if(keyWord.equals(datas!!.get(i).gugun_nm.toString())) {
                        Log.e("if", "${datas!!.get(i)}")
                        searchItems?.add(datas!!.get(i))
                        Log.e("searchItems", "${searchItems?.size}")
                    }
                    var foodAdapter = FoodAdapter(requireContext(), searchItems!!)
                    rv_foodItems!!.adapter = foodAdapter
                }
                if (searchItems?.size == 0){
                    Toast.makeText(requireContext(), "조건에 맞는 지역구명을 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                }

            } else if(keyWord == "") {
                Toast.makeText(requireContext(), "검색값(지역구)을 입력해 주세요.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("FoodTabFragment", "onDetach")
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FoodTabFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FoodTabFragment().apply {
                Log.e("FoodTabFragment", "newInstance")
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}