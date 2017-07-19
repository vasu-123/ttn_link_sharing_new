<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head><title>TestDashboard</title>

<link href="<c:url value='resources/bootstrap/css/bootstrap.css' />" rel="stylesheet" type="text/css" />
		<link href="<c:url value='resources/bootstrap/css/bootstrap-theme.css' />" rel="stylesheet" type="text/css" />
<script src="<c:url value='resources/js/jquery-3.1.0.js' />"></script>
        
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
		<script src="<c:url value='resources/bootstrap/js/bootstrap.js' />"></script>
                
		        		

<script>

$(document).ready(function(){
$.ajax({
		type : "GET" ,
		url : "http://localhost:8080/Link_Sharing_2/api/user/session",
		timeout : 100000,
		async : false ,
		success : function(data) {
		
			if(data.username == null){
				alert(data);
				window.location.href = "http://localhost:8080/Link_Sharing_2/home.jsp";
			}
		username = data.username ;
		
		console.log(username);
		$('#main').prepend('<div id="row1" class="row"> <div class="col-md-3 col-sm-3" style="padding :20px 0px 0px 50px;"> <img src="resources/img/profile.png" style=" height : 80px; " /> </div> <div class="col-md-9 col-sm-9 "> <h4 style="display: inline-block; float : left">'+ data.firstName +' '+ data.lastName +'</h4><br> <span style="color: #999999 ; margin-left : 0">'+ '@'+data.username+'</span><br>Subscriptions<span style="display:inline-block; width: 70px;"></span>Topics<br><span id="subscriptions"></span><span style="display:inline-block; width: 70px;"></span><span id="topics"></span></div><br> ');
		},
		error : function(e) {
			console.log("ERROR: ");
		},
		done : function(e) {
			console.log("DONE");
		} 
	});
		
$.ajax({
	type : "GET" ,
	url : "http://localhost:8080/Link_Sharing_2/api/topics/total",
	timeout : 100000,
	success : function(data) {
				console.log(data);
	   $('#topics').html(data);
	},
	error : function(e) {
		console.log("ERROR: ");
	},
	
	done : function(e) {
		console.log("DONE");
	} 
});

$.ajax({
	type : "GET" ,
	url : "http://localhost:8080/Link_Sharing_2/api/subscriptions/total",
	timeout : 100000,
	success : function(data) {
				console.log(data);
	   $('#subscriptions').html(data);
	},
	error : function(e) {
		console.log("ERROR: ");
	},
	
	done : function(e) {
		console.log("DONE");
	} 
});

	
	$.ajax({
		type : "GET" ,
		url : "http://localhost:8080/Link_Sharing_2/api/created/topics",
		timeout : 100000,
		success : function(data) {
			months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];

			console.log(data);
			$.each(data,function(i,item){
				var d = new Date(item.dateCreated);
			$('#topics_created').append('<div class="row"> <div class="col-md-3 col-sm-3" style="padding :20px 0px 0px 50px;"> <img src="resources/img/profile.png" style=" height : 80px; " /> </div> <div class="col-md-9 col-sm-9 "> <h4 style="display: inline-block;">'+ item.createdBy.firstName +' '+ item.createdBy.lastName +'</h4><span style="color: #999999 ; margin-right : 25%;">'+' ' + '@'+item.createdBy.username+'  '+d.getDate()+'  '+months[d.getMonth()]+'  '+d.getFullYear()+'</span> <a href="" style=" color : #0066ff ; text-decoration : underline ; font-size:16px; ">'+item.name+'</a> <p> <a href="" style="color : #0066ff ; text-decoration : underline ; font-size:16px; margin-left :60%;">View Post</a> </div> </div><br> ');
			});
		},
		error : function(e) {
			console.log("ERROR: ");
		},
		
		done : function(e) {
			console.log("DONE");
		} 
	});

	$.ajax({
		type : "GET" ,
		url : "http://localhost:8080/Link_Sharing_2/api/subscribed/topics",
		timeout : 100000,
		success : function(data) {
			months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];

			console.log(data);
			$.each(data,function(i,item){
				var d = new Date(item.dateCreated);
			$('#topics_subscribed').append('<div class="row"> <div class="col-md-3 col-sm-3" style="padding :20px 0px 0px 50px;"> <img src="resources/img/profile.png" style=" height : 80px; " /> </div> <div class="col-md-9 col-sm-9 "> <h4 style="display: inline-block;">'+ item.createdBy.firstName +' '+ item.createdBy.lastName +'</h4> <span style="color: #999999 ; margin-right : 25%;">'+' ' + '@'+item.createdBy.username+'  '+d.getDate()+'  '+months[d.getMonth()]+'  '+d.getFullYear()+'</span> <a href="" style=" color : #0066ff ; text-decoration : underline ; font-size:16px; ">'+item.name+'</a> <p> <a href="" style="color : #0066ff ; text-decoration : underline ; font-size:16px; margin-left :60%;">View Post</a> </div> </div><br> ');
			});
		},
		error : function(e) {
			console.log("ERROR: ");
		},
		done : function(e) {
			console.log("DONE");
		} 
	});

	//document.getElementById("row1").append("cool");
	document.getElementById("subscriptions").append("ok");

		
});	
</script>
</head>


<body>
<div style="border: 2px solid black ; width : 500px" id="main">
<div style="border: 2px solid black ; width : 500px" id="topics_subscribed" >
                    <div style="padding : 0px; margin:0px; border : 2px solid black ; background-color : #e6e6e6 ">
                        <h4 style="padding-left:30px; "> Subscribed Topics </h4>
                    </div>
                   
                </div>


<div style="border: 2px solid black ; width : 500px " id="topics_created">
                    <div style="padding : 0px; margin:0px; border : 2px solid black ; background-color : #e6e6e6 ">
                        <h4 style="padding-left:30px; "> Created Topics </h4>
                    </div>
                   
                </div>
</div>
</body>
<script>

</script>
</html>