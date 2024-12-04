package server;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import blacklist.BlacklistService;
import domaine.Query;
import domaine.Query.QueryMethod;
import domaine.QueryFactory;

public class ProxyServer {

    QueryFactory queryFactory;
    BlacklistService blacklistService;

    public ProxyServer(QueryFactory queryFactory, BlacklistService blacklistService) {
        this.queryFactory = queryFactory;
        this.blacklistService = blacklistService;
    }

    private final HttpClient client = HttpClient.newHttpClient();

    public void startServer() {


        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String url = scanner.nextLine();
                Query query = this.queryFactory.getQuery();

                //Injection par setter
                query.setMethod(QueryMethod.GET);
                query.setUrl(url);

                if(blacklistService.check(query)) {
                    QueryHandler queryHandler = new QueryHandler(query);
                    queryHandler.sendQueryAndPrintResponse();
                } else {
                    System.err.println("Error ! Domain is blacklisted");
                }
            }
        }
    }

}
