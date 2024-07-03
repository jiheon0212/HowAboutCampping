package com.example.camppers.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.camppers.ResultData
import com.example.camppers.databinding.ResultBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ResultBottomSheet: BottomSheetDialogFragment() {
    lateinit var resultBottomSheetBinding: ResultBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        resultBottomSheetBinding = ResultBottomSheetBinding.inflate(layoutInflater, container, false)
        val view = resultBottomSheetBinding.root

        // todo - version에 따른 분기
        val result = arguments?.getSerializable("result") as ResultData
        resultBottomSheetBinding.apply {
            Glide.with(this@ResultBottomSheet).load(result.imageUrl).into(bottomImage)
            bottomTvName.text = result.name
            bottomTvAddress.text = result.address
            bottomTvTel.text = result.tel
            bottomTvLineIntro.text = result.lineIntro
            bottomTvIntro.text = result.intro
            bottomTvFeatureName.text = result.featureIntro
            bottomTvService.text = result.serviceList
            bottomTvAnimalCarry.text = result.animalCarry
            bottomTvReserveFunc.text = result.reservationRoute
            bottomTvReserveUri.text = result.reservationUrl
        }

        return view
    }

    companion object {
        const val TAG = "ResultBottomSheet"
    }

    // 다른 class or fragment에서 호출해 fragment.arguments에 bundle()을 전달 받아 ResultBottomSheet를 반환해 해당 값을 bottomSheet에서
    // 사용할 수 있도록 하는 method
    fun newSerialize(result: ResultData): ResultBottomSheet {
        val args = Bundle()
        args.putSerializable("result", result)
        val fragment = ResultBottomSheet()
        fragment.arguments = args

        return fragment
    }
}