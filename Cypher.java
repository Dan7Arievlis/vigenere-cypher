/* ***************************************************************
* Autor: Dan7Arievlis
* Inicio: 26/02/2021
* Ultima alteracao: 02/03/2021
* Nome: Cypher
* Funcao: realiza as operacoes de criptografia e decifracao da cifra
* de Vigenere
*************************************************************** */

import java.util.ArrayList;
import java.util.List;

public class Cypher {
  private StringBuilder key;  // chave da operacao da cifra
  private StringBuilder decrypted; // mensagem decodificada ou entregue para criptografia
  private StringBuilder encrypted; // mensagem criptografada

  private List<Integer> decryptedCodePoints; // Lista que guarda os codigos de cada caracter de decrypted
  private List<Integer> encryptedCodePoints; // Lista que guarda os codigos de cada caracter de encrypted

  public Cypher() {
    this.key = new StringBuilder(16);  // determina a capacidade inicial da sequencia de caracteres da chave
    this.decrypted = new StringBuilder(32);  // determina a capacidade inicial da sequencia de caracteres da mensagem decodificada
    this.encrypted = new StringBuilder(32);  // determina a capacidade inicial da sequencia de caracteres da mansagem criptografada
    
    decryptedCodePoints = new ArrayList<>();
    encryptedCodePoints = new ArrayList<>();
  }

  public StringBuilder getKey() {
    return key;
  }

  public StringBuilder getDecrypted() {
    return decrypted;
  }

  public StringBuilder getEncrypted() {
    return encrypted;
  }

  public void changeKey(String key) {
    this.key.replace(0, this.key.length(), key);
  }

  public void setDecrypted(String decrypted) {
    this.decrypted.replace(0, this.decrypted.length(), decrypted);
  }

  public void setEncrypted(String encrypted) {
    this.encrypted.replace(0, this.encrypted.length(), encrypted);
  }

/* ***************************************************************
* Metodo: encrypt
* Funcao: Executa a operacao para criptografar a mensagem passada como parametro
* Parametros: a mensagem a ser criptografada
* Retorno: void
*************************************************************** */
  public void encrypt(String decrypt) {
    /*
     * Preparando variaveis
     */
    setDecrypted(decrypt);
    setEncrypted("");
    encryptedCodePoints.clear();
    
    try {  // protege a pilha de uma possivel excecao
      for (int i = 0; i < decrypt.length(); i++) {  // itera a operacao por toda mensagem a ser criptografada
        if (decrypt.charAt(i) == ' ') {  // condicao para separar a mensagem criptografada em espacos na ocorrencia de um
          encryptedCodePoints.add(" ".codePointAt(0));
        } else {
          encryptedCodePoints.add(decrypt.codePointAt(i) + key.codePointAt(i 
            % key.length()));  // operacao para criptagrafia da mensagem, fazendo loop na chave. Como descreve Vigenere
        }
      }
    } catch(ArithmeticException ae) {}
  
    for (int i = 0; i < encryptedCodePoints.size(); i++) { // itera os novos codigos da mensagem para conversao para String
      if (encryptedCodePoints.get(i) < 0)  // condicional caso encontre um codigo que represente o reverso de um alto substituto
        encryptedCodePoints.set(i, Math.abs(encryptedCodePoints.get(i)));  // atribui um valor em modulo do codigo
        
      encrypted.appendCodePoint(encryptedCodePoints.get(i));  // concatena um caractere compativel com o codigo na posicao na StringBuilder
    }
  }

/* ***************************************************************
* Metodo: decipher
* Funcao: Executa a operacao para decifrar a mensagem passada como parametro
* Parametros: a mensagem a ser decifrada
* Retorno: void
*************************************************************** */
  public void decipher(String encrypt) {
    /*
     * Preparando variaveis
     */
    setEncrypted(encrypt);
    setDecrypted("");
    decryptedCodePoints.clear();
    
    try {  // protege a pilha de uma possivel excecao
      for (int i = 0; i < encrypt.length(); i++) {  // itera a operacao por toda mensagem a ser decifrada
        if (encrypt.charAt(i) == ' ') {  // condicao para separar a mensagem criptografada em espacos na ocorrencia de um
          decryptedCodePoints.add(" ".codePointAt(0));
        } else {
          decryptedCodePoints.add(encrypt.codePointAt(i) - key.codePointAt(i 
            % key.length()));  // operacao para decifracao da mensagem, fazendo loop na chave. Como descreve Vigenere
        }
      }
    } catch(ArithmeticException ae) {} // fim try/catch
  
    for (int i = 0; i < decryptedCodePoints.size(); i++) { // itera os novos codigos da mensagem para conversao para String
      if (decryptedCodePoints.get(i) < 0)  // condicional caso encontre um codigo que represente o reverso de um alto substituto
        decryptedCodePoints.set(i, Math.abs(decryptedCodePoints.get(i)));  // atribui um valor em modulo do codigo
      
      decrypted.appendCodePoint(decryptedCodePoints.get(i));  // concatena um caractere compativel com o codigo na posicao na StringBuilder
    }
  }
}
