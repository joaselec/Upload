<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>




<html>
<head>
<%@ include file="../include/header.jsp" %>
<!-- <%@ include file="../include/sessionCheck.jsp" %>  -->

	<link rel="stylesheet" type="text/css" media="screen" href="resources/css/jquery-ui.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="resources/css/ui.jqgrid.css" />
   																
    <!-- <script type="text/javascript" src="/js/jquery-3.2.1.js"></script>  --> 
    <script type="text/javascript" src="resources/js/i18n/grid.locale-kr.js"></script>
    <script type="text/javascript" src="resources/js/jquery.jqGrid.min.js"></script>

<script>
	$(document).ready(function() {
		jqgridTable.init();
		jqgridTable.search();
		jqgridTable.navGrid();
	});

	var jqgridTable = 
	{
    	init : function(){
		    var cnames = ['번호','타이틀','내용','입력일','조회수','작성자'];
		    $jqGrid = $("#jqGrid");
		 
		    $jqGrid.jqGrid({
		        
		        url: "list1",
		        datatype: "local", //이벤트가 발생되지 않으면 실행하지 않음
		        colNames: cnames,
		        colModel:[
		                  
		                  {name:'bno',index:'bon', width:55, key:true, align:"center"},
		                  {name:'title',index:'title', width:100, align:"center"},
		                  {name:'content',index:'content', width:100},
		                  {name:'regdate',index:'regdate', width:100},
		                  {name:'viewcnt',index:'viewcnt', width:100},
		                  {name:'username',index:'username', width:100}
		                  
		                  ],
		                  
		        height: 480,
		        rowNum: 10,
		        rowList: [10,20,30],
		        pager: '#jqGridPager',
		        rownumbers  : true,                  
		        viewrecords : true,
		        caption:"게시판 리스트"
		        });
    	},
    	
    	search : function () {
			$("#jqGrid").setGridParam({
				datatype		: "json",
			}).trigger("reloadGrid");
		},
    	
    	navGrid : function() {
			$("#jqGrid").jqGrid('navGrid', '#jqGridPager',

				{
					edit:false ,add:false ,del:true ,search:true ,refresh:true
				},
				{
					// 삭제 옵셥
				},			
				{
					// 찾기 옵션	 
				});
		}
		
		
    }

</script>

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
jqgrid Test
 
<div class="row"> 
    <div>
        <table id="jqGrid"></table>
        <div id="jqGridPager"></div>
    </div>
</div>
</body>

</html>