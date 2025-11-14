package emailclient.decorator;

public class SignatureDecorator extends MessageProcessorDecorator {

    public SignatureDecorator(MessageProcessor processor) {
        super(processor);
    }

    @Override
    public String process(String content) {
        String base = super.process(content);
        return base + "\n-- Sent by EmailClient";
    }
}
