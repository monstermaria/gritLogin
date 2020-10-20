import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        VBox root = new VBox(10);
        Scene loginScene = new Scene(root, 300, 100);

        HBox email = new HBox();
        HBox password = new HBox();

        Label labelForEmail = new Label("E-postadress: ");
        Label labelForPassword = new Label("Lösenord: ");

        TextField emailInput = new TextField();
        TextField passwordInput = new TextField();

        Button loginButton = new Button("Logga in");

        loginButton.setDefaultButton(true);
        loginButton.setOnAction(actionEvent -> {
            boolean emailOk = LoginValidator.checkEmail(emailInput.getText());
            boolean passwordOk = LoginValidator.checkPassword(passwordInput.getText());

            System.out.println("Login attempted:");
            System.out.println("E-mail " + emailInput.getText() + (emailOk ? " passed" : " failed"));
            System.out.println("Password " + passwordInput.getText() + (passwordOk ? " passed" : " failed"));
            if (emailOk && passwordOk) {
                System.out.println("Login confirmed");
            }
        });

        email.getChildren().addAll(labelForEmail, emailInput);
        password.getChildren().addAll(labelForPassword, passwordInput);

        root.getChildren().addAll(email, password, loginButton);

        stage.setTitle("Grit Adademy Login");
        stage.setScene(loginScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
