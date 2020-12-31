package congressional_data.adapters.out;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import congressional_data.application.ports.out.LoadCongressionalBillsPort;
import congressional_data.domain.CongressionalBill;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.zeroturnaround.zip.ZipUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import javax.ws.rs.*;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/")
public class LoadCongressionalBillsAdapter implements LoadCongressionalBillsPort {

    @Inject
    @RestClient
    MultipartService multipartService;

    List<CongressionalBill> congressionalBills = new ArrayList<>();

    XmlMapper xmlMapper = new XmlMapper();

    @Override
    public List<CongressionalBill> getAllCongressionalBills() {
        if (this.congressionalBills.isEmpty()) {
            List<CompletableFuture<File>> zips = new ArrayList<>();
            for (BillStartLocations billStartLocation: BillStartLocations.values()) {
                CompletionStage<File> zip = grabZipAsync(billStartLocation.name(), "BILLSTATUS-116-" + billStartLocation + ".zip");
                zip.thenApplyAsync((File file) -> {
                    unzipAsync(file.getAbsolutePath());
                    return file;
                }).thenAcceptAsync((File file) -> {
                    List<CongressionalBill> congressionalBills = deserializeCongressionalBillsInFolder(file);
                    this.congressionalBills.addAll(congressionalBills);
                });
                zips.add((CompletableFuture<File>)zip);
            }
            zips.forEach(CompletableFuture::join);
        }
        return this.congressionalBills;
    }



    @GET
    @Path("/{congressionalHouse}/{zipName}")
    @Produces("application/zip")
    public File grabZip(@PathParam("congressionalHouse") String congressionalHouse, @PathParam("zipName") String zipName) {
        return multipartService.grabZip(congressionalHouse, zipName);
    }

    @GET
    @Path("/{congressionalHouse}/{zipName}")
    @Produces("application/zip")
    public CompletionStage<File> grabZipAsync(@PathParam("congressionalHouse") String congressionalHouse, @PathParam("zipName") String zipName) {
        return multipartService.grabZipAsync(congressionalHouse, zipName);
    }

    private void unzipAsync(String congressionalBillsZipFilepath) {
        ZipUtil.explode(new File(congressionalBillsZipFilepath));
    }


        private List<CongressionalBill> deserializeCongressionalBillsInFolder(File folder) {
            return Arrays.stream(folder.listFiles()).parallel().map((File file) -> {
                CongressionalBill congressionalBill = null;
                try {
                    byte[] rawBillContents = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
                    String readContent = new String(rawBillContents);
                    congressionalBill = xmlMapper.readValue(readContent, CongressionalBill.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return congressionalBill;
            }).collect(Collectors.toList());
        }

}
