/* Vietnamese initialisation for the jQuery UI date picker plugin. */
/* Translated by Le Thanh Huy (lthanhhuy@cit.ctu.edu.vn). */
jQuery(function($) {
	$.datepicker.regional['vi'] = {
		yearRange : "-100:+0",
		changeMonth : true,
		changeYear : true,
		closeText : 'Đóng',
		prevText : '&#x3c;Trước',
		nextText : 'Tiếp&#x3e;',
		currentText : 'Hôm nay',
		monthNames : [ 'Tháng Một', 'Tháng Hai', 'Tháng Ba', 'Tháng Tư',
				'Tháng Năm', 'Tháng Sáu', 'Tháng Bảy', 'Tháng Tám',
				'Tháng Chín', 'Tháng Mười', 'Tháng Mười Một', 'Tháng Mười Hai' ],
		monthNamesShort : [ 'Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4',
				'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9',
				'Tháng 10', 'Tháng 11', 'Tháng 12' ],
		dayNames : [ 'Chủ Nhật', 'Thứ Hai', 'Thứ Ba', 'Thứ Tư', 'Thứ Năm',
				'Thứ Sáu', 'Thứ Bảy' ],
		dayNamesShort : [ 'CN', 'T2', 'T3', 'T4', 'T5', 'T6', 'T7' ],
		dayNamesMin : [ 'CN', 'T2', 'T3', 'T4', 'T5', 'T6', 'T7' ],
		weekHeader : 'Tu',
		dateFormat : 'dd/mm/yy',
		firstDay : 0,
		isRTL : false,
		showMonthAfterYear : false,
		yearSuffix : ''
	};
	$.datepicker.setDefaults($.datepicker.regional['vi']);
});

/* English/UK initialisation for the jQuery UI date picker plugin. */
/* Written by Stuart. */
jQuery(function($) {
	$.datepicker.regional['en'] = {
		yearRange : "-100:+0",
		changeMonth : true,
		changeYear : true,
		closeText : 'Done',
		prevText : 'Prev',
		nextText : 'Next',
		currentText : 'Today',
		monthNames : [ 'January', 'February', 'March', 'April', 'May', 'June',
				'July', 'August', 'September', 'October', 'November',
				'December' ],
		monthNamesShort : [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul',
				'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ],
		dayNames : [ 'Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday',
				'Friday', 'Saturday' ],
		dayNamesShort : [ 'Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat' ],
		dayNamesMin : [ 'Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa' ],
		weekHeader : 'Wk',
		dateFormat : 'dd/mm/yy',
		firstDay : 1,
		isRTL : false,
		showMonthAfterYear : false,
		yearSuffix : ''
	};
	$.datepicker.setDefaults($.datepicker.regional['en']);
});