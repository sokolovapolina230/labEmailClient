package emailclient.decorator;

public class ImportantDecorator extends MessageProcessorDecorator {

    public ImportantDecorator(MessageProcessor processor) {
        super(processor);
    }

    @Override
    public String process(String content) {
        String base = super.process(content);
        return "[IMPORTANT]\n" + base;
    }
}
