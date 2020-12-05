package tech.jhavidit.redcarpetupassignment.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import tech.jhavidit.redcarpetupassignment.di.APIComponent
import tech.jhavidit.redcarpetupassignment.di.MyRetroApplication
import tech.jhavidit.redcarpetupassignment.model.NewsItem
import tech.jhavidit.redcarpetupassignment.network.APIInterface
import tech.jhavidit.redcarpetupassignment.network.API_KEY
import javax.inject.Inject

class NewsHeadlinesRepository {


    @Inject
    lateinit var retrofit: Retrofit

    init {
        val apiComponent = MyRetroApplication.apiComponent
        apiComponent.inject(this)
    }

    private val compositeDisposable = CompositeDisposable()
    val showNewsHeadlines = MutableLiveData<NewsItem>()
    val showProgress = MutableLiveData<Boolean>()


    fun getNewsHeadlines() {
        showProgress.value = true
        val api = retrofit.create(APIInterface::class.java)
        compositeDisposable.add(
            api.getCountryList(API_KEY, "in")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        showNewsHeadlines.value = it
                        showProgress.value = false
                    },
                    {
                        Log.d("error", it.message.toString())
                        showProgress.value = false
                    }
                )
        )
    }
}
