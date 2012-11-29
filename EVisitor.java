
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NameNotFoundException;
import java.security.InvalidParameterException;
import org.omg.CORBA.UserException;

public class EVisitor implements ParserVisitor {

    HashMap<String, Float> vars = new HashMap<String, Float>();
    HashMap<String, FunctionInfo> functs = new HashMap<String, FunctionInfo>();
    boolean inClosure = false;//to determine which map to put asub in.  (global or specific function)
    //int evaluatingFunct = 0;//increment each time entering a function application to evaluate.  Decrement when leaving.
    String Env;
    private static int ADD = 0;
    private static int SUB = 1;
    private static int DIV = 2;
    private static int MUL = 3;
    float result;

    public Object visit(SimpleNode node, Object data) {
        System.out.print("This is an error");
        return data;

    }

    public Object visit(ASTnumber node, Object data) {
        Env = node.getNum() + " " + Env;
        data = node.childrenAccept(this, data);
        return data;
    }

    public Object visit(ASTsymbol node, Object data) {
        Env = node.getName() + " " + Env;
        data = node.childrenAccept(this, data);
        return data;
    }

    public Object visit(ASTNumExp node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }

    public Object visit(ASTFunctionApplication node, Object data) {
        Env = "(aSub " + Env;
        data = node.childrenAccept(this, data);
        return data;
    }

    public Object visit(ASTFunction node, Object data) {
        Env += "";
        data = node.childrenAccept(this, data);
        return data;
    }

    public Object visit(ASTprog node, Object data) {
        Env = "(mtSub)";
        data = node.childrenAccept(this, data);
        return data;
    }

    public Object visit(ASTprog node, Object data, boolean PRINT) {
        Env = "(mtSub)";
        data = node.childrenAccept(this, data);
        System.out.print(Env);
        return data;
    }

    public Object visit(ASTasub node, Object data) {
        Node c0 = node.jjtGetChild(0); //symbol
        Node c1 = node.jjtGetChild(1); //number or closure
        Node c2 = node.jjtGetChild(2); //mtSub or next asub
        //if(node.jjtGetNumChildren() == 3) { //not right now.  mtsub is not nonterminal
        //   c2 = node.jjtGetChild(2);
        //}
        float val;
        String ident;
        ident = ((ASTsymbol) c0).getName();
        if (c1 instanceof ASTnumber) { //This is a variable definition.
            val = ((ASTnumber) c1).getNum();
            if (inClosure) {
                FunctionInfo fInfo = (FunctionInfo) data;
                fInfo.addVar(ident, val);
            } else {
                vars.put(ident, val);
            }
            if (c2 != null) {
                c2.jjtAccept(this, data);
            }
        } else { //This is a function definition
            FunctionInfo info = new FunctionInfo(ident);
            data = info; //pass this functions FunctionInfo as data to children so the asub can put it's vars in the correct FunctionInfo not the global map
            //check if this function has already been defined already there.
            if (functs.containsKey(ident)) {
                try {
                    throw new NameAlreadyBoundException("Function " + ident + "has already been defined");
                } catch (NameAlreadyBoundException ex) {
                    Logger.getLogger(EVisitor.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println(ex.getMessage());
                }
            } else {
                functs.put(ident, info);
            }
            ((ASTclosure) c1).jjtAccept(this, data);
        }
        return data;
    }

    public Object visit(ASTclosure node, Object data) {
        inClosure = true;
        data = node.childrenAccept(this, data);
        return data;
    }

    public Object visit(ASTparams node, Object data) {
        int numParams = node.jjtGetNumChildren();
        FunctionInfo fInfo = (FunctionInfo) data;
        Node temp = null;
        for (int i = 0; i < numParams; i++) {
            temp = node.jjtGetChild(i);
            fInfo.addParam(((ASTsymbol) temp).getName());
        }
        return data;
    }

    public Object visit(ASTfbody node, Object data) {
        FunctionInfo fInfo = (FunctionInfo) data;
        fInfo.setBody((ASTexpr)node.jjtGetChild(0));
        return data;
    }

    public Object visit(ASTmtsub node, Object data) {
        inClosure = false;
        return data;
    }

    public Object visit(ASTexpr node, Object data) {
        ASTaOp opNode = (ASTaOp) node.jjtGetChild(0);
        SimpleNode c1 = (SimpleNode) node.jjtGetChild(1);
        SimpleNode c2 = (SimpleNode) node.jjtGetChild(2);
        float arg1Result, arg2Result;
        float res = 0;
        float opNum = opNode.getOp();
        //evaluate first argument of operator
        if (c1 instanceof ASTsymbol && opNum != 4) { //arg1 is a symbol
            arg1Result = evalSymbol((ASTsymbol) c1);
        } else if (c1 instanceof ASTnumber) { //arg1 is a number
            arg1Result = evalNum((ASTnumber) c1);
        } else if (c1 instanceof ASTexpr) {  //arg1 is an expr
            arg1Result = (Float)this.visit((ASTexpr) c1, data);
        } else { //error
            arg1Result = 0;
            System.out.println("Node must be symbol, number, or expr.");
        }
        //evaluate second argument of operator
        if (c2 instanceof ASTsymbol) { //arg2 is a symbol
            arg2Result = evalSymbol((ASTsymbol) c2);
        } else if (c2 instanceof ASTnumber) { //arg2 is a number
            arg2Result = evalNum((ASTnumber) c2);
        } else if (c1 instanceof ASTexpr) {  //arg2 is an expr
            arg2Result = (Float)this.visit((ASTexpr) c2, data);
        } else { //error
            arg2Result = 0;
            System.out.println("Node must be symbol, number, or expr.");
        }
        //Do the operation indicated by the operator
        if (opNum == ADD) {
            res = arg1Result + arg2Result;
        } else if (opNum == SUB) {
            res = arg1Result - arg2Result;
        } else if (opNum == DIV) {
            res = arg1Result / arg2Result;
        } else if (opNum == MUL) {
            res = arg1Result * arg2Result;
        } else { //Op is function application
            //put args into list
            ArrayList<Float> args = new ArrayList<Float>(node.jjtGetNumChildren() - 2); //create with correct size for # of args
            for(int i = 3; i <= node.jjtGetNumChildren(); i++) {
                args.add(arg2Result);//need to change to add more args if they exist.  Need a list.
            }
            try {
                res = evalFunct(((ASTsymbol)c1).getName(), args);
            } catch (NameNotFoundException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(EVisitor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidParameterException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(EVisitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Float ans = new Float(res);
        return ans;
    }

    public Object visit(ASTaOp node, Object data) {
        return data;
    }

    public float evalSymbol(ASTsymbol sym) {
        String ident = sym.getName();
        return vars.get(ident);
    }

    public float evalNum(ASTnumber node) {
        return node.getNum();
    }
    
    public float evalSymbolInFunction(ASTsymbol sym, HashMap<String, Float> fvars) {
        return fvars.get(sym.getName());
    }

    public float evalFunct(String ident, ArrayList<Float> paramValues) throws NameNotFoundException, InvalidParameterException {
        Object data = null;
        FunctionInfo info = null;
        //Check that function has been defined and if so get its FunctionInformation
        if (!functs.containsKey(ident)) {
            throw new NameNotFoundException("Function " + ident + " has not been defined");
        } 
        else {
            info = functs.get(ident);
        }
        ArrayList<String> params = info.getParams();
        //Check for correct number of arguments
        if(paramValues.size() != info.numParams()) {
            throw new InvalidParameterException("Function " + ident + " takes " + info.numParams() + "arguments.  " + paramValues.size() + " given.");
        }
        else {
            //Add bound parameters to FunctionInfo
            for(int i = 0; i < params.size(); i++) {
                info.addVar(params.get(i), paramValues.get(i));
            }
        }
        ASTexpr body = info.getBody();
        data = visit(body, data);
        Float res = (Float)data;
        return res.floatValue();
    }

    @Override
    public Object visit(ASTapplication node, Object data) {
        ASTsymbol c0 = (ASTsymbol) node.jjtGetChild(0);
        FunctionInfo info = null;
        SimpleNode c1 = (SimpleNode) node.jjtGetChild(1);
        String ident = c0.getName();
        ASTsymbol temp1 = null;
        ASTnumber temp2 = null;
        float arg = 0;
        float res = 0;
        //Check that function has been defined and if so get its FunctionInformation
        if (!functs.containsKey(ident)) {
            try {
                throw new NameNotFoundException("Function " + ident + " has not been defined");
            } catch (NameNotFoundException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(EVisitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        else {
            info = functs.get(ident);
        }
        if(c1 instanceof ASTsymbol) {
            temp1 = (ASTsymbol)c1;
            arg = evalSymbol(temp1);
        }
        else if(c1 instanceof ASTnumber) {
            temp2 = (ASTnumber)c1;
            arg = evalNum(temp2);
        }
        ASTexpr apply = new ASTexpr(0);
        res = applyGlobalFunction(ident, arg);
        return data;
    }
    
    public float applyGlobalFunction(String fname, float val) {
        return 0;
    }
}
