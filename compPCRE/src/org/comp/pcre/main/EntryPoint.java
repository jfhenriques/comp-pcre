package org.comp.pcre.main;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.comp.pcre.automata.CodeGeneration;
import org.comp.pcre.automata.GraphHelper;
import org.comp.pcre.automata.NFACreator;
import org.comp.pcre.automata.State;
import org.comp.pcre.automata.NFACreator.StateQueue;
import org.comp.pcre.automata.State.Connection;
import org.comp.pcre.grammar.PCRE;
import org.comp.pcre.grammar.ParseException;
import org.comp.pcre.grammar.SimpleNode;

public class EntryPoint {

	public static void main( String[] args ) throws ParseException
	{ 
		InputStream is = null;
		String expression = "(a(bc*d)*e){0,2}";
		try {
			is = new ByteArrayInputStream( expression.getBytes( "UTF-8" ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		  PCRE parser = new PCRE( is );
		  
		  SimpleNode root = parser.Start() ;
		  System.out.println("Val:"+root.toString());
		  root.dump("");
		  
		  
		  NFACreator creator = new NFACreator();
		  
		  StateQueue state = creator.fromSimpleNode(root);
		  ArrayList<Connection> allConns = new ArrayList<Connection>();
		  ArrayList<State> allStates = new ArrayList<State>();
		  
		  GraphHelper.dumpSingleArrayS(state.getHead(), allConns, allStates);
		  GraphHelper.generateGraph(allConns, allStates, "out", "png", true);
		
		try {
			CodeGeneration.fromAutomata("Testing", expression, state.getHead(), allStates);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
