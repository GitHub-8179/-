formValidator();
	
function initFunction(){
	
	$("#docDateTable").bootstrapTable({
		method: 'post',
	    contentType: "application/x-www-form-urlencoded",//当请求方法为post的时候,必须要有！！！！
		url: 'borrowCard/selectByExample.do',   
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
	    	 {title : '主键',field : 'cardId',align : 'center'},
	    	 {title : '用户名称',field : 'username',align : 'center'},
	    	 {title : '性别',field : 'gender',align : 'center',formatter:function (value, row, index) {return setGender(value, row, index)}},
	    	 {title : '手机号',field : 'phone',align : 'center'},
	    	 {title : 'E-mail',field : 'email',align : 'center'},
	    	 {title : '等级',field : 'rank',align : 'center',formatter:function (value, row, index) {return setRank(value, row, index)}},
	    	 {title : "失效时间" , field : "failTime",align : "center" ,visible:false },
	    	 {title : "单位" , field : "company",align : "center"}
	    	// events: operateEvents,
            // formatter: operateFormatter
        ]
	}); 
	
}

//查询按钮事件
$('#searchBtn').click(function(){refreshTable()})

function refreshTable(){
	$('#docDateTable').bootstrapTable('refresh', {url: 'borrowCard/selectByExample.do'});
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
            		ids+=selrows[i].cardId+"-";
            	}
            	$.ajax({
            	  	url:"borrowCard/delManageInfoByKey/"+ids+".do",
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

	$("#myModal5 #signupForm #cardId" ).val(data.cardId);
	$("#myModal5 #signupForm #username" ).val(data.username);
//	$("#myModal5 #signupForm #gender" ).val(data.gender);
	var genders = $('#myModal5 #signupForm input[type=radio][name=gender]');
	for ( var i in genders) {
		if(genders[i].value==data.gender){
			genders[i].checked=true;
			break;
		}
	}
	
	
	$("#myModal5 #signupForm #company" ).val(data.company);
	$("#myModal5 #signupForm #phone" ).val(data.phone);
	$("#myModal5 #signupForm #email" ).val(data.email);
	
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
	  	url:"borrowCard/saveOrUpdate.do",
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


function setGender(value, row, index){
	return value=="0"?"女":"男";
}

function setRank(value, row, index){
	return "VIP "+value;
}

$('#add1').click(function () {
	$('#myModal5').modal('hide');
});


function ajaxPost(url,data){

	 $.ajax({
		  	url:url,
		  	type:"post",
		  	data:data,
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
//		             title: "太帅了",
	             text: "信息提交成功！",
	             type: "success"
	         });
	  		 refreshTable();
		  	}
		}); 
}
function formValidator(){

$("#signupForm").bootstrapValidator({
//    live: 'disabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
//    excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的
//    submitButtons: '#btn-test',//指定提交按钮，如果验证失败则变成disabled，但我没试成功，反而加了这句话非submit按钮也会提交到action指定页面
    message: '通用的验证失败消息',//好像从来没出现过
    feedbackIcons: {//根据验证结果显示的各种图标
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {
    	username: {
            validators: {
                notEmpty: {message: '用户名必须输入'},
                stringLength: {min: 4,max: 16,message: '长度必须在4-16之间'}
//                regexp: {regexp: /^[a-zA-Z0-9_\.]+$/,message: '所输入的字符不符要求'},
//                remote: {url: '指定页面',message: 'The username is not available'},
//                different: {field: '指定文本框name',message: '不能与指定文本框内容相同'},
//                emailAddress: {message: '不是正确的email地址'},
//                identical: {field: 'confirmPassword',message: '输入的内容不一致'},
//                date: {format: 'YYYY/MM/DD',message: '日期格式不正确'},
//                choice: { min: 2,max: 4,message: '必须选择2-4个选项'}
            }
        },
        gender: {
            validators: {
                notEmpty: {message: '性别不可为空！'},
                choice: { min: 1,max: 1,message: '性别只能选择一个'}
            }
        },
        company: {
            validators: {
                stringLength: {min: 0,max: 32,message: '单位长度应为0——32位！'},
            }
        },
        phone: {
            validators: {
                notEmpty: {message: '手机号不可为空！'},
                stringLength: {min: 11,max: 11,message: '手机号应为11位！'},
                regexp:{
                    regexp:/^1[3|5|8]{1}[0-9]{9}$/,
                    message:'请输入正确的手机号码'
                }
//                phone:{message: '请正确输入您的手机号！'}
            }
        },
        email: {
            validators: {
                stringLength: {min: 5,max: 16,message: '请正确输入您的邮箱！'},
                emailAddress:{message: '请正确输入您的邮箱！'}
            }
        }
    },
    submitHandler: function (validator, form, submitButton) {
        alert("submit");
    }
});
}
