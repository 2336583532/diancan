var MAIN_NOW_PAGE = 0;				//当前显示的页签的索引
var MAIN_NOW_ARRAY = new Array();	//保存当前打开的页签的title名
var MAIN_PAGE_WIDTH = 49;			//首页的宽度
var MAIN_PAGE_BOXWIDTH = 0;			//页签最宽的显示宽度
var MAIN_PAGE_BOXML = 0;			//页签移动的位置
$(function(){
	
	init();
	$(window).resize(function(){
		init();
	});
	
	//菜单
	$(".menuFA").click(function(){
		menuFAClick($(this));
	});
	$(".menuFA").parent().mouseenter(function(){
		menuFAMouseenter($(this));
	});
	$(".frameMenu").mouseleave(function(){
		menuFAMouseleave($(this));
	});
	
	//小按钮菜单的鼠标进入和离开事件
	$(".shrinkBut ul li").mouseenter(function(){
		var num = $(this).index();
		var nums = $(this).parent().children().length;
		var oneW = $(this).width();
		
		$(".shrinkBut .hoverBox").animate({
		    width: oneW + 'px',
		    left: ($(this).position().left + 20) + 'px'
		},100);
	});
	
	$(".shrinkBut").mouseleave(function(){
		$(".shrinkBut .hoverBox").animate({
		    width: '0px'
		},100);
	});
	
	$(".topMenu ul li").mouseenter(function(){
		var num = $(this).index();
		var nums = $(this).parent().children().length;
		var oneW = $(this).width();
		
		$(".topMenu .hoverBox").animate({
		    width: oneW + 'px',
		    left: $(this).position().left + 'px'
		},100);
	});
	
	$(".topMenu").mouseleave(function(){
		$(".topMenu .hoverBox").animate({
		    width: '0px'
		},100);
	});
	
	//键盘监听事件
	//Esc
	$(document).on('keydown', function(e) {
		var e = event || window.event || arguments.callee.caller.arguments[0];
        if(e && e.keyCode == 27){ // 按 Esc
			$(".icon-compress").attr("class","iconfont icon-full-screen");
			$(".icon-compress").attr("onclick","exitFullScreen('fullScreenBut')");
        } 
	});
	
})

//全屏
function fullScreen(id) {
	var el = document.documentElement;
	var rfs = el.requestFullScreen || el.webkitRequestFullScreen || el.mozRequestFullScreen || el.msRequestFullscreen;
	if(typeof rfs != "undefined" && rfs) {
		rfs.call(el);
	}else{
		getMsg("浏览器不支持全屏调用，请使用其他浏览器或按F11键切换全屏！");
		return;
	}
	
	$("#"+id).attr("onclick","exitScreen('"+id+"')");
	$("#"+id).find("i").attr("class","iconfont icon-compress");
}
//退出全屏
function exitScreen(id) {
	if(document.exitFullscreen) {
		document.exitFullscreen();
	} else if(document.mozCancelFullScreen) {
		document.mozCancelFullScreen();
	} else if(document.webkitCancelFullScreen) {
		document.webkitCancelFullScreen();
	} else if(document.msExitFullscreen) {
		document.msExitFullscreen();
	}
	if(typeof cfs != "undefined" && cfs) {
		cfs.call(el);
	}
	
	$("#"+id).find("i").attr("class","iconfont icon-full-screen");
	$("#"+id).attr("onclick","fullScreen('"+id+"')");
}


//刷新当前IFrame
function frameRefresh(){
	var obj = $(".frameMain .con").children().eq(MAIN_NOW_PAGE).find("iframe");
	obj.attr("src",obj.attr("src"));
}

//主菜单收缩功能
function menuShrink(_this){
	var win_w = $("html")[0].getBoundingClientRect().width;
	if($(_this).find(".icon-caidan-shousuo").length == 1){
		$(_this).find("i").attr("class","iconfont icon-caidan-dakai");
	
		$(".frameMenu").animate({
		    width: '60px'
		},300,function(){
			$(".frameMenu .logo img").show();
			$(".frameMenu .logo .logoText").hide();
			$(".frameMenu .menu ul li a").css("padding", "0");
			$(".frameMenu .menu ul li font").hide();
			$(".frameMenu .menu ul li a i.left").animate({
				"width" : "60px",
				"margin-right" : "0",
				"font-size" : "20px",
				"left" : "0px"
			});
			$(".frameMenu .menu dl").hide();
		});
		$(".main").width(win_w - 60);
		$(".frameMain .title .mainPageBox").width(win_w - 180);
		
	}else{
		$(_this).find("i").attr("class","iconfont icon-caidan-shousuo");
	
		$(".frameMenu").animate({
		    width: '230px'
		},300);
		$(".main").width(win_w - 230);
		
		$(".frameMenu .logo img").hide();
		$(".frameMenu .logo .logoText").show();
		$(".frameMenu .menu ul li a").css("padding", "0 10px 0 20px");
		$(".frameMenu .menu dl dt a").css("padding", "0 10px 0 45px");
		$(".frameMenu .menu ul li font").show();
		$(".frameMenu .menu ul li a i.left").animate({
			"width" : "18px",
			"margin-right" : "7px",
			"font-size" : "16px",
			"left" : "20px"
		});
		$(".frameMenu .menu ul").children().eq(MENU_SHOW_NUM).find("dl").show();
		$(".frameMain .title .mainPageBox").width(win_w - 350);
		
	}
}

function menuFAMouseenter(_this){
	var topH = $(_this).position().top + $(".frameMenu .menu").scrollTop();
	$(".frameMenu .menu .hoverBox").animate({
	    top: topH + 'px',
	    height: "56px",
	    opacity: 1
	},50);
}

function menuFAMouseleave(_this){
	$(".frameMenu .menu .hoverBox").animate({
	    height: 0,
	    opacity: 0
	},100);
}

var MENU_SHOW_NUM = 0;
function menuFAClick(_this){
	var win_w = $(window).width();
	if($(".icon-caidan-dakai").length == 1){
		$(".icon-caidan-dakai").attr("class","iconfont icon-caidan-shousuo");
	
		$(".frameMenu").animate({
		    width: '230px'
		},200);
		$(".main").width($(window).width() - 230);
		
		$(".frameMenu .logo img").hide();
		$(".frameMenu .logo .logoText").show();
		$(".frameMenu .menu ul li a").css("padding", "0 10px 0 20px");
		$(".frameMenu .menu dl dt a").css("padding", "0 10px 0 45px");
		$(".frameMenu .menu ul li font").show();
		$(".frameMenu .menu ul li a i.left").css({
			"width" : "18px",
			"margin-right" : "7px",
			"font-size" : "16px",
			"left" : "20px"
		});
		$(".frameMenu .menu ul").children().eq(MENU_SHOW_NUM).find("dl").show();
		$(".frameMain .title .mainPageBox").width(win_w - 350);
	}
	
	var dl = _this.siblings("dl");
	var color = "#FFF";
	if(dl.length > 0){
		if(dl.css("display") == "none"){
			dl.show(200,function(){
				//解决点击后绿条的位置
				menuFAMouseenter(_this);
			});
			_this.parent().siblings().find(".menuFA .right").attr("class","iconfont icon-dajiantouyou right");
			_this.find(".right").attr("class","iconfont icon-arrow-down right");
			color = "#FFF";
		}else{
			dl.hide(200);
			_this.find(".right").attr("class","iconfont icon-dajiantouyou right");
			color = "#b0b0b2";
		}
	}
	MENU_SHOW_NUM = _this.parent().index();
	
	//关于IE9到11菜单点击时颜色问题
//	if(IEVersion() != 9 && IEVersion() != 10 && IEVersion() != 11 && IEVersion() != "edge"){
//		_this.parent().siblings().find(".menuFA").css("color", "#373d41").css("background-color","transparent");
//		_this.css("color", color).css("background-color", bColor);
//	}else{
//		_this.parent().siblings().find(".menuFA").css("cssText", "color:#373d41 !important;").css("cssText", "background-color:transparent !important;");
//		_this.css("cssText", "color:"+color+" !important;").css("cssText", "background-color:"+bColor+" !important;");
//	}
	_this.parent().siblings().find(".menuFA .left").css("color", "#b0b0b2");
	_this.parent().siblings().find(".menuFA").css("color", "#b0b0b2");
	_this.find(".left").css("color", color);
	_this.css("color", color);
	_this.parent().siblings().find("dl").hide(200);
	//关于IE9到11菜单点击时颜色问题
	if(IEVersion() != 9 && IEVersion() != 10 && IEVersion() != 11 && IEVersion() != "edge"){
		_this.parent().siblings().find("a.menuFA").css("background-color","transparent");
	}else{
		_this.parent().siblings().find("a.menuFA").css("cssText", "background-color:transparent !important;");
	}
	
}

//二级菜单点击后的处理方法
function menuCAClick(url,_this , pageId, text){
	//检查MAIN_NOW_ARRAY里面是否有该pageId
	for(var i = 0; i < MAIN_NOW_ARRAY.length; i++){
		if(MAIN_NOW_ARRAY[i] == pageId){
			$(".mainPage").children().removeClass("active");
			$(".mainPage").children().eq(i+1).addClass("active");
			
			$(".frameMain .con").children().hide();
			$(".frameMain .con").children().eq(i+1).show();
			
			//点击菜单后，存在时需要定位
			var mainPageML = $(".mainPage").css("margin-left");
			var thisW = $(".mainPage").children().eq(i+1)[0].getBoundingClientRect().width;
			var thisNowLeft = $(".mainPage").children().eq(i+1).position().left;
			var mainPageBoxW = $(".mainPageBox")[0].getBoundingClientRect().width;
			mainPageML = parseFloat(mainPageML.substring(0,mainPageML.length-2));
			if(thisNowLeft < 0 || thisNowLeft > (mainPageBoxW-thisW)){
				MAIN_PAGE_BOXML = mainPageML - thisNowLeft;
			}
			$(".mainPage").animate({
				marginLeft: MAIN_PAGE_BOXML+"px"
			});
			
			//处理菜单样式变化
			if(pageId == "pageNotice") return;
			if(pageId == "pageUserInfo" || pageId == "pageModifyPassword") return;
			$(_this).css("cssText", "background-color:#FF9966 !important;").css("color","#FFF");
			$(_this).parent().siblings().find("a").css("cssText", "background-color: transparent").css("color","#FFF");
			$(_this).parent().parent().parent().siblings().find("dl dt a").css("cssText", "background-color:#transparent").css("color","#FFF");
			MAIN_NOW_PAGE = i + 1;	//切换页签后需要更新MAIN_NOW_PAGE的值
			
			//重复的菜单打开时自动刷新
			frameRefresh();
			return;
		}
	}
	
	//处理frameMain页签的显示
	if(text == null || text == ""){
		text = $(_this).text();
	}
	var str = '<span class="active" onclick="pageSwitch(this)">'+
				'<div class="hoverBox"></div>'+
				'<font>'+text+'</font>'+
				'<i class="iconfont icon-chacha fork" onclick="pageClose(this)"></i>'+
			'</span>';
	MAIN_NOW_ARRAY.push(pageId);
	$(".mainPage").append(str);
	var nums = $(".mainPage").children().length;
	MAIN_NOW_PAGE = nums - 1;
	$(".mainPage").children().removeClass("active");
	$(".mainPage").children().eq(MAIN_NOW_PAGE).addClass("active");
	
	MAIN_PAGE_WIDTH = MAIN_PAGE_WIDTH + $(".mainPage").children().eq(MAIN_NOW_PAGE)[0].getBoundingClientRect().width;
	//IE模式下计算的 MAIN_PAGE_WIDTH 值会有0.01的误差；因此仅在IE模式下自动补足该误差。
	if(IEVersion() != -1){
	    MAIN_PAGE_WIDTH = MAIN_PAGE_WIDTH + 0.01;
	}
	$(".mainPage").width(MAIN_PAGE_WIDTH);
	pageShowML();
	
	//处理frameMain url地址
	//跳转时添加时间戳
    if(url!=null && url!=""){
        //时间戳
        var timesSamp=new Date().getTime();
        if(url.indexOf("?")!=-1){
            url+="&timesSamp="+timesSamp;
        }else{
            url+="?timesSamp="+timesSamp;
        }
    }
	var mainStr = '<div class="mainPageCon">'+
				'<iframe class="mainIframe" src="'+url+'" scrolling="yes" height="'+($(window).height() - $(".frameTop").height() - 40)+'"></iframe>'+
			'</div>';
	$(".frameMain .con").children().hide();
	$(".frameMain .con").append(mainStr);
	
	//处理菜单样式变化
	if(pageId == "pageNotice") return;
	if(pageId == "pageUserInfo" || pageId == "pageModifyPassword") return;
	$(_this).css("cssText", "background-color:#FF9966 !important;").css("color","#FFF");
	$(_this).parent().siblings().find("a").css("cssText", "background-color: transparent").css("color","#FFF");
	$(_this).parent().parent().parent().siblings().find("dl dt a").css("cssText", "background-color:#transparent").css("color","#FFF");
	
}

//关闭全部标签页
function allpageClose(){
	$(".mainPage").children().eq(0).nextAll().remove();
	
	$(".frameMain .con").children().eq(0).nextAll().remove();
	
	$(".mainPage").children().eq(0).addClass("active");
	$(".frameMain .con").children().eq(0).show();
	
	MAIN_NOW_ARRAY = [];
	MAIN_PAGE_WIDTH = 49;
	MAIN_NOW_PAGE = 0;
	$(".mainPage").width(300).css("margin-left", "0");
}

//关闭其它标签页
function otherpageClose(){
	$(".mainPage").children().eq(MAIN_NOW_PAGE).nextAll().remove();
	$(".mainPage").children().eq(MAIN_NOW_PAGE).prevUntil($(".mainPage").children().eq(0)).remove();
	
	$(".frameMain .con").children().eq(MAIN_NOW_PAGE).nextAll().remove();
	$(".frameMain .con").children().eq(MAIN_NOW_PAGE).prevUntil($(".frameMain .con").children().eq(0)).remove();
	
	var temp = MAIN_NOW_ARRAY[MAIN_NOW_PAGE - 1];
	MAIN_NOW_ARRAY = [];
	MAIN_NOW_ARRAY[0] = temp;
	
	MAIN_NOW_PAGE = 1;
	//Bug:如果选中的页签为首页页签，则关闭其他时会全部关闭，因此没有索引1的值。
	if($(".mainPage").children().eq(1).length != 0){
		MAIN_PAGE_WIDTH = 49 + $(".mainPage").children().eq(1)[0].getBoundingClientRect().width;
	}else{
		MAIN_PAGE_WIDTH = 49;
	}
	if(MAIN_PAGE_WIDTH < 300){
		$(".mainPage").width(300).css("margin-left", "0");
	}else{
		$(".mainPage").width(MAIN_PAGE_WIDTH).css("margin-left", "0");
	}
}

//关闭当前标签页
function nowpageClose(){
	if($(".mainPage").children().length == 1 || MAIN_NOW_PAGE == 0){
		return;
	}
	MAIN_PAGE_WIDTH = MAIN_PAGE_WIDTH - $(".mainPage").children().eq(MAIN_NOW_PAGE)[0].getBoundingClientRect().width;
	$(".mainPage").children().eq(MAIN_NOW_PAGE).remove();
	$(".frameMain .con").children().eq(MAIN_NOW_PAGE).remove();
	
	for(var i = 0; i < MAIN_NOW_ARRAY.length; i++){
		if(i+1 == MAIN_NOW_PAGE){
			MAIN_NOW_ARRAY.splice(i, 1);
			break;
		}
	}
	
	$(".mainPage").children().removeClass("active");
	$(".mainPage").children().eq(MAIN_NOW_PAGE-1).addClass("active");
	
	$(".frameMain .con").children().hide();
	$(".frameMain .con").children().eq(MAIN_NOW_PAGE-1).show();
	MAIN_NOW_PAGE = MAIN_NOW_PAGE-1;
	if(MAIN_PAGE_WIDTH < 300){
		$(".mainPage").width(300);
	}else{
		$(".mainPage").width(MAIN_PAGE_WIDTH);
	}
}

//页签关闭事件
var MAIN_NOW_CLOSETAG = 0;
function pageClose(_this){
	var num = $(_this).parent().index();
	
	MAIN_PAGE_WIDTH = MAIN_PAGE_WIDTH - $(".mainPage").children().eq(num)[0].getBoundingClientRect().width;
	if(MAIN_PAGE_WIDTH < 300){
		$(".mainPage").width(300);
	}else{
		$(".mainPage").width(MAIN_PAGE_WIDTH);
	}
	
	$(_this).parent().remove();
	$(".frameMain .con").children().eq(num).remove();
	
	for(var i = 0; i < MAIN_NOW_ARRAY.length; i++){
		if(i+1 == num){
			MAIN_NOW_ARRAY.splice(i, 1);
			break;
		}
	}
	MAIN_NOW_CLOSETAG = 1;
	
	if(MAIN_NOW_PAGE != 0){
		if(num < MAIN_NOW_PAGE){
			MAIN_NOW_PAGE--;
		}
		if(num == MAIN_NOW_PAGE){
			MAIN_NOW_PAGE--;
			$(".mainPage").children().removeClass("active");
			$(".mainPage").children().eq(MAIN_NOW_PAGE).addClass("active");
			
			$(".frameMain .con").children().hide();
			$(".frameMain .con").children().eq(MAIN_NOW_PAGE).show();
		}
	}
	
	//修复在打开多个页签的情况下点击菜单后页签定位后删除当前页签后默认选中的页签隐藏的问题。
	//造成另外的问题
//	MAIN_PAGE_BOXML = MAIN_PAGE_BOXML - $(".mainPage").children().eq(MAIN_NOW_PAGE).position().left;
//	$(".mainPage").animate({
//		marginLeft: MAIN_PAGE_BOXML+"px"
//	});
}

//页签切换事件
function pageSwitch(_this){  
	if(MAIN_NOW_CLOSETAG == 1){
		MAIN_NOW_CLOSETAG = 0;
		return;
	}
	MAIN_NOW_PAGE = $(_this).index();
	$(".mainPage").children().removeClass("active");
	$(".mainPage").children().eq(MAIN_NOW_PAGE).addClass("active");
	
	$(".frameMain .con").children().hide();
	$(".frameMain .con").children().eq(MAIN_NOW_PAGE).show();
}

//初始化页面
function init(){
	var win_h = $(window).height();
	//解决计算框架宽度时，小数位产生的冗余导致布局错位问题。
	var win_w = $("html")[0].getBoundingClientRect().width;
	//IE模式下宽度无法获取的问题
	if(IEVersion() != -1){
	    win_w = $(window).width();
	}
	var frameMenuW = $(".frameMenu").width();
	var logoH = 50;
	var frameTopH = $(".frameTop").height();
	
	$(".frameMenu").height(win_h);
	$(".frameMenu .menu").height(win_h - logoH);
	$(".main").width(win_w - frameMenuW).height(win_h);
	$(".frameMain").height(win_h - frameTopH);
	$(".frameMain .con").height(win_h - frameTopH - 40);
	$(".frameMain .con iframe").height(win_h - frameTopH - 40);
	$(".frameMain .title .mainPageBox").width(win_w - 350);
	MAIN_PAGE_BOXWIDTH = win_w - 350;
}

//点击菜单，根据页签数量展示位置
function pageShowML(){	
	if(MAIN_PAGE_WIDTH >= MAIN_PAGE_BOXWIDTH){
		var tempNum = Math.ceil((MAIN_PAGE_WIDTH - MAIN_PAGE_BOXWIDTH)*2/MAIN_PAGE_BOXWIDTH);
		MAIN_PAGE_BOXML = (-1)*MAIN_PAGE_BOXWIDTH/2*tempNum;
	}else{
		MAIN_PAGE_BOXML = 0;
	}
	$(".mainPage").animate({
		marginLeft: MAIN_PAGE_BOXML+"px"
	});
}

//页签左右的按钮事件
function pageShow(tag){
	if(MAIN_PAGE_WIDTH >= MAIN_PAGE_BOXWIDTH){
		var tempNum = Math.ceil((MAIN_PAGE_WIDTH - MAIN_PAGE_BOXWIDTH)*2/MAIN_PAGE_BOXWIDTH);
		if(tag == "l"){
			if(MAIN_PAGE_BOXML+MAIN_PAGE_BOXWIDTH/2 < 0){
				MAIN_PAGE_BOXML += MAIN_PAGE_BOXWIDTH/2;
			}else{
				MAIN_PAGE_BOXML = 0;
			}
		}else{
			if(MAIN_PAGE_BOXML-MAIN_PAGE_BOXWIDTH/2 > (-1)*MAIN_PAGE_BOXWIDTH/2*tempNum){
				MAIN_PAGE_BOXML -= MAIN_PAGE_BOXWIDTH/2;
			}else{
				MAIN_PAGE_BOXML = (-1)*MAIN_PAGE_BOXWIDTH/2*tempNum;
			}
		}
	}else{
		var tempNum = Math.ceil((MAIN_PAGE_WIDTH - MAIN_PAGE_BOXWIDTH)*2/MAIN_PAGE_BOXWIDTH);
		if(tag == "l"){
			if(MAIN_PAGE_BOXML+MAIN_PAGE_BOXWIDTH/2 < 0){
				MAIN_PAGE_BOXML += MAIN_PAGE_BOXWIDTH/2;
			}else{
				MAIN_PAGE_BOXML = 0;
			}
		}
	}
	$(".mainPage").animate({
		marginLeft: MAIN_PAGE_BOXML+"px"
	});
}
