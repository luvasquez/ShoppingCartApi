package sv.com.cuscatlan.shoppingcart.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sv.com.cuscatlan.shoppingcart.controller.response.ProductResponse;
import sv.com.cuscatlan.shoppingcart.service.ProductService;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @NonNull
    private final RestTemplate restTemplate;

    @Value("${fakestore.url.products}")
    private String urlFake;

    @Override
    public List<ProductResponse> findAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent","Application");
        HttpEntity<List<ProductResponse>> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(urlFake, HttpMethod.GET, entity, new ParameterizedTypeReference<List<ProductResponse>>(){}).getBody();
    }

    @Override
    public ProductResponse findById(final Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent","Application");
        HttpEntity<ProductResponse> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(urlFake + "/" + id, HttpMethod.GET, entity, ProductResponse.class).getBody();
    }
}
