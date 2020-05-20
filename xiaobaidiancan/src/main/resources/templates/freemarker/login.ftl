<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录系统</title>
<link rel="stylesheet" type="text/css" href="../css/layui.css">
<link rel="stylesheet" type="text/css" href="../css/base.css">
<link rel="stylesheet" type="text/css" href="../css/login.css">
<link rel="stylesheet" type="text/css" href="../fonts/iconfont.css">
<script type="text/javascript" src="../js/layui.js"></script>
</head>
<body>
	<body>
		<div class="loginBg"></div>
		<div class="login_main">
			<div class="box">
				<div class="left">
					<img src="../images/logo.gif" />
				</div>
				<div class="right">
					<form class="layui-form layui-form-pane" action="login" method="post">
						<div class="layui-form-item">
							<label class="layui-form-label login_title"><i class="iconfont icon-gerenzhongxin-denglu"></i></label>
							<div class="layui-input-block login_input">
								<input type="text" name="uname" required lay-verify="required" autocomplete="off" placeholder="请输入您的用户名" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label login_title"><i class="iconfont icon-mima1"></i></label>
							<div class="layui-input-block login_input">
								<input type="password" name="password" required lay-verify="required" autocomplete="off" placeholder="请输入密码" class="layui-input">
							</div>
						</div>
						<#if data??>
							<div style="color: red;">
								${data.message}
							</div>
						</#if>
						
						<div class="layui-form-item">
							<button class="layui-btn layui-btn-fluid login_but" lay-submit lay-filter="loginBut">登 录</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</body>
</html>