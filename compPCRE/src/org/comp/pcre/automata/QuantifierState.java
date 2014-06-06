package org.comp.pcre.automata;


public class QuantifierState {

	public static enum Type {
		ZERO_OR_ONE,
		ZERO_OR_MORE,
		AT_LEAST_ONE,
		RANGED,
		EXACTLY_X
	}
	
	public Type type = null;
	
	public long max = -1;
	public long value = -1;
	
	public void setValue(String value)
	{
		try {
			this.value = Integer.valueOf(value);
		} catch(Exception e) {
			this.value = -1;
		}
	}
	public void setMax(String value)
	{
		try {
			this.max = Integer.valueOf(value);
		} catch(Exception e) {
			this.max = -1;
		}
	}
}
