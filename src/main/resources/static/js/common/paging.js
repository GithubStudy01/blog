$(function () {


})

function paging(content){
    var first = content.first == true?'disabled':'';
    var last = content.last == true?'disabled':'';
    var pageHeader = '<nav aria-label="...">\n' +
        '                <ul class="pagination">\n' +
        '                    <li class="'+first+'">\n' +
        '                      <span>\n' +
        '                        <span aria-hidden="true">&laquo;</span>\n' +
        '                      </span>\n' +
        '                    </li>';
    var pageMiddle = '';
    for(var i = 0;i<content.totalPages;i++){
        var position = i == content.pageNo?'active':'';
        pageMiddle += '<li class="'+position+'">\n' +
            '                        <span>'+(i+1)+' <span class="sr-only">(current)</span></span>\n' +
            '                    </li>';
    }
    var pageFooter = '<li class="'+last+'">\n' +
        '                      <span>\n' +
        '                        <span aria-hidden="true">&raquo;</span>\n' +
        '                      </span>\n' +
        '                    </li>\n' +
        '                </ul>\n' +
        '            </nav>';

    var pageHtml = pageHeader + pageMiddle + pageFooter;

    $("#paging").empty()
    $("#paging").append(pageHtml);
}
