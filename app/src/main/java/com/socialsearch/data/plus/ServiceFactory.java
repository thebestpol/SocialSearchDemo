package com.socialsearch.data.plus;

import android.support.annotation.NonNull;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * SocialSearchDemo
 * com.socialsearch.data.plus
 * ServiceFactory
 */

public class ServiceFactory {

  /**
   * Creates a retrofit service
   *
   * @param clazz Java interface of the retrofit service
   * @param baseUrl REST endpoint url
   */
  public static <T> T createRetrofitService(final Class<T> clazz, final String baseUrl) {
    final Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .client(getOkHttpClient())
        .build();

    return retrofit.create(clazz);
  }

  @NonNull private static OkHttpClient getOkHttpClient() {
    OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    HttpLoggingInterceptor.Level loggingLevel = HttpLoggingInterceptor.Level.BODY;
    loggingInterceptor.setLevel(loggingLevel);
    httpClientBuilder.addInterceptor(loggingInterceptor);

    // Time Out up to 20 seconds
    httpClientBuilder.connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS);

    httpClientBuilder.addInterceptor(chain -> {
      Request original = chain.request();
      Request.Builder reqBuilder = original.newBuilder()
          .addHeader("Accept", "application/json")
          .addHeader("Content-Type", "application/json");

      return chain.proceed(reqBuilder.build());
    });

    final TrustManager[] trustAllCerts = new TrustManager[] {
        new X509TrustManager() {
          @Override public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
          }

          @Override
          public void checkServerTrusted(final X509Certificate[] chain, final String authType)
              throws CertificateException {
          }

          @Override
          public void checkClientTrusted(final X509Certificate[] chain, final String authType)
              throws CertificateException {
          }
        },
    };

    SSLContext sslContext;
    try {
      sslContext = SSLContext.getInstance("TLS");
      sslContext.init(null, trustAllCerts, new SecureRandom());
      httpClientBuilder.sslSocketFactory(sslContext.getSocketFactory());
    } catch (GeneralSecurityException e) {
      e.printStackTrace();
    }

    HostnameVerifier hostnameVerifier = (hostname, session) -> true;
    httpClientBuilder.hostnameVerifier(hostnameVerifier);

    return httpClientBuilder.build();
  }
}
