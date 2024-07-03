package com.example.camppers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.camppers.campper.CampData
import com.example.camppers.campper.CampInfo
import com.example.camppers.campper.CampService
import com.example.camppers.databinding.ActivityMainBinding
import com.example.camppers.fragments.HomeFragment
import com.example.camppers.fragments.SearchFragment
import com.example.camppers.fragments.UserFragment
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var homeFragment: HomeFragment
    lateinit var searchFragment: SearchFragment
    lateinit var userFragment: UserFragment
    lateinit var selectedFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        homeFragment = HomeFragment()
        searchFragment = SearchFragment()
        userFragment = UserFragment()

        val view = activityMainBinding.root
        setContentView(view)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, homeFragment)
            .commit()

        activityMainBinding.mainBottomTab.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_tab -> {
                    selectedFragment = homeFragment
                    // isChecked를 사용하여 어떤 탭이 선택되었는지 판단해주기
                    it.isChecked = true
                }
                R.id.search_tab -> {
                    selectedFragment = searchFragment
                    it.isChecked = true
                }
                R.id.user_tab -> {
                    selectedFragment = userFragment
                    it.isChecked = true
                }
            }
            selectedFragment.apply {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, selectedFragment)
                    .commit()
            }
            false
        }
    }
}