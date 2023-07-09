<%-- 
    Document   : adminUpdateProfile
    Created on : Dec 25, 2019, 11:09:34 PM
    Author     : nfizah
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%> 
<!DOCTYPE html>
<html>  
    
<head>
    <title>View Prescription</title>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" >
    <link rel="stylesheet" href="css/style.css" >
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>  
    <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    
            <style>
        table, th, td {
            border: 1px solid;
            border-collapse: collapse;
            border-color: black;
        }
        td {
            padding: 15px;
            text-align: left;
            color: black;
        }
        th{
            padding: 15px;
            text-align: left;
            color: white;
        }
        </style>   
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
                        <li class="active"><a href="AdminController?function=displayAdmin">Profile</a></li>  
                        <li class=""><a href="AdminController">Patient</a></li>
                        <li class=""><a href="AdminController?function=appointmentPage">Appointment</a></li>
                        <li class=""><a href="AdminController?function=manageStaffPage">Manage Staff</a></li>
                        <li class=""><a href="AdminController?function=inventoryPage">Inventory</a></li>
                        <li class=""><a href="AdminController?function=logout">Log out</a></li>
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
                            
                          
                            <form action="AdminController" method="post">
                                    <input type="hidden" name = "function" value="updateProfileAdmin">
                                    <table align="center" border="1">
                                        <tr>
                                        <th bgcolor="#008A8A">NAME</th>
                                        <td><c:out value = "${adminInfo.name}" /></td>
                                        </tr>
                                        <tr>
                                            <th bgcolor="#008A8A">E-MAIL</th>
                                            <td><c:out value = "${adminInfo.email}" /></td>
                                        </tr>
                                        <tr>
                                            <th bgcolor="#008A8A">PHONE NUMBER</th>
                                            <td><c:out value = "${adminInfo.phone}" /></td>
                                        </tr>
                                        <tr>
                                            <th bgcolor="#008A8A">DATE OF BIRTH</th>
                                            <td><c:out value = "${adminInfo.DOB}" /></td>
                                        </tr>
                                        <tr>
                                            <th bgcolor="#008A8A">I/C NUMBER</th>
                                            <td><c:out value = "${adminInfo.ic}" /></td>
                                        </tr>
                                    </table>
                                    <br><br>
                                    <p align="center"><button class="btn btn-primary btn-lg" type="submit">Update</button></p> 
                                </form>
                            
                    </div>
                </div>
            </div>
        </div>
            </div>
    </section>
<!--footer-->
    <footer id="footer">
      <div class="top-footer">
        <div class="container">
          <div class="row">
            <div class="col-md-4 col-sm-4 marb20">
              <div class="ftr-tle">
                <h4 class="white no-padding">About Us</h4>
              </div>
              <div class="info-sec">
                <p>Praesent convallis tortor et enim laoreet, vel consectetur purus latoque penatibus et dis parturient.</p>
              </div>
            </div>
            <div class="col-md-4 col-sm-4 marb20">
              <div class="ftr-tle">
                <h4 class="white no-padding">Quick Links</h4>
              </div>
              <div class="info-sec">
                <ul class="quick-info">
                  <li><a href="index.html"><i class="fa fa-circle"></i>Home</a></li>
                  <li><a href="#service"><i class="fa fa-circle"></i>Service</a></li>
                  <li><a href="#contact"><i class="fa fa-circle"></i>Appointment</a></li>
                </ul>
              </div>
            </div>
            <div class="col-md-4 col-sm-4 marb20">
              <div class="ftr-tle">
                <h4 class="white no-padding">Follow us</h4>
              </div>
              <div class="info-sec">
                <ul class="social-icon">
                  <li class="bglight-blue"><i class="fa fa-facebook"></i></li>
                  <li class="bgred"><i class="fa fa-google-plus"></i></li>
                  <li class="bgdark-blue"><i class="fa fa-linkedin"></i></li>
                  <li class="bglight-blue"><i class="fa fa-twitter"></i></li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="footer-line">
        <div class="container">
          <div class="row">
            <div class="col-md-12 text-center">
              Â© Copyright Medilab Theme. All Rights Reserved
              <div class="credits">
                <!--
                  All the links in the footer should remain intact.
                  You can delete the links only if you purchased the pro version.
                  Licensing information: https://bootstrapmade.com/license/
                  Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/buy/?theme=Medilab
                -->
                Designed by <a href="https://bootstrapmade.com/">BootstrapMade.com</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </footer>
    <!--/ footer-->

    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.easing.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/custom.js"></script>
    <script src="contactform/contactform.js"></script>

  </body>
</html>