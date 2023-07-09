<%-- 
    Document   : admin
    Created on : Nov 8, 2019, 10:25:56 PM
    Author     : User
--%>

<%@page import="entity.Inventory"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        <li class=""><a href="AdminController?function=appointmentPage">Appointment</a></li>
                        <li class="active"><a href="AdminController?function=manageStaffPage">Manage Staff</a></li>
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
                        <div class="col-12" style="position: relative; left: -60px">
                        
                        <table align='center' class ="table text-left" id="invTable">
                            <thead>
                                <tr>
                                    <th style = "color: white;" scope="col">Index</th>
                                    <th style = "color: white;" scope="col">Staff ID</th>
                                    <th style = "color: white;" scope="col">Name</th>
                                    <th style = "color: white;" scope="col">Email</th>
                                    <th style = "color: white;" scope="col">Phone</th>
                                    <th style = "color: white;" scope="col">DOB</th>
                                    <th style = "color: white;" scope="col">IC</th>
                                    <th style = "color: white;" scope="col">Gender</th>
                                    <th style = "color: white;" scope="col">Position</th>
                                    <th style = "color: white;" scope="col"></th>
                                    <th style = "color: white;" scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${allStaff}" var="staff" varStatus="loop"> 
                                    
                                    <c:choose>
                                        
                                    <c:when test = "${staff.userID == updateStaffID}">
                                        <tr>
                                            <form class="form-inv" action="AdminController" method="post">
                                                <input type="hidden" name="function" value="updateStaff">
                                                <td style = "color: white;" scope="col"><c:out value="${loop.index + 1}" /></td>
                                                <td style = "color: white;" scope="col"><p><input size="8" maxlength="10" type="text" name="staff_id" value='${staff.userID}' readonly></p></td>
                                                <td style = "color: white;" scope="col"><p><input size="8" maxlength="10" type="text" name="staff_name" value='${staff.name}' required></p></td>
                                                <td style = "color: white;" scope="col"><p><input size="18" maxlength="30" type="text" name="staff_email" value='${staff.email}' required></p></td>
                                                <input type="hidden" name="staff_password" value='${staff.password}'>
                                                <td style = "color: white;" scope="col"><p><input size="10" maxlength="25" type="text" name="staff_phone" value='${staff.phone}' required></p></td>
                                                <td style = "color: white;" scope="col"><p><input size="10" maxlength="25" type="text" name="staff_DOB" value='${staff.DOB}' required></p></td>
                                                <td style = "color: white;" scope="col"><p><input size="15" maxlength="25" type="text" name="staff_IC" value='${staff.ic}' required></p></td>
                                                <td style = "color: white;" scope="col"><p><input size="6" maxlength="10" type="text" name="staff_gender" value='${staff.gender}' required></p></td>
                                                <td style = "color: white;" scope="col"><p><input size="8" maxlength="10" type="text" name="staff_role" value='${staff.role}' required></p></td>
                                                
                                                <td style = "color: white;" scope="col">
                                                <input class="btn btn-inv" type='submit' name='button' id='button' value='Update'>
                                                </td>
                                                <td style = "color: white;" scope="col"></td>
                                            </form>
                                        </tr>
                                    </c:when>
                                    
                                    <c:otherwise>
                                    <tr>
                                        <td style = "color: white;" scope="col"><c:out value="${loop.index + 1}" /></td>
                                        <td style = "color: white;" scope="col"><c:out value="${staff.userID}" /></td>
                                        <td style = "color: white;" scope="col"><c:out value="${staff.name}" /></td>
                                        <td style = "color: white;" scope="col"><c:out value="${staff.email}" /></td>
                                        <td style = "color: white;" scope="col"><c:out value="${staff.phone}" /></td>
                                        <td style = "color: white;" scope="col"><c:out value="${staff.DOB}" /></td>
                                        <td style = "color: white;" scope="col"><c:out value="${staff.ic}" /></td>
                                        <td style = "color: white;" scope="col"><c:out value="${staff.gender}" /></td>
                                        <td style = "color: white;" scope="col"><c:out value="${staff.role}" /></td>
                                        <td style = "color: white;" scope="col">
                                            <form class="form-inv" action="AdminController" method="post">
                                                <input type="hidden" name="function" value="updateFormStaff">
                                                <input type="hidden" name="staff_id" value="${staff.userID}">
                                                <input class="btn btn-inv" type='submit' name='button' id='button' value='Update'>
                                            </form>
                                        </td>
                                        <td style = "color: white;" scope="col">
                                            <form class="form-inv" action="AdminController" method="post">
                                                <input type="hidden" name="function" value="deleteStaff">
                                                <input type="hidden" name="staff_id" value="${staff.userID}">
                                                <input class="btn btn-inv" style="background-color: #d11a2a" type='submit' name='button' id='button' value='Delete'>
                                            </form>
                                        </td>
                                    </tr>
                                    </c:otherwise>
                                    
                                    </c:choose>
                                </c:forEach>
                                    <form class="form-inv"  action="AdminController" method="post">
                                    <tr>
                                            <input type="hidden" name="function" value="addStaff">
                                        <td style = "color: white;" scope="col"></td>
                                        <td style = "color: white;" scope="col"></td>
                                        <td style = "color: white;" scope="col"><p><input size="8" maxlength="10" type="text" name="staff_name" placeholder="First Name " required></p></td>
                                        <td style = "color: white;" scope="col"><p><input size="18" maxlength="30" type="email" name="staff_email" placeholder="Email Address" required></p></td>
                                        <td style = "color: white;" scope="col"><p><input size="10" maxlength="25" type="text" name="staff_phone" maxlength="11" pattern="[0-9]{3}-[0-9]{7|8}" placeholder="(e.g 019-6604766)" required></p></td>
                                        <td style = "color: white;" scope="col"><p><input size="10" maxlength="25" type="text" name="staff_DOB" pattern="(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/\d{4}" placeholder="(e.g. DD/MM/YYYY)" required></p></td>
                                        <td style = "color: white;" scope="col"><p><input size="15" maxlength="25" type="text" name="staff_IC" placeholder="(e.g. 990531035145)" required></p></td>
                                        <td style = "color: white;" scope="col">
                                            <select name = "staff_gender">
                                                <option value="male" required> Male &nbsp;&nbsp;&nbsp;
                                                <option value="female" required> Female 
                                            </select>
                                        </td>
                                        <td style = "color: white;" scope="col">
                                            <select name = "staff_role">
                                                <option value="doctor" required> Doctor &nbsp;&nbsp;&nbsp;
                                                <option value="pharmacist" required> Pharmacist 
                                            </select>
                                        </td>
                                        <td style = "color: white;" scope="col">
                                            <input class="btn btn-inv" style="background-color: darkcyan" type='submit' name='button' id='button' value='Add New'>
                                        </td>
                                        <td style = "color: white;" scope="col">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style = "color: white;" scope="col"></td>
                                        <td style = "color: white;" scope="col"></td>
                                        <td style = "color: white;" scope="col"></td>
                                        <td style = "color: white;" scope="col">  <p><input size="18" maxlength="10" type="password" name="staff_password" placeholder="Password" minlength="6" required></p>  </td>
                                        <td style = "color: white;" scope="col"></td>
                                        <td style = "color: white;" scope="col"></td>
                                        <td style = "color: white;" scope="col"></td>
                                        <td style = "color: white;" scope="col"></td>
                                        <td style = "color: white;" scope="col"></td>
                                        <td style = "color: white;" scope="col">
                                            <input class="btn btn-inv" style="background-color: darkcyan" type='reset' name='button' id='button' value='Reset'>
                                        </td>
                                        <td style = "color: white;" scope="col"></td>
                                        
                                    </tr>
                                    </form>
                            </tbody>
                        </table>
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
