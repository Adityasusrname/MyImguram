package com.afterclass.myimguram.ui.stories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afterclass.libimgur.models.Image
import com.afterclass.libimgur.models.Tag
import com.afterclass.libimgur.models.TagsResponse
import com.afterclass.myimguram.data.ImgurRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StoryViewModel:ViewModel() {
    private val repo = ImgurRepository()
    private val _tags=MutableLiveData<List<Tag>>()
    private val _images = MutableLiveData<List<Image>>()

    val tags = _tags
    val images = _images

    fun get_tags(){
        viewModelScope.launch(Dispatchers.IO){
            _tags.postValue(repo.get_tags())
        }
    }

    fun get_tag(tag:String){
        viewModelScope.launch(Dispatchers.IO){
            _images.postValue(repo.get_tag(tag))
        }
    }

}