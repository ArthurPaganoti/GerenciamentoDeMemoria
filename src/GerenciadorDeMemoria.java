import java.util.*;

public class GerenciadorDeMemoria {
    private static final int TAMANHO_MEMORIA = 32; // Memória com 32 blocos
    private int[] memoria = new int[TAMANHO_MEMORIA];
    private int ponteiroProximoEncaixe = 0; // Posição inicial para o Next Fit
    private Random sorteador = new Random();

    public static void main(String[] args) {
        GerenciadorDeMemoria gerenciador = new GerenciadorDeMemoria();
        gerenciador.executarSimulacao();
    }

    public void executarSimulacao() {
        String[] algoritmos = {"FirstFit", "NextFit", "BestFit", "QuickFit", "WorstFit"};
        List<Processo> processos = criarProcessos();

        for (String algoritmo : algoritmos) {
            System.out.println("\n--- Executando: " + algoritmo + " ---");
            limparMemoria();

            for (int i = 0; i < 30; i++) {
                Processo processo = processos.get(sorteador.nextInt(processos.size()));
                boolean alocar = sorteador.nextBoolean();

                if (alocar) {
                    alocarProcesso(processo, algoritmo);
                } else {
                    desalocarProcesso(processo);
                }
                imprimirEstadoMemoria();
            }
            calcularFragmentacaoExterna();
        }
    }

    private void alocarProcesso(Processo processo, String algoritmo) {
        int indiceInicial = -1;
        switch (algoritmo) {
            case "FirstFit":
                indiceInicial = AlgoritmosDeGerenciamento.firstFit(memoria, processo.getTamanho());
                break;
            case "NextFit":
                indiceInicial = AlgoritmosDeGerenciamento.nextFit(memoria, processo.getTamanho(), ponteiroProximoEncaixe);
                ponteiroProximoEncaixe = (indiceInicial + processo.getTamanho()) % TAMANHO_MEMORIA;
                break;
            case "BestFit":
                indiceInicial = AlgoritmosDeGerenciamento.bestFit(memoria, processo.getTamanho());
                break;
            case "QuickFit":
                indiceInicial = AlgoritmosDeGerenciamento.quickFit(memoria, processo.getTamanho());
                break;
            case "WorstFit":
                indiceInicial = AlgoritmosDeGerenciamento.worstFit(memoria, processo.getTamanho());
                break;
        }

        if (indiceInicial != -1) {
            for (int i = indiceInicial; i < indiceInicial + processo.getTamanho(); i++) {
                memoria[i] = processo.getId();
            }
            System.out.println("Processo " + processo.getId() + " alocado no índice " + indiceInicial);
        } else {
            System.out.println("Falha ao alocar o processo " + processo.getId());
        }
    }

    private void desalocarProcesso(Processo processo) {
        boolean estaNaMemoria = false;
        for (int i = 0; i < TAMANHO_MEMORIA; i++) {
            if (memoria[i] == processo.getId()) {
                memoria[i] = 0;
                estaNaMemoria = true;
            }
        }
        if (estaNaMemoria) {
            System.out.println("Processo " + processo.getId() + " desalocado.");
        } else {
            System.out.println("Processo " + processo.getId() + " não encontrado na memória.");
        }
    }

    private void limparMemoria() {
        Arrays.fill(memoria, 0);
    }

    private void imprimirEstadoMemoria() {
        System.out.println("Estado atual do mapa: " + Arrays.toString(memoria));
    }

    private void calcularFragmentacaoExterna() {
        int fragmentos = 0;
        int menorProcesso = 2; // Menor tamanho da lista de processos
        int espacoLivre = 0;

        for (int bloco : memoria) {
            if (bloco == 0) {
                espacoLivre++;
            } else {
                if (espacoLivre > 0 && espacoLivre < menorProcesso) {
                    fragmentos++;
                }
                espacoLivre = 0;
            }
        }

        if (espacoLivre > 0 && espacoLivre < menorProcesso) {
            fragmentos++;
        }

        System.out.println("Fragmentação externa encontrada: " + fragmentos + " fragmentos.");
    }

    private List<Processo> criarProcessos() {
        return Arrays.asList(
                new Processo(1, 5), new Processo(2, 4), new Processo(3, 2),
                new Processo(4, 5), new Processo(5, 8), new Processo(6, 3),
                new Processo(7, 5), new Processo(8, 8), new Processo(9, 2),
                new Processo(10, 6)
        );
    }
}
