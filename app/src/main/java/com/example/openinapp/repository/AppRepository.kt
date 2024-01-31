package com.example.openinapp.repository



import com.example.openinapp.data.Resource
import com.example.openinapp.model.DataResponse
import com.example.openinapp.model.OverallUrlChart
import com.example.openinapp.model.RecentLinksItem
import com.example.openinapp.model.TopLinksItem
import com.example.openinapp.network.AppApi
import javax.inject.Inject

class AppRepository @Inject constructor(private val api: AppApi) {


    suspend fun getDataResponse(): Resource<DataResponse>
    {
        return try{
            Resource.Loading(data = true)
            val Response=api.getDataResponse()
            if(Response.status) Resource.Loading(data = false)
            Resource.Success(data= Response)
        }catch(exception: Exception){
            Resource.Error(message = exception.message.toString())
        }
    }

    suspend fun getTopLinks(): Resource<List<TopLinksItem>>
    {
        return try{
            Resource.Loading(data = true)
            val topLinkList=api.getDataResponse().data!!.topLinks
            if(topLinkList!!.isNotEmpty()) Resource.Loading(data = false)
            Resource.Success(data= topLinkList)
        }catch(exception: Exception){
            Resource.Error(message = exception.message.toString())
        }
    }

    suspend fun getRecentLinks(): Resource<List<RecentLinksItem>>
    {
        return try{
            Resource.Loading(data = true)
            val recentLinkList=api.getDataResponse().data!!.recentLinks
            if(recentLinkList!!.isNotEmpty()) Resource.Loading(data = false)
            Resource.Success(data=recentLinkList)
        }catch(exception: Exception){
            Resource.Error(message = exception.message.toString())
        }
    }


    suspend fun getOverallUrlChart(): Resource<List<OverallUrlChart>>
    {
        return try{
            Resource.Loading(data = true)
            val overallUrlList=api.getDataResponse().data!!.overallUrlChart

            if(overallUrlList!!.isNotEmpty()) Resource.Loading(data = false)
            Resource.Success(data=overallUrlList)
        }catch(exception: Exception){
            Resource.Error(message = exception.message.toString())
        }
    }




}