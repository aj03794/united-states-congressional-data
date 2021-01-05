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
import java.util.*;
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
    public List<CongressionalBill> getAllCongressionalBills(List<String> congressionalSessions) {
        if (this.congressionalBills.isEmpty()) {
            Map<String, String> partOneOfServiceCall = createPartOneOfServiceCall(congressionalSessions);
            List<String> stringsForServiceCalls = createPartTwoOfServiceCall(partOneOfServiceCall);
            return this.congressionalBills = stringsForServiceCalls
                    .stream()
                    .parallel()
                    .map(this::grabZipAsync)
                    .map(fileCompletionStage -> fileCompletionStage.toCompletableFuture().join())
                    // Peek returns file when unzipAsync returns void
                    .peek(file -> unzipAsync(file.getAbsolutePath()))
                    .map(this::deserializeCongressionalBillsInFolder)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        }
        return this.congressionalBills;
    }

    private Map<String, String> createPartOneOfServiceCall(List<String> congressionalNames) {
        Map<String, String> map = new HashMap<>();
        for (String congressionalName: congressionalNames) {
            map.put(congressionalName, "BILLSTATUS-" + congressionalName + "-");
        }
        return map;
    }

    private List<String> createPartTwoOfServiceCall(Map<String, String> partiallyMadeNames) {
        List<String> stringsForServiceCall = new ArrayList<>();
        for (Map.Entry<String, String> entry: partiallyMadeNames.entrySet()) {
            for (BillStartLocations billStartLocation: BillStartLocations.values()) {
                stringsForServiceCall.add(entry.getKey() + "/" + billStartLocation + "/" + entry.getValue() + billStartLocation + ".zip");
            }
        }
        return stringsForServiceCall;
    }

    @GET
    @Path("/{congressionalHouse}/{zipName}")
    @Produces("application/zip")
    public File grabZip(@PathParam("congressionalHouse") String congressionalHouse, @PathParam("zipName") String zipName) {
        System.out.println("Downloading zip: " + zipName + ", Thread: " + Thread.currentThread().getName());
        return multipartService.grabZip(congressionalHouse, zipName);
    }

    @GET
    @Path("/{zip}")
    @Produces("application/zip")
    public CompletionStage<File> grabZipAsync(@PathParam("zip") String zip) {
        System.out.println("Downloading zip: " + zip + ", Thread: " + Thread.currentThread().getName());
        return multipartService.grabZipAsync(zip);
    }

    private void unzipAsync(String congressionalBillsZipFilepath) {
        System.out.println("Unzipping: " + congressionalBillsZipFilepath + ", Thread: " + Thread.currentThread().getName());
        ZipUtil.explode(new File(congressionalBillsZipFilepath));
    }

    private List<CongressionalBill> deserializeCongressionalBillsInFolder(File folder) {
        System.out.println("Number of files in folder: " + folder + " = " + folder.listFiles().length);
        return Arrays.stream(folder.listFiles()).parallel().map(file -> {
            CongressionalBill congressionalBill = null;
            try {
                String rawCongressionalBill = Files.readString(Paths.get(file.getAbsolutePath()));
                congressionalBill = xmlMapper.readValue(rawCongressionalBill, CongressionalBill.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return congressionalBill;
        }).collect(Collectors.toList());
    }

}








//    @Override
//    public List<CongressionalBill> getAllCongressionalBills(List<String> congressionalNames) {
//        if (this.congressionalBills.isEmpty()) {
//            Map<String, String> partOneOfServiceCall = createPartOneOfServiceCall(congressionalNames);
//            List<String> stringsForServiceCalls = createPartTwoOfServiceCall(partOneOfServiceCall);
//            List<CompletableFuture<File>> zips = new ArrayList<>();
//
//            for (String stringForServiceCall: stringsForServiceCalls) {
//                CompletionStage<File> zip = grabZipAsync(stringForServiceCall);
//                CompletionStage<File> x = zip.thenApplyAsync((File file) -> {
//                    System.out.println("Unzipping file: " + file.getName() + ", Thread: " + Thread.currentThread().getName());
//                    unzipAsync(file.getAbsolutePath());
//                    return file;
//                });
//                CompletionStage<File> a = x.thenApplyAsync((File file) -> {
//                    System.out.println("Deserializing bills, " + "Thread: " + Thread.currentThread().getName());
//                    List<CongressionalBill> congressionalBills = deserializeCongressionalBillsInFolder(file);
//                    this.congressionalBills.addAll(congressionalBills);
//                    return file;
//                });
//                // Doing this results the method returning before async actions have happened
////                zip.thenApply((File file) -> {
////                    unzipAsync(file.getAbsolutePath());
////                    return file;
////                }).thenAccept((File file) -> {
////                    System.out.println(file.getName());
////                    List<CongressionalBill> congressionalBills = deserializeCongressionalBillsInFolder(file);
////                    this.congressionalBills.addAll(congressionalBills);
////                });
////                zips.add((CompletableFuture<File>)zip);
//
//                zips.add((CompletableFuture<File>)a);
//            }
//            try {
//                zips.forEach(CompletableFuture::join);
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
//        return this.congressionalBills;
//    }