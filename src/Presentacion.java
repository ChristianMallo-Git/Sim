import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Presentacion {

    static HashMap<String, Sim> lista = new HashMap<>();
    static public Scanner scanner = new Scanner(System.in);
    static private String dniActualDeJuego = "";
    static private final String rutaArchivoSims = "src/File";

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        cargarSimsCreados();

        int selectedOption = 0;

        System.out.println("Bienvenido al mundo de los Sims!");

        do {
            System.out.println("1. Crear Sim \n" + "2. Elegir Sim creado \n" + "3. Salir");
            try {
                selectedOption = scanner.nextInt();
                selectedOptionManagement(selectedOption);
            } catch (InputMismatchException e) {
                System.out.println("\nPor favor introduce un número válido \n");
                scanner.nextLine();
            }

        } while (selectedOption != 3);

    }

    public static void selectedOptionManagement(int selectedOption) {

        switch (selectedOption) {
            case 1:

                System.out.println("Introduzca DNI: ");
                String dni = scanner.next();

                System.out.println("Introduzca nombre: ");
                String name = scanner.next();

                System.out.println("Introduzca apellido: ");
                String surname = scanner.next();

                String sex = "";
                System.out.println("Elije sexo de tu sim: \n" + "1.Hombre \n" + "2.Mujer \n");
                int opcionSexo = scanner.nextInt();
                if (opcionSexo == 1) {
                    sex = "Hombre";
                } else if (opcionSexo == 2) {
                    sex = "Mujer";
                }

                Sim newSim = crearSimps(name, surname, sex, dni);
                guardarSim(newSim);
                mostrarLista();

                break;

            case 2:
                mostrarLista();

                System.out.println("introduzca DNI de un sim existente: ");

                dniActualDeJuego = scanner.next();

                Sim simActual = lista.get(dniActualDeJuego);

                System.out.println("\nEmpiezas a jugar con " + simActual);

                int opcionDeJuegoSim = 0;

                do {
                    mostrarMenuOpcionesSim();
                    try {
                        opcionDeJuegoSim = scanner.nextInt();
                        selectedOptionAction(opcionDeJuegoSim, simActual);
                    }catch (InputMismatchException e){
                        System.out.println("\nPor favor introduce un número válido \n");
                        scanner.nextLine();
                    }
                } while (opcionDeJuegoSim != 9);

                break;
        }

    }

    private static void mostrarMenuOpcionesSim() {
        System.out.println("¿ Que quieres hacer con tu Sim ?");
        System.out.println("1. Comer \n" + "2. Patalear \n" + "3. Correr \n" + "4. Andar \n" + "5. Teletransportar \n"
                + "6. Comprarcasa \n" + "7. Aumentar estado de animo \n" + "8. Disminuir estado de animo \n"
                + "9.Salir");
    }

    private static Sim crearSimps(String name, String surname, String sex, String dni) {

        Sim newSim = new Sim(name, surname, sex, dni);
        lista.put(newSim.getDni(), newSim);
        return newSim;
    }

    private static void mostrarLista() {
        System.out.println("\n***Lista de Sims creados***");
        lista.values().forEach((sim) -> System.out.println(sim.toString()));
        System.out.println();

    }

    private static void guardarSim(Sim sim) {

        File archivosim = new File(rutaArchivoSims);

        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new FileWriter(archivosim), true);

            for (String dni : lista.keySet()) {

                Sim simAescribir = lista.get(dni);

                pw.write(simAescribir.getNombre() + ";" + simAescribir.getApellido() + ";" + simAescribir.getSexo()
                        + ";" + simAescribir.getDni() + "\n");

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (archivosim != null) {
                    pw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    private static void cargarSimsCreados() {

        BufferedReader br = null;

        try {

            if (!lista.isEmpty()) {
                lista.clear();
            }

            br = new BufferedReader(new FileReader(new File(rutaArchivoSims)));

            String linea = br.readLine();
            int counter = 1;
            System.out.println("***Sims cargados***");

            while (linea != null) {
                String[] partes = linea.split(";");
                Sim simAMostrar = new Sim(partes[0], partes[1], partes[2], partes[3]);

                lista.put(simAMostrar.getDni(), simAMostrar);

                System.out.println(counter + ". " + simAMostrar.toString());
                counter += 1;

                linea = br.readLine();
            }
            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != br) {
                    br.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }

    }

    private static void selectedOptionAction(int opcionDeJuegoSim, Sim simActual) {

        switch (opcionDeJuegoSim) {

            case 1:
                simActual.comer();
                break;
            case 2:
                simActual.patalear();
                break;
            case 3:
                simActual.correr();
                break;
            case 4:
                simActual.andar();
                break;
            case 5:
                simActual.teletransportar();
                break;
            case 6:
                simActual.comprarCasa();
                break;
            case 7:
                simActual.aumentarEstadoAnimo();
                break;
            case 8:
                simActual.disminuirEstadoAnimo();
                break;
            default:
                System.out.println("\nHa dejado de usar el perfil del Sim: " + simActual + "\n");
                break;
        }
    }
}