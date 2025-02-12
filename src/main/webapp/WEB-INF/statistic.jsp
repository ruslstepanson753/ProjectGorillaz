<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp" %>
<body style="overflow-y: auto; margin-bottom: 60px;"> <!-- Добавлена прокрутка и отступ для футера -->
<div class="container">
    <div class="left-part">
        <!-- Левая часть пустая -->
    </div>
    <div class="right-part">
        <!-- Общий заголовок "СТАТИСТИКА" -->
        <h1 style="text-align: center; font-size: 3em; margin-bottom: 20px;">СТАТИСТИКА</h1>

        <div class="top-part" style="display: flex; flex-direction: column; align-items: center;"> <!-- Центрирование таблиц -->
            <!-- Первая таблица: Квест -->
            <h2>Квест</h2>
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
                <tr>
                    <td style="border: 1px solid black; padding: 10px;">1</td>
                    <td style="border: 1px solid black; padding: 10px; text-align: left;">Игрок 1</td>
                    <td style="border: 1px solid black; padding: 10px;">10</td>
                    <td style="border: 1px solid black; padding: 10px;">8</td>
                </tr>
                <tr>
                    <td style="border: 1px solid black; padding: 10px;">2</td>
                    <td style="border: 1px solid black; padding: 10px; text-align: left;">Игрок 2</td>
                    <td style="border: 1px solid black; padding: 10px;">9</td>
                    <td style="border: 1px solid black; padding: 10px;">7</td>
                </tr>
                <tr>
                    <td style="border: 1px solid black; padding: 10px;">3</td>
                    <td style="border: 1px solid black; padding: 10px; text-align: left;">Игрок 3</td>
                    <td style="border: 1px solid black; padding: 10px;">8</td>
                    <td style="border: 1px solid black; padding: 10px;">6</td>
                </tr>
                <tr>
                    <td style="border: 1px solid black; padding: 10px;">4</td>
                    <td style="border: 1px solid black; padding: 10px; text-align: left;">Игрок 4</td>
                    <td style="border: 1px solid black; padding: 10px;">7</td>
                    <td style="border: 1px solid black; padding: 10px;">5</td>
                </tr>
                <tr>
                    <td style="border: 1px solid black; padding: 10px;">5</td>
                    <td style="border: 1px solid black; padding: 10px; text-align: left;">Игрок 5</td>
                    <td style="border: 1px solid black; padding: 10px;">6</td>
                    <td style="border: 1px solid black; padding: 10px;">4</td>
                </tr>
                </tbody>
            </table>

            <!-- Вторая таблица: Рулетка -->
            <h2>Рулетка</h2>
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
                <tr>
                    <td style="border: 1px solid black; padding: 10px;">1</td>
                    <td style="border: 1px solid black; padding: 10px; text-align: left;">Игрок 6</td>
                    <td style="border: 1px solid black; padding: 10px;">10</td>
                    <td style="border: 1px solid black; padding: 10px;">8</td>
                </tr>
                <tr>
                    <td style="border: 1px solid black; padding: 10px;">2</td>
                    <td style="border: 1px solid black; padding: 10px; text-align: left;">Игрок 7</td>
                    <td style="border: 1px solid black; padding: 10px;">9</td>
                    <td style="border: 1px solid black; padding: 10px;">7</td>
                </tr>
                <tr>
                    <td style="border: 1px solid black; padding: 10px;">3</td>
                    <td style="border: 1px solid black; padding: 10px; text-align: left;">Игрок 8</td>
                    <td style="border: 1px solid black; padding: 10px;">8</td>
                    <td style="border: 1px solid black; padding: 10px;">6</td>
                </tr>
                <tr>
                    <td style="border: 1px solid black; padding: 10px;">4</td>
                    <td style="border: 1px solid black; padding: 10px; text-align: left;">Игрок 9</td>
                    <td style="border: 1px solid black; padding: 10px;">7</td>
                    <td style="border: 1px solid black; padding: 10px;">5</td>
                </tr>
                <tr>
                    <td style="border: 1px solid black; padding: 10px;">5</td>
                    <td style="border: 1px solid black; padding: 10px; text-align: left;">Игрок 10</td>
                    <td style="border: 1px solid black; padding: 10px;">6</td>
                    <td style="border: 1px solid black; padding: 10px;">4</td>
                </tr>
                </tbody>
            </table>

            <!-- Третья таблица: Квиз -->
            <h2>Квиз</h2>
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
                <tr>
                    <td style="border: 1px solid black; padding: 10px;">1</td>
                    <td style="border: 1px solid black; padding: 10px; text-align: left;">Игрок 11</td>
                    <td style="border: 1px solid black; padding: 10px;">10</td>
                    <td style="border: 1px solid black; padding: 10px;">8</td>
                </tr>
                <tr>
                    <td style="border: 1px solid black; padding: 10px;">2</td>
                    <td style="border: 1px solid black; padding: 10px; text-align: left;">Игрок 12</td>
                    <td style="border: 1px solid black; padding: 10px;">9</td>
                    <td style="border: 1px solid black; padding: 10px;">7</td>
                </tr>
                <tr>
                    <td style="border: 1px solid black; padding: 10px;">3</td>
                    <td style="border: 1px solid black; padding: 10px; text-align: left;">Игрок 13</td>
                    <td style="border: 1px solid black; padding: 10px;">8</td>
                    <td style="border: 1px solid black; padding: 10px;">6</td>
                </tr>
                <tr>
                    <td style="border: 1px solid black; padding: 10px;">4</td>
                    <td style="border: 1px solid black; padding: 10px; text-align: left;">Игрок 14</td>
                    <td style="border: 1px solid black; padding: 10px;">7</td>
                    <td style="border: 1px solid black; padding: 10px;">5</td>
                </tr>
                <tr>
                    <td style="border: 1px solid black; padding: 10px;">5</td>
                    <td style="border: 1px solid black; padding: 10px; text-align: left;">Игрок 15</td>
                    <td style="border: 1px solid black; padding: 10px;">6</td>
                    <td style="border: 1px solid black; padding: 10px;">4</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="bottom-part">
            <!-- Одна кнопка -->
            <div class="buttons" style="display: flex; justify-content: center; align-items: center; height: 100%;">
                <button class="btn">Кнопка</button>
            </div>
        </div>
    </div>
</div>
</body>

<style>
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

</style>

<%@include file="footer.jsp" %>


