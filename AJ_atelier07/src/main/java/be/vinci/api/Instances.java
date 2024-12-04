package be.vinci.api;

import be.vinci.services.InstancesAnalyzer;
import be.vinci.utils.InstanceGraphBuilder;
import jakarta.json.JsonStructure;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.Annotation;

/**
 * Send instances graph data to make object diagrams
 *
 * The instances graphs are initialized by a class containing the "initInstanceGraph" method,
 * building the instance graph, and returning it.
 *
 * The "instance builder class name" must be given and present into the "instances" package
 */
@Path("instances")
public class Instances {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonStructure getInstanceGraphInfo(@QueryParam("builderclassname") String builderClassname) {
        try {
            Class<?> builder = Class.forName("be.vinci.instances." + builderClassname); // TODO change this line to use the query parameter, and generate dynamically the builder
            Constructor<?> constructor = builder.getConstructor();

            Object classObject = constructor.newInstance();

            // TODO change this line to avoid calling initInstanceGraph() directly
            for (Method declaredMethod : builder.getDeclaredMethods()) {
                if(declaredMethod.isAnnotationPresent(InstanceGraphBuilder.class)){
                    declaredMethod.invoke(classObject);
                    InstancesAnalyzer analyzer = new InstancesAnalyzer(builder);
                    return analyzer.getFullInfo();
                }
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException |
                 ClassNotFoundException e) {
            throw new InternalError();
        }
        throw new WebApplicationException(404);
    }

}
