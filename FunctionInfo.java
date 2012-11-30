
import java.util.ArrayList;
import java.util.HashMap;
import javax.naming.NameNotFoundException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author owner
 */
public class FunctionInfo {

    private String name;
    private ArrayList<String> params;
    private ASTexpr body;
    private HashMap<String, Float> vars;
    private HashMap<String, FunctionInfo> functs;
    
    public FunctionInfo(String n) {
        name = n;
        params = new ArrayList<String>();
        body = null;
        vars = new HashMap<String, Float>();
        functs = new HashMap<String, FunctionInfo>();
    }
    
    public void addFunct(String name, FunctionInfo finfo) {
        functs.put(name, finfo);
    }
    
    public FunctionInfo lookup(String name) throws NameNotFoundException {
        if(!functs.containsKey(name)) {
             throw new NameNotFoundException("Function " + name + " has not been defined in this scope");
        }
        else {
            return functs.get(name);
        }
    }
    public boolean hasParam(String p) {
        if(params.contains(p)) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean hasVar(String v) {
        if(vars.containsKey(v)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void addParam(String p) {
        params.add(p);
    }
    
    public void addVar(String ident, float val) {
        vars.put(ident, val);
    }
    
    public ArrayList<String> getParams() {
        return params;
    }
    
    public float getVarValue(String var) {
        return vars.get(var).floatValue();
    }
    
    public ASTexpr getBody() {
        return body;
    }
    public int numParams() {
        return params.size();
    }
    
    public void setBody(ASTexpr n) {
        body = n;
    }
}
