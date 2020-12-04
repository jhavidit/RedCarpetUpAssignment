package tech.jhavidit.redcarpetupassignment.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import tech.jhavidit.redcarpetupassignment.model.NewsItem
import tech.jhavidit.redcarpetupassignment.repository.NewsHeadlinesRepository

class NewsHeadlinesViewModel(application: Application) : AndroidViewModel(application) {
    val showNewsHeadlines: LiveData<NewsItem>
    val showProgress: LiveData<Boolean>
    private val repository = NewsHeadlinesRepository(application)

    init {
        this.showNewsHeadlines = repository.showNewsHeadlines
        this.showProgress = repository.showProgress
    }

    fun getNewsHeadlines() {
        repository.getNewsHeadlines()
    }
}