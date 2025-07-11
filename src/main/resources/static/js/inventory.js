document.querySelectorAll("canvas").forEach(canvas => {
    const ctx = canvas.getContext("2d");
    const group = canvas.dataset.group;
    const units = parseInt(canvas.dataset.units);

    // Blood bag outline
    ctx.fillStyle = "#ccc";
    ctx.beginPath();
    ctx.moveTo(30, 20);
    ctx.lineTo(70, 20);
    ctx.quadraticCurveTo(90, 20, 90, 40);
    ctx.lineTo(90, 120);
    ctx.quadraticCurveTo(90, 140, 70, 140);
    ctx.lineTo(30, 140);
    ctx.quadraticCurveTo(10, 140, 10, 120);
    ctx.lineTo(10, 40);
    ctx.quadraticCurveTo(10, 20, 30, 20);
    ctx.closePath();
    ctx.fill();

    // Blood fill based on units (max = 500)
    const fillHeight = Math.min(units / 500, 1) * 100;
    ctx.fillStyle = "#b30000";
    ctx.fillRect(15, 140 - fillHeight, 70, fillHeight);

    // Pipe
    ctx.beginPath();
    ctx.moveTo(48, 140);
    ctx.lineTo(52, 160);
    ctx.strokeStyle = "red";
    ctx.lineWidth = 4;
    ctx.stroke();
});
