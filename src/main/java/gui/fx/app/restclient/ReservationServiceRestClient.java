package gui.fx.app.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import gui.fx.app.ClientApp;
import gui.fx.app.model.User;
import gui.fx.app.restclient.dto.*;
import okhttp3.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.Base64;
import java.util.List;

public class ReservationServiceRestClient {
    public static final String URLResevration = "http://localhost:8084/reservation_service/api/reservation";
    public static final String URLVehicle = "http://localhost:8084/reservation_service/api/vehicle";
    public static final String URLReviews = "http://localhost:8084/reservation_service/api/review";

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();



    public VehicleListDto getAvailable(String city, String company, Date beginDate, Date endDate) throws IOException, URISyntaxException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String uri = UriComponentsBuilder.fromUriString(URLVehicle+"/available_vehicles")
                .queryParam("city", encodeUtf8(city))
                .queryParam("company", encodeUtf8(company))
                .queryParam("startDate", beginDate)
                .queryParam("endDate", endDate)
                .queryParam("asc", true)
                .build()
                .toUriString();
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
            return objectMapper.readValue(json, VehicleListDto.class);
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

    public ReservationDto makeReservation(String plateNum, Date startDate, Date endDate) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ReservationCreateDto rd = new ReservationCreateDto(plateNum, startDate, endDate);
        @SuppressWarnings("deprecation")
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(rd));
        Request request = new Request.Builder()
                .url(URLResevration+"/create")
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
        System.out.println(URLResevration);
        Request request = new Request.Builder()
                .url(URLResevration+"/by_user")
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

    public ReservationDto cancelReservation(ReservationDto selected) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        @SuppressWarnings("deprecation")
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(selected));
        Request request = new Request.Builder()
                .url(URLResevration+"/delete")
                .header("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .delete(body)
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

    public CompanyRatingListDto getCompanyRatings() throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Request request = new Request.Builder()
                .url(URLReviews+"/company_ratings")
                .header("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .get()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, CompanyRatingListDto.class);
        }
        throw new RuntimeException();
    }

    public ReviewListDto getReviews(String city, String company) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String uri = UriComponentsBuilder.fromUriString(URLReviews+"/filtered_reviews")
                .queryParam("city", encodeUtf8(city))
                .queryParam("company", encodeUtf8(company))
                .build()
                .toUriString();
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
            return objectMapper.readValue(json, ReviewListDto.class);
        }
        throw new RuntimeException();
    }

    public ReviewDto addReview(ReviewCreateDto reviewCreateDto) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(reviewCreateDto));
        Request request = new Request.Builder()
                .url(URLReviews+"/create")
                .header("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .post(body)
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response.code());
        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, ReviewDto.class);
        }
        throw new RuntimeException();
    }

    public void deleteReviews(String vehiclePlateNumber) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String uri = UriComponentsBuilder.fromUriString(URLReviews+"/delete")
                .queryParam("vehiclePlateNumber", encodeUtf8(vehiclePlateNumber))
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
    }





}
