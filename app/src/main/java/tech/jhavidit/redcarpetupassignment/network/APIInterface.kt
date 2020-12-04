package tech.jhavidit.redcarpetupassignment.network

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import tech.jhavidit.redcarpetupassignment.model.NewsItem


interface APIInterface {
    @GET("top-headlines")
    fun getCountryList(
        @Header("X-Api-Key") apiKey: String,
        @Query("country") country: String
    ): Single<NewsItem>
}