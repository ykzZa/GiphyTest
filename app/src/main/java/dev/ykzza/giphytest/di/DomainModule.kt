package dev.ykzza.giphytest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import dev.ykzza.giphytest.data.GifRepositoryImpl
import dev.ykzza.giphytest.domain.GifRepository

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    fun bindRepository(repositoryImpl: GifRepositoryImpl): GifRepository
}