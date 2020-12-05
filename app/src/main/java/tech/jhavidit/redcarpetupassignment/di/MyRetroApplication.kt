package tech.jhavidit.redcarpetupassignment.di

import android.app.Application
import android.content.Context
import tech.jhavidit.redcarpetupassignment.network.APIModule
import tech.jhavidit.redcarpetupassignment.network.API_KEY
import tech.jhavidit.redcarpetupassignment.network.BASE_URL

class MyRetroApplication:Application() {
    companion object {
        var ctx: Context? = null
        lateinit var apiComponent:APIComponent
    }
    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        apiComponent = initDaggerComponent()

    }

    fun getMyComponent(): APIComponent {
        return apiComponent
    }

    fun initDaggerComponent():APIComponent{
        apiComponent =   DaggerAPIComponent
            .builder()
            .aPIModule(APIModule(BASE_URL))
            .build()
        return  apiComponent

    }
}