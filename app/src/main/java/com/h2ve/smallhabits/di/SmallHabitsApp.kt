package com.h2ve.smallhabits.di

import android.app.Application
import android.content.Context
import com.h2ve.smallhabits.db.SmallHabitsDatabase
import com.h2ve.smallhabits.network.ApiService
import com.h2ve.smallhabits.network.ServiceBuilder
import com.h2ve.smallhabits.network.networkModule
import com.h2ve.smallhabits.repository.HabitRepository
import com.h2ve.smallhabits.repository.authModule
import com.h2ve.smallhabits.repository.habitModule
import com.h2ve.smallhabits.repository.sharedPreferencesModule
import com.h2ve.smallhabits.viewmodel.AuthViewModel
import com.h2ve.smallhabits.viewmodel.HabitViewModel
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
                authModule,
                habitModule,
                sharedPreferencesModule
            )
        }
    }
}

val databaseModule = module {
    single { SmallHabitsDatabase.create(androidContext()) }
}

val viewModelModule = module {
    viewModel { AuthViewModel(get(), androidContext()) }
    viewModel { HabitViewModel(get()) }
}


//val repositoryModule = module {
//    factory { ViewModelProvider.AndroidViewModelFactory(get()) }
//    factory { BoardsDataRepositoryImpl(get(), get(), get()) }
//    factory { BoardsRemoteMediator(get(), get(), get()) }
//    factory { BoardsDataPagingSource(get()) }
//}