package Common;

import com.sun.javafx.geom.transform.BaseTransform;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class DegreeDAO {
    public List<String> getAllDegreePrograms() {
        List<String> DegreeList = new LinkedList<>();
        // SQL Connection Variable
        Connection con = null;

        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);

            // SQL Quarry
            String sql = "SELECT * FROM degree_program";

            PreparedStatement statement = con.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                DegreeList.add(resultSet.getString(1)+ " " +resultSet.getString(2));
            }

        }catch (Exception e){

        }finally {
            try{
                con.close();
            }catch (Exception ignored){

            }

        }
        return DegreeList;
    }
}
