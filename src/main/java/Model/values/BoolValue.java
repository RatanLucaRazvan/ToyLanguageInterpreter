package Model.values;

import Model.types.BoolType;
import Model.types.Type;

public class BoolValue implements Value{
    boolean val;
    public BoolValue(boolean v){
        val = v;
    }

    public boolean getVal(){return val;}

    @Override
    public String toString() {
        return val + " ";
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof BoolValue;
    }
}
