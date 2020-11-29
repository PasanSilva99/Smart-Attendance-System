package Common;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO {

    public List<String> getLocationCodeList(){
        System.out.println("Fetching Locations");

        // Hold all the location codes to return
        List<String> locationCodeList=new ArrayList<>();

        Connection con = null;

        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            // SQL Quarry
            String sql = "SELECT * FROM location";

            //SQL Statement
            PreparedStatement statement = con.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){

                    String code_ = resultSet.getString(1);
                    if(code_!=null) {
                        locationCodeList.add(code_);
                        System.out.println("Fetching: " + code_);
                    }

            }


        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {

            try {
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return locationCodeList;
    }
}
