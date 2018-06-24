/**
 * 
 */
$(function(){
	listMyAttentionByPage(1);
});
function listMyAttentionByPage(pageIndex){
	
	/*移除之前的数据*/
	var user_list = document.getElementsByClassName("user_list");
	var long = user_list.length;
	for (var num = 0; num < long; num++) {
		user_list[0].parentNode.removeChild(user_list[0]);
	}
	
	var xhr=new XMLHttpRequest();
	var formData=new FormData();
	formData.append("myAttentionVO.pageIndex",pageIndex);
	xhr.open("POST","/wlmtxt/Works/Works_listMyAttentionVO");
	xhr.send(formData);
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4 && xhr.status==200){
			var list_myAttention=JSON.parse(xhr.responseText);

			for(var i=0;i<list_myAttention.followDTO.length;i++){
				
				var myAttention='<li class="user_list">';
				myAttention+='<div class="user_list_img_div">';
				/*用户头像*/
				myAttention+='<img class="user_img" id="'+list_myAttention.followDTO[i].user.user_id+'" onclick="to_other_data(this.id)" src="/wlmtxt/Works/Works_getImg?imgName=' +list_myAttention.followDTO[i].user.user_avatar +'"/>';
				myAttention+='</div>';
				myAttention+='<div class="user_info">';
				/*用户名*/
				myAttention+='<div  id="'+list_myAttention.followDTO[i].user.user_id+'" onclick="to_other_data(this.id)" class="user_name">'+list_myAttention.followDTO[i].user.user_username+'</div>';
				/*取消关注*/
				myAttention+='<div class="user_operateing" onclick="cancelFocus(this.id)" id="'+list_myAttention.followDTO[i].user.user_id+'">取消关注</div>';
				/*个性签名*/
				myAttention+='<div class="user_grjj">'+list_myAttention.followDTO[i].user.user_bio+'</div>';
				myAttention+='</div>';
				myAttention+='</li>';
				$(".user_list_ul").append(myAttention);
				
			}
		}
	}
}


//取消单个用户关注
function cancelFocus(focus_user_id){
	console.log("focus_user_id:"+focus_user_id);
	var xhrhp=new XMLHttpRequest();
	xhrhp.open("POST","/wlmtxt/User/User_followUser");
	var formData=new FormData();
	formData.append("accept_user.user_id",focus_user_id);
	xhrhp.send(formData);
	xhrhp.onreadystatechange=function(){
		if(xhrhp.readyState==4&&xhrhp.status==200){
			
			if(xhrhp.responseText=="1"){
				toastr.success("取消关注成功！");
				listMyAttentionByPage(1);
			}else{
				toastr.error("取消关注失败！");
				return false;
			}
		}
	}
}

//取消关注全部用户
function cancelAllFocus(){
	var xhrhttp=new XMLHttpRequest();
	xhrhttp.open("POST","/wlmtxt/User/User_deleteAllMyFollow");
	xhrhttp.send(null);
	xhrhttp.onreadystatechange=function(){
		if(xhrhttp.readyState==4&&xhrhttp.status==200){
			if(xhrhttp.responseText=="1"){
				toastr.success("全部取消关注成功！");
				listMyAttentionByPage(1);
			}
			else if(xhrhttp.responseText=="2"){
				toastr.error("没有关注的用户！");
			}else{
				toastr.error("全部取消关注失败！");
			}
		}
	}
}
