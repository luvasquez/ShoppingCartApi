package sv.com.cuscatlan.shoppingcart.service.mapper;


import org.mapstruct.Mapper;
import sv.com.cuscatlan.shoppingcart.controller.request.ClientRequest;
import sv.com.cuscatlan.shoppingcart.controller.response.ClientResponse;
import sv.com.cuscatlan.shoppingcart.model.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toClient(final ClientRequest clientRequest);

    ClientResponse toClientResponse(final Client client);

}
