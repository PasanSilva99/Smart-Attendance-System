package Common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuizDAO {
    public List<Quiz> getQuizList() {
        List<Quiz> quizz = new ArrayList<>();
        // SQL Connection Variable
        Connection con = null;

        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            // SQL Quarry
            String sql = "SELECT * FROM quiz";

            // SQL Statement
            PreparedStatement statement = con.prepareStatement(sql);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()){
                    quizz.add(new Quiz(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
                }
            }

        }catch (Exception e){

        }finally {
            try {
                con.close();
            }catch (Exception ignored){}
        }

        return quizz;

    }

    /**
     * Returns the list of quizzes for the event
     * @param EventID
     * @return
     */
    public List<Quiz> getQuizListForModule(String EventID){
        System.out.println("Fetching Quizzes");
        List<Quiz> quizz = new ArrayList<>();
        // SQL Connection Variable
        Connection con = null;

        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);
            System.out.println("Searching Quizzes for "+EventID);
            // SQL Quarry
            String sql = "SELECT * FROM quiz WHERE event_id=?";

            // SQL Statement
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, EventID);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()){
                    String qID = resultSet.getString(1);
                    System.out.println("Found Quiz: "+ qID);
                    quizz.add(new Quiz(qID, resultSet.getString(2), resultSet.getString(3)));
                }
            }

        }catch (Exception e){

        }finally {
            try {
                con.close();
            }catch (Exception ignored){}
        }

        return quizz;


    }

    /**
     * Records the quiz in the database
     * @param quizID
     * @param quiz_name
     * @param event_id
     */
    public void addQuiz(String quizID, String quiz_name, String event_id) {

        // SQL Connection Variable
        Connection con = null;

        try {
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            // SQL Quarry
            String sql = "INSERT INTO quiz VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE quiz_name=?, event_id=?";

            // SQL Statement
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, quizID);
            statement.setString(2, quiz_name);
            statement.setString(3, event_id);
            statement.setString(4, quiz_name);
            statement.setString(5, event_id);

            int rowsInserted = statement.executeUpdate();

            if(rowsInserted>0){
                System.out.println("Quiz Saved Successfully");
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                con.close();
            }catch (Exception ignored){}
        }
    }
}
