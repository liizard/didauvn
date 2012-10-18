var FEED_BACK_PER_PAGE = 5;
var IMAGE_PER_PAGE = 10;
var VIDEO_PER_PAGE = 4;
var SEARCH_RESULT_PER_PAGE = 20;
var NEWS_PER_PAGE = 4;
var MESSAGE_PER_PAGE = 10;
var NEWS_TITLE_LENGTH = 50;
var PASSWORD_LENGTH = 5;
var USERNAME_LENGTH = 45;

function normalize(str) {
	str = str.toLowerCase();
	str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g, "a");
	str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, "e");
	str = str.replace(/ì|í|ị|ỉ|ĩ/g, "i");
	str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g, "o");
	str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, "u");
	str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, "y");
	str = str.replace(/đ/g, "d");
	return str;
};

function existed(list, id) {
	for ( var i in list) {
		if (list[i].id == id) {
			return i;
		}
	}
	return -1;
};

function deleteById(list, id) {
	for ( var i in list) {
		if (list[i].id == id) {
			list.splice(i, 1);
		}
	}
}

function getById(list, id) {
	for ( var i in list) {
		if (list[i].id == id) {
			return list[i];
		}
	}
}

function initnicEditor(id, cont) {
	new nicEditor({
		buttonList : [ 'bold', 'italic', 'underline', 'strikeThrough', 'left',
				'right', 'center', 'justify' ],
		maxHeight : 300,
		iconsPath : DOMAIN + '/img/res/nicEditorIcons.gif',
	}).panelInstance(id);
	nicEditors.findEditor(id).setContent(cont);
}

function notifySuccess(title, text) {
	$.pnotify({
		delay : 2000,
		title : title,
		text : text,
		type : 'success',
	});
}

function clearHTMLTag(s) {
	return s.replace(/<(?:.|\n)*?>/gm, '');
}

// Auto fix length of input textbox
function setWidthTag(textTag) {
	var len = $(textTag).val().length;
	if (len > 35) {
		len = 35;
	}
	$(textTag).css('width', (len * 8 + 20) + 'px');
}