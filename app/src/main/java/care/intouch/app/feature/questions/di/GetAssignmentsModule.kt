package care.intouch.app.feature.questions.di

import care.intouch.app.feature.common.di.RetrofitWithAuth
import care.intouch.app.feature.questions.data.api.GetAssignmentsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn
class GetAssignmentsModule {
    @Provides
    @Singleton
    fun provideAssignmentsApi(
        @RetrofitWithAuth retrofit: Retrofit
    ): GetAssignmentsApi {
        return retrofit.create(GetAssignmentsApi::class.java)
    }
}