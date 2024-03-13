package Model.values;

import Model.types.IntType;
import Model.types.Type;

public class IntValue implements Value{
    int val;
    public IntValue(int v){
        val = v;
    }

    public int getVal(){
        return val;
    }

    @Override
    public String toString() {
        return val + " ";
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof IntValue;
    }
}
