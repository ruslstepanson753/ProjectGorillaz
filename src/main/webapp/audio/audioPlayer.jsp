<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 18.12.2024
  Time: 23:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Аудиоплеер</title>
</head>
<body>
<!-- Аудиоплеер -->
<audio id="audioPlayer" controls autoplay style="width: 100%;">
    <source src="${sessionScope.AUDIO_START_FOOTER}" type="audio/mpeg">
    Ваш браузер не поддерживает аудио.
</audio>

<script>
    const audioPlayer = document.getElementById('audioPlayer');

    // Восстановление громкости из localStorage
    const savedVolume = localStorage.getItem('audioVolume');
    if (savedVolume) {
        audioPlayer.volume = savedVolume;
    }

    // Сохранение громкости при её изменении
    audioPlayer.addEventListener('volumechange', () => {
        localStorage.setItem('audioVolume', audioPlayer.volume);
    });

    // Сохранение текущей позиции трека
    audioPlayer.addEventListener('timeupdate', () => {
        localStorage.setItem('audioTime', audioPlayer.currentTime);
    });

    // Восстановление позиции при загрузке
    audioPlayer.addEventListener('loadedmetadata', () => {
        const savedTime = localStorage.getItem('audioTime');
        if (savedTime) {
            audioPlayer.currentTime = savedTime;
        }
    });
</script>
</body>
</html>
