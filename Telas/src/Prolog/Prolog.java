package Prolog;
import org.jpl7.*;

public class Prolog {
	public static Prolog prolog = null;
	
	public Prolog(){
		Query query;
		query = new Query("consult", new Term[] {new Atom("Telas.pl")});
		System.out.println("consult " + (query.hasSolution() ? "succeeded" : "failed"));
	}
	
	public static Query doQuery(String string ) {
		return Prolog.doQuery(string, false );
	}
	
	public static Query doQuery(String string , boolean failAllowed ) {
		Query query = new Query(string);
		if( query.hasSolution() == false && failAllowed == false ) {
			//System.out.println( "DEBUG FALSE: " + string );
		}
		return query;
	}
}
