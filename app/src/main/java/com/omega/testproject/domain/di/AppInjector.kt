package com.omega.testproject.domain.di

import android.content.Context
import com.omega.testproject.data.NumberService
import com.omega.testproject.data.RestClient
import com.omega.testproject.data.repostitory.local.LocalNumberRepository
import com.omega.testproject.data.repostitory.local.LocalNumberRepositoryIml
import com.omega.testproject.data.repostitory.remote.NumberRepository
import com.omega.testproject.data.repostitory.remote.NumberRepositoryIml
import com.omega.testproject.domain.usecase.LocalWorkUseCase
import com.omega.testproject.domain.usecase.NumberListUseCase
import com.omega.testproject.presentation.main.NumberViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module


object AppInjector {

    fun setup(context: Context) {
        startKoin {
            androidContext(context)
            modules(listOf(viewModelsModule, networkModule, repositoryModule))
        }
    }

    private val networkModule = module {
        single<NumberService> { RestClient.provideRetrofit().create(NumberService::class.java) }
    }


    private val repositoryModule = module {
        factory<NumberRepository> { NumberRepositoryIml(get()) }
        single<LocalNumberRepository> { LocalNumberRepositoryIml(androidContext()) }
    }

    private val viewModelsModule = module {
        viewModel {
            NumberViewModel(
                NumberListUseCase(get()),
                LocalWorkUseCase(get())
            )
        }
    }
}