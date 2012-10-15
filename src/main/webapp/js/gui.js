$(document).ready(function() {
	$("#loading").css('display', 'none');

	/*
	 * $("img").error(function(){ $(this).hide(); });
	 * 
	 * $("#searchResult a").click(function(){ $("#header").animate({height:
	 * "50"}, "normal", function(){ $("#divSearch").fadeOut("fast"); }); });
	 * 
	 * $("#iconSearch").click(function(){ $("#header").animate({height: "100"},
	 * "normal", function(){ $("#divSearch").fadeIn("fast"); }); });
	 * 
	 * 
	 * $("#menu img").click(function(){ if ($(this).attr("id") == "iconCat"){
	 * $("#header").animate({height: "50"}, "normal", function(){
	 * $("#divSearch").fadeOut("fast"); }); } });
	 * 
	 * $("#user img").click(function(){ $("#header").animate({height: "50"},
	 * "normal", function(){ $("#divSearch").fadeOut("fast"); }); });
	 */

	/*
	 * var iconURL = ""; $("#menu a img").hover(function() { iconURL =
	 * $(this).attr("src"); // if ($(this).attr("class") != "clicked") { if
	 * (iconURL.length > 0) { var element = iconURL.split(".png");
	 * $(this).attr("src", element[0] + "1.png"); } // } }, function() { // if
	 * ($(this).attr("class") != "clicked") { if (iconURL.length > 0) {
	 * $(this).attr("src", iconURL); } // } });
	 */

	$('#container').on('click', '#placeDetail #tabHeader a', function() {
		$('#placeDetail .selectTabHeader').removeClass('selectTabHeader');
		$(this).addClass('selectTabHeader');
		var v = this.id;
		$('#placeDetail .selectedTab').removeClass('selectedTab');
		$('#placeDetail #tab-data' + v).addClass('selectedTab');
	});
	$('#container').on('click', '#placeMedia #tabHeader a', function() {
		$('#placeMedia .selectTabHeader').removeClass('selectTabHeader');
		$(this).addClass('selectTabHeader');
		var v = this.id;
		$('#placeMedia .selectedTab').removeClass('selectedTab');
		$('#placeMedia #tab-data' + v).addClass('selectedTab');
	});
	$('#container').on('click', '#result #tabHeader a', function() {
		$('#result .selectTabHeader').removeClass('selectTabHeader');
		$(this).addClass('selectTabHeader');
		var v = this.id;
		$('#result .selectedTab').removeClass('selectedTab');
		$('#result #tab-data' + v).addClass('selectedTab');
	});
});

var opts = {
	lines : 9, // The number of lines to draw
	length : 0, // The length of each line
	width : 6, // The line thickness
	radius : 11, // The radius of the inner circle
	corners : 1, // Corner roundness (0..1)
	rotate : 0, // The rotation offset
	color : '#000', // #rgb or #rrggbb
	speed : 1, // Rounds per second
	trail : 60, // Afterglow percentage
	shadow : true, // Whether to render a shadow
	hwaccel : false, // Whether to use hardware acceleration
	className : 'spinner', // The CSS class to assign to the spinner
	zIndex : 2e9, // The z-index (defaults to 2000000000)
	top : 'auto', // Top position relative to parent in px
	left : 'auto' // Left position relative to parent in px
};
var target = document.getElementById("loading");
var spinner = new Spinner(opts).spin(target);
