package be.vinci.services;

import jakarta.json.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.logging.Logger;

/**
 * Class analyzer. It saves a class into attribute, from a constructor, and
 * gives a lot of convenient methods to transform this into a JSON object
 * to print the UML diagram.
 */
public class ClassAnalyzer {

    private Class aClass;

    public ClassAnalyzer(Class aClass) {
        this.aClass = aClass;
    }

    /**
     * Create a JSON Object with all the info of the class.
     * @return
     */
    public JsonObject getFullInfo() {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("name", aClass.getSimpleName());
        objectBuilder.add("fields", getFields());
        objectBuilder.add("methods",this.getMethods());
        return objectBuilder.build();
    }


    /**
     * From the field descriptor f, create a Json Object with all field data.
     * Example :
     * {
     * name: "firstname",
     * type: "String",
     * visibility : "private"  // public, private, protected, package
     * isStatic: false,
     * }
     * @param f filed descriptor - describe an attribute
     * @return the generated JSON
     */
    public JsonObject getField(Field f) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        // TODO add missing info
        objectBuilder.add("name",f.getName());
        objectBuilder.add("type", f.getType().getSimpleName());
        objectBuilder.add("visibility", getFieldVisibility(f));
        objectBuilder.add("isStatic", isFieldStatic(f));
        return objectBuilder.build();
    }

    /**
     * Get fields, and create a Json Array with all fields data.
     * Example :
     * [ {}, {} ]
     * This method rely on the getField() method to handle each field one by one.
     */
    public JsonArray getFields() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        // TODO Add all fields descriptions to array (use the getField() method above)
        for (Field declaredField : this.aClass.getDeclaredFields()) {
            arrayBuilder.add(this.getField(declaredField));
        }
        return arrayBuilder.build();
    }

    public JsonArray getMethods(){
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Method declaredMethod : this.aClass.getDeclaredMethods()) {
            arrayBuilder.add(this.getMethod(declaredMethod));
        }
        return arrayBuilder.build();
    }

    public JsonObject getMethod(Method method){
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("name",method.getName());
        objectBuilder.add("returnType",method.getReturnType().getSimpleName());
        objectBuilder.add("parameters", this.getMethodParameters(method));
        objectBuilder.add("visibility", this.getMethodVisibility(method));
        objectBuilder.add("isStatic",this.isStaticMethod(method));
        objectBuilder.add("isAbstract", this.isAbstractMethod(method));
        return objectBuilder.build();
    }

    private JsonArray getMethodParameters(Method m){
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Parameter parameter : m.getParameters()) {
            arrayBuilder.add(parameter.getType().getSimpleName());
        }
        return arrayBuilder.build();
    }

    private String getMethodVisibility(Method m){
        if(Modifier.isPublic(m.getModifiers())) return "public";
        if(Modifier.isPrivate(m.getModifiers())) return "private";
        if(Modifier.isProtected(m.getModifiers())) return "protected";
        return "package";
    }

    private boolean isStaticMethod(Method method){
        if(Modifier.isStatic(method.getModifiers())) {
            return true;
        }
        return false;
    }

    private boolean isAbstractMethod(Method method){
        if(Modifier.isAbstract(method.getModifiers())){
            return true;
        }
        return false;
    }

    /**
     * Return whether a field is static or not
     *
     * @param f the field to check
     * @return true if the field is static, false else
     */
    private boolean isFieldStatic(Field f) { // TODO
        if(Modifier.isStatic(f.getModifiers())){
            return true;
        }
        return false;
    }

    /**
     * Get field visibility in a string form
     *
     * @param f the field to check
     * @return the visibility (public, private, protected, package)
     */
    private String getFieldVisibility(Field f) { // TODO
        if(Modifier.isPublic(f.getModifiers())) return "public";
        if(Modifier.isPrivate(f.getModifiers())) return "private";
        if(Modifier.isProtected(f.getModifiers())) return "protected";
        return "package";
    }



}
