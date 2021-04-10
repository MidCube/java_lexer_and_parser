package main.java.cig23.cam.ac.uk.SummerCompiler;

import java.util.Objects;

public class Item {
    public final int ProductionRule;
    public final int DotPlace;
    public Item(int ProductionRule, int DotPlace) {
        this.ProductionRule = ProductionRule;
        this.DotPlace = DotPlace;
    }

    @Override
    public boolean equals(Object object)
    {
        boolean isEqual= false;

        if (object != null && object instanceof Item)
        {
            isEqual = (this.ProductionRule == ((Item) object).ProductionRule) && (this.DotPlace == ((Item) object).DotPlace);
        }

        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ProductionRule, this.DotPlace);
    }
}
