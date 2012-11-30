
public class PrintVisitor implements ParserVisitor {

    private int indent = 0;

    private String indentString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < indent; ++i) {
            sb.append(' ');
        }
        return sb.toString();
    }

    public Object visit(SimpleNode node, Object data) {
        System.out.println(indentString() + node
                + ": acceptor not unimplemented in subclass?");
        ++indent;
        data = node.childrenAccept(this, data);
        --indent;
        return data;
    }

    public Object visit(ASTnumber node, Object data) {
        System.out.println(indentString() + node);
        ++indent;
        data = node.childrenAccept(this, data);
        --indent;
        return data;
    }
    public Object visit(ASTnumexp node, Object data) {
        System.out.println(indentString() + node);
        ++indent;
        data = node.childrenAccept(this, data);
        --indent;
        return data;
    }

    public Object visit(ASTsymbol node, Object data) {
        System.out.println(indentString() + node);
        ++indent;
        data = node.childrenAccept(this, data);
        --indent;
        return data;
    }

    public Object visit(ASTFunctionApplication node, Object data) {
        System.out.println(indentString() + node);
        ++indent;
        data = node.childrenAccept(this, data);
        --indent;
        return data;
    }

    public Object visit(ASTFunction node, Object data) {
        System.out.println(indentString() + node);
        ++indent;
        data = node.childrenAccept(this, data);
        --indent;
        return data;
    }

	public Object visit(ASTApp node, Object data) {
        System.out.println(indentString() + node);
        ++indent;
        data = node.childrenAccept(this, data);
        --indent;
        return data;
    }
	public Object visit(ASTop node, Object data) {
        System.out.println(indentString() + node);
        ++indent;
        data = node.childrenAccept(this, data);
        --indent;
        return data;
    }

    public Object visit(ASTprog node, Object data) {
        System.out.println(indentString() + node);
        ++indent;
        data = node.childrenAccept(this, data);
        --indent;
        return data;
    }

    public Object visit(ASTasub node, Object data) {
        System.out.println(indentString() + node);
        ++indent;
        data = node.childrenAccept(this, data);
        --indent;
        return data;
    }

    public Object visit(ASTclosure node, Object data) {
        System.out.println(indentString() + node);
        ++indent;
        data = node.childrenAccept(this, data);
        --indent;
        return data;
    }

    public Object visit(ASTparams node, Object data) {
        System.out.println(indentString() + node);
        ++indent;
        data = node.childrenAccept(this, data);
        --indent;
        return data;
    }

    public Object visit(ASTfbody node, Object data) {
        System.out.println(indentString() + node);
        ++indent;
        data = node.childrenAccept(this, data);
        --indent;
        return data;
    }

    public Object visit(ASTexpr node, Object data) {
        System.out.println(indentString() + node);
        ++indent;
        data = node.childrenAccept(this, data);
        --indent;
        return data;
    }

    public Object visit(ASTaOp node, Object data) {
        System.out.println(indentString() + node);
        ++indent;
        data = node.childrenAccept(this, data);
        --indent;
        return data;
    }

    @Override
    public Object visit(ASTapplication node, Object data) {
        System.out.println(indentString() + node);
        ++indent;
        data = node.childrenAccept(this, data);
        --indent;
        return data;
    }

    public Object visit(ASTmtsub node, Object data) {
        System.out.println(indentString() + node);
        ++indent;
        data = node.childrenAccept(this, data);
        --indent;
        return data;
    }
}
