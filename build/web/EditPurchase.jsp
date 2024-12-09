<%-- 
    Document   : EditMedicine
    Created on : 21 Jan 2024, 1:39:20 am
    Author     : USER
--%>


<%@page import="com.Model.Supplier"%>
<%@page import="com.DAO.SupplierDao"%>
<%@page import="com.Model.Purchase"%>
<%@page import="com.DAO.PurchaseDao"%>
<%@ page import="java.util.List" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet" href="styles.css">
        <title>Pharmacy</title>
        <style>
            @import url("https://fonts.googleapis.com/css2?family=Nunito:wght@400;600;700&display=swap");
            :root {
  --header-height: 3rem;
  --nav-width: 68px;
  --first-color: #2C3136;
  --first-color-light: #AFA5D9;
  --white-color: #F7F6FB;
  --body-font: 'Nunito', sans-serif;
  --normal-font-size: 1rem;
  --z-fixed: 100;
}

*,
::before,
::after {
  box-sizing: border-box;
}

body {
  position: relative;
  margin: var(--header-height) 0 0 0;
  padding: 0 1rem;
  font-family: var(--body-font);
  font-size: var(--normal-font-size);
  transition: .5s;
}

a {
  text-decoration: none;
}

.header {
  width: 100%;
  height: var(--header-height);
  position: fixed;
  top: 0;
  left: 0;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 1rem;
  background-color: var(--white-color);
  z-index: var(--z-fixed);
  transition: .5s;
}

.header_toggle {
  color: var(--first-color);
  font-size: 1.5rem;
  cursor: pointer;
}

.header_img {
  width: 35px;
  height: 35px;
  display: flex;
  justify-content: center;
  border-radius: 50%;
  overflow: hidden;
}

.header_img img {
  width: 40px;
}

.l-navbar {
  position: fixed;
  top: 0;
  left: -30%;
  width: var(--nav-width);
  height: 100vh;
  background-color: var(--first-color);
  padding: .5rem 1rem 0 0;
  transition: .5s;
  z-index: var(--z-fixed);
}

.nav {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  overflow: hidden;
}

.nav_logo,
.nav_link {
  display: grid;
  grid-template-columns: max-content max-content;
  align-items: center;
  column-gap: 1rem;
  padding: .5rem 0 .5rem 1.5rem;
}

.nav_logo {
  margin-bottom: 2rem;
}

.nav_logo-icon {
  font-size: 1.25rem;
  color: var(--white-color);
}

.nav_logo-name {
  color: var(--white-color);
  font-weight: 700;
}

.nav_link {
  position: relative;
  color: var(--first-color-light);
  margin-bottom: 1.5rem;
  transition: .3s;
}

.nav_link:hover {
  color: var(--white-color);
}

.nav_icon {
  font-size: 1.25rem;
}

.show {
  left: 0;
}

.body-pd {
  padding-left: calc(var(--nav-width) + 1rem);
}

.active {
  color: var(--white-color);
}

.active::before {
  content: '';
  position: absolute;
  left: 0;
  width: 2px;
  height: 32px;
  background-color: var(--white-color);
}

.height-100 {
  height: 100vh;
}

@media screen and (min-width: 768px) {
  body {
    margin: calc(var(--header-height) + 1rem) 0 0 0;
    padding-left: calc(var(--nav-width) + 2rem);
  }

  .header {
    height: calc(var(--header-height) + 1rem);
    padding: 0 2rem 0 calc(var(--nav-width) + 2rem);
  }

  .header_img {
    width: 40px;
    height: 40px;
  }

  .header_img img {
    width: 45px;
  }

  .l-navbar {
    left: 0;
    padding: 1rem 1rem 0 0;
  }

  .show {
    width: calc(var(--nav-width) + 156px);
  }

  .body-pd {
    padding-left: calc(var(--nav-width) + 188px);
  }
}

.bxs-capsule {
    color: var(--white-color);
}
        </style>
    </head>
    <body>
        <body id="body-pd">
    <header class="header" id="header">
        <div class="header_toggle"> <i class='bx bx-menu' id="header-toggle"></i> </div>
    </header>
    <div class="l-navbar" id="nav-bar">
        <nav class="nav">
                    <div> 
                        <a href="#" class="nav_logo"> <i class='bx bxs-capsule nav_icon' ></i>
                            <span class="nav_logo-name">Pharmacy</span> 
                        </a>
                        <div class="nav_list"> 
                            <a href="AdminHome.jsp" class="nav_link"> <i class='bx bx-grid-alt nav_icon'></i> 
                                <span class="nav_name">Dashboard</span> 
                            </a>                    
                            
                            <a href="SupplierList.jsp" class="nav_link"> <i class='bx bx-message-square-detail nav_icon'></i>
                                <span class="nav_name">Supplier</span> 
                            </a>
                            
<!--                            <a href="#" class="nav_link"> <i class='bx bx-folder nav_icon'></i>
                                <span class="nav_name">Stock</span> 
                            </a> -->
                            
                            <a href="Purchase.jsp" class="nav_link active"> <i class='bx bx-folder nav_icon'></i>
                                <span class="nav_name">Purchase</span> 
                            </a> 
                            
                            
                            <a href="Account.jsp" class="nav_link"> <i class='bx bx-user nav_icon'></i>
                                <span class="nav_name">Account</span>
                            </a> 
                        </div>
                    </div> 
                    <a href="Logout" class="nav_link"> <i class='bx bx-log-out nav_icon'></i> 
                        <span class="nav_name">SignOut</span> 
                    </a>
                </nav>
    </div>
    <!--Container Main start-->
    <div class="height-100 bg-light">

        
        <%
    // Import necessary classes
    int purchaseId = Integer.parseInt(request.getParameter("id"));
    PurchaseDao purchaseDAO = new PurchaseDao();
    Purchase purchase = purchaseDAO.getPurchaseById(purchaseId);
    SupplierDao supplierDAO = new SupplierDao();
    List<Supplier> suppliers = supplierDAO.getAllSuppliers();
%>

<div class="container mt-5">
    <h2>Edit Purchase</h2>

    <!-- Edit Purchase Form -->
    <form action="PurchaseController" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= purchase.getId() %>">

        <div class="form-group mb-3">
            <label for="supplierId" class="form-label">Supplier</label>
            <select class="form-control" id="supplierId" name="supplierId" required>
                <% if (suppliers != null) { %>
                    <% for (Supplier supplier : suppliers) { %>
                        <option value="<%= supplier.getId() %>" <%= supplier.getId() == purchase.getSupplierId() ? "selected" : "" %>>
                            <%= supplier.getSupplierName() %>
                        </option>
                    <% } %>
                <% } else { %>
                    <option value="">No suppliers available</option>
                <% } %>
            </select>
        </div>
        <div class="form-group mb-3">
            <label for="batchId" class="form-label">Batch ID</label>
            <input type="text" class="form-control" id="batchId" name="batchId" value="<%= purchase.getBatchId() %>" required>
        </div>
        <div class="form-group mb-3">
            <label for="expiryDate" class="form-label">Expiry Date</label>
            <input type="date" class="form-control" id="expiryDate" name="expiryDate" value="<%= purchase.getExpiryDate() %>" required>
        </div>
        <div class="form-group mb-3">
            <label for="stockQty" class="form-label">Stock Quantity</label>
            <input type="number" class="form-control" id="stockQty" name="stockQty" value="<%= purchase.getStockQty() %>" required>
        </div>
        <div class="form-group mb-3">
            <label for="boxPattern" class="form-label">Box Pattern</label>
            <input type="text" class="form-control" id="boxPattern" name="boxPattern" value="<%= purchase.getBoxPattern() %>" required>
        </div>
        <div class="form-group mb-3">
            <label for="boxQty" class="form-label">Box Quantity</label>
            <input type="number" class="form-control" id="boxQty" name="boxQty" value="<%= purchase.getBoxQty() %>" required>
        </div>
        <div class="form-group mb-3">
            <label for="quantity" class="form-label">Quantity</label>
            <input type="number" class="form-control" id="quantity" name="quantity" value="<%= purchase.getQuantity() %>" required>
        </div>
        <div class="form-group mb-3">
            <label for="supplierPrice" class="form-label">Supplier Price</label>
            <input type="text" class="form-control" id="supplierPrice" name="supplierPrice" value="<%= purchase.getSupplierPrice() %>" required>
        </div>
        <!-- No need for totalPurchasePrice input as it will be calculated automatically -->

        <button type="submit" class="btn btn-primary">Update Purchase</button>
        <button type="button" class="btn btn-secondary" onclick="goBack()">Back</button>
    </form>
</div>


    <!-- Display Medicines -->

    <!--Container Main end-->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.2.1/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@iconify/iconify@v2.3.1/dist/iconify.min.js"></script>

    <script>
        document.addEventListener("DOMContentLoaded", function(event) {
   
const showNavbar = (toggleId, navId, bodyId, headerId) =>{
const toggle = document.getElementById(toggleId),
nav = document.getElementById(navId),
bodypd = document.getElementById(bodyId),
headerpd = document.getElementById(headerId)

// Validate that all variables exist
if(toggle && nav && bodypd && headerpd){
toggle.addEventListener('click', ()=>{
// show navbar
nav.classList.toggle('show')
// change icon
toggle.classList.toggle('bx-x')
// add padding to body
bodypd.classList.toggle('body-pd')
// add padding to header
headerpd.classList.toggle('body-pd')
})
}
}

showNavbar('header-toggle','nav-bar','body-pd','header')

/*===== LINK ACTIVE =====*/
const linkColor = document.querySelectorAll('.nav_link')

function colorLink(){
if(linkColor){
linkColor.forEach(l=> l.classList.remove('active'))
this.classList.add('active')
}
}
linkColor.forEach(l=> l.addEventListener('click', colorLink))

 // Your code to run since DOM is loaded and ready
});
function goBack() {
        // Display a confirmation dialog
        var confirmation = confirm("Are you sure you want to cancel the editing and go back to the medicines list page?");
        
        // If the user confirms, navigate back
        if (confirmation) {
            window.location.href = "ManageMedicine.jsp"; // Replace with the actual URL of your list page
        }
    }
    </script>
    </body>
</html>