package tech.jhavidit.redcarpetupassignment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tech.jhavidit.redcarpetupassignment.di.APIComponent

import tech.jhavidit.redcarpetupassignment.di.MyRetroApplication
import tech.jhavidit.redcarpetupassignment.network.APIModule
import tech.jhavidit.redcarpetupassignment.network.API_KEY
import tech.jhavidit.redcarpetupassignment.repository.NewsHeadlinesRepository
import javax.inject.Inject

class ViewModelFactory : ViewModelProvider.Factory {

    @Inject
  lateinit  var retrofitRepository: NewsHeadlinesRepository

    init {
        this.retrofitRepository = NewsHeadlinesRepository()
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>) : T {
        //   initDaggerComponent()
        var  apiComponent  =  MyRetroApplication().getMyComponent()
        apiComponent.inject(this)
        if (modelClass.isAssignableFrom(NewsHeadlinesViewModel::class.java)) {
            return NewsHeadlinesViewModel(retrofitRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}