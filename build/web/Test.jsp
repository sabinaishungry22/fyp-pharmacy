<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        .chart-container {
            width: 50%;
            margin: auto;
        }
    </style>
</head>
<body>
    <%
        int supplierCount = 0;
        int purchaseCount = 0;
        
        try {
            // Load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Create a connection to the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy", "root", "admin");
            
            // Create a statement
            Statement stmt = con.createStatement();
            
            // Execute the query to count suppliers
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM supplier");
            if (rs.next()) {
                supplierCount = rs.getInt(1);
            }
            
            // Execute the query to count purchases
            rs = stmt.executeQuery("SELECT COUNT(*) FROM purchase");
            if (rs.next()) {
                purchaseCount = rs.getInt(1);
            }
            
            // Close the result set, statement, and connection
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>

    <div class="chart-container">
        <canvas id="supplierChart"></canvas>
    </div>
    <div class="chart-container">
        <canvas id="purchaseChart"></canvas>
    </div>

    <script>
        const supplierCount = <%= supplierCount %>;
        const purchaseCount = <%= purchaseCount %>;

        console.log("Supplier Count:", supplierCount);
        console.log("Purchase Count:", purchaseCount);

        const supplierCtx = document.getElementById('supplierChart').getContext('2d');
        const supplierChart = new Chart(supplierCtx, {
            type: 'bar',
            data: {
                labels: ['Suppliers'],
                datasets: [{
                    label: '# of Suppliers',
                    data: [supplierCount],
                    backgroundColor: ['rgba(54, 162, 235, 0.2)'],
                    borderColor: ['rgba(54, 162, 235, 1)'],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });

        const purchaseCtx = document.getElementById('purchaseChart').getContext('2d');
        const purchaseChart = new Chart(purchaseCtx, {
            type: 'bar',
            data: {
                labels: ['Purchases'],
                datasets: [{
                    label: '# of Purchases',
                    data: [purchaseCount],
                    backgroundColor: ['rgba(255, 99, 132, 0.2)'],
                    borderColor: ['rgba(255, 99, 132, 1)'],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    </script>
</body>
</html>
