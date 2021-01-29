/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOGICA;

import Clases.*;
import DATABASE.Persistencia;
import Datatypes.*;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author arena
 */
public class Sistema implements ISistema{
    

    public Sistema(){
        
    };
    
 public void altaUsuario(DTUsuario datos, boolean docente, List nomInst){
    Persistencia p = Persistencia.getInstance();
    EntityManager em = p.obtenerEntityManager();
    Singleton sm = Singleton.getInstance();
    Usuario u;
    String nombreInst;
    Instituto inst;
    if(chekuUsuarioEmail(datos.getCorreo())&& chekusuario(datos.getNick())){
        if(docente){
            u = new Docente(datos.getNick(),datos.getNombre(),datos.getApellido(),datos.getCorreo(),datos.getFecha(),datos.getContrasena(), datos.getImagenDir());
            for(Object nombre : nomInst){
                nombreInst = nombre.toString();
                inst = sm.obtenerInstituto(nombreInst);
                inst.addDocente((Docente)u);
            }
        }else{
            u = new Estudiante(datos.getNick(),datos.getNombre(),datos.getApellido(),datos.getCorreo(),datos.getFecha(), datos.getContrasena(), datos.getImagenDir());
        }
        sm.agregarUsuario(u);
        //try catch
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
    }
}
    
    public boolean chekusuario(String nick){
        Singleton sm = Singleton.getInstance();
        if(sm.obtenerUsuario(nick)!=null){
            return false;
        }else{
            return true;
        }
    }
    public List<String> edicionesDocente(String curso, String nick){
        Singleton sm = Singleton.getInstance();
        List<String> edi = new ArrayList();
        Curso c1 = sm.obtenerCurso(curso);
        Map<String, Edicion> e1 = c1.getEdiciones();
        Iterator<Map.Entry<String, Edicion>> it = e1.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Edicion> e2 = it.next();
            Edicion e3 = e2.getValue();
            Map<String, Docente> doc = e3.getDocentes();
            if(doc.get(nick)!=null){
                edi.add(e3.getNombreEdicion());
            }
        }
        return edi;
    }
    
    public void cambiarEstadoEdicion(String cur, String edi, EstadoEdicion estado){
        Singleton sm = Singleton.getInstance();
        Curso c1 = sm.obtenerCurso(cur);
        Persistencia p = Persistencia.getInstance();
        EntityManager em = p.obtenerEntityManager();
        Map<String, Edicion> ediciones = null;
        if(c1!=null){
            ediciones = c1.getEdiciones();
        }
        Edicion e1 = ediciones.get(edi);
        if(e1!=null){
            e1.setEstado(estado);
            em.getTransaction().begin();
            em.persist(e1);
            em.getTransaction().commit();
        }
    }
    public Set<DTCurso> datosCursos(){
        Set<DTCurso> res = new HashSet();
        Singleton sm = Singleton.getInstance();
        Iterator<Map.Entry<String, Curso>> it = sm.getCursos().entrySet().iterator();
        while(it.hasNext()){
           Map.Entry<String, Curso> usr = it.next();
           Curso c1 = usr.getValue();
           res.add(c1.getDatos());
        }
        return res;
    }
    
    public void cambiarNota(String cur, String edi, String nick, int nota){
        Singleton sm = Singleton.getInstance();
        Curso c1 = sm.obtenerCurso(cur);
        Edicion e1 = c1.obtenerEdicion(edi);
        InscripcionE ins = null;
        Persistencia p = Persistencia.getInstance();
        EntityManager em = p.obtenerEntityManager();
        em.getTransaction().begin();
        for(Object insc : e1.getInscripciones()){
            ins = (InscripcionE)insc;
            if(ins.getEst().getNick().equals(nick)){
                ins.setNota(nota);
                em.persist(ins);
            }
        }
        em.getTransaction().commit();
    }
    
     public Set<DTPrograma> datosProgramas(){
        Set<DTPrograma> res = new HashSet();
        Singleton sm = Singleton.getInstance();
        Iterator<Map.Entry<String, Programa>> it = sm.getProgramas().entrySet().iterator();
        while(it.hasNext()){
           Map.Entry<String, Programa> usr = it.next();
           Programa p1 = usr.getValue();
           res.add(p1.getDatos());
        }
        return res;
    }
    
    public boolean chekuUsuarioEmail(String email){
        Singleton sm = Singleton.getInstance();
        boolean existeMail = true;
        Iterator<Map.Entry<String, Usuario>> it = sm.getUsuarios().entrySet().iterator();
        while(it.hasNext() && existeMail){
           Map.Entry<String, Usuario> usr = it.next();
           if(usr.getValue().getCorreo().equalsIgnoreCase(email)){
               existeMail = false;
           }
        }
        return existeMail;
    };
    
    public boolean esDocente(String login){
       
        Singleton sm = Singleton.getInstance();
        Usuario u1 = sm.obtenerUsuario(login);
        if(u1!=null){
           if(u1 instanceof Docente){
               return true;
           }
           else{
               return false;
           }
        }
        boolean existeMail = true;
        Iterator<Map.Entry<String, Usuario>> it = sm.getUsuarios().entrySet().iterator();
        while(it.hasNext() && existeMail){
           Map.Entry<String, Usuario> usr = it.next();
           if(usr.getValue().getCorreo().equalsIgnoreCase(login)){
               if(usr.getValue() instanceof Docente){
                  return true;
               }
               else{
                   return false;
               }
           }
        }
        
        return false;
    }
    
    public DTInstituto obtenerInstitutoCurso(String nombreC){
        
        Singleton sm = Singleton.getInstance();
        Map i1 = sm.getInstitutos();
        DTInstituto res = null;
        Iterator<Map.Entry<String, Instituto>> it = i1.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Instituto> i2 = it.next();
            Instituto i3 = i2.getValue();
            Map c1 = i3.getCursos();
            Iterator<Map.Entry<String, Curso>> itc = c1.entrySet().iterator();
            while(itc.hasNext()){
                Map.Entry<String, Curso> c2 = itc.next();
                Curso c3 = c2.getValue();
                if(nombreC.equals(c3.getNombre())){
                    res = i3.getDatos();
                }
            }
        }
        return res;
    }
    
    public boolean sintaxisEmailCorrecta(String email){
        boolean resultado = true;
        int cantarrobas = 0;
        int i = 0;
        while(cantarrobas<2 && i<email.length() && resultado){
            if(email.charAt(0)=='@'||email.charAt(0)=='.'){
                resultado = false;
            }
            if(email.charAt(i)=='@'){
                cantarrobas++;
            }
            i++;
        }
        if(cantarrobas!=1){
            resultado = false;
        }
        return resultado;
    }
    
    public ArrayList<String> listarNickUsuarios(){
        Singleton sm = Singleton.getInstance();
        Iterator<Map.Entry<String, Usuario>> it = sm.getUsuarios().entrySet().iterator();
        ArrayList<String> nicks = new ArrayList<String>();
        while(it.hasNext()){
           Map.Entry<String, Usuario> usr = it.next();
           nicks.add(usr.getValue().getNick());
        }
        return nicks;
    }
    
     public ArrayList<String> listarNombreProgramas(){
        Singleton sm = Singleton.getInstance();
        Iterator<Map.Entry<String, Programa>> it = sm.getProgramas().entrySet().iterator();
        ArrayList<String> nombres = new ArrayList<String>();
        while(it.hasNext()){
           Map.Entry<String, Programa> usr = it.next();
           nombres.add(usr.getValue().getNombre());
        }
        return nombres;
    }
     
     public ArrayList<String> listarNombreCursos(){
        Singleton sm = Singleton.getInstance();
        Iterator<Map.Entry<String, Curso>> it = sm.getCursos().entrySet().iterator();
        ArrayList<String> nombres = new ArrayList<String>();
        while(it.hasNext()){
           Map.Entry<String, Curso> usr = it.next();
           nombres.add(usr.getValue().getNombre());
        }
        return nombres;
    }
    
    public DTUsuario obtenerUsuario(String nick){
       Singleton sm = Singleton.getInstance();
       Usuario u = sm.obtenerUsuario(nick);
       
       if(u!=null){
          
          return new DTUsuario(u.getNick(), u.getNombre(), u.getApellido(), u.getCorreo(), u.getFecha_de_nac(), u.getContrasena(), u.getImagenDir()); 
       }
       else{
           
           return null;
       }
       
    }
    public DTUsuario obtenerUsuarioMail(String mail){
        Singleton sm = Singleton.getInstance();
        boolean existeMail = true;
        Iterator<Map.Entry<String, Usuario>> it = sm.getUsuarios().entrySet().iterator();
        while(it.hasNext() && existeMail){
           Map.Entry<String, Usuario> usr = it.next();
           if(usr.getValue().getCorreo().equals(mail)){
               existeMail = false;
               return usr.getValue().getDatos();
               
           }
        }
        return null;
    }
    /*
    public Usuario obtenerUsuario(String nick){
        Singleton sm = Singleton.getInstance();
        return sm.obtenerUsuario(nick);
    }*/
    
    public void modificarDatosUsuario(String nick, String nuevoNom, String nuevoApe, Date nuevaFechaNac){
        Persistencia p = Persistencia.getInstance();
        EntityManager em = p.obtenerEntityManager();
        Singleton sm = Singleton.getInstance();
        Usuario u = sm.obtenerUsuario(nick);
        if(nuevoNom!=null && !nuevoNom.equals("")){
            u.setNombre(nuevoNom);
        }
        if(nuevoApe!=null && !nuevoApe.equals("")){
           u.setApellido(nuevoApe); 
        }
        if(nuevaFechaNac!=null){
            u.setFecha_de_nac(nuevaFechaNac);
        } 
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
    }
    
    public void altaInstituto(String nom){
        Persistencia p = Persistencia.getInstance();
        EntityManager em = p.obtenerEntityManager();
        Singleton sm = Singleton.getInstance();
        em.getTransaction().begin();
        //Query q = em.createQuery("SELECT i FROM Instituto i WHERE i.nombre = ?1");
        //q.setParameter(1, nom);
        
        //if(q.getSingleResult()==null){
            Instituto inst = new Instituto(nom); 
            sm.agregarInstituto(inst);
            em.persist(inst);
        //}
        em.getTransaction().commit();
    }
    
    public ArrayList<String> listarInstitutos(){
        Singleton sm = Singleton.getInstance();
        Iterator<Map.Entry<String, Instituto>> it = sm.getInstitutos().entrySet().iterator();
        ArrayList<String> nombresInst = new ArrayList<String>();
        while(it.hasNext()){
           Map.Entry<String, Instituto> inst = it.next();
           nombresInst.add(inst.getValue().getNombre());
        }
        return nombresInst;
    }
    
    public DTInstituto buscarInstituto(String nombInst){return null;};
    
    public ArrayList<String> mostrarEdicion(String nick){
        Singleton sm = Singleton.getInstance();
        Usuario u = sm.obtenerUsuario(nick);
        ArrayList<String> ediciones = new ArrayList<>();
        Iterator<Map.Entry<String, Curso>> it = sm.getCursos().entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Curso> curso = it.next();
            Iterator<Map.Entry<String, Edicion>> ite = curso.getValue().getEdiciones().entrySet().iterator();
            while(ite.hasNext()){
                Map.Entry<String, Edicion> edic = ite.next();
                if(u instanceof Estudiante){
                    for(Iterator itinsc = edic.getValue().getInscripciones().iterator(); itinsc.hasNext();){
                        InscripcionE ie = (InscripcionE) itinsc.next();
                        if(ie.getEst().getNick().equals(nick)){
                            ediciones.add(edic.getValue().getNombreEdicion());
                        }
                    }
                }else{
                    if(edic.getValue().obtenerDocente(nick)!=null){
                        ediciones.add(edic.getValue().getNombreEdicion());
                    }
                } 
            }
        }
        return ediciones;
    }
    
    public ArrayList<DatosEdicion> mostrarEdicionFULL(String nick){
        Singleton sm = Singleton.getInstance();
        Usuario u = sm.obtenerUsuario(nick);
        ArrayList<DatosEdicion> ediciones = new ArrayList<>();
        Iterator<Map.Entry<String, Curso>> it = sm.getCursos().entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Curso> curso = it.next();
            String c2 = curso.getValue().getNombre();
            DTInstituto inst = obtenerInstitutoCurso(c2);
            Iterator<Map.Entry<String, Edicion>> ite = curso.getValue().getEdiciones().entrySet().iterator();
            while(ite.hasNext()){
                Map.Entry<String, Edicion> edic = ite.next();
                if(u instanceof Estudiante){
                    for(Iterator itinsc = edic.getValue().getInscripciones().iterator(); itinsc.hasNext();){
                        InscripcionE ie = (InscripcionE) itinsc.next();
                        if(ie.getEst().getNick().equals(nick) && (ie.getEstado()==EstadoInscripcion.ACEPTADA)){
                            DatosEdicion de = new DatosEdicion(inst.getNombre(), c2, edic.getValue().getNombreEdicion(),edic.getValue().getEstado());
                            ediciones.add(de);
                        }
                    }
                }else{
                    if(edic.getValue().obtenerDocente(nick)!=null){
                        DatosEdicion de = new DatosEdicion(inst.getNombre(), c2, edic.getValue().getNombreEdicion(),edic.getValue().getEstado());
                        ediciones.add(de);
                    }
                } 
            }
        }
        return ediciones;
    }
    
    public ArrayList<String> mostrarProgramasUsuario(String nick){
        Singleton sm = Singleton.getInstance();
        Usuario u = sm.obtenerUsuario(nick);
        ArrayList<String> programas = new ArrayList<>();
        //if(sm.getProgramas()!=null){
            Iterator<Map.Entry<String, Programa>> it = sm.getProgramas().entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String, Programa> prog = it.next();
                if(u instanceof Estudiante){
                    //if(prog.getValue().getInscripciones()!=null){
                       for (Iterator it2 = prog.getValue().getInscripciones().iterator(); it2.hasNext();) {
                            InscripcionP ip = (InscripcionP) it2.next();
                            if(ip.getEst().getNick().equals(nick)){
                                programas.add(prog.getValue().getNombre());
                            }
                        } 
                    //}else System.out.println("hay programas pero no inscripciones");
                }else{
                    //if(prog.getValue().getCursos()!=null){
                        Iterator<Map.Entry<String, Curso>> itc = prog.getValue().getCursos().entrySet().iterator();
                        while(itc.hasNext()){
                            Map.Entry<String, Curso> curso = itc.next();
                            //if(curso.getValue().getEdiciones()!=null){
                                Iterator<Map.Entry<String, Edicion>> ite = curso.getValue().getEdiciones().entrySet().iterator();
                                while(ite.hasNext()){
                                    Map.Entry<String, Edicion> edic = ite.next();
                                    if(edic.getValue().getDocentes()!=null){
                                        Iterator<Map.Entry<String, Usuario>> itd = edic.getValue().getDocentes().entrySet().iterator();
                                        while(itd.hasNext()){
                                            Map.Entry<String, Usuario> docente = itd.next();
                                            if(docente.getValue().getNick().equals(nick)&&!programas.contains(prog.getValue().getNombre())){
                                                programas.add(prog.getValue().getNombre());
                                            }
                                        } 
                                    }   
                                } 
                            //}else System.out.println("hay cursos pero no tienen ediciones");
                            
                        } 
                    //}else System.out.println("hay programas pero no tienen cursos"); 
                }
            }
        //}else System.out.println("no hay programas en el sistema");
        return programas;
    }
    
    public DTInstituto institutoEdicion(String nombreE){
        Singleton sm = Singleton.getInstance();
        DTInstituto res = null;
        Map inst = sm.getInstitutos();
        Iterator<Map.Entry<String, Instituto>> it = inst.entrySet().iterator();
        while(it.hasNext()){
           Map.Entry<String, Instituto> i1 = it.next();
           Instituto i3 = (Instituto)i1.getValue();
           Map c1 = i3.getCursos();
           Iterator<Map.Entry<String, Curso>> itc = c1.entrySet().iterator();
           while(itc.hasNext()){
               Map.Entry<String, Curso> c2 = itc.next();
               Curso c3 = (Curso) c2.getValue();
               Map e1 = c3.getEdiciones();
               Iterator<Map.Entry<String, Edicion>> ite= e1.entrySet().iterator();
               while(ite.hasNext()){
                   Map.Entry<String,Edicion> e2 = ite.next();
                   Edicion e3 =  e2.getValue();
                   if(e3.getNombreEdicion()==nombreE){
                       res=i3.getDatos();
                       break;
                   }
               }
           }
        }
        return res;
    }
    
    public DTCurso cursoEdicion(String nombreE){
           Singleton sm = Singleton.getInstance();
           DTCurso res = null;
           Map c1 = sm.getCursos();
           Iterator<Map.Entry<String, Curso>> itc = c1.entrySet().iterator();
           while(itc.hasNext()){               
               Map.Entry<String, Curso> c2 = itc.next();               
               Curso c3 = (Curso) c2.getValue();
               Map e1 = c3.getEdiciones();
               Iterator<Map.Entry<String, Edicion>> ite= e1.entrySet().iterator();
               while(ite.hasNext()){
                   Map.Entry<String,Edicion> e2 = ite.next();
                   Edicion e3 =  e2.getValue();
                   if(e3.getNombreEdicion().equals(nombreE)){
                       res=c3.getDatos();
                       break;
                   }
               }
           }
        
        return res;
    }
    
    public ArrayList<String> mostrarCursosDocente(String nick){
        Singleton sm = Singleton.getInstance();
        ArrayList<String> cursos = new ArrayList<>();
        Iterator<Map.Entry<String, Curso>> it = sm.getCursos().entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Curso> curso = it.next();
            Iterator<Map.Entry<String, Edicion>> ite = curso.getValue().getEdiciones().entrySet().iterator();
            while(ite.hasNext()){
                Map.Entry<String, Edicion> edic = ite.next();
                Iterator<Map.Entry<String, Usuario>> itd = edic.getValue().getDocentes().entrySet().iterator();
                while(itd.hasNext()){
                    Map.Entry<String, Usuario> docente = itd.next();
                    if(docente.getValue().getNick().equals(nick)&&!cursos.contains(curso.getValue().getNombre())){
                        cursos.add(curso.getValue().getNombre());
                    }  
                }
            }
        }
        return cursos;
    } 
    
    public Set<DTPrograma> mostrarProgramaDoc(String nick){return null;};
    
    public Set<DTUsuario> consultaUsuarios(){
        Singleton sm = Singleton.getInstance();
        Map<String,Usuario> usrs = sm.getUsuarios();
        Set<DTUsuario> usuarios = new HashSet();
        Iterator<Map.Entry<String,Usuario>> it = usrs.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Usuario> u = it.next();
            DTUsuario u1 = u.getValue().getDatos();
            usuarios.add(u1);
        }
        return usuarios;
    
    };
    
    public DTUsuario seleccionarUsuario(String nick){return null;};
    
    //public void modificarDatosUsuario(DTUsuario nuevo){};
    
    public void agregarCursoPrograma(String nombreP, String nombreC){
        Persistencia p = Persistencia.getInstance();
        EntityManager em = p.obtenerEntityManager();
        Singleton sm = Singleton.getInstance();
        Programa p1 = sm.obtenerPrograma(nombreP);
        Curso c1 = sm.obtenerCurso(nombreC);
        c1.agregarPrograma(p1);
        p1.agregarCurso(c1);
        em.getTransaction().begin();
        em.persist(p1);
        em.persist(c1);
        em.getTransaction().commit();
    };
    //corregir
    
    public List mostrarCursos(){
    	
        Singleton sm = Singleton.getInstance();
        List cur = new ArrayList();
        for(Map.Entry<String,Curso> entry : sm.getCursos().entrySet()){
            Curso c1 = (Curso)entry.getValue();
            DTCurso dtc = c1.getDatos();
            cur.add(dtc);
            
        }
        
        return cur;
    }
    
    public List mostrarProgramas(){
        Singleton sm = Singleton.getInstance();
        List prog = new LinkedList();
        for(Map.Entry<String,Programa> entry : sm.getProgramas().entrySet()){
            Programa p1 = (Programa)entry.getValue();
            DTPrograma dprog = p1.getDatos();
            prog.add(dprog);
        }
        
        return prog;
          
    }
    
    public DTCurso obtenerCurso(String nombreC){
        Singleton sm = Singleton.getInstance();
        Curso c1 = sm.obtenerCurso(nombreC);
        DTCurso dtc = null;
        if(c1!=null){
             dtc = c1.getDatos();
        }
        
        return dtc;
    }
    
    public DTPrograma obtenerPrograma(String nombreP){
        
        Singleton sm = Singleton.getInstance();
        Programa p1 = sm.obtenerPrograma(nombreP);
        DTPrograma dtp=null;
        if(p1!=null){
           dtp = p1.getDatos();
        }
        return dtp;
    }
    
    public ArrayList<String> cursosPrograma(String nombreP){
        
        Singleton sm = Singleton.getInstance();
        Programa p1 = sm.obtenerPrograma(nombreP);
        Iterator<Map.Entry<String, Curso>> it = p1.getCursos().entrySet().iterator();
        ArrayList<String> nombresC = new ArrayList<String>();
        while(it.hasNext()){
           Map.Entry<String, Curso> inst = it.next();
           nombresC.add(inst.getValue().getNombre());
        }
        return nombresC;
        
    }
    
    public ArrayList<String> ProgramasCursos(String nombreC){
        Singleton sm = Singleton.getInstance();
        Curso c1 = sm.obtenerCurso(nombreC);
        Iterator<Map.Entry<String, Programa>> it = c1.getProgramas().entrySet().iterator();
        ArrayList<String> nombresP = new ArrayList<String>();
        while(it.hasNext()){
           Map.Entry<String, Programa> prog = it.next();
           nombresP.add(prog.getValue().getNombre());
        }
        return nombresP;
        
    }
    
    public Set<String> CategoriasProgramas(String nombreP){
        Singleton sm = Singleton.getInstance();
        Programa p = sm.obtenerPrograma(nombreP);
        Set<String> nombresCat = new HashSet<String>();
        if(p!=null){
            Iterator<Map.Entry<String, Curso>> it = p.getCursos().entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String, Curso> curso = it.next();
                for(String Nomcat : CategoriasCurso(curso.getValue().getNombre())){
                    nombresCat.add(Nomcat);
                }
            } 
        }
        return nombresCat;
    }
    
    public ArrayList<String> CategoriasCurso(String nombreC){
        Singleton sm = Singleton.getInstance();
        Curso c1 = sm.obtenerCurso(nombreC);
        Iterator<Map.Entry<String, Categoria>> it = c1.getCategorias().entrySet().iterator();
        ArrayList<String> nombresCat = new ArrayList<String>();
        while(it.hasNext()){
           Map.Entry<String, Categoria> cat = it.next();
           nombresCat.add(cat.getValue().getNombre());
        }
        return nombresCat;
    }
    
    public boolean chequearInstituto(String nombreI){
        Singleton sm = Singleton.getInstance();
        Persistencia p = Persistencia.getInstance();
        EntityManager em = p.obtenerEntityManager();
        boolean ok = true;
        try{
            //em.getTransaction().begin();
            Query q = em.createQuery("SELECT i FROM Instituto i WHERE i.nombre = ?1");
            q.setParameter(1, nombreI);
            if(q.getSingleResult()!=null);
            
        } catch(NoResultException e) {
            return ok=false;
        }
        //em.getTransaction().commit();
        if(sm.obtenerInstituto(nombreI)!=null || ok){
            return true;
        }else
            return false;
    }
    
    public void modificarNombreInstituto(String nombreI, String nuevonombre){};
    
    public ArrayList<String> cursosInstituto(String nombreI){
        Singleton sm = Singleton.getInstance();
        Instituto inst = sm.obtenerInstituto(nombreI);
        Iterator<Map.Entry<String,Curso>> it = inst.getCursos().entrySet().iterator();
        ArrayList<String> cursosInst = new ArrayList<String>();
        while(it.hasNext()){
            Map.Entry<String,Curso> cur = it.next();
            cursosInst.add(cur.getValue().getNombre());
        }
        return cursosInst;
    
    };
    
    public Curso obtenerCursoDelInstituto(String nombreI, String nombreC){
        Singleton sm = Singleton.getInstance();
        Instituto inst = sm.obtenerInstituto(nombreI);
        Curso cur = inst.obtenerCurso(nombreC);
        return cur;
        }
    
     public ArrayList<String> EdicionesCurso(String nombreCurso){
        Singleton sm = Singleton.getInstance();
        Curso cur = sm.obtenerCurso(nombreCurso);
        ArrayList<String> edicionesCurso = new ArrayList<String>();
        Iterator<Map.Entry<String,Edicion>> it = cur.getEdiciones().entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,Edicion> edic = it.next();
            edicionesCurso.add(edic.getValue().getNombreEdicion());
        }
        return edicionesCurso;
     
     };
    
     
     
     public DTEdicion datosEdicion(String nombreCurso, String nombreEdicion){
        Curso cur = Singleton.getInstance().obtenerCurso(nombreCurso);
        Edicion edi = cur.obtenerEdicion(nombreEdicion);
        if(edi != null){
            return edi.getDatos();
        }
        return null;
     }
     
    public void modificarEstadoInscripcion(String cur, String ed, String est, EstadoInscripcion estado){
        Singleton sm = Singleton.getInstance();
        Curso c1 = sm.obtenerCurso(cur);
        Persistencia p1 = Persistencia.getInstance();
        EntityManager em = p1.obtenerEntityManager();
        System.out.println("El curso es: " + cur);
        Iterator<Map.Entry<String,Edicion>> it = c1.getEdiciones().entrySet().iterator();
        while(it.hasNext()){
            Map.Entry e1 = it.next();
            Edicion e2 = (Edicion)e1.getValue();
            if(e2.getNombreEdicion().equals(ed)){
                List i1 = e2.getInscripciones();
                for(Object insc   : i1){
                    InscripcionE i2 = (InscripcionE)insc;
                    if(i2.getEst().getNick().equals(est)){
                        i2.setEstado(estado);
                        if(estado==EstadoInscripcion.ACEPTADA && e2.getCuposMax()>0){
                            i2.setEstado(estado);
                            e2.setCupo_max(e2.getCuposMax()-1);
                        }else{
                            if(e2.getCuposMax()<=0){
                                i2.setEstado(estado.RECHAZADA);
                            }else{
                               i2.setEstado(estado); 
                            }
                        }
                        em.getTransaction().begin();
                        em.persist(i2);
                        em.getTransaction().commit();
                        return;
                    }
                } 
            }
          
        }
    }
     
    public boolean checkCurso(String nomCurso){
        Singleton sm = Singleton.getInstance();
        if(sm.obtenerCurso(nomCurso)!=null){
            return true;
        }else
            return false;
        
    } 
     
    public boolean checkPrograma(String nombrep){
        Singleton sm = Singleton.getInstance();
        Programa p = sm.obtenerPrograma(nombrep);
        if(p != null)
           return true;
        else
            return false;  
    };
    
    public void modificarDatosPrograma(DTPrograma datos){
        Singleton sm = Singleton.getInstance();
        Persistencia p1 = Persistencia.getInstance();
        EntityManager em = p1.obtenerEntityManager();
        Programa p = sm.obtenerPrograma(datos.getNombre());
        p.setNombre(datos.getNombre());
        p.setDescripcion(datos.getDescripcion());
        p.setFecha_ini(datos.getFechaInicial());
        p.setFecha_fin(datos.getFechaFinal());
        p.setFechaAlta(datos.getFechaAlta());
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    
    };
    
    public void crearPrograma(DTPrograma datos){
        Persistencia p = Persistencia.getInstance();
        EntityManager em = p.obtenerEntityManager();
        Singleton sm = Singleton.getInstance();
        Programa pr = new Programa(datos.getNombre(),datos.getDescripcion(),datos.getFechaInicial(),
                                    datos.getFechaFinal(),datos.getFechaAlta(), datos.getImagenDir());
        sm.agregarPrograma(pr);
        em.getTransaction().begin();
        em.persist(pr);
        em.getTransaction().commit();
    };
    
    public String checkEdicionCurso(String nombreC){
        Singleton sm = Singleton.getInstance();
        Curso cur = sm.obtenerCurso(nombreC);
        Date today = new Date();
        Iterator<Map.Entry<String,Edicion>> it = cur.getEdiciones().entrySet().iterator();
        while(it.hasNext()){
           Map.Entry<String, Edicion> edic = it.next();
           if (today.getYear() == edic.getValue().getFechaIni().getYear() && edic.getValue().getEstado()==EstadoEdicion.INSCRIBIENDO ){
               return edic.getValue().getNombreEdicion();   
           }
        }
       return null;
    };
    
   public boolean InscripcionCorrectaEstEdi(String nombreC, String nombreE, Date fecha){
       Singleton sm = Singleton.getInstance();
       Curso curso = sm.obtenerCurso(nombreC);
   //    boolean flag;
       if(curso != null){
            Edicion edicion = curso.obtenerEdicion(nombreE);
             return (edicion.getFechaIni().getYear() == fecha.getYear()) && fecha.after(edicion.getFechaIni());
        //     if (flag) System.out.println("La fecha es menor a la de inicio");
        //     else System.out.println("La fecha es MAYOR a la de inicio");
        //    if( /*(edicion.getFechaIni().getYear() == fecha.getYear()) &&*/ fecha.before(edicion.getFechaIni())  ){
        //             flag = true;
                     
        //    }
       //     else{
      //           flag = false;
       //     }
       //     return !flag;
       }
       else return false;
   }
   
   public boolean InscripcionEstEdiIsNull(String nombreI, String nombreC, String nombreEdi, String nombreEst, Date fecha ){
       return nombreI.isEmpty() && nombreC.isEmpty() && nombreEdi.isEmpty() && nombreEst.isEmpty() && fecha == null;
   }
   
    public boolean ExisteRegistroInscripcionE(String nombreEst, String nombreC, String nombreE){
        Singleton sm = Singleton.getInstance();
        Curso cur = sm.obtenerCurso(nombreC);
        if (cur != null){ 
            Edicion edi  = cur.obtenerEdicion(nombreE);
            if(edi != null){
                InscripcionE existe = edi.obtenerInscripcionE(nombreEst);
                if( existe != null) return true;
                else return false;
            }
            else return false;
        }
        else return false;
    };
    
    public ArrayList<String> listarEstudiantes(){
        Singleton sm = Singleton.getInstance();
        Iterator <Map.Entry<String,Usuario>> it = sm.getUsuarios().entrySet().iterator();
        ArrayList<String> estudiantes = new ArrayList<String>();
        while(it.hasNext()){
            Map.Entry<String, Usuario> usr = it.next();
            if(usr.getValue() instanceof Estudiante){
               estudiantes.add(usr.getValue().getNick());
           }
        }
        return estudiantes;
    };
    
    public ArrayList<String> listarDocentesInstituo(String nomInst){
        Singleton sm = Singleton.getInstance();
        Instituto inst = sm.obtenerInstituto(nomInst);
        ArrayList<String> docentes = new ArrayList<String>();
        Iterator <Map.Entry<String,Docente>> it = inst.getDocentes().entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Docente> doc = it.next();
            docentes.add(doc.getValue().getNick());
        }
        return docentes;
    }
    
    public boolean checkincripcionPrograma (String nomP, String nickE){
        Singleton sm = Singleton.getInstance();
        Programa p = sm.obtenerPrograma(nomP);
        List listaInsc = p.getInscripciones();
        Iterator it = listaInsc.iterator();
        boolean existe = false;
        InscripcionP insc;
        while(it.hasNext() && !existe){
            insc = (InscripcionP)it.next();
            if(insc.getEst().getNick().equals(nickE)){
                existe = true;
            }
        }
        return existe;
    }
    
    public void modificarInscPrograma (String nomP, String nickE, Date nuevaFecha){
        Persistencia p = Persistencia.getInstance();
        EntityManager em = p.obtenerEntityManager();
        Singleton sm = Singleton.getInstance();
        Programa prog = sm.obtenerPrograma(nomP);
        List listaInsc = prog.getInscripciones();
        Iterator it = listaInsc.iterator();
        InscripcionP insc = null;
        boolean existe = false;
        while(it.hasNext()&& !existe){
            insc = (InscripcionP)it.next();
            if(insc.getEst().getNick().equals(nickE)){
                insc.modificarDatos(nuevaFecha);
                existe = true;
            }
        }
        em.getTransaction().begin();
        em.persist(insc);
        em.getTransaction().commit();
    }
    
    public void InscripcionPrograma (String nomP, String nickE, Date fecha){
        Persistencia p = Persistencia.getInstance();
        EntityManager em = p.obtenerEntityManager();
        Singleton sm = Singleton.getInstance();
        Programa prog = sm.obtenerPrograma(nomP);
        Usuario est = sm.obtenerUsuario(nickE);
        InscripcionP insc = new InscripcionP(fecha, (Estudiante) est);
        prog.addInscripcion(insc);
        em.getTransaction().begin();
        em.persist(insc);
        em.getTransaction().commit();
    }
    
    public void crearInscripcionEstudiante(String nombreC, String nombreEdi, String nombreEst, Date fecha_insc, String video){//FALTA CONTROLAR CUPO MAXIMO DE LA EDICION
        Persistencia p = Persistencia.getInstance();
        EntityManager em = p.obtenerEntityManager();
        Singleton sm = Singleton.getInstance();
        Curso curso = sm.obtenerCurso(nombreC);
        if (curso != null){
            Edicion edicion = curso.obtenerEdicion(nombreEdi);
            if (edicion != null){
                Usuario u =  sm.obtenerUsuario(nombreEst);
                Estudiante e = (Estudiante) u;
                InscripcionE nueva = new InscripcionE(fecha_insc, e,video);
                edicion.agregarInscripcionE(nueva);
                em.getTransaction().begin();
                em.persist(nueva);
                em.getTransaction().commit();
            }
        }
 
    }
   public void modificarInscripcionEstudiante(String nombreC, String nombreEdi, String nombreEst, Date nueva_fecha){
       Persistencia p = Persistencia.getInstance();
       EntityManager em = p.obtenerEntityManager();
       Singleton sm = Singleton.getInstance();
       Curso curso = sm.obtenerCurso(nombreC);
       Edicion edicion = curso.obtenerEdicion(nombreEdi);
       InscripcionE insc = edicion.obtenerInscripcionE(nombreEst);
       insc.modificarDatos(nueva_fecha);
       em.getTransaction().begin();
       em.persist(insc);
       em.getTransaction().commit();
       
   }
    
    public Set<String> pickCurso (String nombreC){return null;};
    
    public void modRegAlumEdicion(Date nuevaFecha){};
    
    public InscripcionE crearRegistro(String correo, Date FechaInsc){return null;};
    
    public Set<DTCurso> indicarInstitucion(String nombre){return null;};
    
    public DTCurso seleccionarCurso(String nombre){return null;};
    
    public boolean seleccionarEdicionOPrograma(String nombre, DTEdicion edicion){return false;};
    
    public Set<DTEdicion> listaEdicion(String Instituto){return null;};
    
    public DTEdicion seleccionarEdicion(String nombre, DTEdicion edicion){return null;};
    
    public void editarEdicion(DTEdicion dataedicion){};
    
    public void indicarInstitucion2(String nombreI){}; // que hace?
    
    public boolean indicarNombreCurso(String nombreC){return false;}; // que hace?
    
    public void registrarCurso(String nomInst, DTCurso datoscurso, List previas, List categorias){
        Persistencia p = Persistencia.getInstance();
        EntityManager em = p.obtenerEntityManager();
        Singleton sm = Singleton.getInstance();
        
        Curso c = new Curso(datoscurso.getNombre(), datoscurso.getDescripcion(), datoscurso.getDuracion(), datoscurso.getHoras(), datoscurso.getCreditos(), datoscurso.getFechaReg(), datoscurso.getUrl(), datoscurso.getImagenDir());
        String previaCurso;
        if(previas!=null){
            for(Object previa : previas){
                previaCurso = previa.toString();
                System.out.println("LA MATERIA ES" + previa);
                c.agregarPrevias(sm.obtenerCurso(previaCurso));
            }
        }
        //hay que hacer un try catch
        
        sm.obtenerInstituto(nomInst).addCurso(c);  
        //Query q = em.createQuery("SELECT i FROM Instituto i WHERE i.nombre = ?1");
        //q.setParameter(1, nomInst/*inst.getNombre()*/);
        //Instituto inst1 = (Instituto) q.getSingleResult();
        //inst1.addCurso(c);
       
        sm.agregarCurso(c);
        agregarCategoriaCurso(c.getNombre(), categorias);
        em.getTransaction().begin();
        em.persist(c);
        //em.persist(inst1);
        em.getTransaction().commit();
    }; 
    
    public void altaEdicionCurso(String nombCurso, DTEdicion datos, List docentes){
        Persistencia p = Persistencia.getInstance();
        EntityManager em = p.obtenerEntityManager();
        Singleton sm = Singleton.getInstance();
        Curso c1 = sm.obtenerCurso(nombCurso);
        if(c1.obtenerEdicion(datos.getNombre())==null){
            Edicion e = new Edicion(datos.getNombre(), datos.getFechaIni(), datos.getFechaFin(), datos.getCuposMax(), datos.getFechaPub(), datos.getImagenDir());
            String doc;
            for(Object docente : docentes){
                doc = docente.toString();
                e.agregarDocente((Docente)sm.obtenerUsuario(doc));//****************************************************
            }
            c1.agregarEdicion(e);
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
        }
       
    }
    
    public boolean checkExisteEdicionCurso(String nomCurso, String nomEdic){
        Singleton sm = Singleton.getInstance();
        if(sm.obtenerCurso(nomCurso).obtenerEdicion(nomEdic)!=null){
            return true;
        }else return false;
    }
    public void editarCursoInst(DTCurso datos){};
    
    public void cancelar(){};
    
    public void aceptar(){};
    
    public Categoria obtenerCategoria(String nombreCat){
        Singleton sm = Singleton.getInstance();
       return sm.obtenerCategoria(nombreCat);
    }
    
    public void altaCategoria(String nombreCat){
        Persistencia p = Persistencia.getInstance();
        Singleton sm = Singleton.getInstance();
        EntityManager em = p.obtenerEntityManager();
        Categoria c = new Categoria(nombreCat);
        sm.agregarCategoria(c);
      
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
      
    }
    
    public void modificarCategoria(String viejo,String nuevo){
        Persistencia p = Persistencia.getInstance();
        EntityManager em = p.obtenerEntityManager();
        Singleton sm = Singleton.getInstance();
        Categoria c = sm.obtenerCategoria(viejo);
        c.setNombre(nuevo);
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
    }
    
    public ArrayList<String> listarCategorias(){
        Singleton sm = Singleton.getInstance();
        Iterator<Map.Entry<String, Categoria>> it = sm.getCategorias().entrySet().iterator();       
        ArrayList<String> nombresCat = new ArrayList<String>();
        while(it.hasNext()){
           Map.Entry<String, Categoria> cat = it.next();
           nombresCat.add(cat.getValue().getNombre());
        }
        return nombresCat;
    }
    
    
    
    public void agregarCategoriaCurso(String nombreCurso, List nombreCat){
 //       Persistencia p = Persistencia.getInstance();
 //       EntityManager em = p.obtenerEntityManager();
        Singleton sm = Singleton.getInstance();
        Categoria c ; 
        Curso cur = sm.obtenerCurso(nombreCurso);
        String nombreCateg;
        if(nombreCat!=null){
            for(Object nombre : nombreCat){
                nombreCateg = nombre.toString();
                c = sm.obtenerCategoria(nombreCateg);
                cur.agregarCategoria(c);
            }
        }
      
    /*    em.getTransaction().begin();
        em.persist(cur);
        em.getTransaction().commit();*/
        
        
    }

    public ArrayList<String> listarCursosCategoria (String nomCat){
        Singleton sm = Singleton.getInstance();
        ArrayList<String> cursos = new ArrayList<>();
        Iterator<Map.Entry<String, Curso>> it = sm.getCursos().entrySet().iterator();
        Categoria cate = null;
        while(it.hasNext()){
           Map.Entry<String, Curso> cur = it.next();
           cate = cur.getValue().getCategorias().get(nomCat);
           if(cate!=null){
               cursos.add(cur.getValue().getNombre());
           }
        }
        return cursos;
    }
    
    public Set<DTCurso> DTcursosPrograma(String nombreP){
        Programa p = Singleton.getInstance().obtenerPrograma(nombreP);
        Map<String,Curso> crs = p.getCursos();
        Set<DTCurso> cursos = new HashSet();
        Iterator<Map.Entry<String,Curso>> it = crs.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Curso> c = it.next();
            DTCurso c1 = c.getValue().getDatos();
            cursos.add(c1);
        }
        return cursos;
    
    };
    
     public ArrayList<String> listarDocentesEdicion(String nombreC, String nombreE){
        Curso cur = Singleton.getInstance().obtenerCurso(nombreC);
        Edicion edi = cur.obtenerEdicion(nombreE);
        ArrayList<String> docentesE = new ArrayList<>();
        Iterator<Map.Entry<String,Docente>> it = edi.getDocentes().entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,Docente> doc = it.next();
            docentesE.add(doc.getValue().getNick());
        }
        return docentesE;
    }
    public ArrayList<DTInscripcionE> listarDatosInscEdicion(String nombreC, String nombreE){
        ArrayList<DTInscripcionE> datosinsc = new ArrayList<>();
        Singleton sm = Singleton.getInstance();
        DTInscripcionE datos;
        Edicion e1 = sm.obtenerCurso(nombreC).obtenerEdicion(nombreE);
        InscripcionE insc;
        for (Object obj : sm.obtenerCurso(nombreC).obtenerEdicion(nombreE).getInscripciones()){
            insc = (InscripcionE) obj;
            Usuario u = insc.getEst();
            DTUsuario datosEst = new DTUsuario(u.getNick(), u.getNombre(), u.getApellido(), u.getCorreo(), u.getFecha_de_nac(), u.getContrasena());
            datos = new DTInscripcionE(insc.getFecha(), datosEst, insc.getEstado(),nombreC, nombreE, insc.getVideo(), insc.getNota(),e1.getEstado());
            datosinsc.add(datos);
        }
        return datosinsc;
    }
     
         
     public ArrayList<DTInscripcionE> datosInscripEstudiante (String nick){
         Singleton sm = Singleton.getInstance();         
         ArrayList<DTInscripcionE> datosinsc = new ArrayList<>();
         Iterator<Map.Entry<String,Curso>> it = sm.getCursos().entrySet().iterator();
         DTInscripcionE datos;
         InscripcionE inscripcion;
         while(it.hasNext()){
            Map.Entry<String, Curso> curso = it.next();
            Iterator<Map.Entry<String,Edicion>> ite = curso.getValue().getEdiciones().entrySet().iterator();
            while(ite.hasNext()){
                Map.Entry<String, Edicion> edicion = ite.next();
                for(Object obj : edicion.getValue().getInscripciones()){
                    inscripcion = (InscripcionE) obj;
                    if(inscripcion.getEst().getNick().equalsIgnoreCase(nick)){
                        Usuario u = inscripcion.getEst();
                        DTUsuario datosEst = new DTUsuario(u.getNick(), u.getNombre(), u.getApellido(), u.getCorreo(), u.getFecha_de_nac(), u.getContrasena());
                        datos = new DTInscripcionE(inscripcion.getFecha(), datosEst, inscripcion.getEstado(),curso.getKey(),edicion.getKey(),inscripcion.getVideo(),inscripcion.getNota(),edicion.getValue().getEstado(),inscripcion.getEstadoCurso(),inscripcion.getFecha_aprobado());
                        datosinsc.add(datos);
                    }
                }
            }
        } 
        return datosinsc;
     }
     
     public ArrayList<DTCurso> cursosEstudiante(String nick){
        Singleton sm = Singleton.getInstance();
        Usuario u = sm.obtenerUsuario(nick);
        ArrayList<DTCurso> resultado = new ArrayList<>();
        Iterator<Map.Entry<String, Curso>> it = sm.getCursos().entrySet().iterator();
        while(it.hasNext()){    
            Map.Entry<String, Curso> curso = it.next();
            String c2 = curso.getValue().getNombre();
            //DTInstituto inst = obtenerInstitutoCurso(c2);
            Iterator<Map.Entry<String, Edicion>> ite = curso.getValue().getEdiciones().entrySet().iterator();
            while(ite.hasNext()){
                Map.Entry<String, Edicion> edic = ite.next();
                if(u instanceof Estudiante){
                    for(Iterator itinsc = edic.getValue().getInscripciones().iterator(); itinsc.hasNext();){
                        InscripcionE ie = (InscripcionE) itinsc.next();
                        if(ie.getEst().getNick().equals(nick) && (ie.getEstado()==EstadoInscripcion.ACEPTADA)){
                            //DatosEdicion de = new DatosEdicion(inst.getNombre(), c2, edic.getValue().getNombreEdicion())
                            resultado.add(curso.getValue().getDatos());
                        }
                    }
                }
            }
        }
        return resultado;
     }
     
     public void calificarCurso(String nombreC, String nick, int nota){
         Persistencia p = Persistencia.getInstance();
         EntityManager em = p.obtenerEntityManager();
         Singleton sm = Singleton.getInstance();
         CalifEstuCur calif = new CalifEstuCur(nota, (Estudiante) sm.obtenerUsuario(nick));
         sm.obtenerCurso(nombreC).agregarCalificacion(calif);
         em.getTransaction().begin();
         em.persist(calif);
         em.getTransaction().commit();
     }
     
     public float obtenerCalifCurso(String nombreC){
         Singleton sm = Singleton.getInstance();
         int contador=0;
         float suma = 0;
         CalifEstuCur cal = null;
         for (Object calif : sm.obtenerCurso(nombreC).getCalific()){
             cal = (CalifEstuCur) calif;
             suma = suma + cal.getCalif();
             contador++;
         }
         if(contador!=0){
             return suma/contador;
         }else return suma;
     }
     
     public boolean checkValoracionEst(String nombreC, String nick){
         Singleton sm = Singleton.getInstance();
         CalifEstuCur calif = null;
         boolean califico = false;
         for( Object nota : sm.obtenerCurso(nombreC).getCalific()){
             calif = (CalifEstuCur) nota;
             if (calif.getEst().getNick().equals(nick)){
                 califico = true;
             }
         }
         return califico;
     }
     
       public float notaEstCurso(String nombreC, String nick){
         Singleton sm = Singleton.getInstance();
         CalifEstuCur calif = null;
         float califico = 0;
         for( Object nota : sm.obtenerCurso(nombreC).getCalific()){
             calif = (CalifEstuCur) nota;
             if (calif.getEst().getNick().equals(nick)){
                 califico = calif.getCalif();
             }
         }
         return califico;
     }
       
     public void comentarEdicion(String nick, String nomEd, String comentario){
         Singleton sm = Singleton.getInstance();
         Persistencia p = Persistencia.getInstance();
         EntityManager em = p.obtenerEntityManager();
         Estudiante est = (Estudiante)sm.obtenerUsuario(nick);
         DTCurso datosCurso = this.cursoEdicion(nomEd.trim());
         Curso cur = sm.obtenerCurso(datosCurso.getNombre());
         Comentarios coment = new Comentarios(comentario, est);
         cur.obtenerEdicion(nomEd).agregarComentario(coment);
         em.getTransaction().begin();
         em.persist(coment);
         em.getTransaction().commit();
     }
     
     public ArrayList<DTComentarios> obtenerComentariosEdicion (String nomEd){
         Singleton sm = Singleton.getInstance();
         ArrayList<DTComentarios> comentarios = new ArrayList<>();
         Curso cur = sm.obtenerCurso(cursoEdicion(nomEd).getNombre());
         Comentarios coments = null;
         for(Object com : cur.obtenerEdicion(nomEd).getComentarios()){
             coments = (Comentarios)com;
             comentarios.add(coments.getDatos());
         }
         return comentarios;
     }
     
     public void setComentario(List<Comentarios> comentarios, Comentarios resp, Long id){
        if(comentarios.isEmpty()){
            return;
        }
        for(Comentarios com : comentarios){
            if(com.getId()==id){
                System.out.println("LLEGUEEEEE");
                com.agregarRespuesta(resp);
                break;
            }
            else{
                setComentario(com.getRespuestas(),resp,id);
            }
        }
     }
     
     
     public void responderComentario(String nick, String nomEd, String respuesta, Long id){
         Singleton sm = Singleton.getInstance();
         System.out.println("El id es: "+ id + " La edicion es"+ nomEd + " La respuesta es: " + respuesta + " El nick es: "+nick );
         Curso cur = sm.obtenerCurso(cursoEdicion(nomEd).getNombre());
         Persistencia p = Persistencia.getInstance();
         EntityManager em = p.obtenerEntityManager();
         System.out.println(cur.getNombre());
         Comentarios coments = null;
         Estudiante est = (Estudiante)sm.obtenerUsuario(nick);
         Comentarios resp = new Comentarios(respuesta, est);
         /*for(Object com : cur.obtenerEdicion(nomEd).getComentarios()){
             coments = (Comentarios)com;
             if(coments.getId()==id){
                System.out.println("LLegue aca: " + coments.getComentario() + resp.getComentario());
                 coments.agregarRespuesta(resp);
                em.getTransaction().begin();
                em.persist(coments);
                em.getTransaction().commit();
             }
            
         }*/
        setComentario(cur.obtenerEdicion(nomEd).getComentarios(),resp,id);
        Edicion e1 = cur.obtenerEdicion(nomEd);
        em.getTransaction().begin();
        em.persist(e1);
        em.getTransaction().commit();
    }
     
     public void visitasCurso(String nombreC){
         Persistencia p = Persistencia.getInstance();
         EntityManager em = p.obtenerEntityManager();
         Singleton sm = Singleton.getInstance();
         Curso cur = sm.obtenerCurso(nombreC);
         cur.aumentarVisitas();
         em.getTransaction().begin();
         em.persist(cur);
         em.getTransaction().commit();
     }
     
     public DTCurso[] rankingCursos(){
         Singleton sm = Singleton.getInstance();
         
         List<DTCurso> cursos = mostrarCursos();
         DTCurso[] ordenados = new DTCurso[cursos.toArray().length];
         int k=0;
         for (DTCurso dtcur : cursos){
             ordenados[k]=dtcur;
             k++;
         }
         int i, j;
         DTCurso aux = null;
         for (i = 0; i < ordenados.length - 1; i++) {
            for (j = 0; j < ordenados.length - i - 1; j++) {
                if (ordenados[j + 1].getVisitas() > ordenados[j].getVisitas()) {
                    aux = ordenados[j + 1];
                    ordenados[j + 1] = ordenados[j];
                    ordenados[j] = aux;
                }
            }
        }
         return ordenados;
     }
    public void actualizrEstadoPrograma(String nombreP, String nick){
        Persistencia p = Persistencia.getInstance();
        EntityManager em = p.obtenerEntityManager();
        Singleton sm = Singleton.getInstance();
        Programa prog = sm.obtenerPrograma(nombreP);
        InscripcionP insP = null;
        boolean cursoAprobado = false;
        int contador = 0;
        int cantcursosProg = prog.getCursos().size();
        Iterator<Map.Entry<String, Curso>> it = prog.getCursos().entrySet().iterator();
        while(it.hasNext()){
            Curso cur = it.next().getValue();
            Iterator<Map.Entry<String, Edicion>> ite = cur.getEdiciones().entrySet().iterator();
            cursoAprobado = false;
            while(ite.hasNext() && !cursoAprobado){
                Edicion edi = ite.next().getValue();
                for(Object obj : edi.getInscripciones()){
                    InscripcionE insc = (InscripcionE)obj;
                    if(insc.getEst().getNick().equals(nick)){
                        if(insc.getEstadoCurso()==EstadoCurso.APROBADO && edi.getEstado()==EstadoEdicion.CERRADA){
                            cursoAprobado = true;
                            contador++;
                            break;
                        }
                    }
                }
            }
        }

        if(cantcursosProg==contador){
            for(Object obj : prog.getInscripciones()){
                insP = (InscripcionP)obj;
                if(insP.getEst().getNick().equals(nick)){
                    insP.setEstado(EstadoPrograma.APROBADO);
                    insP.setFecha_aprobado(new Date());
                    em.getTransaction().begin();
                    em.persist(insP);
                    em.getTransaction().commit();
                }
            }
        }
    }
    
    public DTInscripcionP obtenerEstadoProg(String nombreP, String nick){
        Singleton sm = Singleton.getInstance();
        Programa prog = sm.obtenerPrograma(nombreP);
        InscripcionP insP = null;
        DTInscripcionP resultado = null;
        for(Object obj : prog.getInscripciones()){
                insP = (InscripcionP)obj;
                if(insP.getEst().getNick().equals(nick)){
                    resultado = insP.getDatos();
                }
            }
        return resultado;
    }
    
    public ArrayList<DTInscripcionE> datosInscripEstProg (String nombreP, String nick){
        Singleton sm = Singleton.getInstance();
        Programa prog = sm.obtenerPrograma(nombreP);
        ArrayList<DTInscripcionE> datos = new ArrayList<>();
        //DTInscripcionE datosInsc = null;
        for(DTInscripcionE datosInsc : datosInscripEstudiante(nick)){
            if(prog.getCursos().containsValue(sm.obtenerCurso(datosInsc.getNombreCurso()))){
                datos.add(datosInsc);
                System.out.println("esta inscripto en: " + datosInsc.getNombreEdicion());
            }
        }
        return datos;
    }
    public DTCurso obtenerCursoDeEdicion(String nomEdi){
        Singleton sm = Singleton.getInstance();
        Iterator<Map.Entry<String, Curso>> it = sm.getCursos().entrySet().iterator();
        while(it.hasNext()){
            Curso cur = it.next().getValue();
            Iterator<Map.Entry<String, Edicion>> itedi = cur.getEdiciones().entrySet().iterator();
            while(itedi.hasNext()){
                if(itedi.next().getValue().getNombreEdicion().equals(nomEdi)){
                    return cur.getDatos();
                }
            }
        }
        return null;
    }
    
    public boolean checkEdicion(String curso, String edicion){
        Singleton sm = Singleton.getInstance();
        Curso c1 = sm.obtenerCurso(curso);
        Edicion e1 = null;
        if(curso!=null){
            e1 = c1.obtenerEdicion(edicion);
        }
        return e1!=null;
    }
    
    public boolean checkPrevias(String nick, String curso){
        Singleton sm = Singleton.getInstance();
        Curso c1 = sm.obtenerCurso(curso);
        int contador=0;
        int cantCursos= 0;
        boolean cursoAprobado = false;
        if(c1!=null){
            Map<String, Curso> previas = c1.getPrevias();
            cantCursos = previas.size();
            Iterator<Map.Entry<String, Curso>> itprev = previas.entrySet().iterator();
            while(itprev.hasNext()){
                Curso c2 = itprev.next().getValue();
                Iterator<Map.Entry<String, Edicion>> ite = c2.getEdiciones().entrySet().iterator();
                cursoAprobado = false;
                while(ite.hasNext() && !cursoAprobado){
                    Edicion edi = ite.next().getValue();
                    for(Object obj : edi.getInscripciones()){
                        InscripcionE insc = (InscripcionE)obj;
                        if(insc.getEst().getNick().equals(nick)){
                            if(insc.getEstadoCurso()==EstadoCurso.APROBADO && edi.getEstado()==EstadoEdicion.CERRADA){
                                cursoAprobado = true;
                                contador++;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return contador==cantCursos;
    }
            
             
}
