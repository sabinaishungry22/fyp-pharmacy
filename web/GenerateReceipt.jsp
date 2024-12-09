<%@page import="java.math.BigDecimal"%>
<%@page import="com.Model.Supplier"%>
<%@page import="com.Model.Purchase"%>
<% 
    Purchase purchase = (Purchase) request.getAttribute("purchase");
    Supplier supplier = (Supplier) request.getAttribute("supplier");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Purchase Receipt</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
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

.addMedicine {
  position: fixed;
  right: 1rem;
  z-index: 100;
  background-color: var(--first-color-light);
  color: var(--white-color);
  padding: 0.5rem 1rem;
  border-radius: 0.5rem;
  font-family: var(--body-font);
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.bxs-capsule {
    color: var(--white-color);
}
/* Custom styles for the "Update" button */
.btn-warning,
.btn-warning:hover {
    background-color: #3498db; /* Blue color */
    color: #ffffff; /* White text color */
}

/* Custom styles for the "Delete" button */
.btn-danger,
.btn-danger:hover {
    background-color: #2ecc71; /* Green color */
    color: #ffffff; /* White text color */
}
        .receipt-container {
            margin: 50px auto;
            max-width: 800px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .receipt-header {
            text-align: center;
            margin-bottom: 20px;
        }
        .receipt-header img {
            max-width: 150px;
        }
        .receipt-header h2 {
            margin: 10px 0;
            font-size: 24px;
            font-weight: bold;
        }
        .receipt-header p {
            margin: 0;
        }
        .receipt-details {
            margin-bottom: 20px;
        }
        .receipt-details p {
            margin: 5px 0;
        }
        .receipt-table {
            width: 100%;
            margin-bottom: 20px;
        }
        .receipt-table th, .receipt-table td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ccc;
        }
        .receipt-table th {
            background-color: #f8f8f8;
            font-weight: bold;
        }
        .receipt-total {
            text-align: right;
        }
        .receipt-total p {
            margin: 5px 0;
            font-size: 16px;
            font-weight: bold;
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
    <div class="container receipt-container">
        <div class="receipt-header">
            <img src="https://www.creativefabrica.com/wp-content/uploads/2020/02/11/Medicine-Logo-Graphics-1-2.jpg" alt="Logo">
            <h2>Pharmacy</h2>
            <p>Admin Panel</p>
            <p>Batch ID: <%= purchase.getBatchId() %></p>
        </div>
        <div class="row">
            
            <div class="col-md-6 text-left">
                <h5>Billing To</h5>
                <% if (supplier != null) { %>
                    <p><%= supplier.getSupplierName() %></p>
                    <p><%= supplier.getAddress() %></p>
                    <p><%= supplier.getCity() %>, <%= supplier.getState() %> <%= supplier.getZip() %></p>
                    <p><%= supplier.getEmail() %></p>
                    <p>P: <%= supplier.getMobileNo() %></p>
                <% } else { %>
                    <p>Supplier details not available.</p>
                <% } %>
                <p>Date: <%= purchase.getExpiryDate() %></p>
            </div>
        </div>
        <table class="receipt-table">
            <thead>
                <tr>
                    <th>SL</th>
                    <th>Medicine Name</th>
                    <th>QTY(BOX)</th>
                    <th>PCS</th>
                    <th>Supplier Price</th>
                    
                    <th>Purchase Price</th>
                    <th>Per Pcs Price</th>
                    <th>Total Amount</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>Paracetamol</td>
                    <td><%= purchase.getBoxQty() %></td>
                    <td><%= purchase.getQuantity() %></td>
                    <td><%= purchase.getSupplierPrice() %></td>
                    <td><%= purchase.getSupplierPrice() %></td>
                    <td><%= purchase.getSupplierPrice().divide(new BigDecimal(purchase.getQuantity()), 2, BigDecimal.ROUND_HALF_UP) %></td>
                    <td><%= purchase.getTotalPurchasePrice() %></td>
                </tr>
            </tbody>
        </table>
        <div class="receipt-total">
            <p>Sub Total: <%= purchase.getTotalPurchasePrice() %></p>
            <p>Grand Total: <%= purchase.getTotalPurchasePrice() %></p>
            <p>Paid Amount: <%= purchase.getTotalPurchasePrice() %></p>
        </div>
        <div class="text-center">
            <button class="btn btn-primary" onclick="window.print()">Print Receipt</button>
        </div>
    </div>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.2.1/dist/jquery.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@iconify/iconify@v2.3.1/dist/iconify.min.js"></script>

            <script>
    document.addEventListener("DOMContentLoaded", function(event) {
        const showNavbar = (toggleId, navId, bodyId, headerId) => {
            const toggle = document.getElementById(toggleId),
                  nav = document.getElementById(navId),
                  bodypd = document.getElementById(bodyId),
                  headerpd = document.getElementById(headerId);

            // Validate that all variables exist
            if (toggle && nav && bodypd && headerpd) {
                toggle.addEventListener('click', () => {
                    // show navbar
                    nav.classList.toggle('show');
                    // change icon
                    toggle.classList.toggle('bx-x');
                    // add padding to body
                    bodypd.classList.toggle('body-pd');
                    // add padding to header
                    headerpd.classList.toggle('body-pd');
                    
                    // Save the state to local storage
                    const isActive = nav.classList.contains('show');
                    localStorage.setItem('navbar-toggle-state', isActive);
                });
            }
        }

        // Function to set the initial state from local storage
        const setInitialNavbarState = () => {
            const nav = document.getElementById('nav-bar'),
                  toggle = document.getElementById('header-toggle'),
                  bodypd = document.getElementById('body-pd'),
                  headerpd = document.getElementById('header');

            const isActive = localStorage.getItem('navbar-toggle-state') === 'true';

            if (isActive) {
                nav.classList.add('show');
                toggle.classList.add('bx-x');
                bodypd.classList.add('body-pd');
                headerpd.classList.add('body-pd');
            }
        }

        showNavbar('header-toggle', 'nav-bar', 'body-pd', 'header');
        setInitialNavbarState();

        /*===== LINK ACTIVE =====*/
        const linkColor = document.querySelectorAll('.nav_link');

        function colorLink() {
            if (linkColor) {
                linkColor.forEach(l => l.classList.remove('active'));
                this.classList.add('active');
            }
        }
        linkColor.forEach(l => l.addEventListener('click', colorLink));

        // Your code to run since DOM is loaded and ready
    });
</script>
</body>
</html>
