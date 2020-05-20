<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>管理员列表</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="../css/layui.css"  media="all">
  <script type="text/javascript" src="../js/vue.js"></script>
  <script type="text/javascript" src="../js/axios.js"></script>
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
  <script src="../layui/layui.js" charset="utf-8"></script> 
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>

<body>
 <div id="app">
<table class="layui-hide" id="managerList">
	   
</table>
</div>

<script type="text/html" id="options">
  <a id="updateType" class="layui-btn layui-btn-xs" name="{{d.uid}}")>职位调整</a>
  <a id="deleteManager" class="layui-btn layui-btn-danger layui-btn-xs" name="{{d.uid}}")>删除</a>
</script>               
   
<script type="text/html" id="sex">

  {{#  if(d.sex ==0){ }}
    男
  {{#  } else { }}
    女
  {{#  } }}
</script>
 
<script>
layui.use('table', function(){
  var table = layui.table;
  
  table.render({
    elem: '#managerList'
    ,url:'queryManagerList'
    ,cellMinWidth: 80 
    ,page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
        layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
        ,curr: 1//设定初始在第 1 页
        ,groups: 1 //只显示 1 个连续页码
        ,limit:10
        ,limits:[5,10,15]
        ,first: true 
        ,last: true 
        
      }
    ,cols: [[
      {field:'uid', title: '编号',sort: true,align: 'center'}
      ,{field:'uname', title: '姓名',align: "center"} 
      ,{field:'sex', title: '性别',align: 'center',templet: "#sex"}
      ,{field:'phone', title: '手机号',align: 'center'}
      ,{field:'email', title: '邮箱',align: 'center'}
      ,{field:'identity', title: '身份证号',align: 'center'}
      ,{field:'typename', title: '职位',align: "center", templet: "#type"}
      ,{field:'options', title: '操作',align: "center", templet: "#options"} 
    ]]
  });
});
$(document).on('click', '#updateType', function() {
	var uid = $(this).attr("name");
	layer.open({
	    type: 2,
	    title: '修改职位',
	    shadeClose: true,
	    shade: false,
	    maxmin: true, //开启最大化最小化按钮
	    area: ['893px', '600px'],
	    content: 'toUpdataType?uid='+uid
	  });
});


$(document).on('click', '#deleteManager', function() {
	var uid=$(this).attr("name");
	layer.confirm('确定删除?', function(index){
		 var params = new URLSearchParams();
		 
		 params.append("uid",uid);
		axios.post("deleteManager",params).then(hnit=>{
			
			  if(hnit.data.code==-1){
				  if(hnit.data.message!=null){
	    			  layer.open({
	    				  content: hnit.data.message,
	    				  offset: 'rb'
	    			  });
	    		  }else{
	    			  layer.open({
	    				  content: "删除失败",
	    				  offset: 'rb'
	    			  });
	    		  }
			  } else{
				  layer.open({
					  content: "删除成功",
					  offset: 'rb'
				  });
			  }
		   }); 
	});    
	
});
</script>

</body>
</html>