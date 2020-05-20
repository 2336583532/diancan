<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改菜品</title>
<link rel="stylesheet" href="../layui/css/layui.css">
<script type="text/javascript" src="../js/vue.js"></script>
<script type="text/javascript" src="../js/axios.js"></script>
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
</head>
<body>
<div style="margin-left:400px;margin-top: 90px;" id="app">
	<label class="layui-form-label">菜名</label>
    <div class="layui-input-block">
      <input type="text" v-model="fname" required  lay-verify="required" placeholder="请输入菜品名称" autocomplete="off" class="layui-input" style="width: 200px;">
    </div><br>
    <label class="layui-form-label">价格</label>
    <div class="layui-input-block">
      <input type="text" v-model="fprice" required  lay-verify="required" placeholder="请输入菜品价格" autocomplete="off" class="layui-input" style="width: 200px;">
    </div><br>
    <input type="hidden" id="foodPhoto">
	<div class="layui-upload" style="margin-left: 70px;">
	  <button type="button" class="layui-btn" id="upload">上传图片</button>
	  <div class="layui-upload-list" >
	    <img src="" class="layui-upload-img" id="foodPic" style="width: 80px;height: 80px;">
	    <p id="demoText"></p>
	  </div>
	</div>
	
	<button id="addFood" type="button" class="layui-btn" style="margin-left: 70px;" v-on:click="addFood">添加</button>
</div>
<script src="../layui/layui.js" charset="utf-8"></script>
<script>
layui.use('upload', function(){
	  var $ = layui.jquery
	  ,upload = layui.upload;
	//普通图片上传
	  var uploadInst = upload.render({
	    elem: '#upload'
	    ,url: 'foodPicUpload' //改成您自己的上传接口
	    ,auto: false
	    ,bindAction: '#addFood'
	    ,choose: function(obj){
	      obj.preview(function(index, file, result){
	        $('#foodPic').attr('src', result); //图片链接（base64）
	        $("#foodPhoto").val(file.name)
	      });
	    }
	    ,done: function(res){
	      //如果上传失败
	      if(res.code == -1){
	        return layer.msg('上传失败');
	      }
	      //上传成功
	    }
	  });
	});
new Vue({
	  el: '#app',
	  data: {
	  	fname:"",
	  	fprice:null,
	  	
	  },
	  methods:{
		  addFood:function(){
			  if(this.fname==null || this.fname=="" || this.fprice==null || this.fprice==""){
				  layer.msg('菜品名称或价格不能为空!');
				  return;
			  }
			  var params = new URLSearchParams();
			  var fphoto = $("#foodPhoto").val();
			  alert(fphoto)
			  if(fphoto==null || fphoto==""){
				  fphoto=$('#foodPic').attr('src');
			  }
			   params.append("fname",this.fname);
			   params.append("fprice",this.fprice);
			   
			   params.append("fphoto",fphoto);
			   axios.post("addFood",params).then(hnit=>{
				  if(hnit.data.code==-1){
					  layer.open({
						  content: "添加失败",
						  offset: 'rb'
					  });
				  } else{
					  this.fphoto="";
					  this.fname="";
					  this.fprice="";
					  $('#foodPic').attr('src', "");
					  layer.open({
						  content: "添加成功",
						 
						  offset: 'rb'
					  });
				  }
			   });
		  }
	  }
	})
</script> 
</body>
</html>