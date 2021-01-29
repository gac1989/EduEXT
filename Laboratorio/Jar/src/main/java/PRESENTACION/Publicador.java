/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PRESENTACION;


import Datatypes.DTComentarios;
import Datatypes.DTCurso;
import Datatypes.DTEdicion;
import Datatypes.DTInscripcionE;
import Datatypes.DTInscripcionP;
import Datatypes.DTInstituto;
import Datatypes.DTPrograma;
import Datatypes.DTUsuario;
import Datatypes.DatosEdicion;
import Datatypes.EstadoEdicion;
import Datatypes.EstadoInscripcion;
import LOGICA.FabricaLab;
import LOGICA.ISistema;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.Endpoint;

/**
 *
 * @author Surface
 */

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
public class Publicador {
    
   private ISistema ICU = FabricaLab.getInstance().getISistema();
   
   private Endpoint endpoint = null;
   
   public Publicador(){}
   
   
   @WebMethod(exclude = true)
   public void publicar() throws IOException{
         String home = System.getProperty("user.home");
        InputStream input = new FileInputStream(home + "\\config\\local_config.properties") ;
        Properties prop = new Properties();
        if (input == null) {
            System.out.println("Sorry, unable to find config.properties");
            return;
        }
        prop.load(input);
        String endpointURL = prop.getProperty("web.link");
        System.out.println(endpointURL);
        endpoint = Endpoint.publish(endpointURL, this);
   }
   
   @WebMethod(exclude = true)
   public Endpoint getEndpoint(){
       return endpoint;
   }
   
  @WebMethod
  public Set<DTUsuario> consultaUsuarios(){
      return ICU.consultaUsuarios();
  }
   
  @WebMethod
  public String checkEdicionCurso(String curso){
      return ICU.checkEdicionCurso(curso);
  }
  @WebMethod
  public void cambiarNota(String cur, String edi, String nick, int nota){
      ICU.cambiarNota(cur, edi, nick, nota);
  }
  
  @WebMethod
  public List<String> edicionesDocente(String curso, String nick){
      return ICU.edicionesDocente(curso, nick);
  }
  
  @WebMethod
  public ArrayList<String> EdicionesCurso(String curso){
      return ICU.EdicionesCurso(curso);
  }
  @WebMethod
  public ArrayList<String> ProgramasCurso(String curso){
      return ICU.ProgramasCursos(curso);
  }
  @WebMethod
  public ArrayList<String> cursosInstituto(String curso){
      return ICU.cursosInstituto(curso);
  }
  @WebMethod 
  public void altausuario(String nick, String nombre, String ape, String correo, String fecha, String contr, boolean docente, String imagendir, List inst) throws ParseException{
      Date fechaDate = null;
      if(fecha!=null){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        fechaDate = formato.parse(fecha);
       }
      DTUsuario datos = new DTUsuario(nick, nombre, ape, correo, fechaDate, contr, imagendir);
      ICU.altaUsuario (datos, docente, inst);
  }
   @WebMethod
   public boolean chekusuario(String nick){
      return ICU.chekusuario(nick);
   }
   
   @WebMethod
   public ArrayList<DatosEdicion> mostrarEdicionFULL(String nick){
      return ICU.mostrarEdicionFULL(nick);
   }
   
   @WebMethod
   public HashSet<DTCurso> datosCursos(){
        return (HashSet)ICU.datosCursos();
    }
   
   @WebMethod
   public void cambiarEstadoEdicion(String cur, String edi, EstadoEdicion estado){
       ICU.cambiarEstadoEdicion(cur, edi, estado);
   }
   
   @WebMethod
   public Set<DTPrograma> datosProgramas(){
        return (HashSet)ICU.datosProgramas();
   }
   @WebMethod
   public void agregarCursoPrograma(String nombreP, String nombreC){
       ICU.agregarCursoPrograma(nombreP, nombreC);
   }
   
   @WebMethod
   public boolean esDocente(String login){
       return ICU.esDocente(login);
   }
   
   @WebMethod
   public ArrayList<String> listarNombreProgramas(){
       return ICU.listarNombreProgramas();
   }
   
   @WebMethod
   public ArrayList<String> listarDocentesInstituto(String inst){
       return ICU.listarDocentesInstituo(inst);
   }
   
   @WebMethod
   public List mostrarCursos(){
       return ICU.mostrarCursos();
   }
   
   @WebMethod
   public ArrayList<String> mostrarProgramasUsuario(String nick){
       return ICU.mostrarProgramasUsuario(nick);
   }
   
   @WebMethod
   public void modificarDatosUsuario(String nick, String nombre, String apellido, String fechaNac) throws ParseException{
        Date fechaDate=null;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        if(fechaNac!=null && !fechaNac.equals("")){
            fechaDate = formato.parse(fechaNac);
        }
       ICU.modificarDatosUsuario(nick, nombre, apellido, fechaDate);
   }
   
   @WebMethod
   public DTPrograma obtenerPrograma(String nombreP){
       return ICU.obtenerPrograma(nombreP);
   }
   @WebMethod
   public DTUsuario obtenerUsuario(String nick){
       return ICU.obtenerUsuario(nick);
   }
   @WebMethod
   public DTCurso obtenerCurso(String nombre){
       return ICU.obtenerCurso(nombre);
   }
   @WebMethod
   public void registrarCurso(String inst,String nombre,String desc,String duracion, double cant_horas,double cant_cred, String fecha, String url, String imagenDir, List previas, List categorias) throws ParseException{
     
       DTCurso dtc = new DTCurso(nombre, desc, duracion ,cant_horas, cant_cred, new Date(), url, imagenDir,0);
       ICU.registrarCurso(inst, dtc, previas, categorias);
   }
   @WebMethod
   public ArrayList<String> listarInstitutos(){
       return ICU.listarInstitutos();
   }
    @WebMethod
    public ArrayList<String> listarCategorias(){
        return ICU.listarCategorias();
    }
   @WebMethod
   public DTUsuario obtenerUsuarioMail(String nick){
       return ICU.obtenerUsuarioMail(nick);
   }
   @WebMethod
   public ArrayList<DTInscripcionE> datosInscripEstudiante(String nick){
       return ICU.datosInscripEstudiante(nick);
   }
   
   @WebMethod
   public void crearPrograma(String nombre, String desc, String fecha_ini, String fecha_fin, String imagenDir) throws ParseException{
      Date fechaini = null;
      Date fechafin = null;
      if(fecha_ini!=null){
               SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
               fechaini = formato.parse(fecha_ini);
       }
       if(fecha_fin!=null){
               SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
               fechafin = formato.parse(fecha_fin);
       }
       
       DTPrograma datos = new DTPrograma(nombre, desc, fechaini, fechafin, new Date(), imagenDir);
       ICU.crearPrograma(datos);
   }
   
   @WebMethod
   public void modificarDatosPrograma(String nombre, String desc, String fecha_ini, String fecha_fin, String imagenDir) throws ParseException{
       Date fechaini = null;
      Date fechafin = null;
      if(fecha_ini!=null){
               SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
               fechaini = formato.parse(fecha_ini);
       }
       if(fecha_fin!=null){
               SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
               fechafin = formato.parse(fecha_fin);
       }
       
       DTPrograma datos = new DTPrograma(nombre, desc, fechaini, fechafin, new Date(), imagenDir);
       ICU.modificarDatosPrograma(datos);
   }
   
   @WebMethod
   public ArrayList<String> cursosPrograma(String nombreP){
       return ICU.cursosPrograma(nombreP);
   }
   
   @WebMethod
   public Set<String> categoriasProgramas(String nombreP){
       return ICU.CategoriasProgramas(nombreP);
   }
   
   @WebMethod
   public Set<DTCurso> DTCursosPrograma(String nombreP){
       return ICU.DTcursosPrograma(nombreP);
   }
   
   @WebMethod
   public DTInstituto obtenerInstitutoCurso(String nombreC){
       return ICU.obtenerInstitutoCurso(nombreC);
   }
   @WebMethod
   public void altaEdicionCurso(String nomCurso,String nombreEd,String fechaIniDate,String fechaFinDate,int cupos,String fechaPubDate,String imagenDir, List docentes) throws ParseException{
      Date fechaini = null;
      Date fechafin = null;
      Date fechapub = null;
      if(fechaIniDate!=null){
               SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
               fechaini = formato.parse(fechaIniDate);
       }
       if(fechaFinDate!=null){
               SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
               fechafin = formato.parse(fechaFinDate);
       }
       if(fechaPubDate!=null){
               SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
               fechapub = formato.parse(fechaPubDate);
       }
       DTEdicion datos = new DTEdicion(nombreEd, fechaini, fechafin, cupos,fechapub, imagenDir);
       ICU.altaEdicionCurso(nomCurso, datos, docentes);
   }
   
 
   
   @WebMethod
   public void crearInscripcionEstudiante(String nombreC, String nombreEdi, String nombreEst, String video){
       ICU.crearInscripcionEstudiante(nombreC, nombreEdi, nombreEst, new Date(), video);
   }
   
   @WebMethod
   public boolean checkincripcionPrograma(String nomP, String nickE){
       return ICU.checkincripcionPrograma(nomP, nickE);
   }
   
   @WebMethod 
   public void InscripcionPrograma(String nomP, String nickE){
       ICU.InscripcionPrograma(nomP, nickE, new Date());   
   }
   //********************************************************************************************
   @WebMethod
   public void altaInstituto(String nom){
   
       ICU.altaInstituto(nom);
   }
   
   @WebMethod
   public void modificarEstadoInscripcion(String cur, String ed, String est, EstadoInscripcion estado){
   
       ICU.modificarEstadoInscripcion(cur, ed, est, estado);
   }
  
   @WebMethod
   public ArrayList<DTInscripcionE> listarDatosInscEdicion(String nombreC, String nombreE){
   
       return ICU.listarDatosInscEdicion(nombreC, nombreE);
   }
   
   //*********************************************************************************************
   @WebMethod 
   public boolean checkCurso(String nomCurso){
       return ICU.checkCurso(nomCurso);
   }
   
   @WebMethod
   public boolean checkPrograma(String nombreP){
       return ICU.checkPrograma(nombreP);
   }
   
   @WebMethod
   public boolean chekuUsuarioEmail(String email){
       return ICU.chekuUsuarioEmail(email);
   }
   
   public ArrayList<String> listarCursosCategoria(String nomCat){
       return ICU.listarCursosCategoria(nomCat);
   }
   @WebMethod
   public ArrayList<String> ProgramasCursos(String nombreC){
   
      return ICU.ProgramasCursos(nombreC);
   }
   @WebMethod
   public DTEdicion datosEdiciones(String nombreCurso, String nombreEdicion){
      
       return ICU.datosEdicion(nombreCurso, nombreEdicion);
   
   }
   @WebMethod
   public boolean ExisteRegistroInscripcionE(String nombreEst, String nombreC, String nombreE){
   
       return ICU.ExisteRegistroInscripcionE(nombreEst, nombreC, nombreE);
   }
   
   @WebMethod
   public ArrayList<String> listarDocentesEdicion(String nombreC, String nombreE){
   
      return  ICU.listarDocentesEdicion(nombreC, nombreE);
   }
   
   @WebMethod
    public ArrayList<DTCurso> cursosEstudiante(String nick){
       return ICU.cursosEstudiante(nick);
    }
   
    @WebMethod
    public void calificarCurso(String nombreC, String nick, int nota){
       ICU.calificarCurso(nombreC, nick, nota);
    }
   
   @WebMethod
   public float obtenerCalifCurso(String nombreC){
       return ICU.obtenerCalifCurso(nombreC);
   }
   
   @WebMethod
   public boolean checkValoracionEst(String nombreC, String nick){
       return ICU.checkValoracionEst(nombreC, nick);
   }
   
   @WebMethod
   public float notaEstCurso(String nombreC, String nick){
       return ICU.notaEstCurso(nombreC, nick);
   }
   
   @WebMethod
   public ArrayList<DTComentarios> obtenerComentariosEdicion (String nomEd){
       return ICU.obtenerComentariosEdicion(nomEd);
   }
   
   @WebMethod
   public void comentarEdicion(String nick, String nomEd, String comentario){
       ICU.comentarEdicion(nick, nomEd, comentario);
   }
   
   
   public void responderComentario(String nick, String nomEd, String respuesta, Long id){
       ICU.responderComentario(nick, nomEd, respuesta, id);
   }
   
   @WebMethod
   public DTCurso cursoEdicion(String nombreE){
       return ICU.cursoEdicion(nombreE);
   }
   
   @WebMethod
   public void visitasCurso(String nombreC){
       ICU.visitasCurso(nombreC);
   }
   
   @WebMethod
   public DTCurso[] rankingCursos(){
       return ICU.rankingCursos();
   }
   @WebMethod
   public void actualizrEstadoPrograma(String nombreP, String nick){
       ICU.actualizrEstadoPrograma(nombreP, nick);
   }
   
   @WebMethod
   public DTInscripcionP obtenerEstadoProg(String nombreP, String nick){
       return ICU.obtenerEstadoProg(nombreP, nick);
   }
   
    public ArrayList<DTInscripcionE> datosInscripEstProg (String nombreP, String nick){
        return ICU.datosInscripEstProg(nombreP, nick);
    }
    
    @WebMethod
    public DTCurso obtenerCursoDeEdicion(String nomEdi){
        return ICU.obtenerCursoDeEdicion(nomEdi);
    }
    @WebMethod
    public boolean checkEdicion(String curso, String edicion){
        return ICU.checkEdicion(curso, edicion);
    }
    public boolean checkPrevias(String nick, String curso){
        return ICU.checkPrevias(nick, curso);
    }
}

