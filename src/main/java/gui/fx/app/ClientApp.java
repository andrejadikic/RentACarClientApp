package gui.fx.app;

import java.net.URL;

public class ClientApp {

    private String token;
    public static URL reservationUrl = Main.class.getClassLoader().getResource("reservation.fxml");
    public static URL registrationUrl = Main.class.getClassLoader().getResource("registration.fxml");

    private ClientApp()throws IllegalAccessException, NoSuchMethodException{}

    private static class InstanceHolder {
        private static ClientApp instance;

        static {
            try {
                instance = new ClientApp();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static ClientApp getInstance() {
        return InstanceHolder.instance;
    }
}
