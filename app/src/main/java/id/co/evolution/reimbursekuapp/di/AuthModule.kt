package id.co.evolution.reimbursekuapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.co.evolution.reimbursekuapp.feature.auth.data.remote.AuthApi
import id.co.evolution.reimbursekuapp.feature.auth.domain.repository.AuthRepository
import id.co.evolution.reimbursekuapp.feature.auth.domain.repository.AuthRepositoryImpl
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi): AuthRepository {
        return AuthRepositoryImpl(api)
    }
}
