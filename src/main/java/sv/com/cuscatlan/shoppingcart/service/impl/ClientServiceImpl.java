package sv.com.cuscatlan.shoppingcart.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sv.com.cuscatlan.shoppingcart.controller.request.ClientRequest;
import sv.com.cuscatlan.shoppingcart.controller.response.ClientResponse;
import sv.com.cuscatlan.shoppingcart.repository.ClientRepository;
import sv.com.cuscatlan.shoppingcart.service.ClientService;
import sv.com.cuscatlan.shoppingcart.service.mapper.ClientMapper;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    @NonNull
    private final ClientRepository clientRepository;

    @NonNull
    private final ClientMapper clientMapper;

    @Override
    public ClientResponse save(final ClientRequest clientRequest) {
        return clientMapper.toClientResponse(clientRepository.save(clientMapper.toClient(clientRequest)));
    }

    @Override
    public ClientResponse getById(final Integer id) {
        return clientMapper.toClientResponse(
                clientRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }
}
