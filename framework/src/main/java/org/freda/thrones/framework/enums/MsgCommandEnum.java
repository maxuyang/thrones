package org.freda.thrones.framework.enums;

import org.freda.thrones.framework.msg.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * 命令定义
 */
public enum MsgCommandEnum {

    PROCEDURE((byte) 1, "procedure message", ProcedureReqMsg.class),

    PROCEDURE_RES((byte) -1, "procedure response message", ProcedureRespMsg.class),

    ACTIVE((byte) 2, "active message", ActiveMsg.class),

    ACTIVE_RES((byte) -2, "active response message", ActiveRespMsg.class);

    private byte command;

    private String desc;

    private Class clazz;

    MsgCommandEnum(byte command, String desc, Class clazz)
    {
        this.command = command;

        this.desc = desc;

        this.clazz = clazz;
    }

    public static MsgCommandEnum commandOf(byte command)
    {
        return Arrays.stream(MsgCommandEnum.values())
                .filter(t -> t.getCommand() == command)
                .findFirst().get();
    }

    public BaseMsg msgInstanceOf(Header header, byte[] bodyBytes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
    {
         return (BaseMsg) this.getClazz().getConstructor(Header.class, byte[].class).newInstance(header, bodyBytes);
    }
    public byte getCommand() {
        return command;
    }

    public String getDesc() {
        return desc;
    }

    public Class getClazz() {
        return clazz;
    }
}
