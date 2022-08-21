package com.afterclass.libimgur

import com.afterclass.libimgur.services.ImgurAPIv3
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ImgurClient {

    private val BASE_URL = "https://api.imgur.com/3/"

    private val client = OkHttpClient.Builder().addInterceptor(Interceptor {

            val request = it.request().newBuilder()
                .header("Authorization","Client-ID 58e1bdde16af97a").build()
            it.proceed(request)

    }).build()

    private val retrofit = Retrofit
        .Builder()
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val api=retrofit.create(ImgurAPIv3::class.java)


}