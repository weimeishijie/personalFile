package com.personal.file.clone;

import java.io.*;

/**
 *
 */
public class DeepCopyExample implements Serializable {
    // Children 必须实现 Serializable 接口
    public Children children;

    public DeepCopyExample copy(){
        DeepCopyExample copy = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            copy = (DeepCopyExample) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return copy;
    }
}
