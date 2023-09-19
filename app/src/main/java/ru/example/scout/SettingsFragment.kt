package ru.example.scout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.example.scout.adapter.SettingAdapter
import ru.example.scout.databinding.FragmentSettingsBinding
import ru.example.scout.retrofit.MainApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.example.scout.viewmodel.ViewModel

@AndroidEntryPoint
class SettingsFragment : Fragment(), SettingsListener {

    private val adapter by lazy { SettingAdapter(this) }
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var mainApi: MainApi
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRetrofit()
        initRcView()
        viewModel.token.observe(viewLifecycleOwner) { token ->

            CoroutineScope(Dispatchers.IO).launch {
                val list = mainApi.getAllSettings("Bearer " + token)
                requireActivity().runOnUiThread {
                    val list_ = list.data
                    viewModel.SettingsList.value = list_
                    adapter.submitList(list_)
                }
            }
        }
    }

    private fun initRetrofit() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(SERVER).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        mainApi = retrofit.create(MainApi::class.java)
    }

    private fun initRcView() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(context)
        rcView.adapter = adapter

    }

    override fun onItemClick(position: Int) {
        val bundle = Bundle()
        bundle.putInt(ARG_ITEM_ID, position)
        findNavController().navigate(
            R.id.action_settingsFragment_to_viewingtemplateFragment,
            bundle
        )
    }

    override fun onLongClick(position: Int) {
        //do long click here
    }
}



