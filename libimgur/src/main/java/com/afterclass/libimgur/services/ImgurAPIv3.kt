package com.afterclass.libimgur.services

import com.afterclass.libimgur.models.GalleryResponse
import com.afterclass.libimgur.models.TagResponse
import com.afterclass.libimgur.models.TagsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ImgurAPIv3 {


    @GET("gallery/{section}")
    suspend fun get_gallery(@Path("section") section:String): Response<GalleryResponse>

    @GET("tags")
    suspend fun get_tags():Response<TagsResponse>

    @GET("gallery/t/{tag}")
    suspend fun get_tag(@Path("tag") tag: String):Response<TagResponse>




}