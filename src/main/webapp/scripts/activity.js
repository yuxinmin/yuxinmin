/***
 * 获得活动列表
 */
function getActivityList(){
    $.ajax({
        url:"/activity.do",
        method:"get",
        success:function (data) {
            if(data == 'fail') {
                location.href = "login.html";
                return;
            }
            for(var i = 0; i < data.length; i++) {
                var a = data[i];
                var color = 'bg-primary';
                switch (i % 4) {
                    case 0:
                        color = 'bg-primary';
                        break;
                    case 1:
                        color = 'bg-warning';
                        break;
                    case 2:
                        color = 'bg-inverse';
                        break;
                    case 3:
                        color = 'bg-danger';
                        break;
                }
                $("#col_" + (i % 3)).append('<div id="contentfeeds" class="panel panel-animated panel-default animated fadeInUp" style="visibility: visible;"><div class="panel-body bordered-bottom"><div class="no-padding jumbotron '+color+'"><p class="lead"><a href="activity_detail.html#'+a.id+'">'+a.title+'</a></p></div><p class="text-muted">'+a.body+'</p><div class="text-muted"><small style="color:red;" class="endTime">活动结束时间:0</small></div></div></div>');
                var endTime = new Date(a.endTime).getTime() - new Date().getTime();
                $("#col_" + (i % 3) + " small:last").data("endTime", endTime);
            }
        }
    });
    // 开启倒计时
    setInterval(countDown,1000);
}
function countDown() {
    var smalls = $('#side_right .endTime');
    $(smalls).each(function () {
        var endTime = $(this).data("endTime");
        if(endTime > 0) {
            var time = endTime;
            var day = parseInt(time / (1000 * 60 * 60 * 24));
            time %= (1000 * 60 * 60 * 24);
            var hours = parseInt(time / (1000 * 60 * 60));
            time %= (1000 * 60 * 60);
            var minute = parseInt(time / (1000 * 60));
            time %= (1000 * 60);
            var second = parseInt(time / 1000)
            $(this).html('距离活动结束还有：' + day + '天' + hours + "小时" + minute + "分钟" + second + "秒");
            $(this).data("endTime", (endTime - 1000));
        } else {
            $(this).html('活动已经结束');
            var a = $(this).parent().parent().find('a');
            a.attr("href","javascript:return false");
            a.css('cursor','default');
        }
    });
}

/***
 * 查询指定活动下已参加活动的笔记列表
 */
function getNoteActivitys(){
	alert("查询参加活动的笔记列表");
}

/***
 * 查询活动笔记内容
 */
function getNoteActivityDetail(){
	console.log("查询活动笔记内容");
}

/***
 * 查询可选择的笔记本
 */
function getSelectNoteBook(){
	alert("查询可选择的笔记本");
}

/***
 * 查询可选择的笔记
 */
function getSelectNoteList(){
	alert("查询可选择的笔记");
}

/***
 *	将用户选择的笔记参加活动
 */
function createNoteActivity(){
	alert("将用户选择的笔记参加活动");
	$('.close,.cancle').trigger('click');
}

/***
 *	分享活动笔记
 */
function likeActivityNote() {
	alert("分享活动笔记");
}

/***
 *	顶笔记
 */
function up() {
	alert("顶笔记");
}

/***
 *	踩笔记
 */
function down(noteActivityId, dom) {
	alert("踩笔记");
}
