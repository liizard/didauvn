<%@ include file="../inc/taglib.jsp"%>
<div id="divImageUpdate" ng-controller="ImageUpdateCtrl">
	<table class="modify">
		<tr>
			<th colspan="2">{{langPlace.gallery}}&nbsp;|&nbsp;<a
				class="action"
				href="<spring:message code="domain"/>/place/#/update/general/{{placeId}}">{{langPlace.generalInfo}}</a>&nbsp;|&nbsp;
				<a class="action"
				href="<spring:message code="domain"/>/place/#/update/additional/{{placeId}}">{{langPlace.additionalInfo}}</a>&nbsp;|&nbsp;
				<a class="action"
				href="<spring:message code="domain"/>/place/#/update/video/{{placeId}}">{{langPlace.video}}</a>
			</th>
		</tr>
		<tr>
			<td class="title" colspan="3"><a class="action"
				ng-click="popup()">+{{langPlace.upload}}</a>&nbsp;&nbsp;<input
				type="button" class="button" ng-click="goPlace();"
				value="{{langCommon.cancel}} & {{langCommon.back}}" />
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<div class="paging" ng-show="pageList.length>1">
					{{langCommon.page}}:&nbsp; <a ng-repeat="page in pageList"
						class="pageSelected-{{page==selectedPage}}"
						ng-click="getImage(page)">{{page}}&nbsp;</a>
				</div>
			</td>
			</td>
		</tr>
		<tr ng-repeat="imageView in imageViews">
			<td class="title"><img style="height: 70px; width: 70px;" alt=""
				title="{{imageView.caption}}"
				ng-src="<spring:message code="domain"/>/img/gallery/thumb/{{imageView.id}}.jpg" />
			</td>
			<td class="cont" style="vertical-align: middle;"><input
				type="text" class="text" ng-model="imageView.caption"
				style="width: 300px;" />&nbsp;&nbsp;<input type="button"
				class="button buttonAccept" ng-click="updateImage(imageView)"
				value="{{langCommon.save}}" />&nbsp;<input type="button"
				class="button buttonSetAva" ng-click="setAvatar(imageView)"
				value="{{langPlace.setAva}}" />&nbsp;&nbsp;<input type="button"
				class="button buttonDelete" ng-click="delImage(imageView)"
				value="{{langCommon.delete}}" />
			</td>
		</tr>
	</table>
	
	<div id="popup" style="display: none">
		<div id="divGallery">
			<form id="fileupload"
				action="<spring:message code="domain"/>/data/image/upimg/{{placeId}}"
				method="POST" enctype="multipart/form-data">
				<!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
				<div class="row fileupload-buttonbar">
					<div class="span7">
						<!-- The fileinput-button span is used to style the file input field as button -->
						<span class="btn btn-success fileinput-button"> <i
							class="icon-plus icon-white"></i> <span>{{langPlace.addfile}}...</span>
							<input type="file" name="file" multiple> </span>
						<button type="submit" class="btn btn-primary start">
							<i class="icon-upload icon-white"></i> <span>{{langPlace.startupload}}</span>
						</button>
						<button type="reset" class="btn btn-warning cancel">
							<i class="icon-ban-circle icon-white"></i> <span>{{langPlace.cancelupload}}</span>
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
				<table class="table table-striped download-table">
					<tbody class="files" data-toggle="modal-gallery"
						data-target="#modal-gallery"></tbody>
				</table>
			</form>
		</div>
		
	</div>
</div>