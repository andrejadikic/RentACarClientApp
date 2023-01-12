package gui.fx.app.controller;

import gui.fx.app.restclient.ReservationServiceRestClient;
import gui.fx.app.restclient.dto.AccommodationDto;
import gui.fx.app.restclient.dto.ReservationDto;
import gui.fx.app.view.ReservationView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookController{
    private ReservationServiceRestClient reservationServiceRestClient = new ReservationServiceRestClient();


    public void addComment(ActionEvent event) {
    }

    public void book(ActionEvent event) {
    }

    public void cancelReservation(ActionEvent event) {
    }

    public void filterReviews(ActionEvent event) {
    }

    public void deleteComment(ActionEvent event) {
    }

    public void updateComment(ActionEvent event) {
    }

    public void filter(ActionEvent event) {
    }

    public void sort(ActionEvent event) {
    }
}
