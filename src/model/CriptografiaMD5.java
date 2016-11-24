package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptografiaMD5 {
    /**
	* Esta fun��o retorna um hash da senha fornecida pelo usu�rio
	* Este hash � produzido utilizando o algoritmo MD5
	* @param senha A senha fornecida pelo usu�rio
	* @return O hash da senha fornecida pelo usu�rio
	* @throws java.security.NoSuchAlgorithmException Uma exce��o
	* causada caso o algoritmo escolhido n�o exista.
	*/
	public static String getHash(String senha) throws NoSuchAlgorithmException
	{
		MessageDigest algoritmo; 									// Cria objeto usado para obter inst�ncia do algoritmo de criptografia MD5
		algoritmo = MessageDigest.getInstance("MD5"); 				// define o algoritmo a ser utilizado
		algoritmo.reset(); 											// por padr�o chama reset antes de utilizar
		algoritmo.update(senha.getBytes()); 						// Fornece os bytes referentes � senha para gerar o hash
		byte [] messageDigest = algoritmo.digest(); 				// Obt�m o hash
		StringBuilder s = new StringBuilder();
	
		for (int i = 0; i < messageDigest.length; i++) 				// Compatibiliza o hash do Java com o do	MySQL
		{
			int parteAlta = ((messageDigest[i] >> 4) & 0xf) << 4; 	// Faz deslocamento de bits e AND l�gico
			int parteBaixa = messageDigest[i] & 0xf; 				//faz AND l�gico com o hash e 0xF
	
			if (parteAlta == 0) 		//Se parte alta for 0, concatena um 0 na string que representa o hash
				s.append('0');
	
			s.append(Integer.toHexString(parteAlta | parteBaixa)); //faz OR l�gico entre a parte alta e parte baixa
		}
	
		return s.toString(); 			//	Retorna a string que representa o hash da senha
	}
}