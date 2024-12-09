<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Doctor Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f2f5;
            margin: 0;
            padding: 0;
        }
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 20px;
        }
        .dashboard {
            display: grid;
            grid-template-columns: 1fr 2fr;
            grid-template-rows: repeat(2, auto);
            gap: 20px;
            width: 80%;
        }
        .welcome, .chart, .card, .appointments, .profile, .file {
            background-color: white;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .chart canvas {
            max-width: 100%;
        }
        .card {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .card i {
            font-size: 2em;
            color: #007bff;
        }
        .appointments, .profile, .file {
            grid-column: span 2;
        }
        .appointments ul, .profile ul, .file ul {
            list-style: none;
            padding: 0;
        }
        .appointments ul li, .profile ul li, .file ul li {
            display: flex;
            justify-content: space-between;
            padding: 10px 0;
            border-bottom: 1px solid #ddd;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Doctor Dashboard</h1>
    <div class="dashboard">
        <div class="welcome card">
            <div>
                <h2>Welcome James!</h2>
                <p>Let's help patients to live a healthier and happier life</p>
            </div>
            <i class="fas fa-user-md"></i>
        </div>
        <div class="chart card">
            <h3>Doctor Salary</h3>
            <canvas id="salaryChart"></canvas>
        </div>
        <div class="chart card">
            <h3>Duty Hour</h3>
            <canvas id="dutyHourChart"></canvas>
        </div>
        <div class="chart card">
            <h3>Patient Gender</h3>
            <canvas id="genderChart"></canvas>
        </div>
        <div class="appointments card">
            <h3>Upcoming Appointments</h3>
            <ul>
                <li><span>Linda Brown</span><span>08:00 AM</span></li>
                <li><span>Nelly Dean</span><span>09:00 AM</span></li>
                <li><span>John Doe</span><span>10:00 AM</span></li>
                <li><span>James Vane</span><span>10:45 AM</span></li>
                <li><span>Mary Smith</span><span>11:00 AM</span></li>
            </ul>
        </div>
        <div class="profile card">
            <h3>Profile</h3>
            <ul>
                <li><strong>Dr. James Smith</strong></li>
                <li><span>4.5 Rating</span></li>
                <li><span>115 Patients</span></li>
            </ul>
        </div>
        <div class="file card">
            <h3>Patient Files</h3>
            <ul>
                <li><span>Linda Pres***.pdf</span><i class="fas fa-file-pdf"></i></li>
                <li><span>John Checkup.pdf</span><i class="fas fa-file-pdf"></i></li>
                <li><span>Nelly X-ray result.pdf</span><i class="fas fa-file-pdf"></i></li>
            </ul>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', (event) => {
        var ctx1 = document.getElementById('salaryChart').getContext('2d');
        var salaryChart = new Chart(ctx1, {
            type: 'line',
            data: {
                labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                datasets: [{
                    label: 'This Year',
                    data: [300, 400, 350, 500, 450, 550, 600, 650, 700, 750, 800, 850],
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 2,
                    fill: false
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

        var ctx2 = document.getElementById('dutyHourChart').getContext('2d');
        var dutyHourChart = new Chart(ctx2, {
            type: 'bar',
            data: {
                labels: ['Sat', 'Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri'],
                datasets: [{
                    label: 'Duty Hours',
                    data: [7, 9, 8, 9, 8, 7, 6],
                    backgroundColor: 'rgba(153, 102, 255, 0.2)',
                    borderColor: 'rgba(153, 102, 255, 1)',
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

        var ctx3 = document.getElementById('genderChart').getContext('2d');
        var genderChart = new Chart(ctx3, {
            type: 'doughnut',
            data: {
                labels: ['Male', 'Female'],
                datasets: [{
                    label: 'Gender',
                    data: [4000, 1000],
                    backgroundColor: [
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 99, 132, 0.2)'
                    ],
                    borderColor: [
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 99, 132, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false
            }
        });
    });
</script>

</body>
</html>
