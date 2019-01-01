formValidator();
	
function initFunction(){
	
	$("#docDateTable").bootstrapTable({
		method: 'post',
	    contentType: "application/x-www-form-urlencoded",//当请求方法为post的时候,必须要有！！！！
		url: 'bookInfo/selectByExample.do',   
	    search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
	    showRefresh: false,				
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
	        return $("#cxtj #searchForm").serialize()+"&"+jQuery.param(temp);
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
	    	 {title : '主键',field : 'bookId',align : 'center'},
	    	 {title : '图书类型',field : 'bookType',align : 'center'},
	    	 {title : '书名',field : 'bookName',align : 'center'},
	    	 {title : "编码" , field : "code",align : "center" },
	    	 {title : '作者',field : 'author',align : 'center'},
	    	 {title : "价格" , field : "price",align : "center" ,formatter:function (value, row, index) {return setPrice(value, row, index)} },
	    	 {title : '出版社',field : 'publisher',align : 'center'},
	    	 {title : '出版时间',field : 'publishYear',align : 'center'},
	    	 {title : "状态" , field : "status",align : "center" ,formatter:function (value, row, index) {return setStatus(value, row, index)} }
	    	// events: operateEvents,
            // formatter: operateFormatter
	    	
        ]
	}); 
	
}

//查询按钮事件
$('#searchBtn').click(function(){refreshTable()})

function refreshTable(){
	$('#docDateTable').bootstrapTable('refresh', {url: 'bookInfo/selectByExample.do'});
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
            	  	url:"bookInfo/delManageInfoByKey/"+ids+".do",
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
		if(genders[i].value==data.status){
			genders[i].checked=true;
			break;
		}
	}
	 setTimeout(function(){ $("#signupForm").bootstrapValidator('validate');} ,200);//开启执行一次

}

$('#myModal5').on('show.bs.modal', function (e) {
	$("#signupForm").get(0).reset();
	$("#myModal5 #saveOrUpdateTest" ).text("用户信息新增");
	
	$("#signupForm").data('bootstrapValidator').destroy();
    $('#signupForm').data('bootstrapValidator', null);
    formValidator();
})

function saveUpdate(){
	$("#signupForm").bootstrapValidator('validate');//提交验证
	if(!$("#signupForm").data('bootstrapValidator').isValid()){return ;};
	
	$.ajax({
	  	url:"bookInfo/saveOrUpdate.do",
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


function setPrice(value, row, index){
	return "￥"+value;
}

function setStatus(value, row, index){
	return value==0?"下架":"在架";
}


function formValidator(){

	$("#signupForm").bootstrapValidator({
//	    live: 'disabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
//	    excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的
//	    submitButtons: '#btn-test',//指定提交按钮，如果验证失败则变成disabled，但我没试成功，反而加了这句话非submit按钮也会提交到action指定页面
	    message: '通用的验证失败消息',//好像从来没出现过
	    feedbackIcons: {//根据验证结果显示的各种图标
	        valid: 'glyphicon glyphicon-ok',
	        invalid: 'glyphicon glyphicon-remove',
	        validating: 'glyphicon glyphicon-refresh'
	    },
	    fields: {
	    	bookType: {
	            validators: {
	                notEmpty: {message: '图书类型必须输入'},
	                stringLength: {min: 0,max: 16,message: '图书类型长度必须在0-16之间'}
	            }
	        },
	        status: {
	            validators: {
	                notEmpty: {message: '状态不可为空！'},
	                choice: { min: 1,max: 1,message: '状态只能选择一个'}
	            }
	        },
	        bookName: {
	            validators: {
	                notEmpty: {message: '图书名称不可为空！'},
	                stringLength: {min: 0,max: 32,message: '图书名称长度为0——32位！'},
	            }
	        },
	        code: {
	            validators: {
	                notEmpty: {message: '图书编码不可为空！'},
	                stringLength: {min: 0,max: 16,message: '图书编码长度为0——16位！'}
	            }
	        },
	        author: {
	            validators: {
	                stringLength: {min: 0,max: 16,message: '作者长度为0——16位！'}
	            }
	        },
	        price: {
	            validators: {
	                notEmpty: {message: '价格不可为空！'},
	                numeric:{message: '价格应为数字！'},
	                stringLength: {min: 0,max: 16,message: '价格长度为0——16位！'}
	            }
	        },
	        publisher: {
	            validators: {
	                stringLength: {min: 0,max: 32,message: '出版社长度为0——32位！'}
	            }
	        },
	        publishYear: {
	            validators: {
	                stringLength: {min: 0,max: 8,message: '出版社长度为0——8位！'}
//	                date: {format: 'YYYY/MM/DD',message: '日期格式不正确'}
	            }
	        }
	    },
	    submitHandler: function (validator, form, submitButton) {
	        alert("submit");
	    }
	});
	}


var effectTime = laydate.render({
    //event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	   elem: '#publishYear', //需显示日期的元素选择器
	   event: 'click', //触发事件
//	   type: 'time',
//	   type: 'date',
	   type: 'year',
//	   type: 'month',
	   format: 'yyyy', //日期格式
	   showBottom: true, //显示底部栏
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
	   zIndex: 99999999 ,//css z-index
	   done: function(value, date, endDate){
			 setTimeout(function(){
				 $('#signupForm').data('bootstrapValidator').updateStatus('publishYear', 'NOT_VALIDATED',null)
			      .validateField('publishYear');				 
			 } ,100);//开启执行一次
		},
		ready:function(date){
	    },
		change: function(value, date, endDate){
		}
});
