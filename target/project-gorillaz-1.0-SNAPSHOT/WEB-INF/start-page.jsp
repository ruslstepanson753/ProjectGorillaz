<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp" %>

<!-- Средняя часть -->
<main class="main">
    <!-- Бегущая строка -->
    <div class="marquee">
        <h1>ВЫБЕРИТЕ ИГРУ</h1>
    </div>

    <div class="games">
        <div class="game" onclick="window.location.href='#';">
            <img src="${sessionScope.IMG_START_QUEST}" alt="Игра 1">

            <form action="game-quest" method="post">
                <button class="btn" style="white-space: pre-wrap;">КВЕСТ</button>
            </form>
        </div>
        <div class="game" onclick="window.location.href='#';">
            <img src="${sessionScope.IMG_START_ROULETTE}" alt="Игра 2">

            <form action="game-roulette" method="post">
                <button class="btn" style="white-space: pre-wrap;">РУЛЕТКА</button>
            </form>
        </div>
        <div class="game" onclick="window.location.href='#';">
            <img src="${sessionScope.IMG_START_QUIZ}" alt="Игра 3">

            <form action="game-quiz" method="post">
                <button class="btn" style="white-space: pre-wrap;">КВИЗ</button>
            </form>
        </div>
    </div>

    <div class="central-part">
        <form action="statistic" method="post">
            <button class="btn" style="white-space: pre-wrap;">ЗАЛ СЛАВЫ</button>
        </form>
    </div>
</main>

<%@include file="footer.jsp" %>
<style>
    /* Основные стили */
    .text-container2 {
        display: flex;
        flex-direction: column;
        align-items: center; /* Выравнивание по центру */
        justify-content: center; /* Выравнивание по центру */
        text-align: center; /* Выравнивание текста по центру */
    }

    .text-container2 p {
        margin: 0; /* Убираем стандартные отступы */
        margin-bottom: 10px; /* Добавляем отступ между строками */
        white-space: pre-wrap; /* Сохраняем переносы строк */
    }

    .text-container2 p:last-child {
        margin-bottom: 0; /* Убираем отступ у последней строки */
    }

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
        object-fit: contain; /* Картинка заполняет весь блок */
    }

    .right-part {
        flex: 1; /* Правая часть занимает половину ширины */
        display: flex;
        flex-direction: column; /* Разделение на верхнюю и нижнюю части */
        padding: 10px; /* Отступы внутри блока */
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
        width: 100px;
        height: 100px;
        margin-right: 20px; /* Отступ между иконкой и текстом */
    }
</style>
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

    /* Блок для бегущей строки */
    .marquee {
        width: 100%;
        overflow: hidden;
        white-space: nowrap;
        background-color: #333333; /* Чёрный фон для контраста */
        padding: 10px 0; /* Отступы сверху и снизу */
    }

    /* Стили текста бегущей строки */
    .marquee h1 {
        display: inline-block;
        font-size: 2em; /* Размер текста */
        color: white; /* Белый цвет текста */
        animation: marquee 10s linear infinite; /* Анимация текста */
        text-transform: uppercase; /* Преобразование текста в верхний регистр */
    }

    /* Ключевые кадры для анимации */
    @keyframes marquee {
        0% {
            transform: translateX(100%); /* Начальная позиция: за пределами экрана справа */
        }
        100% {
            transform: translateX(-100%); /* Конечная позиция: за пределами экрана слева */
        }
    }

    /* Стили для кнопок */
    .btn {
        padding: 10px 20px;
        text-align: center;
        background-color: black; /* Чёрный фон кнопок */
        color: white; /* Белый текст на кнопках */
        border: none;
        cursor: pointer;
        font-family: "Orbitron", sans-serif; /* Шрифт */
        font-size: 1em; /* Размер текста */
        text-transform: uppercase; /* Преобразование текста в верхний регистр */
        margin: 10px; /* Отступы между кнопками */
    }

    /* Стили для контейнера с играми */
    .games {
        display: flex;
        justify-content: space-around;
        align-items: center;
        margin-top: 20px;
    }

    /* Стили для каждой игры */
    .game {
        text-align: center;
    }

    /* Стили для центральной части */
    .central-part {
        text-align: center;
        margin-top: 20px;
    }
</style>