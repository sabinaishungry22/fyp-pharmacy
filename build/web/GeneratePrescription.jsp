<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Generate Prescription</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        .prescription-container {
            margin: 50px auto;
            max-width: 800px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .prescription-header {
            text-align: center;
            margin-bottom: 20px;
        }
        .prescription-header img {
            max-width: 150px;
        }
        .prescription-header h2 {
            margin: 10px 0;
            font-size: 24px;
            font-weight: bold;
        }
        .prescription-header p {
            margin: 0;
        }
        .prescription-details {
            margin-bottom: 20px;
        }
        .prescription-details p {
            margin: 5px 0;
        }
        .prescription-table {
            width: 100%;
            margin-bottom: 20px;
        }
        .prescription-table th, .prescription-table td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ccc;
        }
        .prescription-table th {
            background-color: #f8f8f8;
            font-weight: bold;
        }
        .table tbody tr:nth-child(odd) {
    background-color: #f2f2f2; /* Light grey color for odd rows */
}

.table tbody tr:nth-child(even) {
    background-color: #ffffff; /* White color for even rows */
}
    </style>
</head>
<body>
<div class="container prescription-container">
    <div class="prescription-header">
        <img src="https://www.creativefabrica.com/wp-content/uploads/2020/02/11/Medicine-Logo-Graphics-1-2.jpg" alt="Logo">
        <h2>Pharmacy</h2>
        <p>Prescription</p>
    </div>
    <div class="row">
        <div class="col-md-6">
            <h5>Patient Information</h5>
            <p>Name: <%= request.getAttribute("patientName") %></p>
            <p>Date: <%= request.getAttribute("prescriptionDate") %></p>
        </div>
        <div class="col-md-6 text-right">
            <h5>Doctor Information</h5>
            <p>Name: <%= request.getAttribute("doctorName") %></p>
        </div>
    </div>
    <table class="prescription-table">
        <thead>
        <tr>
            <th>Medicine Name</th>
            <th>Dosage</th>
            <th>Quantity</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><%= request.getAttribute("medicineName") %></td>
            <td><%= request.getAttribute("dosage") %></td>
            <td><%= request.getAttribute("quantity") %></td>
        </tr>
        </tbody>
    </table>
    <div>
        <h5>Notes</h5>
        <p><%= request.getAttribute("notes") %></p>
    </div>
    <div class="text-center">
        <button class="btn btn-primary" onclick="window.print()">Print Prescription</button>
        <a href="PrescriptionController?action=list" class="btn btn-secondary">Back to Prescriptions</a>
    </div>
</div>
</body>
</html>
