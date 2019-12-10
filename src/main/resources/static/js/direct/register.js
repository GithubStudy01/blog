$(function(){



})

//发送验证码
function sendVerificationCode(){
    $.ajax({
        url:"",
        type:"POST",
        dataType:"json",
        success:function(result){
            if(!result.msg){
                alert(result.sendCode);
            }else{
                countDown(120);//倒计时
            }
        },
        error: function(request) {
            alert("Connection error");
        }
    })

}

//倒计时
function countDown(num){
    var obj=$("");
    if(num == null){
        num=120;
    }
    var timer = setInterval(function () {
        if (num > 1) {
            num--;
            obj.text("重新发送(" + num + ")").attr("disabled", "disabled");
            obj.css('background', '#B8B8B8');
        } else {
            obj.text("发送验证码").removeAttr("disabled");
            obj.css('background', '#009688');
            clearInterval(timer);
        }
    }, 1000);

}

//验证手机验证码
function checkCode(){
    $.ajax({
        url:"",
        type:"POST",
        data:{
            "code":$("input[name=code]").val()
        },
        dataType:"json",
        success:function(result){
            var i;
            var obj=$("#sendVerificationCode");
            if(obj.prev().is("i")){
                obj.prev().remove();
            }
            if(result.msg){
                i='<i class="layui-icon layui-icon-ok-circle" style="font-size: 25px; color: #009688;"></i>';
                $("input[name=code]").attr("disabled", "disabled");
                obj.before(i);
                obj.remove();
                $("input[name=token]").val(result.token);
            }else{
                i='<i class="layui-icon layui-icon-close-fill" style="font-size: 30px; color: red;"></i>';
                obj.before(i);
            }
        },
        error: function(request) {
            alert("Connection error");
        }
    })
}