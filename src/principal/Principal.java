package principal;

import POJOS.Departamentos;
import POJOS.Empleados;
import java.awt.BorderLayout;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Sea la base datos empresa, compuesta de las tablas Departamentos y Empleados, que se
 * creará al ejecutar el script empresa.sql. Utilizando Hibernate, realizar las siguientes
 * operaciones en diferentes métodos.
 * a) Uno que inserte una fila en la tabla DEPARTAMENTOS, con los siguientes valores.
 * Número: 60
 * Nombre: MARKETING
 * Localización: GUADALAJARA
 * b) Uno que inserte un empleado en la tabla EMPLEADOS en el departamento 10.
 * emp_no: 4455
 * apellido: PÉREZ
 * oficio: VENDEDOR
 * dir: 7499
 * fecha_alt: 2020/02/25
 * salario:1500
 * comisión: 10
 * dept_no: 10
 * c) Uno denominado operaciones que realice lo siguiente:
 *  Muestra datos del departamento 20.
 *  Comprueba si existe el departamento 11, si no existe mostrará un mensaje
 * Indicándolo.
 *  Muestra los datos del departamento 10: Nombre, Localidad,
 * número de empleados y listado de los empleados. Mostrar apellido
 * y salario
 *  Mostrar los empleados del departamento 30.
 *  Borra el empleado 7369.
 *  Modificar el salario y comisión del empleado 7499, cuyos valores se
 * pedirán por teclado.
 * d) Finalmente, mostrar por pantalla como quedan las tablas Empleados y
 * Departamentos.
 *
 * @author alumno
 */
public class Principal {

    public static void main(String[] args) {
        
        SessionFactory sf = HibernateUtil.sessionFactory();
        Session sesion = sf.openSession();
        Transaction t = sesion.beginTransaction();
        
        
        Query qselectDepartamentos = sesion.createQuery("From Departamentos");
        List<Departamentos> listaDepartamentos = qselectDepartamentos.list();
        //mostramos la tabla de departamentos
        System.out.println("***************************----Tabla departamentos antes de actualizar----***************************");
        for (int i = 0; i <listaDepartamentos.size(); i++) {
            System.out.println(listaDepartamentos.get(i).Mostrar());
        }
              
        Departamentos depart;
        
        //apartado a
       
        //comprobamos si esta ese registro comparando el id
        int id= 60;
        depart = (Departamentos) sesion.get(Departamentos.class, id);
        
        if(depart != null){
            System.out.println("El departamento ya existe");
        } else{
            
        depart = new Departamentos(60,"MARKETING","GUADALAJARA");
        sesion.save(depart);
        
        System.out.println("Registro insertado correctamente");
        }
        
        for (int i = 0; i <listaDepartamentos.size(); i++) {
            System.out.println(listaDepartamentos.get(i).Mostrar());
        }
        
        //apartado b
        Empleados emple;
        
        emple = (Empleados) sesion.get(Empleados.class, 4455);//id
        if(emple==null){
            //comprobar que tiene el departamento
            depart = (Departamentos) sesion.get(Departamentos.class, 10);//para meter clave foranea del id del departamento
            if(depart == null){
                System.out.println("No se puede dar de alta el empleado pues ese departamento no existe");
                
            }else{
            Empleados emple1;
            emple1=(Empleados) sesion.get(Empleados.class, 7499);
            if(emple1==null){
                System.out.println("No se puede dar de alta al empleado pues el jefe no existe");
            }else{
            emple=new Empleados(4455, depart);
            emple.setApellido("Perez");
            emple.setOficio("Vendedor");
            emple.setEmpleados(emple1);
            //para la fecha
            Date fecha = new Date(2020,02,25);
                    
            emple.setFechaAlt(fecha);
            emple.setSalario((float)1500);
            emple.setComision((float)10);
            sesion.save(emple);
            
            }
            }
            
        }else{
            System.out.println("Ese numero de empleado ya existe");
        }
        
        
        //apartado c
        //mostrar datos departamento 20 
        
        depart = (Departamentos) sesion.get(Departamentos.class, 20);
        
        if(depart==null){
            System.out.println("Ese departamento no existe");
        }else{
            System.out.println("Los datos del departamento  " + depart.getDeptNo()+ " son "); 
             System.out.println(depart.Mostrar());  
        }
        
        //mostrar departamento 11 si existe
        
        depart= (Departamentos) sesion.get(Departamentos.class, 11);
        if(depart== null){
            System.out.println("El departamento no existe");
            
        }else{
            System.out.println("Los datos del departamento "+ depart.getDeptNo());
            System.out.println(depart.Mostrar());
                   
            
        }
       
        /*Muestra los datos del departamento 10: Nombre, Localidad,
        número de empleados y listado de los empleados. Mostrar apellido
        y salario*/

        depart=(Departamentos) sesion.get(Departamentos.class, 10);
        if(depart==null){
            System.out.println("Ese departamento no existe");
        }else{
            //ver este punto
            System.out.println(depart.Mostrar());
            Iterator it = depart.getEmpleadoses().iterator();
            while(it.hasNext()){
                emple=(Empleados) it.next();
                System.out.println(emple.getApellido()+ " " + emple.getSalario());
            }
            
        }
        
        //Mostrar los empleados del departamento 30.
        
        depart=(Departamentos) sesion.get(Departamentos.class, 30);
        if(depart==null){
            System.out.println("El departamento no existe");
        }else{
            System.out.println("Departamento 30");
            Iterator it= depart.getEmpleadoses().iterator();
            while(it.hasNext()){
                emple=(Empleados) it.next();
                System.out.println(emple.Mostrar());
                       
            }
        }
        
        //Borra el empleado 7369.
        
        emple =(Empleados) sesion.get(Empleados.class, 7369);
        if(emple==null){
            System.out.println("Ese empleado no existe, asi que no se puede borrar");
            
        }else{
            sesion.delete(emple);
        }
        
        /*Modificar el salario y comisión del empleado 7499, cuyos valores se
pedirán por teclado.*/
        emple= (Empleados) sesion.get(Empleados.class, 7499);
        if(emple==null){
            System.out.println("Ese empleado no existe, asi que no se puede actualizar");
        }else{
            emple.setComision((float)300);
            emple.setSalario((float)3000);
            sesion.update(emple);
        }
        
        /*Finalmente, mostrar por pantalla como quedan las tablas Empleados y
Departamentos*/
        
        qselectDepartamentos = sesion.createQuery("From Departamentos");
        listaDepartamentos = qselectDepartamentos.list();
        
        Query qselectEmpleados = sesion.createQuery("From Empleados");
        List<Empleados> listaEmpleados = qselectEmpleados.list();
        
        
        System.out.println("Final del ejercicio");
        System.out.println(" ");
        System.out.println("*********************************Departamentos****************************");
        for (int i = 0; i <listaDepartamentos.size(); i++) {
            System.out.println(listaDepartamentos.get(i).Mostrar());
        }
        
        System.out.println(" ");
        System.out.println("*********************************Empleados*********************************");
        for (int i = 0; i <listaEmpleados.size() ; i++) {
            System.out.println(listaEmpleados.get(i).Mostrar());
        }
        
        
        
        t.commit();
        sesion.close();
        System.exit(0);
        
    }
            
}
