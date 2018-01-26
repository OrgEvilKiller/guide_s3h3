<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>&nbsp;</p>
	<form:form action="${pageContext.request.contextPath}/userInfo.form?method=save" method="post" commandName="userInfoForm">
		<table align="center" border="1">
			<tr>
				<td>&nbsp;UserName: </td>
				<td>&nbsp;
					<form:input path="userName"/>
				</td>
			</tr>
			<tr>
				<td>&nbsp;Birthday: </td>
				<td>&nbsp;
					<form:input path="birthday"/> yyyy-MM-dd 
				</td>
			</tr>
			<tr>
				<td>&nbsp;Sex: </td>
				<td>&nbsp;
					<form:radiobutton path="sex" value="0"/>FaMale
					<form:radiobutton path="sex" value="1"/>Male
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;<input type="submit" name="button1" value="Submit"> </td>
			</tr>
		</table>
	</form:form>
</body>
</html>