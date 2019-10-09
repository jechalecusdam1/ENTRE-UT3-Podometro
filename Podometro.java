/**
 * La clase modela un sencillo podómetro que registra información
 * acerca de los pasos, distancia, ..... que una persona (hombre o mujer)
 * ha dado en una semana. 
 * 
 * @author    Jaione Echalecu Sagasti 
 * 
 */
public class Podometro {
    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7;

    private String marca;
    private double altura;
    private char sexo;
    private double longitudZancada;
    private int totalPasosLaborables;
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
    private int tiempo;
    private int caminatasNoche;

    /**
     * Inicializa el podómetro con la marca indicada por el parámetro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
        marca = queMarca;
        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() {
        return marca;

    }

    /**
     * Simula la configuración del podómetro.
     * Recibe como parámetros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna además el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        altura = queAltura  / 100;
        sexo = queSexo;
        if(sexo == MUJER){
            longitudZancada = Math.ceil( queAltura * ZANCADA_MUJER) / 100;
        }
        else {
            longitudZancada = Math.floor( queAltura * ZANCADA_HOMBRE) / 100;
        }
    }

    /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    pasos - el nº de pasos caminados
     *    dia - nº de día de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo)
     *    horaInicio – hora de inicio de la caminata
     *    horaFina – hora de fin de la caminata
     *    
     *    A partir de estos parámetros el método debe realizar ciertos cálculos
     *    y  actualizará el podómetro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,int horaFin) {
        switch(dia){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: 
            totalPasosLaborables += pasos;
            break;
            case SABADO:
            totalPasosSabado += pasos;
            break;
            case DOMINGO:
            totalPasosDomingo += pasos;
            break;
        }
        totalDistanciaSemana = (totalPasosLaborables + totalPasosSabado + totalPasosDomingo) * longitudZancada;
        tiempo += (horaFin / 100 * 60 + horaFin % 100) - (horaInicio / 100 * 60 + horaInicio % 100);

        if (horaInicio >= 2100)  {
            caminatasNoche++; 
        }

        totalDistanciaFinSemana = (totalPasosSabado + totalPasosDomingo)
        * longitudZancada;

    }

    /**
     * Muestra en pantalla la configuración del podómetro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        System.out.println("Configuración del podómetro");
        System.out.println("*********************************");
        System.out.println("");
        System.out.println("Altura: " + altura + " mtos");

        if (sexo == MUJER){
            System.out.println("Sexo: MUJER");
        }  
        else{
            System.out.println("Sexo: HOMBRE");
        }
        System.out.println("Longitud zancada: " + longitudZancada + " mtos");
        System.out.println("");
    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        System.out.println("Estadísticas ");
        System.out.println("********************************* ");
        System.out.println("");
        System.out.println("Distancia recorrida toda la semana: " + totalDistanciaSemana / 1000 + "km");
        System.out.println("Distancia recorrida fin de semana: " + totalDistanciaFinSemana / 1000 + "km");
        System.out.println("");
        System.out.println("Nº pasos dias laborables: " + totalPasosLaborables);
        System.out.println("Nº pasos SÁBADO: " + totalPasosSabado);
        System.out.println("Nº pasos DOMINGO: " + totalPasosDomingo);
        System.out.println("");
        System.out.println("Nº caminatas realizadas a partir de las 21h.: " + (int)caminatasNoche);
        System.out.println("");
        System.out.println("Tiempo total caminado en la semana: " + tiempo / 60 + "h. y " + tiempo % 60 + "m.");
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        if (totalPasosSabado > totalPasosDomingo && totalPasosSabado > totalPasosLaborables){

            return "SÄBADO";
        }
        if (totalPasosDomingo > totalPasosSabado && totalPasosDomingo > totalPasosLaborables){ 
            return "DOMINGO";
        }
        else {
            return "LABORABLE";
        }
    }

    /**
     * Restablecer los valores iniciales del podómetro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no varía
     *  
     */    
    public void reset() {

        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;

    }
}
