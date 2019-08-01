/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HomeBudget.java.model;

/**
 *
 * @author Wielq
 * Item of FXML ChoiceBox
 */
public class ChoiceBoxItem {
    
    public int value;
    public String text;

    /**
     *
     * @param value-unvisible value of item
     * @param text-visible text on item
     */
    public ChoiceBoxItem(int value,String text){
        this.value=value;
        this.text=text;    
    }
    @Override
    public String toString() { 
         return this.text;
      } 
    
}
