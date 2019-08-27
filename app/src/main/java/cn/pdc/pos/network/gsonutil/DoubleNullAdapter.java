package cn.pdc.pos.network.gsonutil;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author davy
 * @since 2017/8/30
 */
public class DoubleNullAdapter extends TypeAdapter<Double>{
    @Override
    public Double read(JsonReader reader) throws IOException {
        // TODO Auto-generated method stub
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return 0.00;
        }
        return reader.nextDouble();
    }
    @Override
    public void write(JsonWriter writer, Double value) throws IOException {
        // TODO Auto-generated method stub
        if (value == null) {
            writer.nullValue();
            return;
        }
        writer.value(value);
    }
}