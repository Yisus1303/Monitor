package monitor;

public class Main {
    public static void main(String[] args) {
        Puente puente = new Puente();

        // Crear coches del Norte y del Sur
        Thread cocheNorte = new Thread(new CocheNorte(puente));
        Thread cocheSur = new Thread(new CocheSur(puente));

        // Simular el paso de coches
        cocheNorte.start();
        cocheSur.start();
    }
}
