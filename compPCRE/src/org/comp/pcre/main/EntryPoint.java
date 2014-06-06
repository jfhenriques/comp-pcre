package org.comp.pcre.main;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.comp.pcre.grammar.PCRE;
import org.comp.pcre.grammar.ParseException;
import org.comp.pcre.grammar.SimpleNode;

public class EntryPoint {

	public static void main( String[] args ) throws ParseException
	{ 
		InputStream is = null;
		try {
			is = new ByteArrayInputStream( "c:\\Windows\n".getBytes( "UTF-8" ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		  PCRE parser = new PCRE( is );//System.in ) ;
		  
		  SimpleNode val = parser.Start() ;
		  System.out.println("Val:"+val.toString());
		  val.dump("");
		
		
	}
}
