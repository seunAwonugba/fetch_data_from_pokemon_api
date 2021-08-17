package com.apipokemonproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.apipokemonproject.abilitiesApi.PokemonAbilities
import com.bumptech.glide.Glide
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient


/**
 * declare base URL to access data in the second activity
 */
const val DETAILSBASEURL = "https://pokeapi.co/api/v2/"
class DetailsActivity : AppCompatActivity() {
    /**
     * Create instance of your views
     */
    private lateinit var instanceOfImageView: ImageView
    private lateinit var instanceOfTextView: TextView

    private lateinit var instanceOfAbility1:TextView
    private lateinit var instanceOfId:TextView
    private lateinit var instanceOfBaseExperience:TextView
    private lateinit var instanceOfHeight:TextView
    private lateinit var instanceOfWeight:TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        /**
         * Grab views ID
         */

        instanceOfImageView = findViewById(R.id.imageView)
        instanceOfTextView = findViewById(R.id.textView)

        instanceOfAbility1 = findViewById(R.id.abilityTextViewId)
        instanceOfId = findViewById(R.id.ability2Id)
        instanceOfBaseExperience = findViewById(R.id.experienceId)
        instanceOfHeight = findViewById(R.id.heightId)
        instanceOfWeight = findViewById(R.id.weightId)

        /**
         * grab intent passed from main activity
         */

        val putName = intent.getStringExtra("NAME").toString()
        val getURL = intent.getStringExtra("IMAGEPOSITION")

        val functionResult = getURL?.let { SplitImageAddress.splitFunction(it) }

        /**
         * Glide supports fetching, of images from any network
         */

        Glide
            .with(this)
            .load(functionResult)
            .fitCenter()
            .placeholder(R.drawable.ic_launcher_background)
            .into(instanceOfImageView)
        instanceOfTextView.text = putName

        /**
         * Call functionGetDataInDetailsActivity
         */

        functionGetDataInDetailsActivity(putName)
    }

    /**
     * Create a function that takes advantage of retrofit to move to a new thread that now helps in making
     * network calls to the pokemon network
     */

    private fun functionGetDataInDetailsActivity(putName:String) {
        /**
         * Create a logging interceptor, so you could access the JSON file in the run
         */
        val interceptor = HttpLoggingInterceptor()
        interceptor.level= HttpLoggingInterceptor.Level.BODY
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        /**
         * Create a retrofit builder that helps you access a REST API so you could get data from it
         */
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(DETAILSBASEURL)
            .client(client)
            .build()
            .create(AbilitiesInterface::class.java)

        Log.d("DetailsActivity", SplitNameAddress.splitName(putName))

        val getDataFromRetrofitBuilderInDetailsActivity = retrofit.getData(putName)

        getDataFromRetrofitBuilderInDetailsActivity.enqueue(object: Callback<PokemonAbilities> {
            override fun onResponse(call: Call<PokemonAbilities>, response: Response<PokemonAbilities>) {
                val responseInDetailsActivity = response.body()!!
                Log.d("TAG", "onResponse: ${responseInDetailsActivity.abilities.size}")

                val attributeArrayList = responseInDetailsActivity.abilities[0].ability.name


                val baseExperience = responseInDetailsActivity.base_experience
                val height = responseInDetailsActivity.height
                val id = responseInDetailsActivity.id
                val weight = responseInDetailsActivity.weight


                instanceOfAbility1.text = "Ability 1: ${attributeArrayList}"
                instanceOfBaseExperience.text = "Experience level: ${baseExperience.toString()}"
                instanceOfHeight.text = "Height: ${height.toString()}"
                instanceOfWeight.text = "Weight: ${weight.toString()}"
                instanceOfId.text = "ID : ${id.toString()}"
            }

            override fun onFailure(call: Call<PokemonAbilities>, t: Throwable) {
                Log.d("TAG" ,"onFailure ${t.message}")
            }

        })

    }
}