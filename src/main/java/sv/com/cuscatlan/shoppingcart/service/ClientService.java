package sv.com.cuscatlan.shoppingcart.service;

import sv.com.cuscatlan.shoppingcart.controller.request.ClientRequest;
import sv.com.cuscatlan.shoppingcart.controller.response.ClientResponse;

public interface ClientService {

    ClientResponse save(final ClientRequest clientRequest);

    ClientResponse getById(final Integer id);
}
