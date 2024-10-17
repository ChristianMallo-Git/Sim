public class Sim {

    private String dni;
    private String nombre;
    private String apellido;
    private String sexo;
    private int animo;

    public Sim(String nombre, String apellido, String sexo, String dni) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.animo = 2;
    }

    public String getDni() {
        return dni;
    }

    public void comer() {
        System.out.println("\nEl Sim está comiendo \n");
        this.aumentarEstadoAnimo();
        this.mostrarEstadodeAnimo();
    }

    public void patalear() {
        System.out.println("\nEl Sim está pataleando \n");
    }

    public void correr() {
        System.out.println("\nEl Sim está corriendo \n");
    }

    public void andar() {
        System.out.println("\nEl Sim está andando \n");
    }

    public void teletransportar() {
        System.out.println("\nEl Sim está teletransportándose \n");
    }

    public void comprarCasa() {
        System.out.println("\nEl Sim está comprando una casa \n");
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getSexo() {
        return this.sexo;
    }

    public void aumentarEstadoAnimo() {
        this.animo += 1;
        System.out.println("\nHa mejorado el estado de animo del Sim \n");
        mostrarEstadodeAnimo();
    }

    public void disminuirEstadoAnimo() {
        this.animo -= 1;
        System.out.println("\nHa empeorado el estado de animo del Sim \n");
        mostrarEstadodeAnimo();
    }

    private void mostrarEstadodeAnimo() {

        switch (this.animo) {

            case 0:
                System.out.println("El Sim se está enfadado \n");
                break;

            case 1:
                System.out.println("El Sim se encuentra triste \n");
                break;

            case 2:
                System.out.println("El Sim está contento \n");
                break;

            case 3:
                System.out.println("El Sim está feliz \n");
                break;

            case 4:
                System.out.println("El Sim está muy Feliz \n");
                break;

            default:
                System.out.println("El Sim no puede estar más contento \n");
                break;
        }
    }

    @Override
    public String toString() {
        return "[dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", sexo=" + sexo + ", animo=" + animo
                + "]";
    }

}
