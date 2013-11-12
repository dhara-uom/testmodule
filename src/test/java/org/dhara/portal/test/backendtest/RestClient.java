package org.dhara.portal.test.backendtest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Created with IntelliJ IDEA.
 * User: harsha
 * Date: 10/27/13
 * Time: 9:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class RestClient {

    public String getResponse(String url) {
        try {
            Client client = Client.create();
            WebResource webResource = client
                    .resource(url);
            ClientResponse response = webResource.accept("application/json")
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
