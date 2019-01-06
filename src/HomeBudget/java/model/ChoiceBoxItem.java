/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HomeBudget.java.model;

/**
 *
 * @author Wielq
 */
public class ChoiceBoxItem {
    
    public int value;
    public String text;
    
    public ChoiceBoxItem(int value,String text){
        this.value=value;
        this.text=text;    
    }
    
    public String toString() { 
         return this.text;
      } 
    
}
