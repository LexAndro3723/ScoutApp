package ru.example.scout.retrofit

import retrofit2.Response
import retrofit2.http.*
import ru.example.scout.Settings
import ru.example.scout.User

interface MainApi {
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @POST("api/v1/login")
    suspend fun auth(@Body authRequest: AuthRequest): Response<User>

    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @GET("api/v1/check_list_action_template/list")
    suspend fun getAllSettings(@Header("Authorization") token: String): Settings
}