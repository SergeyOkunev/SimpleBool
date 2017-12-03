package Symbols;

import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.LinkedList;

public class OpAND extends Operations {
    private ArrayList<UnknownVariable> var1;
    private ArrayList<UnknownVariable> var2;

    public static ArrayList<ISymbol> AND(ArrayList<ISymbol> x, ArrayList<ISymbol> y){
        ArrayList<ISymbol> result = new ArrayList<>();
        if (x.size() == 1){                                                                     //
            if (y.size() == 1){                                                                 // передано 2 переменные
                UnknownVariable tmpX = (UnknownVariable) x.get(0);                              //
                UnknownVariable tmpY = (UnknownVariable) y.get(0);                              //
                if (tmpX.GetName().equals(tmpY.GetName())){
                    if (tmpX.GetFlagNot() != tmpY.GetFlagNot()){                                // имена равны
                        result.add(new UnknownVariable("Zero"));                          // флаги равны
                    }else {
                        result.add(new UnknownVariable("One"));                           //флаги не равны
                    }
                }else {
                    CrateResult(result,tmpX,tmpY);                                              // имена не равны
                }
            }else {
                if (y.contains(x)){                                                              // передана 1 переменная и 1 массив
                    for (ISymbol symbol : y) {                                                   //если содержит тип
                        if (symbol instanceof UnknownVariable){                                 //для каждой переменной
                            ArrayList<ISymbol> tmp = new ArrayList<>();
                            tmp.add(symbol);
                            CrateResult(result,AND(x,tmp));
                        }
                    }
                }else {
                    CrateResult(result,x,y);
                }
            }
        }else {
            if (y.size() == 1){
                if (x.contains(y)){                                                              // передана 1 переменная и 1 массив
                    for (ISymbol symbol : x) {                                                   //если содержит тип
                        if (symbol instanceof UnknownVariable){                                 //для каждой переменной
                            ArrayList<ISymbol> tmp = new ArrayList<>();
                            tmp.add(symbol);
                            CrateResult(result,AND(y,tmp));
                        }
                    }
                }else {
                    CrateResult(result,x,y);
                }
            }else {                                                                               //обе переменные - массивы
                //дописать
            }
        }
        return result;
    }

    private static void CrateResult(ArrayList<ISymbol> result, UnknownVariable tmpX, UnknownVariable tmpY) {
        result.add(new LeftP());
        result.add(tmpX);
        result.add(new OpAND());
        result.add(tmpY);
        result.add(new RightP());
    }
    private static void CrateResult(ArrayList<ISymbol> res,  ArrayList<ISymbol> x,  ArrayList<ISymbol> y){
        res.add(new LeftP());
        res.addAll(x);
        res.add(new OpAND());
        res.addAll(y);
        res.add(new RightP());
    }
    private void CrateResult(ArrayList<ISymbol> res,  UnknownVariable x,  ArrayList<ISymbol> y){
        res.add(new LeftP());
        res.add(x);
        res.add(new OpAND());
        res.addAll(y);
        res.add(new RightP());
    }
    private void CrateResult(ArrayList<ISymbol> res,  ArrayList<ISymbol> x,  UnknownVariable y){
        res.add(new LeftP());
        res.addAll(x);
        res.add(new OpAND());
        res.add(y);
        res.add(new RightP());
    }
    private static void CrateResult(ArrayList<ISymbol> res,  ArrayList<ISymbol> x){
        res.addAll(x);
    }

    @Override
    public String GetName() {
        return "AND";
    }
}
