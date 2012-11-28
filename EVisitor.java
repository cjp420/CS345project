
import java.rmi.AlreadyBoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EVisitor implements ParserVisitor {

    HashMap<String, Float> vars = new HashMap<String, Float>();
    HashMap<String, FunctionInfo> functs = new HashMap<String, FunctionInfo>();
    boolean inClosure = false;//to determine which map to put asub in.  (global or specific function)
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

    public Object visit(ASTAddition node, Object data) {
        Env = ")" + Env;
        data = node.childrenAccept(this, data);
        Env = "(+ " + Env;
        return data;
    }

    public Object visit(ASTSubtraction node, Object data) {
        Env = ")" + Env;
        data = node.childrenAccept(this, data);
        Env = "(- " + Env;
        return data;
    }

    public Object visit(ASTMultiplication node, Object data) {
        Env = ")" + Env;
        data = node.childrenAccept(this, data);
        Env = "(* " + Env;
        return data;
    }

    public Object visit(ASTDivision node, Object data) {
        Env = ")" + Env;
        data = node.childrenAccept(this, data);
        Env = "(/ " + Env;
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
                    throw new AlreadyBoundException("Function " + ident + "has already been defined");
                } catch (AlreadyBoundException ex) {
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
        fInfo.setBody((SimpleNode) node.jjtGetChild(0));
        return data;
    }

    public Object visit(ASTmtsub node, Object data) {
        inClosure = false;
        return data;
    }

    public Object visit(ASTexpr node, Object data) {
        result = evalExpr(node);
        return data;
    }

    public Object visit(ASTaOp node, Object data) {
        return data;
    }

    public float evalExpr(ASTexpr node) {
        ASTaOp opNode = (ASTaOp) node.jjtGetChild(0);
        SimpleNode c1 = (SimpleNode) node.jjtGetChild(1);
        SimpleNode c2 = (SimpleNode) node.jjtGetChild(2);
        float arg1Result, arg2Result;
        float res;
        //evaluate first argument of operator
        if (c1 instanceof ASTsymbol) {
            arg1Result = evalSymbol((ASTsymbol) c1);
        } else if (c1 instanceof ASTnumber) {
            arg1Result = evalNum((ASTnumber) c1);
        } else {
            arg1Result = evalExpr((ASTexpr) c1);
        }
        //evaluate second argument of operator
        if (c2 instanceof ASTsymbol) {
            arg2Result = evalSymbol((ASTsymbol) c2);
        } else if (c2 instanceof ASTnumber) {
            arg2Result = evalNum((ASTnumber) c2);
        } else {
            arg2Result = evalExpr((ASTexpr) c2);
        }
        //Do the operation indicated by the operator
        if (opNode.getOp() == ADD) {
            res = arg1Result + arg2Result;
        } else if (opNode.getOp() == SUB) {
            res = arg1Result - arg2Result;
        } else if (opNode.getOp() == DIV) {
            res = arg1Result / arg2Result;
        } else if (opNode.getOp() == MUL) {
            res = arg1Result * arg2Result;
        } else {
            res = 0;
        }
        return res;
    }

    public float evalSymbol(ASTsymbol sym) {
        String ident = sym.getName();
        return vars.get(ident);
    }

    public float evalNum(ASTnumber node) {
        return node.getNum();
    }

    @Override
    public Object visit(ASTapplication node, Object data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
