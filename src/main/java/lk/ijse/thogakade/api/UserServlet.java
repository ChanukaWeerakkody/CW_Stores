package lk.ijse.thogakade.api;

// UserServlet.java
import com.google.gson.Gson;

import lk.ijse.thogakade.dao.UseerDAO;
import lk.ijse.thogakade.dto.User;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserServlet {
   /* private UseerDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UseerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            List<User> users = userDAO.getAllUsers();
            String json = new Gson().toJson(users);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } else {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length == 2) {
                int userId = Integer.parseInt(pathParts[1]);
                User user = userDAO.getUserById(userId);
                if (user != null) {
                    String json = new Gson().toJson(user);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");

        if (name != null && ageStr != null) {
            int age = Integer.parseInt(ageStr);
            User user = new User();
            user.setName(name);
            user.setAge(age);
            userDAO.addUser(user);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String[] pathParts = pathInfo.split("/");
        if (pathParts.length != 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        int userId = Integer.parseInt(pathParts[1]);
        User userToUpdate = userDAO.getUserById(userId);
        if (userToUpdate == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");
        if (name != null && ageStr != null) {
            int age = Integer.parseInt(ageStr);
            userToUpdate.setName(name);
            userToUpdate.setAge(age);
            userDAO.updateUser(userToUpdate);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String[] pathParts = pathInfo.split("/");
        if (pathParts.length != 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        int userId = Integer.parseInt(pathParts[1]);
        User userToDelete = userDAO.getUserById(userId);
        if (userToDelete == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        userDAO.deleteUser(userId);
        response.setStatus(HttpServletResponse.SC_OK);
    }*/
}
