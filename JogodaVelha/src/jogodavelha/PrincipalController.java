/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodavelha;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Vinícius Lima Ferreira
 * Maria Izabel 
 * Marcos Vinícius Mol
 */
public class PrincipalController implements Initializable {

    @FXML
    Button L1C1, L1C2, L1C3, L2C1, L2C2, L2C3, L3C1, L3C2, L3C3;
    @FXML
    Label estatistica;

    private int ganhou=0, pedeu=0, empate=0;
    private Mesa mesa = new Mesa();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
    }

    public void init() {
        mesa = new Mesa();
        setmesa(mesa);
        mesa.setJogador(Jogador.Min); // Vez do meu usuário de jogar
        //Texto de visualização das Estaticas
        estatistica.setText("Vitórias: "+ganhou+" Derrotas: "+pedeu+" Empates: "+empate);
        
    }

    private Mesa minimaxDecision(Mesa mesa) {
        int melhor = MaxValue(mesa);
        ArrayList<Mesa> matriz = mesa.getTodosmatriz();
        for (Mesa filho : matriz) {//
            
            if (filho.getValor() == melhor) {
                return filho;
            }
        }
        return null;
    }

    private int MinValue(Mesa mesa) {
        if (mesa.isTerminal()) {
            mesa.setValor(mesa.getResultado());
            return mesa.getValor();
        } else {
            mesa.setValor(Integer.MAX_VALUE);
            mesa.setJogador(Jogador.Min);
            ArrayList<Mesa> matriz = mesa.getmatriz(mesa);
            for (Mesa filho : matriz) {
                mesa.setValor(Math.min(mesa.getValor(), MaxValue(filho)));
            }
            return mesa.getValor();
        }
    }

    private int MaxValue(Mesa mesa) {
        if (mesa.isTerminal()) {
            mesa.setValor(mesa.getResultado());
            return mesa.getValor();
        } else {
            mesa.setValor(Integer.MIN_VALUE);
            mesa.setJogador(Jogador.Max);
            ArrayList<Mesa> matriz = mesa.getmatriz(mesa);
            for (Mesa filho : matriz) {
                mesa.setValor(Math.max(mesa.getValor(), MinValue(filho)));
            }
            return mesa.getValor();
        }
    }

    public void marcaX(ActionEvent me) {
        if (mesa.getJogador().equals(Jogador.Min)) {

            //Minha Jogada
            Button celula = (Button) me.getSource();
            System.out.println(celula);
            if (celula.getText() == null || "".equals(celula.getText())) {
                celula.setText("X");
                mesa.setX(Integer.parseInt(celula.getId().charAt(1) + "") - 1, Integer.parseInt(celula.getId().charAt(3) + "") - 1);
                //Calculo o Minimax
                mesa.setJogador(Jogador.Max);
                mesa = minimaxDecision(mesa);
                //Vez da maquina jogar, chama o minimax na classe Mesa
                setmesa(mesa);
                mesa.setJogador(Jogador.Min);
                // Saida da mensagem de vitoria, derroota ou empate 
                if (mesa.isTerminal()) {
              //      JOptionPane.showMessageDialog(null, "VocÃª ganhou? " + mesa.perdeu() + "\nVocÃª perdeu? " + mesa.ganhou() + "\nDeu empate? " + mesa.empate());
                    if(mesa.ganhou()) {
                    	JOptionPane.showMessageDialog(null,"Você perdeu :( ");
                        pedeu++;
                        this.init();
                    }
                    if(mesa.perdeu()) {
                    	JOptionPane.showMessageDialog(null, "Você ganhou :) ");
                    	ganhou++;
                    	this.init();
                    }
                   if(mesa.empate()) {
                    	JOptionPane.showMessageDialog(null, " Empate .... ");
                        empate++;
                    this.init();
                   }
                }
            } else {
                if (celula.getText().equals("X")) {
                    JOptionPane.showMessageDialog(null, "Aguade sua Vez!");
                } else {
                    JOptionPane.showMessageDialog(null, "Sua Vez!");
                }
            }
        } else {
            System.out.println("Porfavor Aguarde Sua Vez!");
        }
    }

    public void setmesa(Mesa tab) {
        L1C1.setText(tab.getXY(0, 0));
        L1C2.setText(tab.getXY(0, 1));
        L1C3.setText(tab.getXY(0, 2));

        L2C1.setText(tab.getXY(1, 0));
        L2C2.setText(tab.getXY(1, 1));
        L2C3.setText(tab.getXY(1, 2));

        L3C1.setText(tab.getXY(2, 0));
        L3C2.setText(tab.getXY(2, 1));
        L3C3.setText(tab.getXY(2, 2));
    }

}
