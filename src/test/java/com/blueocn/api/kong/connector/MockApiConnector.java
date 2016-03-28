package com.blueocn.api.kong.connector;

import com.blueocn.api.kong.model.Api;
import okhttp3.ResponseBody;
import org.junit.Before;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.Calls;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class MockApiConnector {

    private ApiConnector apiConnector;

    @Before
    public void before() {
        final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://mock-server.com").build();
        final NetworkBehavior behavior = NetworkBehavior.create();
        final ExecutorService executor = Executors.newSingleThreadExecutor();

        MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit).networkBehavior(behavior)
            .backgroundExecutor(executor).build();
        BehaviorDelegate<ApiConnector> delegate = mockRetrofit.create(ApiConnector.class);

        apiConnector = new ApiConnector() {
            @Override
            public Call<Api> add(@Body Api newApi) {
                newApi.setId("newApi");
                Call<Api> response = Calls.response(newApi);
                return delegate.returning(response).add(newApi);
            }

            @Override
            public Call<ResponseBody> query(@QueryMap Map<String, Object> queryInfo) {
                return delegate.returningResponse(new Api()).query(queryInfo);
            }

            @Override
            public Call<Api> queryOne(@Path("apiId") String apiId) {
                return delegate.returningResponse(new Api()).queryOne(apiId);
            }

            @Override
            public Call<Api> update(@Path("apiId") String apiId, @Body Api api) {
                api.setId(apiId);
                return delegate.returningResponse(api).update(apiId, api);
            }

            @Override
            public Call<Api> updateOrCreate(@Body Api api) {
                return delegate.returningResponse(api).updateOrCreate(api);
            }

            @Override
            public Call<String> delete(@Path("apiId") String apiId) {
                return delegate.returningResponse("").delete(apiId);
            }
        };
    }
}
