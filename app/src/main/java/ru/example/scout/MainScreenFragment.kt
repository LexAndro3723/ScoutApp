package ru.example.scout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.example.scout.databinding.FragmentMainscreenBinding

@AndroidEntryPoint
class MainScreenFragment : Fragment() {
    private lateinit var binding: FragmentMainscreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainscreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.opensettings.setOnClickListener() {
           findNavController().navigate(R.id.action_mainscreenFragment_to_settingsFragment)
        }
        binding.fileText.setOnClickListener() {
            findNavController().navigate(R.id.action_mainscreenFragment_to_calculatorFragment)
        }
        binding.partners.setOnClickListener() {
            findNavController().navigate(R.id.action_mainscreenFragment_to_partnersFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}