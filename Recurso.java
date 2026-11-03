# Sistema-Compartilhamento-Recursos
  import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Recurso {
    private String id;
    private String titulo;
    private boolean disponivel;

    public Recurso(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
        this.disponivel = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public boolean realizarEmprestimo() {
        if (this.disponivel) {
            this.disponivel = false;
            return true;
        }

        return false;
    }

    public boolean realizarDevolucao() {
        if (!this.disponivel) {
            this.disponivel = true;
            return true;
        }

        return false;
    }

    public void exibirInformacoes() {
        System.out.println("ID: " + id);
        System.out.println("Título/Nome: " + titulo);
        System.out.println("Status: " +
            (disponivel ? "Disponível" : "Emprestado"));
    }
}

class Livro extends Recurso {
    private String autor;
    private int numeroPaginas;

    public Livro(String id, String titulo, String autor, int numeroPaginas) {
        super(id, titulo);
        this.autor = autor;
        setNumeroPaginas(numeroPaginas);
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        if (numeroPaginas > 0) {
            this.numeroPaginas = numeroPaginas;
        } else {
            this.numeroPaginas = 1;
        }
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("--- [RECURSO: LIVRO] ---");
        super.exibirInformacoes();
        System.out.println("Autor: " + autor);
        System.out.println("Páginas: " + numeroPaginas);
        System.out.println("------------------------");
    }
}

class Equipamento extends Recurso {
    private String voltagem;
    private String condicao;

    public Equipamento(String id, String titulo, String voltagem, String condicao) {
        super(id, titulo);
        this.voltagem = voltagem;
        this.condicao = condicao;
    }

    public String getVoltagem() {
        return voltagem;
    }

    public void setVoltagem(String voltagem) {
        this.voltagem = voltagem;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("--- [RECURSO: EQUIPAMENTO] ---");
        super.exibirInformacoes();
        System.out.println("Voltagem: " + voltagem);
        System.out.println("Condição: " + condicao);
        System.out.println("------------------------------");
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Recurso> listaRecursos = new ArrayList<>();

        Livro livro1 = new Livro(
            "L01", "Entendendo Algoritmos",
            "Aditya Y. Bhargava", 256
        );

        Livro livro2 = new Livro(
            "L02", "Código Limpo",
            "Robert C. Martin", 464
        );

        Equipamento eq1 = new Equipamento(
            "E01", "Calculadora Científica TI-84",
            "Bateria", "Excelente"
        );

        Equipamento eq2 = new Equipamento(
            "E02", "Kit Arduino Uno R3",
            "5V USB", "Usado (Bom estado)"
        );

        listaRecursos.add(livro1);
        listaRecursos.add(livro2);
        listaRecursos.add(eq1);
        listaRecursos.add(eq2);

        int opcao = 0;

        while (opcao != 4) {

            System.out.println("\n=============================================");
            System.out.println(" SISTEMA DE COMPARTILHAMENTO DE RECURSOS ");
            System.out.println("=============================================");
            System.out.println("1. Listar todos os recursos e seus status");
            System.out.println("2. Realizar empréstimo de um recurso");
            System.out.println("3. Devolver um recurso");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
                System.out.println("Digite uma opção numérica válida!");
                continue;
            }

            switch (opcao) {

                case 1:

                    System.out.println("\n=== RECURSOS CADASTRADOS NO CAMPUS ===");

                    for (Recurso r : listaRecursos) {
                        r.exibirInformacoes();
                    }

                    break;

                case 2:

                    System.out.print("\nDigite o ID do recurso que deseja emprestar: ");
                    String idEmprestimo = scanner.nextLine().trim();

                    Recurso encontradoEmp = buscarRecursoPorId(
                        listaRecursos, idEmprestimo
                    );

                    if (encontradoEmp != null) {

                        if (encontradoEmp.realizarEmprestimo()) {
                            System.out.println(
                                "SUCESSO: O recurso '" +
                                encontradoEmp.getTitulo() +
                                "' foi emprestado com sucesso!"
                            );
                        } else {
                            System.out.println(
                                "ERRO: Este recurso já está emprestado."
                            );
                        }

                    } else {
                        System.out.println("Recurso não encontrado.");
                    }

                    break;

                case 3:

                    System.out.print("\nDigite o ID do recurso que deseja devolver: ");
                    String idDevolucao = scanner.nextLine().trim();

                    Recurso encontradoDev = buscarRecursoPorId(
                        listaRecursos, idDevolucao
                    );

                    if (encontradoDev != null) {

                        if (encontradoDev.realizarDevolucao()) {
                            System.out.println(
                                "SUCESSO: Recurso '" +
                                encontradoDev.getTitulo() +
                                "' devolvido com sucesso!"
                            );
                        } else {
                            System.out.println(
                                "AVISO: Este recurso já está disponível."
                            );
                        }

                    } else {
                        System.out.println("Recurso não encontrado.");
                    }

                    break;

                case 4:

                    System.out.println("\nSaindo do sistema... Bons estudos!");
                    break;

                default:

                    System.out.println("Opção inválida! Escolha entre 1 e 4.");
            }
        }

        scanner.close();
    }

    private static Recurso buscarRecursoPorId(
        List<Recurso> recursos, String id
    ) {

        for (Recurso r : recursos) {

            if (r.getId().equalsIgnoreCase(id)) {
                return r;
            }
        }

        return null;
    }
}
