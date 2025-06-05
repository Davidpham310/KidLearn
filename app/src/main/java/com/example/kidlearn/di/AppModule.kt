// di/AppModule.kt
package com.example.kidlearn.di

import com.example.kidlearn.data.remote.FirestoreDataSource
import com.example.kidlearn.data.repository.AnimalRepositoryImp
import com.example.kidlearn.data.repository.LetterRepositoryImpl
import com.example.kidlearn.data.repository.NumberRepositoryImpl
import com.example.kidlearn.domain.repository.AnimalRepository
import com.example.kidlearn.domain.repository.LetterRepository
import com.example.kidlearn.domain.repository.NumberRepository
import com.example.kidlearn.domain.usecase.GetAllAnimalUseCase
import com.example.kidlearn.domain.usecase.GetAllLettersUseCase
import com.example.kidlearn.domain.usecase.GetAllNumberUseCase
import com.example.kidlearn.domain.usecase.GetAnimalByIdUseCase
import com.example.kidlearn.domain.usecase.GetLetterByIdUseCase
import com.example.kidlearn.domain.usecase.GetNumberByIdUseCase
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

    @Provides
    @Singleton
    fun provideNumberRepository(
        remote: FirestoreDataSource
    ): NumberRepository = NumberRepositoryImpl(remote)

    @Provides
    @Singleton
    fun provideAnimalRepository(
        remote: FirestoreDataSource
    ): AnimalRepository = AnimalRepositoryImp(remote)

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

    @Provides
    @Singleton
    fun provideGetAllNumberUseCase(
        repository: NumberRepository
    ) = GetAllNumberUseCase(repository)

    @Provides
    @Singleton
    fun provideGetNumberByIdUseCase(
        repository: NumberRepository
    ) = GetNumberByIdUseCase(repository)

    @Provides
    @Singleton
    fun provideGetAllAnimalUseCase(
        repository: AnimalRepository
    ) = GetAllAnimalUseCase(repository)

    @Provides
    @Singleton
    fun provideGetAnimalByIdUseCase(
        repository: AnimalRepository
    ) = GetAnimalByIdUseCase(repository)

}