<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajax File Uploader Plugin For Jquery</title>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="ajaxfileupload.js"></script>
<script type="text/javascript">
	function ajaxFileUpload() {
		$("#loading").ajaxStart(function() {
			$(this).show();
		}).ajaxComplete(function() {
			$(this).hide();
		});

		$.ajaxFileUpload({
			url : 'upload',
			secureuri : false,
			fileElementId : 'fileToUpload',
			dataType : 'json',
			data : {username : $("#username").val()},
			success : function(data, status) {
				if(data.msg) {
					alert(data.msg);
				}
			},
			error : function(data, status, e) {
				alert('上传出错');
			}
		})

		return false;

	}
</script>
</head>

<body>
	<h1>Ajax文件上传例子,JAVA版</h1>
	<img id="loading" src="loading.gif" style="display: none;">
	<form name="form" action="" method="POST" enctype="multipart/form-data">
		用户名:<input type="text" id="username" name="username"> <br>
		<input id="fileToUpload" type="file" size="45" name="fileToUpload"
			class="input">
		<button class="button" onclick="return ajaxFileUpload();">上传</button>
	</form>
</body>
</html>