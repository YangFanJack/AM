 function checkUsername() {
            var username = $("#username").val();
            var reg_username = /^\w{8,20}$/;
            var flag = reg_username.test(username);
            if(flag){
            checkPassword()
                $("#username").css("border","");
                $("#tishi").text("用户名符合规则");
				$("#tishi").css("color","green");
				$("#submit").attr("disabled",false);	
				
            }
            else{
                $("#username").css("border","2px solid red");
                $("#tishi").text("用户名不符合规则");
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
            	
            	$("#tishi").text("密码符合规则");
				$("#tishi").css("color","green");
                $("#password").css("border","");
                $("#checkpassword").css("border","");
                $("#submit").attr("disabled",false)

            }
            else{
                $("#password").css("border","2px solid red");
                $("#tishi").text("密码不符合规则");
				$("#tishi").css("color","red");
				$("#submit").attr("disabled","disabled")
            }
            return flag;
        }