package Model.values;

import Model.types.RefType;
import Model.types.Type;

public class RefValue implements Value{
    int address;
    Type locationType;

    public RefValue(int _address, Type _locationType){
        address = _address;
        locationType = _locationType;
    }
    public Type getLocationType()
    {
        return locationType;
    }
    public int getAddress(){
        return address;
    }
    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    @Override
    public String toString() {
        return "(" + address + ", " + locationType + ")";
    }
}
