package src;

import java.util.ArrayList;
import java.io.*;


 class DVDtaller {
    private String titulo;
    private String categoria;
    private int anio;
    private String autor;
    private double precio;

    public DVDtaller(String titulo, String categoria, int anio, String autor, double precio) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.anio = anio;
        this.autor = autor;
        this.precio = precio;
    }

  
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}




 class ColeccionDVD {
    private ArrayList<DVDtaller> coleccion;

    public ColeccionDVD() {
        this.coleccion = new ArrayList<>();
    }

    public boolean agregarDVD(DVDtaller dvd) {
        return coleccion.add(dvd);
    }

    public boolean eliminarDVD(String titulo) {
        return coleccion.removeIf(dvd -> dvd.getTitulo().equalsIgnoreCase(titulo));
    }

    public boolean modificarDVD(String titulo, String nuevoTitulo, String nuevaCategoria, int nuevoAnio, String nuevoAutor, double nuevoPrecio) {
        for (DVDtaller dvd : coleccion) {
            if (dvd.getTitulo().equalsIgnoreCase(titulo)) {
                dvd.setTitulo(nuevoTitulo);
                dvd.setCategoria(nuevaCategoria);
                dvd.setAnio(nuevoAnio);
                dvd.setAutor(nuevoAutor);
                dvd.setPrecio(nuevoPrecio);
                return true;
            }
        }
        return false;
    }

    public ArrayList<DVDtaller> buscarPorCategoria(String categoria) {
        ArrayList<DVDtaller> resultados = new ArrayList<>();
        for (DVDtaller dvd : coleccion) {
            if (dvd.getCategoria().equalsIgnoreCase(categoria)) {
                resultados.add(dvd);
            }
        }
        return resultados;
    }

    public ArrayList<DVDtaller> obtenerTodos() {
        return new ArrayList<>(coleccion);
    }
}






 class Main {
    public static void main(String[] args) {
        ColeccionDVD coleccion = new ColeccionDVD();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int opcion;

        do {
            mostrarMenu();
            opcion = obtenerOpcion(reader);
            ejecutarOpcion(opcion, coleccion, reader);
        } while (opcion != 5);
    }

    private static void mostrarMenu() {
        System.out.println("=====================================");
        System.out.println("|         Menú de Opciones          |");
        System.out.println("=====================================");
        System.out.println("| 1. Agregar DVD                    |");
        System.out.println("| 2. Eliminar DVD                   |");
        System.out.println("| 3. Modificar DVD                  |");
        System.out.println("| 4. Mostrar DVDs por Categoría     |");
        System.out.println("| 5. Salir                          |");
        System.out.println("=====================================");
    }

    private static int obtenerOpcion(BufferedReader reader) {
        int opcion = -1;
        while (true) {
            try {
                System.out.print("Seleccione una opción: ");
                opcion = Integer.parseInt(reader.readLine());
                break;
            } catch (IOException | NumberFormatException e) {
                System.out.println("Opción inválida. Por favor, ingrese un número.");
            }
        }
        return opcion;
    }

    private static void ejecutarOpcion(int opcion, ColeccionDVD coleccion, BufferedReader reader) {
        switch (opcion) {
            case 1:
                agregarDVD(coleccion, reader);
                break;
            case 2:
                eliminarDVD(coleccion, reader);
                break;
            case 3:
                modificarDVD(coleccion, reader);
                break;
            case 4:
                mostrarDVDsPorCategoria(coleccion, reader);
                break;
            case 5:
                System.out.println("Saliendo del programa.");
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
    }

    private static void agregarDVD(ColeccionDVD coleccion, BufferedReader reader) {
        try {
            System.out.print("Título: ");
            String titulo = reader.readLine();
            System.out.print("Categoría: ");
            String categoria = reader.readLine();
            int anio = obtenerInt("Año de Liberación: ", reader);
            System.out.print("Autor: ");
            String autor = reader.readLine();
            double precio = obtenerDouble("Precio: ", reader);

            DVDtaller dvd = new DVDtaller(titulo, categoria, anio, autor, precio);
            if (coleccion.agregarDVD(dvd)) {
                System.out.println("DVD agregado correctamente.");
            } else {
                System.out.println("No se pudo agregar el DVD.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer la entrada. Por favor, intente de nuevo.");
        }
    }

    private static void eliminarDVD(ColeccionDVD coleccion, BufferedReader reader) {
        try {
            System.out.print("Título del DVD a eliminar: ");
            String titulo = reader.readLine();
            if (coleccion.eliminarDVD(titulo)) {
                System.out.println("DVD eliminado correctamente.");
            } else {
                System.out.println("No se encontró el DVD.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer la entrada. Por favor, intente de nuevo.");
        }
    }

    private static void modificarDVD(ColeccionDVD coleccion, BufferedReader reader) {
        try {
            System.out.print("Título del DVD a modificar: ");
            String titulo = reader.readLine();
            System.out.print("Nuevo Título: ");
            String nuevoTitulo = reader.readLine();
            System.out.print("Nueva Categoría: ");
            String nuevaCategoria = reader.readLine();
            int nuevoAnio = obtenerInt("Nuevo Año de Liberación: ", reader);
            System.out.print("Nuevo Autor: ");
            String nuevoAutor = reader.readLine();
            double nuevoPrecio = obtenerDouble("Nuevo Precio: ", reader);

            if (coleccion.modificarDVD(titulo, nuevoTitulo, nuevaCategoria, nuevoAnio, nuevoAutor, nuevoPrecio)) {
                System.out.println("DVD modificado correctamente.");
            } else {
                System.out.println("No se encontró el DVD.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer la entrada. Por favor, intente de nuevo.");
        }
    }

    private static void mostrarDVDsPorCategoria(ColeccionDVD coleccion, BufferedReader reader) {
        try {
            System.out.print("Categoría: ");
            String categoria = reader.readLine();
            ArrayList<DVDtaller> resultados = coleccion.buscarPorCategoria(categoria);
            if (resultados.isEmpty()) {
                System.out.println("No se encontraron DVDs en la categoría especificada.");
            } else {
                for (DVDtaller dvd : resultados) {
                    System.out.println("Título: " + dvd.getTitulo() + ", Autor: " + dvd.getAutor() + ", Año: " + dvd.getAnio() + ", Precio: " + dvd.getPrecio());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer la entrada. Por favor, intente de nuevo.");
        }
    }

    private static int obtenerInt(String mensaje, BufferedReader reader) {
        int valor = -1;
        while (true) {
            try {
                System.out.print(mensaje);
                valor = Integer.parseInt(reader.readLine());
                break;
            } catch (IOException | NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
            }
        }
        return valor;
    }

    private static double obtenerDouble(String mensaje, BufferedReader reader) {
        double valor = -1;
        while (true) {
            try {
                System.out.print(mensaje);
                valor = Double.parseDouble(reader.readLine());
                break;
            } catch (IOException | NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número decimal.");
            }
        }
        return valor;
    }
}
