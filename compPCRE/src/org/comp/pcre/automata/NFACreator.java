package org.comp.pcre.automata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import org.comp.pcre.automata.State.Connection;
import org.comp.pcre.grammar.SimpleNode;




public class NFACreator {

	public static class StateQueue
	{
		protected final State head;
		protected State last = null;
		
		public StateQueue(State head)
		{
			this.head = head; 
			this.last = head;
		}
		
		public State getHead()
		{
			return head;
		}
		
		public State getTail()
		{
			return last;
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

	
	public StateQueue fromSimpleNode(SimpleNode root)
	{
		this.reset();
		
		stateQueue.addFirst(new StateQueue(new State()));
		
		iterate(root);
		
		return stateQueue.element();
	}
	
	
	private static State createOrGetStateFromMap(HashMap<Long, State> stateMap, State ref)
	{
		State find = stateMap.get(ref.name);
		
		if( find == null )
		{
			find = new State();
			stateMap.put(ref.name, find);
		}
		
		return find;
	}
	private static State replicateStateSequenceBranch(HashMap<Long, State> stateMap, State curRef, State startRef, State endRef)
	{
		if( stateMap == null || curRef == null )
			return null;
		
		State addTo = createOrGetStateFromMap(stateMap, curRef);
		
		for(Connection c : curRef.connections)
		{
			State newS = null;
			
			if( c.cyclic )
			{
				if( curRef.name == startRef.name )
					continue;			
				
				newS = createOrGetStateFromMap(stateMap, c.to);
			}
			else
			{
				if( endRef != null && curRef.name == endRef.name)
					continue;
				
				newS = replicateStateSequenceBranch(stateMap, c.to, startRef, endRef);
			}
			
			if( newS == null )
				continue;
			
			Connection nC = addTo.connect(newS, c.cyclic);
			nC.character = c.character;
		}
	
		return addTo;
	}
	
	
	private StateQueue replicateStateSequence(StateQueue state)
	{

		HashMap<Long, State> stateMap = new HashMap<Long, State>();
		
		
		replicateStateSequenceBranch(stateMap, state.head, state.head, state.last);
		
		State head = stateMap.get(state.head.name);
		StateQueue newQueue = new StateQueue(head);
		
		newQueue.last = stateMap.get(state.last.name); 
		
		return newQueue;
	}

	
	
	
	private void analysis(SimpleNode node)
	{
		if( node == null || node.jjtGetValue() == null)
			return;
			
		String name = node.toString();
		StateQueue queue = stateQueue.element();
		
		if( name.equals("Chars") )
		{
			System.out.println(level + " Chars");
			
			State s = new State();
			Connection c = queue.last.connect(s, false);
			queue.last = s;
			
			c.character =  node.jjtGetValue().toString();
		}
		else
		if( name.equals("Quantifier") )
		{
			System.out.println(level + " Quantifier");
			QuantifierState quantifier = (QuantifierState) node.jjtGetValue();

			
			if( quantifier.type == QuantifierState.Type.ZERO_OR_MORE )
				queue.last.connect(queue.head, true);

			else
			{
				long start = -1;
				long end = -1;
				
				switch(quantifier.type)
				{
					case ONE_OR_MORE:
						start = 1;
						end = 2;
						
						break;
					case EXACTLY_X:
						start = quantifier.value;
						end = quantifier.value;
						
						break;
					case ZERO_OR_ONE:
						start = 0;
						end = 1;
						
						break;
					case RANGED:
						start = quantifier.value;
						end = quantifier.max;
						
						break;
				}
				
				State newQueueLast = queue.last;
				
				ArrayList<State> toConnect = new ArrayList<State>();
				
				if( start == 0 )
					toConnect.add(queue.head);
				
				for(long i = 1; i < end; i++)
				{
					StateQueue newQueue = replicateStateSequence(queue);
					
					if( i >= start && i < end )
						toConnect.add(newQueue.head);
					
					newQueueLast.connect(newQueue.head, false);
					
					newQueueLast = newQueue.last;
				}
				queue.last = newQueueLast;
				
				for(State s : toConnect)
				{
					s.connect(queue.last, true);
				}
			}
			
		}
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
			
			analysis(nodeChild);
			
			
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
