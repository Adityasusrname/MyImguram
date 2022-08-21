package com.afterclass.myimguram.data

import com.afterclass.libimgur.ImgurClient
import com.afterclass.libimgur.models.Image
import com.afterclass.libimgur.models.Tag
import com.afterclass.libimgur.models.TagsResponse

class ImgurRepository {

    val ImgurClient = ImgurClient()

    suspend fun get_gallery(section:String):List<Image>?{

        val response = ImgurClient.api.get_gallery(section)

        return response.body()?.data

    }

    suspend fun get_tags():List<Tag>?{
        val response = ImgurClient.api.get_tags()
        return response.body()?.data?.tags
    }

    suspend fun get_tag(tag:String):List<Image>?{
        val response=ImgurClient.api.get_tag(tag)
        return response.body()?.data?.items
    }

}