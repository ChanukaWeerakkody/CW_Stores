package lk.ijse.thogakade.api;

import com.google.gson.Gson;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.thogakade.dto.CustomerDTO;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/register")
public class CustomerManage extends HttpServlet {
    Connection connection;
    DataSource pool;
    private List<CustomerDTO> customers = new ArrayList<>();

    @Override
    public void init() {
        try {
            InitialContext initialContext = new InitialContext();
            pool = (DataSource) initialContext.lookup("java:comp/env/jdbc/register");
            this.connection=pool.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO customer values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setObject(1,0);
        preparedStatement.setObject(2,customerDTO.getName());
        preparedStatement.setObject(3,customerDTO.getCity());
        preparedStatement.setObject(4,customerDTO.getContact());

        int i = preparedStatement.executeUpdate();
        if (i>0){
            System.out.println("Added successfully");
        }
        ResultSet rst = preparedStatement.getGeneratedKeys();
        rst.next();
        resp.setContentType("application/json");
        Jsonb js = JsonbBuilder.create();
        js.toJson(customerDTO,resp.getWriter());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CustomerDTO> customers = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM customer")) {

            while (resultSet.next()) {
                CustomerDTO customer = new CustomerDTO(
                        resultSet.getString("id"),
                        resultSet.getString("fname"),
                        resultSet.getString("city"),
                        resultSet.getString("contact")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convert customers list to JSON and send as the response
        String json = new Gson().toJson(customers);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Delete recieve");
        String id = req.getParameter("txtCusId");
        System.out.println(id);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE id=?");
            preparedStatement.setObject(1,id);

            boolean b=preparedStatement.executeUpdate()>0;
            PrintWriter writer = resp.getWriter();
            if(b){
                writer.write("Customer delete successfully");
            }
        }catch (Exception e){

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("PUT method recieve");
        String id = req.getParameter("id");

        String name = req.getParameter("name");
        String city = req.getParameter("city");
        String contact = req.getParameter("contact");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customer SET fname=?,city=?,contact=? WHERE id=?");
            preparedStatement.setObject(1,name);
            preparedStatement.setObject(2,city);
            preparedStatement.setObject(3,contact);
            preparedStatement.setObject(4,id);

            boolean b=preparedStatement.executeUpdate()>0;
            PrintWriter writer = resp.getWriter();
            if(b){
                writer.write("Customer update successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(id+" "+name+" "+city+" "+contact);
    }
}
