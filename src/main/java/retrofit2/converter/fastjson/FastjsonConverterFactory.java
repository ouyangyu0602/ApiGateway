package retrofit2.converter.fastjson;

import com.google.common.base.Charsets;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 * Title: FastjsonConverterFactory
 * Description: Fastjson 转换类工厂类, 初始化请求和响应的转换器, 使用 Fastjson 转换
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-15 18:38
 */
public class FastjsonConverterFactory extends Converter.Factory {
    private Charset charset;

    public FastjsonConverterFactory(Charset charset) {
        this.charset = charset;
    }

    public static FastjsonConverterFactory create() {
        return create(Charsets.UTF_8);
    }

    public static FastjsonConverterFactory create(Charset charset) {
        return new FastjsonConverterFactory(charset);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations
        , Annotation[] methodAnnotations, Retrofit retrofit) {
        return new FastjsonRequestBodyConverter<>(type, charset);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
        Retrofit retrofit) {
        return new FastjsonResponseBodyConverter<>(type, charset);
    }
}
