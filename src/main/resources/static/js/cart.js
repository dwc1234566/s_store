// /*按加号数量增*/
// function addNum(rid) {
// 	var n = parseInt($("#goodsCount"+rid).val());
//
// 	$("#goodsCount"+rid).val(n + 1);
// 	calcRow(rid);
// }
// /*按减号数量减*/
// function reduceNum(rid) {
// 	var n = parseInt($("#goodsCount"+rid).val());
// 	if (n == 0)
// 		return;
// 	$("#goodsCount"+rid).val(n - 1);
// 	calcRow(rid);
// }
/*全选全不选*/
function checkall(ckbtn) {
	$(".ckitem").prop("checked", $(ckbtn).prop("checked"));
	//calcTotal();
}
//删除按钮
function delCartItem(btn) {
	let cid = $(btn).parent("td").prev().prev().prev().prev().prev().children("input").val();
	$.ajax({
		url: "/carts/" + cid + "/delete",
		type: "POST",
		dataType: "JSON",
		success: function (json) {
			if (json.state == 200) {
				$(btn).parents("tr").remove();
			} else {
				alert("删除商品失败！" + json.errorMsge);
			}
		}
	})

	//calcTotal();
}
//批量删除按钮
function selDelCart() {
	let j = 0;
	let cids = [];
	//遍历所有按钮
	for (var i = $(".ckitem").length - 1; i >= 0; i--) {
		//如果选中
		if ($(".ckitem")[i].checked) {
		    cids[j] = $($(".ckitem")[i]).val();
			j++;

			$($(".ckitem")[i]).parents("tr").remove();
		}
	}

	$.ajax({
		url: "/carts/deleteList",
		type: "POST",
		data: {"cids":cids},
		success: function (json) {
			if (json.state === 200) {
				for (var i = $(".ckitem").length - 1; i >= 0; i--) {
					//如果选中
					if ($(".ckitem")[i].checked) {
						cids[j] = $($(".ckitem")[i]).val();
						j++;

					}
				}
			} else {
				alert("删除商品失败！" + json.errorMsg);
			}
		}
	})
	//calcTotal();
}


$(function() {



	//单选一个也得算价格
	$(".ckitem").click(function() {
			//calcTotal();
		})
		//开始时计算价格
		//calcTotal();




})

//计算单行小计价格的方法
function calcRow(rid) {
	//取单价
	var vprice = parseFloat($("#goodsPrice"+rid).html());
	//取数量
	var vnum = parseFloat($("#goodsCount"+rid).val());
	//小计金额
	var vtotal = vprice * vnum;
	//赋值
	$("#goodsCast"+rid).html("¥" + vtotal);

}

//计算总价格的方法

 function calcTotal() {
 	//选中商品的数量
 	var vselectCount = 0;
 	//选中商品的总价
 	var vselectTotal = 0;

 	//循环遍历所有tr
 	for (var i = 0; i < $(".cart-body tr").length; i++) {
		//计算每个商品的价格小计开始
 		//取出1行
		var $tr = $($(".cart-body tr")[i]);
 		//取单价
		var vprice = parseFloat($tr.children(":eq(3)").children("span").html());
		//取数量
 		var vnum = parseFloat($tr.children(":eq(4)").children(".num-text").val());
 		//小计金额
 		var vtotal = vprice * vnum;
		//赋值
		$tr.children(":eq(5)").children("span").html("¥" + vtotal);
		//计算每个商品的价格小计结束

		//检查是否选中
		if ($tr.children(":eq(0)").children(".ckitem").prop("checked")) {
 			//计数
 			vselectCount++;
 			//计总价
			vselectTotal += vtotal;
		}
		//将选中的数量和价格赋值
 		$("#selectTotal").html(vselectTotal);
 		$("#selectCount").html(vselectCount);
	}

}