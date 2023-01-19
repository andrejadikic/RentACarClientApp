package gui.fx.app.controller;

import gui.fx.app.ClientApp;
import gui.fx.app.restclient.ReservationServiceRestClient;
import gui.fx.app.restclient.dto.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookController implements Initializable {
    @FXML
    private TableView<VehicleDto> vehicleTable;
    @FXML
    private TableView<ReservationDto> reservationsTable;
    @FXML
    private TableView<ReviewDto> reviewsTable;
    @FXML
    private ListView<CompanyRatingDto> reviewView;


    @FXML
    private TextField cityTxt;
    @FXML
    private TextField companyTxt;
    @FXML
    private DatePicker fromTxt;
    @FXML
    private DatePicker toTxt;
    @FXML
    private ComboBox<String> sortCmb;
    @FXML
    private Button filterBtn;
    @FXML
    private Button sortBtn;


    @FXML
    private TableColumn<VehicleDto, String> cityCol;
    @FXML
    private TableColumn<VehicleDto, String> typeCol;
    @FXML
    private TableColumn<VehicleDto, String> plateNumberCol;
    @FXML
    private TableColumn<VehicleDto, String> modelCol;
    @FXML
    private TableColumn<VehicleDto, String> companyCol;
    @FXML
    private TableColumn<VehicleDto, Integer> priceCol;


    @FXML
    private TableColumn<ReviewDto, String> commentCol;
    @FXML
    private TableColumn<ReviewDto, String> plateCol1;
    @FXML
    private TableColumn<ReviewDto, String> usernameCol1;
    @FXML
    private TableColumn<ReviewDto, Integer> ratingCol;


    @FXML
    private TableColumn<ReservationDto,Date> endDateCol;
    @FXML
    private TableColumn<ReservationDto,String> plateCol;
    @FXML
    private TableColumn<ReservationDto,Integer> priceCol1;
    @FXML
    private TableColumn<ReservationDto, Date> startDateCol;
    @FXML
    private TableColumn<ReservationDto,String> usernameCol;


    @FXML
    private TextField ratingTxt;
    @FXML
    private TextArea commentTxt;


    private ReservationServiceRestClient reservationServiceRestClient = new ReservationServiceRestClient();
    private ObservableList<VehicleDto> vehicleList= FXCollections.observableArrayList();
    private ObservableList<ReservationDto> reservationList= FXCollections.observableArrayList();
    private ObservableList<ReviewDto> reviewList= FXCollections.observableArrayList();
    private ObservableList<CompanyRatingDto> companyList= FXCollections.observableArrayList();


    public void addComment(ActionEvent event) {
        try{
            ReviewCreateDto reviewCreateDto = new ReviewCreateDto(Integer.parseInt(ratingTxt.getText()),commentTxt.getText(),reservationsTable.getSelectionModel().getSelectedItem().getPlateNumber());
            reservationServiceRestClient.addReview(reviewCreateDto);
            companyList.clear();
            companyList.addAll(reservationServiceRestClient.getCompanyRatings().getContent());
            updateReviews();

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void book(ActionEvent event) {
        VehicleDto selected = vehicleTable.getSelectionModel().getSelectedItem();
        try {
            ReservationDto reservation = reservationServiceRestClient.makeReservation(selected.getPlateNumber(), Date.valueOf(fromTxt.getValue()), Date.valueOf(toTxt.getValue()));
            reservationList.add(reservation);
            vehicleList.remove(selected);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cancelReservation(ActionEvent event) {
        ReservationDto selected = reservationsTable.getSelectionModel().getSelectedItem();
        try {
            ReservationDto reservation = reservationServiceRestClient.cancelReservation(selected);
            reservationList.remove(selected);
            vehicleList.clear();
            VehicleListDto vehicles = reservationServiceRestClient.getAvailable(cityTxt.getText(),
                    companyTxt.getText(), Date.valueOf(fromTxt.getValue()), Date.valueOf(toTxt.getValue()));
            vehicleList.addAll(vehicles.getContent());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void filterReviews(ActionEvent event) {
        try {
            updateReviews();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateReviews() throws IOException {
        reviewList.clear();
        String city = cityTxt.getText()==null? "":cityTxt.getText();
        String company = companyTxt.getText()==null? "":companyTxt.getText();
        ReviewListDto reviews = reservationServiceRestClient.getReviews(city,
                company);
        reviewList.addAll(reviews.getContent());
    }

    public void deleteComment(ActionEvent event) {
        try{
            reservationServiceRestClient.deleteReviews(reservationsTable.getSelectionModel().getSelectedItem().getPlateNumber());
            companyList.clear();
            companyList.addAll(reservationServiceRestClient.getCompanyRatings().getContent());
            updateReviews();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateComment(ActionEvent event) {
        try{
            ReviewDto reviewDto = new ReviewDto(Integer.parseInt(ratingTxt.getText()),commentTxt.getText(),reviewsTable.getSelectionModel().getSelectedItem().getVehiclePlateNumber(),reviewsTable.getSelectionModel().getSelectedItem().getUsername());
            reservationServiceRestClient.updateReview(reviewDto);
            companyList.clear();
            companyList.addAll(reservationServiceRestClient.getCompanyRatings().getContent());
            updateReviews();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public void filter(ActionEvent event) {
        try {
            vehicleList.clear();
            VehicleListDto vehicles = reservationServiceRestClient.getAvailable(cityTxt.getText(),
                    companyTxt.getText(), Date.valueOf(fromTxt.getValue()), Date.valueOf(toTxt.getValue()));
            vehicleList.addAll(vehicles.getContent());
        } catch (IOException | URISyntaxException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sort(ActionEvent event)  {

        if(ClientApp.getInstance().getToken()!=null){
            try {
                reservationList.clear();
                reservationList.addAll(reservationServiceRestClient.getReservations().getContent());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if(ClientApp.getInstance().getToken()!=null)
                reservationList.addAll(reservationServiceRestClient.getReservations().getContent());
            companyList.addAll(reservationServiceRestClient.getCompanyRatings().getContent());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        reviewView.setItems(companyList);


        companyCol.setCellValueFactory(new PropertyValueFactory<VehicleDto,String>("Company"));
        plateNumberCol.setCellValueFactory(new PropertyValueFactory<VehicleDto,String>("PlateNumber"));
        typeCol.setCellValueFactory(new PropertyValueFactory<VehicleDto,String>("Type"));
        modelCol.setCellValueFactory(new PropertyValueFactory<VehicleDto,String>("Model"));
        priceCol.setCellValueFactory(new PropertyValueFactory<VehicleDto,Integer>("PricePerDay"));
        cityCol.setCellValueFactory(new PropertyValueFactory<VehicleDto,String>("City"));
        vehicleTable.setItems(vehicleList);

        usernameCol.setCellValueFactory(new PropertyValueFactory<ReservationDto,String>("Username"));
        plateCol.setCellValueFactory(new PropertyValueFactory<ReservationDto,String>("PlateNumber"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<ReservationDto,Date>("StartDate"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<ReservationDto,Date>("EndDate"));
        priceCol1.setCellValueFactory(new PropertyValueFactory<ReservationDto,Integer>("Price"));
        reservationsTable.setItems(reservationList);

        plateCol1.setCellValueFactory(new PropertyValueFactory<ReviewDto,String>("PlateNumber"));
        usernameCol1.setCellValueFactory(new PropertyValueFactory<ReviewDto,String>("Username"));
        commentCol.setCellValueFactory(new PropertyValueFactory<ReviewDto,String>("Comment"));
        ratingCol.setCellValueFactory(new PropertyValueFactory<ReviewDto,Integer>("Rating"));
        reviewsTable.setItems(reviewList);
    }
}
