package domaine;
/**
 * This interface represents a basic HTTP query.
 * It contains also a QueryMethod Enum to represent the Method (GET, POST...)
 *
 * @author Kakuze
 */
public interface Query {

	/**
	 * Obetnir l'URL d'un QueryImpl
	 * @return url du QueryImpl
	 */
	String getUrl();

	/**
	 * Modifier l'URL d'une QueryImpl
	 * @param url d'un site internet
	 */
	void setUrl(String url);

	/**
	 * Obtenir une méthode GET ou POST selon une QueryImpl
	 * @return la méthode GET ou POST
	 */
	QueryMethod getMethod();

	/**
	 * Modifier la méthode GET ou POST d'une QueryImpl
	 * @param method le type qu'on souhaite attribué
	 */
	void setMethod(QueryMethod method);

	/**
	 * Modifier la méthode d'une Query
	 * @param method GET ou POST
	 * @throws IllegalArgumentException Si le nom de la méthode ne correspond pas à l'énuméré
	 * @deprecated Méthode à l'ancienne
	 */
	void setMethod(String method) throws IllegalArgumentException;

	/**
	 * Classe énuméré pour la obtenir une méthode GET ou POST
	 */
	public enum QueryMethod {
		GET, POST;
	}



}