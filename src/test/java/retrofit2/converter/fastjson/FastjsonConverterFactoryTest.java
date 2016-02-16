package retrofit2.converter.fastjson;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.io.IOException;

public class FastjsonConverterFactoryTest {
    public static class AnName {
        private String theName;

        public AnName() {
        }

        public AnName(String theName) {
            this.theName = theName;
        }

        public void setTheName(String theName) {
            this.theName = theName;
        }

        public String getTheName() {
            return theName;
        }
    }


    interface Service {
        @POST("/")
        Call<AnName> anName(@Body AnName name);

    }


    @Rule
    public final MockWebServer server = new MockWebServer();

    private Service service;

    @Before
    public void setUp() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(server.url("/"))
            .addConverterFactory(FastjsonConverterFactory.create()).build();
        service = retrofit.create(Service.class);
    }

    @Test
    public void testImplementation() throws IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody("{\"theName\":\"value\"}"));

        Call<AnName> call = service.anName(new AnName("value"));
        Response<AnName> response = call.execute();
        AnName body = response.body();
        Assert.assertEquals(body.theName, "value");

        RecordedRequest request = server.takeRequest();
        Assert.assertEquals(request.getBody().readUtf8(), "{\"theName\":\"value\"}");
        Assert.assertEquals(request.getHeader("Content-Type"), "application/json; charset=UTF-8");
    }

    @Test
    public void testSerializeUsesConfiguration() throws IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody("{}"));
        service.anName(new AnName(null)).execute();

        RecordedRequest request = server.takeRequest();
        Assert.assertEquals(request.getBody().readUtf8(), "{}"); // Null value was not serialized.
        Assert.assertEquals(request.getHeader("Content-Type"), "application/json; charset=UTF-8");
    }
}
