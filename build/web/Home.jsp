<%-- 
    Document   : Start
    Created on : 11 Jan 2024, 1:29:40 am
    Author     : USER
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page import="java.util.List" %>
<%@ page import="com.DAO.MedicineDao" %>
<%@ page import="com.Model.Medicine" %>
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
            --nav-width: 250px; /* Updated width */
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
            padding: 0;
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
    left: -30%; /* Initial position */
    width: var(--nav-width); /* Set the width of the sidebar */
    min-width: 40px; /* Set the minimum width of the sidebar when minimized */
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

        .report-container {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .box {
            margin: 10px;
            padding: 20px;
            border: 1px solid #ccc;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        h1, h2 {
            text-align: center;
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
                <a href="Home.jsp" class="nav_link active"> <i class='bx bx-grid-alt nav_icon'></i>
                    <span class="nav_name">Dashboard</span>
                </a>

                <a href="ManageMedicine.jsp" class="nav_link"> <i class='bx bx-message-square-detail nav_icon'></i>
                    <span class="nav_name">Medicine</span>
                </a>

                <a href="ManageStock.jsp" class="nav_link"> <i class='bx bx-folder nav_icon'></i>
                    <span class="nav_name">Stock</span>
                </a>

<!--                <a href="#" class="nav_link"> <i class='bx bx-chart nav_icon'></i>
                    <span class="nav_name">Report</span>
                </a>-->

                <a href="Account.jsp" class="nav_link"> <i class='bx bx-user nav_icon'></i>
                    <span class="nav_name">Account</span>
                </a>
            </div>
        </div>
        <a href="Logout" class="nav_link"> <i class='bx bx-log-out nav_icon'></i> <span class="nav_name">SignOut</span> </a>
    </nav>
</div>
<!--Container Main start-->
<div class="height-100 bg-light">
    <h4>Welcome Back</h4>
    <%
            int medicineCount = 0;
            int purchaseCount = 0;
            int supplierCount = 0;
            int stockCount = 0;

            try {
                // Load the MySQL driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Create a connection to the database
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "admin");

                // Create a statement
                Statement stmt = con.createStatement();

                // Execute the query to count suppliers
                ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM medicines");
                if (rs.next()) {
                    medicineCount = rs.getInt(1);
                }

                rs = stmt.executeQuery("SELECT COUNT(*) FROM supplier");
                if (rs.next()) {
                    supplierCount = rs.getInt(1);
                }
                
                // Execute the query to count purchases
                rs = stmt.executeQuery("SELECT COUNT(*) FROM purchase");
                if (rs.next()) {
                    purchaseCount = rs.getInt(1);
                }

                rs = stmt.executeQuery("SELECT COUNT(*) FROM stocks");
                if (rs.next()) {
                    stockCount = rs.getInt(1);
                }
                // Close the result set, statement, and connection
                rs.close();
                stmt.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
    <div class="overview-boxes">
                    <div class="box">
                        <div class="right-side">
                            <div class="box-topic">Total Medicine</div>
                            <div class="number"><%= medicineCount %></div>
                            <div class="indicator">
                                <i class='bx bxs-detail'></i>
                                <a href="ManageMedicine.jsp" class="text">Show Details</a>
                            </div>
                        </div>
                        <i class='bx bx-cart-alt cart'></i>
                    </div>
                    <div class="box">
                        <div class="right-side">
                            <div class="box-topic">Stocks</div>
                            <div class="number"><%= stockCount %></div>
                            <div class="indicator">
                                <i class='bx bxs-detail'></i>
                                <a href="ManageStock.jsp" class="text">Show Details</a>
                            </div>
                        </div>
                        <i class='bx bxs-cart-add cart two'></i>
                    </div>
                    <div class="box">
                        <div class="right-side">
                            <div class="box-topic">Total Purchases</div>
                            <div class="number"><%= purchaseCount %></div>
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
                            <div class="number"><%= supplierCount %></div>
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