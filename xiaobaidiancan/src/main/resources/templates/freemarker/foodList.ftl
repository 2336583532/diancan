<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>菜品列表</title>
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
<table class="layui-hide" id="foodList">
	   
</table>
</div>

<script type="text/html" id="options">

  <a id="updataFood" class="layui-btn layui-btn-xs" name="{{d.fid}}")>修改</a>
  <a id="deleteFood" class="layui-btn layui-btn-danger layui-btn-xs" name="{{d.fid}}")>删除</a>
</script>
<script type="text/html" id="imgtmp">
 	<img src="{{d.fphoto}}" style="width:40px;height:40px;"></img>
</script>                 
   

 
<script>
layui.use('table', function(){
  var table = layui.table;
  
  table.render({
    elem: '#foodList'
    ,url:'queryFoodList'
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
      {field:'fid', title: '菜品编号',align: 'center'}
      ,{field:'fphoto', title: '图片',align: "center", templet: "#imgtmp"} 
      ,{field:'fname', title: '菜品名称',align: 'center'}
      ,{field:'fprice', title: '菜品价格',sort: true,align: 'center'}
      ,{field:'options', title: '操作',align: "center", templet: "#options"} 
    ]]
  });
});
$(document).on('click', '#updataFood', function() {
	var fid = $(this).attr("name");
	layer.open({
	    type: 2,
	    title: '修改菜品信息',
	    shadeClose: true,
	    shade: false,
	    maxmin: true, //开启最大化最小化按钮
	    area: ['893px', '600px'],
	    content: 'toUpdateFood?fid='+fid
	  });
});

$(document).on('click', '#updateFood', function() {
	var fid = $(this).attr("name");
	layer.open({
	    type: 2,
	    title: '修改菜品信息',
	    shadeClose: true,
	    shade: false,
	    maxmin: true, //开启最大化最小化按钮
	    area: ['893px', '600px'],
	    content: 'toUpdateFood?fid='+fid
	  });
});

$(document).on('click', '#deleteFood', function() {
	var fid=$(this).attr("name");
	layer.confirm('确定删除?', function(index){
		 var params = new URLSearchParams();
		 
		 params.append("fid",fid);
		axios.post("deleteFood",params).then(hnit=>{
			
			  if(hnit.data.code==-1){
				  layer.open({
					  content: "删除失败",
					  offset: 'rb'
				  });
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