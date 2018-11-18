<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h2>@RequestParam 사용</h2>
    <form action="upload1.do" method="post" enctype="multipart/form-data">
        <br /> 
        	학번 : <input type="text" name="hakbun" id="hakbun" />
        <br /> 
                     파일 : <input type="file" name="report" id="report" />
        <br /> 
        	<input type="submit" value="제출"><br />
    </form>
 
    <h2>@MultipartRequest 사용</h2>
    <form action="upload2.do" method="post"
        enctype="multipart/form-data">
        학번 : <input type="text" name="hakbun" /><br /> 파일 : <input type="file"
            name="report" /><br /> <input type="submit" value="제출"><br />
    </form>
 
 
    <h2>Command 사용</h2>
    <form action="upload3.action" method="post"
        enctype="multipart/form-data">
        학번 : <input type="text" name="hakbun" /><br /> 파일 : <input type="file"
            name="report" /><br /> <input type="submit" value="제출"><br />
    </form>
</body>
</html>