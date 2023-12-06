package com.example.coink.ui.register

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.coink.RegisterActivity
import com.example.coink.adapter.DocumentTypeAdapter
import com.example.coink.databinding.FragmentAccountDataBinding

class AccountDataFragment: Fragment() {

    lateinit var binding: FragmentAccountDataBinding
    private lateinit var viewModel: RegisterViewModel
    private lateinit var spinnerDocumentType: Spinner
    private lateinit var spinnerGender: Spinner

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        binding = FragmentAccountDataBinding.inflate(inflater, container, false)

        observerViewModel()

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

        spinnerDocumentType = binding.spinnerDocumentType

        spinnerGender = binding.spinnerGender

        val gender = arrayOf("Hombre", "Mujer", "Otro")
        val adapter = context?.let { ArrayAdapter(it, R.layout.simple_spinner_item, gender) }
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGender.adapter = adapter

    }

    private fun observerViewModel() {
        viewModel.getDocumentType()

        viewModel.documentTypes.observe(viewLifecycleOwner) {
            it?.let {
                val types = it.map { it.description }
                val adapter = context?.let { ArrayAdapter(it, R.layout.simple_spinner_item, types) }
                adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerDocumentType.adapter = adapter
            }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            it?.let {
                if(it) {
                    val alert = AlertDialog.Builder(requireContext())
                    alert.setTitle("Error")
                    alert.setMessage("Lo sentimos ha ocurrido un error")
                    alert.setPositiveButton("Ok") { dialog, which ->
                        dialog.dismiss()
                    }
                    alert.show()
                }

            }
        }
    }

}