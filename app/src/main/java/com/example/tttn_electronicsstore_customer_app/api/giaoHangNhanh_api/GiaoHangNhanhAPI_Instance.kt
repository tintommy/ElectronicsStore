package com.example.tttn_electronicsstore_customer_app.api.giaoHangNhanh_api

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class GiaoHangNhanhAPI_Instance {
    companion object {


     val BASE_URL = "https://online-gateway.ghn.vn/shiip/public-api/"
    // val BASE_URL = "http://192.168.30.104:8080/"
        fun getClient(): Retrofit {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                        .addHeader("token", "d8d361af-795b-11ef-980e-b2ccec12a426")
                        .addHeader("Content-Type", "application/json")
                    val request = requestBuilder.build()
                    return chain.proceed(request)
                }
            }).connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        }

    }
}