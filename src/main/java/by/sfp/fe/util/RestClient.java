package by.sfp.fe.util;

import by.sfp.fe.domain.DomainCategory;
import org.glassfish.jersey.client.ClientConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static java.lang.String.format;
import static java.lang.String.join;
import static javax.ws.rs.client.Entity.json;

@Component
public class RestClient {
    private ClientConfig clientConfig;
    private Client client;
    private WebTarget webTarget;

    public RestClient() {
        this.clientConfig = new ClientConfig();
        this.client = ClientBuilder.newClient(clientConfig);
        this.webTarget = client.target("http://localhost:5555");
    }

    public List<DomainCategory> getDomainCategories() {
        return webTarget.path("domainCategories")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get()
                .readEntity(new GenericType<List<DomainCategory>>() {});
    }

    public void addNewClassCategory(Long id, String name) {
        webTarget.path(format("domainCategories/%s/classCategories", id))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(json(
                        join("", "{\"name\" : \"", name, "\"}")
                ));
    }
}
