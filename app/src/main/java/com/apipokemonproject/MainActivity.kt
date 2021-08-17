package com.apipokemonproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apipokemonproject.api.PokemonDataClass
import com.apipokemonproject.api.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder
import java.util.ArrayList
import kotlin.math.log

const val BASE_URL = "https://pokeapi.co/api/v2/"

class MainActivity : AppCompatActivity() {
    lateinit var instanceOfRecyclerView: RecyclerView
    lateinit var instanceOfAdapter: PokemonAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        instanceOfRecyclerView = findViewById(R.id.recyclerViewId)

        instanceOfRecyclerView.layoutManager = GridLayoutManager(this,2)
        instanceOfRecyclerView.setHasFixedSize(true)

        try {
            functionGetData()
        }catch (e:Throwable){
            e.printStackTrace()
                Log.d("TAG", "${e.message}")
            Toast.makeText(this, "An error occurred, invalid address or broken link", Toast.LENGTH_LONG).show()
        }
    }

    private fun functionGetData() {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val getDataFromRetrofitBuilder = retrofit.getProperties()

        getDataFromRetrofitBuilder.enqueue(object : Callback<PokemonDataClass> {
            override fun onResponse(call: Call<PokemonDataClass>, response: Response<PokemonDataClass>) {
                val responseBody = response.body()
                if (responseBody != null) {
                val arrayList = responseBody.results
                    instanceOfAdapter = PokemonAdapter(arrayList,this@MainActivity)
                    instanceOfRecyclerView.adapter = instanceOfAdapter
                }
            }

            override fun onFailure(call: Call<PokemonDataClass>, t: Throwable) {
                Log.d("TAG" ,"onFailure ${t.message}")
            }
        })
    }
}

