package monitor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Puente {
    private boolean cocheNorteCruzando;
    private boolean cocheSurEsperando;
    private int cochesEsperandoSur;

    private Lock lock;
    private Condition esperaSur;
    private Condition esperaNorte;

    public Puente() {
        cocheNorteCruzando = false;
        cocheSurEsperando = false;
        cochesEsperandoSur = 0;

        lock = new ReentrantLock();
        esperaSur = lock.newCondition();
        esperaNorte = lock.newCondition();
    }

    public void cruzarPuenteDesdeElNorte() throws InterruptedException {
        lock.lock();
        try {
            while (cocheSurEsperando || cocheNorteCruzando) {
                esperaNorte.await();
            }
            cocheNorteCruzando = true;
            System.out.println("Coche del Norte cruzando el puente");
        } finally {
            lock.unlock();
        }
    }

    public void salirPuenteDesdeElNorte() {
        lock.lock();
        try {
            cocheNorteCruzando = false;
            esperaSur.signal();
            esperaNorte.signal();
        } finally {
            lock.unlock();
        }
    }

    public void cruzarPuenteDesdeElSur() throws InterruptedException {
        lock.lock();
        try {
            cochesEsperandoSur++;
            while (cocheNorteCruzando) {
                cocheSurEsperando = true;
                esperaSur.await();
            }
            cocheSurEsperando = false;
            cochesEsperandoSur--;
            System.out.println("Coche del Sur cruzando el puente");
        } finally {
            lock.unlock();
        }
    }

    public void salirPuenteDesdeElSur() {
        lock.lock();
        try {
            esperaNorte.signal();
            if (cochesEsperandoSur == 0) {
                esperaSur.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}

