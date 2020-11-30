package Common;

import javafx.scene.control.Alert;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UniEventDAO {

    public UniEventDAO(){

    }

    public List<UniEvent> getLectureList() {

        List<UniEvent> lectureList = new ArrayList<>(); // Holds all events

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
                String event_id = resultSet.getString(1);
                String event_name = resultSet.getString(2);
                String module_code = resultSet.getString(3);
                String start_time = resultSet.getString(4);
                String end_time = resultSet.getString(5);
                String lecturer = resultSet.getString(6);
                String batch = resultSet.getString(7);
                String event_type = resultSet.getString(8);
                String location = resultSet.getString(9);

                System.out.println("Fetching: " + event_id);
                lectureList.add(new UniEvent(event_id, event_name, module_code, start_time, end_time, lecturer, batch, event_type, location));
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

    public void AddEvent(String event_id, String event_name, String module_code, String start_time, String end_time, String lecturer, String batch, String event_type, String location) {

        // SQL COnnection Variable
        Connection con = null;
        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            // SQL Quarry
            String sql = "INSERT INTO event VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            //SQL Statement
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, event_id);
            statement.setString(2, event_name);
            statement.setString(3, module_code);
            statement.setString(4, start_time);
            statement.setString(5, end_time);
            statement.setString(6, lecturer);
            statement.setString(7, batch);
            statement.setString(8, event_type);
            statement.setString(9, location);

            int rowsAffected = statement.executeUpdate();

            if(rowsAffected>0)
                System.out.println("Successfully added event " + event_id);

        }catch (Exception e){
            e.printStackTrace();
        }finally {

            try {
                con.close();
            }catch (Exception ignore)
            {

            }
        }

    }

    public void updateEvent(String event_id, String event_name, String module_code, String start_time, String end_time, String lecturer, String batch, String event_type, String location) {
        // SQL COnnection Variable
        Connection con = null;
        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            // SQL Quarry
            String sql = "UPDATE event SET event_name=?, module_code=?, start_time=?, end_time=?, lecturer=?, batch=?, event_type=?, location=? WHERE event_id=?";
            //SQL Statement
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, event_name);
            statement.setString(2, module_code);
            statement.setString(3, start_time);
            statement.setString(4, end_time);
            statement.setString(5, lecturer);
            statement.setString(6, batch);
            statement.setString(7, event_type);
            statement.setString(8, location);
            statement.setString(9, event_id);

            int rowsAffected = statement.executeUpdate();

            if(rowsAffected>0)
                System.out.println("Successfully updated event " + event_id);

        }catch (Exception e){
            e.printStackTrace();
        }finally {

            try {
                con.close();
            }catch (Exception ignore)
            {

            }
        }
    }
    public boolean removeEvent(String event_id) {
        // SQL COnnection Variable
        Connection con = null;
        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            // SQL Quarry
            String sql = "DELETE FROM event WHERE event_id=?";
            //SQL Statement
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, event_id);

            int rowsAffected = statement.executeUpdate();

            if(rowsAffected>0) {
                System.out.println("Successfully Deleted event " + event_id);
                return true;
            }



        }catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Error !");
            alert.setContentText("Error Deleting event "+event_id);
            alert.show();
            return false;

        }finally {

            try {
                con.close();
            }catch (Exception ignore)
            {

            }
        }
        return false;
    }
}
