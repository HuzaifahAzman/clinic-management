<%-- 
    Document   : admin
    Created on : Nov 8, 2019, 10:25:56 PM
    Author     : User
--%>

<%@page import="entity.Prescription"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%> 
<!DOCTYPE html>
<html>  
    
<head>
    <title>Prescribe Prescription</title>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" >
    <link rel="stylesheet" href="css/style.css" >
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>  
    <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
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
                        <li class=""><a href="DoctorController?function=displayUser">Profile</a></li>
                        <li class="active"><a href="DoctorController?function=homePage">Appointments</a></li>
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
                        <c:if test="${not empty failed}">
                            <div class="alert alert-dismissable alert-danger fade in">
                                <span type="button" class="close" data-dismiss="alert">&times;</span>
                                <strong>${failed}</strong>
                            </div>
                        </c:if>
                            <h1>Patient Information</h1><br>
                                <input type="hidden" name = "function" value="">
                                    <div class="form-groups">
                                        Name: <c:out value="${patientInfo.name}"/>
                                    </div>
                                    <div class="form-groups" >
                                        Date of Birth: <c:out value="${patientInfo.DOB}"/>
                                    </div>
                                    <div class="form-groups">
                                        Gender: <c:out value="${patientInfo.gender}"/>
                                    </div>
                                    <hr>
                                    <form name="prescriptionForm" action="DoctorController" method="post" id="prescriptionForm" >
                                        <input type="hidden" name="function" value="addPrescription">
                                        <input type="hidden" name="bookingID" value="${bookingID}">
                                        <input type="hidden" name="patientID" value="${patientInfo.userID}">
                                        <div id="hiddenValues"/>
                                        <div id="itemRows">
                                            <div class="text-left" style="padding-left: 70px">
                                            <br>Medicine: <select name="medicine[]" required autofocus />
                                                                <c:forEach var="temp" items="${inventory}">
                                                                    <option value="${temp.product_name}">${temp.product_name}</option>
                                                                </c:forEach>
                                                          </select><br><br>
                                            Dosage: <input type="text" name="dosage[]" required autofocus /> 
                                            <select name="dosage_type[]" required autofocus>
                                                        <option value="tablet">Tablet</option>
                                                        <option value="bottle">Bottle</option>
                                                    </select><br><br>
                                            Intake: <input type="text" name="intake[]" required autofocus /><br><br>
                                            Frequency: <input type="text" name="frequency[]" required autofocus /><br><br>
                                            Total Stock: <input type="text" name="stock[]" required autofocus /><br><br>
                                            </div>
                                            <input onclick="addRow();" type="button" value="Add Prescription" /><br>
                                        </div>
                                        <br><input type="submit" class="btn btn-primary " value="Submit"/>
                                    </form>         
                                        <br><br>
                            </div>
                        </div>     
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script>
        var rowNum = 0;
        function addRow() {
            rowNum ++;
            var row = '<hr><p id="rowNum'+rowNum+'">\n\
                        <div class="text-left" style="padding-left: 70px"> \n\
                        <br>Medicine: <select name="medicine[]" required autofocus>\n\
                            <c:forEach var="temp" items="${inventory}">\n\
                                <option value="${temp.product_name}">${temp.product_name}</option>\n\
                            </c:forEach> \n\
                            </select><br><br>\n\
                        Dosage: <input type="text" name="dosage[]" required autofocus> \n\
                        <select name="dosage_type[]" required autofocus>\n\
                                <option value="tablet">Tablet</option>\n\
                                <option value="bottle">Bottle</option>\n\
                            </select><br><br>\n\
                        Intake: <input type="text" name="intake[]" required autofocus><br><br> \n\
                        Fequency: <input type="text" name="frequency[]" required autofocus><br><br> \n\
                        Total Stock: <input type="text" name="stock[]" required autofocus /><br><br> \n\
                        </div> \n\
                        <input type="button" value="Remove" onclick="removeRow('+rowNum+');"></p>';
            jQuery('#itemRows').append(row);
        }

        function removeRow(rnum){
            jQuery('#rowNum'+rnum).remove();
        }
        
    </script>   
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

  <script src="js/jquery.min.js"></script>
  <script src="js/jquery.easing.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/custom.js"></script>
  <script src="contactform/contactform.js"></script>

</body>

</html>



