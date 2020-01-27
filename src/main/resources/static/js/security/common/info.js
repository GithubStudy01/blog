$(function () {

    //每次显示模态框之前要先把所有内容清理一遍
    $("#addTeachingMessageModel").on("show.bs.modal", function () {
        $("#teacherImage").attr("src", "");
        $("#teacherImage").addClass("hidden");
        $(this).removeData("bs.modal");
    });

    //    图片裁剪插件初始化
    var options =
        {
            thumbBox: '.thumbBox',
            spinner: '.spinner',
            imgSrc: ''
        }
    var cropper = $('.imageBox').cropbox(options);
    $('#file').on('change', function(){
        // $("#addTeachingMessageModel").modal("hide")
        $("#addTeachingMessageModel").css("opacity", 0);
        $("#cutImage").modal("show");
        var reader = new FileReader();
        reader.onload = function(e) {
            options.imgSrc = e.target.result;
            cropper = $('.imageBox').cropbox(options);
            $("#teacherImage").removeClass("hidden");
            // $("#teacherPicName").val(e.target.result)
            $(".uploadPhotos").removeClass("hiddenWarning");
            $(".uploadPhotos").addClass("hiddenWarning");
        }
        reader.readAsDataURL(this.files[0]);
        // this.files = [];
        $("#file").val("");
    })

    $('#btnCrop').on('click', function(){
        var img = cropper.getDataURL();
        // $('.cropped').append('<img src="'+img+'">');
        $("#teacherImage").removeClass("hidden");
        $("#teacherImage").attr('src',img);
        $("#teacherImage").css('width',"165px");
        $("#teacherImage").css('height',"165px");
        // $("#addTeachingMessageModel").modal("show")
        $("#cutImage").modal("hide");
        $("#addTeachingMessageModel").css("opacity", 1);
    })
})


//点击 上传图片按钮，会跳出一个选择照片的框
function uploadPhotos() {
    $("#file").click();
}
// 裁剪图片的那个框选择取消按钮
function backtoChose() {
    $("#addTeachingMessageModel").css("opacity", 1);
    $("#cutImage").modal("hide")
}
function hideImg() {
    $("#teacherImage").addClass('hidden');
}

function update() {
    $("#addTeachingMessageModel .modal-header .modal-title").text('修改内容');
    //显示模态框
    $("#addTeachingMessageModel").modal("show");
    e.stopPropagation();
}
layui.use('layer',function(){


})

