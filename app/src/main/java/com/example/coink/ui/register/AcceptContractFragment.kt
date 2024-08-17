package com.example.coink.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.coink.R
import com.example.coink.RegisterActivity
import com.example.coink.databinding.FragmentAcceptContractBinding

class AcceptContractFragment: Fragment() {

    lateinit var binding: FragmentAcceptContractBinding
    private lateinit var viewModel: RegisterViewModel
    private lateinit var readContract: TextView
    private lateinit var acceptContract: CheckBox
    private lateinit var acceptButtonUnable: Button
    private lateinit var acceptButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        binding = FragmentAcceptContractBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val registerActivity = activity as? RegisterActivity

        if (registerActivity != null) {
            val toolbarTitle = registerActivity.binding.toolbarTitle
            toolbarTitle.text = "FINALIZAR"
            registerActivity.binding.toolbar.setNavigationOnClickListener {
                findNavController().navigate(R.id.action_fragment_accept_contract_to_fragment_account_data)
            }
        }

        readContract = binding.txtReadContract
        acceptContract = binding.acceptContract
        acceptButtonUnable = binding.acceptButtonUnable
        acceptButton = binding.acceptButton

        readContract.setOnClickListener {
            Toast.makeText(context, "Mostrar Contrato", Toast.LENGTH_SHORT).show()
        }

        acceptContract.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                acceptButtonUnable.visibility = View.GONE
                acceptButton.visibility = View.VISIBLE
            } else {
                acceptButtonUnable.visibility = View.VISIBLE
                acceptButton.visibility = View.GONE
            }

        }

        acceptButtonUnable.setOnClickListener {
            val alert = AlertDialog.Builder(requireContext())
            alert.setTitle("Error")
            alert.setMessage("Por favor, acepta el contrato para continuar")
            alert.setPositiveButton("Ok") { dialog, which ->
                dialog.dismiss()
            }
            alert.show()
        }

        acceptButton.setOnClickListener {

        }
    }
}