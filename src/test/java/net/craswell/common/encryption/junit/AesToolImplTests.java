package net.craswell.common.encryption.junit;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

import net.craswell.common.encryption.AesToolImpl;
import net.craswell.common.encryption.AesTool;
import net.craswell.common.encryption.AesToolException;
import net.craswell.common.encryption.EncryptedObject;

/**
 * AES encryption tests.
 * 
 * @author scraswell@gmail.com
 *
 */
public class AesToolImplTests {
  private static final String TEST_ENCODING = "UTF-8";
  
  static final String TEST_PASSPRHASE = "Test encryption pass phrase.";
  static final String BAD_TEST_PASSPRHASE = "Test encryption pass phrase!";


  /**
   * Validates that we can encrypt data.
   * 
   * @throws AesToolException Thrown when a failure is caught in the AesTool.
   * @throws UnsupportedEncodingException
   */
  @Test
  public void CanEncryptData()
      throws AesToolException,
      UnsupportedEncodingException {

    AesTool tool = new AesToolImpl();
    byte[] encryptMe = "Hello World!".getBytes(AesToolImplTests.TEST_ENCODING);

    EncryptedObject encryptedObject = tool.encrypt(encryptMe, AesToolImplTests.TEST_PASSPRHASE);

    byte[] decryptedBytes = tool.decrypt(encryptedObject, AesToolImplTests.TEST_PASSPRHASE);

    Assert.assertArrayEquals(encryptMe, decryptedBytes);
  }

  /**
   * Tests that the proper exception is thrown when we attempt to decrypt data with an incorrect
   * passphrase.
   * 
   * @throws AesToolException Thrown when a failure is caught in the AesTool.
   * @throws UnsupportedEncodingException Never.
   */
  @Test(expected = AesToolException.class)
  public void AttemptToDecryptWithIncorrectPassphraseThrowsAesToolException()
      throws AesToolException,
      UnsupportedEncodingException {

    AesTool tool = new AesToolImpl();

    byte[] encryptMe = "Hello World!".getBytes(AesToolImplTests.TEST_ENCODING);

    EncryptedObject encryptedObject = tool.encrypt(encryptMe, AesToolImplTests.TEST_PASSPRHASE);

    tool.decrypt(encryptedObject, AesToolImplTests.BAD_TEST_PASSPRHASE);
  }

  /**
   * Tests that we can utilize the Base64 encoding options on the AesTool.
   * 
   * @throws AesToolException Thrown when a failure is caught in the AesTool.
   * @throws UnsupportedEncodingException Never.
   */
  @Test
  public void CanPerformOperationsUsingEncodedEncryptedObjects()
      throws AesToolException,
      UnsupportedEncodingException {

    AesTool tool = new AesToolImpl();
    byte[] encryptMe = "Hello World!".getBytes(AesToolImplTests.TEST_ENCODING);

    EncryptedObject encryptedObject = tool.encrypt(encryptMe, AesToolImplTests.TEST_PASSPRHASE);
    String encodedEncryptedObject = tool.encodeObject(encryptedObject);

    EncryptedObject decodedEncryptedObject = tool.decodeObject(encodedEncryptedObject);
    byte[] decryptedBytes = tool.decrypt(decodedEncryptedObject, AesToolImplTests.TEST_PASSPRHASE);

    Assert.assertArrayEquals(encryptMe, decryptedBytes);
  }
}
