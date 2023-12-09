package com.example.coink.ui.register

import com.example.coink.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.coink.RegisterActivity
import com.example.coink.adapter.DocumentTypeAdapter
import com.example.coink.databinding.FragmentAccountDataBinding
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AccountDataFragment: Fragment() {

    lateinit var binding: FragmentAccountDataBinding
    private lateinit var viewModel: RegisterViewModel
    private lateinit var adapter: DocumentTypeAdapter
    private lateinit var spinnerDocumentType: Spinner
    private lateinit var txtDocumentNumber: EditText
    private lateinit var spinnerGender: Spinner
    private lateinit var tilTxtDocumentDateBirth: TextInputLayout
    private lateinit var txtDocumentDateExpedition: EditText
    private lateinit var tilTxtDocumentDateExpedition: TextInputLayout
    private lateinit var txtDocumentDateBirth: EditText
    private lateinit var tilEmail: TextInputLayout
    private lateinit var txtEmail: EditText
    private lateinit var tilEmailConfirm: TextInputLayout
    private lateinit var txtEmailConfirm: EditText
    private lateinit var imgConfirmEmail: ImageView
    private lateinit var tilPasswordConfirm: TextInputLayout
    private lateinit var txtPasswordConfirm: EditText
    private lateinit var tilPassword: TextInputLayout
    private lateinit var txtPassword: EditText
    private lateinit var btnShowPassword: ImageButton
    private lateinit var btnHidePassword: ImageButton
    private lateinit var btnShowConfirmPassword: ImageButton
    private lateinit var btnHideConfirmPassword: ImageButton
    private lateinit var registerButtonUnable: Button
    private lateinit var registerButton: Button

    private lateinit var progressBar: ProgressBar

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
                findNavController().navigate(R.id.action_fragment_account_data_to_fragment_register_number)
            }
        }

        spinnerDocumentType = binding.spinnerDocumentType

        txtDocumentNumber = binding.txtDocumentNumber

        spinnerGender = binding.spinnerGender

        tilTxtDocumentDateBirth = binding.tilDocumentDateBirth
        txtDocumentDateBirth = binding.txtDocumentDateBirth

        tilTxtDocumentDateExpedition = binding.tilDocumentDateExpedition
        txtDocumentDateExpedition = binding.txtDocumentDateExpedition

        tilEmail = binding.tilTxtEmail
        txtEmail = binding.txtEmail

        tilEmailConfirm = binding.tilTxtEmailConfirm
        txtEmailConfirm = binding.txtEmailConfirm
        imgConfirmEmail = binding.imgConfirmEmail

        tilPasswordConfirm = binding.tilTxtPinConfirm
        txtPasswordConfirm = binding.txtPinConfirm
        tilPassword = binding.tilTxtPin
        txtPassword = binding.txtPin
        btnShowPassword = binding.btnShowPassword
        btnHidePassword = binding.btnHidePassword
        btnShowConfirmPassword = binding.btnShowConfirmPassword
        btnHideConfirmPassword = binding.btnHideConfirmPassword

        registerButtonUnable = binding.registerButtonUnable
        registerButton = binding.registerButton

        progressBar = binding.progressBar

        performTimeConsumingOperation()

        txtDocumentNumber.addTextChangedListener {
            updateRegisterButtonVisibility(null)
        }

        txtEmail.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                updateCheckImageVisibility()
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                updateCheckImageVisibility()
            }
        })
        txtEmailConfirm.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                updateCheckImageVisibility()
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                updateCheckImageVisibility()
            }
        })

        txtPassword.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                checkPinConfimation()
            }
        })

        txtPasswordConfirm.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                checkPinConfimation()
            }
        })

        txtDocumentDateExpedition.addTextChangedListener(object : TextWatcher {
            private var currentLength = 0

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                currentLength = s?.length ?: 0
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                val input = editable.toString()

                if (input.length > currentLength) {
                    // El usuario está agregando texto
                    if (input.length == 2 || input.length == 5) {
                        // Agrega automáticamente "/" después de los dos primeros y cinco primeros caracteres
                        editable?.append("/")
                    }
                } else if (input.length < currentLength) {
                    // El usuario está eliminando texto
                    if (input.length == 2 || input.length == 5) {
                        // Elimina automáticamente el "/" si el usuario retrocede después de escribir dos o cinco caracteres
                        if (input.endsWith("/")) {
                            editable?.delete(input.length - 1, input.length)
                        }
                    }
                }

                // Realiza la validación de formato de fecha aquí si es necesario
                if (!isValidDate(input)) {
                    tilTxtDocumentDateExpedition.error = "Formato de fecha no válido"
                } else {
                    tilTxtDocumentDateExpedition.error = null
                }
                updateRegisterButtonVisibility(tilTxtDocumentDateExpedition.error)
            }
        })

        txtDocumentDateBirth.addTextChangedListener(object : TextWatcher {
            private var currentLength = 0

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                currentLength = s?.length ?: 0
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                val input = editable.toString()

                if (input.length > currentLength) {
                    // El usuario está agregando texto
                    if (input.length == 2 || input.length == 5) {
                        // Agrega automáticamente "/" después de los dos primeros y cinco primeros caracteres
                        editable?.append("/")
                    }
                } else if (input.length < currentLength) {
                    // El usuario está eliminando texto
                    if (input.length == 2 || input.length == 5) {
                        // Elimina automáticamente el "/" si el usuario retrocede después de escribir dos o cinco caracteres
                        if (input.endsWith("/")) {
                            editable?.delete(input.length - 1, input.length)
                        }
                    }
                }

                // Realiza la validación de formato de fecha aquí si es necesario
                if (!isValidDate(input)) {
                    tilTxtDocumentDateBirth.error = "Formato de fecha no válido"
                } else {
                    tilTxtDocumentDateBirth.error = null
                }
                updateRegisterButtonVisibility(tilTxtDocumentDateBirth.error)
            }
        })


        registerButtonUnable.setOnClickListener {
            val alert = AlertDialog.Builder(requireContext())
            alert.setTitle("Error")
            alert.setMessage("Por favor, completa todos los campos")
            alert.setPositiveButton("Ok") { dialog, which ->
                dialog.dismiss()
            }
            alert.show()
        }

        registerButton.setOnClickListener {
            saveRegisterData()
            findNavController().navigate(R.id.action_fragment_account_data_to_fragment_accept_contract)
        }



        btnShowPassword.setOnClickListener {
            btnShowPassword.visibility = View.GONE
            btnHidePassword.visibility = View.VISIBLE
            txtPassword.inputType = 1
        }
        btnHidePassword.setOnClickListener {
            btnShowPassword.visibility = View.VISIBLE
            btnHidePassword.visibility = View.GONE
            txtPassword.inputType = 129
        }
        btnShowConfirmPassword.setOnClickListener {
            btnShowConfirmPassword.visibility = View.GONE
            btnHideConfirmPassword.visibility = View.VISIBLE
            txtPasswordConfirm.inputType = 1
        }
        btnHideConfirmPassword.setOnClickListener {
            btnShowConfirmPassword.visibility = View.VISIBLE
            btnHideConfirmPassword.visibility = View.GONE
            txtPasswordConfirm.inputType = 129
        }


        val gender = arrayOf("Hombre", "Mujer", "Otro")
        val adapter = context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, gender) }
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGender.adapter = adapter

    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return email.matches(emailRegex.toRegex())
    }

    private fun isValidDate(date: String): Boolean {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dateFormat.isLenient = false  // Hace que la validación sea estricta

        try {
            val parsedDate = dateFormat.parse(date)

            // Verificar que la fecha no sea posterior a hoy
            if (parsedDate != null && parsedDate.after(Date())) {
                return false
            }

        } catch (e: ParseException) {
            return false  // Error al analizar la fecha
        }

        return true
    }

    private fun saveRegisterData() {
        val documentType = spinnerDocumentType.selectedItem.toString()
        val documentNumber = txtDocumentNumber.text.toString()
        val documentDateExpedition = txtDocumentDateExpedition.text.toString()
        val documentDateBirth = txtDocumentDateBirth.text.toString()
        val gender = spinnerGender.selectedItem.toString()
        val email = txtEmail.text.toString()
        val password = txtPassword.text.toString()

        viewModel.documentTypeTextValue = documentType
        viewModel.documentNumberTextValue = documentNumber
        viewModel.documentDateExpeditionTextValue = documentDateExpedition
        viewModel.documentDateBirthTextValue = documentDateBirth
        viewModel.genderTextValue = gender
        viewModel.emailTextValue = email
        viewModel.passwordTextValue = password
    }

    private fun updateRegisterButtonVisibility(error: CharSequence?) {
        if (error == null) {
            val documentType = spinnerDocumentType.selectedItem.toString()
            val documentNumber = txtDocumentNumber.text.toString()
            val documentDateExpedition = txtDocumentDateExpedition.text.toString()
            val documentDateBirth = txtDocumentDateBirth.text.toString()
            val email = txtEmail.text.toString()
            val emailConfirm = txtEmailConfirm.text.toString()
            val password = txtPassword.text.toString()
            val passwordConfirm = txtPasswordConfirm.text.toString()

            if (documentType.isNotEmpty() && documentNumber.isNotEmpty() && documentDateExpedition.isNotEmpty() && documentDateBirth.isNotEmpty() && email.isNotEmpty() && emailConfirm.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty()) {
                registerButtonUnable.visibility = View.GONE
                registerButton.visibility = View.VISIBLE
            } else {
                registerButtonUnable.visibility = View.VISIBLE
                registerButton.visibility = View.GONE
            }
        } else {
            registerButtonUnable.visibility = View.VISIBLE
            registerButton.visibility = View.GONE
        }
    }


    private fun updateCheckImageVisibility() {
        val email = txtEmail.text.toString()
        val emailConfirm = txtEmailConfirm.text.toString()

        if (isValidEmail(email)) {
            tilEmail.error = null
        } else {
            tilEmail.error = "Correo electrónico no válido"
        }
        updateRegisterButtonVisibility(tilEmail.error)
        if (isValidEmail(emailConfirm)) {
            if (email == emailConfirm) {
                imgConfirmEmail.visibility = View.VISIBLE
                tilEmailConfirm.error = null
            } else {
            imgConfirmEmail.visibility = View.GONE
            tilEmailConfirm.error = "Los correos electrónicos no coinciden"
            }
        } else {
            tilEmailConfirm.error = "Correo electrónico no válido"
        }
        updateRegisterButtonVisibility(tilEmailConfirm.error)
    }

    private fun checkPinConfimation(){
        val pin = txtPassword.text.toString()
        val pinConfirm = txtPasswordConfirm.text.toString()

        if(pin == pinConfirm){
            tilPasswordConfirm.error = null
        }else{
            tilPasswordConfirm.error = "Este campo no coincide con el PIN"
        }
        updateRegisterButtonVisibility(tilPasswordConfirm.error)
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun performTimeConsumingOperation() {
        // Show the loader
        showLoader()

        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                kotlinx.coroutines.delay(3000)

                // Hide the loader when the operation is complete
                hideLoader()
            }
        }
    }

    private fun showLoader() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        progressBar.visibility = View.GONE
    }
    private fun observerViewModel() {
        viewModel.getDocumentType()

        viewModel.documentTypes.observe(viewLifecycleOwner) { documentTypes ->
            documentTypes?.let {
                adapter = DocumentTypeAdapter(requireContext(), it)
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