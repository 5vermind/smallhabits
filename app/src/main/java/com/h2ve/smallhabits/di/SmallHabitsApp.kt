package com.h2ve.smallhabits.di

import android.app.Application
import com.h2ve.smallhabits.db.SmallHabitsDatabase
import com.h2ve.smallhabits.network.ApiService
import com.h2ve.smallhabits.repository.authModule
import com.h2ve.smallhabits.viewmodel.AuthViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class SmallHabitsApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SmallHabitsApp)
            modules(
                networkModule,
                databaseModule,
                viewModelModule,
                authModule
            )
        }
    }
}

val networkModule = module {
    single { ApiService.create() }
}

val databaseModule = module {
    single { SmallHabitsDatabase.create(androidContext()) }
}

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
}


//val repositoryModule = module {
//    factory { ViewModelProvider.AndroidViewModelFactory(get()) }
//    factory { BoardsDataRepositoryImpl(get(), get(), get()) }
//    factory { BoardsRemoteMediator(get(), get(), get()) }
//    factory { BoardsDataPagingSource(get()) }
//}