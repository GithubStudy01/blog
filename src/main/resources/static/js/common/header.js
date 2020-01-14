$(function () {
    $("#searchBtn").click(function () {
        var title = $("#search").val()
        localStorage.setItem("title",title);
        window.location.href = "/search/article";
        return false;
    })

})
