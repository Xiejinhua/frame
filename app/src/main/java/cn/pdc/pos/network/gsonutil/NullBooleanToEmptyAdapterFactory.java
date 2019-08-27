package cn.pdc.pos.network.gsonutil;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

/**
 * @author davy
 * @since 2017/8/30
 */
public class NullBooleanToEmptyAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<T> rawType = (Class<T>) type.getRawType();
        if (rawType != Boolean.class) {
            return null;
        }
        return (TypeAdapter<T>) new BooleanNullAdapter();
    }


}