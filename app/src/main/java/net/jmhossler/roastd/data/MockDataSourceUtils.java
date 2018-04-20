package net.jmhossler.roastd.data;

import org.apache.commons.lang3.SerializationUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MockDataSourceUtils {

  public static <T extends Serializable> T deepClone(T object) {
    try {

      T copy = SerializationUtils.clone(object);

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(baos);
      oos.writeObject(object);
      ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
      ObjectInputStream ois = new ObjectInputStream(bais);
      T obj = (T) ois.readObject();
      return obj;
    }
    catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
