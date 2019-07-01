/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodavelha;

import java.util.ArrayList;

/**
 *
 * 
 * @author Vinícius Lima Ferreira
 * Maria Izabel 
 * Marcos Vinícius Mol
 */
public class Mesa {

    private String[][] mapa = new String[3][3];
    private Jogador jogador;
    private int valor = 0;

    private Mesa master;
    private ArrayList<Mesa> matriz = new ArrayList<Mesa>();

    public Mesa getMelhorFilho() {
        Mesa m = new Mesa();
        int melhor = Integer.MIN_VALUE;
        int i = 1;
        for (Mesa filho : matriz) {
            //System.out.println("Filho "+(i++));
            //filho.mostra();
            
            if (filho.getValor() > melhor) {
                m = filho;
                melhor = filho.getValor();
              //  System.out.println("Melhor: "+melhor+" Ganhou? "+filho.ganhou());
            }
           // System.out.println("");
        }
        return m;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Mesa getmaster() {
        return master;
    }

    public void setmaster(Mesa master) {
        this.master = master;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public String getXY(int linha, int coluna) {
        return mapa[linha][coluna];
    }

    public String[][] getMapa() {
        return mapa;
    }
//Criar uma variavel no inicio para escolhar de X and OO
    public void setX(int linha, int coluna) {
        mapa[linha][coluna] = "X";
    }

    public void setO(int linha, int coluna) {
        mapa[linha][coluna] = "O";
    }

    private boolean verificaHorizontal(int linha) {
        return ("O".equals(mapa[linha][0]) && "O".equals(mapa[linha][1]) && "O".equals(mapa[linha][2]));
    }

    private boolean verificaVertical(int coluna) {
        return ("O".equals(mapa[0][coluna]) && "O".equals(mapa[1][coluna]) && "O".equals(mapa[2][coluna]));
    }

    private boolean verificaDiagonalPrincipal() {
        return ("O".equals(mapa[0][0]) && "O".equals(mapa[1][1]) && "O".equals(mapa[2][2]));
    }

    private boolean verificaDiagonalSecundaria() {
        return ("O".equals(mapa[0][2]) && "O".equals(mapa[1][1]) && "O".equals(mapa[2][0]));
    }

    private boolean verificaPerdeuHorizontal(int linha) {
        return ("X".equals(mapa[linha][0]) && "X".equals(mapa[linha][1]) && "X".equals(mapa[linha][2]));
    }

    private boolean verificaPerdeuVertical(int coluna) {
        return ("X".equals(mapa[0][coluna]) && "X".equals(mapa[1][coluna]) && "X".equals(mapa[2][coluna]));
    }

    private boolean verificaPerdeuDiagonalPrincipal() {
        return ("X".equals(mapa[0][0]) && "X".equals(mapa[1][1]) && "X".equals(mapa[2][2]));
    }

    private boolean verificaPerdeuDiagonalSecundaria() {
        return ("X".equals(mapa[0][2]) && "X".equals(mapa[1][1]) && "X".equals(mapa[2][0]));
    }

    public boolean empate() {
        boolean empate = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (mapa[i][j] == null) {
                    empate = false;
                    break;
                }
            }
        }

        return empate && !ganhou();
    }

    public boolean perdeu() {
        return (verificaPerdeuHorizontal(0)
                || verificaPerdeuHorizontal(1)
                || verificaPerdeuHorizontal(2)
                || verificaPerdeuVertical(0)
                || verificaPerdeuVertical(1)
                || verificaPerdeuVertical(2)
                || verificaPerdeuDiagonalPrincipal()
                || verificaPerdeuDiagonalSecundaria());
    }

    public boolean ganhou() {
        //return false;
        return (verificaHorizontal(0)
                || verificaHorizontal(1)
                || verificaHorizontal(2)
                || verificaVertical(0)
                || verificaVertical(1)
                || verificaVertical(2)
                || verificaDiagonalPrincipal()
                || verificaDiagonalSecundaria());
    }

    public boolean isTerminal() {
        return ganhou() || empate() || perdeu();
    }

    public int getResultado() {
        if (ganhou()) {
            valor=1;
            return 1;
        } else {
            if (perdeu()) {
                valor=-1;
                return -1;
            } else { 
                return 0;
            }
        }
    }

    public void mostra() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(mapa[i][j] + "\t");
            }
            System.out.println("");
        }
        System.out.println("Utilidade: "+this.getValor());
    }

    public ArrayList<Mesa> getTodosmatriz(){
        return matriz;
    }
    public ArrayList<Mesa> getmatriz(Mesa t) {
        matriz = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    Mesa filho = t.clone();
                    filho.setmaster(t);
                    if (filho.getXY(i, j) == null) {
                        if (jogador.equals(Jogador.Max)) {
                            filho.setO(i, j);
                        } else {
                            filho.setX(i, j);
                        }
         //           filho.mostra();
                        //         System.out.println("-----------------");
                        filho.getResultado();
                        matriz.add(filho);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        return matriz;
    }

    @Override
    protected Mesa clone() throws CloneNotSupportedException {
        Mesa t = new Mesa();
        for (int i = 0; i < 3; i++) {
            System.arraycopy(mapa[i], 0, t.getMapa()[i], 0, 3);
        }
        return t;
    }

    @Override
    public String toString() {
        String s = "[";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                s+=mapa[i][j]+",";
            }
        }
        return s+"]\n";
    }

}
