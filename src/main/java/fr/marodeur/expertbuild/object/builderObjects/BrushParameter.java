package fr.marodeur.expertbuild.object.builderObjects;

import fr.marodeur.expertbuild.object.AbstractShape;

import java.util.UUID;

public class BrushParameter extends IDataProfile {


    private AbstractShape abstractShape;


    protected BrushParameter(UUID ProfileID, AbstractShape abstractShape) {
        super(ProfileID);
        this.abstractShape = abstractShape;
    }

    public AbstractShape getShape() {
        return abstractShape;
    }

    public BrushParameter setShape(AbstractShape shape) {
        abstractShape = shape;
        return this;
    }
}
