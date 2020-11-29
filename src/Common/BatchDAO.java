package Common;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BatchDAO {

    /**
     * Saves a new batch to the database
     * @param batch Batch Number and University
     * @throws SQLException
     */
    public void AddNewBatch(String batch) throws SQLException {
        // SQL Connection
        Connection con = null;
        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            //SQL Quarry
            String sql = "INSERT INTO batch VALUES (?)";

            // SQL Statement
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, batch);

            int rowsInserted = statement.executeUpdate();

            if(rowsInserted>0){
                System.out.println("Batch "+batch+" Inserted Successfully");
            }
        }catch (Exception e){
            System.out.println("ERROR <!> Adding New Batch "+batch+ " !!! "+e.getMessage());
        }finally {
            // if the connection is not null and the connection is not closed
            if(con!=null&&!con.isClosed()){
                con.close();  // Close the Connection
            }
        }
    }

    public void RemoveBatch(String batch) throws SQLException {
        // SQL Connection Variable
        Connection con=null;
        try{
            //SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            // SQL Quarry
            String sql= "DELETE FROM batch WHERE batch_number=?";

            // SQL Statement
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, batch);

            // Executing the variable with a variable to collect how many rows updated
            int rowsInserted = statement.executeUpdate();

            if(rowsInserted>0){
                System.out.println("Batch Deleted Successfully");
            }
            else {
                System.out.println("Unknown Error at Deleting Batch");
            }

        }catch (Exception e){
            System.out.println("ERROR <!> Deleting Batch "+batch +" !!! "+e.getMessage());
        }finally {
            // if the connection is not null and the connection is not closed
            if(con!=null&&!con.isClosed()){
                con.close();  // Close the Connection
            }
        }

    }

    public static List<String> getBatchList() {
        List<String> BatchList  = new ArrayList<>();

        // SQL Connection Variable
        Connection con = null;

        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            // SQL Quarry
            String sql = "SELECT * FROM batch";

            PreparedStatement statement = con.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                String batchName = resultSet.getString(1);
                if(batchName!=null)
                {
                    BatchList.add(batchName);
                }
            }

        }catch (Exception e){
            System.out.println("ERROR <!> Retrieving Batch List !!! "+e.getMessage());
        }finally {
            // if the connection is not null and the connection is not closed
            try {
                if (con != null && !con.isClosed()) {
                    con.close();  // Close the Connection
                }
            }catch (Exception ignored){

            }
        }
        return BatchList;
    }
}
