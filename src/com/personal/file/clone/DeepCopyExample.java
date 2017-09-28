package com.personal.file.clone;

import java.io.*;

/**
 * Created by mj on 2017/9/27.
 */
public class DeepCopyExample implements Serializable {
    //Children必须实现Serializable接口
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
