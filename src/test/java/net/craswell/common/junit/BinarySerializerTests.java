package net.craswell.common.junit;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import net.craswell.common.BinarySerializer;

/**
 * Unit tests for the BinarySerializer.
 * 
 * @author scraswell@gmail.com.
 *
 */
public class BinarySerializerTests {

  /**
   * Tests that the BinarySerializer works for the built-in long type.
   * 
   * @throws IOException Never.
   * @throws ClassNotFoundException Never.
   */
  @Test
  public void CanSerializeDeserializeLong()
      throws IOException,
      ClassNotFoundException {
    long t = 10L;

    byte[] tBytes = BinarySerializer.serializeObject(t);
    Object o = BinarySerializer.deserializeObject(tBytes);

    long u = (long) o;

    Assert.assertEquals(t, u);
  }

  /**
   * Tests that the BinarySerializer works for the built-in long type when serializing to/from
   * base64 strings.
   * 
   * @throws IOException Never.
   * @throws ClassNotFoundException Never.
   */
  @Test
  public void CanSerializeDeserializeLongToString()
      throws IOException,
      ClassNotFoundException {
    long t = 10L;

    String serializedObject = BinarySerializer.serializeToString(t);
    Object o = BinarySerializer.deserializeFromString(serializedObject);

    long u = (long) o;

    Assert.assertEquals(t, u);
  }
}
