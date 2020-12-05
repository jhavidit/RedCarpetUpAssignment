package tech.jhavidit.redcarpetupassignment.di

import dagger.Component
import tech.jhavidit.redcarpetupassignment.network.APIModule

import tech.jhavidit.redcarpetupassignment.repository.NewsHeadlinesRepository
import tech.jhavidit.redcarpetupassignment.view.HomeFragment
import tech.jhavidit.redcarpetupassignment.viewModel.NewsHeadlinesViewModel
import tech.jhavidit.redcarpetupassignment.viewModel.ViewModelFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, APIModule::class])
interface APIComponent {
    fun inject(newsHeadlinesRepository: NewsHeadlinesRepository)
    fun inject(newsHeadlinesViewModel: NewsHeadlinesViewModel)
    fun inject(homeFragment: HomeFragment)
    fun inject(viewModelFactory: ViewModelFactory)

}