package com.example.firebaseauthapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.firebaseauthapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(email, password)
            } else {
                Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loginState.observe(viewLifecycleOwner) { result ->
            result.onSuccess {
                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                // Navigate to next screen
            }.onFailure {
                Toast.makeText(context, "Login failed: ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
