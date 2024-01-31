package com.example.openinapp.screens.links

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.key.Key.Companion.U
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openinapp.data.Resource
import com.example.openinapp.model.Data
import com.example.openinapp.model.DataResponse
import com.example.openinapp.model.RecentLinksItem
import com.example.openinapp.model.TopLinksItem
import com.example.openinapp.repository.AppRepository
import com.squareup.moshi.Moshi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class LinksViewModel @Inject constructor(private val repository: AppRepository)
    :ViewModel(){
    var listTopLinks : List<TopLinksItem> by mutableStateOf(listOf())
    var isloadingTopLinks:Boolean by mutableStateOf(true)


    var listRecentLinks : List<RecentLinksItem> by mutableStateOf(listOf())
    var isloadingRecentLink:Boolean by mutableStateOf(true)

    var dataResponseVal : DataResponse by mutableStateOf( DataResponse())
    var isloadingDataResponse:Boolean by mutableStateOf(true)


    init {
        loadApi()
    }

     fun loadApi() {
        topLinks()
        recentLinks()
        dataResponseFun()
    }

     fun dataResponseFun() {
        viewModelScope.launch(Dispatchers.Default){
            try{
                when(val response=repository.getDataResponse()){
                    is Resource.Success->{
                        dataResponseVal=response.data!!
                        if(dataResponseVal.status) isloadingDataResponse=false
                    }
                    is Resource.Error->{
                        Log.e("Network", "DataResponse: Failed getting DataResponse")
                    }else->{isloadingDataResponse=false}
                }
            }catch (exception: Exception){
                isloadingDataResponse=false
                Log.d("Network", "DataResponse: ${exception.message.toString()}")
            }
        }
    }


     fun topLinks() {
        viewModelScope.launch(Dispatchers.Default){
            try{
                when(val response=repository.getTopLinks()){
                    is Resource.Success->{
                        listTopLinks=response.data!!
                        if(listTopLinks.isNotEmpty()) isloadingTopLinks=false
                    }
                    is Resource.Error->{
                        Log.e("Network", "TopLinks: Failed getting TopLinks")

                    }else->{isloadingTopLinks=false}
                }
            }catch (exception: Exception){
                isloadingTopLinks=false
                Log.d("Network", "TopLinks: ${exception.message.toString()}")
            }
        }
    }



    fun recentLinks() {
        viewModelScope.launch(Dispatchers.Default){
            try{
                when(val response=repository.getRecentLinks()){
                    is Resource.Success->{
                        listRecentLinks=response.data!!
                        if(listRecentLinks.isNotEmpty()) isloadingRecentLink=false
                    }
                    is Resource.Error->{
                        Log.e("Network", "RecentLinks: Failed getting RecentLinks")
                    }else->{isloadingRecentLink=false}
                }
            }catch (exception: Exception){
                isloadingRecentLink=false
                Log.d("Network", "RecentLinks: ${exception.message.toString()}")
            }
        }
    }

}