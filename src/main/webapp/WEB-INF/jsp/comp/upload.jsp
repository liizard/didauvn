<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../inc/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>didau.vn</title>
<!-- Bootstrap CSS fixes for IE6 -->
<!--[if lt IE 7]><link rel="stylesheet" href="<spring:message code="domain"/>/css/lib/bootstrap-ie6.min.css"><![endif]-->
<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
<link rel="stylesheet"
	href="<spring:message code="domain"/>/css/lib/jquery.fileupload-ui.css">
<link rel="stylesheet" type="text/css"
	href="<spring:message code="domain"/>/css/common.css" />

</head>
<body>
	<input id="placeId" type="hidden" value="${placeId}" />
	<div id="divGallery">
		<form id="fileupload"
			action="<spring:message code="domain"/>/data/image/upimg/${placeId}"
			method="POST" enctype="multipart/form-data">
			<!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
			<div class="row fileupload-buttonbar">
				<div class="span7">
					<!-- The fileinput-button span is used to style the file input field as button -->
					<span class="btn btn-success fileinput-button"> <i
						class="icon-plus icon-white"></i> <span>Add files...</span> <input
						type="file" name="file" multiple> </span>
					<button type="submit" class="btn btn-primary start">
						<i class="icon-upload icon-white"></i> <span>Start upload</span>
					</button>
					<button type="reset" class="btn btn-warning cancel">
						<i class="icon-ban-circle icon-white"></i> <span>Cancel
							upload</span>
					</button>
				</div>
				<!-- The global progress information -->
				<div class="span5 fileupload-progress fade">
					<!-- The global progress bar -->
					<div class="progress progress-success progress-striped active">
						<div class="bar" style="width: 0%;"></div>
					</div>
					<!-- The extended global progress information -->
					<div class="progress-extended">&nbsp;</div>
				</div>
			</div>
			<!-- The loading indicator is shown during file processing -->
			<div class="fileupload-loading"></div>
			<br>
			<!-- The table listing the files available for upload/download -->
			<table class="table table-striped">
				<tbody class="files" data-toggle="modal-gallery"
					data-target="#modal-gallery"></tbody>
			</table>
		</form>
	</div>
	<!-- The template to display files available for upload -->
	<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td class="preview"><span class="fade"></span></td>
        <td class="name"><span>{%=file.name%}</span></td>
		<td class="title"><label>Caption: <input name="caption" required></label></td>
        <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
        {% if (file.error) { %}
            <td class="error" colspan="2"><span class="label label-important">{%=locale.fileupload.error%}</span> {%=locale.fileupload.errors[file.error] || file.error%}</td>
        {% } else if (o.files.valid && !i) { %}
            <td>
                <div class="progress progress-success progress-striped active"><div class="bar" style="width:0%;"></div></div>
            </td>
            <td class="start">{% if (!o.options.autoUpload) { %}
                <button class="btn btn-primary">
                    <i class="icon-upload icon-white"></i>
                    <span>{%=locale.fileupload.start%}</span>
                </button>
            {% } %}</td>
        {% } else { %}
            <td colspan="2"></td>
        {% } %}
        <td class="cancel">{% if (!i) { %}
            <button class="btn btn-warning">
                <i class="icon-ban-circle icon-white"></i>
                <span>{%=locale.fileupload.cancel%}</span>
            </button>
        {% } %}</td>
    </tr>
{% } %}
</script>
	<!-- The template to display files available for download -->
	<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        {% if (file.error) { %}
            <td></td>
            <td class="name"><span>{%=file.name%}</span></td>
            <td class="error" colspan="2"><span class="label label-important">{%=locale.fileupload.error%}</span> {%=locale.fileupload.errors[file.error] || file.error%}</td>
        {% } %}
            
{% } %}
</script>

	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/jquery.min.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/config.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/common.js"></script>
	<script type="text/javascript"
		src="<spring:message code="domain"/>/js/lib/fileupload/vendor/jquery.ui.widget.js"></script>
	<script
		src="<spring:message code="domain"/>/js/lib/fileupload/tmpl.min.js"></script>
	<script
		src="<spring:message code="domain"/>/js/lib/fileupload/load-image.min.js"></script>
	<script
		src="<spring:message code="domain"/>/js/lib/fileupload/canvas-to-blob.min.js"></script>
	<script
		src="<spring:message code="domain"/>/js/lib/fileupload/jquery.iframe-transport.js"></script>
	<script
		src="<spring:message code="domain"/>/js/lib/fileupload/jquery.fileupload.js"></script>
	<script
		src="<spring:message code="domain"/>/js/lib/fileupload/jquery.fileupload-fp.js"></script>
	<script
		src="<spring:message code="domain"/>/js/lib/fileupload/jquery.fileupload-ui.js"></script>
	<script
		src="<spring:message code="domain"/>/js/lib/fileupload/locale.js"></script>
	<!--[if gte IE 8]><script src="<spring:message code="domain"/>/js/lib/fileupload/cors/jquery.xdr-transport.js"></script><![endif]-->
	<script type="text/javascript">
		var RunCallbackFunction = function(json) {
		};
		$(function() {
			'use strict';
			$('#fileupload').fileupload();

			$('#fileupload').fileupload(
					'option',
					'redirect',
					window.location.href.replace(/\/[^\/]*$/,
							'/cors/result.html?%s'));

			$('#fileupload').fileupload('option', {
				url : DOMAIN + '/data/image/upimg/' + $('#placeId').val(),
				maxFileSize : 5000000,
				acceptFileTypes : /(\.|\/)(gif|jpe?g|png)$/i,
				process : [ {
					action : 'load',
					fileTypes : /^image\/(gif|jpeg|png)$/,
					maxFileSize : 20000000
				}, {
					action : 'resize',
					maxWidth : 1024,
					maxHeight : 768
				}, {
					action : 'save'
				} ]
			});

			$('#fileupload').bind('fileuploadsubmit', function(e, data) {
				var inputs = data.context.find(':input');
				data.formData = inputs.serializeArray();
				data.formData.push({
					"name" : "placeId",
					"value" : $('#placeId').val(),
				});
			});

			$('#fileupload').bind('fileuploadcompleted', function(e, data) {
				data.context.each(function(index) {
					var file = ($.isArray(data.result) && data.result[index]);
					RunCallbackFunction(file);
				});
			});
		});
	</script>
</body>
</html>
