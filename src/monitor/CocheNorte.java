package monitor;

class CocheNorte implements Runnable {
    private Puente puente;

    public CocheNorte(Puente puente) {
        this.puente = puente;
    }

    @Override
    public void run() {
        try {
            puente.cruzarPuenteDesdeElNorte();
            // Simulación del tiempo que lleva cruzar el puente
            Thread.sleep(2000);
            puente.salirPuenteDesdeElNorte();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

