$(function () {
    var nickname = getQueryVariable("nickname");
    searchAuthor(nickname)
    $("#search-input").val(nickname);
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
function getSearch(){
    var nickname = $("#search-input").val();
    searchAuthor(nickname)
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
            //清空子节点
            $("#author-search").empty()
            var content = result.content;
            var count = 0;
            var html = "";
            for(var i=0;i<content.content.length;i++){
                if(i%4 == 0){
                    html += '<div class="row">';
                    count ++;
                }
                html += '<div class="col-sm-6 col-md-3">' +
                    '                    <a href="/home/'+content.content[i].id+'" class="thumbnail">' +
                    '                        <img src="'+content.content[i].headurl+'" alt="'+ content.content[i].nickname +'" class="img-circle">' +
                    '                        <div class="caption">' +
                    '                            <h4>'+ content.content[i].nickname +'</h4>' +
                    '                            <div class="row">' +
                    '                                <div class="col-sm-4 col-md-4"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>&nbsp;'+content.content[i].goodSum + '</div>' +
                    '                                <div class="col-sm-4 col-md-4"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>&nbsp;'+content.content[i].viewSum + '</div>' +
                    '                                <div class="col-sm-4 col-md-4"><span class="glyphicon glyphicon-comment" aria-hidden="true"></span>&nbsp;'+content.content[i].commentSum + '</div>' +
                    '                            </div>' +
                    '                        </div>' +
                    '                    </a>' +
                    '                </div>';
                if(count*3+(count-1) == i){
                    html += '</div>';
                }
            }
            $("#author-search").append(html)
            //分页
            paging(content);

        },
        error: function (request) {
            alert("Connection error");
        }
    })
}


