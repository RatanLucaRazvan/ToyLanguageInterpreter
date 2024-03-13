package Model.values;

import Model.types.StringType;
import Model.types.Type;

public class StringValue implements Value{
    String val;

    public StringValue(String _val)
    {
        val = _val;
    }
    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof StringValue;
    }

    public String getVal(){
        return val;
    }

    @Override
    public String toString() {
        return "\""+ val + "\"" + " ";
    }
}
