package it.andrearosa.kickstarter.services;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import it.andrearosa.kickstarter.misc.AppConstants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andre on 18/04/16.
 * <p/>
 * kickstarter-android.
 */
public class RestClient {
    public static final String TAG = "RestClient";

    private static OkHttpClient httpClient;
    private static RestInterface restInterface;

    public RestClient(final Context context) {

        if (this.getRestInterface() == null) {

            httpClient = new OkHttpClient.Builder().readTimeout(10 * 1000, TimeUnit.MILLISECONDS).build();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory())
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss") // 2015-11-18T00:00:00
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            restInterface = retrofit.create(RestInterface.class);

        }

    }

    public RestInterface getRestInterface() {
        return restInterface;
    }

    public static class NullStringToEmptyAdapterFactory<T> implements TypeAdapterFactory {
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

            Class<T> rawType = (Class<T>) type.getRawType();
            if (rawType != String.class) {
                return null;
            }
            return (TypeAdapter<T>) new StringAdapter();
        }
    }

    public static class StringAdapter extends TypeAdapter<String> {
        public String read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return "";
            }
            return reader.nextString();
        }

        public void write(JsonWriter writer, String value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }
    }

}
