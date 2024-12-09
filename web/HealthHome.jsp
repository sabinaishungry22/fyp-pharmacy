<%-- 
    Document   : Start
    Created on : 11 Jan 2024, 1:29:40 am
    Author     : USER
--%>

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
  font-size: larger;
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
.overview-boxes {
    display: flex;
    justify-content: space-around;
    flex-wrap: wrap;
    margin-top: 20px;
}

.box {
    background: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.05);
    width: 45%;
    margin: 10px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.box .right-side {
    display: flex;
    flex-direction: column;
}

.box .box-topic {
    font-size: 20px;
    font-weight: 600;
    color: #2C3136;
}

.box .number {
    font-size: 24px;
    font-weight: 700;
    color: #2C3136;
    margin-top: 5px;
}

.box .indicator {
    display: flex;
    align-items: center;
    margin-top: 10px;
}

.box .indicator i {
    color: green;
    font-size: 20px;
    margin-right: 5px;
}

.box .indicator i.down {
    color: red;
}

.box .indicator .text {
    font-size: 16px;
    color: #999;
}

.box i.cart {
    font-size: 50px;
    color: #AFA5D9;
}

.box i.cart.two {
    color: #FFBF00;
}

.box i.cart.three {
    color: #FF3E00;
}

.box i.cart.four {
    color: #FF0000;
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
                    <a href="HealthHome.jsp" class="nav_link active"> <i class='bx bx-grid-alt nav_icon'></i> 
                        <span class="nav_name">Dashboard</span> 
                    </a>                    
                    
                    <a href="ManagePrescription.jsp" class="nav_link"> <i class='bx bx-message-square-detail nav_icon'></i>
                        <span class="nav_name">Prescription</span> 
                    </a>
                                        
<!--                    <a href="#" class="nav_link"> <i class='bx bx-chart nav_icon'></i> 
                        <span class="nav_name">Report</span> 
                    </a> -->
                    
                    <a href="Account.jsp" class="nav_link"> <i class='bx bx-user nav_icon'></i>
                        <span class="nav_name">Account</span>
                    </a> 
                </div>
            </div> <a href="Logout" class="nav_link"> <i class='bx bx-log-out nav_icon'></i> <span class="nav_name">SignOut</span> </a>
        </nav>
    </div>
    <!--Container Main start-->
    <div class="height-100 bg-light">
        <h4>Welcome Back</h4>
        <div class="overview-boxes">
                    <div class="box">
                        <div class="right-side">
                            <div class="box-topic">Total Medicine</div>
                            
                            <div class="indicator">
                                <i class='bx bxs-detail'></i>
                                <span class="text">Show Details</span>
                            </div>
                        </div>
                        <i class='bx bx-cart-alt cart'></i>
                    </div>
                    <div class="box">
                        <div class="right-side">
                            <div class="box-topic">Expired Medicine</div>
                            
                            <div class="indicator">
                                <i class='bx bxs-detail'></i>
                                <span class="text">Show Details</span>
                            </div>
                        </div>
                        <i class='bx bxs-cart-add cart two'></i>
                    </div>
                    <div class="box">
                        <div class="right-side">
                            <div class="box-topic">Total Purchases</div>
                            <div class="number">${totalPurchases}</div>
                            <div class="indicator">
                                <i class='bx bxs-detail'></i>
                                <a href="Purchase.jsp" class="text">Show Details</a>
                            </div>
                        </div>
                        <i class='bx bx-cart cart three'></i>
                    </div>
                    <div class="box">
                        <div class="right-side">
                            <div class="box-topic">Total Supplier</div>
                            <div class="number">${totalSuppliers}</div>
                            <div class="indicator">
                                <i class='bx bxs-detail'></i>
                                <a href="SupplierList.jsp" class="text">Show Details</a>
                            </div>
                        </div>
                        <i class='bx bxs-cart-download cart four'></i>
                    </div>
                              
                            
                </div>
    </div>
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
    </script>
    </body>
</html>