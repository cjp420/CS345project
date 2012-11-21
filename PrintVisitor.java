public class PrintVisitor implements ParserVisitor
{
  private int indent = 0;

  private String indentString() {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < indent; ++i) {
      sb.append(' ');
    }
    return sb.toString();
  }

  public Object visit(SimpleNode node, Object data) {
    System.out.println(indentString() + node +
                   ": acceptor not unimplemented in subclass?");
    ++indent;
    data = node.childrenAccept(this, data);
    --indent;
    return data;
  }

  public Object visit(ASTinteger node, Object data){
System.out.println(indentString() + node);
++indent;
data = node.childrenAccept(this, data);
--indent;
return data;
}
  public Object visit(ASTsymbol node, Object data){
System.out.println(indentString() + node);
++indent;
data = node.childrenAccept(this, data);
--indent;
return data;
}
  public Object visit(ASTAddition node, Object data){
System.out.println(indentString() + node);
++indent;
data = node.childrenAccept(this, data);
--indent;
return data;
}
  public Object visit(ASTNumExp node, Object data){
System.out.println(indentString() + node);
++indent;
data = node.childrenAccept(this, data);
--indent;
return data;
}
  public Object visit(ASTSubtraction node, Object data){
System.out.println(indentString() + node);
++indent;
data = node.childrenAccept(this, data);
--indent;
return data;
}
  public Object visit(ASTMultiplication node, Object data){
System.out.println(indentString() + node);
++indent;
data = node.childrenAccept(this, data);
--indent;
return data;
}
  public Object visit(ASTDivision node, Object data){
System.out.println(indentString() + node);
++indent;
data = node.childrenAccept(this, data);
--indent;
return data;
}
  public Object visit(ASTFunctionApplication node, Object data){
System.out.println(indentString() + node);
++indent;
data = node.childrenAccept(this, data);
--indent;
return data;
}
  public Object visit(ASTFunction node, Object data){
System.out.println(indentString() + node);
++indent;
data = node.childrenAccept(this, data);
--indent;
return data;
}
  public Object visit(ASTprog node, Object data){
System.out.println(indentString() + node);
++indent;
data = node.childrenAccept(this, data);
--indent;
return data;
}
}

