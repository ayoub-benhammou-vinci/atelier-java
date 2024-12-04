package domaine;

public interface Query {
    String getUrl();

    HttpMethod getHttpMethod();

    public enum HttpMethod {
        GET, POST
    }

    public void setUrl(String url);
    public void setHttpMethod(HttpMethod method);
}
