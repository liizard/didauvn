<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../inc/taglib.jsp"%>

<div id="welcome">
	<div id="divLogo">
		<img alt="" src="<spring:message code="domain"/>/img/res/wlogo.png" />
	</div>

	<div id="overview">
		<table border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="iconLine"><img
					src="<spring:message code="domain"/>/img/res/logo.png" /></td>
				<td class="iconLine"><a
					href="<spring:message code="domain"/>/goaround"> <img
						title="{{langCommon.goaround}}"
						src=" <spring:message code="domain"/>/img/res/icon/cat.png" />
				</a></td>
				<td class="iconLine"><a
					href="<spring:message code="domain"/>/search"><img
						title="{{langCommon.search}}"
						src=" <spring:message code="domain"/>/img/res/icon/search.png" />
				</a></td>
				<td class="iconLine"><a
					href="<spring:message code="domain"/>/user/#login"><img
						title="{{langCommon.login}}"
						src=" <spring:message code="domain"/>/img/res/icon/login.png" />
				</a></td>
				<td class="iconLine"><a
					href="<spring:message code="domain"/>/user/#register"><img
						title="{{langCommon.register}}"
						src=" <spring:message code="domain"/>/img/res/icon/signup.png" />
				</a></td>
			</tr>
			<tr>
				<td>Chao mung!<br> Ban dang vao trang mang dia diem Viet
					Nam<br> Kham pha ngay!
				<td>Ban phan van khong biet di dau choi. Hay vao day.</td>
				<td>Tim kiem dia diem, thong tin cac quan an, cua hang, khu vui
					choi, giai tri, ..., nhanh va chinh xac nhat.</td>
				<td>Dang nhap de co the ghi nho cac dia diem yeu thich, danh
					gia, binh luan, ru re ban be...</td>
				<td>Ban chua co tai khoan? Dang ky tai day.</td>
			</tr>
		</table>
	</div>
</div>