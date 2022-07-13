package com.forttiori.multimodulereactivewebflux.routes;

import com.forttiori.multimodulereactivewebflux.exception.routeerror.RouteErrorException;
import com.forttiori.multimodulereactivewebflux.exception.routeerror.RouteErrorResponse;
import com.forttiori.multimodulereactivewebflux.routes.model.request.RoutesIntegrationRequest;
import com.forttiori.multimodulereactivewebflux.routes.model.response.RoutesIntegrationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class RoutesIntegration {

	private static final String API_KEY_GOOGLE="AIzaSyBHPJ4BG_aO5nKPNdURmO-Es";
	private WebClient webClient;

    public Mono<RoutesIntegrationResponse> getRoutes(RoutesIntegrationRequest routesIntegrationRequest){

		return webClient.get().uri(uriBuilder -> uriBuilder.path("/distancematrix/json")
				.queryParam("origins",routesIntegrationRequest.getOriginAddress())
				.queryParam("destinations",routesIntegrationRequest.getDestinationAddress())
				.queryParam("units","metric")
				.queryParam("key",API_KEY_GOOGLE)
						.build())
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, (ClientResponse clientResponse)->
								      clientResponse.bodyToMono(RouteErrorResponse.class).map(RouteErrorException::new))
				.bodyToMono(RoutesIntegrationResponse.class);

    }
	//2 chamadas assincronas MONO       , zip     , 	tratamento exception na webclient(x)
	//doOn, doOn, doerror
	// contrato padrao de erros

}
