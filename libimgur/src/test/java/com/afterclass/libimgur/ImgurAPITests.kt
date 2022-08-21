package com.afterclass.libimgur

import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ImgurAPITests {

  private  val ImgurClient=ImgurClient()


 @Test
  fun get_gallery_hot_working(){

    runBlocking {
        val response = ImgurClient.api.get_gallery("hot")
        assertNotNull(response?.body())
    }

 }
    @Test
    fun get_gallery_top_working(){

        runBlocking {
            val response = ImgurClient.api.get_gallery("top")
            assertNotNull(response?.body())
        }

    }

    @Test
    fun get_tags_working(){
        runBlocking {
            val response = ImgurClient.api.get_tags()
            assertNotNull(response.body())
        }
    }

    @Test
    fun get_tag_working(){
        runBlocking {
            val response = ImgurClient.api.get_tag("aww")
            assertNotNull(response.body())
        }
    }



}