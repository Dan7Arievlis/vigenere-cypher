/* ***************************************************************
* Autor: Daniel Neves Brasileiro Costa Silveira
* Matricula: 201911913
* Inicio: 26/02/2021
* Ultima alteracao: 02/03/2021
* Nome: Panel
* Funcao: Inicializa os componentes do painel e os adiciona na janela
*************************************************************** */

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Panel extends JPanel {
  private static final long serialVersionUID = 5L;

  private Cypher cypher = new Cypher();  // programa da cifra

  private GridBagConstraints constraints; // organiza os componentes em celula por parametros da variavel
  private JButton encryptButton, decryptButton;  // botoes da janela
  private JTextField keyTextField, encryptedTextField, decryptedTextField; // caixas de texto da janela
  private JLabel keyLabel, encryptedLabel, decryptedLabel, limitKey, 
    limitEncrypt, limitDecrypt;  // rótulos da janela
  private Border border;  // border da janela

  public Panel() {
    super(new GridBagLayout());  // configura o layout do painel
    init();

    /*
     * Adicionando elementos ao painel
     */

    setBorder(border);
    //  BOTOES
    // Botao "Criptografar"
    formatContent(1, 1, GridBagConstraints.CENTER, new Insets(40, 0, 100, 0), false, false);
    add(encryptButton, constraints);  // adiciona componente segundo restricoes
    // Botao "Decifrar"
    formatContent(1, 1, GridBagConstraints.CENTER, new Insets(60, 0, 10, 0), false, false);
    add(decryptButton, constraints);  // adiciona componente segundo restricoes

    //  TEXT FIELDS
    // Campo de texto de Chave
    formatContent(1, 0, GridBagConstraints.PAGE_START, new Insets(10, 10, 2, 10), false, true);
    add(keyTextField, constraints);  // adiciona componente segundo restricoes
    // Campo de texto de Mensagem
    formatContent(0, 1, GridBagConstraints.CENTER, new Insets(0, 0, 2, 0), false, true);
    add(decryptedTextField, constraints);  // adiciona componente segundo restricoes
    // Campo de texto de Criptografado
    formatContent(2, 1, GridBagConstraints.CENTER, new Insets(0, 0, 2, 0), false, true);
    add(encryptedTextField, constraints);  // adiciona componente segundo restricoes

    // LABELS
    // Label "CHAVE"
    formatContent(1, 1, GridBagConstraints.FIRST_LINE_START, new Insets(0, 10, 120, 0), true, false);
    add(keyLabel, constraints);  // adiciona componente segundo restricoes
    // Label limite de caracteres de Chave
    formatContent(1, 1, GridBagConstraints.FIRST_LINE_END, new Insets(0, 0, 120, 10), true, false);
    add(limitKey, constraints);  // adiciona componente segundo restricoes

    // Label "Mensagem"
    formatContent(0, 1, GridBagConstraints.LINE_START, new Insets(38, 0, 0, 0), true, false);
    add(decryptedLabel, constraints);  // adiciona componente segundo restricoes
    // Label limite de caracteres de Mensagem
    formatContent(0, 1, GridBagConstraints.LINE_END, new Insets(38, 0, 0, 0), true, false);
    add(limitDecrypt, constraints);  // adiciona componente segundo restricoes

    // Label "Criptografado"
    formatContent(2, 1, GridBagConstraints.LINE_START, new Insets(38, 0, 0, 0), true, false);
    add(encryptedLabel, constraints);  // adiciona componente segundo restricoes
    // Label limite de caracteres de Criptografado
    formatContent(2, 1, GridBagConstraints.LINE_END, new Insets(38, 0, 0, 0), true, false);
    add(limitEncrypt, constraints);  // adiciona componente segundo restricoes
  }//fim do construtor

/* ***************************************************************
* Metodo: init
* Funcao: inicializa variaveis e estados
* Parametros: sem parametros
* Retorno: void
*************************************************************** */
  public void init() {
    constraints = new GridBagConstraints();  // inicializa as restricoes do grid
    border = BorderFactory.createEmptyBorder(10, 20, 10, 20);  // cria a borda da janela

    encryptButton = new JButton("Criptografar");  // cria botao
    encryptButton.setPreferredSize(new Dimension(112, 24));
    decryptButton = new JButton("Decrifrar");  // cria botao
    decryptButton.setPreferredSize(new Dimension(112, 24));
    //
    keyTextField = new JTextField();  // cria caixa de texto
    keyTextField.setPreferredSize(new Dimension(0, 26));  // define altura
    keyTextField.addKeyListener(new KeyAdapter() {  //funcao para limitar o numero de caracteres na caixa de texto
      public void keyTyped(KeyEvent e) {
        if(keyTextField.getText().length() >= cypher.getKey().capacity()) {  // limite de caracteres na caixa de texto definido para a capacidade da chave da cifra
          e.consume();
        }// fim do if
      }
    });
    //
    encryptedTextField = new JTextField();  // cria caixa de texto
    encryptedTextField.setPreferredSize(new Dimension(0, 26));  // define altura
    encryptedTextField.addKeyListener(new KeyAdapter() {  //funcao para limitar o numero de caracteres na caixa de texto
      public void keyTyped(KeyEvent e) {
        if(encryptedTextField.getText().length() >= cypher.getEncrypted().capacity()) {  // limite de caracteres na caixa de texto definido para a capacidade da chave da cifra
          e.consume();
        }// fim do if
      }
    });
    //
    decryptedTextField = new JTextField();  // cria caixa de texto
    decryptedTextField.setPreferredSize(new Dimension(0, 26));  // define altura
    decryptedTextField.addKeyListener(new KeyAdapter() {  //funcao para limitar o numero de caracteres na caixa de texto
      public void keyTyped(KeyEvent e) {
        if(decryptedTextField.getText().length() >= cypher.getDecrypted().capacity()) {  // limite de caracteres na caixa de texto definido para a capacidade da chave da cifra
          e.consume();
        }// fim do if
      }
    });
    //
    keyLabel = new JLabel("CHAVE");  // inicia o rotulo
    encryptedLabel = new JLabel("Criptografado");  // inicia o rotulo
    decryptedLabel = new JLabel("Mensagem");  // inicia o rotulo
    //
    limitKey = new JLabel(refreshKeyLimit());  // inicia o rotulo
    limitEncrypt = new JLabel(refreshEncryptLimit());  // inicia o rotulo
    limitDecrypt = new JLabel(refreshDecryptLimit());  // inicia o rotulo
  }// fim do metodo init

/* ***************************************************************
* Metodo: formatContent
* Funcao: fomatar os componentes da tela e evitar reescrita de codigo
* Parametros: int posicao em x
*             int posicao em y
*             int disposicao na celula do grid
*             Insets para espacar os componentes
*             boolean para identificar rotulos
*             boolean para identificar caixa de texto
* Retorno: void
*************************************************************** */
  private void formatContent(int posx, int posy, int gridBagConstraints, Insets insets, boolean isLabel, boolean isTextField) {
    constraints.weightx = (isLabel) ? 0.2 : 0;  // seleciona um padrao se for um rotulo
    constraints.fill = (isTextField) ? GridBagConstraints.HORIZONTAL : GridBagConstraints.NONE;  //seleciona um padra se for caixa de texto
    
    constraints.gridx = posx;
    constraints.gridy = posy;
    constraints.anchor = gridBagConstraints;
    constraints.insets = insets;
  }// fim do metodo formatContent

/* ***************************************************************
* Metodo: refreshKeyLimit
* Funcao: atualizar o rotulo de limite da chave
* Parametros: sem parametros
* Retorno: String do novo rotulo
*************************************************************** */
  public String refreshKeyLimit() {
    cypher.changeKey(keyTextField.getText());
    return (cypher.getKey().capacity() - cypher.getKey().length())
      + "/" + cypher.getKey().capacity();
  }// fim do metodo refreshKeyLimit

/* ***************************************************************
* Metodo: refreshEncryptLimit
* Funcao: atualizar o rotulo de limite da mensagem criptografada
* Parametros: sem parametros
* Retorno: String do novo rotulo
*************************************************************** */
  public String refreshEncryptLimit() {
    cypher.setEncrypted(encryptedTextField.getText());
    return (cypher.getEncrypted().capacity() - cypher.getEncrypted().length())
       + "/" + cypher.getEncrypted().capacity();
  }// fim do metodo refreshEncryptedLimit


/* ***************************************************************
* Metodo: refreshDecryptLimit
* Funcao: atualizar o rotulo de limite da mensagem decifrada
* Parametros: sem parametros
* Retorno: String do novo rotulo
*************************************************************** */
  public String refreshDecryptLimit() {
    cypher.setDecrypted(decryptedTextField.getText());
    return (cypher.getDecrypted().capacity() - cypher.getDecrypted().length())
       + "/" + cypher.getDecrypted().capacity();
  }// fim do metodo refreshDecryptedLimit

  public Cypher getCypher() {
    return cypher;
  } // fim do metodo getCypher

  public JButton getEncryptButton() {
    return encryptButton;
  } // fim do metodo getEncryptButton

  public JButton getDecryptButton() {
    return decryptButton;
  } // fim do metodo getDecryptButton

  public JTextField getKeyTextField() {
    return keyTextField;
  } // fim do metodo getKeyTextField

  public JTextField getEncryptedTextField() {
    return encryptedTextField;
  } // fim do metodo getEncryptedTextField

  public JTextField getDecryptedTextField() {
    return decryptedTextField;
  } // fim do metodo getDecryptedTextField

  public JLabel getLimitKey() {
    return limitKey;
  } // fim do metodo getLimitKey

  public JLabel getLimitEncrypt() {
    return limitEncrypt;
  } // fim do metodo getLimitEncrypt

  public JLabel getLimitDecrypt() {
    return limitDecrypt;
  } // fim do metodo getLimitDecrypt
} // fim do classe Panel
