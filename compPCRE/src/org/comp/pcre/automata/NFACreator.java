package org.comp.pcre.automata;

import java.util.LinkedList;

import org.comp.pcre.automata.State.Connection;
import org.comp.pcre.grammar.SimpleNode;




public class NFACreator {

	private static class StateQueue
	{
		final State head;
		State last = null;
		
		public StateQueue(State head)
		{
			this.head = head; 
			this.last = head;
		}
	}
	
	
	private LinkedList<StateQueue> stateQueue = new LinkedList<StateQueue>();
	private int level = 0;
	
	
	public NFACreator()
	{
	}
	
	
	
	
	
	public void reset()
	{
		stateQueue.clear();
	}

	
	public State fromSimpleNode(SimpleNode root)
	{
		this.reset();
		
		State rootState = new State();
		
		stateQueue.addFirst(new StateQueue(rootState));
		
		iterate(root);
		
		return rootState;
	}
	
	
	private StateQueue replicateStateSequence(StateQueue state)
	{
		State head = new State();
		StateQueue newStateQueue = new StateQueue(head);
		
		
		
		
		
		return newStateQueue;
	}

	
	
	
	private Connection analysis(SimpleNode node)
	{
		if( node == null || node.jjtGetValue() == null)
			return null;
			
		String name = node.toString();
		StateQueue queue = stateQueue.element();
		
		if( name.equals("Chars") )
		{
			System.out.println(level + " Chars");
			
			State s = new State();
			Connection c = new Connection(queue.last, s);
			
			c.character =  node.jjtGetValue().toString();
			c.to = s;
			
			return c;
		}
		else
		if( name.equals("Quantifier") )
		{
			System.out.println(level + " Quantifier");
			QuantifierState quantifier = (QuantifierState) node.jjtGetValue();
			
			switch(quantifier.type)
			{
				case ZERO_OR_MORE:
					
					Connection c = new Connection(queue.last, queue.head);
					c.cyclic = true;
					
					queue.last.connections.add(c);
					
					break;
					
				case AT_LEAST_ONE:
				case EXACTLY_X:
				case ZERO_OR_ONE:
				case RANGED:
					
					
					
					break;
			}	
			
		}
		
		return null;
	}
	

	
	
	public State iterate(SimpleNode root)
	{
		SimpleNode nodeChild = null;
		
		StateQueue cur = stateQueue.element();
		
		if( cur == null )
			return null;
		
		for(int i=0;i<root.jjtGetNumChildren();i++)
		{
			nodeChild = (SimpleNode) root.jjtGetChild(i);
			
			if( nodeChild == null )
				continue;
			
			Connection s = analysis(nodeChild);
			if( s != null )
			{	
				cur.last.connections.add(s);
				cur.last = s.to;
			}
			
			
			if( nodeChild.jjtGetNumChildren() > 0 )
			{
				level++;
				stateQueue.addFirst(new StateQueue(cur.last));
				
				cur.last = iterate(nodeChild);
				
				stateQueue.remove();
				level--;
			}
		}
		
		return cur.last;
	}
}
