/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATABASE;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import Clases.*;
import LOGICA.Singleton;
/**
 *
 * @author arena
 */
public class Persistencia {
    
    private static Persistencia instancia = null;
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Laboratorio");
    
    private EntityManager em = emf.createEntityManager();
    
    public void inicializarBaseDeDatos(){
        
        Singleton sm = Singleton.getInstance();
        
        try{
           for (Object obj : em.createQuery("Select e from Estudiante e").getResultList()){
                Usuario est = (Usuario) obj;
                sm.agregarUsuario(est);
            }
      
           for (Object obj : em.createQuery("Select d from Docente d").getResultList()){
                Usuario doc = (Usuario) obj;
                sm.agregarUsuario(doc);
            }
           
           for (Object obj : em.createQuery("Select c from Curso c").getResultList()){
                Curso cur = (Curso) obj;
                Query q = em.createNativeQuery("select e.nombre from edicion e join cursoedicion c on e.id = c.ediciones_id join curso d on d.id = c.curso_id where d.nombre = ?1");
                q.setParameter(1, cur.getNombre());
                for(Object objE : q.getResultList()){
                    Edicion ed = cur.obtenerEdicion((String)objE);
                   
                    /*
                    for(Object objI : em.createQuery("select i from Inscripcionestu i").getResultList()){
                        InscripcionE insc = (InscripcionE) objI;
                        ed.agregarInscripcion(insc);
                    } 
                       */
                    cur.agregarEdicion(ed);
                }
               
                sm.agregarCurso(cur);
            }
           
           for (Object obj : em.createQuery("Select i from Instituto i").getResultList()){
                Instituto inst = (Instituto) obj;
                sm.agregarInstituto(inst);
            }
           for (Object obj : em.createQuery("Select p from Programa p").getResultList()){
                Programa prog = (Programa) obj;
                sm.agregarPrograma(prog);
            }
           
           for (Object obj : em.createQuery("Select cat from Categoria cat").getResultList()){
               Categoria categ = (Categoria) obj;
               sm.agregarCategoria(categ);
           }
           //for(Object obj : em.createQuery("Select e from Edicion e").getResultList()){
               
           //}
           
       }catch(Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            //em.close();
            //emf.close();
        }
       
       
    }
   
    private Persistencia(){
 
    }
    
    public static Persistencia getInstance(){
    
        if (instancia == null){
            
            instancia = new Persistencia();
        }
        
        return instancia;
    }
    
    public EntityManager obtenerEntityManager(){
        return em;
    }     
}
