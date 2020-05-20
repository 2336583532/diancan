<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>修改密码</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="../layui/css/layui.css"  media="all">
  <script type="text/javascript" src="../js/vue.js"></script>
<script type="text/javascript" src="../js/axios.js"></script>
</head>
<body>
<div id="app">
	<form class="layui-form" action="">

  
  <div class="layui-form-item">
  	<input type="hidden" id="phone" name="phone" value="${phone}"/>
  	<div class="layui-inline">
      <label class="layui-form-label">新密码</label>
      <div class="layui-input-inline">
        <input type="password" name="password1" lay-verify="required" autocomplete="off" class="layui-input" placeholder="">
      </div>
    </div><br>
    	<div class="layui-inline">
      <label class="layui-form-label">确认密码</label>
      <div class="layui-input-inline">
        <input type="password" name="password2" lay-verify="required" autocomplete="off" class="layui-input" placeholder="">
      </div>
    </div><br>
    <div class="layui-inline">
      <label class="layui-form-label">验证码</label>
      <div class="layui-input-inline">
        <input type="text" name="code" lay-verify="required" autocomplete="off" class="layui-input" placeholder="">
      </div>
     <button id="sendCode" type="button" class="layui-btn layui-btn-primary" onclick="getCode()">发送验证码</button>
      
    </div><br>
    
	<div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="formDemo">确认</button>
    </div>
  </div>
 
</form>
</div>

          
<script src="../layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
layui.use('form', function(){
	  var form = layui.form;
	  
	  //监听提交
	  form.on('submit(formDemo)', function(data){
		  var index = layer.load(1, {
			  shade: [0.1,'#fff']
			});
		  var json = data.field;
		  var params = Object.keys(json).map(function (key) {
		          return encodeURIComponent(key) + "=" + encodeURIComponent(json[key]);
		      }).join("&");
	    axios.post('changePassword',params).then(hnit=>{
	    	  layer.close(index);
	    	  if(hnit.data.code==-1){
	    		  if(hnit.data.message!=null){
	    			  layer.open({
	    				  content: hnit.data.message,
	    				  offset: 'rb'
	    			  });
	    		  }else{
	    			  layer.open({
	    				  content: "修改失败",
	    				  offset: 'rb'
	    			  });
	    		  }
				  
			  } else{
				  layer.open({
					  content: "修改成功",
					  offset: 'rb'
				  });
			  }
	      })
	    return false;
	  })
	});

</script>
<script type="text/javascript">
function getCode(){
	$("#sendCode").attr("class","layui-btn layui-btn-disabled");
	var phone = $("#phone").val();
	layer.msg('已向手机号'+phone+"发送验证码", {
		  time: 3000
	});
	var data = "phone="+phone;
	axios.get('getChangePasswordCode?'+data).then(hnit=>{
  	  if(hnit.data.code==-1){
  		  if(hnit.data.message!=null){
  			  layer.open({
  				  content: hnit.data.message,
  				  offset: 'rb'
  			  });
  			$("#sendCode").attr("class","layui-btn layui-btn-primary");
  		  }
    	}
	});
	var btn = $("#sendCode");
	var a = true;
	var len = 60;
	if (a) {
        
        var time = setInterval(function() {
            btn.innerHTML = parseFloat(btn.innerHTML) - 1 + 's'
        }, 1000);
        a = false;
    } else {
    	$("#sendCode").attr("class","layui-btn layui-btn-primary");
        return false;
    }
}
</script>
</body>
</html>