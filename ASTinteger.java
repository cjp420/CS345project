/* Generated By:JJTree: Do not edit this line. ASTinteger.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTinteger extends SimpleNode {
  public ASTinteger(int id) {
    super(id);
  }

  public ASTinteger(Parser p, int id) {
    super(p, id);
  }

  private int num;

  public int getNum()
  {
	return num;
  }

  public void setNum(int i)
  {
    num = i;
  }
  /** Accept the visitor. **/
  public Object jjtAccept(ParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
  
  public String toString()
  {
    return "Integer: " + num;
  }
}
/* JavaCC - OriginalChecksum=2589ce5fbe4393d8aa955e70c6436de2 (do not edit this line) */
