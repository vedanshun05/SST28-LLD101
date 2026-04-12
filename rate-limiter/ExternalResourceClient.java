public class ExternalResourceClient {

    public String call(String payload) {
        // Simulates an external paid API call
        System.out.println("[ExternalResource] Called with: " + payload);
        return "response for: " + payload;
    }
}