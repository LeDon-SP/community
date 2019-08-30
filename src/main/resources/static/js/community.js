/*
 * 评论问题
 */
function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();

    comment2target(questionId, 1, content);
}

/*
 * 评论回复
 */
function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();

    comment2target(commentId, 2, content);
}

function comment2target(targetId, type, content) {
    if (!content) {
        alert("回复内容不能为空！")
        return;
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
            } else {
                if (response.code == 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=e05d34e830a2eaab39fb&redirect_uri=http://localhost:8888/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }

            }
        },
        dataType: "json"
    });
}

/*
 *展开二级评论
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);

    var hasClass = comments.hasClass("in");
    if (hasClass) {
        //折叠二级评论
        comments.removeClass("in");
        e.classList.remove("active");
    } else {
        var subCommentContainner = $("#comment-" + id);
        if (subCommentContainner.children().length != 1) {
            //展开二级评论
            comments.addClass("in");
            e.classList.add("active");

        } else {
            $.getJSON("/comment/" + id, function (data) {

                $.each(data.data.reverse(), function (index, comment) {

                    var mediaLeftElement =
                        $("<div/>", {
                            "class": "media-left"
                        }).append(
                            $("<img/>", {
                                "class": "media-object img-rounded",
                                "src": comment.user.avatarUrl
                            }));

                    var mediaBodyElement =
                        $("<div/>", {
                            "class": "media-body"
                        }).append(
                            $("<h5/>", {
                                "class": "media-heading",
                                "html": comment.user.name
                            })).append(
                            $("<div/>", {
                                "html": comment.content
                            })).append(
                            $("<div/>", {
                                "class": "comment-menu"
                            }).append(
                                $("<span/>", {
                                    "class": "current-time pull-right",
                                    "html": moment(comment.gmtCreate).startOf().fromNow()
                                })));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement)
                        .append(mediaBodyElement);
                    ;

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 .col-md-12 .col-sm-12 .col-xs-12 comment-part"
                    });
                    commentElement.append(mediaElement);
                    subCommentContainner.prepend(commentElement);
                });

                //展开二级评论
                comments.addClass("in");
                e.classList.add("active");
            });
        }
    }

    /*//获取二级评论的展开状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse){
        //折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    }else {
        //展开二级评论
        comments.addClass("in");
        //标记二级评论展开状态
        e.setAttribute("data-collage","in");
        e.classList.add("active");
    }*/
}