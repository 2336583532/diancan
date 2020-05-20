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
<table class="layui-hide" id="orderList">
	   
</table>
</div>

<script type="text/html" id="options">
  {{# if(d.type=='1'){ }}
	<a id="orders" class="layui-btn layui-btn-xs" name="{{d.id}}")>接单</a>
  {{# } }}  
</script>
<script type="text/html" id=orderType>
	{{# if(d.type=='1'){ }}
		<span>未接单</span>
	{{# } else if (d.type=='2') { }}
		<span>已接单</span>
	{{# } }}  
</script>                 
<script type="text/html" id="feedbackTime">
    {{#
     return layui.util.toDateString(d.date, 'yyyy-MM-dd HH:mm:ss')
    }}
</script>

 <script type="text/html" id="time">
    {{layui.util.toDateString(d.date*1000, 'yyyy-MM-dd HH:mm:ss')}}
    
</script>
<script>
layui.use(['table','laydate','util'], function(){
  var table = layui.table;
  
  table.render({
	id:"id" 
    ,elem: '#orderList'
    ,url:'queryOrderList'
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
      {field:'username', title: '姓名',align: 'center'}
      ,{field:'phone', title: '手机号',align: "center",} 
      ,{field:'orderList', title: '订单列表',align: 'center'}
      ,{field:'date', title: '下单时间',sort: true,align: 'center',templet : "#time"}
      ,{field:'type', title: '订单状态',align: "center", templet: "#orderType"}
      ,{field:'options', title: '操作',align: "center", templet: "#options"} 
    ]]
  });
});


$(document).on('click', '#orders', function() {
	var id=$(this).attr("name");
	
		 var params = new URLSearchParams();
		 
		 params.append("id",id);
		axios.post("orders",params).then(hnit=>{
			
			  if(hnit.data.code==-1){
				  layer.open({
					  content: "请重试!",
					  offset: 'rb'
				  });
			  } else{
				  layer.open({
					  content: "成功接单",
					  offset: 'rb'
				  });
			  }
	});    
	
});
</script>

</body>
</html>