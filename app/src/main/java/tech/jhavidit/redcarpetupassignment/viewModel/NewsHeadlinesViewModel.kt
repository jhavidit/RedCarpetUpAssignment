package tech.jhavidit.redcarpetupassignment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tech.jhavidit.redcarpetupassignment.model.NewsItem
import tech.jhavidit.redcarpetupassignment.repository.NewsHeadlinesRepository

class NewsHeadlinesViewModel(newsHeadlinesRepository: NewsHeadlinesRepository) :
    ViewModel() {
     lateinit var newsHeadlinesRepository : NewsHeadlinesRepository
    val showNewsHeadlines: LiveData<NewsItem>
    val showProgress: LiveData<Boolean>
    init {
       this.newsHeadlinesRepository  = newsHeadlinesRepository
        this.showNewsHeadlines = newsHeadlinesRepository.showNewsHeadlines
        this.showProgress = newsHeadlinesRepository.showProgress
    }

    fun getNewsHeadlines() {
        newsHeadlinesRepository.getNewsHeadlines()
    }
}