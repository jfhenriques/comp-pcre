package org.comp.pcre.automata;

import org.comp.pcre.automata.State.Connection;
import org.comp.pcre.grammar.SimpleNode;




public class NFA {

	
	private NFA()
	{
	}
	
	private static State globalCurState = null;

	
	public static State fromSimpleNode(SimpleNode root)
	{
		State rootState = new State();
		
		globalCurState = rootState;
		
		iterate(root);
		
		//GraphViz a = new GraphViz();
		
		return rootState;
	}
	
	
	
	private static Connection analysis(State stateRoot, SimpleNode node)
	{
		if(node.toString().equals("Chars")
			&& node.jjtGetValue() != null )
		{
			State s = new State();
			Connection c = new Connection(stateRoot, s);
			
			c.character =  node.jjtGetValue().toString();
			c.to = s;
			
			return c;
		}
		
		return null;
	}
	
	
	private static void iterate(SimpleNode root)
	{
		SimpleNode nodeChild = null;
		
		State curRoot = globalCurState;
		
		for(int i=0;i<root.jjtGetNumChildren();i++)
		{
			nodeChild = (SimpleNode) root.jjtGetChild(i);
			
			if( nodeChild == null )
				continue;
			
			Connection s = analysis(curRoot, nodeChild);
			if( s != null )
			{
				curRoot.connections.add(s);
				curRoot = s.to;
				
				globalCurState = curRoot;
			}
			
			/*if(nodeChild.toString().equals("Expression")){
				//analysis(nodeChild);
			}
			else if(nodeChild.toString().equals("PRT")){
				System.out.println(nodeChild.toString());
				//analysis(nodeChild);
			}
			else{ //SIMBOLO
				nfa.add(id,new No((String) nodeChild.jjtGetValue()));
				nfa.get(id).addLink(id+1);
				System.out.println(nodeChild.toString()+nodeChild.jjtGetValue());
			}*/
			
			if( nodeChild.jjtGetNumChildren() > 0 )
				iterate(nodeChild);
		}
		
		
	}
}
