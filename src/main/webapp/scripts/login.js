/**
 * 页面初始化后，绑定函数。
 */
$(function(){
	//注册
	$("#regist_button").click(function(){
		register();
	});
	function clearName(e){
	    $(e).val().trim();
	    if(name==null||name.length==0){
	        $('#name-date').html("用户名不能为空");
	        return;
        }
        $.ajax({
            url:"user/clearName.do",
            method:"post",
            data:{name:name},
            success:function (data) {
                if(!data){
                    $('#name-date').html("用户名已经存在");
                }
            }
        })
    }
	
	//登录
	$("#login").click(function(){
		login();
	});
	
	//登出
	$("#logout").click(function(){
		logout();
	});
	
	//修改密码
	$("#changePassword").click(function(){
		changepwd();
	})
	
});

//注册
function register() {
        var name=$('#regist_username').val().trim();
        var password=$("#regist_password").val().trim();
        var nickName=$("#nickname").val().trim();
        $.ajax({
            url:'/user/reg.do',
            method:"post",
            data:{name:name, password:password, nickName:nickName},
            success:function (result) {
            	alert(result);
                if(result.success){
                    location.href = "/login.html";
                    alert("注册成功.");
                    $("#zc").attr("class","sig sig_out");
                    $("#dl").attr("class","log log_in");
                } else {
                    $('#reserror').html(result.msg);
                }
            }
        });

}

//登陆
function login() {
    var name=$('#name').val().trim();
    var password=$('#password').val().trim();
    $.ajax({
        url:'/user/login.do',
        method:"post",
        data:{name:name,password:password},
        success:function (result) {
            if(result.success){
                alert(result.value.name+"登录成功");
                addCookie("userId",result.value.id);
                addCookie("nickName",result.value.nickName);
                location.href="/edit.html";
            }else{
                $('#loginerror').html(result.msg);
            }
        }
    })
}

/**
 * 退出登录
 */
function logout(){
    delCookie("userId");
    delCookie("nickName");
    $.ajax({
        url:'/user/exit.do',
        method:'post',
        success:function (result) {
            if(result.success){
                location.href="/login.html";
            }
        }
    })
}

/**
 * 修改密码
 */
function changepwd(){
	var oldpwd=$('#last_password').val().trim();
	var newpwd=$('#new_password').val().trim();
	var renewpwd=$('#final_password').val().trim();
	var id=getCookie("userId");
	if(newpwd!=renewpwd){
        $('#error').html("两次密码输入不一致");
	}else {
		$.ajax({
            url:'/user/update.do',
			method:'post',
			data:{id:id,oldpwd:oldpwd,password:newpwd},
            success:function (result) {
                if(result.success){
                    alert("修改成功.");
                    logout();
                }
            }
		})

	}


}


