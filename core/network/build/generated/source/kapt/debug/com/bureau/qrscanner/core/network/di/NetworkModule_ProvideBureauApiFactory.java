package com.bureau.qrscanner.core.network.di;

import com.bureau.qrscanner.core.network.BureauApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class NetworkModule_ProvideBureauApiFactory implements Factory<BureauApi> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideBureauApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public BureauApi get() {
    return provideBureauApi(retrofitProvider.get());
  }

  public static NetworkModule_ProvideBureauApiFactory create(Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideBureauApiFactory(retrofitProvider);
  }

  public static BureauApi provideBureauApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideBureauApi(retrofit));
  }
}
