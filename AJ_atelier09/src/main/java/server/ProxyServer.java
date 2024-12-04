package server;

import domaine.Query;
import domaine.QueryFactory;
import domaine.QueryFactoryImpl;

import java.util.Scanner;

public class ProxyServer {

    private QueryFactory queryFactory;

    public ProxyServer(QueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public void startServer(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Entrez l'URL de votre choix : ");
            try {
                String url = scanner.nextLine();

                Query query = this.queryFactory.getQuery();
                query.setUrl(url);
                query.setHttpMethod(Query.HttpMethod.GET);

                QueryHandler queryHandler = new QueryHandler(query);
                queryHandler.sendQueryAndPrintResponse();


            } catch (Exception e){
                System.out.println("Message d'erreur : " + e.getMessage());
            }
        }
    }

}
