package domaine;

class QueryImpl implements Query {
    private String url;
    private HttpMethod httpMethod;

    public QueryImpl() {

    }

    public QueryImpl(String url, HttpMethod httpMethod) {
        this.url = url;
        this.httpMethod = httpMethod;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }
}
