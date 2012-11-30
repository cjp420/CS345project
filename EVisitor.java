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
    int inClosure = 0;//to determine which map to put asub in.  (global or specific function)
    int evaluatingInFunction = 0;//increment when applying a function.  Decrement when done.  Use global vars and functs when 0
    String Env;
    String App = "";
    private static int ADD = 0;
    private static int SUB = 1;
    private static int DIV = 2;
    private static int MUL = 3;
    private static int APP = 4;
    float result;

public void printResult() {
    System.out.println("Result: " + result);
}

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

    public Object visit(ASTFunctionApplication node, Object data) {
	String tmp = Env;
	Node[] children = node.getChildren();
	int len = children.length;
	//Let's have 2n + 1 arguments, second child is the first that can be a function, every other argument could be a function.
	for (int i = 1; i < len - 1; i+=2)
	if (!(node.jjtGetChild(i).toString()).equals("Function"))
	{
	Env = "(aSub " + node.jjtGetChild(i - 1) + " " + node.jjtGetChild(i) + Env + ")";

	}
	else
	{
	data = (node.jjtGetChild(i)).jjtAccept(this, data);
	Env = "(aSub " + node.jjtGetChild(i - 1) + Env + ")";
 	}
	if ((node.jjtGetChild(len - 1).toString()).equals("App")){
	App = "(";
	Node child = node.jjtGetChild(len - 1);
	int len2 = children[len - 1].jjtGetNumChildren();
	for (int i = 0; i < len2; i++)
	App +=  child.jjtGetChild(i) + " ";
	App += ")";}
	else
	data = (node.jjtGetChild(len - 1)).jjtAccept(this, data);
       return data;
    }

    public Object visit(ASTFunction node, Object data) {
	       String tmp = Env;
        Node[] children = node.getChildren();
        int len = children.length;
        Env = tmp + ")" + Env;
        for (int i = len - 1; i >= 0; i--) {
            data = children[i].jjtAccept(this, data);
        }
        Env = "(closure " + Env;
        return data;
    }
    
    public Object visit(ASTnumexp node, Object data) {
        data = node.childrenAccept(this, data);
        return data;
    }
    
    public Object visit(ASTop node, Object data) {
  	Env = ")" + Env;
	data = node.childrenAcceptr(this, data);
        String op = "";
        switch (node.getOp()) {
            case 0:
                op = "add";
                break;
            case 1:
                op = "sub";
                break;
            case 2:
                op = "mul";
                break;
            case 3:
                op = "div";
                break;
            case 4:
                break;
        }
	Env = "(" + op + " " + Env;
        return data;
    }
    public Object visit(ASTApp node, Object data) {
	Env = ")" + Env;
  	data = node.childrenAcceptr(this, data);
	Env = "(" + "app" + " " + Env;
        return data;
    }

    public Object visit(ASTprog node, Object data) {
        Env = " (mtSub)";
        data = node.childrenAccept(this, data);
	Env = App + Env;
        return data;
    }

    public Object visit(ASTprog node, Object data, boolean PRINT) {
        Env = " (mtSub)";
        data = node.childrenAcceptr(this, data);
        Env = App + Env;
        System.out.println(Env);
        return data;
    }

     public Object visit(ASTasub node, Object data) {
        Node c0 = node.jjtGetChild(0); //symbol
        Node c1 = node.jjtGetChild(1); //number or closure
        Node c2 = node.jjtGetChild(2); //mtSub or next asub
        float val;
        String ident;
        ident = ((ASTsymbol) c0).getName();
        if (c1 instanceof ASTnumber) { //This is a variable definition.
            val = ((ASTnumber) c1).getNum();
            if (inClosure > 0) {
                FunctionInfo fInfo = (FunctionInfo) data;
                fInfo.addVar(ident, val);
            } else {
                vars.put(ident, val);
            }
        } else { //This is a function definition
            FunctionInfo info = new FunctionInfo(ident);
            data = info; //pass this functions FunctionInfo as data to children so the asub can put it's vars in the correct FunctionInfo not the global map
            //check if this function has been defined already in that scope.  We really want to allow this I think and just overwrite
//            if (functs.containsKey(ident)) {
//                try {
//                    throw new NameAlreadyBoundException("Function " + ident + "has already been defined");
//                } catch (NameAlreadyBoundException ex) {
//                    Logger.getLogger(EVisitor.class.getName()).log(Level.SEVERE, null, ex);
//                    System.out.println(ex.getMessage());
//                }
//            } else {
                if (inClosure > 1) {
                    FunctionInfo fInfo = (FunctionInfo) data;
                    fInfo.addFunct(ident, info);
                } else {
                    functs.put(ident, info);
                }
//            }
            ((ASTclosure) c1).jjtAccept(this, data);
        }
        c2.jjtAccept(this, data);
        return data;
    }

    public Object visit(ASTclosure node, Object data) {
        inClosure++;
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
        inClosure--;
        return data;
    }

    public Object visit(ASTexpr node, Object data) {
        ASTaOp opNode = (ASTaOp) node.jjtGetChild(0);
        SimpleNode c1 = (SimpleNode) node.jjtGetChild(1);
        SimpleNode c2 = (SimpleNode) node.jjtGetChild(2);
        SimpleNode c3 = null;
        float arg1Result, arg2Result;
        float res = 0;
        int opNum = opNode.getOp();
        FunctionInfo fInfo = (FunctionInfo) data;
        //evaluate first argument of operator
        if (c1 instanceof ASTsymbol && opNum != 4) { //arg1 is a symbol
            if(fInfo == null) {
                arg1Result = evalSymbol((ASTsymbol) c1);
            }
            else {
                arg1Result = evalSymbolInFunction((ASTsymbol) c1, fInfo);
            }
        } else if (c1 instanceof ASTnumber) { //arg1 is a number
            arg1Result = evalNum((ASTnumber) c1);
        } else if (c1 instanceof ASTexpr) {  //arg1 is an expr
            arg1Result = (Float)this.visit((ASTexpr) c1, fInfo);
        } else if (c1 instanceof ASTsymbol && opNum == 4) {
            //nothing to do.
            arg1Result = 0;
        } else { //error
            arg1Result = 0;
            System.out.println("Arg1 must be symbol, number, or expr.");
        }
        //evaluate second argument of operator
        if (c2 instanceof ASTsymbol) { //arg2 is a symbol
            if(fInfo == null) {
                arg2Result = evalSymbol((ASTsymbol) c2);
            }
            else {
                arg2Result = evalSymbolInFunction((ASTsymbol) c2, fInfo);
            }
        } else if (c2 instanceof ASTnumber) { //arg2 is a number
            arg2Result = evalNum((ASTnumber) c2);
        } else if (c1 instanceof ASTexpr) {  //arg2 is an expr
            arg2Result = (Float)this.visit((ASTexpr) c2, fInfo);
        } else { //error
            arg2Result = 0;
            //System.out.println("Arg2 must be symbol, number, or expr.");
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
            ArrayList<Float> argResults = new ArrayList<Float>();
            Float argResult = null;
            argResults.add(arg2Result);
            for (int i = 3; i < node.jjtGetNumChildren(); i++) {
                c3 = (SimpleNode)node.jjtGetChild(i);
                if (c3 instanceof ASTsymbol) { //arg is a symbol
                    if (fInfo == null) {
                        argResult = evalSymbol((ASTsymbol) c3);
                    } else {
                        argResult = evalSymbolInFunction((ASTsymbol) c3, fInfo);
                    }
                } else if (c3 instanceof ASTnumber) { //arg is a number
                    argResult = evalNum((ASTnumber) c3);
                } else if (c3 instanceof ASTexpr) {  //arg is an expr
                    argResult = (Float) this.visit((ASTexpr) c3, fInfo);
                } else { //error
                    argResult = (float)0;
                    System.out.println("Arg1 must be symbol, number, or expr.");
                }
                argResults.add(argResult);
            }
            ArrayList<Float> args = new ArrayList<Float>(node.jjtGetNumChildren() - 2); //create with correct size for # of args
            args.add(arg2Result);
            for(int i = 3; i <= node.jjtGetNumChildren(); i++) {
                args.add(argResults.get(i-3));
            }
            //args now contains all the arguments in order
            try {
                res = evalFunct(((ASTsymbol)c1).getName(), args, fInfo);
            } catch (NameNotFoundException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(EVisitor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidParameterException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(EVisitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Float ans = new Float(res);
        if(node.jjtGetParent() == null) {
            result = ans;
        }
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
    
    public float evalSymbolInFunction(ASTsymbol sym, FunctionInfo info) {
        return info.getVarValue(sym.getName());
    }

    public float evalFunct(String ident, ArrayList<Float> paramValues, FunctionInfo info) throws NameNotFoundException, InvalidParameterException {
        Object data = null;
        evaluatingInFunction++;
        //Check that function has been defined and if so get its FunctionInformation
        ArrayList<String> params = info.getParams();
        //Check for correct number of arguments
        if(paramValues.size() - info.numParams() != info.numParams()) {
            throw new InvalidParameterException("Function " + ident + " takes " + info.numParams() + "arguments.  " + paramValues.size() + " given.");
        }
        else {
            //Add bound parameters to FunctionInfo
            for(int i = 0; i < params.size(); i++) {
                info.addVar(params.get(i), paramValues.get(i));
            }
        }
        ASTexpr body = info.getBody();
        data = visit(body, info);
        Float res = (Float)data;
        evaluatingInFunction--;
        return res.floatValue();
    }

    @Override
    public Object visit(ASTapplication node, Object data) {
        int numChildren = node.jjtGetNumChildren();
        ASTsymbol c0 = (ASTsymbol) node.jjtGetChild(0);
        FunctionInfo info = null;
        SimpleNode c1 = null;
        String ident = c0.getName();
        ASTsymbol temp1 = null;
        ASTnumber temp2 = null;
        float arg = 0;
        float res = 0;
        //Check that function has been defined globally and if so get its FunctionInformation
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
        ASTexpr apply = new ASTexpr(0);
        ASTaOp op = new ASTaOp(0);
        op.setOp(APP);
        apply.jjtAddChild(op, 0);
        apply.jjtAddChild(c0, 1);
        for (int i = 1; i < numChildren; i++) {
            c1 = (SimpleNode) node.jjtGetChild(i);
            if (c1 instanceof ASTsymbol) {
                temp1 = (ASTsymbol) c1;
                apply.jjtAddChild(temp1, i + 1);
            } else if (c1 instanceof ASTnumber) {
                temp2 = (ASTnumber) c1;
                apply.jjtAddChild(temp2, i + 1);
            }
        }
        data = visit(apply, info);
        return data;
    }
}
