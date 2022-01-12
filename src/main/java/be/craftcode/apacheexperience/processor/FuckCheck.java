package be.craftcode.apacheexperience.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class FuckCheck implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        log.info("COUNT: {}", StringUtils.countMatches(exchange.getIn().getBody(String.class), "fuck") +
                StringUtils.countMatches(exchange.getIn().getBody(String.class), "Fuck"));
    }
}
