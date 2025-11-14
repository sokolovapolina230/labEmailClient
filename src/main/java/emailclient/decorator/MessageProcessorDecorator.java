package emailclient.decorator;

public abstract class MessageProcessorDecorator implements MessageProcessor {
    protected final MessageProcessor processor;

    protected MessageProcessorDecorator(MessageProcessor processor) {
        this.processor = processor;
    }

    @Override
    public String process(String content) {
        return processor.process(content);
    }
}
