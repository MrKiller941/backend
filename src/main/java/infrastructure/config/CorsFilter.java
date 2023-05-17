package infrastructure.config;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.container.ContainerRequestContext;
import java.io.IOException;

@Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
      responseContext.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
          responseContext.getHeaders().putSingle("Access-Control-Allow-Credentials", "true");
          responseContext.getHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
          responseContext.getHeaders().putSingle("Access-Control-Allow-Headers", "*");
    }
}