package monitor;

class CocheSur implements Runnable {
    private Puente puente;

    public CocheSur(Puente puente) {
        this.puente = puente;
    }

    @Override
    public void run() {
        try {
            puente.cruzarPuenteDesdeElSur();
            // Simulación del tiempo que lleva cruzar el puente
            Thread.sleep(2000);
            puente.salirPuenteDesdeElSur();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
