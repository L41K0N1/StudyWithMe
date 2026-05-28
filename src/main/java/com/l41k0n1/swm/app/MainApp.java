package com.l41k0n1.swm.app;

import com.l41k0n1.swm.controller.AppController;
import com.l41k0n1.swm.model.session.Session;
import com.l41k0n1.swm.model.session.SessionPreset;
import com.l41k0n1.swm.model.timer.SessionTimer;
import com.l41k0n1.swm.controller.AppView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        AppController controller = getController();
        AppView view = new AppView(controller);

        Platform.setImplicitExit(true);

        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(
                getClass().getResource("/view/timer_iteration_3.fxml")));
        loader.setController(view);
        Parent root = loader.load();

        Scene scene = new Scene(root);

        Font font = Font.loadFont(getClass().getResource("/fonts/Unbounded-Black.ttf").toExternalForm(), 20);

        scene.getStylesheets().add(
                getClass().getResource("/themes/fonts.css").toExternalForm()
        );

        stage.getIcons().add(
                new Image(Objects.requireNonNull(
                        getClass().getResourceAsStream("/appicon.png")))
        );
        stage.setTitle("Study With Me");

        stage.setScene(scene);
        stage.show();
    }

    private static AppController getController() {

        // Creating a new session with default preset
        Session session = new Session();
        SessionPreset defaultPreset = session.getDefaultSessionPreset();

        // Initializing Timer
        SessionTimer timer = new SessionTimer(
                defaultPreset.getWorkDuration(),
                defaultPreset.getBreakDuration());

        // Session can not be existing w/o timer (just preventing NullPointer when clicking on buttons which are switching session);
        session.setTimer(timer);

        // Initializing UserInputController
        return new AppController(timer, session);
    }

    private static void loadModules() {

    }

    @Override
    public void stop() throws Exception {

        // has to be saving user preferences before exit. consider to implement.

        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}