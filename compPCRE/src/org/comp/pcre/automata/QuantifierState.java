package org.comp.pcre.automata;


public class QuantifierState {

	public static enum Type {
		ZERO_OR_ONE,
		ZERO_OR_MORE,
		ONE_OR_MORE,
		RANGED,
		EXACTLY_X
	}
	
	public Type type = null;
	
	public Long max = null;
	public Long value = null;
	
	public void setValue(String value)
	{
		try {
			this.value = Long.valueOf(value);
		} catch(Exception e) {
			this.value = null;
		}
	}
	public void setMax(String value)
	{
		try {
			this.max = Long.valueOf(value);
		} catch(Exception e) {
			this.max = null;
		}
	}
}
