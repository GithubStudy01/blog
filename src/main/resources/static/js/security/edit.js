$(function () {
    // $("#content").text("## hhh");
    var editor = editormd("md-editor", {
        width  : "90%",
        height : 720,
        path   : "/editor/lib/",
        theme : "dark",
        // previewTheme : "dark",
        editorTheme : "pastel-on-dark",
        // markdown : "md",
        codeFold : true,
        searchReplace : true,
        saveHTMLToTextarea : true, // 保存 HTML 到 Textarea
        toolbarAutoFixed:true,//工具栏自动固定定位的开启与禁用
        emoji : true,//开启表情
        // watch : false,                // 关闭实时预览
        taskList : true,
        tocm : true,         // Using [TOCM]
        tex : true,                   // 开启科学公式TeX语言支持，默认关闭
        flowChart : true,             // 开启流程图支持，默认关闭
        sequenceDiagram : true,       // 开启时序/序列图支持，默认关闭,
        imageUpload : true,
        imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        imageUploadURL : "/file/uploadImage",
        onload: function(){

        }
    });

    // 初始化标签控件
    $("#tag").tagEditor({
        initialTags: ['spring','springmvc'],
        maxTags: 5,
        delimiter: ',',
        forceLowercase: false,
        animateDelete: 0,
        placeholder: '请输入标签'
    });
})
layui.use('layer',function(){
    

})

