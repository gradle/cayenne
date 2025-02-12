package org.apache.cayenne.testdo.testmap.auto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.exp.property.DateProperty;
import org.apache.cayenne.exp.property.NumericIdProperty;
import org.apache.cayenne.exp.property.PropertyFactory;
import org.apache.cayenne.exp.property.SelfProperty;
import org.apache.cayenne.exp.property.StringProperty;
import org.apache.cayenne.testdo.testmap.ArtistCallback;

/**
 * Class _ArtistCallback was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _ArtistCallback extends BaseDataObject {

    private static final long serialVersionUID = 1L;

    public static final SelfProperty<ArtistCallback> SELF = PropertyFactory.createSelf(ArtistCallback.class);

    public static final NumericIdProperty<Integer> ARTIST_ID_PK_PROPERTY = PropertyFactory.createNumericId("ARTIST_ID", "ArtistCallback", Integer.class);
    public static final String ARTIST_ID_PK_COLUMN = "ARTIST_ID";

    public static final StringProperty<String> ARTIST_NAME = PropertyFactory.createString("artistName", String.class);
    public static final DateProperty<Date> DATE_OF_BIRTH = PropertyFactory.createDate("dateOfBirth", Date.class);

    protected String artistName;
    protected Date dateOfBirth;


    public void setArtistName(String artistName) {
        beforePropertyWrite("artistName", this.artistName, artistName);
        this.artistName = artistName;
    }

    public String getArtistName() {
        beforePropertyRead("artistName");
        return this.artistName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        beforePropertyWrite("dateOfBirth", this.dateOfBirth, dateOfBirth);
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfBirth() {
        beforePropertyRead("dateOfBirth");
        return this.dateOfBirth;
    }

    protected abstract void prePersistEntityObjEntity();

    protected abstract void preRemoveEntityObjEntity();

    protected abstract void preUpdateEntityObjEntity();

    protected abstract void postPersistEntityObjEntity();

    protected abstract void postRemoveEntityObjEntity();

    protected abstract void postUpdateEntityObjEntity();

    protected abstract void postLoadEntityObjEntity();

    @Override
    public Object readPropertyDirectly(String propName) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch(propName) {
            case "artistName":
                return this.artistName;
            case "dateOfBirth":
                return this.dateOfBirth;
            default:
                return super.readPropertyDirectly(propName);
        }
    }

    @Override
    public void writePropertyDirectly(String propName, Object val) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch (propName) {
            case "artistName":
                this.artistName = (String)val;
                break;
            case "dateOfBirth":
                this.dateOfBirth = (Date)val;
                break;
            default:
                super.writePropertyDirectly(propName, val);
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        writeSerialized(out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        readSerialized(in);
    }

    @Override
    protected void writeState(ObjectOutputStream out) throws IOException {
        super.writeState(out);
        out.writeObject(this.artistName);
        out.writeObject(this.dateOfBirth);
    }

    @Override
    protected void readState(ObjectInputStream in) throws IOException, ClassNotFoundException {
        super.readState(in);
        this.artistName = (String)in.readObject();
        this.dateOfBirth = (Date)in.readObject();
    }

}
