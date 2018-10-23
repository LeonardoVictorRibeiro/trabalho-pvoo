/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mvc.model;

/**
 * 
 * @author Leonardo Victor Ribeiro <leonardovictor@outlook.com>
 */
public class Exercico {
    
    public static void main(String[] args) {
        //Valor de entrada
        double vln = 4;
        //Valor do numerador
        double vlnum = 1 ;
        //valor do denomiador
        double vlden = 1;
        //Valor da soma
        double vls = 0;
        
        while(vln >= vlnum){
            vls = vls + (vlnum/vlden);
            vlnum = vlnum + 1;
            vlden = vlden + 2;
        }
        System.out.println(" = " + vls);
        

        
       
        
        /* 
 atrasves de laços de repetição tenho que fazer isso ( (1/1)+(2/3)+(3/5)+(4/7)+.....+ x/y
 explicando o exercicio tenho que começar do 1/1 até que o vlnum (valor do numerador seja igual ao valor de entrada VLN
exemplo se meu valor de entrada for 4 entao tenho que fazer (1/1)+(2/3)+(3/5)+(4/7)
        */
        
    }

}
