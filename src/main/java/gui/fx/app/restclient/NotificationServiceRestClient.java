package gui.fx.app.restclient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import gui.fx.app.ClientApp;
import gui.fx.app.restclient.dto.*;
import okhttp3.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class NotificationServiceRestClient {
    public static final String URL = "http://localhost:8084/notification_service/api";
    /// http://localhost:8080/api

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public String addType(String type) throws IOException {
        TypeDto typeDto = new TypeDto(type);
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(typeDto));
        Request request = new Request.Builder()
                .url(URL+"/types/add")
                .header("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .post(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() == 200) {
            String token = response.body().string();
            return token;
        }
        throw new RuntimeException(response.body().string());
    }

    public void deleteType(String type) throws IOException {
        String uri = UriComponentsBuilder.fromUriString(URL + "/types")
                .queryParam("type", encodeUtf8(type))
                .build()
                .toUriString();
        Request request = new Request.Builder()
                .url(uri)
                .header("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .delete()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response.code());
            String json = response.body().string();
            System.out.println(json);

    }


    private static String encodeUtf8(String val) throws UnsupportedEncodingException {
        return URLEncoder.encode(val, "UTF-8");
    }
}
