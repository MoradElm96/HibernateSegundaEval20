package POJOS;
// Generated 08-jun-2022 20:27:16 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Departamentos generated by hbm2java
 */
public class Departamentos  implements java.io.Serializable {


     private int deptNo;
     private String nombre;
     private String loc;
     private Set<Empleados> empleadoses = new HashSet<Empleados>(0);

    public Departamentos() {
    }

    public Departamentos(int deptNo, String nombre, String loc) {
        this.deptNo = deptNo;
        this.nombre = nombre;
        this.loc = loc;
    }

    
    public String Mostrar() {
        return "Departamentos{" + "deptNo=" + deptNo + ", nombre=" + nombre + ", loc=" + loc + ", empleadoses=" + empleadoses + '}';
    }

	
    public Departamentos(int deptNo) {
        this.deptNo = deptNo;
    }
    public Departamentos(int deptNo, String nombre, String loc, Set<Empleados> empleadoses) {
       this.deptNo = deptNo;
       this.nombre = nombre;
       this.loc = loc;
       this.empleadoses = empleadoses;
    }
   
    public int getDeptNo() {
        return this.deptNo;
    }
    
    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getLoc() {
        return this.loc;
    }
    
    public void setLoc(String loc) {
        this.loc = loc;
    }
    public Set<Empleados> getEmpleadoses() {
        return this.empleadoses;
    }
    
    public void setEmpleadoses(Set<Empleados> empleadoses) {
        this.empleadoses = empleadoses;
    }




}


