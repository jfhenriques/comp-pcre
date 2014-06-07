package org.comp.pcre.automata;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

import org.comp.pcre.automata.State.Connection;


public class CodeGeneration {

	///*
	public static class InvalidStateException extends Exception
	{
		private static final long serialVersionUID = -3540825325077073436L;
		public InvalidStateException(String message) { super(message); }
	}
	public static class Transition {
		public long state = -1; public String character = null;
		public Transition(long state, String character)
		{ this.state = state; this.character = character; }
	}
	public static class AnyTransition extends Transition {
		public AnyTransition(long state, String character)
		{ super(state,character); }
	}
	
	public static boolean isFinal(long state)
	{ return _finalMap.contains(state); }
	
	private static boolean putNull(int pos, long state)
	{
		ArrayList<Long> list = _nullStates.get(pos);
		if( list == null ) {
			list = new ArrayList<Long>();
			_nullStates.put(pos, list);
		}
		if( list.contains(state) ) return true;
		list.add(state);
		return false;
	}
//	private static void removeNull(int pos, long state)
//	{
//		ArrayList<Long> list = _nullStates.get(pos);
//		if( list != null )
//			list.remove(state);
//	}
	
	public static boolean accept(String str, int pos, int total, long curState) throws InvalidStateException
	{
		boolean isFinalChar = (pos == total);
		if(isFinalChar && isFinal(curState)) return true;
		Transition[] _states = _stateMap.get(curState);
		if(_states == null) throw new InvalidStateException("Invalid state transition");
		if( !isFinalChar ) {
			for(Transition t: _states) {
				if( t.getClass() == AnyTransition.class || ( t.character != null && str.charAt(pos) == t.character.charAt(0) ) ) {
					System.out.println("Pos["+pos+"]"+t.state+"-"+t.character);
					if( accept(str, pos + 1, total, t.state) ) return true;
				}
			}
		}
		for(Transition t: _states) {
			if( t.character == null ) {
				System.out.println("Pos["+pos+"]"+curState + "-" + t.state+"-null");
				if(putNull(pos, t.state)) continue;
				if( accept(str, pos, total, t.state) ) return true;
			}
		}
		return false;
	}
	
	private static void _loadMaps() {
		_stateMap.put(0L,new Transition[]{new Transition(1L,"a"),new Transition(11L,null),});
		_stateMap.put(1L,new Transition[]{new Transition(2L,"b"),new Transition(4L,null),});
		_stateMap.put(2L,new Transition[]{new Transition(3L,"c"),new Transition(3L,null),});
		_stateMap.put(3L,new Transition[]{new Transition(2L,null),new Transition(4L,"d"),});
		_stateMap.put(4L,new Transition[]{new Transition(1L,null),new Transition(5L,"e"),});
		_stateMap.put(5L,new Transition[]{new Transition(6L,null),});
		_stateMap.put(6L,new Transition[]{new Transition(7L,"a"),new Transition(11L,null),});
		_stateMap.put(7L,new Transition[]{new Transition(8L,"b"),new Transition(10L,null),});
		_stateMap.put(8L,new Transition[]{new Transition(9L,"c"),new Transition(9L,null),});
		_stateMap.put(9L,new Transition[]{new Transition(8L,null),new Transition(10L,"d"),});
		_stateMap.put(10L,new Transition[]{new Transition(7L,null),new Transition(11L,"e"),});
		_finalMap.add(11L);
	}
	
	
	
	
	private static final HashMap<Long, Transition[]> _stateMap = new HashMap<Long, Transition[]>();
	private static final ArrayList<Long> _finalMap = new ArrayList<Long>();
	private static final HashMap<Integer, ArrayList<Long>> _nullStates = new HashMap<Integer, ArrayList<Long>>();


	
	public static void main( String[] args ) throws IOException {
		_loadMaps();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			_nullStates.clear();
			System.out.println("Accepting expression: ");
			String toAccept = br.readLine();
			try {
				if( accept(toAccept, 0, toAccept.length(), 0) ) {
					System.out.println("-> Input accepted");
					continue;
				}
			} catch(InvalidStateException ise) { }
			System.out.println("-> Input could not be accepted");
		}
	}
	//*/
	
	
	
	/************************************************************************************************
	 * 
	 * 
	 ************************************************************************************************/
	
	
	
	
	
	private final static Charset CHARSET = Charset.forName("UTF-8");
	private static BufferedOutputStream outS = null;
	
	private static void apd(int tabs, String text) throws IOException
	{
		if( text != null)
		{
			for(int i = 0; i <tabs; i++)
				outS.write('\t');
			
			outS.write(text.getBytes(CHARSET));
		}
	}
	
	public static void _dumpStates(State s) throws IOException
	{
		if(s.connections.size() == 0 )
			return;
		
		apd(2, "_stateMap.put(" + s.name + "L," );
		apd(0, "new Transition[]{" );
		
		for(Connection c: s.connections)
		{
			String cchar = null;
			
			if( c.character == null )
				cchar = "null";
			else
			if( c.character.length() > 1 && c.character.charAt(0) == '\\')
				cchar = "\"" + c.character.charAt(1) + "\"";
			else
				cchar = "\"" + c.character + "\"";
			
			apd(0, "new " + (c.isAnyChar ? "Any" : "")+ "Transition(" + c.to.name + "L," + cchar + ")," );
		}
		apd(0, "});\n");
	}
	
	public static void _dumpFinal(State s) throws IOException
	{
		if( s.isFinal )
			apd(2, "_finalMap.add(" + s.name + "L);\n");
	}
	
	public static String fromAutomata(String className, String expression, State root, ArrayList<State> allStates) throws IOException
	{
		
		try(OutputStream writeOut = new FileOutputStream(new File("output/" + className + ".java"));
			BufferedOutputStream out = new BufferedOutputStream(writeOut))
		{
			outS = out;
			
			
			apd(0, "import java.io.BufferedReader;\n");
			apd(0, "import java.io.IOException;\n");
			apd(0, "import java.io.InputStreamReader;\n");
			apd(0, "import java.util.ArrayList;\n");
			apd(0, "import java.util.HashMap;\n\n");
			
			
			apd(0, "public class " + className + " {\n\n");
			
			apd(1, "private static final HashMap<Long, Transition[]> _stateMap = new HashMap<Long, Transition[]>();\n");
			apd(1, "private static final ArrayList<Long> _finalMap = new ArrayList<Long>();\n");
			apd(1, "private static final HashMap<Integer, ArrayList<Long>> _nullStates = new HashMap<Integer, ArrayList<Long>>();\n\n");
			
			apd(1, "public static class InvalidStateException extends Exception {\n");
			apd(2, "private static final long serialVersionUID = -3540825325077073436L;\n");
			apd(2, "public InvalidStateException(String message) { super(message); }\n\t}\n\n");
			
			apd(1, "public static class Transition {\n");
			apd(2, "public long state = -1; public String character = null;\n");
			apd(2, "public Transition(long state, String character)\n");
			apd(2, "{ this.state = state; this.character = character; }\n");
			apd(1, "}\n\n");
			
			apd(1, "public static class AnyTransition extends Transition {\n");
			apd(2, "public AnyTransition(long state, String character)\n");
			apd(2, "{ super(state,character); }\n");
			apd(1, "}\n\n");
			
			apd(2, "public static boolean isFinal(long state)\n");
			apd(2, "{ return _finalMap.contains(state); }\n\n");
			
			apd(1, "private static boolean putNull(int pos, long state) {\n");
			apd(2, "ArrayList<Long> list = _nullStates.get(pos);\n");
			apd(2, "if( list == null ) {\n");
			apd(3, "list = new ArrayList<Long>();\n");
			apd(3, "_nullStates.put(pos, list);\n");
			apd(2, "}\n");
			apd(2, "if( list.contains(state) ) return true;\n");
			apd(2, "list.add(state);\n");
			apd(2, "return false;\n");
			apd(1, "}\n\n");
			
			apd(1, "public static boolean accept(String str, int pos, int total, long curState) throws InvalidStateException {\n");
			apd(2, "boolean isFinalChar = (pos == total);\n");
			apd(2, "if(isFinalChar && isFinal(curState)) return true;\n");
			apd(2, "Transition[] _states = _stateMap.get(curState);\n");
			apd(2, "if(_states == null) throw new InvalidStateException(\"Invalid state transition\");\n");
			apd(2, "if( !isFinalChar ) {\n");
			apd(3, "for(Transition t: _states) {\n");
			apd(4, "if( t.getClass() == AnyTransition.class || ( t.character != null && str.charAt(pos) == t.character.charAt(0) ) ) {\n");
			apd(5, "//System.out.println(\"Pos[\"+pos+\"]\"+t.state+\"-\"+t.character);\n");
			apd(5, "if( accept(str, pos + 1, total, t.state) ) return true;\n");
			apd(4, "}\n");
			apd(3, "}\n");
			apd(2, "}\n");
			apd(2, "for(Transition t: _states) {\n");
			apd(3, "if( t.character == null ) {\n");
			apd(4, "//System.out.println(\"Pos[\"+pos+\"]\"+curState + \"-\" + t.state+\"-null\");\n");
			apd(4, "if(putNull(pos, t.state)) continue;\n");
			apd(4, "if( accept(str, pos, total, t.state) ) return true;\n");
			apd(3, "}\n");
			apd(2, "}\n");
			apd(2, "return false;\n");
			apd(1, "}\n\n");
			
			apd(1, "private static void _loadMaps() {\n");
	
			_dumpStates(root);
			_dumpFinal(root);
			for(State s : allStates)
			{
				_dumpStates(s);
				_dumpFinal(s);
			}

			apd(1, "}\n\n" );
			
			apd(1, "public static void main( String[] args ) throws IOException {\n");
			apd(2, "_loadMaps();\n");
			apd(2, "BufferedReader br = new BufferedReader(new InputStreamReader(System.in));\n");
			apd(2, "while(true) {\n");
			apd(3, "_nullStates.clear();\n");
			apd(3, "System.out.println(\"Accepting expression: " + expression.replaceAll("\\\\", "\\\\\\\\") + "\");\n");
			apd(3, "String toAccept = br.readLine();\n");
			apd(3, "try {\n");
			apd(4, "if( accept(toAccept, 0, toAccept.length(), 0) ) {\n");
			apd(5, "System.out.println(\"** Input accepted **\\n\");\n");
			apd(5, "continue;\n");
			apd(4, "}\n");
			apd(3, "} catch(InvalidStateException ise) { }\n");
			apd(3, "System.out.println(\"## Input could not be accepted ##\\n\");\n");
			apd(2, "}\n");
			apd(1, "}\n\n");
			
			apd(0, "}\n");
		}
		
		return null;
	}
}
