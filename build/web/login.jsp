<%-- 
    Document   : login
    Created on : Nov 8, 2019, 4:30:44 PM
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" >
    <link rel="stylesheet" href="css/style.css" >
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
    <body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">
      <section id="banner" class="banner">
        <div class="bg-color">
          <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container">
              <div class="col-md-12">
                <div class="navbar-header">
                  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                                            <span class="icon-bar"></span>
                                            <span class="icon-bar"></span>
                                            <span class="icon-bar"></span>
                                          </button>
                  <a class="navbar-brand" href="#"><img src="img/logo.png" class="img-responsive" style="width: 140px; margin-top: -16px;"></a>
                </div>
                <div class="collapse navbar-collapse navbar-right" id="myNavbar">
                  <ul class="nav navbar-nav">
                    <li class=""><a href="index.jsp">Home</a></li>
                    <li class=""><a href="index.jsp#service">Services</a></li>
                    <li class=""><a href="index.jsp#about">About</a></li>
                    <li class=""><a href="index.jsp#testimonial">Testimonial</a></li>
                    <li class=""><a href="index.jsp#contact">Contact</a></li>
                    <li class=""><a href="login.jsp">Login</a></li>
                  </ul>
                </div>
              </div>
            </div>
          </nav>
        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-md-4 col-md-offset-4">                    
                    <div class="account-wall">
                        <c:if test="${not empty fail}">
                            <div class="alert alert-dismissable alert-danger fade in">
                                <span type="button" class="close" data-dismiss="alert">&times;</span>
                                <strong>${fail}</strong>
                            </div>
                        </c:if>
                        <img class="profile-img" src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120"
                            alt="">
                        <form class="form-signin" action="loginController" method="post">
                            <input type="hidden" name="function" value="login">
                            <input type="text" class="form-control" name = "email" placeholder="Email" required autofocus>
                            <input type="password" class="form-control" name = "password" placeholder="Password" required>
                            <button class="btn btn-lg btn-primary btn-block" type="submit">
                                Sign in</button>
                        </form>
            </div>
                <label class="form-signin checkbox text-center">
                           <p>Don't have an account?</p>
                                       <a href="loginController?function=signUp" class="text-center" id="button" >Sign up!</a><span class="clearfix"></span>
                </label> 
        </div>
            </div>
        </div>
    </section>

</body>
</html>


