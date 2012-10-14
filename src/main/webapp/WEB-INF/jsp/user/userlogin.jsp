
<form action="auth/login" method="post">
	<table class="modify">
		<tr>
			<th colspan="2">{{langUser.login}}&nbsp;|&nbsp;<a class="action" href="javascript:void(0)"
				onclick="connectfb()">{{langUser.loginfb}}</a>
			</th>
		</tr>
		<tr>
			<td class="title">{{langUser.email}}:</td>
			<td class="cont"><input name="email" type="text" class="text" />
			</td>
		</tr>
		<tr>
			<td class="title">{{langUser.password}}:</td>
			<td class="cont"><input name="password" type="password"
				class="text" />
			</td>
		</tr>
		<tr>
			<td></td>
			<td class="cont"><input type="checkbox"
				name="_spring_security_remember_me">&nbsp;{{langUser.remember}}</td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="{{langUser.login}}"
				class="button" />
			</td>
		</tr>
	</table>
</form>
<br>
<form id="fb_signin" action="/didauvn/signin/facebook" method="POST">
	<input type="hidden" name="scope" value="publish_stream,email,user_birthday" />
</form>
