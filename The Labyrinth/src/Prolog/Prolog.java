package Prolog;

import org.jpl7.*;

public class Prolog {
	
	public Prolog(){
		Query query;
		query = new Query("consult", new Term[] {new Atom("Mapa.pl")});
		System.out.println("consult " + (query.hasSolution() ? "succeeded" : "failed"));
	}
	
	public static Query doQuery(String string ){
		Query query = null;
		try {
			query = Prolog.doQuery(string, false );
		} catch (FalseQuery e) {
			
		}
		finally {
			return query;
		}
		
	}
	
	public static Query doQuery(String string , boolean failAllowed ) throws FalseQuery {
		Query query = new Query(string);
		if( query.hasSolution() == false && failAllowed == true ) {
			throw new FalseQuery();
		}
		return query;
	}
}
