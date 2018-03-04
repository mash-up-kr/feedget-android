package kr.mashup.feedget.injection.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import kr.mashup.feedget.BuildConfig
import kr.mashup.feedget.data.executor.JobExecutor
import kr.mashup.feedget.data.repository.*
import kr.mashup.feedget.data.source.*
import kr.mashup.feedget.domain.executor.PostExecutionThread
import kr.mashup.feedget.domain.executor.ThreadExecutor
import kr.mashup.feedget.domain.interactor.usecases.*
import kr.mashup.feedget.domain.repository.*
import kr.mashup.feedget.injection.scopes.PerApplication
import kr.mashup.feedget.remote.*
import kr.mashup.feedget.repository.PrefsDataStore
import kr.mashup.feedget.ui.UiThread


@Module
open class AppModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context = application

    @Provides
    @PerApplication
    internal fun provideTokenProvider(context: Context): TokenRepository = kr.mashup.feedget.util.TokenProvider(context)

    @Provides
    @PerApplication
    internal fun provideFeegetService(tokenProvider: TokenRepository): FeedGetService =
//        DummyDataService() // 이부분이 더미입니당.
        FeedgetServiceFactory.makeFeedGetService(BuildConfig.DEBUG, BuildConfig.API_SERVER_IP, tokenProvider)

    @Provides
    @PerApplication
    internal fun providePrefsRepository(context: Context): PrefsRepository = PrefsDataStore(context)

    @Provides
    @PerApplication
    internal fun provideCategoryRemote(apiService: FeedGetService): CategoryRemote =
        CategoryRemoteImpl(apiService)

    @Provides
    @PerApplication
    internal fun provideContentRemote(apiService: FeedGetService): ContentRemote =
        ContentRemoteImpl(apiService)

    @Provides
    @PerApplication
    internal fun provideCreationRemote(apiService: FeedGetService): CreationRemote =
        CreationRemoteImpl(apiService)

    @Provides
    @PerApplication
    internal fun provideFeedbackRemote(apiService: FeedGetService): FeedbackRemote =
        FeedbackRemoteImpl(apiService)

    @Provides
    @PerApplication
    internal fun provideUserRemote(apiService: FeedGetService): UserRemote =
        UserRemoteImpl(apiService)

    @Provides
    @PerApplication
    internal fun provideCategoryRepository(categoryRemote: CategoryRemote): CategoryRepositoy =
        CategoryRemoteDataSource(categoryRemote)

    @Provides
    @PerApplication
    internal fun provideContentRepository(contentRemote: ContentRemote): ContentRepository =
        ContentRemoteDataSource(contentRemote)

    @Provides
    @PerApplication
    internal fun provideCreationRepository(creationRemote: CreationRemote): CreationRepository =
        CreationRemoteDataSource(creationRemote)

    @Provides
    @PerApplication
    internal fun provideFeedbackRepository(feedbackRemote: FeedbackRemote): FeedbackRepository =
        FeedbackRemoteDataSource(feedbackRemote)

    @Provides
    @PerApplication
    internal fun provideUserRepository(userRemote: UserRemote): UserRepository =
        UserRemoteDataSource(userRemote)

    @Provides
    @PerApplication
    internal fun provideDeleteCreation(
        creationRepository: CreationRepository,
        contentRepository: ContentRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ): DeleteCreation =
        DeleteCreation(creationRepository, contentRepository, threadExecutor, postExecutionThread)

    @Provides
    @PerApplication
    internal fun provideDeleteFeedback(
        feedbackRepository: FeedbackRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ): DeleteFeedback =
        DeleteFeedback(feedbackRepository, threadExecutor, postExecutionThread)

    @Provides
    @PerApplication
    internal fun provideGetCategories(
        categoryRepositoy: CategoryRepositoy,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ): GetCategories =
        GetCategories(categoryRepositoy, threadExecutor, postExecutionThread)

    @Provides
    @PerApplication
    internal fun provideGetCreation(
        creationRepository: CreationRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ): GetCreation =
        GetCreation(creationRepository, threadExecutor, postExecutionThread)

    @Provides
    @PerApplication
    internal fun provideGetCreations(
        creationRepository: CreationRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ): GetCreations =
        GetCreations(creationRepository, threadExecutor, postExecutionThread)

    @Provides
    @PerApplication
    internal fun provideGetFeedbacks(
        feedbackRepository: FeedbackRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ): GetFeedbacks =
        GetFeedbacks(feedbackRepository, threadExecutor, postExecutionThread)

    @Provides
    @PerApplication
    internal fun providePatchCreation(
        creationRepository: CreationRepository,
        contentRepository: ContentRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ): PatchCreation =
        PatchCreation(creationRepository, contentRepository, threadExecutor, postExecutionThread)

    @Provides
    @PerApplication
    internal fun providePostCreation(
        creationRepository: CreationRepository,
        contentRepository: ContentRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ): PostCreation =
        PostCreation(creationRepository, contentRepository, threadExecutor, postExecutionThread)

    @Provides
    @PerApplication
    internal fun providePostFeedback(
        feedbackRepository: FeedbackRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ): PostFeedback =
        PostFeedback(feedbackRepository, threadExecutor, postExecutionThread)

    @Provides
    @PerApplication
    internal fun provideRegister(
        userRepository: UserRepository,
        tokenRepository: TokenRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ): Register =
        Register(userRepository, tokenRepository, threadExecutor, postExecutionThread)

    @Provides
    @PerApplication
    internal fun provideIsLogined(
        tokenRepository: TokenRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ): IsLogined =
        IsLogined(tokenRepository, threadExecutor, postExecutionThread)

    @Provides
    @PerApplication
    internal fun provideRegisterFCM(
        userRepository: UserRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ): RegisterFCM =
        RegisterFCM(userRepository, threadExecutor, postExecutionThread)

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread = uiThread

}
