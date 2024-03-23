package com.dicoding.tugasgithubapi.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter (activity: AppCompatActivity, private val username: String):FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment.arguments = Bundle().apply {
            putInt(DetailFragment.ARG_POSTION,position + 1)
            putString(DetailFragment.ARG_USERNAME,username)
        }
        return  fragment
    }
}