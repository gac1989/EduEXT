/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOGICA;

import Clases.*;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author arena
 */
public interface ISistema {
    
    public void altaUsuario(DTUsuario datos, boolean docente, List nomInst);
    
    public boolean chekusuario(String nick);
    
    public boolean chekuUsuarioEmail(String email);
    
    public boolean sintaxisEmailCorrecta(String email);
    
    public ArrayList<String> listarNickUsuarios();
    
    public DTUsuario obtenerUsuario(String nick);
    
    public void cambiarEstadoEdicion(String cur, String edi, EstadoEdicion estado);
    
    //public Usuario obtenerUsuario(String nick);
    
    public ArrayList<String> listarNombreProgramas();
    
    public ArrayList<String> listarNombreCursos();
    
    public Set<DTCurso> datosCursos();
    
    public Set<DTPrograma> datosProgramas();
    
    public void modificarDatosUsuario(String nick, String nuevoNom, String nuevoApe, Date nuevaFechaNac);
    
    public DTUsuario obtenerUsuarioMail(String mail);
    
    public boolean esDocente(String login);
       
    public void modificarEstadoInscripcion(String cur, String ed,String est, EstadoInscripcion estado);
    
    public void altaInstituto(String nom);
    
    public ArrayList<String> listarInstitutos();
    
    public DTInstituto buscarInstituto(String nombInst);
    
    public ArrayList<String> mostrarEdicion(String nick);
    
    public ArrayList<DatosEdicion> mostrarEdicionFULL(String nick);
    
    public void cambiarNota(String cur, String edi, String nick, int nota);
    
    public ArrayList<String> mostrarProgramasUsuario(String nick);
    
    public ArrayList<String> mostrarCursosDocente(String nick); // da error porque no esta implementado Curso
    
    public DTInstituto institutoEdicion(String nombreE);
    
    public DTInstituto obtenerInstitutoCurso(String nombreC);
    
    public DTCurso cursoEdicion(String nombreE);
     
    public List<String> edicionesDocente(String curso, String nick);
     
    public Set<DTPrograma> mostrarProgramaDoc(String nick); //no es necesaria
    
    public Set<DTUsuario> consultaUsuarios();
    
    public DTUsuario seleccionarUsuario(String nick);
   
    public void agregarCursoPrograma(String nombreP, String nombreC);
    
    public List mostrarCursos();
    
    public List mostrarProgramas();
    
    public DTCurso obtenerCurso(String nombreC);
    
    public DTPrograma obtenerPrograma(String nombreP);
    
    public boolean chequearInstituto(String nombreI);
    
    public void modificarNombreInstituto(String nombreI, String nuevonombre);
    
    public ArrayList<String> cursosInstituto(String nombreI);
    
    public ArrayList<String> cursosPrograma(String nombreP);
    
    public ArrayList<String> ProgramasCursos(String nombreC);
    
    public ArrayList<String> CategoriasCurso(String nombreC);
    
    public Curso obtenerCursoDelInstituto(String nombreI, String nombreC);
 
    public ArrayList<String> EdicionesCurso(String nombreCurso);
     
    public DTEdicion datosEdicion(String nombreCurso, String nombreEdicion);
    
    public boolean checkCurso(String nomCurso);
    
    public boolean checkPrograma(String datos);
    
    public void modificarDatosPrograma(DTPrograma datos);
    
    public void crearPrograma(DTPrograma datos); 
    
    public String checkEdicionCurso(String nombreC);
    
    public boolean InscripcionCorrectaEstEdi(String nombreC, String nombreE, Date fecha);
    
    public boolean InscripcionEstEdiIsNull(String nombreI, String nombreC, String nombreEdi, String nombreEst, Date fecha );
    
    public boolean ExisteRegistroInscripcionE(String nombreEst, String nombreC, String nombreE);
    
    public ArrayList<String> listarEstudiantes();
    
    public ArrayList<String> listarDocentesInstituo(String nomInst);
    
    public void crearInscripcionEstudiante(String nombreC, String nombreEdi, String nombreEst, Date fecha_insc, String video);
    
    public void modificarInscripcionEstudiante(String nombreC, String nombreEdi, String nombreEst, Date nueva_fecha);
    
    public Set<String> pickCurso (String nombreC);
    
    public void modRegAlumEdicion(Date nuevaFecha);
    
    public InscripcionE crearRegistro(String correo, Date FechaInsc);
    
    public Set<DTCurso> indicarInstitucion(String nombre);
    
    public DTCurso seleccionarCurso(String nombre);
    
    public DTCurso obtenerCursoDeEdicion(String nomEdi);
    
    public boolean seleccionarEdicionOPrograma(String nombre, DTEdicion edicion);
    
    public Set<DTEdicion> listaEdicion(String Instituto);
    
    public DTEdicion seleccionarEdicion(String nombre, DTEdicion edicion);
    
    public void editarEdicion(DTEdicion dataedicion);
    
    public void indicarInstitucion2(String nombreI); // que hace?
    
    public boolean indicarNombreCurso(String nombreC); // que hace?
    
    public void registrarCurso(String nomInst, DTCurso datoscurso, List previas, List categorias);
    
    
    public boolean checkExisteEdicionCurso(String nomCurso, String nomEdic);
    
    public void altaEdicionCurso(String nomCurso, DTEdicion datos, List docentes);
    
    public void editarCursoInst(DTCurso datos);
    
    public void cancelar();
    
    public void aceptar();
    
    public Categoria obtenerCategoria(String nombreCat);
    
    public void altaCategoria(String nombreCat);
    
    public void modificarCategoria(String viejo, String nuevo);
    
    public ArrayList<String> listarCategorias();
    
    public void agregarCategoriaCurso(String nombreCurso, List nombreCat);
    
    public Set<String> CategoriasProgramas(String nombreP);
    
    public boolean checkincripcionPrograma (String nomP, String nickE);
    
    public void modificarInscPrograma (String nomP, String nickE, Date nuevaFecha);
    
    public void InscripcionPrograma (String nomP, String nickE, Date fecha);
    
    public ArrayList<String> listarCursosCategoria (String nomCat);
    
    public Set<DTCurso> DTcursosPrograma(String nombreP);
    
    public ArrayList<String> listarDocentesEdicion(String nombreC, String nombreE);
    
    public ArrayList<DTInscripcionE> listarDatosInscEdicion(String nombreC, String nombreE);
    
    public ArrayList<DTInscripcionE> datosInscripEstudiante(String nick);
    
    public ArrayList<DTCurso> cursosEstudiante(String nick);
    
    public void calificarCurso(String nombreC, String nick, int nota);
    
    public float obtenerCalifCurso(String nombreC);
    
    public boolean checkValoracionEst(String nombreC, String nick);
    
    public float notaEstCurso(String nombreC, String nick);
    
    public void comentarEdicion(String nick, String nomEd, String comentario);
    
    public ArrayList<DTComentarios> obtenerComentariosEdicion (String nomEd);
    
    public void responderComentario(String nick, String nomEd, String respuesta, Long id);
    
    public void visitasCurso(String nombreC);
    
    public DTCurso[] rankingCursos();
     
    public void actualizrEstadoPrograma(String nombreP, String nick);
     
    public DTInscripcionP obtenerEstadoProg(String nombreP, String nick);
    
    public ArrayList<DTInscripcionE> datosInscripEstProg (String nombreP, String nick);
     
    public boolean checkEdicion(String curso, String edicion);
    
    public boolean checkPrevias(String nick, String curso);
}
