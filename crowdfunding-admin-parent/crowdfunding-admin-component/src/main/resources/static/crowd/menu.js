function AddDiyDom(treeId, treeNode) {
  var spanId = treeNode.tId + "_ico";
  $("#" + spanId).removeClass().addClass(treeNode.icon);
}

function RemoveHoverDom(treeId, treeNode) {
  // 拼接按钮组的 id
  var btnGroupId = treeNode.tId + "_btnGrp";
  // 移除对应的元素
  $("#" + btnGroupId).remove();
}

// 在鼠标移入节点范围时添加按钮组
function AddHoverDom(treeId, treeNode) {
  // 按钮组的标签结构：<span><a><i></i></a><a><i></i></a></span>
  // 按钮组出现的位置：节点中 treeDemo_n_a 超链接的后面
  // 为了在需要移除按钮组的时候能够精确定位到按钮组所在 span，需要给 span 设置有规律的id
  var btnGroupId = treeNode.tId + "_btnGrp";
  // 判断一下以前是否已经添加了按钮组
  if ($("#" + btnGroupId).length > 0) {
    return;
  }

  // 准备各个按钮的 HTML 标签
  var addBtn = "<a id='" + treeNode.id
      + "' class='btn btn-info dropdown-toggle btn-xs addBtn' style='margin-left:0px;padding-top:0px;' href='#' title='添加子节点'>&nbsp;&nbsp;<i class='fa fa-plus obg fa-fw ' aria-hidden='true'></i></a>";
  var removeBtn = "<a id='" + treeNode.id
      + "' class='btn btn-info dropdown-toggle btn-xs removeBtn' style='margin-left:10px;padding-top:0px;' href='#' title=' 删 除 节 点 '>&nbsp;&nbsp;<i class='fa fa-times obg fa-fw '></i></a>";
  var editBtn = "<a id='" + treeNode.id
      + "' class='btn btn-info dropdown-toggle btn-xs editBtn' style='margin-left:10px;padding-top:0px;' href='#' title=' 修 改 节 点 '>&nbsp;&nbsp;<i class='fa fa-edit obg fa-fw '></i></a>";
  // 获取当前节点的级别数据
  var level = treeNode.level;
  // 声明变量存储拼装好的按钮代码
  var btnHTML = "";
  // 判断当前节点的级别
  if (level === 0) {
    // 级别为 0 时是根节点，只能添加子节点
    btnHTML = addBtn;
  }
  if (level === 1) {
    // 级别为 1 时是分支节点，可以添加子节点、修改
    btnHTML = addBtn + " " + editBtn;
    // 获取当前节点的子节点数量
    var length = treeNode.children.length;
    // 如果没有子节点，可以删除
    if (length === 0) {
      btnHTML = btnHTML + " " + removeBtn;
    }
  }
  if (level === 2) {
    // 级别为 2 时是叶子节点，可以修改、删除
    btnHTML = editBtn + " " + removeBtn;
  }
  // 找到附着按钮组的超链接
  var anchorId = treeNode.tId + "_a";
  // 执行在超链接后面附加 span 元素的操作
  $("#" + anchorId).after(
      "<span id='" + btnGroupId + "'>" + btnHTML + "</span>");
}

function generateTree() {

  $.ajax({
    "url": "/menu/get/whole/tree.json",
    "type": "post",
    "contentType": "json",
    "success": function (response) {
      if (response.code === 200) {
        var setting = {
          "view": {
            "addDiyDom": AddDiyDom,
            "addHoverDom": AddHoverDom,
            "removeHoverDom": RemoveHoverDom
          },
          "data": {
            "key": {
              "url": "maomi"
            }
          }
        };
        var zNodes = response.obj;
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
      } else {
        layer.msg(response.message);
      }
    },
    "error": function () {
      layer.msg("请求失败");
    }

  });
}
