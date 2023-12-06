package com.example.newscompose.di

import android.app.Application
import androidx.room.Room
import com.example.newscompose.data.local.NewsDao
import com.example.newscompose.data.local.NewsDatabase
import com.example.newscompose.data.local.NewsTypeConvertor
import com.example.newscompose.data.manager.LocalUserManagerImpl
import com.example.newscompose.data.remote.api.NewsApi
import com.example.newscompose.data.repository.NewsRepositoryImpl
import com.example.newscompose.domain.manager.LocalUserManager
import com.example.newscompose.domain.repository.NewsRepository
import com.example.newscompose.domain.usecases.app_entry.AppEntryUsecases
import com.example.newscompose.domain.usecases.app_entry.ReadAppEntry
import com.example.newscompose.domain.usecases.app_entry.SaveAppEntry
import com.example.newscompose.domain.usecases.news.DeleteArticle
import com.example.newscompose.domain.usecases.news.GetNews
import com.example.newscompose.domain.usecases.news.InsertArticle
import com.example.newscompose.domain.usecases.news.NewsUseCases
import com.example.newscompose.domain.usecases.news.SearchNews
import com.example.newscompose.domain.usecases.news.SelectArticle
import com.example.newscompose.domain.usecases.news.SelectArticles
import com.example.newscompose.utils.Constants.BASE_URL
import com.example.newscompose.utils.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUsecases(
        localUserManager: LocalUserManager
    ) = AppEntryUsecases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun providesNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun providesNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(newsApi,newsDao)

    @Provides
    @Singleton
    fun providesNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            insertArticle = InsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ):NewsDao=newsDatabase.newsDao

}