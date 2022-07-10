<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>


</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${authUser.id}/admin/writeForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->

			<div id="admin-content">

				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>카테고리명</th>
							<th>포스트 수</th>
							<th>설명</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody id="cateList">
						<!-- 리스트 영역 -->
						<!-- 리스트 영역 -->
					</tbody>
				</table>

				<table id="admin-cate-add">
					<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
					<tr>
						<td class="t">카테고리명</td>
						<td>
							<input type="text" id = "cate-cateName" name="name" value="">
						</td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td>
							<input type="text" id="cate-description" name="desc">
						</td>
					</tr>
				</table>

				<div id="btnArea">
					<input type="hidden" id="cate-id" name="id" value="${bMap.blogVo.id }">
					<button id="btnAddCate" class="btn_l" type="submit">카테고리추가</button>
				</div>

			</div>
			<!-- //admin-content -->
		</div>
		<!-- //content -->


		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>



	</div>
	<!-- //wrap -->
</body>
<script type="text/javascript">
$(document).ready(function() {
	console.log("카테고리");
	
	fetchList();
	
});

	//카테고리명, 설명 추가하기!
	$("#btnAddCate")
			.on(
					"click",
					function() {
						console.log("카테고리추가버튼")

						var categoryVo = {
							id : $("#cate-id").val(),
							cateName : $("#cate-cateName").val(),
							description : $("#cate-description").val()
						};
						console.log(categoryVo);

						//데이터 ajax방식으로 서버에 전송	
						$
								.ajax({
									url : "${pageContext.request.contextPath }/${id}/admin/category/write",
									type : "post",
									// 		contentType : "application/json",
									data : categoryVo,
									//			dataType : "json",
									success : function(resultCateVo) {
										/*성공시 처리해야될 코드 작성*/
										console.log(resultCateVo);
										render(resultCateVo, "up");

										//초기화
										$("#cate-cateName").val("");
										$("#cate-description").val("");

									},
									error : function(XHR, status, error) {
										console.error(status + " : " + error);
									}
								});

					});

	//리스트
	function fetchList() {
		var id = "${blogVo.id}"

		//ajax 요청하기
		$
				.ajax({

					url : "${pageContext.request.contextPath }/${id}/admin/category/list",
					type : "post",
					//contentType : "application/json",
					//				data : {id: id},
					//				dataType : "json",					
					success : function(cateList) {
						/*성공시 처리해야될 코드 작성*/
						console.log(cateList);

						for (var i = 0; i < cateList.length; i++) {
							render(cateList[i], "down");
						}

					},
					error : function(XHR, status, error) {
						console.error(status + " : " + error);
					}
				});

	};

	//카테고리 1개씩 렌더링
	function render(categoryVo, type) {

		var str = "";

		str += '<tr id=t-' + categoryVo.cateNo + '>';
		str += '	<td>' + categoryVo.cateNo + '</td>';
		str += '	<td>' + categoryVo.cateName + '</td>';
		str += '	<td>' + categoryVo.pCount + '</td>';
		str += '	<td>' + categoryVo.description + '</td>';
		str += '    <td class="text-center">';
		str += '    	<img class="btnCateDel" data-no="' + categoryVo.cateNo + '"src="${pageContext.request.contextPath}/assets/images/delete.jpg">';
		str += '    </td>';
		str += '</tr>';

		if (type === 'down') {

			$("#cateList").append(str);

		} else if (type === 'up') {
			$("#cateList").prepend(str);
		}

	};
	
	//카테고리 리스트에서 삭제버튼 클릭
 	$("#cateList").on("click", ".btnCateDel", function () {
		console.log("카테고리 삭제버튼클릭");
 		
		var cateNo = $(this).data("no");
		console.log(cateNo);
		
		//서버에 삭제요청
		$.ajax({
				
			url : "${pageContext.request.contextPath }/${id}/admin/category/remove",		
			type : "post",
			//contentType : "application/json",
			data : {cateNo: cateNo},
			dataType : "json",
			success : function(result){
				/*성공시 처리해야될 코드 작성*/
				
				if(result == false) {
					alert("삭제할 수 없습니다.")
				} else if(result == true) {
					
					$("#t-" + cateNo).remove();
				}
				
				
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
			
		});
		
	});
</script>
</html>