package congressional_data.adapters.out;

import javax.ws.rs.*;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Path("/")
@RegisterRestClient(configKey = "my-api")
public interface MultipartService {

    @GET
    @Path("/{congressionalHouse}/{zipName}")
    @Produces("application/zip")
    File grabZip(@PathParam("congressionalHouse") String congressionalHouse, @PathParam("zipName") String zipName);

    @GET
    @Path("/{congressionalHouse}/{zipName}")
    @Produces("application/zip")
    CompletionStage<File> grabZipAsync(@PathParam("congressionalHouse") String congressionalHouse, @PathParam("zipName") String zipName);

}
