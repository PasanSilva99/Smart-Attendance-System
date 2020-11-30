package Common;


import java.sql.*;
import java.util.ArrayList;
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

    //Add data to the degree table
    public void AddDegree(DegreeProgram degree) throws SQLException {
        Connection con = null;
        try {
            Class.forName(DAO.SqlDriverClass);
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);
            String sql = "INSERT INTO degree_program (degree_code, degree_name) VALUES (?, ?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, degree.getDegreeCode());
            statement.setString(2, degree.getDegreeName());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("New degree added successfully!");
            }
        }catch (Exception e){
            System.out.println("Degree Record Failed.");
        }finally {
            assert con != null;
            if(!con.isClosed())
            {
                con.close();
            }
        }
    }

    public List<DegreeProgram> getDegreeList() {
        List<DegreeProgram> DegreeList = new ArrayList<>();

        // SQL Connection con
        Connection con=null;

        try{
            // SQL Driver Class
            Class.forName(DAO.SqlDriverClass);
            // SQL Connection
            con = DriverManager.getConnection(DAO.DatabaseUrl, DAO.DBuser, DAO.DBpass);
            // SQL Quarry
            String sql = "SELECT * FROM degree_program";

            // SQL Statement
            PreparedStatement statement = con.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                String degree_name = resultSet.getString(2);
                String degree_code = resultSet.getString(1);


                DegreeList.add( new DegreeProgram(degree_name,degree_code));
                System.out.println("Fetching: Degree  "+degree_code+" " +degree_name+" ");

            }


        }catch (Exception e){

        }finally {
            try {
                con.close();
            }catch (Exception ignored){}
        }
        return DegreeList;
    }
}
