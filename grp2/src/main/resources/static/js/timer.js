function dateTimer() {
    const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, '0');
    const date = String(now.getDate()).padStart(2, '0');
    const hours = String(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');
    const seconds = String(now.getSeconds()).padStart(2, '0');
    const formattedDateTime = `${year}年${month}月${date}日 ${hours}:${minutes}:${seconds}`;

    document.getElementById("dateTimer").textContent = formattedDateTime;
}

dateTimer(); // ページロード時に一度実行

setInterval(dateTimer, 1000); // 1秒ごとに更新

function goBack() {
    window.history.back();
}
