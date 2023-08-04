package lk.ijse.thogakade.api;

import com.google.gson.Gson;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.thogakade.dto.CustomerDTO;
import lk.ijse.thogakade.dto.ItemDTO;

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

@WebServlet(urlPatterns = "/items")
public class ItemManage extends HttpServlet {
    Connection connection;
    DataSource pool;
    private List<ItemDTO> items = new ArrayList<>();

/*    @Override
    public void init() {
        try {
            InitialContext initialContext = new InitialContext();
            pool = (DataSource) initialContext.lookup("java:comp/env/jdbc/items");
            this.connection=pool.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO item values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setObject(1,0);
            preparedStatement.setObject(2,itemDTO.getItem_name());
            preparedStatement.setObject(3,itemDTO.getPrice());
            preparedStatement.setObject(4,itemDTO.getQty());

            System.out.println(itemDTO.getItem_name());

            int i = preparedStatement.executeUpdate();
            if (i>0){
                System.out.println("Added successfully");
            }
            ResultSet rst = preparedStatement.getGeneratedKeys();
            rst.next();
            resp.setContentType("application/json");
            Jsonb js = JsonbBuilder.create();
            js.toJson(itemDTO,resp.getWriter());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ItemDTO> items = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM item")) {

            while (resultSet.next()) {
                ItemDTO itemDTO = new ItemDTO(
                        resultSet.getString("item_id"),
                        resultSet.getString("item_name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("qty")
                );
                items.add(itemDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convert customers list to JSON and send as the response
        String json = new Gson().toJson(items);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Delete recieve");
        String id = req.getParameter("txtItemCode");
        System.out.println(id);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM item WHERE item_id=?");
            preparedStatement.setObject(1,id);

            boolean b=preparedStatement.executeUpdate()>0;
            PrintWriter writer = resp.getWriter();
            if(b){
                writer.write("Item delete successfully");
            }
        }catch (Exception e){

        }
    }
}
