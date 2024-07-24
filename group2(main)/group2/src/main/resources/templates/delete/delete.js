function dateTimer() {
    const now = new Date();
    const year = now.getFullYear();
    const month = now.getMonth() + 1;
    const date = now.getDate();
    const hours = now.getHours();
    const minutes = now.getMinutes();
    const seconds = now.getSeconds();
    const formattedDateTime = `${year}年${month}月${date}日 ${hours}:${minutes}:${seconds}`;

    document.getElementById("dateTimer").textContent = formattedDateTime;
}

dateTimer();

setInterval(dateTimer, 1000);

function goBack() {
    window.history.back();
}