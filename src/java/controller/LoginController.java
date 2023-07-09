/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.UserDAO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Adam
 */
@WebServlet(name = "loginController", urlPatterns = {"/loginController"})
public class LoginController extends HttpServlet {
    
    private User user = new User();
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        HttpSession session = request.getSession(true);
        
        String function = request.getParameter("function");
        
        if(function == null){
            function = "login";
        }
        
        try{
                switch(function){
                    
                    //redirect user respective interface
                    case "login":
                    loginUser(request, response);
                    break;
                    
                    //redirect user to sign up page
                    case "signUp":
                    signUpPage(request, response);
                    break;
                }
        }catch(Exception e){
            throw new ServletException(e);
        }
        
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        String function = request.getParameter("function");
        
        if(function == null){
            function = "login";
        }
        
        try{
                switch(function){
                    
                    case "login":
                        //redirect to respective user interface
                        findUser(request, response);
                        break;  
                }
            
        }catch(Exception e){
            throw new ServletException(e);
        }
        
    }

private void findUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        user.setEmail(email);
        user.setPassword(password);
        
        //validate user
        //check type of user
        if(userDAO.findUser(user)){
            
            if(user.getRole().equals("admin")){
                //create session for admin
                HttpSession session = request.getSession(true);
                session.setAttribute("admin", user.getEmail());
                
                //redirect to admin controller
                response.sendRedirect("AdminController");  
            }else if(user.getRole().equals("doctor")){
                //create session for doctor
                HttpSession session = request.getSession(true);
                session.setAttribute("doctor", user.getEmail());
                
                 //redirect to doctor controller
                response.sendRedirect("DoctorController");
            }else if(user.getRole().equals("patient")){
                //create session for patient
                HttpSession session = request.getSession(true);
                session.setAttribute("patient", user.getEmail());
                
                 //redirect to patient controller
                response.sendRedirect("PatientController");
            }else if(user.getRole().equals("pharmacist")){
                //create session for pharmacists
                HttpSession session = request.getSession(true);
                session.setAttribute("pharmacist", user.getEmail());
                
                //redirect to pharmacist controller
                response.sendRedirect("PharmacistController");
            }  
        }else{
                request.setAttribute("fail", "Invalid username or password!");
                loginUser(request, response);
            }
    }
  
private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    
    RequestDispatcher dispatcher;
    dispatcher = request.getRequestDispatcher("/login.jsp");
    dispatcher.forward(request, response);

}

private void signUpPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   RequestDispatcher dispatcher;
   dispatcher = request.getRequestDispatcher("/signUp.jsp");
   dispatcher.forward(request, response);
}

}

