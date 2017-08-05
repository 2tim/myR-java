package pricing;

public class Pricing {
    private final String id;
    private final String content;

    public Pricing(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
