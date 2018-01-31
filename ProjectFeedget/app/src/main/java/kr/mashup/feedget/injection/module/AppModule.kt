package kr.mashup.feedget.injection.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import kr.mashup.feedget.BuildConfig
import kr.mashup.feedget.data.executor.JobExecutor
import kr.mashup.feedget.data.repository.*
import kr.mashup.feedget.injection.scopes.PerApplication
import kr.mashup.feedget.domain.executor.PostExecutionThread
import kr.mashup.feedget.domain.executor.ThreadExecutor
import kr.mashup.feedget.domain.interactor.usecases.*
import kr.mashup.feedget.domain.repository.*
import kr.mashup.feedget.dummy.DummyDataService
import kr.mashup.feedget.remote.FeedGetService
import kr.mashup.feedget.remote.FeedgetServiceFactory
import kr.mashup.feedget.remote.TokenProvider
import kr.mashup.feedget.repository.PrefsDataStore
import kr.mashup.feedget.ui.UiThread


@Module
open class AppModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context = application

    @Provides
    @PerApplication
    internal fun provideTokenProvider(context: Context): TokenProvider = kr.mashup.feedget.util.TokenProvider(context)

    @Provides
    @PerApplication
    internal fun provideFeegetService(tokenProvider: TokenProvider): FeedGetService =
        DummyDataService() // 이부분이 더미입니당. 
//        FeedgetServiceFactory.makeFeedGetService(BuildConfig.DEBUG, tokenProvider)

    @Provides
    @PerApplication
    internal fun providePrefsRepository(context: Context): PrefsRepository = PrefsDataStore(context)

    @Provides
    @PerApplication
    internal fun provideCategoryRepository(apiService: FeedGetService): CategoryRepositoy =
        CategoryRemoteDataSource(apiService)

    @Provides
    @PerApplication
    internal fun provideContentRepository(apiService: FeedGetService): ContentRepository =
        ContentRemoteDataSource(apiService)

    @Provides
    @PerApplication
    internal fun provideCreationRepository(apiService: FeedGetService): CreationRepository =
        CreationRemoteDataSource(apiService)

    @Provides
    @PerApplication
    internal fun provideFeedbackRepository(apiService: FeedGetService): FeedbackRepository =
        FeedbackRemoteDataSource(apiService)

    @Provides
    @PerApplication
    internal fun provideUserRepository(apiService: FeedGetService): UserRepository =
        UserRemoteDataSource(apiService)

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
        prefsRepository: PrefsRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ): Register =
        Register(userRepository,prefsRepository, threadExecutor, postExecutionThread)

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
