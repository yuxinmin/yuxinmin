/***
 * 加载笔记本
 */
function loadNoteBook(){
	$.ajax({
		url:"/notebook.do",
		method:"get",
		success:function(data) {
		    if(data=='fail'){
		        location.href="login.html";
		        return;
            }
			var special=data['special'];
			var normal=data['normal'];
			var list=$('#first_side_right .contacts-list');
			//绑定特殊笔记本
			for(var i=0;i<special.length;i++){
				var nb=special[i];
				switch (nb.name){
					case '回收站':
						$('#rollback_button').data("notebook",nb);
						break;
                    case '收藏':
                        $('#like_button').data("notebook",nb);
                        break;
                    case '活动':
                        $('#action_button').data("notebook",nb);
                        break;
                    case '默认':
                        $('#first_side_right .contacts-list li:first').data("notebook",nb);
                        break;
				}
			}
			//绑定普通
			for (var i=0;i<normal.length;i++){
				var nb=normal[i];
				list.append('<li class="online">\n' +
                    '<a class=\'unchecked\'>\n' +
                    '<i class="fa fa-book" title="笔记本" rel="tooltip-bottom"></i> ' +
					nb.name+
                    '<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button>\n' +
                    '</a>\n' +
                    '</li>');
                $('#first_side_right .contacts-list li:last').data("notebook",nb);
                $('#first_side_right .contacts-list li:first').click();
			}

        }
	})
}


/****
 * 添加笔记本
 */
function addNoteBook(){
	var name=$('#input_notebook').val().trim();
	if(name==null||name.length==0){
		alert("笔记本名字不能为空");
		return;
	}
	$.ajax({

		url:"/notebook.do",
		method:"post",
		data:{name:name},
		success:function (data) {
			if(data['success']){
				alert("添加成功");
				var nb=data['notebook'];
                $('#first_side_right .contacts-list li:first')
                    .after('<li class="online">\n' +
                        '<a class=\'unchecked\'>\n' +
                        '<i class="fa fa-book" title="笔记本" rel="tooltip-bottom"></i> ' +
                        nb.name+
                        '<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button>\n' +
                        '</a>\n' +
                        '</li>');
                $('#first_side_right .contacts-list li:first').next().data('notebook',nb);
                //新加入笔记本被选中
                $('#first_side_right .contacts-list li:first').next().click();
			}else if(data['name_null']){
				alert("笔记本名字不能为空");
			}else if(data['name_repeat']){
				alert("笔记本名字已经存在");
			}
        }
	})
}

/***
 * 重命名笔记本
 */
function updateNoteBook(){
    var name=$('#input_notebook_rename').val().trim();
    var li=$('#first_side_right .contacts-list li .checked').parent();
    var nb=li.data('notebook');
    var id=nb.id;
    if(name==null||name.length==0){
        alert("笔记本名字不能为空");
        return;
    }
    $.ajax({
        url:"/notebook.do",
        method:"post",
        data:{name:name,id:id},
        success:function (data) {
            if(data=='fail'){
                location.href="login.html";
                return;
            }
            if(data['success']){
                alert("修改成功");
                li.html(
                        '<a class=\'unchecked\'>\n' +
                        '<i class="fa fa-book" title="笔记本" rel="tooltip-bottom"></i> ' +
                        name+
                        '<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button>\n' +
                        '</a>');

                nb.name=name;
                li.data("notebook",nb);
                li.click();
            }else if(data['name_null']){
                alert("笔记本名字不能为空");
            }else if(data['name_repeat']){
                alert("笔记本名字已经存在");
            }
        }
    })
}

/***
 * 删除笔记本
 */
function deleteNoteBook(){
    var li=$('#first_side_right .contacts-list li .checked').parent();
    var id=li.data('notebook').id;
    $.ajax({
        url: "/notebook.do",
        method: "delete",
        data: {id: id},
        success: function (data) {
            if(data=='fail') {
                location.href = "login.html";
                return;
            }
            li.remove();
            $('#first_side_right .contacts-list li:first').click();
            alert("删除成功");
        }
    })

}

/**
 * 将笔记本列表放置到select组件中
 */
function setNoteBookToSelect(){
	$.ajax({
        url:"/notebook/normal.do",
        method:"get",
        success:function (data) {
            if(data=='fail') {
                location.href = "login.html";
                return;
            }
            var defaultNotebook=$('#first_side_right .contacts-list li:first').data("notebook");
            $("#moveSelect").append('<option value="'+defaultNotebook.id+'">默认笔记本</option>');
            for(var i=0;i<data.length;i++){
                var notebook=data[i];
                $("#moveSelect").append('<option value="'+notebook.id+'">'+notebook.name+'</option>');
            }
        }
    })
}