package core.util.htmlsafety;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;

public class DdWhitelist extends Whitelist {
	private Set<TagName> tagNames; // tags allowed, lower case. e.g. [p, br,
									// span]
	private Map<TagName, Set<AttributeKey>> attributes; // tag -> attribute[].
														// allowed attributes
														// [href] for a tag.
	private Map<TagName, Map<AttributeKey, AttributeValue>> enforcedAttributes; // always
																				// set
																				// these
																				// attribute
																				// values
	private Map<TagName, Map<AttributeKey, Set<Protocol>>> protocols; // allowed
																		// URL
																		// protocols
																		// for
																		// attributes
	private Map<TagName, Map<AttributeKey, List<AttributeValue>>> allowValueAttributes; // always
																						// set
																						// these
																						// attribute
																						// values
	private boolean preserveRelativeLinks; // option to preserve relative links

	/**
	 * Create a new, empty whitelist. Generally it will be better to start with
	 * a default prepared whitelist instead.
	 */
	public DdWhitelist() {
		tagNames = new HashSet<TagName>();
		attributes = new HashMap<TagName, Set<AttributeKey>>();
		enforcedAttributes = new HashMap<TagName, Map<AttributeKey, AttributeValue>>();
		protocols = new HashMap<TagName, Map<AttributeKey, Set<Protocol>>>();
		allowValueAttributes = new HashMap<TagName, Map<AttributeKey, List<AttributeValue>>>();
		preserveRelativeLinks = false;
	}

	/**
	 * Add a list of allowed elements to a whitelist. (If a tag is not allowed,
	 * it will be removed from the HTML.)
	 * 
	 * @param tags
	 *            tag names to allow
	 * @return this (for chaining)
	 */
	public DdWhitelist addTags(String... tags) {
		Validate.notNull(tags);

		for (String tagName : tags) {
			Validate.notEmpty(tagName);
			tagNames.add(TagName.valueOf(tagName));
		}
		return this;
	}

	/**
	 * Add a list of allowed attributes to a tag. (If an attribute is not
	 * allowed on an element, it will be removed.)
	 * <p/>
	 * E.g.: <code>addAttributes("a", "href", "class")</code> allows
	 * <code>href</code> and <code>class</code> attributes on <code>a</code>
	 * tags.
	 * <p/>
	 * To make an attribute valid for <b>all tags</b>, use the pseudo tag
	 * <code>:all</code>, e.g. <code>addAttributes(":all", "class")</code>.
	 * 
	 * @param tag
	 *            The tag the attributes are for. The tag will be added to the
	 *            allowed tag list if necessary.
	 * @param keys
	 *            List of valid attributes for the tag
	 * @return this (for chaining)
	 */
	public DdWhitelist addAttributes(String tag, String... keys) {
		Validate.notEmpty(tag);
		Validate.notNull(keys);
		Validate.isTrue(keys.length > 0, "No attributes supplied.");

		TagName tagName = TagName.valueOf(tag);
		if (!tagNames.contains(tagName))
			tagNames.add(tagName);
		Set<AttributeKey> attributeSet = new HashSet<AttributeKey>();
		for (String key : keys) {
			Validate.notEmpty(key);
			attributeSet.add(AttributeKey.valueOf(key));
		}
		if (attributes.containsKey(tagName)) {
			Set<AttributeKey> currentSet = attributes.get(tagName);
			currentSet.addAll(attributeSet);
		} else {
			attributes.put(tagName, attributeSet);
		}
		return this;
	}

	/**
	 * Add an enforced attribute to a tag. An enforced attribute will always be
	 * added to the element. If the element already has the attribute set, it
	 * will be overridden.
	 * <p/>
	 * E.g.: <code>addEnforcedAttribute("a", "rel", "nofollow")</code> will make
	 * all <code>a</code> tags output as
	 * <code>&lt;a href="..." rel="nofollow"></code>
	 * 
	 * @param tag
	 *            The tag the enforced attribute is for. The tag will be added
	 *            to the allowed tag list if necessary.
	 * @param key
	 *            The attribute key
	 * @param value
	 *            The enforced attribute value
	 * @return this (for chaining)
	 */
	public DdWhitelist addEnforcedAttribute(String tag, String key, String value) {
		Validate.notEmpty(tag);
		Validate.notEmpty(key);
		Validate.notEmpty(value);

		TagName tagName = TagName.valueOf(tag);
		if (!tagNames.contains(tagName))
			tagNames.add(tagName);
		AttributeKey attrKey = AttributeKey.valueOf(key);
		AttributeValue attrVal = AttributeValue.valueOf(value);

		if (enforcedAttributes.containsKey(tagName)) {
			enforcedAttributes.get(tagName).put(attrKey, attrVal);
		} else {
			Map<AttributeKey, AttributeValue> attrMap = new HashMap<AttributeKey, AttributeValue>();
			attrMap.put(attrKey, attrVal);
			enforcedAttributes.put(tagName, attrMap);
		}
		return this;
	}

	/**
	 * Add a list of allowed value to an attribute. (If a value is not allowed
	 * on an element, it will be removed.)
	 * <p/>
	 * E.g.: <code>addValueAttribute("a", "style", "color: red")</code>
	 * 
	 * @param tag
	 *            The tag the attributes are for. The tag will be added to the
	 *            allowed tag list if necessary.
	 * @param key
	 *            The attribute key
	 * @param value
	 *            The attribute value
	 * @return this (for chaining)
	 */
	public DdWhitelist addValueAttribute(String tag, String key, String value) {
		Validate.notEmpty(tag);
		Validate.notEmpty(key);
		Validate.notEmpty(value);

		TagName tagName = TagName.valueOf(tag);
		if (!tagNames.contains(tagName))
			tagNames.add(tagName);
		AttributeKey attrKey = AttributeKey.valueOf(key);
		AttributeValue attrVal = AttributeValue.valueOf(value);

		if (allowValueAttributes.containsKey(tagName)) {
			if (allowValueAttributes.get(tagName).get(attrKey) == null) {
				allowValueAttributes.get(tagName).put(attrKey,
						new ArrayList<AttributeValue>());
			}
			allowValueAttributes.get(tagName).get(attrKey).add(attrVal);
		} else {
			Map<AttributeKey, List<AttributeValue>> attrMap = new HashMap<AttributeKey, List<AttributeValue>>();
			attrMap.put(attrKey, new ArrayList<AttributeValue>());
			attrMap.get(attrKey).add(attrVal);
			allowValueAttributes.put(tagName, attrMap);
		}
		return this;
	}

	/**
	 * Configure this DdWhitelist to preserve relative links in an element's URL
	 * attribute, or convert them to absolute links. By default, this is
	 * <b>false</b>: URLs will be made absolute (e.g. start with an allowed
	 * protocol, like e.g. {@code http://}.
	 * <p />
	 * Note that when handling relative links, the input document must have an
	 * appropriate {@code base URI} set when parsing, so that the link's
	 * protocol can be confirmed. Regardless of the setting of the
	 * {@code preserve relative
	 * links} option, the link must be resolvable against the base URI to an
	 * allowed protocol; otherwise the attribute will be removed.
	 * 
	 * @param preserve
	 *            {@code true} to allow relative links, {@code false} (default)
	 *            to deny
	 * @return this DdWhitelist, for chaining.
	 * @see #addProtocols
	 */
	public DdWhitelist preserveRelativeLinks(boolean preserve) {
		preserveRelativeLinks = preserve;
		return this;
	}

	/**
	 * Add allowed URL protocols for an element's URL attribute. This restricts
	 * the possible values of the attribute to URLs with the defined protocol.
	 * <p/>
	 * E.g.: <code>addProtocols("a", "href", "ftp", "http", "https")</code>
	 * 
	 * @param tag
	 *            Tag the URL protocol is for
	 * @param key
	 *            Attribute key
	 * @param protocols
	 *            List of valid protocols
	 * @return this, for chaining
	 */
	public DdWhitelist addProtocols(String tag, String key, String... protocols) {
		Validate.notEmpty(tag);
		Validate.notEmpty(key);
		Validate.notNull(protocols);

		TagName tagName = TagName.valueOf(tag);
		AttributeKey attrKey = AttributeKey.valueOf(key);
		Map<AttributeKey, Set<Protocol>> attrMap;
		Set<Protocol> protSet;

		if (this.protocols.containsKey(tagName)) {
			attrMap = this.protocols.get(tagName);
		} else {
			attrMap = new HashMap<AttributeKey, Set<Protocol>>();
			this.protocols.put(tagName, attrMap);
		}
		if (attrMap.containsKey(attrKey)) {
			protSet = attrMap.get(attrKey);
		} else {
			protSet = new HashSet<Protocol>();
			attrMap.put(attrKey, protSet);
		}
		for (String protocol : protocols) {
			Validate.notEmpty(protocol);
			Protocol prot = Protocol.valueOf(protocol);
			protSet.add(prot);
		}
		return this;
	}

	boolean isSafeTag(String tag) {
		return tagNames.contains(TagName.valueOf(tag));
	}

	boolean isSafeAttribute(String tagName, Element el, Attribute attr) {
		TagName tag = TagName.valueOf(tagName);
		AttributeKey key = AttributeKey.valueOf(attr.getKey());

		if (attributes.containsKey(tag)) {
			if (attributes.get(tag).contains(key)) {
				if (protocols.containsKey(tag)) {
					Map<AttributeKey, Set<Protocol>> attrProts = protocols
							.get(tag);
					// ok if not defined protocol; otherwise test
					return !attrProts.containsKey(key)
							|| testValidProtocol(el, attr, attrProts.get(key));
				} else { // attribute found, no protocols defined, so OK
					return true;
				}
			}
		}
		// no attributes defined for tag, try :all tag
		return !tagName.equals(":all") && isSafeAttribute(":all", el, attr);
	}

	boolean isSafeAttributeValue(String tagName, Element el, Attribute attr,
			String val) {
		TagName tag = TagName.valueOf(tagName);
		AttributeKey key = AttributeKey.valueOf(attr.getKey());
		AttributeValue value = AttributeValue.valueOf(StringUtils.strip(val));
		if (attributes.containsKey(tag)) {
			if (attributes.get(tag).contains(key)) {
				return allowValueAttributes.get(tag).get(key) == null ? false
						: allowValueAttributes.get(tag).get(key)
								.contains(value);
			}
		}
		// no attributes defined for tag, try :all tag
		return !tagName.equals(":all") && isSafeAttribute(":all", el, attr);
	}

	private boolean testValidProtocol(Element el, Attribute attr,
			Set<Protocol> protocols) {
		// try to resolve relative urls to abs, and optionally update the
		// attribute so output html has abs.
		// rels without a baseuri get removed
		String value = el.absUrl(attr.getKey());
		if (value.length() == 0)
			value = attr.getValue(); // if it could not be made abs, run as-is
										// to allow custom unknown protocols
		if (!preserveRelativeLinks)
			attr.setValue(value);

		for (Protocol protocol : protocols) {
			String prot = protocol.toString() + ":";
			if (value.toLowerCase().startsWith(prot)) {
				return true;
			}
		}
		return false;
	}

	Attributes getEnforcedAttributes(String tagName) {
		Attributes attrs = new Attributes();
		TagName tag = TagName.valueOf(tagName);
		if (enforcedAttributes.containsKey(tag)) {
			Map<AttributeKey, AttributeValue> keyVals = enforcedAttributes
					.get(tag);
			for (Map.Entry<AttributeKey, AttributeValue> entry : keyVals
					.entrySet()) {
				attrs.put(entry.getKey().toString(), entry.getValue()
						.toString());
			}
		}
		return attrs;
	}

	// named types for config. All just hold strings, but here for my sanity.

	static class TagName extends TypedValue {
		TagName(String value) {
			super(value);
		}

		static TagName valueOf(String value) {
			return new TagName(value);
		}
	}

	static class AttributeKey extends TypedValue {
		AttributeKey(String value) {
			super(value);
		}

		static AttributeKey valueOf(String value) {
			return new AttributeKey(value);
		}
	}

	static class AttributeValue extends TypedValue {
		AttributeValue(String value) {
			super(value);
		}

		static AttributeValue valueOf(String value) {
			return new AttributeValue(value);
		}
	}

	static class Protocol extends TypedValue {
		Protocol(String value) {
			super(value);
		}

		static Protocol valueOf(String value) {
			return new Protocol(value);
		}
	}

	abstract static class TypedValue {
		private String value;

		TypedValue(String value) {
			Validate.notNull(value);
			this.value = value;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TypedValue other = (TypedValue) obj;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return value;
		}
	}
}
