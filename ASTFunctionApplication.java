/* Generated By:JJTree: Do not edit this line. ASTFunctionApplication.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTFunctionApplication extends SimpleNode {
  public ASTFunctionApplication(int id) {
    super(id);
  }

  public ASTFunctionApplication(Parser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(ParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=e8e0075df3f457078f975dca6a3073ad (do not edit this line) */