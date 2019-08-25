/*
* 回复的ajax
* */
function postComment() {
    var id = $("#questionId").val();
    var textarea = $("#comment-textarea").val();
    console.log(id);
    console.log(textarea);
    var data = {
        parentId: id,
        content: textarea,
        type: 1
    }

    $.ajax({
        type: 'post',
        url: '/comment',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(data),
        success: function (data) {
            if (data.code == 1001) {
                //confirm(data.message) 返回Boolean
                if (confirm(data.message)) {
                    window.localStorage.setItem("boolean", 1);
                    window.open("https://github.com/login/oauth/authorize?client_id=3b5993c41418af591fc3&redirect_uri=http://localhost:8080/callback&scope=user&state=1")

                }

            } else if (data.code == 1002) {
                alert(data.message);
            }
            else {
                window.location.reload();

            }
            console.log(data);

        }


    });
}

/*
* */

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    alert(commentId);
    alert(content);

    var data = {
        parentId: commentId,
        content: content,
        type: 2
    }
    $.ajax({
        type: 'post',
        url: '/comment',
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(data),
        success: function (data) {
            if (data.code == 1001) {
                //confirm(data.message) 返回Boolean
                if (confirm(data.message)) {
                    window.localStorage.setItem("boolean", 1);
                    window.open("https://github.com/login/oauth/authorize?client_id=3b5993c41418af591fc3&redirect_uri=http://localhost:8080/callback&scope=user&state=1")

                }
            } else if (data.code == 1002) {
                alert(data.message);
            }
            else {
                window.location.reload();

            }
            console.log(data);
        }
    });
}

/**
 * 展开二级评论
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);
    // 获取一下二级评论的展开状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        // 折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        var subCommentContainer = $("#comment-" + id);

        // $.getJSON("/comment/" + id, function (data) {
        //
        //
        //         console.log(data);
        //         var subCommentContainer = $("#comment-"+ id);
        //         $.each(data, function (index,comment) {
        //             console.log(comment.content);
        //             var c = $("<div/>", {
        //                 "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments",
        //                 html: comment.content
        //             });
        //             subCommentContainer.prepend(c);
        //         });
        //
        //         //展开二级评论
        //         comments.addClass("in");
        //         // 标记二级评论展开状态
        //         e.setAttribute("data-collapse", "in");
        //         e.classList.add("active");
        //     });
        if (subCommentContainer.children().length != 1) {
            //展开二级评论
            comments.addClass("in");
            // 标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        }else {
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.reverse(), function (index, comment) {
                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "class":"comment-font",
                        "html": comment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "html":moment(comment.gmtCreate).format('YYYY-MM-DD HH:mm')
                    }).append($("<hr>",{

                        }
                        ))
                    ));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });
                //展开二级评论
                comments.addClass("in");
                // 标记二级评论展开状态
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });

        }
    }
}