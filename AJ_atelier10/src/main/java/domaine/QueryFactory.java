package domaine;

/**
 * This interface represent the QueryFactory
 * It's necessary to use this interface to maintains the project
 *
 * @author Kakuze
 */
public interface QueryFactory {

	/**
	 * Créer une nouvelle instance de type Query
	 * @return la nouvelle Query crée
	 */
	Query getQuery();

}