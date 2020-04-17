package ie.wit.api

import com.google.gson.GsonBuilder
import ie.wit.models.ValetModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit


interface ValetService {
    @GET("/donations")
    fun getall(): Call<List<ValetModel>>

    @GET("/donations/{email}")
    fun findall(@Path("email") email: String?)
            : Call<List<ValetModel>>

    @GET("/donations/{email}/{id}")
    fun get(@Path("email") email: String?,
            @Path("id") id: String): Call<ValetModel>

    @DELETE("/donations/{email}/{id}")
    fun delete(@Path("email") email: String?,
               @Path("id") id: String): Call<ValetWrapper>

    @POST("/donations/{email}")
    fun post(@Path("email") email: String?,
             @Body donation: ValetModel)
            : Call<ValetWrapper>

    @PUT("/donations/{email}/{id}")
    fun put(@Path("email") email: String?,
            @Path("id") id: String,
            @Body donation: ValetModel
    ): Call<ValetWrapper>

    companion object {

        val serviceURL = "https://donationweb-hdip-mu-server.herokuapp.com"

        fun create() : ValetService {

            val gson = GsonBuilder().create()

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(serviceURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()
            return retrofit.create(ValetService::class.java)
        }
    }
}