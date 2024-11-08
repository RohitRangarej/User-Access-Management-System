<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Request Access</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
            padding-top: 20px;
            display: flex;
            justify-content: center;
        }
        .container {
            max-width: 900px;
            width: 100%;
            padding: 20px 30px;
            background: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        h2 {
            margin-top: 0;
            color: #333;
        }
        form {
            margin-bottom: 30px;
        }
        label {
            display: block;
            margin: 10px 0 5px;
        }
        select, textarea, input[type="text"], input[type="submit"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .dropdown-container {
            position: relative;
            display: inline-block;
            width: 100%;
            margin-bottom: 15px;
        }
        .dropdown-button {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            cursor: pointer;
            background-color: #f9f9f9;
            text-align: left;
            border-radius: 4px;
        }
        .dropdown-menu {
            display: none;
            position: absolute;
            width: 100%;
            background-color: #fff;
            border: 1px solid #ccc;
            max-height: 150px;
            overflow-y: auto;
            z-index: 1;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
        }
        .dropdown-container:hover .dropdown-menu {
            display: block;
        }
        .dropdown-menu label {
            padding: 8px;
            display: block;
            margin-bottom: -15px;
            cursor: pointer;
        }
        .dropdown-menu label:hover {
            background-color: #f1f1f1;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ccc;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .message {
            padding: 10px;
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
            border-radius: 4px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Request Access to Software</h2>
        <form action="requestAccess" method="post" onsubmit="return validateForm()">
            <label for="softwareId">Software:</label>
            <select name="softwareId" id="softwareId" onchange="fetchAccessLevels()">
                <c:forEach var="software" items="${softwareList}">
                    <option value="${software.id}">${software.name}</option>
                </c:forEach>
            </select>

            <div class="dropdown-container">
                <div class="dropdown-button">Select Access Levels</div>
                <div class="dropdown-menu" id="accessLevelsContainer">
                    
                </div>
            </div>

            <label for="reason">Reason for Request:</label>
            <textarea name="reason" id="reason" rows="4" placeholder="Provide a reason for access..." required></textarea>

            <input type="submit" value="Submit Request">
        </form>

        <c:if test="${not empty successMessage}">
            <p class="message">${successMessage}</p>
        </c:if>

        <h2>Pending Requests</h2>
        <c:if test="${empty pendingRequests}">
            <p>No pending requests.</p>
        </c:if>
        <table>
            <thead>
                <tr>
                    <th>Software Name</th>
                    <th>Access Type</th>
                    <th>Reason</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="request" items="${pendingRequests}">
                    <tr>
                        <td>${request.software.name}</td>
                        <td>${request.accessType}</td>
                        <td>${request.reason}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <h2>Actioned Requests</h2>
        <c:if test="${empty actionedRequests}">
            <p>No actioned requests.</p>
        </c:if>
        <table>
            <thead>
                <tr>
                    <th>Software Name</th>
                    <th>Access Type</th>
                    <th>Reason</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="request" items="${actionedRequests}">
                    <tr>
                        <td>${request.software.name}</td>
                        <td>${request.accessType}</td>
                        <td>${request.reason}</td>
                        <td>${request.status}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script>
        function fetchAccessLevels() {
            var softwareId = document.getElementById("softwareId").value;
            var xhr = new XMLHttpRequest();
            xhr.open('GET', '/getAvailableAccessLevels?softwareId=' + softwareId, true);
            xhr.setRequestHeader('Content-Type', 'application/json');
            xhr.onload = function () {
                if (xhr.status === 200) {
                    var response = JSON.parse(xhr.responseText);
                    var accessLevels = response.accessLevels;
                    var container = document.getElementById('accessLevelsContainer');
                    container.innerHTML = '';
                    accessLevels.forEach(function(level) {
                        var label = document.createElement('label');
                        var checkbox = document.createElement('input');
                        checkbox.type = 'checkbox';
                        checkbox.name = 'accessLevels';
                        checkbox.value = level;
                        label.appendChild(checkbox);
                        label.appendChild(document.createTextNode(level));
                        container.appendChild(label);
                        container.appendChild(document.createElement('br'));
                    });
                }
            };
            xhr.send();
        }

        window.onload = function() {
            fetchAccessLevels();
        }
    </script>
</body>
</html>
