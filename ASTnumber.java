/* Generated By:JJTree: Do not edit this line. ASTnumber.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */

public class ASTnumber extends SimpleNode {

    private float num;

    public ASTnumber(int id) {
        super(id);
    }

    public ASTnumber(Parser p, int id) {
        super(p, id);
    }

    public void setNum(int i) {
        num = i;
    }

    public float getNum() {
        return num;
    }

    /**
     * Accept the visitor. *
     */
    @Override
    public Object jjtAccept(ParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    @Override
    public String toString() {
        return "Number: " + num;
    }
}
/* JavaCC - OriginalChecksum=896cc88eaf029bb6c0f8ec0d9801b133 (do not edit this line) */
