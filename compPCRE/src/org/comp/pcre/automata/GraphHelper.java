package org.comp.pcre.automata;

import java.io.File;
import java.util.ArrayList;

import org.comp.pcre.automata.State.Connection;
import org.comp.pcre.automata.State;


public class GraphHelper {
	
	private GraphHelper()
	{
	}
	
	
	private static void dumpConnectionsToSingleArray(State root, ArrayList<Connection> array)
	{
		root.visited = true;
		for(Connection c: root.connections)
		{
			array.add(c);
			
			if( c.to != null && !c.to.visited )
				dumpConnectionsToSingleArray(c.to, array);
		}
	}
	
	public static void dumpSingleArrayS(State root, ArrayList<Connection> connections, ArrayList<State> states)
	{
		if( connections == null || states == null)
			return;
		
		connections.clear();
		states.clear();
		
		dumpConnectionsToSingleArray(root, connections);
		

		ArrayList<Long> map = new ArrayList<Long>();
		
		for( Connection c : connections)
		{
			if( !map.contains(c.to.name) )
			{
				map.add(c.to.name);
				states.add(c.to);
			}
		}
	}
	
	
	public static void generateGraph(ArrayList<Connection> connections,
										ArrayList<State> states,
										String filename, String type, Boolean printConsole) 
    {
        String finalStates = "";

        // Define GraphViz
        GraphViz gv = new GraphViz();
        gv.addln(gv.start_graph());
        gv.addln("rankdir=LR; node [shape = point, color=white, fontcolor=white]; start;");


        // Add final states to declaration
        for ( State s : states )
        {
            if ( s.isFinal ) 
            {
                finalStates += s.name + " ";
            }
        }

        if ( !finalStates.equals("") )
        	gv.addln("node [shape = doublecircle, color=black, fontcolor=black]; " + finalStates + ";");
        //gv.addln("node [shape = doublecircle, color=black, fontcolor=black]; " + endState.name);

        gv.addln("node [shape = circle];");
        gv.addln("start -> 0;");

        // Connections
        for(Connection c : connections)
        {
        	gv.addln( c.from.name + " -> " + c.to.name + " [ label = \"" + ( c.character == null ? "< E >" : c.character ) + "\" ];" );
        }

	    gv.addln(gv.end_graph());

        // Print to console	    
        if ( printConsole )
        	System.out.println(gv.getDotSource());		
	
	    File out = new File("output/" + filename + "." + type);
	    gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	}

}
