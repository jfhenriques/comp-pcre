package org.comp.pcre.automata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import org.comp.pcre.automata.State.Connection;
import org.comp.pcre.grammar.ParseException;
import org.comp.pcre.grammar.SimpleNode;




public class NFACreator {

	public static class StateQueue
	{
		protected final State head;
		protected State last = null;
		protected ArrayList<State> orQueue = new ArrayList<State>(0);
		protected ArrayList<State> toConnect = new ArrayList<State>(0);
		
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
	//private int level = 0;
	
	
	public NFACreator()
	{
	}
	
	
	
	
	
	public void reset()
	{
		stateQueue.clear();
	}

	
	public StateQueue fromSimpleNode(SimpleNode root) throws ParseException
	{
		this.reset();
		
		stateQueue.addFirst(new StateQueue(new State()));
		
		iterate(root);
		
		stateQueue.element().last.isFinal = true;
		
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
			
			addTo.connect(newS, c.character, c.cyclic);
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

	
	
	private static void _connectAll(StateQueue queue, State s, String str)
	{
		if( queue.toConnect.size() > 0 )
		{
			for(State st: queue.toConnect)
			{
				st.isFinal = false;
				st.connect(s, str, false);
			}
			
			queue.toConnect.clear();
		}
	}
	private void analysis(SimpleNode node) throws ParseException
	{
		if( node == null || node.jjtGetValue() == null)
			return;
			
		String name = node.toString();
		StateQueue queue = stateQueue.element();
		
		if( name.equals("Chars") )
		{
			//System.out.println(level + " Chars");
			
			State s = new State();
			
			_connectAll(queue, s, node.jjtGetValue().toString());
			
			queue.last.connect(s, node.jjtGetValue().toString(), false);
			queue.last = s;
		}
//		else
//		if( name.equals("Expression") )
//		{
//			State s = new State();
//			
//
//			
//			queue.last.connect(s, "aa", false);
//			queue.last = s;
//		}
		else
		if( name.equals("CharTypes") )
		{
			if(node.jjtGetValue().toString().equals("."))
			{
				State s = new State();
				
				_connectAll(queue, s, node.jjtGetValue().toString());

				Connection nC = queue.last.connect(s, "<ANY>", false);
				
				nC.isAnyChar = true;
				queue.last = s;
			}
			else
				throw new ParseException("Invalid character received");
		}
		else
		if( name.equals("Quantifier") )
		{
			//System.out.println(level + " Quantifier");
			QuantifierState quantifier = (QuantifierState) node.jjtGetValue();

			long start = -1;
			long end = -1;
			
			switch( quantifier.type )
			{
				case ZERO_OR_MORE:
					queue.head.connect(queue.last, null, true);
					queue.last.connect(queue.head, null, true);
					
					return;
					
				case ONE_OR_MORE:
					queue.last.connect(queue.head, null, true);
					
					return;
					
				case ZERO_OR_ONE:
					queue.head.connect(queue.last, null, true);
					
					return;

					
				case EXACTLY_X:
					
					if( quantifier.value != null && quantifier.value < 1L )
						throw new ParseException("Quantifier repetition cannot be zero");
					
					start = quantifier.value;
					end = quantifier.value;
					
					break;

				case RANGED:
					
					if( quantifier.max != null && quantifier.max < 1L )
						throw new ParseException("Quantifier repetition cannot be zero");
					
					if( quantifier.value > quantifier.max )
						throw new ParseException("Quantifier minimum repetitions cannot be greater than max repetition");
					
					start = quantifier.value;
					end = quantifier.max;
					
					break;
			}
			

			State newQueueLast = queue.last;
			
			ArrayList<State> ttttoConnect = new ArrayList<State>();
			
			if( start == 0 )
				ttttoConnect.add(queue.head);
			
			for(long i = 1; i < end; i++)
			{
				StateQueue newQueue = replicateStateSequence(queue);
				
				if( i >= start && i < end )
					ttttoConnect.add(newQueue.head);
				
				newQueueLast.connect(newQueue.head, null, false);
				
				newQueueLast = newQueue.last;
			}
			queue.last = newQueueLast;
			
			for(State s : ttttoConnect)
			{
				if( queue.last != null )
					s.connect(queue.last, null, true);
			}
			
		}
	}
	

	
	
	public State iterate(SimpleNode root) throws ParseException
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
				boolean isOR = nodeChild.toString().equals("OR");
				
				if( isOR )
				{
					cur.orQueue.add(cur.last);
					cur.last.isFinal = true;
//					State newS = new State();
//					cur.head.connect(newS, null, false);
//					stateQueue.addFirst(new StateQueue(newS));
					
					stateQueue.addFirst(new StateQueue(cur.head));
				}
				else
				{
					StateQueue sq = new StateQueue(cur.last);
					if( cur.toConnect.size() > 0)
					{
						sq.toConnect.addAll(cur.toConnect);
						cur.toConnect.clear();
					}
					stateQueue.addFirst(sq);
				}
				
				
				cur.last = iterate(nodeChild);
				
				
//				if( cur.toConnect.size() > 0 )
//				{
//					for(State s: cur.toConnect)
//						s.connect(cur.last, null, false);
//					
//					cur.toConnect.clear();
//				}
				
//				if( isOR )
//				{
//					cur.last.isFinal = true;
//					cur.orQueue.add(cur.last);
//				}
				
				StateQueue last = stateQueue.remove();
				if( last != null )
				{
					if( last.orQueue.size() > 0 )
						cur.toConnect.addAll(last.orQueue);
					
					if( last.toConnect.size() > 0 )
						cur.toConnect.addAll(last.toConnect);
				}
			}
		}
		
		return cur.last;
	}
}
