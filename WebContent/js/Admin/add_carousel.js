var query_data = {
	"worksVO.currPage" : "1",
};
// 当前页面分页信息
var page_infomantion = {
	currPage : 1,
	countRecords : 1,
	pageSize : 10,
	totalPages : 1,
	havePrePage : false,
	haveNexPage : false,
}
$(function() {
	get_ListBreakecaseInformationByPageAndSearch(query_data);
})
// 列表查询
function get_ListBreakecaseInformationByPageAndSearch(query_data) {
	$
			.post(
					'/wlmtxt/WorksExamine/WorksExamine_worksListThree',
					{
						'worksVO.works_passed' : 1,
						'worksVO.works_deleted' : 2,
						'worksVO.currPage' : 1
					},

					function(xhr) {
						var data_list = xhr.wlmtxt_worksList;
						var str = '';
						for (var len = 0; len < data_list.length; len++) {
							str += '<tr>';
							str += '<td>' + (len + 1) + '</td>';// 序号
							str += '<td>' + data_list[len].works_title
									+ '</td>';// 标题

							str += '<td>'
									+ '<input type="hidden" value="'
									+ data_list[len].works_id
									+ '" />'
									+ '<button type="button" style="margin-left:6px;" class="btn btn-primary btn-xs"><i class="fa fa-plus-square"></i>添加</button>'

							str += '</tr>';

						}
						// 加载案件列表到表格中
						$('.breakcase_table_info tbody').html(str); // 操作点击事件

						// -----------------------------------------------------
						// 设置确认、删除点击事件
						$('.btn-xs').click(modifi_delete);
						// -----------------------------------------------------

						// 分页信息存入page_infomantion中
						page_infomantion.currPage = xhr.currPage; // 当前页数
						page_infomantion.countRecords = xhr.totalPage; // 总页数
						page_infomantion.pageSize = xhr.pageSize; // 每页记录数
						page_infomantion.totalPages = xhr.totalCount; // 总记录数
						page_infomantion.havePrePage = xhr.havePrePage; // 是否有上一页
						page_infomantion.haveNexPage = xhr.haveNexPage; // 是否有下一页

						// 分页下的记录信息
						var opt = '<option value=""></option>';
						for (var index = 1; index <= xhr.totalPage; index++) {
							opt += '<option>' + index + '</option>';
						}
//						$('.carousel').html(
//								'共 ' + xhr.totalCount + '条信息 当前'
//										+ xhr.currPage + '/' + xhr.totalPage
//										+ '页 ' + xhr.pageSize
//										+ '条信息/页&nbsp&nbsp转到第'
//										+ '<select onchange="toPage(this)">'
//										+ opt + '</select> 页');
						// 影藏模态框
						// $('#newQuery').modal('hide')
					}, 'json')
}

// 首页
function firstPage() {
	if (page_infomantion.currPage == 1) {
		toastr.error('已经是第一页！');
		return;
	}
	query_data['worksVO.currPage'] = 1;
	get_ListBreakecaseInformationByPageAndSearch(query_data);
}
// 上一页
function prePage() {
	if (page_infomantion.currPage <= 1) {
		toastr.error('已经是第一页！');
		return;
	}
	query_data['worksVO.currPage'] = page_infomantion.currPage - 1;
	get_ListBreakecaseInformationByPageAndSearch(query_data);
}
// 下一页
function nextPage() {
	if (page_infomantion.currPage >= page_infomantion.totalPages) {
		toastr.error('已经是最后一页！');
		return;
	}
	query_data['worksVO.currPage'] = page_infomantion.currPage + 1;
	get_ListBreakecaseInformationByPageAndSearch(query_data);
}
// 尾页
function lastPage() {
	if (page_infomantion.currPage == page_infomantion.totalPages) {
		toastr.error('已经是最后一页！');
		return;
	}
	query_data['worksVO.currPage'] = page_infomantion.totalPages;
	get_ListBreakecaseInformationByPageAndSearch(query_data);
}
// 跳转到n页
function toPage(object) {
	query_data['worksVO.currPage'] = $(object).val();
	get_ListBreakecaseInformationByPageAndSearch(query_data);
}

function searchtitle(){
	$.post('/wlmtxt/WorksExamine/WorksExamine_worksListThree', {'worksVO.works_passed':1,'worksVO.works_deleted':2,'worksVO.works_title':$('#input_PoliceSearchText').val(),'worksVO.currPage' : 1 },
			function(xhr) {
				var data_list = xhr.wlmtxt_worksList;
				var str = '';
				for (var len = 0; len < data_list.length; len++) {
					str += '<tr>';
					str += '<td>' + (len + 1) + '</td>';// 序号
					str += '<td>' + data_list[len].works_title
							+ '</td>';// 标题
				
				
					
						str += '<td>'
								+ '<input type="hidden" value="'
								+ data_list[len].works_id
								+ '" />'
								+ '<button type="button" style="margin-left:6px;" class="btn btn-primary btn-xs"><i class="fa fa-plus-square"></i>添加</button>'
								+ '</td>';
					
						str += '</tr>';
					
				}
				// 加载案件列表到表格中
				$('.breakcase_table_info tbody').html(str); // 操作点击事件

				// -----------------------------------------------------
				// 设置确认、删除点击事件
				$('.btn-xs').click(modifi_delete);
				// -----------------------------------------------------

				// 分页信息存入page_infomantion中
				page_infomantion.currPage = xhr.currPage; // 当前页数
				page_infomantion.countRecords = xhr.totalPage; // 总页数
				page_infomantion.pageSize = xhr.pageSize; // 每页记录数
				page_infomantion.totalPages = xhr.totalCount; // 总记录数
				page_infomantion.havePrePage = xhr.havePrePage; // 是否有上一页
				page_infomantion.haveNexPage = xhr.haveNexPage; // 是否有下一页

				// 分页下的记录信息
				var opt = '<option value=""></option>';
				for (var index = 1; index <= xhr.totalPage; index++) {
					opt += '<option>' + index + '</option>';
				}
//				$('.carousel').html(
//						'共 ' + xhr.totalCount + '条信息 当前'
//								+ xhr.currPage + '/' + xhr.totalPage
//								+ '页 ' + xhr.pageSize
//								+ '条信息/页&nbsp&nbsp转到第'
//								+ '<select onchange="toPage(this)">'
//								+ opt + '</select> 页');
				// 影藏模态框
				// $('#newQuery').modal('hide')
		}, 'json')
	}

						// 分页信息存入page_infomantion中
						page_infomantion.currPage = xhr.currPage; // 当前页数
						page_infomantion.countRecords = xhr.totalPage; // 总页数
						page_infomantion.pageSize = xhr.pageSize; // 每页记录数
						page_infomantion.totalPages = xhr.totalCount; // 总记录数
						page_infomantion.havePrePage = xhr.havePrePage; // 是否有上一页
						page_infomantion.haveNexPage = xhr.haveNexPage; // 是否有下一页

						// 分页下的记录信息
						var opt = '<option value=""></option>';
						for (var index = 1; index <= xhr.totalPage; index++) {
							opt += '<option>' + index + '</option>';
						}
						$('.carousel').html(
								'共 ' + xhr.totalCount + '条信息 当前' + xhr.currPage
										+ '/' + xhr.totalPage + '页 '
										+ xhr.pageSize + '条信息/页&nbsp&nbsp转到第'
										+ '<select onchange="toPage(this)">'
										+ opt + '</select> 页');
						// 影藏模态框
						// $('#newQuery').modal('hide')
					}, 'json')
}

var modifi_delete = function() {
	var type = $(this).text().trim();
	var id = $(this).siblings('input').val();
	// dis_bnt(id);
	if (type == "添加") {
		var formData = new FormData();
		formData.append('recommend.recommend_works_id', id);
		$
				.confirm({
					title : '确定添加?',
					smoothContent : false,
					content : false,
					autoClose : 'cancelAction|10000',
					buttons : {
						deleteUser : {
							btnClass : 'btn-danger',
							text : '确认',
							action : function() {
								$
										.ajax({
											url : '/wlmtxt/WorksExamine/WorksExamine_addRecommend',
											type : 'post',
											data : formData,
											processData : false,
											contentType : false,
											dataType : 'text',
											success : function(data) {

												if (data == "1") {
													toastr.success("添加成功！");

													window.location.reload();

													// get_ListBreakecaseInformationByPageAndSearch(query_data);
												} else {
													toastr.error("轮播图已达上限！");
												}
											}
										});
							}
						},
						cancelAction : {
							btnClass : 'btn-blue',
							text : '取消',
						}
					}
				});
	}
}
function dis_bnt(id) {
	
	var formData = new FormData();
	formData.append('wlmtxt_work.works_id', id);
	$.ajax({
		url : '/wlmtxt/WorksExamine/WorksExamine_getworksById',
		type : 'post',
		data : formData,
		processData : false,
		contentType : false,
		dataType : 'text',
		success : function(data) {

			if (data == "2") {
				window.location.reload();
				// get_ListBreakecaseInformationByPageAndSearch(query_data);
			} else {
				return true;
			}
		}
	});
}