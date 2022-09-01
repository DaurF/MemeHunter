package mob.dau.memehunter.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://api.imgflip.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MemesApiService {
    @GET("get_memes")
    suspend fun fetchMemes(): ResponseData
}

object MemesApi {
    val retrofitService: MemesApiService by lazy {
        retrofit.create(MemesApiService::class.java)
    }
}