package com.example.camppers.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.camppers.R
import com.example.camppers.databinding.FragmentUserBinding

class UserFragment : Fragment() {
    lateinit var fragmentUserBinding: FragmentUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUserBinding = FragmentUserBinding.inflate(layoutInflater, container, false)
        val view = fragmentUserBinding.root

        return view
    }
}