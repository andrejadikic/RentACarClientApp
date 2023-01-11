package gui.fx.app.controller;

import gui.fx.app.restclient.ReservationServiceRestClient;
import gui.fx.app.restclient.dto.AccommodationListDto;
import gui.fx.app.view.ReservationView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.net.URISyntaxException;

public class SearchController implements EventHandler<ActionEvent> {

    private ReservationView reservationView;
    private ReservationServiceRestClient reservationServiceRestClient;

    public SearchController(ReservationView reservationView) {
        this.reservationView = reservationView;
        this.reservationServiceRestClient = new ReservationServiceRestClient();
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            reservationView.getCarsList().clear();
            AccommodationListDto accommodationList = reservationServiceRestClient.getAvailable(reservationView.getTfCity().getText(),
                    reservationView.getTfCompany().getText(), reservationView.getTfFrom().getText(), reservationView.getTfTo().getText());
            reservationView.getCarsList().addAll(accommodationList.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
