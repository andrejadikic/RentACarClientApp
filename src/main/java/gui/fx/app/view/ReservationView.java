package gui.fx.app.view;

import gui.fx.app.controller.BookController;
import gui.fx.app.controller.SearchController;
import gui.fx.app.restclient.ReservationServiceRestClient;
import gui.fx.app.restclient.dto.AccommodationDto;
import gui.fx.app.restclient.dto.ReservationDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.io.IOException;
@Getter
public class ReservationView extends VBox {

    private HBox hBoxFilers;

    private Label lblCity;
    private Label lblCompany;
    private Label lblFrom;
    private Label lblTo;
    private Label lblReservation;
    private TextField tfCity;
    private TextField tfCompany;
    private TextField tfFrom;
    private TextField tfTo;
    private Button btnSearch;
    private Button btnBook;
    private ComboBox<String> cmbSort;

    private ObservableList<AccommodationDto> carsList;
    private TableView<AccommodationDto>  tableReservation;
    private ObservableList<ReservationDto> reservationList;
    private TableView<ReservationDto>  userReservationtable;

    private ReservationServiceRestClient reservationServiceRestClient;

    public ReservationView(){
        initViewElements();
        addElements();
        //addListeners();
        //initReservations();
    }

    private void initReservations() {
        reservationServiceRestClient = new ReservationServiceRestClient();
        try {
            reservationList.addAll(reservationServiceRestClient.getReservations().getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addListeners() {
        btnSearch.setOnAction(new SearchController(this));
        btnBook.setOnAction(new BookController(this));
    }

    private void addElements() {
        this.setPadding(new Insets(10));
        this.hBoxFilers.getChildren().addAll(lblCity, tfCity, lblCompany, tfCompany, lblFrom, tfFrom, lblTo, tfTo, btnSearch);
        this.getChildren().addAll(hBoxFilers, tableReservation, btnBook, lblReservation, userReservationtable);
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
    }

    private void initViewElements() {
        hBoxFilers = new HBox(5);
        lblCity = new Label("City");
        lblCompany = new Label("Company");
        lblFrom = new Label("From");
        lblTo = new Label("To");
        lblReservation = new Label("Your reservations");
        tfCity = new TextField();
        tfCompany = new TextField();
        tfFrom = new TextField();
        tfTo = new TextField();
        btnSearch = new Button("Search");
        btnBook = new Button("Book");
        cmbSort = new ComboBox<>();
        carsList = FXCollections.observableArrayList();
        tableReservation = new TableView<>(carsList);
        reservationList = FXCollections.observableArrayList();
        userReservationtable = new TableView<>(reservationList);

        cmbSort.getItems().addAll("asc","desc");

        TableColumn<AccommodationDto, String> col1 = new TableColumn<>("Type of car");
        col1.setCellValueFactory(new PropertyValueFactory<>("hotel"));
        TableColumn<AccommodationDto, Double> col23 = new TableColumn<>("Model");
        col23.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        TableColumn<AccommodationDto, Double> col2 = new TableColumn<>("Price");
        col2.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        TableColumn<AccommodationDto, Double> col3 = new TableColumn<>("Total");
        col3.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        tableReservation.getColumns().add(col1);
        tableReservation.getColumns().add(col2);
        tableReservation.getColumns().add(col23);
        tableReservation.getColumns().add(col3);

        TableColumn<ReservationDto, String> col11 = new TableColumn<>("Hotel");
        col11.setCellValueFactory(new PropertyValueFactory<>("hotel"));
        TableColumn<ReservationDto, Double> col22 = new TableColumn<>("Room type");
        col22.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        TableColumn<ReservationDto, Double> col33 = new TableColumn<>("From");
        col33.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        TableColumn<ReservationDto, Double> col44 = new TableColumn<>("To");
        col44.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        userReservationtable.getColumns().add(col11);
        userReservationtable.getColumns().add(col22);
        userReservationtable.getColumns().add(col33);
        userReservationtable.getColumns().add(col44);
    }

}
