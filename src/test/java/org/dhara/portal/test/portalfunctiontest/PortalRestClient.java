package org.dhara.portal.test.portalfunctiontest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import org.dhara.portal.test.exception.PortalException;

/**
 * Rest client for access portal resources
 */
public class PortalRestClient {

    private PortalConfiguration portalConfiguration;

    public PortalRestClient() throws PortalException {
         portalConfiguration=new PortalConfiguration();
    }
    public String getResponse(String url) {
        try {
            Client client = Client.create();
            WebResource webResource = client
                    .resource(portalConfiguration.getServerUrl()+url);
            client.addFilter(new HTTPBasicAuthFilter(portalConfiguration.getUserName(), portalConfiguration.getPassword()));
            ClientResponse response = webResource.accept("application/text")
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            return response.getEntity(String.class);

        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }
}
