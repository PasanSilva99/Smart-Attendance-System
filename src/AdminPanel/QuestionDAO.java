package AdminPanel;

import Common.DAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QuestionDAO {
    public void SaveNewQuestion(String q_number, String question, String answerList, String quizID) {
        // SQL Connection Variable
        Connection con = null;

        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            String sql = "INSERT INTO question_answers VALUES (?, ?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, q_number);
            statement.setString(2, question);
            statement.setString(3, answerList);
            statement.setString(4, quizID);

            int rowsUpdated = statement.executeUpdate();

            if(rowsUpdated>0){
                System.out.println("Question "+q_number+" added successfully");
            }


        }catch (Exception e){
            e.printStackTrace();
            Alert.AlertType alertAlertType = AlertType.ERROR;
            Alert alert = new Alert(alertAlertType);
            alert.setTitle("Question Error");
            alert.setHeaderText("Cannot add the Question");
            alert.setContentText("Error occurred when adding the question");
        }finally {
            try{
                con.close();
            }catch (Exception ignored){}
        }
    }

    public boolean isDuplicated(String newQuizID) {
        // SQL Connection Variable
        Connection con = null;

        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            String sql = "SELECT COUNT(*) FROM quiz WHERE quiz_id=?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, newQuizID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int count = resultSet.getInt(1);
                if(!(count >0)){
                    return false;
                }
            }


        }catch (Exception e){
            e.printStackTrace();
            Alert.AlertType alertAlertType = AlertType.ERROR;
            Alert alert = new Alert(alertAlertType);
            alert.setTitle("Question Error");
            alert.setHeaderText("Cannot add the Question");
            alert.setContentText("Error occurred when adding the question");
        }finally {
            try{
                con.close();
            }catch (Exception ignored){}
        }
        return true;
    }
}
