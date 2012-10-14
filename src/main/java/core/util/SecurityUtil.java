package core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.safety.Whitelist;

import core.util.htmlsafety.DdCleaner;
import core.util.htmlsafety.DdWhitelist;

public class SecurityUtil {

	public static String getMd5String(String input) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(input.getBytes());
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
						.substring(1));
			}

			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public static String secureTextToPlain(String content) {
		return Jsoup.clean(content, Whitelist.none());
	}
	
	public static String secureTextToEditor(String content) {
		/*Whitelist whitelist = new Whitelist();
		whitelist.addTags("b", "i", "span");
		whitelist.addAttributes("span", "style");
		whitelist.addEnforcedAttribute("span", "style", "font-weight: bold");
		//Chrome
		whitelist.addTags("em","b","u","div");
		whitelist.addAttributes("style");
		whitelist.addEnforcedAttribute("div", "style", "text-align: right");
		whitelist.addEnforcedAttribute("div", "style", "text-align: left");
		whitelist.addEnforcedAttribute("div", "style", "text-align: center");
		whitelist.addEnforcedAttribute("div", "style", "text-align: justify");
		//Firefox
		whitelist.addTags("i");
		whitelist.addAttributes("align");
		whitelist.addEnforcedAttribute("div", "align", "left");
		whitelist.addEnforcedAttribute("div", "align", "right");
		whitelist.addEnforcedAttribute("div", "align", "center");
		whitelist.addEnforcedAttribute("div", "align", "justify");
		//IE8
		whitelist.addTags("strong");
		
		return Jsoup.clean(content, Whitelist.none());*/
		Document dirty = Parser.parseBodyFragment(content, "");

		DdWhitelist whitelist = new DdWhitelist();
		//Chrome
		whitelist.addTags("em","b","u","div");
		whitelist.addAttributes("div", "style");
		whitelist.addValueAttribute("div", "style", "text-align: left");
		whitelist.addValueAttribute("div", "style", "text-align: right");
		whitelist.addValueAttribute("div", "style", "text-align: center");
		whitelist.addValueAttribute("div", "style", "text-align: justify");
		
		//Firefox
		whitelist.addTags("i");
		whitelist.addAttributes("div", "align");
		whitelist.addValueAttribute("div", "align", "left");
		whitelist.addValueAttribute("div", "align", "right");
		whitelist.addValueAttribute("div", "align", "center");
		whitelist.addValueAttribute("div", "align", "justify");
		
		DdCleaner cleaner = new DdCleaner(whitelist);
		Document clean = cleaner.clean(dirty);
		return clean.body().html();
	}
	
	public static boolean checkEmail(String email) {
		String patternEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(patternEmail);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return true;
		} 
		return false;
	}
}
