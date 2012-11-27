
import java.util.ArrayList;
import java.util.HashMap;

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
    private SimpleNode body;
    private HashMap<String, Float> vars;
    
    public FunctionInfo(String n) {
        name = n;
        params = new ArrayList<String>();
        body = null;
        vars = new HashMap();
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
    
    public SimpleNode getBody() {
        return body;
    }
    public int numParams() {
        return params.size();
    }
    
    public void setBody(SimpleNode n) {
        body = n;
    }
}
