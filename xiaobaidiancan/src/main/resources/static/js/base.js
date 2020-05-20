
//图片加载失败后默认图片
function errorImg(_this){
    _this.src = '/static/res/images/imgError.png';
    _this.onerror=null;
}

//身份证正面图片加载失败后默认图片
function errorCardZMImg(_this){
    _this.src = '/static/res/images/cardZMImgError.png';
    _this.onerror=null;
}

//身份证反面图片加载失败后默认图片
function errorCardFMImg(_this){
    _this.src = '/static/res/images/cardFMImgError.png';
    _this.onerror=null;
}

//营业执照图片加载失败后默认图片
function errorYyzzImg(_this){
    _this.src = '/static/res/images/yyzzImgError.png';
    _this.onerror=null;
}

//2018年07月13日 新功能tips提示功能详细用途的提示栏
function getTips(_this, str){
    layer.tips(str , $(_this), {
        tips: [1, '#3595CC'],
        time: 0
    });
}

//2019年01月04日 新功能tips提示功能，方向左
function getLeftTips(_this, str){
    layer.tips(str , $(_this), {
        tips: [4, '#000'],
        time: 0
    });
}

//2019年01月04日 提示层
function getMsg(str){
    layer.msg(str);
}

//2018年07月20日 营业执照、身份证正反面放大看图功能
function seeBigImg(_this){
    var url = $(_this).attr("src");
    layui.use('layer', function(){
        var layer = layui.layer;

        layer.open({
            type: 1,
            area: ['80%', '80%'],
            fixed: false, //不固定
            maxmin: true,
            content: '<img src="'+url+'" width="100%"/>'
        });
    });
}

/***获取当前时间 - 开始***/
function getDate(){
    var today=new Date();
    var y=today.getFullYear();
    var mo=today.getMonth()+1;
    var d=today.getDate();
    var h=today.getHours();
    var m=today.getMinutes();
    var s=today.getSeconds();// 在小于10的数字前加一个‘0’
    mo=checkTime(mo);
    d=checkTime(d);
    h=checkTime(h);
    m=checkTime(m);
    s=checkTime(s);
    return y+"/"+mo+"/"+d+"&nbsp;&nbsp;"+h+":"+m+":"+s;
}
function checkTime(i){
    if (i<10){
        i="0" + i;
    }
    return i;
}
/***获取当前时间 - 结束***/

//判断IE
function IEVersion() {
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串  
    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器  
    var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; //判断是否IE的Edge浏览器  
    var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
    if(isIE) {
        var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
        reIE.test(userAgent);
        var fIEVersion = parseFloat(RegExp["$1"]);
        if(fIEVersion == 7) {
            return 7;
        } else if(fIEVersion == 8) {
            return 8;
        } else if(fIEVersion == 9) {
            return 9;
        } else if(fIEVersion == 10) {
            return 10;
        } else {
            return 6;//IE版本<=7
        }   
    } else if(isEdge) {
        return 'edge';//edge
    } else if(isIE11) {
        return 11; //IE11  
    }else{
        return -1;//不是ie浏览器
    }
}