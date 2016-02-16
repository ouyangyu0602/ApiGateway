package retrofit2.converter.fastjson;

import com.alibaba.fastjson.JSON;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 * Title: FastjsonResponseBodyConverter
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-15 18:46
 */
public class FastjsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Type type;
    private Charset charset; // NOSONAR

    public FastjsonResponseBodyConverter() {
        // Do nothing
    }

    public FastjsonResponseBodyConverter(Type type, Charset charset) {
        this.type = type;
        this.charset = charset;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            return JSON.parseObject(value.string(), type);
        } finally {
            value.close();
        }
    }
}
