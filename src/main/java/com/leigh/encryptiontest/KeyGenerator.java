package com.leigh.encryptiontest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.util.Base64;

public class KeyGenerator {

    private PublicKey publicKey;
    private PrivateKey privateKey;

    private KeyPair generateKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(512);
        KeyPair keyPair = generator.generateKeyPair();

        return keyPair;
    }

    private void outputKeyPairToFile(KeyPair keyPair) throws IOException {
        this.publicKey = keyPair.getPublic();
        this.privateKey = keyPair.getPrivate();

        System.out.println("Public Key\n");
        System.out.println(Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        System.out.println("\nPrivate Key\n");
        System.out.println(Base64.getEncoder().encodeToString(privateKey.getEncoded()));

        writeToFile("RSA/publicKey", publicKey.getEncoded());
        writeToFile("RSA/privateKey", privateKey.getEncoded());
    }

    private void writeToFile(String path, byte[] key) throws IOException {
        File file = new File(path);
        file.getParentFile().mkdirs();

        FileOutputStream fileOutput = new FileOutputStream(file);
        fileOutput.write(key);
        fileOutput.flush();
        fileOutput.close();
    }

    public void createKeyFiles() throws NoSuchAlgorithmException, IOException {
        KeyPair keyPair = generateKeys();

        outputKeyPairToFile(keyPair);
    }


}
