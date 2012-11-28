/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author owner
 */
public class ASTop extends SimpleNode {

    private static int ADD = 0;
    private static int SUB = 1;
    private static int DIV = 2;
    private static int MUL = 3;
    private int opNum = -1;

    public ASTop(int id) {
        super(id);
    }

    public ASTop(Parser p, int id) {
        super(p, id);
    }

    public void setOp(int i) {
        opNum = i;
    }

    public int getOp() {
        return opNum;
    }

    /**
     * Accept the visitor. *
     */
    public Object jjtAccept(ParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    public String toString() {
        String result = "";
        switch (opNum) {
            case 0:
                result = "add";
                break;
            case 1:
                result = "sub";
                break;
            case 2:
                result = "mul";
                break;
            case 3:
                result = "div";
                break;
        }
        return "Op: " + result;
    }
}
