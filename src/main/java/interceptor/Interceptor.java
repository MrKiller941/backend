package interceptor;

import java.io.IOException;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import token.IToken;

@Provider
@TokenRequired
public class Interceptor implements ContainerRequestFilter {

    @Inject
    private IToken tokenService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String login = requestContext.getHeaderString("login");
        String token = requestContext.getHeaderString("token");
        if (!tokenService.checkToken(login, token)) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }
    } 
}
