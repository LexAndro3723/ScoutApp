package ru.example.scout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.example.scout.databinding.FragmentPartnersBinding
import android.widget.Toast

@AndroidEntryPoint
class PartnersFragment : Fragment() {

    private val emailValidator = EmailValidator()
    private lateinit var binding: FragmentPartnersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPartnersBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.emailInput.addTextChangedListener(emailValidator)
        binding.saveButton.setOnClickListener {
            if (emailValidator.isValid) {
                Toast.makeText(
                    requireActivity(),
                    getString(R.string.valid_email),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val errorEmail = getString(R.string.invalid_email)
                binding.emailInput.error = errorEmail
                Toast.makeText(requireActivity(), errorEmail, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
