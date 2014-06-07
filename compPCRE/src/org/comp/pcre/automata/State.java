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
	public ArrayList<Connection> connections = new ArrayList<Connection>();
	
	public State()
	{
		this.name = ++stateCounter;
	}
	
	public Connection connect(State to, boolean cyclic)
	{
		Connection c = new Connection(this, to);
		c.cyclic = cyclic;
		
		this.connections.add(c);
		
		return c;
	}

}
