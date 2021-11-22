package com.leigh.encryptiontest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class EncryptiontestApplication {

//	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
//		SpringApplication.run(EncryptiontestApplication.class, args);
//
////		EncryptiontestApplication program = new EncryptiontestApplication();
////		program.encryptInformation();
////		program.decryptInformation();
////		File file = new File("RSA/publicKey");
////		System.out.println("\nBYTES FROM FILE BACK IN TO STRING....");
////		System.out.println(Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath())));
//	}

	private void generateNewKeys() {
		try {
			KeyGenerator generator = new KeyGenerator();

			generator.createKeyFiles();
		}
		catch (Exception e) {
			System.out.println ("Error thing");
		}
	}

	private void encryptInformation() {
		String message = "Leigh is so great. He really is. Don't you think.";

		try {
			File file = new File("RSA/publicKey");
			System.out.println("\nBYTES FROM FILE BACK IN TO STRING....");
			String publicKey = Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));

			byte[] encrypted = RSAUtil.encrypt(message, publicKey);

			System.out.println(Base64.getEncoder().encodeToString(encrypted));
		}
		catch (Exception e) {
			System.out.println ("Error thing again");
			System.out.println (e.getMessage());
		}

	}

	private void decryptInformation() {
		String message = "eWPcQomSq/l6lSIP4selATNRQ3QwB7ltMnO2Va3gkueaRwRJ9BHr6sggwBmvppXusLhIcfen6OdWYNAolu0vzg==";

		try {
			File file = new File("RSA/privateKey");
//			System.out.println("\nBYTES FROM FILE BACK IN TO STRING....");
			String privateKey = Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));
			System.out.println("PrivateKey: " + privateKey);

			String decrypted = RSAUtil.decrypt(message, privateKey);

			System.out.println("Decrypted: " + decrypted);
		}
		catch (Exception e) {
			System.out.println ("Error thing again");
			System.out.println (e.getMessage());
		}

	}

}

//  Public Key
//	MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKJlP3s0jnAJn84rpRPQKQ54sRRdM3uNWc09v2TEKoyQpgvj7oomS2JZvSDgdJ+etub2pfIUdXBecAiTKlVKbK0CAwEAAQ==
//
//	Private Key
//	MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAomU/ezSOcAmfziulE9ApDnixFF0ze41ZzT2/ZMQqjJCmC+PuiiZLYlm9IOB0n5625val8hR1cF5wCJMqVUpsrQIDAQABAkBlDz0CHyLE9jhMxTxlqBTkvJb2ZUm0ibJo1xRUUP9pmVzde3RNjXuJJSEw+1ppbVe7jcayLf1Rm90sWWFC09wBAiEA0Y07NPwkqwk6gnQJvWavED+UbZoNl8i93jZ+CME3ix0CIQDGZDGAWkXc3eNoVUi8A1uB1w3X7grJK1X05R+XTtgi0QIgTcwcPhiaWHFtqn8AGrQjrGmeZm56O27IptYeRovNXjUCIQCLKjVfJ/Ph3vRqd4ix+ljyV3yqf5ypSPDe/OqQJQqQQQIhAKZkBYti89qUAGC+krCf+F7Bt8otREF4Vh7nhB8DDiT5

