
	
function initFunction(){
	
	laydate.render({
        //event: 'focus' //响应事件。如果没有传入event，则按照默认的click
 	   elem: '#data', //需显示日期的元素选择器
 	   event: 'click', //触发事件
 	   format: 'yyyy-MM-dd', //日期格式
 	   showBottom: false, //显示底部栏
 	   istime: false, //是否开启时间选择
 	   isclear: false, //是否显示清空
 	   istoday: false, //是否显示今天
 	   issure: false,  //是否显示确认
 	   festival: false, //是否显示节日,
 	   calendar: true,//显示公历节日
 	   min: '2010-01-01 00:00:00', //最小日期
 	   max: '2099-12-31 23:59:59', //最大日期
 	   start: '2014-6-15 23:00:00',    //开始日期
 	   value: new Date(), //初始值
 	   isInitValue: true ,//是否允许填充初始值，默认为 true
 	   fixed: true, //是否固定在可视区域
 	   zIndex: 99999999 //css z-index
  });
	
	$("#docDateTable").bootstrapTable({
		method: 'post',
	    contentType: "application/x-www-form-urlencoded",//当请求方法为post的时候,必须要有！！！！
		url: 'borrowRecord/selectByExample.do',   
	    search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
	    showRefresh: false,				
//	    showColumns:true,
	    showToggle: false,				//是否显示详细视图和列表视图的切换按钮
	    showColumns: false,				//是否显示所有的列
	    //toolbar: '#add',                //工具按钮用哪个容器
	    pagination: true,				//是否显示分页（*）
	    sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
	    pageNumber:1,                       //初始化加载第一页，默认第一页
	    pageSize: 10,                       //每页的记录行数（*）
	    pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
	    sortable: true,                     //是否启用排序
	    sortOrder: "asc",                   //排序方式
	    clickToSelect: true,                //是否启用点击选中行
	    uniqueId: "cardId",
	    //showpaginationswitch:true,//是否显示 数据条数选择框
	    height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
	  //得到查询的参数
	    queryParams : function (params) {
	        //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	        var temp = {   
	            rows: params.limit,                         //页面大小
	            page: (params.offset / params.limit) + 1,   //页码
	            sort: params.sort,      //排序列名  
	            sortOrder: params.order //排位命令（desc，asc） 
	        };
//	        JSON.stringify(temp);
	        return $("#cxtj #searchForm").serialize()+"&"+jQuery.param(temp);
//	        return jQuery.param(temp);
	    },
	    responseHandler: function(res) {
	    	//console.log(res);
	        return {
	            "total": res.extend.date.total,//总页数
	            "rows": res.extend.date.list   //数据
	         };
	    },
	    columns: [
	    	 {title : '序号',field : 'did',align : 'center',checkbox : true, width : 10},
//	    	 {title : '主键',field : 'bookId',align : 'center'},
	    	 {title : '借阅时间',field : 'data',align : 'center',formatter:function (value, row, index) {return setDate(value, row, index)}},
	    	 {title : "借阅图书id" , field : "bookId",align : "center" },
	    	 {title : "借阅图书编号" , field : "bookCode",align : "center" },
	    	 {title : "借阅图书名称" , field : "bookName",align : "center",formatter:function (value, row, index) {return setBookId(value, row, index)}},
	    	 {title : '借阅人ID',field : 'cardId',align : 'center' },
	    	 {title : '借阅人名称',field : 'userName',align : 'center' ,formatter:function (value, row, index) {return setCardId(value, row, index)}},
	    	 {title : '操作人ID',field : 'adminId',align : 'center'},
	    	 {title : '操作人名称',field : 'adminName',align : 'center',formatter:function (value, row, index) {return setAdminId(value, row, index)}},
	    	 {title : "借阅状态" , field : "recordType",align : "center" ,formatter:function (value, row, index) {return setRecordType(value, row, index)} }
	    	// events: operateEvents,
            // formatter: operateFormatter
	    	
        ]
	}); 
	
}

//查询按钮事件
$('#searchBtn').click(function(){refreshTable()})

function refreshTable(){
	$('#docDateTable').bootstrapTable('refresh', {url: 'borrowRecord/selectByExample.do',silent: true});
}

function delInfo(){
	var selrows=$('#docDateTable').bootstrapTable('getSelections');
	   
	if(selrows.length>0){
		swal({
            title: "您确定要删除这条信息吗",
            text: "删除后将无法恢复，请谨慎操作！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "是的，我要删除！",
            cancelButtonText: "让我再考虑一下…",
            closeOnConfirm: true,
            closeOnCancel: true
        },
        function (isConfirm) {
            if (isConfirm) {
               // swal("删除成功！", "您已经永久删除了这条信息。", "success");
                var ids="";
            	for ( var i in selrows) {
            		ids+=selrows[i].bookId+"-";
            	}
            	$.ajax({
            	  	url:"borrowRecord/delManageInfoByKey/"+ids+".do",
            	  	type:"post",
            	  	data:{_method:"DELETE",ids:ids},
//            	  	type:"DELETE",
//            	  	data:{ids:ids},
            	  	dataType: "json", 
            	  	error: function(result){ //失败 
              		 swal({
                         //title: "太帅了",
                         text: "信息提交失败！",
                         type: "error"
                     });
            	  	}, 
            	  	success:function(result){
            	  		swal({title: "提示",text: "删除数据成功"});
            	  		refreshTable();
            	  	}
            	}); 
            } else {
            }
        });
	}else{
		swal({title: "提示",text: "请选择要删除的数据"});
	}
	
	
}
function updateInfo(){
	var selrow=$('#docDateTable').bootstrapTable('getSelections');
	if(selrow.length!=1){
		swal({
            title: "提示",
            text: "请选择一条数据进行修改"
        });
		return ;
	}
//	$('#myModal5').modal('show',selrow[0]);
	var data = selrow[0];
	$('#myModal5').modal('show');

	$("#myModal5 #saveOrUpdateTest" ).text("用户信息修改");

	$("#myModal5 #signupForm #bookId" ).val(data.bookId);
	$("#myModal5 #signupForm #bookType" ).val(data.bookType);
	$("#myModal5 #signupForm #bookName" ).val(data.bookName);
	$("#myModal5 #signupForm #publisher" ).val(data.publisher);
	$("#myModal5 #signupForm #publishYear" ).val(data.publishYear);
	$("#myModal5 #signupForm #author" ).val(data.author);
	$("#myModal5 #signupForm #price" ).val(data.price);
	$("#myModal5 #signupForm #code" ).val(data.code);
	var genders = $('#myModal5 #signupForm input[type=radio][name=status]');
	for ( var i in genders) {
		if(genders[i].value==data.gender){
			genders[i].checked=true;
			break;
		}
	}
}

$('#showAdminInfo').on('show.bs.modal', function (e) {
	$("#adminForm").get(0).reset();
})

$('#showCardIdInfo').on('show.bs.modal', function (e) {
	$("#cardForm").get(0).reset();
})

$('#myModal5').on('show.bs.modal', function (e) {
	$("#signupForm").get(0).reset();
})

function saveUpdate(){
	$.ajax({
	  	url:"borrowRecord/saveOrUpdate.do",
	  	type:"post",
	  	data:$("#myModal5 #signupForm").serialize(),
	  	dataType: "json", 
	  	error: function(result){ //失败 
  		 swal({
             //title: "太帅了",
             text: "信息提交失败！",
             type: "error"
         });
	  	}, 
	  	success:function(result){
	  	 $('#myModal5').modal('hide');
  		 swal({
//	             title: "太帅了",
             text: "信息提交成功！",
             type: "success"
         });
  		refreshTable();
	  	}
	}); 
}




function setDate(value, row, index){
	 var dateVal = value + "";
    if (value != null) {
        var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
//        var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
//        var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
//        var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
//        return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;

        return date.getFullYear() + "-" + month + "-" + currentDate;

    }
//	return value;
}

function setRecordType(value, row, index){
	return value=="0"?"借阅":"返还";
}
function setCardId(value, row, index){
//    var url = '<a herf="/order/get_order?id='+ value +'" target="_blank" class="">' + value +'</a>';

	return [
        '<button class="btn btn-info btn-sm rightSize detailBtn" type="button" onclick="showCardId('+row.cardId+')"><i class="fa fa-paste"></i> '+value+'</button>'
//        '<button class="btn btn-danger btn-sm rightSize packageBtn" type="button"><i class="fa fa-envelope"></i> 通知</button>'
    ].join('');
}

function setAdminId(value, row, index){
	return [
        '<button class="btn btn-info btn-sm rightSize detailBtn" type="button"  onclick="showAdminId('+row.adminId+')"><i class="fa fa-paste"></i> '+value+'</button>'
    ].join('');	
}
function setBookId(value, row, index){
	return [
        '<button class="btn btn-info btn-sm rightSize detailBtn" type="button"  onclick="showBookId('+row.bookId+')"> <i class="fa fa-paste"></i> '+value+'</button>'
    ].join('');	
}


function showCardId(cardId){
	$.ajax({
	  	url:"borrowCard/selectByPrimaryKey.do",
	  	type:"post",
	  	data:{cardId:cardId},
	  	dataType: "json", 
	  	error: function(result){ //失败 
  		 swal({text: "详情信息查询失败！",type: "error"});
	  	}, 
	  	success:function(result){
	  		var data = result.extend.data;
	  		$('#showCardIdInfo').modal('show');
	  		
	  		$("#showCardIdInfo #cardForm #cardId" ).val(data.cardId);
	  		$("#showCardIdInfo #cardForm #username" ).val(data.username);
	  		var genders = $('#showCardIdInfo #cardForm input[type=radio][name=gender]');
	  		for ( var i in genders) {
	  			if(genders[i].value==data.gender){
	  				genders[i].checked=true;
	  				break;
	  			}
	  		}
	  		$("#showCardIdInfo #cardForm #company" ).val(data.company);
	  		$("#showCardIdInfo #cardForm #phone" ).val(data.phone);
	  		$("#showCardIdInfo #cardForm #email" ).val(data.email);

	  	}
	}); 

	
}
function showAdminId(adminId){
	$.ajax({
	  	url:"manage/selectByPrimaryKey.do",
	  	type:"post",
	  	data:{adminId:adminId},
	  	dataType: "json", 
	  	error: function(result){ //失败 
  		 swal({text: "详情信息查询失败！",type: "error"});
	  	}, 
	  	success:function(result){
	  		var data = result.extend.data;
	  		$('#showAdminInfo').modal('show');
	  		
	  		$("#showAdminInfo #adminForm #fadminId" ).val(data.adminId);
	  		$("#showAdminInfo #adminForm #fname" ).val(data.name);
	  		$("#showAdminInfo #adminForm #fphone" ).val(data.phone);
	  		var genders = $('#showAdminInfo #adminForm input[type=radio][name=gender]');
	  		for ( var i in genders) {
	  			if(genders[i].value==data.gender){
	  				genders[i].checked=true;
	  				break;
	  			}
	  		}
	  		var isValids = $('#showAdminInfo #adminForm input[type=radio][name=isValid]');
	  		for ( var i in isValids) {
	  			if(isValids[i].value==data.isValid){
	  				isValids[i].checked=true;
	  				break;
	  			}
	  		}
	  		$("#showAdminInfo #adminForm #femail" ).val(data.email);
	  		$("#showAdminInfo #adminForm #feffectTime" ).val(data.effectTime);
	  		$("#showAdminInfo #adminForm #ffailTime" ).val(data.failTime);
	  		$("#showAdminInfo #adminForm #fdepartment" ).val(data.department);

	  	}
	}); 
}
function showBookId(bookId){
	$.ajax({
	  	url:"bookInfo/selectByPrimaryKey.do",
	  	type:"post",
	  	data:{bookId:bookId},
	  	dataType: "json", 
	  	error: function(result){ //失败 
  		 swal({text: "详情信息查询失败！",type: "error"});
	  	}, 
	  	success:function(result){
	  		var data = result.extend.data;
	  		$('#myModal5').modal('show');
	  		
	  		$("#myModal5 #signupForm #bookId" ).val(data.bookId);
	  		$("#myModal5 #signupForm #bookType" ).val(data.bookType);
	  		$("#myModal5 #signupForm #fphone" ).val(data.phone);
	  		var genders = $('#myModal5 #signupForm input[type=radio][name=status]');
	  		for ( var i in genders) {
	  			if(genders[i].value==data.status){
	  				genders[i].checked=true;
	  				break;
	  			}
	  		}
	  		$("#myModal5 #signupForm #bookName" ).val(data.bookName);
	  		$("#myModal5 #signupForm #code" ).val(data.code);
	  		$("#myModal5 #signupForm #author" ).val(data.author);
	  		$("#myModal5 #signupForm #price" ).val(data.price);
	  		$("#myModal5 #signupForm #publisher" ).val(data.publisher);
	  		$("#myModal5 #signupForm #publishYear" ).val(data.publishYear);

	  	}
	}); 
}