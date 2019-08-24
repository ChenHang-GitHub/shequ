/*
* 回复的ajax
* */
function  postComment() {
    var id =$("#questionId").val();
    var textarea = $("#comment-textarea").val();
    console.log(id);
    console.log(textarea);
    var data = {
        parentId:id,
        content:textarea,
        type:1
    }

    $.ajax({
        type:'post',
        url:'/comment',
        contentType: 'application/json;charset=utf-8',
        data:JSON.stringify(data),
        success:function (data) {
            if(data.code == 1001)
            {
                //confirm(data.message) 返回Boolean
                if(confirm(data.message))
                {
                    window.localStorage.setItem("boolean",1);
                    window.open("https://github.com/login/oauth/authorize?client_id=3b5993c41418af591fc3&redirect_uri=http://localhost:8080/callback&scope=user&state=1")

                }

            }else  if(data.code==1002)
            {
                alert(data.message);
            }
            else
            {
                window.location.reload();

            }
            console.log(data);

        }


    });


}