package Common;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UniEventDAO {

    public UniEventDAO(){

    }

    public List<UniEvent> getLectureList() {

        List<UniEvent> lectureList = null; // Holds all events

        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start_date = DateFormat.format(new Date())+" 00:00:00";
        String end_date = DateFormat.format(new Date())+" 23:59:59";
        System.out.println("Today ST: " + start_date);
        System.out.println("Today ED: " + end_date);

        // SQL Connection Variable
        Connection con = null;

        try {
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            // SQL Quarry
            String sql = "SELECT * FROM event WHERE start_time>? AND end_time<?";
            // SQL Statement
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setTimestamp(1, Timestamp.valueOf(start_date));
            statement.setTimestamp(2, Timestamp.valueOf(end_date));

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                System.out.println("Fetching: " + resultSet.getString(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
            assert con != null;
            con.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return lectureList;
    }

}
