package AdminPanel;

import Common.DAO;
import Common.Question;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QuestionDAO {
    public void SaveNewQuestion(String q_number, String question, String answerList, String quizID) {
        // SQL Connection Variable
        Connection con = null;

        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            String sql = "INSERT INTO question_answers VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE question=?, answerList=?, quizID=?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, q_number);
            statement.setString(2, question);
            statement.setString(3, answerList);
            statement.setString(4, quizID);
            statement.setString(5, question);
            statement.setString(6, answerList);
            statement.setString(7, quizID);

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

    public List<Question> getQuestionList(String quizID) {
        System.out.println("Fetching Questions for "+quizID);

        List<Question> QuestionList = new ArrayList<>();
        // SQL Connection Variable
        Connection con = null;

        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            // SQL Quarry
            String sql = "SELECT * FROM question_answers WHERE quizID=?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, quizID);

            ResultSet resultSet = statement.executeQuery();

            System.out.println("Retrieving Questions");
            while (resultSet.next()){
                System.out.println("-0-0-0-0-0-0-0-");
                String q_number=resultSet.getString(1);
                String question = resultSet.getString(2);
                String answerList = resultSet.getString(3);
                String quiz_ID = resultSet.getString(4);

                QuestionList.add(new Question(q_number, question, answerList, quiz_ID));

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception ignored){

            }

        }
        System.out.println("Found "+QuestionList.size()+" Questions");

        return QuestionList;

    }
}
