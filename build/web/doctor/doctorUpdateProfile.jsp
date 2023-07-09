<%-- 
    Document   : doctorUpdateProfile
    Created on : Dec 26, 2019, 1:46:51 AM
    Author     : HP-PAVILION
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
    <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
    

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
                        <li class="Active"><a href="DoctorController?function=displayUser">Profile</a></li>
                        <li class=""><a href="DoctorController?function=homePage">Appointments</a></li>
                        <li class=""><a href="DoctorController?function=logout">Logout</a></li>
                  </ul>
                </div>
              </div>
            </div>
          </nav>
            
       
           <div class = container>
            <div class="row">
                <div class="col-sm-4 col-md-5 col-md-offset-4">
                    <div class="sign-up">
                            <h1>Personal Details</h1><br>
                            
                          
                            <form action="DoctorController" method="post">
                                    <input type="hidden" name = "function" value="displayUser">
                                    <div class="form-groups">
                                    <input type="text" class="form-control" name="name" value="<c:out value = "${doctorInfo.name}" />" placeholder="Full Name">
                                    </div>
                                    <div class="form-groups">
                                    <input type="email" class="form-control" name="email" value="<c:out value = "${doctorInfo.email}" />" placeholder="Email Address">
                                    </div>
                                    <div class="form-groups">
                                    <input type="text" class="form-control" name="phone" value="<c:out value = "${doctorInfo.phone}" />" pattern="[0-9]{3}-[0-9]{7|8}" placeholder="Phone Number (e.g 019-6604766)">
                                    </div>
                                    <div class="form-inline">
                                    <div class="form-group" >
                                    <input type="text" class="form-control" name="DOB" value="<c:out value = "${doctorInfo.DOB}" />" pattern="(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/\d{4}" placeholder="DOB (e.g. DD/MM/YYYY)">
                                    </div>
                                    <div class="form-group" >
                                    <input type="text" class="form-control" name="Ic" value="<c:out value = "${doctorInfo.ic}" />" placeholder="IC ( e.g.990531035145)">
                                    </div>
                                    </div>
                                    <div class="form-groups" >
                                    <input type="password" class="form-control" name="password" value="<c:out value = "${doctorInfo.password}" />"minlength="6" placeholder="Password">
                                    </div>
                                   
                                    <p align="center"><button class="btn btn-primary btn-lg" type="submit">Submit</button></p> 
                                </form>
                            
                    </div>
                </div>
            </div>
        </div>
    </section>
    </body>
</html>