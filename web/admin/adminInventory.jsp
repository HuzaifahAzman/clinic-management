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
                        <li class=""><a href="AdminController?function=manageStaffPage">Manage Staff</a></li>
                        <li class="active"><a href="AdminController?function=inventoryPage">Inventory</a></li>
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
                        
                        <table align='center' class ="table text-left" id="invTable">
                            <thead>
                                <tr>
                                    <th style = "color: white;" scope="col">Index</th>
                                    <th style = "color: white;" scope="col">Product Name</th>
                                    <th style = "color: white;" scope="col">Product ID</th>
                                    <th style = "color: white;" scope="col">Supplier Name</th>
                                    <th style = "color: white;" scope="col">Quantity</th>
                                    <th style = "color: white;" scope="col">Price(RM)</th>
                                    <th style = "color: white;" scope="col"></th>
                                    <th style = "color: white;" scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${inventory}" var="currentinventory" varStatus="loop"> 
                                    
                                    <c:choose>
                                        
                                    <c:when test = "${currentinventory.product_id.equals(updateID)}">
                                        <tr>
                                            <form class="form-inv" action="AdminController" method="post">
                                                <input type="hidden" name="function" value="updateInventory">
                                                <td style = "color: white;" scope="col"><c:out value="${loop.index + 1}" /></td>
                                                <td style = "color: white;" scope="col"><p><input size="10" maxlength="20" type="text" name="product_name" id='product_name' value='${currentinventory.product_name}' required></td>
                                                <td style = "color: white;" scope="col"><p><input size="5" maxlength="5" type="text" name="product_id" id='product_id' value='${currentinventory.product_id}' readonly></p></td>
                                                <td style = "color: white;" scope="col"><p><input size="10" maxlength="25" type="text" name="supplier_name" id='supplier_name' value='${currentinventory.supplier_name}' required></p></td>
                                                <td style = "color: white;" scope="col"><p><input min="0" max="100" type="number" name="stock" id='stock' value='${currentinventory.stock}' required></p></td>
                                                <td style = "color: white;" scope="col"><p><input min="1" max="100" type="number" name="price" id='price' value='${currentinventory.price}' required></td>
                                                <td style = "color: white;" scope="col">
                                                    <input type="hidden" name="status" value="${currentinventory.status}">
                                                <input class="btn btn-inv" type='submit' name='button' id='button' value='Update'>
                                            </form>
                                            </td>
                                        </tr>
                                    </c:when>
                                    
                                    <c:otherwise>
                                    <tr>
                                        <td style = "color: white;" scope="col"><c:out value="${loop.index + 1}" /></td>
                                        <td style = "color: white;" scope="col"><c:out value="${currentinventory.product_name}" /></td>
                                        <td style = "color: white;" scope="col"><c:out value="${currentinventory.product_id}" /></td>
                                        <td style = "color: white;" scope="col"><c:out value="${currentinventory.supplier_name}" /></td>
                                        <td style = "color: white;" scope="col"><c:out value="${currentinventory.stock}" /></td>
                                        <td style = "color: white;" scope="col"><c:out value="${currentinventory.price}" /></td>
                                        <td style = "color: white;" scope="col">
                                            <form class="form-inv" action="AdminController" method="post">
                                                <input type="hidden" name="function" value="updateFormInventory">
                                                <input type="hidden" name="product_id" value="${currentinventory.product_id}">
                                                <input class="btn btn-inv" type='submit' name='button' id='button' value='Update'>
                                            </form>
                                            
                                            <c:if test = "${currentinventory.status == 'inactive'}">
                                                <form class="form-inv" action="AdminController" method="post">
                                                    <input type="hidden" name="function" value="activationInventory">
                                                    <input type="hidden" name="product_id" value="${currentinventory.product_id}">
                                                    <input class="btn btn-inv" type='submit' name='button' id='button' value='Activate'>
                                                </form>
                                            </c:if>
                                            <c:if test = "${currentinventory.status == 'active'}">
                                                <form class="form-inv" action="AdminController" method="post">
                                                    <input type="hidden" name="function" value="deactivationInventory">
                                                    <input type="hidden" name="product_id" value="${currentinventory.product_id}">
                                                    <input class="btn btn-inv" type='submit' name='button' id='button' value='Deactivate'>
                                                </form>
                                            </c:if>
                                                <form class="form-inv" action="AdminController" method="post">
                                                    <input type="hidden" name="function" value="deleteInventory">
                                                    <input type="hidden" name="product_id" value="${currentinventory.product_id}">
                                                    <input class="btn btn-inv" style="background-color: #d11a2a" type='submit' name='button' id='button' value='Delete'>
                                                </form>
                                        </td>
                                    </tr>
                                    </c:otherwise>
                                    
                                    </c:choose>
                                </c:forEach>
                                    <tr>
                                        <form action="AdminController" method="post">
                                            <input type="hidden" name="function" value="addInventory">
                                            <input type="hidden" name="status" value="active">
                                            
                                        <td style = "color: white;" scope="col"></td>
                                        <td style = "color: white;" scope="col"><p><input size="10" maxlength="20" type="text" name="product_name" required></p></td>
                                        <td style = "color: white;" scope="col"><p><input size="5" maxlength="5" type="text" name="product_id" required></p></td>
                                        <td style = "color: white;" scope="col"><p><input size="10" maxlength="25" type="text" name="supplier_name" required></p></td>
                                        <td style = "color: white;" scope="col"><p><input min="0" max="100" type="number" name="stock" required></p></td>
                                        <td style = "color: white;" scope="col"><p><input min="1" max="100" type="number" name="price" required></p></td>
                                        <td style = "color: white;" scope="col">
                                            <input class="btn btn-inv" style="background-color: darkcyan" type='submit' name='button' id='button' value='Add New'>
                                            <input class="btn btn-inv" style="background-color: darkcyan" type='reset' name='button' id='button' value='Reset'>
                                        </td>
                                        </form>
                                    </tr>
                            </tbody>
                        </table>
                    </div>    
                    <div class="col-1">
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
