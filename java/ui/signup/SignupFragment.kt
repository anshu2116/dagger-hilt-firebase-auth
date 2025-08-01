package com.example.firebaseauthapp.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.firebaseauthapp.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private val viewModel: SignupViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonSignup.setOnClickListener {
            val name = binding.editTextName.text.toString().trim()
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.signup(name, email, password)
            } else {
                Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.signupState.observe(viewLifecycleOwner) { result ->
            result.onSuccess {
                Toast.makeText(context, "Signup successful", Toast.LENGTH_SHORT).show()
                // Navigate or finish
            }.onFailure {
                Toast.makeText(context, "Signup failed: ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
