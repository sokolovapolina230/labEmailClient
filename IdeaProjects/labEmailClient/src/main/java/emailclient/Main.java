package emailclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            System.out.println("Поточний клас: " + getClass().getName());
            System.out.println("Шукаємо FXML: /emailclient/ui/view/user_form.fxml");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/emailclient/ui/view/user_form.fxml"));
            Parent root = loader.load();

            primaryStage.setTitle("Email Client - Users");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

            System.out.println("FXML успішно завантажено!");
        } catch (Exception e) {
            System.err.println("ПОМИЛКА при завантаженні FXML:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
