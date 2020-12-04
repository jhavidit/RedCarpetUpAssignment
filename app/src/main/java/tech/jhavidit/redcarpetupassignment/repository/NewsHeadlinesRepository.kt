package tech.jhavidit.redcarpetupassignment.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tech.jhavidit.redcarpetupassignment.model.NewsItem
import tech.jhavidit.redcarpetupassignment.network.APIClient

class NewsHeadlinesRepository(val application: Application) {
    private val compositeDisposable = CompositeDisposable()
    val showNewsHeadlines = MutableLiveData<NewsItem>()
    val showProgress = MutableLiveData<Boolean>()


    fun getNewsHeadlines() {
        showProgress.value = true
        val retrofitService = APIClient.getClient(application)
        compositeDisposable.add(
            retrofitService.getCountryList(APIClient.API_KEY, "in")
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
