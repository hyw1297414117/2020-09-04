var t=6;//设定跳转的时间
if(isSucc==1){
    setInterval("refer()",1000); //启动1秒定时
}
function refer() {
    if (t == 0) {
        location = window.location.protocol+"//"+window.location.host+"/"+"login"; //#设定跳转的链接地址
    }
    document.getElementById('countdown').innerHTML = t +""; // 显示倒计时
    t--; // 计数器递减
}