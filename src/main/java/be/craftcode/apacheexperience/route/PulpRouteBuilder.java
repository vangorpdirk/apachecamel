package be.craftcode.apacheexperience.route;

import be.craftcode.apacheexperience.processor.FuckCheck;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PulpRouteBuilder extends RouteBuilder {
    @Override
    public void configure()  {
        from("{{sftp.server}}")
                .log("** Connected to poll folder")
                .bean(FuckCheck.class)
                .to("{{sftp.out.server}}");
    }
}
