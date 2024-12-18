
<%--
  Created by IntelliJ IDEA.
  User: Ruslan
  Date: 11.12.2024
  Time: 2:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Нижняя часть -->
<footer class="footer">
    <p>Санкт-Петербург 2024г</p>
<c:choose>
    <c:when test="${not empty sessionScope.login}">
        <div style="margin: 20px; text-align: center;">
            <!-- Аудиоплеер -->
            <audio id="audioPlayer" controls style="width: 300px;">
                <source src="${sessionScope.AUDIO_START_FOOTER}" type="audio/mpeg">
                Ваш браузер не поддерживает аудио.
            </audio>
            <!-- Регулировка громкости -->
            <div style="margin-top: 10px;">
                <label for="volumeControl">Громкость:</label>
                <input id="volumeControl" type="range" min="0" max="1" step="0.01" value="0.5"
                       onchange="setVolume(this.value)" />
            </div>
        </div>
        <script>
            const audioPlayer = document.getElementById('audioPlayer');

            // Устанавливаем громкость через ползунок
            function setVolume(value) {
                audioPlayer.volume = value;
            }
        </script>
    </c:when>
</c:choose>

</footer>
<script>
    function submitForm(action) {
        // Устанавливаем action формы в зависимости от нажатой кнопки
        document.getElementById("authForm").action = action;
        // Отправляем форму
        document.getElementById("authForm").submit();
    }
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>

