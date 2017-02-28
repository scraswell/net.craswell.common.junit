package net.craswell.common.encryption.junit;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Assert;
import org.junit.Test;

import net.craswell.common.encryption.AesTool;
import net.craswell.common.encryption.EncryptedObject;

/**
 * AES encryption tests.
 * 
 * @author scraswell@gmail.com
 *
 */
public class AesToolTests {

  /**
   * Validates that we can encrypt data.
   * 
   * @throws NoSuchAlgorithmException
   * @throws InvalidKeyException
   * @throws InvalidKeySpecException
   * @throws InvalidParameterSpecException
   * @throws IllegalBlockSizeException
   * @throws BadPaddingException
   * @throws UnsupportedEncodingException
   * @throws NoSuchPaddingException
   * @throws InvalidAlgorithmParameterException
   */
  @Test
  public void CanEncryptData()
      throws NoSuchAlgorithmException,
      InvalidKeyException,
      InvalidKeySpecException,
      InvalidParameterSpecException,
      IllegalBlockSizeException,
      BadPaddingException,
      UnsupportedEncodingException,
      NoSuchPaddingException,
      InvalidAlgorithmParameterException {

    AesTool tool = new AesTool();

    final String passPhrase = "MYSECRET!";
    byte[] encryptMe = "Hello World!".getBytes("UTF-8");

    EncryptedObject encryptedObject = tool.encrypt(encryptMe, passPhrase);

    byte[] decryptedBytes = tool.decrypt(encryptedObject, passPhrase);

    Assert.assertArrayEquals(encryptMe, decryptedBytes);
  }
}
