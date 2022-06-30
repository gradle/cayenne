package org.apache.cayenne.lifecycle.db.auto;

import java.util.List;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.property.ListProperty;
import org.apache.cayenne.exp.property.PropertyFactory;
import org.apache.cayenne.lifecycle.db.E3;

/**
 * Class _E4 was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _E4 extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String ID_PK_COLUMN = "ID";

    public static final ListProperty<E3> E3S = PropertyFactory.createList("e3s", E3.class);

    public void addToE3s(E3 obj) {
        addToManyTarget("e3s", obj, true);
    }
    public void removeFromE3s(E3 obj) {
        removeToManyTarget("e3s", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<E3> getE3s() {
        return (List<E3>)readProperty("e3s");
    }


}
