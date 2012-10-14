<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../inc/taglib.jsp"%>

<div id="divCat">
		</a> <a href="#posNewPlace"> <img alt=""
			src="<spring:message code="domain"/>/img/res/icon/cat/drink.png">
		</a> <a href="#posGallery"> <img alt=""
			src="<spring:message code="domain"/>/img/res/icon/cat/entertainment.png">
		</a>
	</div>

<div id="goaround" ng-controller="GoAroundGalleryCtrl">
	<span class="GaTitle" id="posNewPlace">
		New Place Joined Us
	</span>
	<div id="categoryNewPlace" class="category">
		<div id="containerNewPlace" class="boxContainer"></div>
	</div>
	<div class="getmore">
		<input type="button" value="Previous" class="button"
			ng-click="getprevious()" />
		<input type="button" value="Next" class="button"
			ng-click="getnext()" />
	</div>
	
	<div class="seperateLine"></div>
	<span class="GaTitle" id="posGallery">
		Place Around Us
	</span>
	
	<div id="categoryGallery" class="category">
		<div id="containerGallery" class="boxContainer">
			<div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_1.jpg" width="200" height="283"><p>1</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_2.jpg" width="200" height="300"><p>2</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_3.jpg" width="200" height="252"><p>3</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_4.jpg" width="200" height="158"><p>4</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_5.jpg" width="200" height="300"><p>5</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_6.jpg" width="200" height="297"><p>6</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_7.jpg" width="200" height="200"><p>7</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_8.jpg" width="200" height="200"><p>8</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_9.jpg" width="200" height="398"><p>9</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_10.jpg" width="200" height="267"><p>10</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_1.jpg" width="200" height="283"><p>11</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_2.jpg" width="200" height="300"><p>12</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_3.jpg" width="200" height="252"><p>13</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_4.jpg" width="200" height="158"><p>14</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_5.jpg" width="200" height="300"><p>15</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_6.jpg" width="200" height="297"><p>16</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_7.jpg" width="200" height="200"><p>17</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_8.jpg" width="200" height="200"><p>18</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_9.jpg" width="200" height="398"><p>19</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_10.jpg" width="200" height="267"><p>20</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_1.jpg" width="200" height="283"><p>21</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_2.jpg" width="200" height="300"><p>22</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_3.jpg" width="200" height="252"><p>23</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_4.jpg" width="200" height="158"><p>24</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_5.jpg" width="200" height="300"><p>25</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_6.jpg" width="200" height="297"><p>26</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_7.jpg" width="200" height="200"><p>27</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_8.jpg" width="200" height="200"><p>28</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_9.jpg" width="200" height="398"><p>29</p></div>
	        <div class="box"><img src="<spring:message code="domain"/>/img/go-gallery/image_10.jpg" width="200" height="267"><p>30</p></div>
		</div>
	</div>
	<div id="loading" style="display: none"><img alt="Loading..." src="http://i.imgur.com/6RMhx.gif"><div style="opacity: 1; ">Loading the next set of gallery...</div></div>
</div>