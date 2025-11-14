package emailclient.decorator;

public class BasicMessageProcessor implements MessageProcessor {
    @Override
    public String process(String content) {
        return content;
    }
}
