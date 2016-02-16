package retrofit2.converter.fastjson;

import com.alibaba.fastjson.JSON;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 * Title: FastjsonRequestBodyConverter
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-15 18:41
 */
public class FastjsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private Type type; // NOSONAR
    private Charset charset;
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    public FastjsonRequestBodyConverter(Type type, Charset charset) {
        this.type = type;
        this.charset = charset;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        return RequestBody.create(MEDIA_TYPE, JSON.toJSONString(value).getBytes(charset));
    }
}
