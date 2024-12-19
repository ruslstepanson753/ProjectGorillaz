<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp" %>

<!-- Средняя часть -->
<main class="main">
    <h1>ВЫБЕРИТЕ ИГРУ</h1>
    <div class="games">
        <div class="game" onclick="window.location.href='#';">
            <img src="${sessionScope.IMG_START_QUEST}" alt="Игра 1">
            <a href="game-quest">Квест</a>
        </div>
        <div class="game" onclick="window.location.href='#';">
            <img src="${sessionScope.IMG_START_ROULETTE}" alt="Игра 2">
            <a href="game-roulette">Рулетка</a>
        </div>
        <div class="game" onclick="window.location.href='#';">
            <img src="${sessionScope.IMG_START_QUIZ}" alt="Игра 3">
            <a href="game-quiz">Викторина</a>
        </div>
    </div>
</main>

<%@include file="footer.jsp" %>


