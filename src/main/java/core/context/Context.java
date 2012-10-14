package core.context;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Context implements Serializable {
	
	private static final long serialVersionUID = -6065477930727342855L;
	
	private Map<ContextTypeEnum, Object> map = new HashMap<ContextTypeEnum, Object>();
	
	public Context() {
	}
	
	public void add(ContextTypeEnum type, Object value) {
		map.put(type, value);
	}
	
	public Object get(ContextTypeEnum type) {
		return map.get(type);
	}
	
	public void clear() {
		map.clear();
	}
	
}
