<%-- 
    Document   : signUp
    Created on : Nov 29, 2019, 10:03:21 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" >
        <link rel="stylesheet" href="css/style.css" >
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body  id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60"> 
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
                    <li class=""><a href="index.html">Home</a></li>
                    <li class=""><a href="index.html#service">Services</a></li>
                    <li class=""><a href="index.html#about">About</a></li>
                    <li class=""><a href="index.html#testimonial">Testimonial</a></li>
                    <li class=""><a href="index.html#contact">Contact</a></li>
                    <li class=""><a href="login.jsp">Login</a></li>
                  </ul>
                </div>
              </div>
            </div>
          </nav>
        <div class = container>
            <div class="row">
                <div class="col-sm-4 col-md-5 col-md-offset-4">
                    <div class="sign-up">
                        <!--<img class="register" src="img/registration-icon-blue.png" alt="">-->
                            <h1>Registration form</h1><br>
                            <form action="PatientController" method="post">
                                <input type="hidden" name = "function" value="registerPatient">
                                    <div class="form-groups">
                                        <input type="text" class="form-control" name = "name" placeholder="First Name " required autofocus>
                                    </div>
                                    <div class="form-inline">
                                    <div class="form-group" >
                                        <input type="text" class="form-control" name = "ic" placeholder="IC ( e.g.990531035145)" required autofocus>
                                    </div>
                                    <div class="form-group" >
                                        <input type="text" class="form-control" name = "dob"  pattern="(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/\d{4}" placeholder="DOB (e.g. DD/MM/YYYY)" required autofocus>
                                    </div> 
                                    </div> 
                                    <div class="form-groups" >
                                        <input type ="text" class="form-control" name="phone" maxlength="11" pattern="[0-9]{3}-[0-9]{7|8}" placeholder="Phone Number (e.g 019-6604766)">
                                    </div>   
                                    <div id="gender" >
                                        <input type="radio" name = "gender" value="male" required> Male &nbsp;&nbsp;&nbsp;
                                        <input type="radio" name = "gender" value="female" required> Female      
                                    </div>
                                    <div class="form-groups">
                                         <input type="email" class="form-control" name = "email" placeholder="Email Address" required autofocus>
                                    </div>
                                    <div class="form-groups">
                                        <input type="password" class="form-control" name = "password" placeholder="Password" minlength="6" required autofocus>
                                    </div>
                                    <p align="center"><button class="btn btn-primary btn-lg" type="submit">Submit</button></p>
                                </div>
                             </div>     
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    </body>
</html>
