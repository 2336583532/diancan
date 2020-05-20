<!DOCTYPE html>
<html>

	<head>
		<meta name="keywords" content="搜索关键字，以半角英文逗号隔开" />
		<title>小白点餐管理系统</title>

		<!-- 公共样式 开始 -->
		<link rel="shortcut icon" href="images/logo.gif"/>
		<link rel="bookmark" href="images/logo.gif"/>
		<link rel="stylesheet" type="text/css" href="../css/base.css">
		<link rel="stylesheet" type="text/css" href="../fonts/iconfont.css">
		<link rel="stylesheet" href="../layui/css/layui.css"  media="all">
		<script type="text/javascript" src="framework/jquery-1.11.3.min.js" ></script>
		<script type="text/javascript" src="js/base.js"></script>
		<script src="../layui/layui.js" charset="utf-8"></script>
		<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
		<link rel="stylesheet" type="text/css" href="../layui/css/layui.css">
	    <!--[if lt IE 9]>
	      	<script src="framework/html5shiv.min.js"></script>
	      	<script src="framework/respond.min.js"></script>
	    <![endif]-->
		<script type="text/javascript" src="layui/layui.js"></script>
		<script src="framework/cframe.js"></script><!-- 仅供所有子页面使用 -->
		<!-- 公共样式 结束 -->
		
		<link rel="stylesheet" type="text/css" href="../css/frameStyle.css">
		<script type="text/javascript" src="../framework/frame.js" ></script>
		
	</head>

	<body>
		<!-- 左侧菜单 - 开始 -->
		<div class="frameMenu">
		    <div class="logo">
		        <img src="images/logo.gif"/>
		        <div class="logoText">
		            <h1>小白点餐管理系统</h1>
		        </div>
		    </div>
		    <div class="menu">
		    	<div class="hoverBox"></div>
		        <ul>
		            <li>
		                <a class="menuFA" href="javascript:void(0)"><i class="iconfont icon-liuliangyunpingtaitubiao03 left" onmouseenter="getLeftTips(this, '菜品管理)" onmouseleave="layer.closeAll('tips')"></i><font>菜品管理</font><i class="iconfont icon-dajiantouyou right"></i></a>
		                <dl>
		                	<dt><a href="javascript:void(0)" onclick="menuCAClick('toAddFood',this, 'page1')">添加菜品</a></dt>
		                	<dt><a href="javascript:void(0)" onclick="menuCAClick('toQueryFoodList',this, 'page2')">菜品列表</a></dt>
		                </dl>
		            </li>
		            <li>
		                <a class="menuFA" href="javascript:void(0)"><i class="iconfont icon-liuliangyunpingtaitubiao03 left" onmouseenter="getLeftTips(this, '员工管理)" onmouseleave="layer.closeAll('tips')"></i><font>员工管理</font><i class="iconfont icon-dajiantouyou right"></i></a>
		                <dl>
		                	<dt><a href="javascript:void(0)" onclick="menuCAClick('toQueryManagerList',this, 'page3')">员工列表</a></dt>
		                	<dt><a href="javascript:void(0)" onclick="menuCAClick('toAddManager',this, 'page4')">新增员工</a></dt>
		                	<dt><a href="javascript:void(0)" onclick="menuCAClick('toAddManagerType',this, 'page5')">新增员工类型</a></dt>
		                </dl>
		            </li>
		            <li>
		                <a class="menuFA" href="javascript:void(0)"><i class="iconfont icon-liuliangyunpingtaitubiao03 left" onmouseenter="getLeftTips(this, '订单管理)" onmouseleave="layer.closeAll('tips')"></i><font>订单管理</font><i class="iconfont icon-dajiantouyou right"></i></a>
		                <dl>
		                	<dt><a href="javascript:void(0)" onclick="menuCAClick('toOrderList',this, 'page6')">所有订单</a></dt>
		                </dl>
		            </li>
		            <li>
		                <a class="menuFA" href="javascript:void(0)"><i class="iconfont icon-liuliangyunpingtaitubiao03 left" onmouseenter="getLeftTips(this, '我的消息)" onmouseleave="layer.closeAll('tips')"></i><font>我的消息</font><i class="iconfont icon-dajiantouyou right"></i></a>
		                <dl>
		                	<dt><a href="javascript:void(0)" onclick="menuCAClick('toKefu?userName=${user.phone}',this, 'page6')">客户消息</a></dt>
		                </dl>
		            </li>
		        </ul>
		    </div>
		</div>
		<!-- 左侧菜单 - 结束 -->
		
		<div class="main">
			<!-- 头部栏 - 开始 -->
			<div class="frameTop">
				<div class="shrinkBut">
		    		<div class="hoverBox"></div>
					<ul>
						<li><a href="javascript:void(0)" onclick="menuShrink(this)" onmouseenter="getTips(this, '收缩菜单')" onmouseleave="layer.closeAll('tips')"><i class="iconfont icon-caidan-shousuo"></i></a></li>
						<li><a href="javascript:void(0)" onclick="frameRefresh()" onmouseenter="getTips(this, '刷新')" onmouseleave="layer.closeAll('tips')"><i class="iconfont icon-htmal5icon23"></i></a></li>
					</ul>
				</div>
				<div class="topMenu">
		    		<div class="hoverBox"></div>
					<ul>
						<li><a href="javascript:void(0)" onclick="menuCAClick('tgls/modify_password.html',this, 'pageNotice', '通知公告')" onmouseenter="getTips(this, '通知公告')" onmouseleave="layer.closeAll('tips')"><i class="iconfont icon-tongzhigonggao1"></i><span class="news"></span></a></li>
						<li><a href="javascript:void(0)" id="fullScreenBut" onclick="fullScreen('fullScreenBut')" onmouseenter="getTips(this, '全屏')" onmouseleave="layer.closeAll('tips')"><i class="iconfont icon-full-screen"></i></a></li>
						<li>
							<a href="javascript:void(0)"><font id="userName" name="${user.phone }">${user.uname }</font><i class="iconfont icon-up-copy"></i></a>
							<dl>
			                	<dt><a href="javascript:void(0)" onclick="changePassword()">修改密码</a></dt>
			                </dl>
						</li>
						<li><a href="toLogin" onmouseenter="getTips(this, '退出系统')" onmouseleave="layer.closeAll('tips')"><i class="iconfont icon-084tuichu"></i></a></li>
					</ul>
				</div>
			</div>
			<!-- 头部栏 - 结束 -->
			
			<div class="frameMain">
				<div class="title" id="frameMainTitle">
					<i class="iconfont icon-shuangzuojiantou- leftbut" onclick="pageShow('l')"></i>
					<div class="mainPageBox">
						<div class="mainPage">
							<span class="active" onclick="pageSwitch(this)">
			    				<div class="hoverBox"></div>
								<i class="iconfont icon-shouye"></i>
							</span>
						</div>
					</div>
					<i class="iconfont icon-shuangyoujiantou- rightbut" onclick="pageShow('r')"></i>
					<i class="iconfont icon-iconfontarrows1 rightbut pageAllBut">
						<dl>
		                	<dt><a href="javascript:void(0)" onclick="nowpageClose()">关闭当前标签页</a></dt>
		                	<dt><a href="javascript:void(0)" onclick="otherpageClose()">关闭其它标签页</a></dt>
		                	<dt><a href="javascript:void(0)" onclick="allpageClose()">关闭全部标签页</a></dt>
		                </dl>
					</i>
				</div>
				<div class="con">
					<div class="mainPageCon">
						<iframe class="mainIframe" src="" scrolling="yes"></iframe>
					</div>
				</div>
			</div>
			<!-- 核心区域 - 结束 -->
		</div>
		
		<script type="text/javascript">
		
			var ws = null;
			if ("WebSocket" in window)
            {
			  var phone = $("#userName").attr("name");
               ws = new WebSocket("ws:localhost:8080/userWebSocket/"+phone);
            }else
            {
               
               alert("请更换浏览器登录!");
               window.opener=null;
               window.open('','_self');
               window.close();
            }
			
			ws.open = function(){
				
			}
			
			//接收到消息的回调方法
		    ws.onmessage = function(event){
		        if(event.data==1){
		        	alert("有新订单");
		        }else{
		        	
		        }
		    }

			
             
             ws.onclose = function()
             { 
          	  
             };
			
			window.onbeforeunload = function () {
		         closeWebSocket();
		    }
			
			function webSocketClose(){
				ws.close();
			}
	
		
      </script>
      <script type="text/javascript">
      
      layui.use('table', function(){
    	  
    	});
      function changePassword(){
			var phone = $("#userName").attr("name");
			layer.open({
			    type: 2,
			    title: '修改密码',
			    shadeClose: true,
			    shade: false,
			    maxmin: true, //开启最大化最小化按钮
			    area: ['500px', '300px'],
			    content: 'tochangePassword?phone='+phone
			  });
		}
      </script>
      
      <script type="text/javascript">
      
      </script>
	</body>
	
</html>