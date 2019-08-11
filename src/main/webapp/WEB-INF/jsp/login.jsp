<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/layui/css/layui.css" />
		<style>
			#login{
			    margin-left: 57%;
			    margin-top: 8%;
			    border: solid 1px ghostwhite;
		        height: 396px;
    			width: 372px;
			    border-radius: 12px;
			    box-shadow: 6px 6px 32px 10px gainsboro;
			}
			.boder{
			    margin-left: 11%;
			    margin-top: 4%;
			    border: solid 1px ghostwhite;
			    height: 552px;
			    width: 1097px;
			    border-radius: 12px;
			    background-image: url(../../img/backg.jpg);
			}
			.layui-form-item{
				margin-top: 60px;
				margin-right: 30px;
			}
		</style>
	</head>
	<body>
		<div class="boder">
			<div id="login">
				<form method="post" action="login" class="layui-form">
					<div class="layui-form-item" >
						<label class="layui-form-label">用户名：</label>
						<div class="layui-input-block">
						    <input id="name" type="text" name="name" lay-verify="title" autocomplete="off" placeholder="请输入用户名" class="layui-input">
						</div>
				    </div>
				    <div class="layui-form-item" >
						<label class="layui-form-label">密码：</label>
						<div class="layui-input-block">
						    <input type="password" name="pwd" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
						</div>
				    </div>
				    <div class="layui-form-item" >
					    <div class="layui-input-block">
					      <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
					      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
					    </div>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>