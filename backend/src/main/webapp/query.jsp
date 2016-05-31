<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.example.mealbuddy.myapplication.backend.Data.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Query Result</title>
</head>
<body>
	<%
		String retStr = (String) request.getAttribute("_retStr");
		if (retStr != null) {
	%>
	<%=retStr%><br>
	<%
		}
	%>
	<p style="font-size:20px">
		<b>Meal Request:</b>
	</p>

	<TABLE BORDER="1">
		<TR>
			<TH>id</TH>
			<TH>name</TH>
			<TH>class year</TH>
			<TH>major</TH>
			<TH>email</TH>
			<TH>preferred class year</TH>
			<TH>preferred major</TH>
			<TH>date</TH>
			<TH>time</TH>
			<TH>location</TH>
			<TH>regID</TH>
			<TH>phone</TH>
			<TH>dba</TH>
			<TH>status</TH>
			<TH>friend name</TH>
		</TR>
		<%
        			ArrayList<RequestMealData> resultList = (ArrayList<RequestMealData>) request.getAttribute("result");
        			if (resultList != null) {
        				for (RequestMealData entry : resultList) {
        		%>
        					<TR>
        						<TD> <%= entry.mID %></TD>
        						<TD> <%= entry.mName %></TD>
        						<TD> <%= entry.mClassYear %></TD>
        						<TD> <%= entry.mMajor %></TD>
        						<TD> <%= entry.mEmail %></TD>
        						<TD> <%= entry.mPrefClassYear %></TD>
        						<TD> <%= entry.mPrefMajor %></TD>
        						<TD> <%= entry.mDate %></TD>
        						<TD> <%= entry.mTime %></TD>
        						<TD> <%= entry.mLocation %></TD>
        						<TD> <%= entry.mRegId %></TD>
        						<TD> <%= entry.mPhone %></TD>
        						<TD> <%= entry.mDba %></TD>
        						<TD> <%= entry.mStatus %></TD>
        						<TD> <%= entry.mFriend %></TD>
        					</TR>
        			<% }
        			} %>
	</TABLE>

</body>
</html>