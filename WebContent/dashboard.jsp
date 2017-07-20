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

function createTopic(){
	var topic_name = window.prompt("Enter topic name");
	if(topic_name == null)
		return;
	
	var visibility = window.prompt("Enter Visibility(Private , Public)");
	if(visibility == null)
		return;
	
	
	console.log(topic_name+" "+visibility);
	var topic_data = {} ;
	topic_data.name = topic_name;
	topic_data.visibility = visibility ;
    topic_data = JSON.stringify(topic_data);	 

	$.ajax({
		type : "POST" ,
		url : "http://localhost:8080/Link_Sharing_2/api/topic/create",
		data : topic_data ,
		timeout : 100000,
		success : function(data) {
			alert(data);
			},
		error : function(e) {
			console.log("ERROR: ");
		},
		
		done : function(e) {
			console.log("DONE");
		} 
	});

}

function shareLink(topic_name , createdBy){

var link = window.prompt("Enter Link");
var description = window.prompt("Enter description");

var link_data = {};
link_data.topic_name = topic_name ;
link_data.topicCreatedBy = createdBy ;
link_data.url = link;
link_data.description = description;
link_data = JSON.stringify(link_data);	 
console.log(link_data);

	
	$.ajax({
		type : "POST" ,
		url : "http://localhost:8080/Link_Sharing_2/api/resource/add",
		data : link_data ,
		timeout : 100000,
		success : function(data) {
			alert(data);
			},
		error : function(e) {
			console.log("ERROR: ");
		},
		
		done : function(e) {
			console.log("DONE");
		} 
	});

}

function getSubscriptions(i, topic_id){
	console.log("Trending "+i +" "+topic_id);
	var topic_data = {} ;
	topic_data.id = topic_id ;
	topic_data = JSON.stringify(topic_data);	 
	console.log(topic_data);
	
	$.ajax({
		type : "POST" ,
		url : "http://localhost:8080/Link_Sharing_2/api/subscriptions/topic",
		timeout : 100000,
		data : topic_data ,
		success : function(data) {
						console.log(data);
						
			document.getElementById("trending_topic_sub_"+i).innerHTML= data;
		},
		error : function(e) {
			console.log("ERROR: ");
		},
		done : function(e) {
			console.log("DONE");
		} 
	});

	

}

function getPosts(i, topic_id){
	console.log("Trending "+i +" "+topic_id);
	var topic_data = {} ;
	topic_data.id = topic_id ;
	topic_data = JSON.stringify(topic_data);	 
	console.log(topic_data);
	
	$.ajax({
		type : "POST" ,
		url : "http://localhost:8080/Link_Sharing_2/api/resources/topic",
		timeout : 100000,
		data : topic_data ,
		success : function(data) {
						console.log(data);
						
			document.getElementById("trending_topic_posts_"+i).innerHTML= data;
		},
		error : function(e) {
			console.log("ERROR: ");
		},
		done : function(e) {
			console.log("DONE");
		} 
	});


}

function checkSubscribed(i, topic_id){
	console.log("Trending "+i +" "+topic_id);
	var topic_data = {} ;
	topic_data.id = topic_id ;
	topic_data = JSON.stringify(topic_data);	 
	console.log(topic_data);
	
	$.ajax({
		type : "POST" ,
		url : "http://localhost:8080/Link_Sharing_2/api/check/user/subscribed",
		timeout : 100000,
		data : topic_data ,
		success : function(data) {
						console.log(data);
					if(data == false)	
			document.getElementById("check_sub_"+i).innerHTML ="Subscribe";
					
					else
						document.getElementById("check_sub_"+i).innerHTML= "Unsubscribe";

						
		},
		error : function(e) {
			console.log("ERROR: ");
		},
		done : function(e) {
			console.log("DONE");
		} 
	});


}

function changeSubscription(i){
	console.log("Check : "+$(i).text());
	console.log("Check Attribute: "+$(i).attr("topic_id"));
	
	
	if($(i).text() == "Unsubscribe"){
		var topic_data = {} ;
		topic_data.id = $(i).attr("topic_id") ;
		topic_data = JSON.stringify(topic_data);	 
		console.log(topic_data);
		
		
		$(i).text("Subscribe");
		
		$.ajax({
			type : "POST" ,
			url : "http://localhost:8080/Link_Sharing_2/api/unsubscribe/topic",
			timeout : 100000,
			data : topic_data ,
			success : function(data) {
						alert(data);							
			},
			error : function(e) {
				console.log("ERROR: ");
			},
			done : function(e) {
				console.log("DONE");
			} 
		});

	}
	
	else{
		var seriousness = window.prompt("Please mention your seriousness for the subscription(Casual , Serious , Very serious)");
		if(seriousness == null)
			return;
		
		$(i).text("Unsubscribe");

		var subscription_data = {} ;
		subscription_data.topic_id = parseInt($(i).attr("topic_id")) ;
		subscription_data.seriousness = seriousness ;
		subscription_data = JSON.stringify(subscription_data);	 
		console.log(subscription_data);
		
		
		$.ajax({
			type : "POST" ,
			url : "http://localhost:8080/Link_Sharing_2/api/subscribe/topic",
			timeout : 100000,
			data : subscription_data ,
			success : function(data) {
						alert(data);							
			},
			error : function(e) {
				console.log("ERROR: ");
			},
			done : function(e) {
				console.log("DONE");
			} 
		});


	}
		
}

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
		$('#main').prepend('<div id="row1" class="row"> <div class="col-md-3 col-sm-3" style="padding :20px 0px 0px 50px;"> <img src="resources/img/profile.png" style=" height : 80px; " /> </div> <div class="col-md-9 col-sm-9 "> <h4 style="display: inline-block; float : left">'+ data.firstName +' '+ data.lastName +'</h4><br> <span style="color: #999999 ; margin-left : 0">'+ '@'+data.username+'</span><br>Subscriptions<span style="display:inline-block; width: 70px;"></span>Topics<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="display:inline-block; width: 70px;"<span id="subscriptions"></span><span style="display:inline-block; width: 90px;"></span><span id="topics"></span></div><br> ');
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
	/*
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
*/

$.ajax({
	type : "GET" ,
	url : "http://localhost:8080/Link_Sharing_2/api/topics/trending",
	async : false ,
	timeout : 100000,
	success : function(data) {
				console.log(data);
		$.each(data,function(i,item){
			
		$('#topics_trending').append('<div class="row"> <div class="col-md-3 col-sm-3" style="padding :20px 0px 0px 50px;"> <img src="resources/img/profile.png" style=" height : 80px; " /> </div> <div class="col-md-9 col-sm-9 "> <h4 style="display: inline-block;">'+ item.name+'</h4><span style="color: #999999 ; margin-right : 25%;">'+' ' +'<br>@'+item.createdBy.username+'  '+'</span><br> Subscriptions<span style="display:inline-block; width: 70px;"></span>Posts</div> <br><br>&nbsp;&nbsp;&nbsp;&nbsp;<span id="trending_topic_sub_'+i+'"></span><span style="display:inline-block; width: 150px;"></span><span id="trending_topic_posts_'+i+'"></span><br><br><span style="display:inline-block; margin-left : 200px ; width: 150px;"><span id="check_sub_'+i+'" topic_id = "'+item.id+'" onclick=changeSubscription(this)></span></div><br> ');
		console.log("Topic id : "+item.id);
		getSubscriptions(i,item.id);
		getPosts(i,item.id);
		checkSubscribed(i,item.id);
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
						console.log(data);
			$.each(data,function(i,item){
				var d = new Date(item.dateCreated);
				$('#topics_subscribed').append('<div id="subscription_'+i+'" class="row"> <div class="col-md-3 col-sm-3" style="padding :20px 0px 0px 50px;"> <img src="resources/img/profile.png" style=" height : 80px; " /> </div> <div class="col-md-9 col-sm-9 "> <h4 style="display: inline-block; float : left">'+ item.name+'</h4>&nbsp; &nbsp;&nbsp;&nbsp; <h4 style="display: inline-block; "><a href="#" onclick="shareLink(\''+item.name+'\''+  ',\''+item.createdBy.username+'\')">Share Link</a></h4><br> <span style="color: #999999 ; margin-left : 0">'+ '@'+item.createdBy.username+'</span><br>Unsubscribe<span style="display:inline-block; width: 30px;">Subscriptions<span style="display:inline-block; width: 30px;"></span>Posts<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="display:inline-block; width: 70px;"<span id="subscriptions_topic_'+i+'"></span><span style="display:inline-block; width: 90px;"></span><span id="topics"></span></div><br> ');
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
	//document.getElementById("subscriptions").append("ok");

		
});	
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
      
<div class="navbar-header" id="create_topic" onclick="createTopic()">
                <a class="navbar-brand" href="#" style=" color : #0066ff ; text-decoration : underline ; font-size:20px; ">
                    Create Topic
                </a>
            </div>
        <div class="navbar-header">
                <a class="navbar-brand" href="#" style=" color : #0066ff ; text-decoration : underline ; font-size:20px; ">
                    Send Invitation
                </a>
            </div>
            
            <div class="navbar-header">
                <a class="navbar-brand" href="#" style=" color : #0066ff ; text-decoration : underline ; font-size:20px; ">
                    Create Resource
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
<br><br><br>
<div style="border: 2px solid black ; width : 700px" id="main">
<div style="border: 2px solid black ; width : 700px" id="topics_subscribed" >
                    <div style="padding : 0px; margin:0px; border : 2px solid black ; background-color : #e6e6e6 ">
                        <h4 style="padding-left:30px; "> Subscribed Topics </h4>
                    </div>
                   
                </div>

<br><br><br>
<div style="border: 2px solid black ; width : 700px " id="topics_trending">
                    <div style="padding : 0px; margin:0px; border : 2px solid black ; background-color : #e6e6e6 ">
                        <h4 style="padding-left:30px; "> Trending Topics </h4>
                    </div>
                   
                </div>
</div>
</body>
<script>

</script>
</html>