package com.qucoon.watchguard.modules

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.qucoon.watchguard.livedata.EnrollmentLiveData
import com.qucoon.watchguard.localdata.pref.PaperPrefs
import com.qucoon.watchguard.remote.WatchGuardAPI
import com.qucoon.watchguard.repository.EnrollmentRepository
import com.qucoon.watchguard.repository.EnrollmentRepositoryImpl
import com.qucoon.watchguard.repository.LoginRepository
import com.qucoon.watchguard.repository.LoginRepositoryImpl
import com.qucoon.watchguard.viewmodel.EnrollmentViewmodel
import com.qucoon.watchguard.viewmodel.LoginViewmodel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val RUBIES_CIRCLE_BASE_URL = "https://c0m53988id.execute-api.us-east-2.amazonaws.com/dev/"

val appModules = module {
    //Networking
    single { createWebService<WatchGuardAPI>(RxJava2CallAdapterFactory.create(), RUBIES_CIRCLE_BASE_URL)}
    //RoomDatabase
//    single { Room.databaseBuilder(androidApplication(),RubiesGroupDatabase::class.java,"CircleDB").fallbackToDestructiveMigration()
//        .build() }
    // dao initialization
//    single(createdAtStart = false){get<RubiesGroupDatabase> ().groupDao()}
//    single(createdAtStart = false){get<RubiesGroupDatabase>().groupMemberDao()}
//    single(createdAtStart = false){get<RubiesGroupDatabase>().tasksDao()}

    //Preferences
    single { PaperPrefs(androidApplication()) }

    //LiveData
    single { EnrollmentLiveData() }

    // Repository Initialization
    factory<EnrollmentRepository> { EnrollmentRepositoryImpl(watchGuardAPI = get(),paperPrefs = get(),enrollmentLiveData = get()) }
    factory <LoginRepository >{ LoginRepositoryImpl(watchGuardAPI = get(),paperPrefs = get()) }


    //ViewModel
    viewModel { EnrollmentViewmodel(enrollmentRepository = get()) }
    viewModel { LoginViewmodel(loginRepository = get()) }

}


/* function to build our Retrofit service */
inline fun <reified T> createWebService(
    factory: CallAdapter.Factory,
    baseUrl: String
): T {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val client = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.MINUTES)
        .writeTimeout(5, TimeUnit.MINUTES) // write timeout
        .readTimeout(5, TimeUnit.MINUTES) // read timeout
        .addInterceptor(interceptor).build()
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addCallAdapterFactory(factory)

        .client(client)
        .build()
    return retrofit.create(T::class.java)
}