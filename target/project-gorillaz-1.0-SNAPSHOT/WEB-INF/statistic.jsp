<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="head.jsp" %>
<body style="overflow-y: auto; margin-bottom: 60px;"> <!-- Добавлена прокрутка и отступ для футера -->
<div class="container">
    <div class="left-part">
        <!-- Левая часть пустая -->
    </div>

    <!-- Общий заголовок "СТАТИСТИКА" -->
    <h1 class="rainbow-text">ЗАЛ СЛАВЫ</h1>

    <div class="top-part" style="display: flex; flex-direction: column; align-items: center;">
        <!-- Центрирование таблиц -->
        <!-- Первая таблица: Квест -->
        <h2 style="text-align: center; font-size: 1.5em; margin-bottom: 20px;">КВЕСТ</h2>
        <table style="width: 80%; border-collapse: collapse; border: 1px solid black; margin-bottom: 20px; text-align: center;">
            <thead>
            <tr>
                <th style="border: 1px solid black; padding: 10px;">Место</th>
                <th style="border: 1px solid black; padding: 10px;">Имя</th>
                <th style="border: 1px solid black; padding: 10px;">Число игр</th>
                <th style="border: 1px solid black; padding: 10px;">Число побед</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="questGamers" items="${sessionScope.questGamers}" varStatus="loop">
                <tr>
                    <td style="border: 1px solid black; padding: 10px;">${loop.index + 1}</td>
                    <td style="border: 1px solid black; padding: 10px; text-align: left;">${questGamers.login}</td>
                    <td style="border: 1px solid black; padding: 10px;">${questGamers.gamesCount}</td>
                    <td style="border: 1px solid black; padding: 10px;">${questGamers.winsCount}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <!-- Вторая таблица: Рулетка -->
        <h2 style="text-align: center; font-size: 1.5em; margin-bottom: 20px;">РУЛЕТКА</h2>
        <table style="width: 80%; border-collapse: collapse; border: 1px solid black; margin-bottom: 20px; text-align: center;">
            <thead>
            <tr>
                <th style="border: 1px solid black; padding: 10px;">Место</th>
                <th style="border: 1px solid black; padding: 10px;">Имя</th>
                <th style="border: 1px solid black; padding: 10px;">Число игр</th>
                <th style="border: 1px solid black; padding: 10px;">Число побед</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="rouletteGamers" items="${sessionScope.rouletteGamers}" varStatus="loop">
                <tr>
                    <td style="border: 1px solid black; padding: 10px;">${loop.index + 1}</td>
                    <td style="border: 1px solid black; padding: 10px; text-align: left;">${rouletteGamers.login}</td>
                    <td style="border: 1px solid black; padding: 10px;">${rouletteGamers.gamesCount}</td>
                    <td style="border: 1px solid black; padding: 10px;">${rouletteGamers.winsCount}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <!-- Третья таблица: Квиз -->
        <h2 style="text-align: center; font-size: 1.5em; margin-bottom: 20px;">КВИЗ</h2>
        <table style="width: 80%; border-collapse: collapse; border: 1px solid black; margin-bottom: 20px; text-align: center;">
            <thead>
            <tr>
                <th style="border: 1px solid black; padding: 10px;">Место</th>
                <th style="border: 1px solid black; padding: 10px;">Имя</th>
                <th style="border: 1px solid black; padding: 10px;">Число игр</th>
                <th style="border: 1px solid black; padding: 10px;">Число побед</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="quizGamers" items="${sessionScope.quizGamers}" varStatus="loop">
                <tr>
                    <td style="border: 1px solid black; padding: 10px;">${loop.index + 1}</td>
                    <td style="border: 1px solid black; padding: 10px; text-align: left;">${quizGamers.login}</td>
                    <td style="border: 1px solid black; padding: 10px;">${quizGamers.gamesCount}</td>
                    <td style="border: 1px solid black; padding: 10px;">${quizGamers.winsCount}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="bottom-part">
        <!-- Одна кнопка -->
        <div class="buttons" style="display: flex; justify-content: center; align-items: center; height: 100%;">
            <form action="start-page" method="post">
                <button class="btn">Вернуться в главное меню</button>
            </form>

        </div>
    </div>

</div>
</body>

<style>
    .left-part img {
        width: 100%;
        height: 100%;
        object-fit: contain; /* Картинка заполняет весь блок */
    }

    .right-part {
        flex: 1; /* Правая часть занимает половину ширины */
        display: flex;
        flex-direction: column; /* Разделение на верхнюю и нижнюю части */
        padding: 10px; /* Отступы внутри блока */
    }
    /* Основные стили */
    .resource span {
        font-size: 2em; /* Увеличенный текст в 2 раза */
    }

    body, html {
        margin: 0;
        padding: 0;
        height: 100%;
        font-family: "Orbitron", sans-serif; /* Шрифт */
        background-color: #333333; /* Задний фон */
        color: white; /* Белый текст */
    }

    .btn {
        padding: 10px 20px;
        text-align: center;
        background-color: black; /* Цвет кнопок */
        color: white; /* Белый текст на кнопках */
        border: none;
        cursor: pointer;
    }

    /* Анимация для заголовка "СТАТИСТИКА" */
    .rainbow-text {
        font-size: 2em;
        text-align: center;
        margin-bottom: 20px;
        background: linear-gradient(90deg, #00f7ff, rgb(255, 255, 255), #44ff00, #454d45, blue, indigo, violet);
        background-size: 200% 100%;
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
        animation: rainbow 5s linear infinite;
    }

    @keyframes rainbow {
        0% {
            background-position: 0% 50%;
        }
        100% {
            background-position: 100% 50%;
        }
    }
</style>

<%@include file="footer.jsp" %>