package com.example.demo.thread.thread2.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by eduardo on 16/11/17.
 */
public class ServidorTarefas {

    private final ServerSocket serverSocket;
    private final ExecutorService executorService;
    private final ExecutorService cachedThreadPool;
    private final ExecutorService poolDeThreads;
    private volatile boolean estaRodando;  //volatile signigica q as threads q fazem acesso a essa variavel nao
                                           // irao obter seus valores do cache de cada thread, mas sim da memoria
                                           //principal garantindo que obterao o valor correto.
    // private AtomicBoolean estaRodando; //uma alternativa é usar AtomicBoolean
                                          //a classe Atomic encapsula os tipos primitivos de forma volatil
    private BlockingQueue<String> filaComandos;

    public ServidorTarefas() throws IOException {
        System.out.println("servidor estabelecido");

        this.filaComandos = new ArrayBlockingQueue<String>(2);

        this.estaRodando = true;

        this.serverSocket = new ServerSocket(12345);

        //limitando a duas threads apenas dois clientes poderao conectar
        this.executorService = Executors.newFixedThreadPool(4, new FabricaDeThread()); //nessa implementacao
        // iremos passar a fabrica de threads como parametro pois iremos especificar como ela deve tratar as excecoes
        //caso a excecao nao seja tratada ela ira finalizar no metodo run, matando a thread e mostrando na tela

        //dessa forma o controle de forma e gerado automaticamente, se uma thread nao estiver senso utilizada ele encerra a conexao
        this.cachedThreadPool = Executors.newCachedThreadPool();

        //dessa forma apenas uma thread será instanciada
        this.poolDeThreads = Executors.newSingleThreadExecutor();

        iniciarConsumidores();
        /*Runnable tarefa = ....;
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(4);
        Através desse pool podemos agendar e executar uma tarefa periodicamente, por exemplo:
        pool.scheduleAtFixedRate(tarefa, 0, 60, TimeUnit.MINUTES); //executamos uma tarefa a cada 60 minutos*/
        //////////

        /*A classe Thread possui um método estático getAllStackTraces que devolve um conjunto com todas as threads da JVM. Veja o código:
        Set<Thread> todasAsThreads = Thread.getAllStackTraces().keySet();
        for (Thread thread : todasAsThreads) {
            System.out.println(thread.getName());
        }*/

        ///////////
        //As informações que a JVM expõe não param por ai. Também podemos "perguntar"
        // quantos processadores temos disponíveis. Para tal usamos a classe Runtime:
        /*Runtime runtime = Runtime.getRuntime();
        int qtdProcessadores = runtime.availableProcessors();
        System.out.println("Qtd de processadores: " + qtdProcessadores);*/
    }

    private void iniciarConsumidores() {
        for (int i = 0; i < 2; i++) {
            TarefaConsumir tarefaConsumir = new TarefaConsumir(filaComandos);
            this.executorService.execute(tarefaConsumir);
        }

    }

    public void rodar() throws IOException {
        while (this.estaRodando) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                System.out.println("Aceitando novo cliente porta: " + socket.getPort());

                DistribuirTarefas distribuirTarefas = new DistribuirTarefas(filaComandos, executorService, socket, this);
                executorService.execute(distribuirTarefas);
                //cachedThreadPool.execute(distribuirTarefas);
                //poolDeThreads.execute(distribuirTarefas);
            } catch (SocketException e) {
                System.out.println("Parando servidor: " + this.estaRodando);
            }
        }
    }

    public void parar() throws IOException {
        this.estaRodando = false;
        this.serverSocket.close();
        //this.executorService.shutdown();
        this.cachedThreadPool.shutdown();
        //this.poolDeThreads.shutdown();
    }
}

