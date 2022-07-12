package com.example.kotlinprj.foodcategory

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.kotlinprj.MainActivity
import com.example.kotlinprj.R
import com.example.roomtestkt.RoomAccess
import kotlinx.android.synthetic.main.fragment_food_info.*
import kotlinx.android.synthetic.main.fragment_food_info.view.*
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FoodInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodInfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var itemNo: Int? = null
    private var foodInfo: RoomFoodData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        Log.e("info", "${arguments?.getInt("itemNo")}")
        itemNo = arguments?.getInt("itemNo")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        foodInfo = RoomAccess(context as MainActivity).foodGetById(itemNo!!)
        Log.e("InfoFrag", "${foodInfo}")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_infoTitle.text = foodInfo?.main_title
        Glide.with(view)
            .load(foodInfo?.main_img)
            .into(view.iv_infoImg)
        tv_infoGugun.text = "위치 : ${foodInfo?.gugun_nm}"
        tv_infoAddr1.text = "주소 : ${ foodInfo?.addr1 }"
        tv_infoTel.text = "전화번호 : ${ foodInfo?.cntct_tel }"
        tv_infoTime.text = "영업시간 : ${foodInfo?.usage_day_week_and_time}"
        tv_infoMenu.text = "메뉴 : ${foodInfo?.menu}"
        tv_infoContent.text = foodInfo?.itemcntnts

        var lat: Double? = foodInfo?.lat
        var lng: Double? = foodInfo?.lng

        iv_arrowBack.setOnClickListener(View.OnClickListener {
            activity?.onBackPressed()
        })

        val mapView: MapView = MapView(requireContext())

        val mapViewContainer: ViewGroup = map_view as ViewGroup
        mapViewContainer.addView(mapView)
        //지도 중심점 변경
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(lat!!, lng!!), true);



        val marker = MapPOIItem()

        //맵 포인트 위도경도 설정
        //맵 포인트 위도경도 설정
        val mapPoint = MapPoint.mapPointWithGeoCoord(lat!!, lng!!)
        marker.setItemName("${foodInfo?.main_title}")
        marker.setTag(0)
        marker.setMapPoint(mapPoint)
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin) // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin) // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.


        mapView.addPOIItem(marker)

        var state: Int = 0
        Log.e("menuwidth", "${foodInfo?.menu?.length}")
        if(foodInfo!!.menu!!.length <= 15) {
            tv_infoMore.visibility = GONE
        }
        tv_infoMore.setOnClickListener(View.OnClickListener {
            if(state == 0) {
                tv_infoMenu.maxLines = 2
                state = 1
            } else if(state == 1) {
                tv_infoMenu.maxLines = 1
                state = 0
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FoodInfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FoodInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}