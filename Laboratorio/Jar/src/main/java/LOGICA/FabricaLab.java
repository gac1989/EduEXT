/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOGICA;

/**
 *
 * @author arena
 */
public class FabricaLab {
    
    private static FabricaLab instancia;
    
    private FabricaLab(){};
    
    public static FabricaLab getInstance(){
        
    if (instancia == null)
        
        instancia = new FabricaLab();
    
    return instancia;
}
    
public ISistema getISistema() {
    
    ISistema IG = new Sistema(); // ? 
    
    return IG;
   
   
}
    

    
    
    
}
