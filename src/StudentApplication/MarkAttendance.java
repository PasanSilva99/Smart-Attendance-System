package StudentApplication;

import Common.*;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MarkAttendance {

    public VBox vbox_quizzes;
    User user;
    UniEvent uniEvent;

    public void setUser(User user) {
        this.user = user;
    }

    public void setEvent(UniEvent clickedItem) {
        this.uniEvent = clickedItem;

        // Get the quizzes from the database
        System.out.println("Initializing quizzes");
        List<Quiz> AvailableQuizzes = new QuizDAO().getQuizListForModule(uniEvent.getEventID());

        for (Quiz quiz: AvailableQuizzes) {
            AnchorPane q = quiz.getQuizView();
            System.out.println("Loading Quiz std: " + q.getId());

            EventHandler<MouseEvent> OnMouseCLick = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Platform.runLater(()-> quizViewClick(quiz));
                }
            };


            q.addEventFilter(MouseEvent.MOUSE_CLICKED, OnMouseCLick);

            vbox_quizzes.getChildren().add(q);


        }
    }

    private void quizViewClick(Quiz quiz) {
        try {
            FXMLLoader loder = new FXMLLoader();
            loder.setLocation(getClass().getResource("QuizViewPage.fxml"));
            AnchorPane root = loder.load();
            QuizViewPage controller = loder.getController();
            controller.setUser(user);
            controller.setEvent(uniEvent);
            controller.setQuiz(quiz);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Quiz");
            primaryStage.setScene(new Scene(root, 600, 600));
            primaryStage.show();

            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
            primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void signIn() throws IOException {
        System.out.println("Marking Attendance");
        //new UserLogin().markAttendance(user, uniEvent.getModuleO(), uniEvent.getEventID());
    }
}
