package com.anushka.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var retService : AlbumService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        retService = RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)


        getRequestWithPathParam()
        getRequestwithQuery()


    }


    private fun getRequestwithQuery(){
        val responseLiveData: LiveData<Response<Album>> = liveData {
            //val response: Response<Album> = retService.getAlbums()
            val response: Response<Album> = retService.getSortedAlbums(3)
            emit(response)
        }

        responseLiveData.observe(this, Observer {
            val albumList = it.body()?.listIterator()
            if (albumList != null) {
                while (albumList.hasNext()) {
                    val albumItem: AlbumItem = albumList.next()
                    //Log.i("TEST", albumItem.title)

                    val result: String = " " + "Album Title : ${albumItem.title}" + "\n" +
                            " " + "Album ID : ${albumItem.id}" + "\n" +
                            " " + "UserID : ${albumItem.userId}" + "\n"

                    textView.append(result)

                }
            }
        })
    }


    private fun getRequestWithPathParam(){
        //Path Parameter exmaple
        val pathResponse: LiveData<Response<AlbumItem>> = liveData {
            val response : Response<AlbumItem> = retService.getAlbum(3)
            emit(response)
        }
        pathResponse.observe(this, Observer {
            val title: String? = it.body()?.title
            Toast.makeText(this, title, Toast.LENGTH_SHORT).show()
        })
        //
    }

}
