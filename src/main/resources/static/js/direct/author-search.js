$(function () {
    var nickname = getQueryVariable("nickname");
    searchAuthor(nickname)
})
function getQueryVariable(variable)
{
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return("");
}
function searchAuthor(nickname){
    $.ajax({
        url: "http://localhost:8080/user/users?nickname="+nickname,
        type: "GET",
        dataType: "json",
        async: false,
        success: function (result) {
            console.log(result)
            var code = result.code;
            if(code != '0001'){
                alert(result.msg)
                return;
            }

        },
        error: function (request) {
            alert("Connection error");
        }
    })
}


