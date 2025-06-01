// di/AppModule.kt
package com.example.kidlearn.di

import com.example.kidlearn.data.remote.FirestoreDataSource
import com.example.kidlearn.data.repository.LetterRepositoryImpl
import com.example.kidlearn.domain.repository.LetterRepository
import com.example.kidlearn.domain.usecase.GetAllLettersUseCase
import com.example.kidlearn.domain.usecase.GetLetterByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module cung cấp các đối tượng được yêu cầu bởi ứng dụng.
 * Đối tượng [FirestoreDataSource] được cung cấp cho toàn bộ ứng dụng.
 * Đối tượng [LetterRepository] được cung cấp cho toàn bộ ứng dụng và được sử dụng bởi [GetAllLettersUseCase].
 * Đối tượng [GetAllLettersUseCase] được cung cấp cho toàn bộ ứng dụng và được sử dụng bởi các màn hình khác nhau.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Cung cấp đối tượng [FirestoreDataSource] cho toàn bộ ứng dụng.
     */
    @Provides
    @Singleton
    fun provideFirestoreDataSource() = FirestoreDataSource()

    /**
     * Cung cấp đối tượng [LetterRepository] cho toàn bộ ứng dụng.
     * Đối tượng này được sử dụng bởi [GetAllLettersUseCase].
     */
    @Provides
    @Singleton
    fun provideLetterRepository(
        remote: FirestoreDataSource
    ): LetterRepository = LetterRepositoryImpl(remote)

    /**
     * Cung cấp đối tượng [GetAllLettersUseCase] cho toàn bộ ứng dụng.
     * Đối tượng này được sử dụng bởi các màn hình khác nhau.
     */
    @Provides
    @Singleton
    fun provideGetAllLettersUseCase(
        repository: LetterRepository
    ) = GetAllLettersUseCase(repository)

    @Provides
    @Singleton
    fun provideGetLetterByIdUseCase(
        repository: LetterRepository
    ) = GetLetterByIdUseCase(repository)

}