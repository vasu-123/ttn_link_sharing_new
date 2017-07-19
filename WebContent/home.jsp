<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    

		<link href="<c:url value='resources/bootstrap/css/bootstrap.css' />" rel="stylesheet" type="text/css" />
		<link href="<c:url value='resources/bootstrap/css/bootstrap-theme.css' />" rel="stylesheet" type="text/css" />
<script src="<c:url value='resources/js/jquery-3.1.0.js' />"></script>
        
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
		<script src="<c:url value='resources/bootstrap/js/bootstrap.js' />"></script>
        		




<script>

function submitLoginForm(form){
    var url = $(form).attr("action");
     var formData = {};
formData.username =  $('#username').val();
formData.password = $('#password').val();
formData = JSON.stringify(formData);	 
console.log(formData);
	 
	$.ajax({
		type : "POST" ,
		url : url,
		data : formData ,
		timeout : 100000,
		success : function(data) {
		 console.log(data);
		if(data == true){
		console.log("Session : ");
		window.location.href = "http://localhost:8080/Link_Sharing_2/dashboard.jsp";
		}
		
		else{
		alert(data);
		}
		
		},
		error : function(e) {
			console.log("ERROR: ");
		},
		done : function(e) {
			console.log("DONE");
		}
	});
		

	}

function registerUser(form){
    var url = $(form).attr("action");
     var formData = {};
	 
formData.username =  $("#username_register").val();
formData.password = $("#password_register").val();
formData.confirm_password = $("#confirm_password").val();
formData.email = $("#email").val();
formData.firstName = $("#firstName").val();
formData.lastName = $("#lastName").val();

formData = JSON.stringify(formData);	 
console.log(formData);
	 $.post(url, formData).done(function (data) {
        alert(data);
    });
}

</script>
</head>

<body>


    <nav class="navbar" style="border :2px solid black; ">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#" style=" color : #0066ff ; text-decoration : underline ; font-size:30px; ">
                    Link Sharing
                </a>
            </div>

            <form class="navbar-form navbar-right">
                <div class="input-group">
                    <span type="submit" class="input-group-addon">
                         <i class="fa fa-search"></i>
                    </span>
                    <input type="text" class="form-control" placeholder="Search.." />

                </div>

            </form>
        </div>
    </nav>
    <div class="container-fluid">
        <div class="row">

            <div class="col-md-7 col-sm-12 col-xs-12">

                <br>
                <br>
                <div style="border: 2px solid black " id="recent_shares">
                    <div style="padding : 0px; margin:0px; border : 2px solid black ; background-color : #e6e6e6 ">
                        <h4 style="padding-left:30px; "> Recent Shares </h4>
                    </div>
                   
                </div>


                <br>
                <br>
                <div style="border: 2px solid black " id="top_posts">
				
                    <div style="padding : 0px; margin:0px; border : 2px solid black ; background-color : #e6e6e6 ">
                        <h4 style="padding-left:30px; display : inline-block; margin-right :50% ;"> Top Posts </h4>
                        <select style=" color : black ; text-decoration : none ; font-size:18px; ">
                            <option value="today">Today</option>
                            <option value="week">1 week</option>
                            <option value="month">1 month</option>
                            <option value="year">1 year</option>
                        </select>


                    </div>
                    <br>

                </div>


            </div>
            <div class="col-md-5 col-sm-12 col-xs-12">
                <br>
                <br>
                <div style="border: 2px solid black ">
                    <div style="padding : 0px; margin:0px; border : 2px solid black ; background-color : #e6e6e6 ">
                        <h4 style="padding-left:30px; "> Login </h4>
                    </div>
                    <form class="form-horizontal " action="http://localhost:8080/Link_Sharing_2/api/user/login" name="login" style="padding-left:50px; padding-top:30px; ">
                        <div class="form-group ">
                            <label class="col-sm-4 control-label " style="text-align : left; ">Username*</label>
                            <div class="col-sm-7 ">
                                <input type="email"  id="username" class="form-control " placeholder="Email " required>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label " style="text-align : left; ">Password*</label>
                            <div class="col-sm-7 ">
                                <input type="password" id="password" class="form-control " placeholder="Password " required>
                            </div>
                        </div>

                        <div class="form-group ">
                            <a class="col-sm-4 control-label " style="text-align : left; color : #0066ff ; text-decoration : underline ; font-size:16px;">Forgot Password</a>
                            <div class="col-sm-7 ">
                                <button type="button" onclick="submitLoginForm(login)" class="btn btn-default " style="background-color:#e6e6e6"><strong>Login</strong></button>
                            </div>
                        </div>
                    </form>
                </div>

                <br><br><br>
                <div style="border: 2px solid black ">
                    <div style="padding : 0px; margin:0px; border : 2px solid black ; background-color : #e6e6e6 ">
                        <h4 style="padding-left:30px; "> Register </h4>
                    </div>
                    <form name="register" action="http://localhost:8080/Link_Sharing_2/api/user/register" class="form-horizontal " style="padding-left:50px; padding-top : 30px; ">
                        <div class="form-group " >
                            <label class="col-sm-4 control-label " style="text-align : left; ">First name*</label>
                            <div class="col-sm-7 ">
                                <input type="text " class="form-control " id="firstName" placeholder="First name " required>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label " style="text-align : left; ">Last name*</label>
                            <div class="col-sm-7 ">
                                <input type="text " class="form-control " id="lastName" placeholder="Last name " required>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label " style="text-align : left; ">Email*</label>
                            <div class="col-sm-7 ">
                                <input type="email" class="form-control " id="email" placeholder="Email " required>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label " style="text-align : left; ">Username*</label>
                            <div class="col-sm-7 ">
                                <input type="text" class="form-control " id="username_register" placeholder="Username " required>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label " style="text-align : left; ">Password*</label>
                            <div class="col-sm-7 ">
                                <input type="password" class="form-control " id="password_register" placeholder="Password " required>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label " style="text-align : left; ">Confirm Password*</label>
                            <div class="col-sm-7 ">
                                <input type="password" class="form-control " id="confirm_password" placeholder="Confirm Password " required>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-4 control-label " style="text-align : left; ">Photo</label>
                            <div class="col-sm-7 ">
                                <input type="file" class="form-control ">
                            </div>
                        </div>
                        <div class="form-group ">
                            <span class="col-sm-4 control-label "> </span>
                            <div class="col-sm-7 ">
                                <button type="button" onclick= "registerUser(register)" class="btn btn-default " style="background-color:#e6e6e6"><strong>Register</strong></button>
                            </div>
                        </div>


                    </form>

                </div>

                <br><br>
            </div>
        </div>
    </div>
	<script>
	$(document).ready(function(){
	
	$.ajax({
		type : "GET" ,
		url : "http://localhost:8080/Link_Sharing_2/api/resources/recent",
		timeout : 100000,
		success : function(data) {
		console.log(data);
		months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
			console.log("SUCCESS:");
			$.each(data,function(i,item){
			var d = new Date(item.dateCreated);
			console.log("Date : "+months[d.getMonth()]);
			$('#recent_shares').append('<div class="row"> <div class="col-md-3 col-sm-3" style="padding :20px 0px 0px 50px;"> <img src="resources/img/profile.png" style=" height : 80px; " /> </div> <div class="col-md-9 col-sm-9 "> <h4 style="display: inline-block;">'+ item.createdBy.firstName +' '+ item.createdBy.lastName +'</h4> <span style="color: #999999 ; margin-right : 25%;">'+' ' + '@'+item.createdBy.username+'  '+d.getDate()+'  '+months[d.getMonth()]+'  '+d.getFullYear()+'</span> <a href="" style=" color : #0066ff ; text-decoration : underline ; font-size:16px; ">'+item.topic.name+'</a> <p>'+item.description+' <a href="" style="color : #0066ff ; text-decoration : underline ; font-size:16px; margin-left :60%;">View Post</a> </div> </div><br> ');
			});
			
		},
		error : function(e) {
			console.log("ERROR: " + e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
	
$.ajax({
		type : "GET" ,
		url : "http://localhost:8080/Link_Sharing_2/api/resources/top",
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
		console.log(data);
				months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];

			console.log("SUCCESS:");
			$.each(data,function(i,item){
			var d = new Date(item.dateCreated);
			$('#top_posts').append('<div class="row"> <div class="col-md-3 col-sm-3" style="padding :20px 0px 0px 50px;"> <img src="resources/img/profile.png" style=" height : 80px; " /> </div> <div class="col-md-9 col-sm-9 "> <h4 style="display: inline-block;">'+ item.createdBy.firstName +' '+ item.createdBy.lastName +'</h4> <span style="color: #999999 ; margin-right : 25%;">'+' ' + '@'+item.createdBy.username+'  '+d.getDate()+'  '+months[d.getMonth()]+'  '+d.getFullYear()+'</span> <a href="" style=" color : #0066ff ; text-decoration : underline ; font-size:16px; ">'+item.topic.name+'</a> <p>'+item.description+' <a href="" style="color : #0066ff ; text-decoration : underline ; font-size:16px; margin-left :60%;">View Post</a> </div> </div><br> ');
			
			});
			
		},
		error : function(e) {
			console.log("ERROR: " + e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
	

	
	
	
	});
	
	</script>

</body>

</html>