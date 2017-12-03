package Symbols;

public class UnknownVariable extends Variables {
    private boolean flagNOT;
    private String varType;
    public UnknownVariable(String name){
        this.varType = name;
    }
    public boolean GetFlagNot(){
        return flagNOT;
    }
    public void SetFlagNot(){
        flagNOT = !flagNOT;
    }
    @Override
    public String GetName() {
        return varType;
    }
}