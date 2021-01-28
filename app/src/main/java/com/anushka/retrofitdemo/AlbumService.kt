package com.anushka.retrofitdemo

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumService {

    //https://jsonplaceholder.typicode.com/albums

    @GET("/albums")
    suspend fun getAlbums() : Response<Album>


    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId") userId:Int) : Response<Album>

    @GET("/albums/{id}")
    suspend fun getAlbum(@Path(value = "id") albumId :Int): Response<AlbumItem>

}