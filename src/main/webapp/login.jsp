<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
           background-color:#ffa280;
            background-size: cover;
            background-position: center;
            height: 100vh;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            position: relative;
        }

        /* Overlay to reduce the opacity of the background image */
        body::before {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
             /* Adjust the opacity as needed */
            z-index: 1;
        }

        .login-container {
            position: relative;
            z-index: 2;
            
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.3);
            width: 350px;
            text-align: center;
        }

       

        .input-group {
            margin-bottom: 15px;
            text-align: left;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: white;
        }

        input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 14px;
        }

        .buttons {
            display: flex;
            justify-content: center; /* Center align the button */
            align-items: center;
        }

        button {
            padding: 8px 15px;
            background-color: #FF4500;
            border: none;
            border-radius: 4px;
            color: white;
            font-size: 16px;
            cursor: pointer;
            width: 100%; /* Full width button */
        }

        button:hover {
            background-color: #e03d00;
        }

        .signup-link {
            margin-top: 20px;
            display: block;
            font-size: 14px;
            color: #17153B;
            text-decoration: none;
        }

        .signup-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="login-container">
        
        <form action="login" method="POST">
            <div class="input-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="input-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="buttons">
                <button type="submit">Login</button>
            </div>
        </form>
        <a class="signup-link" href="signup.jsp"><font color="white">Don't have an account? Sign up here</font></a>
    </div>
</body>
</html>
