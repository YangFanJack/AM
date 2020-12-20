  function checkName() {
            var name = $("#name").val();
            var reg_name = /^[\u2E80-\u9FFF]+$/;
            var flag = reg_name.test(name);
            if(flag){
            
                $("#name").css("border","");
                $("#tishi").text("姓名符合规则");
								$("#tishi").css("color","green");
								$("#submit").attr("disabled",false);
								checkPassword();
								
            }
            else{
                $("#name").css("border","2px solid red");
                $("#tishi").text("姓名不符合规则");
								$("#tishi").css("color","red");
								$("#submit").attr("disabled","disabled")
            }
            return flag;
        }
        function checkPassword() {
            var password = $("#password").val();
            var reg_password = /^\w{8,20}$/;
            var flag = reg_password.test(password);
            if(flag){
				       if(equalPassword()){
            		$("#tishi").text("密码符合规则");
								$("#tishi").css("color","green");
                $("#password").css("border","");
                $("#checkpassword").css("border","");
                $("#submit").attr("disabled",false)
               	checkName()
				       }

            }
            else{
                $("#password").css("border","2px solid red");
                $("#tishi").text("密码不符合规则");
								$("#tishi").css("color","red");
								$("#submit").attr("disabled","disabled")
            }
            return flag;
        }
        function equalPassword(){
        	var password1 = $("#password").val();
        	var password2 = $("#checkpassword").val();
        	var flag=equals(password1,password2);
        	if(flag){
        		    $("#checkpassword").css("border","");
        		    $("#password").css("border","");
								$("#tishi").text("两次密码相同");
								$("#tishi").css("color","green");
								$("#submit").attr("disabled",false)
								checkName();
        	}
            
            else{
            	  $("#checkpassword").css("border","2px solid red");
            		$("#tishi").text("两次密码不同");
								$("#tishi").css("color","red");
                $("#submit").attr("disabled","disabled")
            }
            return flag;
        	}

 function equals(str1, str2)    
{    
    if(str1 ==str2)    
    {    
        return true;    
    }    
    return false;    
}

function checkxueyuan(){
	var checkxueyuan=$("#xueyuan option:selected").val();
	if(checkxueyuan== "XX") {
				$("#banji").val("kong")
				$("#rj1").css("display","inline")
				$("#rj2").css("display","inline")
				$("#jk1").css("display","inline")
				$("#jk2").css("display","inline")
				$("#jg1").css("display","none")
				$("#jg2").css("display","none")
				$("#wl1").css("display","none")
				$("#wl2").css("display","none")
				$("#hh1").css("display","none")
				$("#hh2").css("display","none")
				$("#lj1").css("display","none")
				$("#lj2").css("display","none")
				$("#yy1").css("display","none")
				$("#yy2").css("display","none")
				$("#ry1").css("display","none")
				$("#ry2").css("display","none")
			}
  else if(checkxueyuan== "JT"){
  	$("#banji").val("kong")
  			$("#jg1").css("display","inline")
				$("#jg2").css("display","inline")
				$("#wl1").css("display","inline")
				$("#wl2").css("display","inline")
				$("#hh1").css("display","none")
				$("#hh2").css("display","none")
				$("#lj1").css("display","none")
				$("#lj2").css("display","none")
				$("#yy1").css("display","none")
				$("#yy2").css("display","none")
				$("#ry1").css("display","none")
				$("#ry2").css("display","none")
				$("#rj1").css("display","none")
				$("#rj2").css("display","none")
				$("#jk1").css("display","none")
				$("#jk2").css("display","none")
  }
	else if(checkxueyuan== "HH"){
		$("#banji").val("kong")
				$("#hh1").css("display","inline")
				$("#hh2").css("display","inline")
				$("#lj1").css("display","inline")
				$("#lj2").css("display","inline")
				$("#yy1").css("display","none")
				$("#yy2").css("display","none")
				$("#ry1").css("display","none")
				$("#ry2").css("display","none")
				$("#rj1").css("display","none")
				$("#rj2").css("display","none")
				$("#jk1").css("display","none")
				$("#jk2").css("display","none")
			  $("#jg1").css("display","none")
				$("#jg2").css("display","none")
				$("#wl1").css("display","none")
				$("#wl2").css("display","none")
	}
	else if(checkxueyuan== "WY"){
		$("#banji").val("kong")
				$("#yy1").css("display","inline")
				$("#yy2").css("display","inline")
				$("#ry1").css("display","inline")
				$("#ry2").css("display","inline")
				$("#rj1").css("display","none")
				$("#rj2").css("display","none")
				$("#jk1").css("display","none")
				$("#jk2").css("display","none")
			  $("#jg1").css("display","none")
				$("#jg2").css("display","none")
				$("#wl1").css("display","none")
				$("#wl2").css("display","none")
				$("#hh1").css("display","none")
				$("#hh2").css("display","none")
				$("#lj1").css("display","none")
				$("#lj2").css("display","none")
	}
	}
