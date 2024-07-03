package com.example.camppers.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.camppers.R
import com.example.camppers.ResultData
import com.example.camppers.adapter.SearchViewAdapter
import com.example.camppers.campper.CampData
import com.example.camppers.campper.CampInfo.Companion.API_KEY
import com.example.camppers.campper.CampService.campLocationInterface
import com.example.camppers.campper.CampService.campSearchInterface
import com.example.camppers.databinding.FragmentSearchBinding
import retrofit2.Call
import retrofit2.Response

class SearchFragment : Fragment() {
    lateinit var fragmentSearchBinding: FragmentSearchBinding
    val resultList = mutableListOf<ResultData>()
    val searchViewAdapter = SearchViewAdapter(resultList, this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSearchBinding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        val view = fragmentSearchBinding.root

        fragmentSearchBinding.fragSearchRecycler.run {
            adapter = searchViewAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        fragmentSearchBinding.fragSearchView.editText.addTextChangedListener {
            searchCampByKeyWord(it?.toString()!!)
        }
    }

    private fun searchCampByKeyWord(keyWord: String) {
        val camp = campSearchInterface.getCampList(
            API_KEY,
            "json",
            getString(R.string.app_name),
            "AND",
            keyWord
        )
        camp.enqueue(object: retrofit2.Callback<CampData> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(p0: Call<CampData>, p1: Response<CampData>) {
                val resultMsg= p1.body()?.response?.header?.resultMsg
                if (resultMsg == "OK") {
                    resultList.clear()
                    p1.body()?.response?.body?.items?.item?.forEach {
                        resultList.add(
                            ResultData(it.firstImageUrl, // 대표 이미지
                                it.facltNm, // 시설명
                                it.addr1, // 주소
                                it.manageSttus, // 운영 여부
                                it.lineIntro, // 한줄 설명
                                it.intro,
                                it.featureNm, // 설명
                                it.animalCmgCl, // 동물 반입 여부
                                it.sbrsCl, // 편의 시설
                                it.resveUrl, // 예약 주소
                                it.resveCl, // 예약 방법
                                it.tel) // 전화
                        )
                    }
                    Log.d("tester", "$resultList")

                    // todo - view 관련 작업 시작 부분
                    // todo - 추가된 resultList를 통해서 recyclerView 갱신
                    // todo - recyclerView touch event 발생 시, bottomSheet 등장시키고 모든 정보를 보여주는 view
                    searchViewAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(p0: Call<CampData>, p1: Throwable) {
                Log.d("enqueue function failed", "$p1")
            }
        })
    }

    private fun searchCampByLocation(location: Pair<Int, Int>, range: String) {
        val camp = campLocationInterface.getCampList(
            API_KEY,
            "json",
            getString(R.string.app_name),
            "AND",
            location.first.toString(),
            location.second.toString(),
            range
        )
        camp.enqueue(object: retrofit2.Callback<CampData> {
            override fun onResponse(p0: Call<CampData>, p1: Response<CampData>) {
                val resultMsg= p1.body()?.response?.header?.resultCode
                if (resultMsg == "OK") {
                    resultList.clear()
                    p1.body()?.response?.body?.items?.item?.forEach {
                        resultList.add(
                            ResultData(it.firstImageUrl, // 대표 이미지
                                it.facltNm, // 시설명
                                it.addr1, // 주소
                                it.manageSttus, // 운영 여부
                                it.lineIntro, // 한줄 설명
                                it.intro,
                                it.featureNm, // 설명
                                it.animalCmgCl, // 동물 반입 여부
                                it.sbrsCl, // 편의 시설
                                it.resveUrl, // 예약 주소
                                it.resveCl, // 예약 방법
                                it.tel) // 전화
                        )
                    }

                    // todo - view 관련 작업 시작 부분
                    // todo - 추가된 resultList를 통해서 recyclerView 갱신
                    // todo - recyclerView touch event 발생 시, bottomSheet 등장시키고 모든 정보를 보여주는 view
                }
            }

            override fun onFailure(p0: Call<CampData>, p1: Throwable) {
                Log.d("enqueue function failed", "$p1")
            }
        })
    }
}