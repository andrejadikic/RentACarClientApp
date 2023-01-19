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

public class UserServiceRestClient {
    public static final String URL = "http://localhost:8084/user_service/api";
    /// http://localhost:8080/api

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public String login(String username, String password) throws IOException {
        TokenRequestDto tokenRequestDto = new TokenRequestDto(username, password);
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(tokenRequestDto));
        String uri = UriComponentsBuilder.fromUriString(URL + "/user/login")
                .queryParam("username", encodeUtf8(username))
                .queryParam("password", encodeUtf8(password))
                .build()
                .toUriString();
        Request request = new Request.Builder()
                .url(uri)
                .post(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() == 200) {
            String token = response.body().string();
            return token;
        }
        throw new RuntimeException("Invalid username or password");
    }

    public ClientDto registerClient(ClientCreateDto clientCreateDto) throws IOException {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(clientCreateDto));
        Request request = new Request.Builder()
                .url(URL + "/client/register")
                .post(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() == 201) {
            String json = response.body().string();
            return objectMapper.readValue(json, ClientDto.class);
        }
        throw new RuntimeException("Something went wrong");
    }
    public ManagerDto registerManager(ManagerCreateDto clientCreateDto) throws IOException {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(clientCreateDto));
        Request request = new Request.Builder()
                .url(URL + "/manager/register")
                .post(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() == 201) {
            String json = response.body().string();
            return objectMapper.readValue(json, ManagerDto.class);
        }
        throw new RuntimeException("Something went wrong");
    }

    public void updateClient(ClientCreateDto clientCreateDto) throws IOException{
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(clientCreateDto));
        Request request = new Request.Builder()
                .url(URL + "/client")
                .header("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .put(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() != 201) {
            throw new RuntimeException("Something went wrong");
        }
    }
    public void updateManager(ManagerCreateDto clientCreateDto) throws IOException{
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(clientCreateDto));
        Request request = new Request.Builder()
                .url(URL + "/manager")
                .header("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .put(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() != 201) {
            throw new RuntimeException("Something went wrong");
        }
    }

    public UserListDto getUsers() throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Request request = new Request.Builder()
                .url(URL+"/user")
                .header("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .get()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, UserListDto.class);
        }
        throw new RuntimeException();
    }

    public void banUser(String username, boolean ban) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(username));
        String uri = UriComponentsBuilder.fromUriString(URL + "/user/ban")
                .queryParam("username", encodeUtf8(username))
                .queryParam("ban", ban)
                .build()
                .toUriString();
        Request request = new Request.Builder()
                .url(uri)
                .header("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .post(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();

        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            return;
        }
        throw new RuntimeException();
    }

    private static String encodeUtf8(String val) throws UnsupportedEncodingException {
        return URLEncoder.encode(val, "UTF-8");
    }
}
