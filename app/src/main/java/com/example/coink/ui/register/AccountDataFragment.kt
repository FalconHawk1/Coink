package com.example.coink.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.coink.RegisterActivity
import com.example.coink.databinding.FragmentAccountDataBinding

class AccountDataFragment: Fragment() {

    lateinit var binding: FragmentAccountDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val registerActivity = activity as? RegisterActivity

        if (registerActivity != null) {
            val toolbarTitle = registerActivity.binding.toolbarTitle
            toolbarTitle.text = "DATOS DE CUENTA"
            registerActivity.binding.toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

}