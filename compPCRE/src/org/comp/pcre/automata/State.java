package org.comp.pcre.automata;

import java.util.ArrayList;


public class State {
	
	public static class Connection {
		public String character = null;
		public State to = null;
		public State from = null;
		public boolean cyclic = false;
		
		public Connection(State from, State to)
		{
			this.from = from;
			this.to = to;
		}
	}
	
	private static long stateCounter = -1;

	public final long name;
	public boolean isFinal = false;
	public boolean visited = false;
	public ArrayList<Connection> connections = new ArrayList<Connection>();
	
	public State()
	{
		this.name = ++stateCounter;
	}
	
	
	public Connection connect(State to, String character, boolean cyclic)
	{
		for(Connection cc: connections)
		{
			if(    to.name == cc.to.name 
				&& (     ( character == null && cc.character == null )
				      || ( character != null && character.equals(cc.character) ) ) )
			{
				cc.cyclic = cyclic;
				return cc;
			}
		}

		Connection c = new Connection(this, to);
		
		c.cyclic = cyclic;
		c.character = character;

		this.connections.add(c);
		
		return c;
	}

}
