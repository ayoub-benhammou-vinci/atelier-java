package domaine;


/**
 * This class implement the Query interface
 * We can create new object of type Query
 *
 * @author Ayoub
 */
class QueryImpl implements Query {
	
	private String url;
	private QueryMethod method;

	/**
	 * Construire un QueryImpl sans aucune valeur initialiser
	 */
	public QueryImpl() {
	}

	/**
	 * Construire un QueryImpl avec les valeurs mit en paramètres
	 * @param url d'un site internet
	 * @param method GET ou POST (énuméré)
	 */
	public QueryImpl(String url, QueryMethod method) {
		this.url = url;
		this.method = method;
	}

	/**
	 * Obetnir l'URL d'un QueryImpl
	 * @return url du QueryImpl
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Modifier l'URL d'une QueryImpl
	 * @param url d'un site internet
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Obtenir une méthode GET ou POST selon une QueryImpl
	 * @return la méthode GET ou POST
	 */
	public QueryMethod getMethod() {
		return method;
	}

	/**
	 * Modifier la méthode GET ou POST d'une QueryImpl
	 * @param method le type qu'on souhaite attribué
	 */
	public void setMethod(QueryMethod method) {
		this.method = method;
	}

	/**
	 * Modifier la méthode d'une Query
	 * @param method GET ou POST
	 * @throws IllegalArgumentException Si le nom de la méthode ne correspond pas à l'énuméré
	 * @deprecated
	 */
	@Override
	public void setMethod(String method) throws IllegalArgumentException {
		this.method = QueryMethod.valueOf(method);
	}

}
