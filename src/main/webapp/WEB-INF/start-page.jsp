<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp"%>

<!-- Средняя часть -->
<main class="main">
    <h1>ВЫБЕРИТЕ ИГРУ</h1>
    <div class="games">
        <div class="game" onclick="window.location.href='#';">
            <img src="https://via.placeholder.com/150" alt="Игра 1">
            <a href="game-quest">Игра Квест</a>
        </div>
        <div class="game" onclick="window.location.href='#';">
            <img src="https://via.placeholder.com/150" alt="Игра 2">
            <a href="game-roulette">Игра Рулетка</a>
        </div>
        <div class="game" onclick="window.location.href='#';">
            <img src="https://via.placeholder.com/150" alt="Игра 3">
            <a href="game-quiz">Игра Викторина</a>
        </div>
    </div>
</main>

<%@include file="footer.jsp"%>


