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
}
