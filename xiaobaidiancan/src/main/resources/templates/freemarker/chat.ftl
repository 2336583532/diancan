<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>小白点餐</title>
<link rel="stylesheet" href="../layui/css/layui.mobile.css" media="all">
<script src="../layui/layui.js"></script>
<script src="../js/jquery-3.3.1.js"></script>
</head>
<body>
<input type="hidden" id="userName" value="${userName}">
<input type="hidden" id="id" value="${id}">

<script>
layui.use('mobile', function(){
  
  var userName=$("#userName").val();
  var id=$("#id").val().replace(/\,/g,"");
  var mobile = layui.mobile
  ,layim = mobile.layim; 
  var socket = new WebSocket('ws://localhost:8080/kefuWebSocket/'+userName);
  
  //基础配置
  layim.config({
    init: {
      //设置我的基础信息
      mine: {
        "username": userName //我的昵称
        ,"id": id //我的ID
        ,"status": "online"
        ,"avatar": "../images/user.jpg" //我的头像
      }
      //好友列表数据
      ,friend: [] //见下文：init数据格式
    }
  });
  //创建一个会话
  layim.chat({
    id: 3
    ,name: '在线客服'
    ,type: 'friend' //friend、group等字符，如果是group，则创建的是群聊
    ,avatar: '../images/kefu.gif'
  });
  layim.on('sendMessage', function(res){
	  var mine = res.mine; //包含我发送的消息及我的信息
	  var msg=mine.content;
	  socket.send(JSON.stringify({
		      type: "msg",
		      data: {
		    	  username: userName //消息来源用户名
	    		  ,avatar: "../images/user.jpg" //消息来源用户头像
	    	      ,id: id
	    		  ,content: msg //消息内容
	    		  ,mine: false //是否我发送的消息，如果为true，则会显示在右方
	    		  ,type:"friend"
	    		  ,timestamp: Date.parse(new Date()) //服务端时间戳毫秒数。注意：如果你返回的是标准的 unix 时间戳，记得要 *1000
		      }
	  })); 
  });
  socket.onmessage = function(res){
	  res = JSON.parse(res.data);
	  layim.getMessage(res); //res.data即你发送消息传递的数据（阅读：监听发送的消息）
	};
});


</script>
</body>
</html>