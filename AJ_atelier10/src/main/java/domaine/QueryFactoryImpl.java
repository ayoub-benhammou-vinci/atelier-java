package domaine;

/**
 * This class implement interface QueryFactory
 * We can use it to create new Object of type QueryFactory
 *
 * @author Ayoub
 */
public class QueryFactoryImpl implements QueryFactory {
	
	/* (non-Javadoc)
	 * @see domaine.QueryFactory#getQuery()
	 */

	/**
	 * Créer une nouvelle QueryImpl
	 * @return la nouvelle QueryImpl
	 */
	@Override
	public Query getQuery() {
		return new QueryImpl();
	}

}
