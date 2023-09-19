package ru.example.scout

import dagger.hilt.android.AndroidEntryPoint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.example.scout.databinding.FragmentLoginBinding
import ru.example.scout.retrofit.AuthRequest
import ru.example.scout.retrofit.MainApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.example.scout.viewmodel.ViewModel
import com.google.android.material.snackbar.Snackbar

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var mainApi: MainApi
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRetrofit()
        binding.apply {
             bSignIn.setOnClickListener {
                auth(
                    AuthRequest(
                        login.text.toString(),
                        password.text.toString()
                    )
                )
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

    private fun auth(authRequest: AuthRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = mainApi.auth(authRequest)
            val message = response.errorBody()?.string()?.let {
                (JSONObject(it).getJSONObject("error")).getString("message")
            }
            requireActivity().runOnUiThread {
                if (message != null) {
                    Snackbar.make(
                        requireActivity().findViewById(R.id.constraintlayout),
                        message.toString(),
                        Snackbar.LENGTH_LONG
                    )
                        .setAnchorView(R.id.bSignIn).show()
                }
                val user = response.body()
                if (user != null) {
                    viewModel.token.value = user.data.token
                    findNavController().navigate(R.id.action_loginFragment_to_mainscreenFragment)
                }
            }
        }
    }
}

