/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jogodavelha;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * 
 * @author Vinícius Lima Ferreira
 * Maria Izabel 
 * Marcos Vinícius Mol
 */
public class Principal extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Inicial.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Jogo da Velha com Minimax");
        stage.setScene(scene);
        stage.show();
        
    }

    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
    	
    	String[] options = {"Nao", "Sim"};       
    	
    	int resposta = JOptionPane.showOptionDialog(null,"Olá seja bem vindo, vamos começar o jogo da Velha ?", "Titulo da Janela",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
    	if(resposta==1) {
        launch(args);
    	}else {
    		JOptionPane.showMessageDialog(null,"Obrigado");
    	}
    }
    
}
