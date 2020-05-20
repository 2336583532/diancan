<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>修改员工类型</title>
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
  	<input type="text" name="uid" value="${user.uid}" hidden="hidden">
      <label class="layui-form-label">职位名称</label>
      <div class="layui-input-block" style="width: 190px;">
      <select name="type" lay-filter="aihao">
      <#list managerType as type>
      	<#if type.typeid == user.type>
      		<option value="${type.typeid}" selected="">${type.typename}</option>
      	<#else>
      		<option value="${type.typeid}">${type.typename}</option>
      	</#if>
      </#list>
        
        
      </select>
    </div>
    </div><br>
	<div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit lay-filter="formDemo">修改</button>
    </div>
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
	  var json = data.field;
	  var params = Object.keys(json).map(function (key) {
	          return encodeURIComponent(key) + "=" + encodeURIComponent(json[key]);
	      }).join("&");
    axios.post('updataManagerType',params).then(hnit=>{
    	
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
  });
});
</script>

</body>
</html>