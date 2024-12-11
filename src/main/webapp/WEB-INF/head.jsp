<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Выберите игру</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styles.css"> <!-- Подключение CSS-файла -->
    <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@400;500;700&display=swap" rel="stylesheet">
</head>
<body>

<!-- Верхняя часть -->
<c:if test="${2==1}">
    <header class="header">
        <div class="left-part">
            <img src="path/to/image.jpg" alt="Header Image" class="header-image">
        </div>
        <div class="right-part">
            <div class="auth">
                <button class="btn">Регистрация</button>
                <button class="btn enter-btn">Войти</button>
                <input type="text" placeholder="Логин" class="form-control">
                <input type="password" placeholder="Пароль" class="form-control">
            </div>
        </div>
    </header>
</c:if>
<c:if test="${2==2}">
    <header class="header">
        <div class="left-part">
            <p class="stat">Игр: <span>0</span></p>
            <p class="stat">Побед: <span>0</span></p>
            <p class="stat">Поражений: <span>0</span></p>
        </div>
        <div class="right-part">
            <p class="login"> Hello World!</p>
        </div>
        </div>
    </header>
</c:if>