package main;

import domaine.Query;
import domaine.QueryFactory;
import domaine.QueryFactoryImpl;
import server.ProxyServer;
import server.QueryHandler;

public class Main {
    public static void main(String[] args) {
        QueryFactory query = new QueryFactoryImpl();
        ProxyServer proxyServer = new ProxyServer(query);
        proxyServer.startServer();
    }



}
