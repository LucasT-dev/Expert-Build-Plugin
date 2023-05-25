package fr.Marodeur.ExpertBuild.Enum;

public enum MsgEnum {

    NOT_PERM("You don't have the permission"),
    CONSOLE_NOT_EXECUTE_CMD("Console cannot use this command"),

    ERROR_SELECTION("§4Error : IncompleteRegionException; Use <selection> selection and complete selection"),
    ERROR_FORMAT_NUMBER("Invalid Number format, please try again"),

    SERVER_OFF("Server unregistered"),

    BRUSH_ENABLE_TYPE("Brush <brush_type> enable"),
    BRUSH_ENABLE_AND_RAYON("Brush <brush_type> enable, Rayon : <rayon>"),
    BRUSH_ENABLE("Brush enable"),
    BRUSH_DISABLE("Brush disable"),
    RAYON_BRUSH("Rayon : <rayon>"),

    SET_AUTOFLIP("AutoFlip enable"),

    GIVE_TOOL("<tool_type> tools given"),

    SET_PRIMARY_POS("Pos 1 set <pos>"),
    SET_SECONDARY_POS("Pos 2 set <pos>"),
    SET_SECONDARY_VERTEX("Vertex add to <pos>"),
    SELECTION_CLEAR("Selection clear"),
    SET_SELECTION("Selection <selection_type> set"),
    CLIPBOARDPASTE("Clipboard paste at ( <pos> )");



    public final String msg;

    MsgEnum(String msg) {
        this.msg = msg;
    }

    public final String getPrefix() {
        return msg;
    }
    
}
