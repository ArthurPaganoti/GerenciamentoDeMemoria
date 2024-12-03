public class AlgoritmosDeGerenciamento {

    public static int firstFit(int[] memoria, int tamanho) {
        for (int i = 0; i <= memoria.length - tamanho; i++) {
            boolean encaixa = true;
            for (int j = 0; j < tamanho; j++) {
                if (memoria[i + j] != 0) { // Verifica se o bloco está ocupado
                    encaixa = false;
                    break;
                }
            }
            if (encaixa) return i; // Retorna o índice do primeiro encaixe
        }
        return -1; // Retorna -1 se não encontrar espaço
    }

    public static int nextFit(int[] memoria, int tamanho, int ponteiro) {
        int inicioBusca = ponteiro;
        for (int i = 0; i < memoria.length; i++) {
            int indice = (inicioBusca + i) % memoria.length;
            boolean encaixa = true;
            for (int j = 0; j < tamanho; j++) {
                if (indice + j >= memoria.length || memoria[indice + j] != 0) {
                    encaixa = false;
                    break;
                }
            }
            if (encaixa) return indice; // Retorna o índice do próximo encaixe
        }
        return -1; // Retorna -1 se não encontrar espaço
    }

    public static int bestFit(int[] memoria, int tamanho) {
        int melhorIndice = -1;
        int menorEspaco = Integer.MAX_VALUE;

        for (int i = 0; i < memoria.length; i++) {
            if (memoria[i] == 0) {
                int j = i;
                while (j < memoria.length && memoria[j] == 0) j++; // Conta o espaço livre
                int espacoLivre = j - i;

                if (espacoLivre >= tamanho && espacoLivre < menorEspaco) {
                    menorEspaco = espacoLivre;
                    melhorIndice = i;
                }
                i = j; // Avança para o próximo bloco ocupado
            }
        }
        return melhorIndice; // Retorna o índice do melhor encaixe
    }

    public static int quickFit(int[] memoria, int tamanho) {
        // Simula Quick Fit utilizando o algoritmo First Fit
        return firstFit(memoria, tamanho);
    }

    public static int worstFit(int[] memoria, int tamanho) {
        int piorIndice = -1;
        int maiorEspaco = -1;

        for (int i = 0; i < memoria.length; i++) {
            if (memoria[i] == 0) {
                int j = i;
                while (j < memoria.length && memoria[j] == 0) j++; // Conta o espaço livre
                int espacoLivre = j - i;

                if (espacoLivre >= tamanho && espacoLivre > maiorEspaco) {
                    maiorEspaco = espacoLivre;
                    piorIndice = i;
                }
                i = j; // Avança para o próximo bloco ocupado
            }
        }
        return piorIndice; // Retorna o índice do pior encaixe
    }
}
