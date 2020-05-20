<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>小白点餐</title>
<link rel="stylesheet" href="../layui/css/layui.css" media="all">
<script src="../layui/layui.js"></script>
<script src="../js/jquery-3.3.1.js"></script>
</head>
<body>
<input type="hidden" id="userName" value="${userName}">
<input type="hidden" id="id" value="${id}">

<script>
layui.use('mobile', function(){
  

  var mobile = layui.mobile
  ,layim = mobile.layim; 
  
  //基础配置
  layim.config({

    init: {
      //设置我的基础信息
      mine: {
        "username": userName //我的昵称
        ,"id": 3 //我的ID
        ,"status": "online"
        ,"avatar": "../images/kefu.gif" //我的头像
      }
      //好友列表数据
      ,friend: [] //见下文：init数据格式
    },
	brief:false
  });
  //创建一个会话
  var userName=$("#userName").val();
  var id=$("#id").val().replace(/\,/g,"");
  var socket = new WebSocket('ws://localhost:8080/kefuWebSocket/'+userName);
  layim.on('sendMessage', function(res){
	  var mine = res.mine; //包含我发送的消息及我的信息
	  var msg=mine.content;
	  var to = res.to.name;
	  socket.send(JSON.stringify({
		      type: "huifu",
		      to:to,
		      data: {
		    	      username: userName //消息来源用户名
		    		  ,avatar: "../images/kefu.gif" //消息来源用户头像
		    		  ,id:3
		    		  ,content: msg //消息内容
		    		  
		    		  ,mine: false //是否我发送的消息，如果为true，则会显示在右方
		    		  ,timestamp: Date.parse(new Date()) //服务端时间戳毫秒数。注意：如果你返回的是标准的 unix 时间戳，记得要 *1000
		      }
	  })); 
  });
  socket.onmessage = function(res){
	  res = JSON.parse(res.data);
	  layim.chat({
	        name: res.username
	        ,type: res.type
	        ,avatar: res.avatar
	        ,id: res.id
	      });
	  layim.getMessage(res);
				
	};
	
});


</script>
</body>
</html>