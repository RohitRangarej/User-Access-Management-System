<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Access Approval</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
            display: flex;
            justify-content: center;
            padding-top: 40px;
        }
        .container {
            width: 80%;
            max-width: 900px;
            background: #fff;
            padding: 20px 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h2 {
            margin-bottom: 20px;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        table th, table td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ccc;
        }
        table th {
            background-color: #007bff;
            color: #fff;
        }
        table tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .form-inline {
            display: flex;
            gap: 10px;
        }
        .form-inline input[type="submit"] {
            padding: 8px 12px;
            background-color: #28a745;
            border: none;
            border-radius: 4px;
            color: white;
            cursor: pointer;
        }
        .form-inline input[type="submit"]:last-child {
            background-color: #dc3545;
        }
        .form-inline input[type="submit"]:hover {
            opacity: 0.9;
        }
        .message {
            padding: 10px;
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
            border-radius: 4px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Pending Access Requests</h2>
        <table>
            <thead>
                <tr>
                    <th>Employee Name</th>
                    <th>Software Name</th>
                    <th>Access Type</th>
                    <th>Reason for Request</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="request" items="${pendingRequests}">
                    <tr>
                        <td>${request.user.username}</td>
                        <td>${request.software.name}</td>
                        <td>${request.accessType}</td>
                        <td>${request.reason}</td>
                        <td>
                            <form action="approveRequest" method="post" class="form-inline">
                                <input type="hidden" name="requestId" value="${request.id}" />
                                <input type="submit" name="action" value="Approve" />
                                <input type="submit" name="action" value="Reject" />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <c:if test="${not empty actionMessage}">
            <p class="message">${actionMessage}</p>
        </c:if>
    </div>
</body>
</html>
