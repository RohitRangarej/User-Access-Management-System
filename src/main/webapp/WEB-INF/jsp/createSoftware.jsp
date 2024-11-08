<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Software Creation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .form-container {
            background: #fff;
            padding: 20px 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }
        .form-container h2 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #333;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #555;
        }
        .form-group input[type="text"],
        .form-group textarea {
            width: 100%;
            padding: 8px;
            margin-top: 4px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .form-group textarea {
            resize: vertical;
            min-height: 80px;
        }
        .form-group input[type="checkbox"] {
            margin-right: 5px;
        }
        .form-group .access-label {
            margin-right: 10px;
            color: #555;
        }
        .form-group .checkbox-group {
            margin-top: 5px;
        }
        .btn-submit {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        .btn-submit:hover {
            background-color: #45a049;
        }
        .message {
            margin-top: 15px;
            padding: 10px;
            border-radius: 4px;
        }
        .message.success {
            background-color: #dff0d8;
            color: #3c763d;
        }
        .message.error {
            background-color: #f2dede;
            color: #a94442;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Create New Software</h2>
        <form action="createSoftware" method="post">
            <div class="form-group">
                <label for="name">Software Name:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description"></textarea>
            </div>
            <div class="form-group">
                <label>Access Levels:</label>
                <div class="checkbox-group">
                    <input type="checkbox" name="accessLevels" value="Read" id="read">
                    <label for="read" class="access-label">Read</label>
                    <input type="checkbox" name="accessLevels" value="Write" id="write">
                    <label for="write" class="access-label">Write</label>
                    <input type="checkbox" name="accessLevels" value="Admin" id="admin">
                    <label for="admin" class="access-label">Admin</label>
                </div>
            </div>
            <input type="submit" value="Create Software" class="btn-submit">
        </form>
        <c:if test="${not empty successMessage}">
            <p class="message success">${successMessage}</p>
        </c:if>
    </div>
</body>
</html>