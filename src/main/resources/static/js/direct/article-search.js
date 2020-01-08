$(function () {
    var article = getQueryVariable("article");
    // searchArticle(article,null,null,null,null)
    test();
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
function test(){
    // searchArticle("","1","createtime,desc",null,null)
    searchArticle("","3",["goodTimes,desc","commentTimes,desc","viewTimes,desc"],null,null)
}

function searchArticle(title,limitTimeType,sort,page,size){
    $.ajax({
        url: "http://localhost:8080/article/search",
        type: "GET",
        dataType: "json",
        data:{
          "title":title,
          "limitTimeType":limitTimeType,
            "page":page,
            "size":size,
            "sort":sort
        },
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
