package com.dicoding.proyekakhir.ui.main.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.proyekakhir.databinding.FragmentAboutBinding.inflate as FragmentAboutBinding

class AboutFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        FragmentAboutBinding(layoutInflater, container, false).also {
            return it.root
        }
    }

}