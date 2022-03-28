package com.example.task.di;

import com.example.task.network.ApiInterface;
import com.example.task.utils.Constants;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class RetrofitModule {



    @Provides
    @Singleton
    public static ApiInterface provideApiInterface(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.callTimeout(120, TimeUnit.SECONDS);
        httpClient.readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS);

//        httpClient.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request newRequest  = chain.request().newBuilder()
//                        .addHeader("Authorization", "Bearer " + Constants.api_token)
//                        .build();
//                return chain.proceed(newRequest);            }
//        });

        return new Retrofit.Builder().baseUrl(Constants.retrofit_BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(ApiInterface.class);

    }
}
