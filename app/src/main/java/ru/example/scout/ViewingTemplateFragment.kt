package ru.example.scout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.example.scout.databinding.FragmentViewingtemplateBinding
import ru.example.scout.viewmodel.ViewModel

@AndroidEntryPoint
class ViewingTemplateFragment : Fragment() {

    private val viewModel: ViewModel by activityViewModels()
    private lateinit var binding: FragmentViewingtemplateBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewingtemplateBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                val index = it.getInt(ARG_ITEM_ID)
                val item = viewModel.SettingsList.value?.get(index)
                binding.name.setText(item?.name)
                binding.label.setText(item?.label)

            }
        }

    }
}