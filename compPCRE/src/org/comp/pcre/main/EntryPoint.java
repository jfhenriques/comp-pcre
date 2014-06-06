package org.comp.pcre.main;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.comp.pcre.automata.GraphHelper;
import org.comp.pcre.automata.NFACreator;
import org.comp.pcre.automata.State;
import org.comp.pcre.automata.State.Connection;
import org.comp.pcre.grammar.PCRE;
import org.comp.pcre.grammar.ParseException;
import org.comp.pcre.grammar.SimpleNode;

public class EntryPoint {

	public static void main( String[] args ) throws ParseException
	{ 
		InputStream is = null;
		try {
			is = new ByteArrayInputStream( "a*(b*a){1,2}".getBytes( "UTF-8" ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		  PCRE parser = new PCRE( is );//System.in ) ;
		  
		  SimpleNode root = parser.Start() ;
		  System.out.println("Val:"+root.toString());
		  root.dump("");
		  
		  
		  NFACreator creator = new NFACreator();
		  State nfaRoot = creator.fromSimpleNode(root);
		  ArrayList<Connection> allConns = new ArrayList<Connection>();
		  ArrayList<State> allStates = new ArrayList<State>();
		  GraphHelper.dumpSingleArrayS(nfaRoot, allConns, allStates);
		  GraphHelper.generateGraph(allConns, allStates, "out", "png", true);
		
		
	}
}
