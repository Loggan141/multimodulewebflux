import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;
import com.forttiori.multimodulereactivewebflux.routes.RoutesIntegration;
import stub.RouteStub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = RoutesIntegration.class)
class RoutesIntegrationTest {
    @InjectMocks
    private RoutesIntegration routesIntegration;

    private static ClientAndServer mockServer;

    ObjectMapper objectMapper = new ObjectMapper();
    @BeforeAll
    static void startServer(){
        mockServer = startClientAndServer();
    }

    @BeforeEach
    void setupClass() {
        WebClient webClient = WebClient.builder()
                .baseUrl(String.format("http://localhost:%d", mockServer.getPort()))
                .build();

        routesIntegration = new RoutesIntegration(webClient);
    }

    @AfterAll
    static void stopServer(){
        mockServer.close();
    }
    @Test
    void whenFindRouteReturnsRouteResponse() throws JsonProcessingException {
        var expectRoute = RouteStub.expectedRouteIntegrationResponse();

        HttpRequest request =  HttpRequest.request()
                .withPath("/distancematrix/json")
                .withQueryStringParameter("origins",RouteStub.routesIntegrationRequest().getOriginAddress())
                .withQueryStringParameter("destinations",RouteStub.routesIntegrationRequest().getDestinationAddress())
                .withQueryStringParameter("units","metric")
                .withQueryStringParameter("key","AIzaSyBHPJ4BG_aSyl4DAul3lO5nKPNdURmO-Es")
                .withMethod("GET");

        var bodyExpectedRoute = objectMapper.writeValueAsString(expectRoute);

        HttpResponse response = HttpResponse.response()
                .withBody(bodyExpectedRoute)
                .withStatusCode(HttpStatus.OK.value())
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());

        mockServer.when(request).respond(response);

        StepVerifier.create(routesIntegration.getRoutes(RouteStub.routesIntegrationRequest()))
                .assertNext(routesIntegrationResponse -> assertEquals(expectRoute, routesIntegrationResponse))
                .verifyComplete();
    }

}