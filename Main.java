import java.util.Random;

public class Main {

    // Nó da lista encadeada
    static class No {
        int valor;
        No proximo;

        No(int valor) {
            this.valor = valor;
            this.proximo = null;
        }
    }

    public static void main(String[] args) {
        final int TAMANHO = 100;
        final int QTD_ELEMENTOS = 1000;
        final int LIMITE = 100000;

        No[] tabela = new No[TAMANHO];
        Random random = new Random(1090);

        // Guarda os três últimos valores inseridos em listas encadeadas (colisões)
        int[] ultimosTres = new int[3];
        int qtdUltimos = 0;

        // Inserção dos 1000 elementos
        for (int i = 0; i < QTD_ELEMENTOS; i++) {
            int valor = random.nextInt(LIMITE + 1); // 0 a 100 000
            int posicao = valor % TAMANHO;

            No novo = new No(valor);

            if (tabela[posicao] == null) {
                // Posição livre: insere direto
                tabela[posicao] = novo;
            } else {
                // Colisão: insere no fim da lista encadeada da posição
                No atual = tabela[posicao];
                while (atual.proximo != null) {
                    atual = atual.proximo;
                }
                atual.proximo = novo;

                // Atualiza os três últimos valores inseridos em listas
                if (qtdUltimos < 3) {
                    ultimosTres[qtdUltimos] = valor;
                    qtdUltimos++;
                } else {
                    ultimosTres[0] = ultimosTres[1];
                    ultimosTres[1] = ultimosTres[2];
                    ultimosTres[2] = valor;
                }
            }
        }

        // Percorre a tabela para contar listas, posições vazias e achar a maior lista
        int qtdListas = 0;
        int posicoesVazias = 0;
        int maiorTamanho = 0;
        int posicaoMaior = -1;

        for (int i = 0; i < TAMANHO; i++) {
            if (tabela[i] == null) {
                posicoesVazias++;
            } else {
                int tamanho = 0;
                No atual = tabela[i];
                while (atual != null) {
                    tamanho++;
                    atual = atual.proximo;
                }

                // Só é lista encadeada se houve colisão (mais de 1 elemento)
                if (tamanho > 1) {
                    qtdListas++;
                }

                if (tamanho > maiorTamanho) {
                    maiorTamanho = tamanho;
                    posicaoMaior = i;
                }
            }
        }

        // Impressão dos resultados
        System.out.println("===== RESULTADOS =====");
        System.out.println();

        System.out.println("Maior lista encadeada: posicao " + posicaoMaior
                + " com " + maiorTamanho + " elementos");
        No atual = tabela[posicaoMaior];
        while (atual != null) {
            System.out.print(atual.valor);
            if (atual.proximo != null) {
                System.out.print(" -> ");
            }
            atual = atual.proximo;
        }
        System.out.println(" -> null");
        System.out.println();

        System.out.println("Quantidade de listas encadeadas geradas: " + qtdListas);
        System.out.println("Quantidade de posicoes vazias: " + posicoesVazias);
        System.out.println();

        System.out.print("Tres ultimos valores inseridos em listas encadeadas: ");
        for (int i = 0; i < qtdUltimos; i++) {
            System.out.print(ultimosTres[i]);
            if (i < qtdUltimos - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
