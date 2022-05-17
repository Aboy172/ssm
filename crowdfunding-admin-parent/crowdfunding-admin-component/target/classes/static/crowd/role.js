// 声明专门的函数用来在分配 Auth 的模态框中显示 Auth
function fillAuthTree() {
    // 1.发送 Ajax 请求查询 Auth 数据
    var ajaxReturn = $.ajax({
        "url": "/assgin/get/all/auth.json",
        "type": "post",
        "dataType": "json",
        "async": false
    });

    var authList = ajaxReturn.responseJSON.obj;
    // 3.准备对 zTree 进行设置的 JSON 对象
    var setting = {
        "data": {
            "simpleData": {
                // 开启简单 JSON 功能
                "enable": true, // 使用 categoryId 属性关联父节点，不用默认的 pId 了
                "pIdKey": "categoryId"
            }, "key": {
                // 使用 title 属性显示节点名称，不用默认的 name 作为属性名了
                "name": "title"
            }
        }, "check": {
            "enable": true
        }
    };
    // 4.生成树形结构
    // <ul id="authTreeDemo" class="ztree"></ul>
    $.fn.zTree.init($("#authTreeDemo"), setting, authList);
    // 获取 zTreeObj 对象
    var zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");
    // 调用 zTreeObj 对象的方法，把节点展开
    zTreeObj.expandAll(true);
    // 5.查询已分配的 Auth 的 id 组成的数组
    ajaxReturn = $.ajax({
        "url": "/assign/get/assigned/auth/id/by/role/id.json",
        "type": "post",
        "data": {
            "roleId": window.roleId
        },
        "dataType": "json",
        "async": false,

    });
    if (ajaxReturn.responseJSON.code !== 200) {
        layer.msg(" 请 求 处 理 出 错 ！ 响 应 状 态 码 是 ： " + ajaxReturn.responseJSON.code + " 说 明 是:+  " + ajaxReturn.responseJSON.message);
        return;
    }
    // 从响应结果中获取 authIdArray
    var authIdArray = ajaxReturn.responseJSON.obj;
    console.log(authIdArray);
    // 6.根据 authIdArray 把树形结构中对应的节点勾选上
    // ①遍历 authIdArray
    for (var i = 0; i < authIdArray.length; i++) {
        var authId = authIdArray[i];
        //     // ②根据 id 查询树形结构中对应的节点
        var treeNode = zTreeObj.getNodeByParam("id", authId);
        //     // ③将 treeNode 设置为被勾选
        //     // checked 设置为 true 表示节点勾选
        var checked = true;
        //     // checkTypeFlag 设置为 false，表示不“联动”，不联动是为了避免把不该勾选的勾
        //     //选上
        var checkTypeFlag = false;
        //     // 执行
        zTreeObj.checkNode(treeNode, checked, checkTypeFlag);
    }
}


//显示删除的模态框
function showRemoveModal(roleArray) {
    $("#removeModal").modal('show');
    // 创建全局变量数组，用来存放角色id和角色
    $("#roleNameDiv").empty();
    window.roleArray = [];
    for (var i = 0; i < roleArray.length; i++) {
        var role = roleArray[i];
        var roleName = role.roleName;
        $("#roleNameDiv").append(roleName + "<br/>");
        var roleId = role.roleId;
        window.roleArray.push(roleId);
    }

}

//执行分页，生成页面效果，调用其他函数，重新加载页面
function generaPage() {
    //1.获取页面的数据
    var page = getPageRemote();
    //2.填充表格
    fillTableBody(page);

}

//远程访问服务器，获取分页数据
function getPageRemote() {
    var ajaxResult = $.ajax({
        "url": "/role/get/page.html",
        "type": "post",
        "dataType": "json",
        "async": false,
        "data": {
            pageNum: window.pageNum,
            pageSize: window.pageSize,
            keyword: window.keyword
        },
    })
    //获取状态码，如果状态不是200，返回错误信息
    if (ajaxResult.responseJSON.code !== 200) {
        layer.msg(ajaxResult.responseJSON.message);
        return null;
    }
    //获取返回的page信息，如果信息是error，展示错误信息
    // let respBean = ajaxResult.responseJSON;
    // if (ajaxResult.message === "error") {
    //     layer.msg(ajaxResult.message);
    //     return null;
    // }
    //返回page信息
    return ajaxResult.responseJSON.obj;
}

//填充表格
function fillTableBody(page) {

    //清空旧数据
    $("#rolePageBody").empty();
    $("#Pagination").empty();
    if (page == null || page == undefined || page.records == null || page.records.length === 0) {
        $("#rolePageBody").append("<tr><td colspan='4''>抱歉，没有查询您要查找的信息！</td></tr>");
        return;
    }

    for (var i = 0; i < page.records.length; i++) {
        var role = page.records[i];
        var roleTd = role.id;
        var roleName = role.name;
        var numberTd = "<td>" + roleTd + "</td>";
        var checkboxTd = "<td><input id='" + roleTd + "' class='itemBox' type='checkbox'></td>";
        var roleNameTd = "<td id='roleName'>" + roleName + "</td>";

        var checkBtn = "<button  id='" + roleTd + "' type = 'button'  class='btn btn-success btn-xs checkBtn'><i class ='glyphicon glyphicon-check'></i></button>";
        var pencilBtn = "<button id='" + roleTd + "' type = 'button'  class='btn btn-primary btn-xs pencilBtn' ><i class = 'glyphicon glyphicon-pencil'> </i></button>";
        var removeBtn = "<button  id = '" + roleTd + "' type='button' class='btn btn-danger btn-xs removeBtn' ><i class=' glyphicon glyphicon-remove'></i></button>";


        var buttonTd = "<td>" + checkBtn + " " + pencilBtn + " " + removeBtn + "</td>";
        var tr = "<tr>" + roleTd + numberTd + checkboxTd + roleNameTd + buttonTd + "</tr>";
        $("#rolePageBody").append(tr);
    }
    generaNavigator(page);
}

//生成分页页码导航条
function generaNavigator(page) {

    //获取总记录数
    var total = page.total;
    var pageSize = page.size;
    var current = page.current;
    var properties = {
        num_edge_entries: 3,
        num_display_entries: 5,   //连续分页主体部分显示的分页条目数
        items_per_page: pageSize,   //每页显示的条目数
        current_page: current - 1,  //current_page内部是pageIndex，默认是从0开始
        prev_text: "上一页",
        next_text: "下一页",
        callback: paginationCallback
    };
    $("#Pagination").pagination(total, properties);


}

//翻页效果
function paginationCallback(pageIndex, jQuery) {

    window.pageNum = pageIndex + 1;
    //生成页面
    generaPage();

    //取消超链接默认行为
    return false;
}
