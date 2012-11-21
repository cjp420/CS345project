public class EVisitor implements ParserVisitor
{

	String Env;
  public Object visit(SimpleNode node, Object data)
  {
    	System.out.print("This is an error");
	return data;
	
  }
  public Object visit(ASTinteger node, Object data)
  {
	Env = node.getNum() + " " + Env;
	data = node.childrenAccept(this, data);
	return data;
  }
  public Object visit(ASTsymbol node, Object data)
  {
	Env = node.getName() + " " + Env;
	data = node.childrenAccept(this, data);
	return data;
  }
   public Object visit(ASTNumExp node, Object data)
  {
	data = node.childrenAccept(this, data);
	return data;
  }
  public Object visit(ASTAddition node, Object data)
  {
   	Env = ")" + Env;    	
	data = node.childrenAccept(this, data);
	Env = "(+ " + Env;
	return data;
  }
  public Object visit(ASTSubtraction node, Object data)
  {
    	Env = ")" + Env;    	
	data = node.childrenAccept(this, data);
	Env = "(- " + Env;
	return data;
  }
  public Object visit(ASTMultiplication node, Object data)
  {
	Env = ")" + Env;    	
	data = node.childrenAccept(this, data);
	Env = "(* " + Env;
	return data;
  }
  public Object visit(ASTDivision node, Object data)
  {
    	Env = ")" + Env;    	
	data = node.childrenAccept(this, data);
	Env = "(/ " + Env;
	return data;
  }
  public Object visit(ASTFunctionApplication node, Object data)
  {
	Env = "(aSub " + Env;
    	data = node.childrenAccept(this, data);
	return data;
  }
  public Object visit(ASTFunction node, Object data)
  {
	Env += "";
    	data = node.childrenAccept(this, data);
	return data;
  }
  public Object visit(ASTprog node, Object data)
  {
	Env = "(mtSub)";
    	data = node.childrenAccept(this, data);
	return data;
  }

  public Object visit(ASTprog node, Object data, boolean PRINT)
  {
	Env = "(mtSub)";
    	data = node.childrenAccept(this, data);
	System.out.print(Env);
	return data;
  }
}
