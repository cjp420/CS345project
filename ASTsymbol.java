/* Generated By:JJTree: Do not edit this line. ASTsymbol.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTsymbol extends SimpleNode {
  private String name;

  public void setName(String l)
  {
    name = l;
  }

  public String getName()
  {
	return name;
  }

  public ASTsymbol(int id) {
    super(id);
  }

  public ASTsymbol(Parser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(ParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

   public String toString() {
  return name;
}
}
/* JavaCC - OriginalChecksum=2e8114ee45d069f5bb5ce552e9ac3016 (do not edit this line) */
