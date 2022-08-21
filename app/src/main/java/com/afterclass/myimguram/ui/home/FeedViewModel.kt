package com.afterclass.myimguram.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afterclass.libimgur.models.Image
import com.afterclass.myimguram.data.ImgurRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {
    private val _images=MutableLiveData<List<Image>>()
    private val _repo = ImgurRepository()

    val images:LiveData<List<Image>> =_images

    fun get_gallery(section:String){
        viewModelScope.launch(Dispatchers.IO){
           _images.postValue(_repo.get_gallery(section))
        }
    }

}