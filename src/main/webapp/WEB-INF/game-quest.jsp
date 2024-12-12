<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp"%>


<div class="container">
    <!-- Левая часть: картинка -->
    <div class="left-part">
        <img src="path/to/image.jpg" alt="Картинка">
    </div>

    <!-- Правая часть: блок для дальнейшей верстки -->
    <div class="right-part">
        <!-- Верхняя часть: текст -->
        <div class="top-part">
            <p>${fullText}</p>
        </div>

        <!-- Нижняя часть: кнопки и ресурсы -->
        <div class="bottom-part">
            <!-- Кнопки -->
            <div class="buttons">
                <!-- Левая кнопка -->
                <button class="btn">${buttonLeft}</button>

                <!-- Правая кнопка -->
                <button class="btn">${buttonRight}</button>
            </div>

            <!-- Ресурсы -->
            <div class="resources">
                <!-- Время -->
                <div class="resource">
                    <img src="path/to/time-icon.png" alt="Время">
                    <span>${time}</span>
                </div>

                <!-- Деньги -->
                <div class="resource">
                    <img src="path/to/money-icon.png" alt="Деньги">
                    <span>${evidence}</span>
                </div>

                <!-- Улики -->
                <div class="resource">
                    <img src="path/to/clue-icon.png" alt="Улики">
                    <span>${gold}</span>
                </div>
            </div>
        </div>
    </div>
</div>



<style>
    /* Основные стили */
    body, html {
        margin: 0;
        padding: 0;
        height: 100%;
        font-family: "Orbitron", sans-serif; /* Шрифт */
        background-color: #333333; /* Задний фон */
        color: white; /* Белый текст */
    }

    .container {
        display: flex;
        height: 100vh; /* Контейнер занимает всю высоту экрана */
    }

    .left-part {
        flex: 1; /* Левая часть занимает половину ширины */
    }

    .left-part img {
        width: 100%;
        height: 100%;
        object-fit: cover; /* Картинка заполняет весь блок */
    }

    .right-part {
        flex: 1; /* Правая часть занимает половину ширины */
        display: flex;
        flex-direction: column; /* Разделение на верхнюю и нижнюю части */
        padding: 20px; /* Отступы внутри блока */
    }

    .top-part {
        flex: 1; /* Верхняя часть занимает половину высоты правой части */
        display: block; /* Строки будут идти одна под другой */
    }

    .bottom-part {
        flex: 1; /* Нижняя часть занимает половину высоты правой части */
        display: flex;
        flex-direction: column; /* Разделение на кнопки и ресурсы */
        margin-top: 20px; /* Отступ между верхней и нижней частями */
    }

    .buttons {
        display: flex;
        justify-content: space-between; /* Растягиваем кнопки по горизонтали */
    }

    .btn {
        flex: 1; /* Кнопки растягиваются по горизонтали */
        margin: 0 10px; /* Отступы между кнопками */
        padding: 10px;
        text-align: center;
        background-color: black; /* Цвет кнопок */
        color: white; /* Белый текст на кнопках */
        border: none;
        cursor: pointer;
    }

    .resources {
        display: flex;
        justify-content: space-between; /* Расположение ресурсов по горизонтали */
        margin-top: 20px; /* Отступ между кнопками и ресурсами */
    }

    .resource {
        display: flex;
        align-items: center; /* Выравнивание иконки и текста по вертикали */
    }

    .resource img {
        width: 24px;
        height: 24px;
        margin-right: 10px; /* Отступ между иконкой и текстом */
    }
</style>
<%@include file="footer.jsp"%>