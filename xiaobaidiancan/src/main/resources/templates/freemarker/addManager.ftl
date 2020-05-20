<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="../layui/css/layui.css"  media="all">
  <script type="text/javascript" src="../js/vue.js"></script>
<script type="text/javascript" src="../js/axios.js"></script>
</head>
<body>
<div style="margin-left:400px;margin-top: 90px;" id="app">
	<form class="layui-form" action="">

  
  <div class="layui-form-item">
  	<div class="layui-inline">
      <label class="layui-form-label">姓名</label>
      <div class="layui-input-inline">
        <input type="text" name="uname" lay-verify="required" autocomplete="off" class="layui-input" placeholder="">
      </div>
    </div><br>
    <div class="layui-inline">
      <label class="layui-form-label">手机号</label>
      <div class="layui-input-inline">
        <input type="tel" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="手机号为用户名">
      </div>
    </div><br>
    <div class="layui-inline">
      <label class="layui-form-label">邮箱</label>
      <div class="layui-input-inline">
        <input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input">
      </div>
    </div>
    
      <div class="layui-form-item">
    	<label class="layui-form-label">身份证</label>
    	<div class="layui-input-inline">
      		<input type="text" name="identity" lay-verify="identity" autocomplete="no" class="layui-input">
    	</div>
  	</div>
	<div class="layui-form-item">
    <label class="layui-form-label">员工类型</label>
    <div class="layui-input-block" style="width: 190px;">
      <select name="type" lay-filter="aihao">
      <#list managerType as type>
      	<option value="${type.typeid}">${type.typename}</option>
      </#list>
        
        
      </select>
    </div>
  </div>
  </div>
  


  <div class="layui-form-item">
    <label class="layui-form-label">性别</label>
    <div class="layui-input-block">
      <input type="radio" name="sex" value="0" title="男" >
      <input type="radio" name="sex" value="1" title="女" >
    </div>
  </div>
  
  
	<div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="formDemo">添加</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
 
</form>
</div>

          
<script src="../layui/layui.js" charset="utf-8"></script>

<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
//Demo
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
    axios.post('addManager',params).then(hnit=>{
    	  layer.close(index);
    	  if(hnit.data.code==-1){
    		  if(hnit.data.message!=null){
    			  layer.open({
    				  content: hnit.data.message,
    				  offset: 'rb'
    			  });
    		  }else{
    			  layer.open({
    				  content: "添加失败",
    				  offset: 'rb'
    			  });
    		  }
			  
		  } else{
			  layer.open({
				  content: "添加成功",
				  offset: 'rb'
			  });
		  }
      })
    return false;
  });
});
</script>

</body>
</html>