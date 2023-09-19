package ru.example.scout.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.example.scout.retrofit.Setting
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(): ViewModel() {
    val token = MutableLiveData<String>()
    val SettingsList = MutableLiveData<List<Setting>>()
}

