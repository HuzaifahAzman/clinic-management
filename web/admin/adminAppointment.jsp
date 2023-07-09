<%-- 
    Document   : adminAppointment
    Created on : Nov 8, 2019, 10:25:56 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.AppointmentHistory"%>
<%@page import="entity.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
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
                          <li class=""><a href="AdminController?function=displayAdmin">Profile</a></li>
                        <li class=""><a href="AdminController">Patient</a></li>
                        <li class="active"><a href="AdminController?function=appointmentPage">Appointment</a></li>
                        <li class=""><a href="AdminController?function=manageStaffPage">Manage Staff</a></li>
                        <li class=""><a href="AdminController?function=inventoryPage">Inventory</a></li>
                        <li class=""><a href="AdminController?function=logout">Log out</a></li>
                      </ul>
                    </div>
                  </div>
                </div>
            </nav>
            
            <div class="container">
                <div class="row">
                    <div class="banner-info">
                        <div class="col-1">
                        </div>
                        
                        <div class="col-10">
                            <table class ="table text-left">
                                <thead>
                                    <tr> 
                                        <th style = "color: white;" scope="col">Index</th>
                                        <th style = "color: white;" scope="col">Year</th>
                                        <th style = "color: white;" scope="col">Month</th>
                                        <th style = "color: white;" scope="col">Appointment Counts</th>
                                        <th style = "color: white;" scope="col"></th>
                                    </tr>
                                </thead>

                                <c:forEach items="${monthlyAptHistory}" var="currentappointment" varStatus="loop">
                                        <tr>                        
                                            <td style = "color: white;" ><c:out value="${loop.index + 1}" /></td>
                                            <td style = "color: white;" >${currentappointment.year}</td>
                                            <td style = "color: white;" >${currentappointment.getMonthString()}</td>
                                            <td style = "color: white;" >${currentappointment.appointmentCount}</td>
                                            <td style = "color: white;" >
                                                <form class="form-inv" action="AdminController" method="post">
                                                    <input type="hidden" name="function" value="appointmentHistory">
                                                    <input type="hidden" name="year" value="${currentappointment.year}">
                                                    <input type="hidden" name="month" value="${currentappointment.month}">
                                                    <input class="btn btn-inv" type='submit' name='button' id='button' value='View Appointments'>
                                                </form>
                                                <form class="form-inv" action="AdminController" method="post">
                                                    <input type="hidden" name="function" value="appointmentReport">
                                                    <input type="hidden" name="year" value="${currentappointment.year}">
                                                    <input type="hidden" name="month" value="${currentappointment.month}">
                                                    <input class="btn btn-inv" type='submit' name='button' id='button' value='Report'>
                                                </form>
                                            </td>
                                        </tr>
                                </c:forEach>
                            </table>  
                            
                            <c:if test = "${displayAppointmentHistory == 'yes'}">
                                <table class ="table text-left">
                                    <thead>
                                        <tr backgound-color=""> 
                                            <th style = "color: white;" scope="col">Index</th>
                                            <th style = "color: white;" scope="col">Date</th>
                                            <th style = "color: white;" scope="col">Time</th>
                                            <th style = "color: white;" scope="col">Symptoms</th>
                                            <th style = "color: white;" scope="col">Doctor</th>
                                            <th style = "color: white;" scope="col">Status</th>
                                        </tr>
                                    </thead>

                                    <c:forEach var="tempBook" items="${monthlyHistory}" varStatus="loop">
                                           <tr>
                                               <th style = "color: white;" ><c:out value="${loop.index + 1}" /></th>
                                               <td style = "color: white;" >${tempBook.date}</td>
                                               <td style = "color: white;" >${tempBook.time}</td>
                                               <td style = "color: white;" >${tempBook.symptoms}</td>
                                               <td style = "color: white;" >${tempBook.doctorName}</td>
                                               <td style = "color: white; ">${tempBook.status}</td>
                                           </tr>
                                   </c:forEach>

                                </table>
                            </c:if>
                            
                            <c:if test = "${displayAppointmentReport == 'yes'}">
                                    <table class ="table text-left">
                                        <thead>
                                            <tr backgound-color=""> 
                                                <th style = "color: white;" scope="col">Doctor</th>
                                                <th style = "color: white;" scope="col">Total Booking</th>
                                                <th style = "color: white;" scope="col">Completed</th>
                                                <th style = "color: white;" scope="col">Pending</th>
                                                <th style = "color: white;" scope="col">Approved</th>
                                                <th style = "color: white;" scope="col">Rejected</th>
                                            </tr>
                                        </thead>

                                        <c:forEach var="temp" items="${monthlyHistoryReport}" varStatus="loop">
                                               <tr>
                                                   <td style = "color: white;" >${temp.doctorName}</td>
                                                   <td style = "color: white;" >${temp.appointmentCount}</td>
                                                   <td style = "color: white;" >${temp.completedCount}</td>
                                                   <td style = "color: white;" >${temp.pendingCount}</td>
                                                   <td style = "color: white; ">${temp.approvedCount}</td>
                                                   <td style = "color: white; ">${temp.rejectedCount}</td>
                                               </tr>
                                       </c:forEach>
                                    </table>
                            </c:if>
                        </div>
                                    
                        <div class="col-1">
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
</body>
</html>
