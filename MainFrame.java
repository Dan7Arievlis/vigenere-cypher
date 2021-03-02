/* ***************************************************************
* Autor: Daniel Neves Brasileiro Costa Silveira
* Matricula: 201911913
* Inicio: 26/02/2021
* Ultima alteracao: 02/03/2021
* Nome: MainFrame
* Funcao: Adiciona acoes dos componentes do painel
*************************************************************** */

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
  private static final long serialVersionUID = 3L;

  private Panel panel;  // tela de componentes do programa

  public MainFrame() {
    super("Cifra da Vigenere - Versao Unicode");
    init();

    /*
     * Acoes dos componentes do panel
     */

    //  BOTOES
    panel.getDecryptButton().addActionListener(a -> {  // acao do botao para descriptografia
      this.decryptButtonAction();
    });

    panel.getEncryptButton().addActionListener(a -> {  // acao do botao para criptografia
      this.encryptButtonAction();
    });

    //  TEXT FIELDS

    panel.getKeyTextField().addCaretListener(a -> {  // executa a toda alteracao feita na caixa de texto da chave
      panel.getLimitKey().setText(panel.refreshKeyLimit());
    });

    panel.getEncryptedTextField().addCaretListener(a -> {  // executa a toda alteracao feita na caixa de texto da mensagem criptografada
      panel.getLimitEncrypt().setText(panel.refreshEncryptLimit());  // atualiza a label dessa caixa de texto
    });

    panel.getDecryptedTextField().addCaretListener(a -> {  // executa a toda alteracao feita na caixa de texto da mensagem decifrada
      panel.getLimitDecrypt().setText(panel.refreshDecryptLimit());  // atualiza a label dessa caixa de texto
    });
  } // fim do construtor

/* ***************************************************************
* Metodo: init
* Funcao: inicializa variaveis e estados
* Parametros: sem parametros
* Retorno: void
*************************************************************** */
  public void init() {
    // Painel
    panel = new Panel();
    panel.setSize(new Dimension(704, 281));  // regula o tamanho do painel

    // Frame
    this.add(panel);
    this.setSize(new Dimension(720, 320));  // regula o tamanho da janela
    getContentPane().setLayout(new BorderLayout());  // configura o layout dos componentes da janela

    setLocationRelativeTo(null);  // a janela comeca no centro da tela
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // encerra a execucao ao fechar a janela
    this.setVisible(true);  // torna a janela visivel
  } // fim do metodo init
   
/* ***************************************************************
* Metodo: encryptButtonAction
* Funcao: realiza as operacoes do botao de Criptografia
* Parametros: sem parametros
* Retorno: void
*************************************************************** */
  public void encryptButtonAction() {
    if(!panel.getEncryptedTextField().getText().isEmpty()) {  // condicional para apagar a outra caixa de texto
      panel.getEncryptedTextField().setText("");
    }  // fim do if
    panel.getCypher().encrypt(panel.getDecryptedTextField().getText());  // criptografa mensagem na caixa de texto
    panel.getEncryptedTextField().setText(panel.getCypher().getEncrypted().toString());  // escreve a mensagem criptografada na outra caixa
  } // fim do metodo encryptButtonAction

/* ***************************************************************
* Metodo: decryptButtonAction
* Funcao: realiza as operacoes do botao de Decifracao
* Parametros: sem parametros
* Retorno: void
*************************************************************** */
  public void decryptButtonAction() {
    if(!panel.getDecryptedTextField().getText().isEmpty()) {  // condicional para apagar a outra caixa de texto
      panel.getDecryptedTextField().setText("");
    }  // fim do if
    panel.getCypher().decipher(panel.getEncryptedTextField().getText());  // decifra mensagem na caixa de texto
    panel.getDecryptedTextField().setText(panel.getCypher().getDecrypted().toString());  // escreve a mensagem decifrada na outra caixa
  } // fim do metodo decryptButtonAction
} // fim da classe MainFrame
