package graphDB.resource;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import graphDB.entity.*;
import graphDB.entity.relation.*;
import graphDB.graphdB.Neo4jSessionFactory;
import graphDB.service.GraphHelperService;
import graphDB.utils.BaseResponse;
import graphDB.utils.GraphRequest;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

@Path("/graph")
public class GraphResource {

    @Inject
    GraphHelperService service;

    @POST
    public CompletionStage<BaseResponse> insert(GraphRequest request) {
        CompletableFuture<BaseResponse> future = new CompletableFuture<>();
        try {
            service.insert(request).thenApply((Function<BaseResponse, Object>) future::complete);
        } catch (Exception e) {
            future.complete(BaseResponse.create(e));
        }
        return future;
    }
}
