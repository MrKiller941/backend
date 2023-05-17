package infrastructure.interceptor;

import java.io.IOException;

import application.auth.IAuth;
import infrastructure.builder.Build.Built;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
@TokenRequired
public class Interceptor implements ContainerRequestFilter {

    @Inject @Built
    private IAuth tokenService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String login = requestContext.getHeaderString("login");
        String token = requestContext.getHeaderString("token");
        if (!tokenService.checkToken(login, token)) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }
    } 
}
