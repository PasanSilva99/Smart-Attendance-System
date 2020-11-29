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

    /**
     * This function checks whether the event ID Is duplicate and returns true if duplicate
     * @param newID ID that you want to validate
     * @return if it is a duplicate - True --> If not - False
     */
    public boolean CheckDuplicateID(String newID) {
        // SQL COnnection Variable
        Connection con = null;
        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            // SQL Quarry
            String sql = "SELECT COUNT(*) FROM event WHERE event_id=?";
            //SQL Statement
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, newID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int count = resultSet.getInt(1);

                if(!(count >0))
                {
                    System.out.println("Hooray! No duplicate ID. Valid Event ID");
                    return false;
                }else {
                    System.out.println("Duplicate ID Detected Re Generate a New ID");
                    return true;
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {

            try {
                con.close();
            }catch (Exception ignore)
            {

            }
        }
        return true;


    }

}
