<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - User Access Management System</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            background: linear-gradient(135deg, #ff9a9e, #fad0c4);
            color: #333;
            text-align: center;
        }
        .container {
            max-width: 600px;
            background-image: linear-gradient(to top, #fbc2eb 0%, #a6c1ee 100%);
            padding: 20px;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        h1 {
            font-size: 2rem;
            margin-bottom: 1rem;
        }
        p {
            margin-bottom: 20px;
        }
        .button-container {
            margin-top: 20px;
        }
        .button-container a {
            display: inline-block;
            padding: 10px 20px;
            margin: 0 10px;
            font-size: 16px;
            text-decoration: none;
            border-radius: 5px;
            color: #fff;
            background-color: #ff6f61;
            transition: background-color 0.3s;
        }
        .button-container a:hover {
            background-color: #ff4a34;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to the Access Management System</h1>
        <p>Manage your software access requests and approvals with ease. Log in or sign up to get started.</p>
        <div class="button-container">
            <a href="/login">Login</a>
            <a href="/signup">Sign Up</a>
        </div>
    </div>
</body>
</html>
