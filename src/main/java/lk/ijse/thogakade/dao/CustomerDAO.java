package lk.ijse.thogakade.dao;

import lk.ijse.thogakade.dto.CustomerDTO;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDAO extends HttpServlet {
    /*Connection connection;

    @Override
    public void init() throws ServletException {
        try {
            InitialContext initialContext = new InitialContext();
            DataSource pool = (DataSource) initialContext.lookup("java:comp/env/jdbc/customer");
            this.connection=pool.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertCustomer(CustomerDTO customerDTO) throws SQLException {
        // try-with-resource statement will auto close the connection.

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Customer values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setObject(1,0);
        preparedStatement.setString(2,customerDTO.getName());
        preparedStatement.setString(3,customerDTO.getCity());
        preparedStatement.setString(4,customerDTO.getContact());

        preparedStatement.executeUpdate();

    }*/
}
