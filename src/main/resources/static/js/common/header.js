$(function () {
    $("#searchBtn").click(function () {
        var title = $("#search").val()
        localStorage.setItem("title",title);
        window.location.href = "/search/article";
        return false;
    })
    loginInfo();
})

//导航栏登陆信息
function loginInfo(){
    $.ajax({
        url: "http://localhost:8080/user/loginInfo",
        type: "GET",
        dataType: "json",
        async: false,
        success: function (result) {
            console.log(result)
            var user = result.content;
            var html;
            if(user == null){
                html = '<li><a href="/logoreg">注册 / 登陆</a></li>'

            }else{
                html = '<li class="dropdown">\n' +
                    '                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="padding: 0px;">\n' +
                    '                            <img src="http://5b0988e595225.cdn.sohucs.com/q_70,c_zoom,w_640/images/20180806/9425645d47cd4f8e9c11fc6a9959340a.jpeg" alt="测试" class="img-circle" style="width:auto;height:auto;max-width:40px;max-height:40px;padding: 0px;margin: 0px;"/>\n' +
                    '                            <span class="caret"></span>\n' +
                    '                        </a>\n' +
                    '                        <ul class="dropdown-menu">\n' +
                    '                            <li><a href="#">个人中心</a></li>\n' +
                    '                            <li><a href="/home/'+user.id+'">我的博客</a></li>\n' +
                    '                            <li><a href="/edit">写博客</a></li>\n' +
                    '                            <li><a href="#">博客管理</a></li>\n' +
                    '                            <li><a href="#">我的收藏</a></li>\n' +
                    '                            <li role="separator" class="divider"></li>\n' +
                    '                            <li><a href="javascript:void(0)" id="logout">注销</a></li>\n' +
                    '                        </ul>\n' +
                    '                    </li>'
            }
            $("#loginInfo").empty();
            $("#loginInfo").append(html);
            $("#logout").click(logout);
        },
        error: function (request) {
            alert("Connection error");
        }
    })
}
function logout(){
    $.get("/logout", function(result){
        $("#loginInfo").empty();
        var html = '<li><a href="/logoreg">注册 / 登陆</a></li>';
        $("#loginInfo").append(html);
    });
}