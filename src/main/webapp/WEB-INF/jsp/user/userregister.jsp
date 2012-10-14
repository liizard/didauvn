<div id="divNewUser" ng-controller="RegisterCtrl">
	<table class="modify">
		<tr>
			<th colspan="2">{{langUser.register}}&nbsp;|&nbsp;<a class="action"
				href="javascript:void(0)" onclick="connectfb()">{{langUser.registerfb}}</a>
			</th>
		</tr>
		<tr>
			<td class="title">{{langUser.email}}:</td>
			<td class="cont"><input type="text" class="text"
				ng-model="user.email" /></td>
		</tr>
		<tr>
			<td class="title">{{langUser.name}}:</td>
			<td class="cont"><input type="text" class="text"
				ng-model="user.name" /></td>
		</tr>
		<tr>
			<td class="title">{{langUser.birthday}}:</td>
			<td class="cont"><input type="text" class="text"
				ng-model="user.birthday" id="datepicker"/>&nbsp;(dd/mm/yyyy)</td>
		</tr>
		<tr>
			<td class="title">{{langUser.password}}:</td>
			<td class="cont"><input type="password" class="text"
				ng-model="user.password" />
			</td>
		</tr>
		<tr>
			<td class="title">{{langUser.confirm}}:</td>
			<td class="cont"><input type="password" class="text"
				ng-model="confirmpassword" />
			</td>
		</tr>
		<tr>
			<td class="title">{{langUser.gender}}:</td>
			<td class="cont"><input type="radio" ng-model="user.gender"
				value="male">&nbsp;{{langUser.male}} <input type="radio"
				ng-model="user.gender" value="female">&nbsp;{{langUser.female}}</td>
		</tr>
		<tr>
			<td></td>
			<td><input type="button" class="button" ng-click="register()"
				value="{{langUser.register}}" /></td>
		</tr>
	</table>
	<form id="fb_signin" action="/didauvn/signin/facebook" method="POST">
		<input type="hidden" name="scope"
			value="publish_stream,email,user_birthday" />
	</form>
</div>
