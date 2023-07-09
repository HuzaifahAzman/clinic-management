<%-- 
    Document   : doctorValidateAppointment
    Created on : Dec 8, 2019, 10:57:44 PM
    Author     : User
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Validate Appointments</title>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" >
    <link rel="stylesheet" href="css/style.css" >
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>          
</head>
<body>
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
                        <li class=""><a href="DoctorController?function=displayUser">Profile</a></li>
                        <li class="active"><a href="DoctorController?function=homePage">Appointments</a></li>
                        <li class=""><a href="DoctorController?function=logout">Logout</a></li>
                      </ul>
                    </div>
                  </div>
                </div>
            </nav>
        
        
        <br><br><br><br><br><br>
     
        <div class="container">
            <a href ="DoctorController?function=homePage" class="btn btn-primary">Validate Appointment</a>
            <a href ="DoctorController?function=todaysAppointment" class="btn btn-primary">Today's Appointment</a>
            <a href ="DoctorController?function=viewUpcomingAppointment" class="btn btn-primary">View Upcoming Appointments</a>    
            <a href ="DoctorController?function=appointmentHistory" class="btn btn-primary">View Appointment History</a> 
            <br><br>
            <c:if test="${not empty success}">
                <div class="alert alert-dismissable alert-success">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>${success}</strong>
                </div>    
            </c:if>

            <c:if test="${not empty fail}">
                <div class="alert alert-dismissable alert-success">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>${fail}</strong>
                </div>    
            </c:if>
            <table class ="table text-left">
                <thead>
                    <tr>
                        <th style = "color: white;" scope="col">Patient</th>
                        <th style = "color: white;" scope="col">Time</th>
                        <th style = "color: white;" scope="col">Symptoms</th>
                        <th style = "color: white;" scope="col">Date</th>
                        <th style = "color: white;" scope="col">Status</th>
                        <th style = "color: white;" scope="col">Action</th>
                    </tr>
                </thead>
                
                <c:forEach var="tempBook" items="${appointmentList}">
                    
                    <c:url var="approveLink" value="DoctorController">
                        <c:param name="function" value="approveAppointment"/>
                        <c:param name="bookingID" value="${tempBook.booking_id}"/>
                    </c:url>
                    
                    <c:url var="rejectLink" value="DoctorController">
                        <c:param name="function" value="rejectAppointment"/>
                        <c:param name="bookingID" value="${tempBook.booking_id}"/>
                    </c:url>
                    
                        <tr>
                            <td style = "color: white;" >${tempBook.patientName}</td>
                            <td style = "color: white;" >${tempBook.time}</td>
                            <td style = "color: white;" >${tempBook.symptoms}</td>
                            <td style = "color: white;" >${tempBook.date}</td>
                            <td style = "color: white; ">${tempBook.status}</td>
                            <td style = "color: white; "><a href="${rejectLink}" class="btn btn-danger"
                                                            onclick="if (!(confirm('Are you sure you want to reject the appointment?')))                                                                
                                                                        return false">Reject</a>
                                                                    <a href="${approveLink}" class="btn btn-success">Accept</a></td>
                                                                
                        </tr>
                </c:forEach>
            </table>    
        </div>
        </div>
    </section>
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
                © Copyright Medilab Theme. All Rights Reserved
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

