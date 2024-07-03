package com.example.camppers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.camppers.ResultData
import com.example.camppers.databinding.SearchResultRecyclerBinding

class SearchViewAdapter(val searchList: MutableList<ResultData>, val view: Fragment): RecyclerView.Adapter<SearchViewAdapter.SearchViewHolder>() {
    val resultBottomSheet = ResultBottomSheet()

    inner class SearchViewHolder(val binding: SearchResultRecyclerBinding): RecyclerView.ViewHolder(binding.root) {
        val mainImage = binding.resultCampImage
        val campName = binding.resultCampName
        val campAddress = binding.resultCampAddress
        val lineIntro = binding.resultCampLineIntro
        val featureIntro = binding.resultCampFeatureIntro

        init {
            binding.recyclerViewHolder.setOnClickListener {
                val position = adapterPosition
                searchList[position].let {
                    // todo - bottomSheet에 정보담아서 올려주기
                    resultBottomSheet.newSerialize(it)
                        .show(view.parentFragmentManager, ResultBottomSheet.TAG)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        SearchViewHolder(SearchResultRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        Glide.with(holder.mainImage).load(searchList[position].imageUrl).into(holder.mainImage)
        holder.campName.text = searchList[position].name
        holder.campAddress.text = searchList[position].address
        holder.lineIntro.text = searchList[position].lineIntro
        holder.featureIntro.text = searchList[position].featureIntro
    }
}