package blacklist;

import domaine.Query;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class BlacklistServiceImpl implements BlacklistService {

    private static Set<String> blacklistedDomains;

    static {
        Properties properties = new Properties();

        try (FileInputStream inputStream = new FileInputStream("blacklist.properties")) {
            try {
                properties.load(inputStream);
                String domains = properties.getProperty("blacklistedDomains");
                String[] domainsTab = domains.split(";");

                blacklistedDomains = Set.of(domainsTab);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean check(Query query){
        for (String domain : blacklistedDomains) {
            if(query.getUrl().equals(domain)){
                return false;
            }
        }
        return true;

        //Version stream : !blacklistedDomains.stream().anyMatch(d -> query.getUrl().contains(d));
    }

    
}
