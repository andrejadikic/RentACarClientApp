package gui.fx.app.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import gui.fx.app.ClientApp;
import gui.fx.app.model.User;
import gui.fx.app.restclient.dto.AccommodationListDto;
import gui.fx.app.restclient.dto.ReservationDto;
import gui.fx.app.restclient.dto.ReservationListDto;
import okhttp3.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.Base64;

public class ReservationServiceRestClient {
    public static final String URL = "http://localhost:8084/reservationservice/api/reservation";

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public AccommodationListDto getAvailable(String nameCity, String hotelId, String beginDate, String endDate) throws IOException, URISyntaxException {

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String uri = UriComponentsBuilder.fromUriString(URL)
                .queryParam("nameCity", encodeUtf8(nameCity))
                .queryParam("hotelId", encodeUtf8(hotelId))
                .queryParam("beginDate", encodeUtf8(beginDate))
                .queryParam("endDate", encodeUtf8(endDate))
                .build()
                .toUriString();
        System.out.println(uri);
        Request request = new Request.Builder()
                .url(uri)
                .header("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, AccommodationListDto.class);
        }

        throw new RuntimeException();
    }

    private static String encodeUtf8(String val) throws UnsupportedEncodingException {
        return URLEncoder.encode(val, "UTF-8");
    }

    private User getUser(String token) throws JsonProcessingException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(token.split("[.]")[1]));
        User userMapper = objectMapper.readValue(payload, User.class);
        return userMapper;
    }

    public ReservationDto makeReservation(String hotel, String room, Date startDate, Date endDate) throws IOException {

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ReservationDto rd = new ReservationDto(hotel, room, startDate, endDate, getUser(ClientApp.getInstance().getToken()).getId());
        @SuppressWarnings("deprecation")
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(rd));
        Request request = new Request.Builder()
                .url(URL)
                .header("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();

            return objectMapper.readValue(json, ReservationDto.class);
        }

        throw new RuntimeException();
    }

    public ReservationListDto getReservations() throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        System.out.println(URL + "/" + getUser(ClientApp.getInstance().getToken()).getId());
        Request request = new Request.Builder()
                .url(URL + '/' + getUser(ClientApp.getInstance().getToken()).getId())
                .header("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();

            return objectMapper.readValue(json, ReservationListDto.class);
        }

        throw new RuntimeException();
    }
}
