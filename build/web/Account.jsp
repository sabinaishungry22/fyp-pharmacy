<%-- 
    Document   : Account
    Created on : 21 Jan 2024, 11:58:24 pm
    Author     : USER
--%>

<%@ page import="java.util.List" %>
<%@ page import="com.DAO.UserInfo" %>
<%@ page import="com.Model.User" %>
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

.profile-box {
    position: fixed;
  right: 1rem;
  z-index: 100;
  background-color: grey;
  color: var(--white-color);
  padding: 0.5rem 1rem;
  border-radius: 0.5rem;
  font-family: var(--body-font);
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.profileup {
    text-decoration: none; /* Remove underlines from the anchor tag */
}

.profileup button {
    background-color: transparent;
    border: none;
    cursor: pointer;
    color: white;
    font-weight: bold;
    display: flex;
    align-items: center;
}

.profileup i {
    font-size: 24px;
    margin-right: 5px;
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
<!--                    <a href="Home.jsp" class="nav_link "> <i class='bx bx-grid-alt nav_icon'></i> 
                        <span class="nav_name">Dashboard</span> 
                    </a> 
                    
                    <a href="ManageMedicine.jsp" class="nav_link"> <i class='bx bx-message-square-detail nav_icon'></i>
                        <span class="nav_name">Medicine</span> 
                    </a>
                    
                    <a href="#" class="nav_link"> <i class='bx bx-message-square-detail nav_icon'></i>
                        <span class="nav_name">Supplier</span> 
                    </a>
                    
                    <a href="#" class="nav_link"> <i class='bx bx-folder nav_icon'></i>
                        <span class="nav_name">Stock</span> 
                    </a> -->
                    
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
        <h2>Account Profile</h2>
        <button onclick="goBack()" class="btn btn-primary">Go Back</button>
        
        <%
    // Retrieve the user object from the session
    User user = (User) session.getAttribute("user");

    // Check if the user object exists in the session
    if (user != null) {
%>

<form action="UpdateUserController" method="post" class="mb-3">
    <input type="hidden" name="id" value="<%= user.getId() %>">
    
    <div class="mb-3">
        <label for="username" class="form-label">Username</label>
        <input type="text" class="form-control" id="username" name="username" value="<%= user.getUsername() %>" readonly >
    </div>
    
    <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <div class="input-group">
            <input type="password" id="MyPassword" name="password" value="<%= user.getPassword() %>" class="form-control" readonly>
            <span class="input-group-text">
                <i class='bx bx-hide'></i>
            </span>
        </div>
    </div>
    
    <div class="mb-3">
        <label for="email" class="form-label">Email</label>
        <input type="text" class="form-control" id="email" name="email" value="<%= user.getEmail() %>" readonly>
    </div>
    
    <div class="mb-3">
        <label for="phone" class="form-label">Phone</label>
        <input type="text" class="form-control" id="phone" name="phone" value="<%= user.getPhone() %>" readonly>
    </div>
    
    <div class="mb-3">
        <label for="role" class="form-label">Role</label>
        <input type="text" class="form-control" id="role" name="role" value="<%= user.getRole() %>" readonly>
    </div>
    
    <div class="profile-box">
    <a href="UpdateUser.jsp" class="profileup">
        <button type="button">
            <span>Update Profile?</span>
        </button>
    </a>        
</div>


</form>



<%
    } else {
        out.println("User not found in session.");
    }
%>
    </div>


    <!--Container Main end-->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.2.1/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@iconify/iconify@v2.3.1/dist/iconify.min.js"></script>

    <script>
        
    function goBack() {
        const userRole = '<%= ((User) session.getAttribute("user")).getRole() %>';
        
        switch(userRole) {
            case 'Pharmacist':
                window.location.href = 'Home.jsp';
                break;
            case 'Admin':
                window.location.href = 'AdminHome.jsp';
                break;
            case 'Doctor':
                window.location.href = 'HealthHome.jsp';
                break;
            default:
                window.history.back();
                break;
        }
    }

            document.addEventListener("DOMContentLoaded", function (event) {

                const showNavbar = (toggleId, navId, bodyId, headerId) => {
                    const toggle = document.getElementById(toggleId),
                          nav = document.getElementById(navId),
                          bodypd = document.getElementById(bodyId),
                          headerpd = document.getElementById(headerId)

                    // Validate that all variables exist
                    if (toggle && nav && bodypd && headerpd) {
                        toggle.addEventListener('click', () => {
                            // Toggle the 'show' class on the navigation bar
                            nav.classList.toggle('show');
                            // Toggle the 'bx-x' class on the toggle icon
                            toggle.classList.toggle('bx-x');
                            // Toggle the 'body-pd' class on the body
                            bodypd.classList.toggle('body-pd');
                            // Toggle the 'body-pd' class on the header
                            headerpd.classList.toggle('body-pd');
                        })
                    }
                }

                showNavbar('header-toggle', 'nav-bar', 'body-pd', 'header');

                const linkColor = document.querySelectorAll('.nav_link')

                function colorLink() {
                    if (linkColor) {
                        linkColor.forEach(l => l.classList.remove('active'))
                        this.classList.add('active')
                    }
                }

                linkColor.forEach(l => l.addEventListener('click', colorLink))

                // Add alert for delete confirmation
                const deleteLinks = document.querySelectorAll('.btn-danger');

                function confirmDelete(event) {
                    event.preventDefault();
                    const confirmation = confirm("Are you sure you want to delete this item?");
                    if (confirmation) {
                        window.location.href = event.target.href; // Proceed with the delete link
                    }
                }

                deleteLinks.forEach(link => link.addEventListener('click', confirmDelete));

                // Highlight active link based on the current URL
                const currentURL = window.location.href;

                linkColor.forEach(link => {
                    if (link.href === currentURL) {
                        link.classList.add('active');
                    }
                });

            });
</script>

    </body>
</html>